__config() -> {'stay_loaded' -> true, 'scope' -> 'global'};

_spawn_glowing_squid(squid) -> (
    if(biome(block(squid))~'deep' != null && !rand(4),
        nbt = squid~'nbt';
        modify(squid,'remove');
        glow_squid = spawn('glow_squid',squid,nbt)
    )
);

entity_load_handler('squid', '_spawn_glowing_squid')
