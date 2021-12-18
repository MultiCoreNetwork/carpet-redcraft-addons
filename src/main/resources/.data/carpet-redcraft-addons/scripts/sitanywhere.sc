__config() -> {'stay_loaded' -> true, 'scope' -> 'global'};

__on_player_right_clicks_block(player, item_tuple, hand, block, face, hitvec) -> (
    if(!item_tuple || player ~ 'gamemode' == 'spectator', return());
    [item, count, nbt] = item_tuple;
    if(item != 'saddle', return());
    armor_stand = spawn('armor_stand', pos(block) + hitvec - [0,1.03,0], '{Tags:["gb.sitawyway"],Marker:1,NoGravity:1,Invisible:1}');
    entity = spawn('pig', t=pos(block); t:1=0; t, '{Team:"gb.nocollision",NoGravity:1,DeathLootTable:"minecraft:empty",Tags:["gb.sitawyway.pig","gb.effect.invisible"],Saddle:1,Invulnerable:1,PersistenceRequired:1,NoAI:1,Silent:1,Attributes:[{Name:generic.max_health,Base:0}]}');
    modify(entity, 'effect', 'invisibility', 2147483647, 1, false, false);
    modify(entity, 'yaw', player~'yaw');
    modify(entity, 'mount', armor_stand);
    entity_event(entity, 'on_removed',
        _(e,outer(armor_stand)) -> modify(armor_stand,'remove')
    );
    modify(player, 'swing', hand);
    sound('entity.pig.saddle',pos(armor_stand),1.0,1.5,'block');
    if(player ~ 'gamemode' != 'creative',
        inventory_set(player, if(hand == 'mainhand', player~'selected_slot', -1), count - 1, item, nbt)
    )
);
__on_player_attacks_entity(player, entity) -> if(
    query(entity,'has_scoreboard_tag','gb.sitawyway.pig'),
    modify(entity, 'remove');
    if( player ~ 'gamemode' != 'creative',
        spawn('item', pos(entity)+[0,1.04,0], str('{Item:{id:"minecraft:saddle",Count:1b},PickupDelay:10,Motion:[%f,.2,%f]}', rand(0.2) - 0.1, rand(0.2) - 0.1))
    )
);

team_add('gb.nocollision');
team_property('gb.nocollision', 'collisionRule', 'never');

slow_looper() -> (
    for(entity_selector('@e[type=pig,tag=gb.sitawyway.pig]'),
        modify(_, 'effect', 'invisibility', 2147483647, 1, false, false)
    );
    for(entity_selector('@e[type=armor_stand,tag=gb.sitawyway]'),
        if(!_ ~ 'is_ridden',
            modify(_, 'remove')
        )
    );
    schedule(12000, 'slow_looper')
);
slow_looper()
