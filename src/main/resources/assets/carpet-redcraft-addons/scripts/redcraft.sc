__config() -> {'stay_loaded' -> true, 'scope' -> 'global'};

// 21w13a Fix: End Generation with wrong noise height
//__on_server_starts() -> (
//    create_datapack('redcraft3gen', {
//        'data' -> {
//            'minecraft' -> {
//                'dimension' -> {
//                    'overworld.json' -> {
//                        'type' -> {
//                            'name' -> 'minecraft:overworld',
//                            'logical_height' -> 512,
//                            'infiniburn' -> 'minecraft:infiniburn_overworld',
//                            'effects' -> 'minecraft:overworld',
//                            'ambient_light' -> 0.0,
//                            'respawn_anchor_works' -> false,
//                            'has_raids' -> true,
//                            'natural' -> true,
//                            'coordinate_scale' -> 1.0,
//                            'piglin_safe' -> false,
//                            'bed_works' -> true,
//                            'has_skylight' -> true,
//                            'has_ceiling' -> false,
//                            'ultrawarm' -> false,
//                            'min_y' -> -128,
//                            'height' -> 512
//                        },
//                        'generator' -> {
//                            'type' -> 'minecraft:noise',
//                            'seed' -> 3280542799242379911,
//                            'biome_source' -> {
//                                'type' -> 'minecraft:vanilla_layered',
//                                'seed' -> 3280542799242379911,
//                                'large_biomes' -> false
//                            },
//                            'settings' -> 'minecraft:overworld'
//                        }
//                    }
//                },
//                'worldgen' -> {
//                    'noise_settings' -> {
//                        'end.json' -> {
//                            'aquifers_enabled' -> false,
//                            'noise_caves_enabled' -> false,
//                            'deepslate_enabled' -> false,
//                            'bedrock_floor_position' -> -2147483648,
//                            'sea_level' -> 0,
//                            'disable_mob_generation' -> true,
//                            'default_block' -> {
//                                'Name' -> 'minecraft:end_stone'
//                            },
//                            'default_fluid' -> {
//                                'Name' -> 'minecraft:air'
//                            },
//                            'bedrock_roof_position' -> -2147483648,
//                            'structures' -> {
//                                'structures' -> {
//                                    'minecraft:village' -> {
//                                        'spacing' -> 32,
//                                        'separation' -> 8,
//                                        'salt' -> 10387312
//                                    },
//                                    'minecraft:stronghold' -> {
//                                        'spacing' -> 1,
//                                        'separation' -> 0,
//                                        'salt' -> 0
//                                    },
//                                    'minecraft:endcity' -> {
//                                        'spacing' -> 20,
//                                        'separation' -> 11,
//                                        'salt' -> 10387313
//                                    },
//                                    'minecraft:buried_treasure' -> {
//                                        'spacing' -> 1,
//                                        'separation' -> 0,
//                                        'salt' -> 0
//                                    },
//                                    'minecraft:nether_fossil' -> {
//                                        'spacing' -> 2,
//                                        'separation' -> 1,
//                                        'salt' -> 14357921
//                                    },
//                                    'minecraft:swamp_hut' -> {
//                                        'spacing' -> 32,
//                                        'separation' -> 8,
//                                        'salt' -> 14357620
//                                    },
//                                    'minecraft:mineshaft' -> {
//                                        'spacing' -> 1,
//                                        'separation' -> 0,
//                                        'salt' -> 0
//                                    },
//                                    'minecraft:jungle_pyramid' -> {
//                                        'spacing' -> 32,
//                                        'separation' -> 8,
//                                        'salt' -> 14357619
//                                    },
//                                    'minecraft:mansion' -> {
//                                        'spacing' -> 80,
//                                        'separation' -> 20,
//                                        'salt' -> 10387319
//                                    },
//                                    'minecraft:desert_pyramid' -> {
//                                        'spacing' -> 32,
//                                        'separation' -> 8,
//                                        'salt' -> 14357617
//                                    },
//                                    'minecraft:bastion_remnant' -> {
//                                        'spacing' -> 27,
//                                        'separation' -> 4,
//                                        'salt' -> 30084232
//                                    },
//                                    'minecraft:ruined_portal' -> {
//                                        'spacing' -> 40,
//                                        'separation' -> 15,
//                                        'salt' -> 34222645
//                                    },
//                                    'minecraft:fortress' -> {
//                                        'spacing' -> 27,
//                                        'separation' -> 4,
//                                        'salt' -> 30084232
//                                    },
//                                    'minecraft:igloo' -> {
//                                        'spacing' -> 32,
//                                        'separation' -> 8,
//                                        'salt' -> 14357618
//                                    },
//                                    'minecraft:ocean_ruin' -> {
//                                        'spacing' -> 20,
//                                        'separation' -> 8,
//                                        'salt' -> 14357621
//                                    },
//                                    'minecraft:monument' -> {
//                                        'spacing' -> 32,
//                                        'separation' -> 5,
//                                        'salt' -> 10387313
//                                    },
//                                    'minecraft:pillager_outpost' -> {
//                                        'spacing' -> 32,
//                                        'separation' -> 8,
//                                        'salt' -> 165745296
//                                    },
//                                    'minecraft:shipwreck' -> {
//                                        'spacing' -> 24,
//                                        'separation' -> 4,
//                                        'salt' -> 165745295
//                                    }
//                                }
//                            },
//                            'noise' -> {
//                                'simplex_surface_noise' -> true,
//                                'island_noise_override' -> true,
//                                'size_vertical' -> 1,
//                                'density_factor' -> 0.0,
//                                'density_offset' -> 0.0,
//                                'top_slide' -> {
//                                    'target' -> -3000,
//                                    'size' -> 64,
//                                    'offset' -> -46
//                                },
//                                'bottom_slide' -> {
//                                    'target' -> -30,
//                                    'size' -> 7,
//                                    'offset' -> 1
//                                },
//                                'size_horizontal' -> 2,
//                                'min_y' -> 0,
//                                'height' -> 128,
//                                'sampling' -> {
//                                    'xz_scale' -> 2.0,
//                                    'y_scale' -> 1.0,
//                                    'xz_factor' -> 80.0,
//                                    'y_factor' -> 160.0
//                                }
//                            }
//                        }
//                    }
//                }
//            },
//            'redcraft2' -> {
//                'dimension' -> {
//                    'overworld.json' -> {
//                        'type' -> {
//                            'name' -> 'minecraft:overworld',
//                            'logical_height' -> 256,
//                            'infiniburn' -> 'minecraft:infiniburn_overworld',
//                            'effects' -> 'minecraft:overworld',
//                            'ambient_light' -> 0.0,
//                            'respawn_anchor_works' -> false,
//                            'has_raids' -> true,
//                            'natural' -> true,
//                            'coordinate_scale' -> 1.0,
//                            'piglin_safe' -> false,
//                            'bed_works' -> true,
//                            'has_skylight' -> true,
//                            'has_ceiling' -> false,
//                            'ultrawarm' -> false,
//                            'min_y' -> 0,
//                            'height' -> 256
//                        },
//                        'generator' -> {
//                            'biome_source' -> {
//                                'seed' -> 3280542799242379911,
//                                'large_biomes' -> false,
//                                'type' -> 'minecraft:vanilla_layered'
//                            },
//                            'seed' -> 3280542799242379911,
//                            'settings' -> 'minecraft:overworld',
//                            'type' -> 'minecraft:noise'
//                        }
//                    },
//                    'the_end.json' -> {
//                        'type' -> 'minecraft:the_end',
//                        'generator' -> {
//                            'type' -> 'minecraft:noise',
//                            'seed' -> 3280542799242379911,
//                            'biome_source' -> {
//                                'type' -> 'minecraft:the_end',
//                                'seed' -> 3280542799242379911
//                            },
//                            'settings' -> 'minecraft:end'
//                        }
//                    }
//                }
//            }
//        }
//    });
//    enable_hidden_dimensions()
//);

__on_player_changes_dimension(player, from_pos, from_dimension, to_pos, to_dimension) -> if(
    from_dimension == 'redcraft2:overworld' && to_dimension == 'the_end',
    run(str('execute in redcraft2:the_end run tp %s 100.5 49.00 0.5 90 0',player~'command_name'))
)
