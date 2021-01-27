__config() -> {'stay_loaded' -> true, 'scope' -> 'global'};

_spawn_glowing_squid(squid) -> (
    if(biome(block(pos(squid)))~'deep' != null && !rand(4),
        spawn('glow_squid',pos(squid));
        modify(squid,'remove')
    )
);

entity_load_handler('squid', '_spawn_glowing_squid')
