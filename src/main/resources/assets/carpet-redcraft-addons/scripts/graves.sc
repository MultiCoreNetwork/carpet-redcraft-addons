__config() -> {
    'stay_loaded' -> true,
    'scope' -> 'global',
    'commands' -> {
        '' -> '_command',
        'list' -> _() -> _graves_list(player()~'name'),
        'list <player>' -> '_graves_list',
    },
    'arguments' -> {
        'player' -> {'type'->'players','single'->true}
    }
};

_command() -> if((player=player())~'permission_level'>=1, _remove_grave(player, pos(player), null, true));
_graves_list(player_name) -> (
    for(keys(uuid_storage = parse_nbt(nbt_storage('redcraft:players'))),
        if(lower(uuid_storage:_)==lower(player_name), player_uuid = _; break())
    );
    if(!player_uuid,
        p = player(player_name);
        player_uuid = p~'uuid';
    );
    if(player_uuid,
        graves = parse_nbt(nbt_storage('redcraft:graves')):player_uuid;
        if(graves,
            print('-'*20);
            print(player(), format(str('fb %s\'s graves:', player_name)));
            for(graves,
                print(player(), format(str('g %s @ %.00f %.00f %.00f',_:'Dimension',_:'Pos':0,_:'Pos':1,_:'Pos':2)))
            );
            print('-'*20),
        // else
            print(player(),format(str('ri No grave found for %s',player_name)))
        ),
    // else
        print(player(),format(str('ri No player found with name %s',player_name)))
    )
);

scoreboard_add('gb.grave.time');

__on_player_dies(player) -> (
	if(inventory_has_items(player),
        schedule(0, _(outer(player)) -> if(
            (items = filter(entity_area('item', (pos=pos(player)) + [0,player~'eye_height',0], [3, 3, 3]), _~'age'==0)) != [],
            _make_grave(player, pos, items)
        ))
    )
);

_make_grave(player, pos, items) -> (
    time = _save_grave_position(player);
    armor_stands = [
        spawn('armor_stand', pos+[0,-1.7,0.85],  '{ArmorItems:[{},{},{},{id:"minecraft:spruce_sign",Count:1b}],Invisible:true,DisabledSlots:2039583}'),
        spawn('armor_stand', pos+[0,-1.7,0],     '{ArmorItems:[{},{},{},{id:"minecraft:coarse_dirt",Count:1b}],Invisible:true,DisabledSlots:2039583}'),
        spawn('armor_stand', pos+[0,-1.7,0.625], '{ArmorItems:[{},{},{},{id:"minecraft:coarse_dirt",Count:1b}],Invisible:true,DisabledSlots:2039583}'),
        spawn('armor_stand', pos+[0,-1.1,0.75],  '{ArmorItems:[{},{},{},{id:"minecraft:cobblestone_wall",Count:1b}],Rotation:[90f,0f],Invisible:true,DisabledSlots:2039583}')
    ];
    for(armor_stands,
        modify(_, 'invulnerable', true);
        modify(_, 'gravity', false);
        modify(_, 'tag', ['gb.grave_armor_stand', 'gb.grave']);
        scoreboard('gb.grave.time', _~'uuid', time)
    );
    for(items,
        modify(_, 'invulnerable', true);
        modify(_, 'pos', pos);
        modify(_, 'pickup_delay', 32767);
        modify(_, 'gravity', false);
        modify(_, 'motion', [0,0,0]);
        modify(_, 'nbt_merge', '{Age:-32768}');
        modify(_, 'mount', armor_stands:1);
        modify(_, 'tag', ['gb.grave_item', 'gb.grave']);
        scoreboard('gb.grave.time', _~'uuid', time)
    )
);

_save_grave_position(player) -> (
    nbt = parse_nbt(nbt_storage('redcraft:graves'));
    if(!has(nbt,player~'uuid'), nbt:(player~'uuid') = []);
    nbt:(player~'uuid') += {
        'Pos' -> player~'pos',
        'Dimension' -> player~'dimension',
        'Tick' -> world_time()
    };
    nbt_storage('redcraft:graves', encode_nbt(nbt));
    world_time()
);
_remove_grave_position(player,tick) -> (
    nbt = parse_nbt(nbt_storage('redcraft:graves'));
    if(!has(nbt,player~'uuid'), return(false));
    for(nbt:(player~'uuid'),if(_:'Tick' == tick, i=_i; break()));
    if(i==null, return(false));
    delete(nbt:(player~'uuid'):i);
    nbt_storage('redcraft:graves', encode_nbt(nbt));
    true
);

_get_graves_positions(player) -> (
    nbt = parse_nbt(nbt_storage('redcraft:graves'));
    if(!has(nbt,player~'uuid'), nbt:(player~'uuid') = []);
    return(nbt:(player~'uuid'))
);

_distance(p1, p2) -> reduce(p1-p2, _a + _*_, 0);

__on_player_starts_sneaking(player) -> (
    if(player ~ 'gamemode' == 'spectator', return());
	for(_get_graves_positions(player),
		grave = _;
		if(player ~ 'dimension' == grave:'Dimension' && _distance(pos(player), grave:'Pos')<1,
			_remove_grave(player, pos(player), grave:'Tick', false);
            _remove_grave_position(player,grave:'Tick')
		)
	)
);

_remove_grave(player, pos, tick, ignore_tick) -> (
    for(filter(entity_area('armor_stand', pos, [1.5,1.5,1.5]), scoreboard('gb.grave.time', _~'uuid') == tick || ignore_tick),
        modify(_, 'remove')
    );
    for(filter(entity_area('item', pos, [1,1,1]), scoreboard('gb.grave.time', _~'uuid') == tick || ignore_tick),
        modify(_, 'pickup_delay', 0);
        modify(_, 'gravity', true);
        if(!ignore_tick, modify(_, 'nbt_merge', str('{Owner:%s}',player~'nbt':'UUID')))
    );
    sound('block.wart_block.fall', pos, 1.0, 0, 'block');
)
