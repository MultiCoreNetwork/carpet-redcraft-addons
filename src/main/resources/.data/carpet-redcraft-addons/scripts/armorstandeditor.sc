import('math','_euclidean');

// DESCRIZIONE:
// Picchiando un supporto per armature con una zappa esso entrerà in modalità editing
// Quando un armorstand è in modalità editing, sarà possibile piazzarlgi blocchi in testa
// con il tasto destro, muoverlo in giro con shift + tasto destro e cambiargli la posa
// usando una zappa.

// COSTANTS
global_tool = '_hoe$';

global_parts = {
    'Body' -> [0, 21, 0],
    'Head' -> [0, 22, 0],
    'LeftArm' -> [5, 21.5, 0],
    'RightArm' -> [-5, 21.5, 0],
    'LeftLeg' -> [1.9, 12, 0],
    'RightLeg' -> [-1.9, 12, 0]
};

global_modes = [
    'gb.armorstandeditor.mode.pitch',
    'gb.armorstandeditor.mode.yaw',
    'gb.armorstandeditor.mode.roll',
    'gb.armorstandeditor.mode.test',
];

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

_cycle_tag(entity, ... tags) -> if(tags,
    for(entity ~ 'scoreboard_tags',
        if((i = tags ~ _) != null,
            index = i;
        );
    );
    modify(entity, 'clear_tag', tags);
    index = (index + 1) % length(tags);
    modify(entity, 'tag', tags:index);
    tags:index
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

direction(yaw, pitch) -> [-sin(yaw)*cos(pitch), -sin(pitch), cos(pitch)*cos(yaw)];

_r_matrix(r, yaw) -> (
    if(!r, r = [0,0]);
    d = -sin(yaw)*(1-cos(r:1));
    [[
        [1+d*sin(yaw),-cos(yaw)*sin(r:1),d*cos(yaw)],
        [cos(yaw)*sin(r:1),cos(r:1),sin(yaw)*sin(r:1)],
        [d*cos(yaw),-sin(yaw)*sin(r:1),1+d*sin(yaw)]
    ],[
        [cos(r:0),0,sin(r:0)],
        [0,1,0],
        [-sin(r:0),0,cos(r:0)]
    ]]
);

_t_matrix(r) -> ( // Rotation to traslation matrix
    if(!r, r = [0,0,0]);
    [[
        [1,0,0],
        [0,cos(r:0),sin(r:0)],
        [0,-sin(r:0),cos(r:0)]
    ],[
        [cos(r:1),0,sin(r:1)],
        [0,1,0],
        [-sin(r:1),0,cos(r:1)]
    ],[
        [cos(r:2),-sin(r:2),0],
        [sin(r:2),cos(r:2),0],
        [0,0,1],
    ]];
);

_t(v, m) -> reduce(m, _a + v:_i * _, v * 0); // Traslate vector

_t_apply(v, m) -> map(v,reduce(m, _t(_a, _), _)); // Apply traslation matrix to vectors

_to_euler_angles(matrix, i_yaw) -> (
    print('x: ' + (x = matrix:0));
    print('y: ' + (y = matrix:1));
    print('z: ' + (z = matrix:2));
    print('dx: ' + sqrt(reduce(x, _a + _*_, 0)));
    print('dy: ' + sqrt(reduce(y, _a + _*_, 0)));
    print('dz: ' + sqrt(reduce(z, _a + _*_, 0)));

    ret = _rotation_matrix_to_euler_angles(matrix);
    print('pitch: ' + ret:0);
    print('yaw: ' +   ret:1);
    print('roll: ' +  ret:2);
    ret
);

// ARMORSTAND GETTER
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

_change_pose(player, entity, mode, part) -> (
    init_pose = parse_nbt(entity ~ 'nbt':'Pose':part);
    init_rotation = [player ~ 'yaw', player ~ 'pitch'];
    init_orientation = _t_apply(f = _t_apply(global_identity, _t_matrix(init_pose)), _r_matrix([entity ~ 'yaw', 0], 0));

    if(init_pose == 'null', init_pose = [0,0,0]);
    modify(entity, 'tag',
        'gb.armorstandeditor.moving',
        'gb.armorstandeditor.moving.player.' + lower(player ~ 'command_name'),
        str('gb.armorstandeditor.moving.x.%.03f', init_pose:0),
        str('gb.armorstandeditor.moving.y.%.03f', init_pose:1),
        str('gb.armorstandeditor.moving.z.%.03f', init_pose:2)
    );
    // Ticking
    entity_event(entity, 'on_tick', '_change_pose_tick', player, mode, part, init_orientation, init_rotation);
    // Feedback
    _sound(player, pos(entity), 'click');
);

_change_pose_tick(entity, player, mode, part, init_orientation, init_rotation) -> (
    // Undo (changing item)
    if(!_has_tool(player), _undo_pose(player, entity, part); return());
    // Confirm (unsneaking)
    if(!player ~ 'sneaking', _confirm_move(player, entity); return());

    // Move
    pos = parse_nbt(entity ~ 'nbt':'Pose':part) || [0,0,0];
    if(mode == 'pitch',
        args = [player ~ 'yaw', pos:1, pos:2]
    , mode == 'yaw', // elif
        args = [pos:0, player ~ 'yaw', pos:2]
    , mode == 'roll', // elif
        args = [pos:0, pos:1, player ~ 'yaw']
    , // else
        p = pos(entity)+_parts(entity):part;
        l = player ~ 'look';
        ln = direction(...init_rotation);
        draw_shape('line',2,'from',p,'to',p + ln);
        draw_shape('line',2,'from',p,'to',p + l,'color', 0xFFFFFF);

        dv = [player ~ 'yaw', player ~ 'pitch'] - init_rotation;

        v = _t_apply(init_orientation, _r_matrix(dv, 90 + init_rotation:0));
        //v = _t_apply(global_identity, _r_matrix(dv, 90 + init_rotation:0));

        c = [0xFF0000FF, 0xFF00FF, 0xFFFF];
        for(v, draw_shape('line',2,'from',p,'to',p+_,'color', c:_i));
        args = _to_euler_angles(v, entity ~ 'yaw')
    );
    if(args, modify(entity, 'nbt_merge', str('{Pose:{%s:[%.03ff,%.03ff,%.03ff]}}', [part, ... args])));
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

_cycle_moving_mode(player, entity) -> (
    mode = (_cycle_tag(entity, ... global_modes) - 'gb.armorstandeditor.mode.') || global_modes:0;
    // Feedback
    _sound(player, pos(entity), 'click');
    display_title(player, 'actionbar', 'Mode selected: ' + title(replace(mode, '_', ' & ')));
    mode
);

_put_on_head(player, entity) -> (
    armor_items = parse_nbt(entity ~ 'nbt':'ArmorItems');
    head = armor_items:3;
    item_tuple = player ~ 'holds' || ['air', 0, {}];
    armor_items:3 = {'id' -> item_tuple:0, 'Count' -> item_tuple:1, 'tag' -> item_tuple:2 || {}};
    inventory_set(player, player ~ 'selected_slot', head:'Count', head:'id', head:'tag');
    modify(entity, 'nbt_merge', encode_nbt({'ArmorItems' -> armor_items}, true));
    // Feedback
    _sound(player, pos(entity), 'click');
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
    , _has_tool(player), // elif
        if(player ~ 'sneaking',
            // Change pose
            part = _nearest_part(entity, hitvec);
            mode = _tag_match(entity, '(?:gb\\.armorstandeditor\\.mode\\.)(\\w+)');
            _change_pose(player, entity, mode, part)
        , // else
            // Change moving mode
            _cycle_moving_mode(player, entity)
        )
    , // else
        item_tuple = player ~ 'holds';
        if(player ~ 'sneaking' && item_tuple,
            [item, count, nbt] = item_tuple;
            remove_count = 1;
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
            , // else
                nospecial = true;
            );
            if(!nospecial,
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

_test(entity, hitvec) -> (
    part = _nearest_part(entity, hitvec);
    r = parse_nbt(entity ~ 'nbt':'Pose':part);

    p = pos(entity)+_parts(entity):part;

    v = _t_apply(global_identity, _t_matrix(r));

    c = [0xFF0000FF, 0xFF00FF, 0xFFFF];
    for(v, draw_shape('line',2,'from',p,'to',p+_,'color', c:_i))
);

_transpose(matrix) -> (
    rows = length(matrix);
    columns = length(matrix:0);
    matrix_T = [];
    loop(columns,
        row = [];
        loop(rows,
            row += matrix:i:j
        );
        matrix_T += row
    );
    matrix_T
);

_is_rotation_matrix(matrix) -> (
    matrix_T = _transpose(matrix);
    identity = _t(matrix, matrix_T);
    identity - global_identity
);

_rotation_matrix_to_euler_angles(matrix) -> (
    //if(!_is_rotation_matrix(matrix), print(player('all'), 'Matrice non valida'); return());

    sy = sqrt(matrix:0:0 ^ 2 +  matrix:1:0 ^ 2);

    if(sy >= 1e-6,
        [
            -atan2(matrix:2:1, matrix:2:2),
            atan2(-matrix:2:0, sy),
            atan2(matrix:1:0, matrix:0:0)
        ]
    , // Else
        [
            -atan2(-matrix:1:2, matrix:1:1),
            atan2(-matrix:2:0, sy),
            0
        ]
    )
)