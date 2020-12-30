__config() -> {'stay_loaded' -> true, 'scope' -> 'global'};

__on_player_right_clicks_block(player, item_tuple, hand, block, face, hitvec) -> (
    if(!item_tuple || player ~ 'gamemode' == 'spectator' || !(block ~ '_fence$'), return());
    [item, count, nbt] = item_tuple;
    if(item != 'lead', return());
    player_uuid = player ~ 'nbt':'UUID';
    if(entity_selector('@e[nbt={Leash:{UUID:' + player_uuid + '}}]'), return());
    if(!entity_pos('leash_knot', pos(block)), spawn('leash_knot', block));
    entity = spawn('rabbit', pos(block) + [0.5, 0.5, 0.5], str('{Age:-2147483648,Leash:{UUID:%s},Tags:["gb.effect.baby","gb.cord_leash","gb.effect.invisible"],NoAI:1b,Silent:1b,Invulnerable:1b,PersistenceRequired:1b,DeathLootTable:"minecraft:empty"}',player_uuid));
    modify(entity, 'effect', 'invisibility', 2147483647, 1, false, false);
    if(player ~ 'gamemode' != 'creative', 
        inventory_set(player, if(hand == 'mainhand', player~'selected_slot', -1), count - 1, item, nbt)
    )
);

entity_pos(entity, pos) -> 
    filter(entity_list('*'), _ ~ 'type' == entity && pos(_) == pos);

_remove_leash(entity) ->
if(!entity ~ 'nbt':'Leash' ||
   !entity_pos('leash_knot', pos(entity)) ||
   map(['X','Y','Z'], entity ~ 'nbt':'Leash':_) == map(pos(entity), floor(_)),
    if(entity ~ 'nbt':'Leash', 
        spawn('item', pos(entity) ,str('{Item:{id:"minecraft:lead",Count:1b},PickupDelay:10,Motion:[%f,.2,%f]}', rand(0.2) - 0.1, rand(0.2) - 0.1))
    );
    modify(entity, 'remove')
);

__on_tick() -> (
    if(tick_time() % 20, return());
    for(entity_selector('@e[tag=gb.cord_leash]'), 
        _remove_leash(_);
        // modify(_, 'effect', 'invisibility', 2147483647, 1, false, false);
        // modify(_, 'age', '-2147483648);
    )
)
