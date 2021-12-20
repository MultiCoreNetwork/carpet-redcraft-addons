__config() -> {'scope'->'global','stay_loaded'->true};

__on_player_clicks_block(player, block, face) -> (
    g = player ~ 'gamemode';
    if(g == 'spectator' || g == 'creative' || block != 'barrier', return());
    if((g == 'adventure' && !nbt:'CanDestroy' ~ '"minecraft:barrier"'), return());
    item_tuple = player ~ 'holds';
    if (item_tuple:0 ~ '_pickaxe$',
        (
        schedule(0, '_break', player, pos(block), str(block), 1, 0);
        ),
        schedule(0, '_break', player, pos(block), str(block), 5, 0);
    )
);

_break(player, pos, block_name, step, lvl) -> (
   current = player~'active_block';
   if (current != block_name || pos(current) != pos,
      modify(player, 'breaking_progress', null);
   ,
      modify(player, 'breaking_progress', lvl);
      if (lvl >= 10, destroy(pos, -1));
      schedule(step, '_break', player, pos, block_name, step, lvl+1)
   );
);

_show_barrier(player, pos) -> particle('barrier', pos + [0.5, 0.5, 0.5], 1, 0, 0, player);
_show_barrier_area(player) ->
in_dimension(player,
    scan(pos(player), [5, 5, 5],
        if(_ == 'barrier',
            _show_barrier(player, pos(_))
        )
    )
);

__on_player_switches_slot(player, from, to)->
if((item_tuple = inventory_get(player, to)) && item_tuple:0 == 'barrier' || (item_tuple = inventory_get(player, -1)) && item_tuple:0 == 'barrier',
    _show_barrier_area(player)
);

__on_explosion_outcome(pos, power, source, causer, mode, fire, blocks, entities) -> (
print(source);
if (length(blocks) == 0 || (system_info('world_gamerules'):'mobGriefing' == 'false' && source != 'Primed TNT'), return());
in_dimension(query(source, 'dimension'),
    scan(pos, [power, power, power],
        if(_ == 'barrier',
            destroy(pos(_), -1)
        )
    )
)
);

__on_player_places_block(player, item_tuple, hand, block) -> (
if((item_tuple = player ~ 'holds') && item_tuple:0 == 'barrier' || (item_tuple = inventory_get(player, -1)) && item_tuple:0 == 'barrier',
    _show_barrier_area(player)
)
);

_looper() -> (
    for(filter(player('*'), (h = _ ~ 'holds') && h:0 == 'barrier' && _ ~ 'gamemode' != 'creative' || (h = inventory_get(_, -1)) && h:0 == 'barrier' && _ ~ 'gamemode' != 'creative'),
        _show_barrier_area(_);
    );
    schedule(40, '_looper')
);
_looper();
