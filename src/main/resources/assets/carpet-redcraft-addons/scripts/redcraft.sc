__config() -> {'stay_loaded' -> true, 'scope' -> 'global'};

__on_server_starts() -> (
    create_datapack('redcraft3gen', {
        'data' -> {
            'redcraft2' -> {
                'dimension' -> {
                    'overworld.json' -> {
                        'type' -> {
                            'name' -> 'minecraft:overworld',
                            'logical_height' -> 256,
                            'infiniburn' -> 'minecraft:infiniburn_overworld',
                            'effects' -> 'minecraft:overworld',
                            'ambient_light' -> 0.0,
                            'respawn_anchor_works' -> false,
                            'has_raids' -> true,
                            'natural' -> true,
                            'coordinate_scale' -> 1.0,
                            'piglin_safe' -> false,
                            'bed_works' -> true,
                            'has_skylight' -> true,
                            'has_ceiling' -> false,
                            'ultrawarm' -> false,
                            'min_y' -> 0,
                            'height' -> 256
                        },
                        'generator' -> {
                            'biome_source' -> {
                                'seed' -> 3280542799242379911,
                                'large_biomes' -> false,
                                'type' -> 'minecraft:vanilla_layered'
                            },
                            'seed' -> 3280542799242379911,
                            'settings' -> 'minecraft:overworld',
                            'type' -> 'minecraft:noise'
                        }
                    },
                    'the_end.json' -> {
                        'type' -> 'minecraft:the_end',
                        'generator' -> {
                            'type' -> 'minecraft:noise',
                            'seed' -> 3280542799242379911,
                            'biome_source' -> {
                                'type' -> 'minecraft:the_end',
                                'seed' -> 3280542799242379911
                            },
                            'settings' -> 'minecraft:end'
                        }
                    }
                }
            }
        }
    });
    enable_hidden_dimensions()
);

__on_player_changes_dimension(player, from_pos, from_dimension, to_pos, to_dimension) -> if(
    from_dimension == 'redcraft2:overworld' && to_dimension == 'the_end',
    run(str('execute in redcraft2:the_end run tp %s 100.5 49.00 0.5 90 0',player~'command_name'))
)
