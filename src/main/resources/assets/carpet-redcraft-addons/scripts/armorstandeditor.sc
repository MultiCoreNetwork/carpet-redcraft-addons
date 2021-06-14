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
    'gb.armorstandeditor.mode.roll'
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
        if(sd == null || d < sd,
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

_confirm_pose(player, entity) -> (
    // Feedback
    _sound(player, pos(entity), 'success');
    // Stop moving
    _stop_moving(entity)
);

_stop_moving(entity) -> (
    // Remove Tags
    _clear_matching_tag(entity, 'gb\\.armorstandeditor\\.moving');
    // Stop ticking
    entity_event(entity, 'on_tick', null)
);

_change_pose(player, entity, mode, part) -> (
    init_pose = parse_nbt(entity ~ 'nbt':'Pose':part);
    if(init_pose == 'null', init_pose = [0,0,0]);
    modify(entity, 'tag',
        'gb.armorstandeditor.moving',
        'gb.armorstandeditor.moving.player.' + lower(player ~ 'command_name'),
        str('gb.armorstandeditor.moving.x.%.03f', init_pose:0),
        str('gb.armorstandeditor.moving.y.%.03f', init_pose:1),
        str('gb.armorstandeditor.moving.z.%.03f', init_pose:2)
    );
    // Ticking
    entity_event(entity, 'on_tick', '_change_pose_tick', player, mode, part);
    // Feedback
    _sound(player, pos(entity), 'click');
);

_change_pose_tick(entity, player, mode, part) -> (
    // Undo (changing item)
    if(!_has_tool(player), _undo_pose(player, entity, part); return());
    // Confirm (unsneaking)
    if(!player ~ 'sneaking', _confirm_pose(player, entity); return());

    // Move
    pos = parse_nbt(entity ~ 'nbt':'Pose':part) || [0,0,0];
    if(mode == 'pitch',
        args = [player ~ 'yaw', pos:1, pos:2]
    , mode == 'yaw', // elif
        args = [pos:0, player ~ 'yaw', pos:2]
    , mode == 'roll', // elif
        args = [pos:0, pos:1, player ~ 'yaw']
    );
    if(args, modify(entity, 'nbt_merge', str('{Pose:{%s:[%.03ff,%.03ff,%.03ff]}}', [part, ... args])));
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

    schedule(0, _(outer(entity)) -> spawn('armor_stand', pos(entity), entity ~ 'nbt'));
);

__on_player_interacts_with_entity_at(player, entity, hand, hitvec) ->  // Carpet RedCraft Addons
if(hand == 'mainhand' && _edit_mode(entity),
    if(player_moving = _moving_mode(entity),
        // Confirm (clicking again)
        _confirm_pose(player_moving, entity)
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
        if(player ~ 'sneaking',
            // TODO: move armostand
            TODO;
        , // else
            // Set item on head
            _put_on_head(player, entity)
        )
    )
);
