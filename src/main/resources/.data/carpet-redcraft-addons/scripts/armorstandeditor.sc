import('math','_euclidean');
__config() -> {
    'scope' -> 'global',
    'commands' -> {
        'list' -> '_list_poses',
        'load <posename>' -> '_load_pose',
        'save <name>' -> '_save_pose',
        'remove <posename>' -> ['_remove_pose', false],
        'remove <posename> force' -> ['_remove_pose', true],
        'save <name> <nbt>' -> '_save_pose_nbt'
    },
    'arguments' -> {
        'posename' -> {
            'type' -> 'identifier',
            'suggester' -> _(args) -> keys(global_poses);
        },
        'name' -> {
            'type' -> 'identifier',
            'suggest' -> [];
        },
        'nbt' -> {
            'type' -> 'text',
            'suggester' -> _(args) -> values(global_poses)
        }
    }
};

// COSTANTS
global_tool = '_hoe$';

global_parts = {
    'Body' -> [0, 21, 0],
    'Head' -> [0, 23, 0],
    'LeftArm' -> [5, 21.5, 0],
    'RightArm' -> [-5, 21.5, 0],
    'LeftLeg' -> [1.9, 12, 0],
    'RightLeg' -> [-1.9, 12, 0]
};

// TAGS UTILS
_toggle_tag(entity, tag) -> (
    if(query(entity, 'has_scoreboard_tag', tag),
        modify(entity, 'clear_tag', tag);
        false;
    , // else
        modify(entity, 'tag', tag);
        true
    )
);

_tag_match(entity, match) -> (
    tags = query(entity, 'scoreboard_tags');
    reduce(tags, _ ~ match || _a, null)
);

_clear_matching_tag(entity, match) -> (
    for(query(entity, 'scoreboard_tags'),
        if(_ ~ match,
            modify(entity, 'clear_tag', _)
        )
    )
);

// MATRIX UTILS
global_identity = [[1,0,0],[0,1,0],[0,0,1]];
// Returns unit vector of given yaw and pitch
_direction(yaw, pitch) -> [-sin(yaw)*cos(pitch), -sin(pitch), cos(pitch)*cos(yaw)];
// Returns an array of transformation matrices
// to apply a rotation on pitch, yaw, roll axes
_t_matrix(pitch, yaw, roll) -> (
    [[
        [1,0,0],
        [0,cos(pitch),sin(pitch)],
        [0,-sin(pitch),cos(pitch)]
    ],[
        [cos(yaw),0,sin(yaw)],
        [0,1,0],
        [-sin(yaw),0,cos(yaw)]
    ],[
        [cos(roll),-sin(roll),0],
        [sin(roll),cos(roll),0],
        [0,0,1],
    ]];
);
// Traslate vector
_t(v, m) -> reduce(m, _a + v:_i * _, v * 0);
// Apply all traslation matrices to vectors
_t_apply(v, m) -> map(v,reduce(m, _t(_a, _), _));
// Return [pitch, yaw, roll] array from a rotation matrix
_rotation_matrix_to_euler_angles(matrix) -> (
    if(matrix:0:2 == 1 || matrix:0:2 == -1,
        yaw = 0;
        dlta = atan2(matrix:0:1, matrix:0:2);
        if(matrix:0:2 == -1,
            yaw = 180;
            pitch = roll + dlta,
        // Else
            yaw = -180;
            pitch = -roll + dlta;
        ),
    // Else
        yaw = -asin(matrix:0:2);
        pitch = atan2(matrix:1:2/cos(E2), matrix:2:2/cos(E2));
        roll = atan2(matrix:0:1/cos(E2), matrix:0:0/cos(E2));
    );
    [pitch, -yaw, -roll]
);

// ARMORSTAND GETTER
// Return a map with the position of each entity's part
_parts(e) -> (
    ret = {};
    for(global_parts,
        p = global_parts:_;
        ret:_ = [
            (p:0 * cos(e~'yaw') - p:2 * sin(e~'yaw')) * e~'width' / 8,
            p:1 * e~'height'/32,
            (p:0 * sin(e~'yaw') + p:2 * cos(e~'yaw')) * e~'width' / 8
        ]
    );
    ret
);

// Return the nearest entity's part to the hitvec
_nearest_part(entity, hitvec) -> (
    sd = null;
    for(parts = _parts(entity),
        d = _euclidean(hitvec, parts:_);
        if((sd == null || d < sd) && (!_ ~ 'Arm$' || entity ~ 'nbt':'ShowArms'),
            sd = d;
            ret = _
        )
    );
    ret
);

// CHECKS
_has_tool(player) -> (
    player ~ 'gamemode' != 'spectator' &&
    (item_tuple = player ~ 'holds') &&
    item_tuple:0 ~ global_tool != null
);

_edit_mode(entity) -> (
    query(entity, 'has_scoreboard_tag', 'gb.armorstandeditor')
);

_moving_mode(entity) -> (
    query(entity, 'has_scoreboard_tag', 'gb.armorstandeditor.moving');
    player(_tag_match(entity ,'(?:gb\\.armorstandeditor\\.moving\\.player\\.)(\\w+)'))
);

// ACTIONS
_toggle_edit_mode(player, entity) ->
if(_toggle_tag(entity, 'gb.armorstandeditor'),
    // Glowing
    modify(entity, 'nbt_merge', '{Glowing:true}');
    // Add DisabledSlots
    modify(entity, 'tag', 'gb.armorstandeditor.disabledslots', 'gb.armorstandeditor.disabledslots.' + str(entity ~ 'nbt':'DisabledSlots'));
    modify(entity, 'nbt_merge', '{DisabledSlots:' + str(2^22-1) + '}');
    // Feedback
    _sound(player, pos(entity), 'success')
, // else
    // Glowing
    modify(entity, 'nbt_merge', '{Glowing:false}');
    // Remove DisabledSlots
    if(query(entity, 'has_scoreboard_tag', 'gb.armorstandeditor.disabledslots'),
        ds = _tag_match(entity, '(?:gb\\.armorstandeditor\\.disabledslots\\.)(\\d+)');
        modify(entity, 'nbt_merge', '{DisabledSlots:' + ds + '}');
        modify(entity, 'clear_tag', 'gb.armorstandeditor.disabledslots', 'gb.armorstandeditor.disabledslots.' + ds)
    );
    // Feedback
    _sound(player, pos(entity), 'error');
    // Undo (exit edit mode)
    if(player_moving = _moving_mode(entity),
        _undo_pose(player_moving, entity, part)
    )
);

_init_pose(entity) -> (
    x = _tag_match(entity, '(?:gb\\.armorstandeditor\\.moving\\.x\\.)(-?\\d+(?:\\.\\d+)?)');
    y = _tag_match(entity, '(?:gb\\.armorstandeditor\\.moving\\.y\\.)(-?\\d+(?:\\.\\d+)?)');
    z = _tag_match(entity, '(?:gb\\.armorstandeditor\\.moving\\.z\\.)(-?\\d+(?:\\.\\d+)?)');
    [x,y,z]
);

_undo_pose(player, entity, part) -> (
    modify(entity, 'nbt_merge', str('{Pose:{%s:[%.03ff,%.03ff,%.03ff]}}', [part, ... _init_pose(entity)]));
    // Feedback
    _sound(player, pos(entity), 'error');
    // Stop moving
    _stop_moving(entity)
);

_undo_move(player, entity) -> (
    modify(entity, 'pos', map(_init_pose(entity), number(_)));
    // Feedback
    _sound(player, pos(entity), 'error');
    // Stop moving
    _stop_moving(entity)
);

_confirm_move(player, entity) -> (
    // Feedback
    _sound(player, pos(entity), 'success');
    // Stop moving
    _stop_moving(entity)
);

_stop_moving(entity) -> (
    // Remove Tags
    _clear_matching_tag(entity, 'gb\\.armorstandeditor\\.moving');
    // Remove NoClip
    if(query(entity, 'has_scoreboard_tag', 'gb.armorstandeditor.noclip'),
        modify(entity, 'no_clip', false);
        modify(entity, 'clear_tag', 'gb.armorstandeditor.noclip')
    );
    // Remove NoGravity
    if(query(entity, 'has_scoreboard_tag', 'gb.armorstandeditor.nogravity'),
        ds = _tag_match(entity, '(?:gb\\.armorstandeditor\\.nogravity\\.)(\\d+)');
        modify(entity, 'gravity', !number(ds));
        modify(entity, 'clear_tag', 'gb.armorstandeditor.nogravity', 'gb.armorstandeditor.nogravity.' + ds)
    );
    // Stop ticking
    entity_event(entity, 'on_tick', null)
);

_change_pose(player, entity, part) -> (
    init_pose = parse_nbt(entity ~ 'nbt':'Pose':part);
    if(!init_pose || init_pose == 'null', init_pose =  [0,0,0]);

    init_orientation = _t_apply(global_identity, _t_matrix(...init_pose));
    init_orientation = _t_apply(init_orientation, _t_matrix(null, entity ~ 'yaw', null));

    modify(entity, 'tag',
        'gb.armorstandeditor.moving',
        'gb.armorstandeditor.moving.player.' + lower(player ~ 'command_name'),
        str('gb.armorstandeditor.moving.x.%.03f', init_pose:0),
        str('gb.armorstandeditor.moving.y.%.03f', init_pose:1),
        str('gb.armorstandeditor.moving.z.%.03f', init_pose:2)
    );
    // Ticking
    entity_event(entity, 'on_tick', '_change_pose_tick', player, part, init_orientation, player ~ 'yaw', player ~ 'pitch');
    // Feedback
    _sound(player, pos(entity), 'click');
);

_change_pose_tick(entity, player, part, init_orientation, init_yaw, init_pitch) -> (
    // Undo (changing item)
    if(!_has_tool(player), _undo_pose(player, entity, part); return());
    // Confirm (unsneaking)
    if(!player ~ 'sneaking', _confirm_move(player, entity); return());

    // Move
    pos = parse_nbt(entity ~ 'nbt':'Pose':part) || [0,0,0];
    unit_vectors = _t_apply(init_orientation, _t_matrix(null, -init_yaw, null));
    unit_vectors = _t_apply(unit_vectors, _t_matrix(player ~ 'pitch' - init_pitch, player ~ 'yaw', null));
    // _draw_unit_vector(player, entity, part, init_yaw, init_pitch, unit_vectors);
    unit_vectors = _t_apply(unit_vectors, _t_matrix(null, -entity ~ 'yaw', null));
    args = _rotation_matrix_to_euler_angles(unit_vectors);
    modify(entity, 'nbt_merge', str('{Pose:{%s:[%.03ff,%.03ff,%.03ff]}}', [part, ... args]));
);

_move(player, entity, hitvec) -> (
    init_pos = pos(entity);
    modify(entity, 'tag',
        'gb.armorstandeditor.moving',
        'gb.armorstandeditor.moving.player.' + lower(player ~ 'command_name'),
        str('gb.armorstandeditor.moving.x.%.03f', init_pos:0),
        str('gb.armorstandeditor.moving.y.%.03f', init_pos:1),
        str('gb.armorstandeditor.moving.z.%.03f', init_pos:2)
    );
    // Add NoGravity and NoClip
    modify(entity, 'tag', 'gb.armorstandeditor.nogravity', 'gb.armorstandeditor.nogravity.' + number(!entity ~ 'gravity'), 'gb.armorstandeditor.noclip');
    modify(entity, 'gravity', false);
    modify(entity, 'no_clip', true);
    // Absolute direction
    vector = pos(entity) + hitvec - pos(player) - [0, player ~ 'eye_height', 0];
    distance = sqrt(reduce(vector, _a + _*_, 0));
    // Ticking
    entity_event(entity, 'on_tick', '_move_tick', player, distance, hitvec, player ~ 'selected_slot');
    // Feedback
    _sound(player, pos(entity), 'click');
);

_move_tick(entity, player, distance, hitvec, selected_slot) -> (
    // Confirm (unsneaking)
    if(!player ~ 'sneaking', _confirm_move(player, entity); return());
    // Undo (change slot)
    if(selected_slot != player ~ 'selected_slot', _undo_move(player, entity); return());
    // Relative direction
    vector = distance * player ~ 'look';
    pos = [0, player ~ 'eye_height', 0] + vector - hitvec;
    // Move
    modify(entity, 'pos', pos(player) + pos)
);

_put_on_head(player, entity) -> (
    armor_items = parse_nbt(entity ~ 'nbt':'ArmorItems');
    head = armor_items:3;
    item_tuple = player ~ 'holds' || ['air', 0, {}];
    armor_items:3 = {'id' -> item_tuple:0, 'Count' -> item_tuple:1, 'tag' -> item_tuple:2 || {}};
    inventory_set(player, player ~ 'selected_slot', head:'Count', head:'id', encode_nbt(head:'tag' || {}));
    modify(entity, 'nbt_merge', encode_nbt({'ArmorItems' -> armor_items}, true));
    // Feedback
    _sound(player, pos(entity), 'click');
);

_special_actions(item, count, entity) -> (
 if(item == 'stick' && count >= 2 && !entity ~ 'nbt' : 'ShowArms',
        modify(entity, 'nbt_merge', '{ShowArms:true}');
        remove_count = 2;
    , item == 'smooth_stone_slab' && !entity ~ 'nbt' : 'NoBasePlate',
        modify(entity, 'nbt_merge', '{NoBasePlate:true}')
    , item == 'feather' && entity ~ 'gravity',
        modify(entity, 'tag', 'gb.armorstandeditor.nogravity.1');
        modify(entity, 'gravity', false)
    , item == 'phantom_membrane' && !entity ~ 'nbt' : 'Invisible',
        modify(entity, 'tag', 'gb.Invisible');
        modify(entity, 'nbt_merge', '{Invisible:true}')
    , item == 'ender_eye' && entity ~ 'nbt' : 'Invisible',
        modify(entity, 'nbt_merge', '{Invisible:false}');
        modify(entity, 'tag', 'gb.Visible')
    , item == 'campfire' && !query(entity, 'has_scoreboard_tag', 'gb.SmokeParticles'),
        modify(entity, 'tag', 'gb.CampfireSmokeParticles', 'gb.SmokeParticles')
    , item == 'soul_campfire' && !query(entity, 'has_scoreboard_tag', 'Sgb.mokeParticles'),
        modify(entity, 'tag', 'gb.SoulCampfireSmokeParticles', 'gb.SmokeParticles')
    , item == 'stone_button' && !entity ~ 'nbt' : 'Small',
        modify(entity, 'nbt_merge', '{Small:true}');
        modify(entity, 'tag', 'gb.StoneSmall')
    , item == 'polished_blackstone_button' && !entity ~ 'nbt' : 'Small',
        modify(entity, 'nbt_merge', '{Small:true}');
        modify(entity, 'tag', 'gb.BlackstoneSmall')
    , item == 'stone_pressure_plate' && entity ~ 'nbt' : 'Small',
        modify(entity, 'nbt_merge', '{Small:false}');
        modify(entity, 'tag', 'gb.StoneBig')
    , item == 'polished_blackstone_pressure_plate' && entity ~ 'nbt' : 'Small',
        modify(entity, 'nbt_merge', '{Small:false}');
        modify(entity, 'tag', 'gb.BlackstoneBig')
    , item == 'blaze_powder' && !query(entity, 'has_scoreboard_tag', 'gb.FlameParticles'),
        modify(entity, 'tag', 'gb.FlameParticles')
    , // Else
        return(false)
    );
    true
);

// SOUNDS
_sound(player, pos, type) ->
if(
    type == 'success', sound('minecraft:block.note_block.bit', pos, 1, 2, 'player'),
    type == 'click', sound('minecraft:block.note_block.bit', pos, 1, 1, 'player'),
    type == 'error', sound('minecraft:block.note_block.bit', pos, 1, 0.5, 'player')
);

// EVENTS
__on_player_attacks_entity(player, entity) ->
if(entity ~ 'type' == 'armor_stand' && _has_tool(player),
    _toggle_edit_mode(player, entity);

    if(player ~ 'gamemode' == 'creative',
        schedule(0, _(outer(entity)) -> spawn('armor_stand', pos(entity), entity ~ 'nbt'))
    );
);

__on_player_interacts_with_entity_at(player, entity, hand, hitvec) ->  // Carpet RedCraft Addons
if(hand == 'mainhand' && _edit_mode(entity),
    if(player_moving = _moving_mode(entity),
        // Confirm (clicking again)
        _confirm_move(player_moving, entity)
    , player ~ 'sneaking' && _has_tool(player), // Elif
        // Change pose
        part = _nearest_part(entity, hitvec);
        _change_pose(player, entity, part)
    , // Else
        item_tuple = player ~ 'holds';
        if(player ~ 'sneaking' && item_tuple,
            [item, count, nbt] = item_tuple;
            remove_count = 1;
            if(_special_actions(item, count, entity),
                modify(player, 'swing', hand);
                sound('minecraft:item.dye.use', pos(entity), 1.0, 1, 'neutral');
                if(player ~ 'gamemode' != 'creative',
                    inventory_set(player, if(hand == 'mainhand', player ~ 'selected_slot', -1), count - remove_count, item, nbt)
                );
                return()
            )
        );
        if(player ~ 'sneaking',
            // Move armostand
            _move(player, entity, hitvec)
        , // Else
            // Set item on head
            _put_on_head(player, entity)
        )
    )
);

// DEBUG UTIL
_draw_unit_vector(player, entity, part, init_yaw, init_pitch, unit_vectors) -> (
    p = pos(entity)+_parts(entity):part;
    draw_shape('line',2,'from',p,'to',p + _direction(init_yaw, init_pitch));
    draw_shape('line',2,'from',p,'to',p + player ~ 'look','color', 0xFFFFFF);
    c = [0xFF0000FF, 0xFF00FF, 0xFFFF];
    for(unit_vectors, draw_shape('line',2,'from',p,'to',p+_,'color', c:_i));
);

// COMMANDS
_load_pose(name) -> (
    if(!(entity = player() ~ ['trace',5,'entities']) || entity ~ 'type' != 'armor_stand',
        _sound(player(), pos(player()), 'error');
        exit(print(player(), format('r You must look an Armor Stand to run this command.')))
    );
    if(!(pose = global_poses:name) && !(pose = global_poses:(lower(player() + ':' + name))),
        _sound(player(), pos(entity), 'error');
        exit(print(player(), format('r Unvalid pose name.')))
    );
    modify(entity, 'nbt_merge', str('{Pose:%s}', pose));
    _sound(player(), pos(entity), 'success');
);

_save_pose(name) -> (
    if(!(entity = player() ~ ['trace',5,'entities']) || entity ~ 'type' != 'armor_stand',
        _sound(player(), pos(player()), 'error');
        exit(print(player(), format('r You must look an Armor Stand to run this command.')))
    );
    _save_pose_nbt(name, entity ~ 'nbt':'Pose');
);

_save_pose_nbt(name, nbt) -> (
    try(nbt(nbt),
    // catch
        _sound(player(), pos(player()), 'error');
        exit(print(player(), format('r Unvalid nbt pose.')))
    );
    if(!name ~ ':', name = lower(player()) + ':' + name);
    namespace = name ~ '^[^:]*';
    if(!player() ~ 'permission_level' && namespace != lower(player()),
        _sound(player(), pos(player()), 'error');
        exit(print(player(), format('r You can\'t save a pose with this name.')))
    );
    global_poses:name = nbt;
    write_file('poses', 'json', global_poses);
    print(player(), format('l Saved "', 'li '+name, 'l " pose.'));
    _sound(player(), pos(player()), 'success');
);

_remove_pose(name, force) -> (
    if(!has(global_poses, name) && !has(global_poses, name = lower(player()) + ':' + name),
        _sound(player(), pos(player()), 'error');
        exit(print(player(), format('r Unvalid pose name.')))
    );
    namespace = name ~ '^[^:]*';
    if(!player() ~ 'permission_level' && namespace != lower(player()),
        _sound(player(), pos(player()), 'error');
        exit(print(player(), format('r You can\'t remove the pose with this name.')))
    );
    if(force,
        delete(global_poses:name);
        write_file('poses', 'json', global_poses);
        print(player(), format('l Removed "', 'li '+name, 'l " pose.'));
        _sound(player(), pos(player()), 'success');
    , // Else
        command = system_info('app_name');
        ce = ['^ Click here to delete', str('!/%s remove %s force', command, name)];
        print(player(), format('y Click here to delete "', ...ce, 'yi ' + name, ...ce, 'y " pose.', ...ce));
        _sound(player(), pos(player()), 'click');
    )
);

_default_poses() -> (
    poses = {
        'default' -> '{Head:[0f,0f,0f],Body:[0f,0f,0f],LeftArm:[-10f,0f,-10f],RightArm:[-15f,0f,10f],LeftLeg:[-1f,0f,-1f],RightLeg:[1f,0f,1f]}'
    };
    write_file('poses', 'json', poses);
    poses
);

global_poses = read_file('poses', 'json') || _default_poses();

_list_poses() -> (
    print(player(), format('b Aviable poses:'));
    command = system_info('app_name');
    for(keys(global_poses),
        print(player(), format('  ● ', if(_ ~ ':', ' ', 'g ') + _, '^ Click to load this pose', str('!/%s load %s', command, _)));
    );
    _sound(player(), pos(player()), 'click');
);
