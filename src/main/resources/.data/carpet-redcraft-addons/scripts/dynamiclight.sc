
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
    'emdrod' -> 14,
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
    'sculk_sensor' -> 1
};

__on_tick() -> (
    _remove_lights();
    for(player('all'),
        p = _;
        hold_mainhand = p ~ 'holds' || [null,null,null];
        hold_offhand = p ~ ['holds', 'offhand'] || [null,null,null];
        light = max(global_lights:(hold_mainhand:0), global_lights:(hold_offhand:0));
        if(light,
            _set_lights(pos(p)+[0,p~'eye_height',0], light)
        )
    )
);

_remove_lights() -> for(entity_list('marker'),
    if(_ ~ ['has_scoreboard_tag', 'gb.light_block'],
        block = block(pos(_));
        set(pos(_), if(liquid(block), 'water', 'air'));
        modify(_, 'remove')
    )
);

_set_lights(pos, level) -> (
    _set_light(pos, level);
    for(neighbours(pos), 
        _set_light(pos, level-1)
    )
);

_set_light(pos, level) -> (
    without_updates(
        if(air(pos), 
            set(pos, 'light', 'level', level),
        block(pos) == 'water', // elif
            set(pos, 'light', 'level', level, 'waterlogged', 'true'),
        // else
            return()
        );
        modify(spawn('marker', pos), 'tag', 'gb.light_block')
    )
)