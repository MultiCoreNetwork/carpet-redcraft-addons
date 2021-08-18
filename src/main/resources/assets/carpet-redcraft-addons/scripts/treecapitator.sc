__config() -> {
    'stay_loaded' -> true,
    'scope' -> 'global',
    'command_permission' -> 'ops',
    'commands' -> {
        '' -> _() -> _enchant(player())
    }
};

// Enchants player's tool with treecapitator
_enchant(player) -> (
    item_tuple = player ~ 'holds' || ['diamond_axe',1,null];
    [item, count, nbt] = item_tuple;
    if(!item ~ '_axe$' || nbt:'Enchantments[{id:"redcraft:treecapitator"}]',
        print(format('r Unable to apply the treecapitator enchantment'));
        return()
    );
    if(!nbt, nbt = nbt('{}'));
    nbt:'display.Lore' = nbt:'display.Lore' || nbt('[]');
    nbt:'Enchantments' = nbt:'Enchantments' || nbt('[]');
    nbt_map = parse_nbt(nbt);
    nbt_map:'display':'Lore' += '{"text":"TreeCapitator","color":"red","italic":false}';
    nbt_map:'Enchantments' += {'id' -> 'redcraft:treecapitator'};
    nbt = encode_nbt(nbt_map);
    nbt:'Enchantments[{id:"redcraft:treecapitator"}].lvl' = nbt('1s');
    inventory_set(player, player ~ 'selected_slot', count, item, nbt)
);

_valid_item(player) -> (
	[item, count, nbt] = if(h=player~'holds', h, return(false));
    if(item ~ '_axe$' == null, return(false));
    if(max(nbt:'Enchantments[{id:"redcraft:treecapitator"}]'<1),
        if(str(nbt:'display.Lore[]') ~ '\\{"text":"TreeCapitator","color":"red","italic":false\\}' == null,
            return(false),
            ench = parse_nbt(if(t = nbt:'Enchantments', t, '[]'));
            ench += {'id' -> 'redcraft:treecapitator', 'lvl'->1};
            nbt:'Enchantments' = encode_nbt(ench);
            inventory_set(player, player ~ 'selected_slot', count, item, nbt);
            return(true)
        ),
        return(true)
    )
);

_type(block) -> join('_',slice(t=split('_',block),0,length(t)-1));

global_blocks = ['_log$', '_stem$'];
_valid_block(block) -> reduce(global_blocks, _a || bool(block ~ _), false);

_log_area(log, ... large_oak) -> if(
    log ~ '^birch_' || log ~ '^spruce_' || log ~ '_stem$', [block(pos_offset(log,'up'))],
    has(large_oak:0) && large_oak:0, t = [rect(log, [1,0,1][1,1,1])]; delete(t:4); t,
    [rect(pos_offset(log,'up'),[1,0,1])]
);

_leaves_area(leaves) -> if(
    leaves ~ '_block$' || leaves ~ 'azalea_leaves$', t = [rect(leaves, [1,1,1])]; delete(t:13); t,
    neighbours(leaves)
);

global_large_trees = {'dark_oak','spruce','jungle'};
_log_base_area(log) -> if(
    !has(global_large_trees,_type(log)), [log],
    reduce([rect(log, [0,0,0], [1,1,1])], _a && str(_) == str(log), true), [rect(log, [0,0,0], [1,1,1])],
    reduce([rect(log, [0,0,1], [1,1,0])], _a && str(_) == str(log), true), [rect(log, [0,0,1], [1,1,0])],
    reduce([rect(log, [1,0,0], [0,1,1])], _a && str(_) == str(log), true), [rect(log, [1,0,0], [0,1,1])],
    reduce([rect(log, [1,0,1], [0,1,0])], _a && str(_) == str(log), true), [rect(log, [1,0,1], [0,1,0])],
    [log]
);

_valid_leaves(log, leaves) -> if(
    log == 'warped_stem', leaves == 'warped_wart_block',
    log == 'crimson_stem', leaves == 'nether_wart_block',
    log == 'oak_log', leaves == 'oak_leaves' || leaves ~ 'azalea_leaves$',
    log == 'mushroom_stem', bool(leaves ~ '_mushroom_block$'),
    block_tags(leaves, 'leaves') && leaves ~ _type(log) && !bool(block_state(leaves,'persistent'))
);

_valid_tree(log) -> (
    if(reduce(t=neighbours(log); delete(t:1); t, _a || _valid_leaves(log, _), false), return(true));
    reduce(_log_area(log),
        _a || if(str(log) == str(_), _valid_tree(_)), false
    )
);

_detect_leaves(log, leaves, ... map) -> (
    if(map == [] || (map = map:0) == null, map = {});
    map += pos(leaves);
    distance = number(block_state(leaves, 'distance')) + 1;
    for(_leaves_area(leaves),
        if(str(leaves) == str(_) && ((_ ~ '_block$' && _distance(pos(log), pos(_)) <= 6) || block_state(_, 'distance') == distance) && !has(map:pos(_)),
            map = _detect_leaves(log, _, map)
        );
        if(str(leaves) ~ '_wart_block$' && _ == 'shroomlight' && !has(map:pos(_)),
            map += pos(_)
        );
        if(str(leaves) ~ 'azalea_leaves$' && _ ~ 'azalea_leaves$' && !has(map:pos(_)),
            map += pos(_)
        )
    );
    map
);

_distance(vec1, vec2) -> sqrt(reduce(vec1 - vec2, _a + _*_, 0));

_not_far(log) -> (
    [x,y,z] = pos(log) - pos(global_first_log);
    x^2 + z^2 <= 30.25
);

_detect_base_log(log) -> (
    map = {};
    for(_log_base_area(log),
        map = _detect_log(_, map)
    );
    map
);

_detect_log(log, ... map) -> (
    if(map == [] || (map = map:0) == null, map = {});
    map += pos(log);
    for(_log_area(log, global_first_log == 'oak_log' && _not_far(log)),
        if(str(log) == str(_) && !has(map:pos(_)),
            map = _detect_log(_, map),
           _valid_leaves(log, _)
        )
    );
    map
);

_detect_attached_leaves(logs) -> (
    map = {};
    for(logs, for(neighbours(log = block(_)),
        if(_valid_leaves(log,_), map = map + _detect_leaves(log, _))
    ));
    map
);

__on_player_breaks_block(player, block) -> (
    if(!_valid_item(player) || !_valid_block(block) || !_valid_tree(block), return());
    for(_detect_attached_leaves(logs = _detect_base_log(global_first_log = block)),
        [item, count, nbt] = player~'holds';
        destroy(_, item, nbt)
    );
    for(logs,
        harvest(player,_)
    )
)
