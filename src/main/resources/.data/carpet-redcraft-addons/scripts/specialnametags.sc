__config() -> {'stay_loaded' -> true, 'scope' -> 'global'};

__on_player_interacts_with_entity(player, entity, hand) -> (
    if(player ~ 'gamemode' == 'spectator', return());
    item_tuple = query(player, 'holds', hand);
    if(item_tuple == null, return());
    [item, count, nbt] = item_tuple;
    if(item != 'name_tag', return());
    if(entity ~ 'type' == 'armor_stand',
        modify(entity, 'nbt_merge', '{CustomNameVisible:true}')
    );
    schedule(0, _(outer(entity)) -> (
        if(entity ~ 'custom_name' == null, return());
        custom_name = lower(entity ~ 'custom_name');
        if(
            ['freeze','noai','congela','congelato']~custom_name!=null,           modify(entity,'ai',false); modify(entity,'invulnerable',true),
            ['unfreeze','ai','scongela','scongelato']~custom_name!=null,         modify(entity,'ai',true); modify(entity,'invulnerable',false),
            ['silent','silenzia','muta','mutato']~custom_name!=null,             modify(entity,'silent',true),
            ['unsilent','smuta','smutato']~custom_name!=null,                    modify(entity,'silent',false),
            ['baby','cucciolo','bambino']~custom_name!=null,                     modify(entity,'breeding_age',-32768); modify(entity,'nbt_merge','{IsBaby:true}');  modify(entity,'tag','gb.is_baby'),
            ['unbaby','adult','adulto']~custom_name!=null,                       modify(entity,'breeding_age',0);      modify(entity,'nbt_merge','{IsBaby:false}'); modify(entity,'clear_tag','gb.is_baby'),
            ['gravity','gravità']~custom_name!=null,                             modify(entity,'gravity',true),
            ['ungravity','floating','fluttuante','nogravity']~custom_name!=null, modify(entity,'gravity',false),
            ['invulnerable','invulnerabile']~custom_name!=null,                  modify(entity,'invulnerable',true),
            ['vulnerable','vulnerabile']~custom_name!=null,                      modify(entity,'invulnerable',false),
            ['noname','unname','nonome']~custom_name!=null,                      modify(entity,'custom_name',null),
            ['killer rabbit']~custom_name!=null,                                 modify(entity,'nbt_merge','{RabbitType:99}'),
            ['nozombification','nozombificatione']~custom_name!=null,            modify(entity,'nbt_merge','{IsImmuneToZombification:true}'),
            ['zombification','zombificatione']~custom_name!=null,                modify(entity,'nbt_merge','{IsImmuneToZombification:false}'),
            ['johnny']~custom_name!=null,                                        modify(entity,'nbt_merge','{Johnny:true}'),
            ['unjohnny']~custom_name!=null,                                      modify(entity,'nbt_merge','{Johnny:false}'),
            ['noclip']~custom_name!=null,                                        modify(entity,'no_clip',true),
            ['clip']~custom_name!=null,                                          modify(entity,'no_clip',false),
            ['sit','seduto']~custom_name!=null,                                  sit(entity),
            ['unsleep','unsit','alzato']~custom_name!=null,                      modify(entity,'clear_tag','gb.is_sleeping'); modify(entity,'dismount'); modify(entity,'gravity', true); ,
            ['sleep','sdraiato']~custom_name!=null,                              modify(entity,'gravity',false); modify(entity,'tag','gb.is_sleeping')
        )
    ))
);

sit(entity) ->
if(!entity ~ 'is_riding',
    modify(entity, 'tag', 'gb.sitted');
    armor_stand = spawn('armor_stand', pos(entity) - [0,0.1,0], str('{Tags:["gb.sitted.armor_stand"],Marker:1,NoGravity:1,Invisible:1,Rotation:[%.02ff,0f]}', entity ~ 'yaw'));
    modify(entity, 'mount', armor_stand);
    modify(armor_stand, 'yaw', entity ~ 'yaw');
    entity_event(entity, 'on_removed',
        _(e,outer(armor_stand)) -> modify(armor_stand,'remove')
    )
);

slow_looper() -> (
    for(entity_selector('@e[tag=gb.is_baby]'),
        modify(_,'breeding_age',-32768)
    );
    for(entity_selector('@e[type=armor_stand,tag=gb.sitted.armor_stand]'),
        if(!_ ~ 'is_ridden',
            modify(_, 'remove')
        )
    );
    schedule(12000,'slow_looper')
);
slow_looper();


__on_tick() -> (
    for(entity_selector('@e[tag=gb.is_sleeping]'),
        modify(_,'nbt_merge', str('{SleepingX:%d,SleepingY:%d,SleepingZ:%d}', _ ~ 'pos'))
    );
)
