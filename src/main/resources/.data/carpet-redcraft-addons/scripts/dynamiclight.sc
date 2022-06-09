
global_lights = {
    'beacon' -> 15,
    'campfire' -> 15,
    'conduit' -> 15,
    'glowstone' -> 15,
    'lava_bucket' -> 15,
    'jack_o_lantern' -> 15,
    'lantern' -> 15,
    'sea_lantern' -> 15,
    'shroomlight' -> 15,
    'end_rod' -> 14,
    'blaze_rod' -> 14,
    'blaze_powder' -> 14,
    'glow_berries' -> 14,
    'torch' -> 14,
    'soul_campfire' -> 10,
    'soul_lantern' -> 10,
    'soul_torch' -> 10,
    'ender_chest' -> 7,
    'glow_lichen' -> 7,
    'redstone_torch' -> 7,
    'amethyst_cluster' -> 5,
    'large_amethyst_bud' -> 4,
    'magma_block' -> 3,
    'medium_amethyst_bud' -> 2,
    'small_amethyst_bud' -> 1,
    'brewing_stand' -> 1,
    'brown_mushroom' -> 1,
    'dragon_egg' -> 1,
    'end_portal_frame' -> 1,
    'sculk_sensor' -> 1,
    'enchanting_table' -> 7
};

__on_tick() -> (
    _remove_lights();
    for(player('all'),
        p = _;
        if (p~'gamemode' == 'spectator', return());
        hold_mainhand = p ~ 'holds' || [null,null,null];
        hold_offhand = p ~ ['holds', 'offhand'] || [null,null,null];
        hold_head = p ~ ['holds', 'head'] || [null,null,null];
        light = max(global_lights:(hold_mainhand:0), global_lights:(hold_offhand:0), global_lights:(hold_head:0));
        if(light,
            _set_lights(pos(p)+[0,p~'eye_height',0], light)
        )
    )
);

_remove_lights() -> for(entity_list('marker'),
    if(_ ~ ['has_scoreboard_tag', 'gb.light_block'],
        block = block(pos(_));
        if( block == 'light',
            without_updates(set(pos(_), if(liquid(block), 'water', 'air')));
            modify(_, 'remove')
        )
    )
);

_set_lights(pos, level) -> (
    _set_light(pos, level);
    for(neighbours(pos), 
        _set_light(pos, level-1)
    )
);

_set_light(pos, level) -> (
    if(air(pos),
        without_updates(set(pos, 'light', 'level', level)),
    block(pos) == 'water[level=0]', // elif
        without_updates(set(pos, 'light', 'level', level, 'waterlogged', 'true')),
    // else
        return()
    );
    modify(spawn('marker', pos), 'tag', 'gb.light_block')
)
