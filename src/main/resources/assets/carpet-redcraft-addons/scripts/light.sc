__config() -> {'scope'->'global','stay_loaded'->true};

__on_player_clicks_block(player, block, face) -> (
    g = player ~ 'gamemode';
    item_tuple = player ~ 'holds';
    if(g == 'spectator' || g == 'creative' || block != 'light' || !item_tuple, return());
    [item, count, nbt] = item_tuple;
    if(item != 'light' || (g == 'adventure' && !nbt:'CanDestroy' ~ '"minecraft:light"'), return());
    level = block_state(block, 'level');
    set(pos(block), 'air');
    if(g == 'creative', return());
    spawn('item', pos(block) + [0.5, 0, 0.5], str('{Item:{id:"minecraft:light",tag:{BlockStateTag:{level:"%d"}},Count:1b},PickupDelay:10,Motion:[%f,.2,%f]}', level, rand(0.2) - 0.1, rand(0.2) - 0.1))
);

_show_light(player, pos) -> particle('light', pos + [0.5, 0.5, 0.5], 1, 0, 0, player);
_show_light_area(player) ->
in_dimension(player,
    scan(pos(player), [5, 5, 5],
        if(_ == 'light',
            _show_light(player, pos(_))
        )
    )
);

__on_player_switches_slot(player, from, to)->
if((item_tuple = inventory_get(player, to)) && item_tuple:0 == 'light',
    _show_light_area(player)
);

__on_player_places_block(player, item_tuple, hand, block) ->
if(block == 'light' && item_tuple:1 > 1,
    _show_light(player, pos(block))
);

_looper() -> (
    for(filter(player('*'), (h = _ ~ 'holds') && h:0 == 'light' && _ ~ 'gamemode' != 'creative'),
        _show_light_area(_);
    );
    schedule(40, '_looper')
);
_looper();
