__config() -> {'stay_loaded' -> true, 'scope' -> 'global'};

global_placeable_items = {'allium', 'azure_bluet', 'bamboo', 'big_dripleaf', 'blue_orchid', 'cornflower', 'crimson_fungus', 'crimson_roots', 'dandelion', 'dead_bush', 'fern', 'grass', 'lily_of_the_valley', 'nether_sprouts', 'orange_tulip', 'oxeye_daisy', 'pink_tulip', 'poppy', 'red_tulip', 'sweet_berries', 'warped_fungus', 'warped_roots', 'white_tulip', 'wither_rose'};
global_placeable_tall_items = {'large_fern', 'lilac', 'peony', 'rose_bush', 'sunflower', 'tall_grass'};

// script run l=[];for(block_list(),set([x,y,z],'air');set([x,y-1,z],_);if(place_item('rail',[x,y,z]), l+=_));print(map(l,'\''+_+'\''))
// += 'dirt_path', 'oak_leaves', 'spruce_leaves', 'birch_leaves', 'jungle_leaves', 'acacia_leaves', 'dark_oak_leaves', 'azalea_leaves', 'flowering_azalea_leaves'
// -= 'grass_block', 'dirt', 'coarse_dirt', 'podzol', 'rooted_dirt', 'moss_block'
global_flat_surface = {'stone', 'granite', 'polished_granite', 'diorite', 'polished_diorite', 'andesite', 'polished_andesite', 'cobblestone', 'oak_planks', 'spruce_planks', 'birch_planks', 'jungle_planks', 'acacia_planks', 'dark_oak_planks', 'bedrock', 'sand', 'red_sand', 'gravel', 'gold_ore', 'deepslate_gold_ore', 'iron_ore', 'deepslate_iron_ore', 'coal_ore', 'deepslate_coal_ore', 'nether_gold_ore', 'oak_log', 'spruce_log', 'birch_log', 'jungle_log', 'acacia_log', 'dark_oak_log', 'stripped_spruce_log', 'stripped_birch_log', 'stripped_jungle_log', 'stripped_acacia_log', 'stripped_dark_oak_log', 'stripped_oak_log', 'oak_wood', 'spruce_wood', 'birch_wood', 'jungle_wood', 'acacia_wood', 'dark_oak_wood', 'stripped_oak_wood', 'stripped_spruce_wood', 'stripped_birch_wood', 'stripped_jungle_wood', 'stripped_acacia_wood', 'stripped_dark_oak_wood', 'sponge', 'wet_sponge', 'glass', 'lapis_ore', 'deepslate_lapis_ore', 'lapis_block', 'dispenser', 'sandstone', 'chiseled_sandstone', 'cut_sandstone', 'note_block', 'sticky_piston', 'piston', 'white_wool', 'orange_wool', 'magenta_wool', 'light_blue_wool', 'yellow_wool', 'lime_wool', 'pink_wool', 'gray_wool', 'light_gray_wool', 'cyan_wool', 'purple_wool', 'blue_wool', 'brown_wool', 'green_wool', 'red_wool', 'black_wool', 'gold_block', 'iron_block', 'bricks', 'tnt', 'bookshelf', 'mossy_cobblestone', 'obsidian', 'spawner', 'diamond_ore', 'deepslate_diamond_ore', 'diamond_block', 'crafting_table', 'furnace', 'redstone_ore', 'deepslate_redstone_ore', 'ice', 'snow_block', 'clay', 'jukebox', 'pumpkin', 'netherrack', 'soul_sand', 'soul_soil', 'basalt', 'polished_basalt', 'glowstone', 'carved_pumpkin', 'jack_o_lantern', 'white_stained_glass', 'orange_stained_glass', 'magenta_stained_glass', 'light_blue_stained_glass', 'yellow_stained_glass', 'lime_stained_glass', 'pink_stained_glass', 'gray_stained_glass', 'light_gray_stained_glass', 'cyan_stained_glass', 'purple_stained_glass', 'blue_stained_glass', 'brown_stained_glass', 'green_stained_glass', 'red_stained_glass', 'black_stained_glass', 'stone_bricks', 'mossy_stone_bricks', 'cracked_stone_bricks', 'chiseled_stone_bricks', 'infested_stone', 'infested_cobblestone', 'infested_stone_bricks', 'infested_mossy_stone_bricks', 'infested_cracked_stone_bricks', 'infested_chiseled_stone_bricks', 'brown_mushroom_block', 'red_mushroom_block', 'mushroom_stem', 'melon', 'mycelium', 'nether_bricks', 'cauldron', 'water_cauldron', 'lava_cauldron', 'powder_snow_cauldron', 'end_stone', 'redstone_lamp', 'emerald_ore', 'deepslate_emerald_ore', 'emerald_block', 'command_block', 'beacon', 'redstone_block', 'nether_quartz_ore', 'hopper', 'quartz_block', 'chiseled_quartz_block', 'quartz_pillar', 'dropper', 'white_terracotta', 'orange_terracotta', 'magenta_terracotta', 'light_blue_terracotta', 'yellow_terracotta', 'lime_terracotta', 'pink_terracotta', 'gray_terracotta', 'light_gray_terracotta', 'cyan_terracotta', 'purple_terracotta', 'blue_terracotta', 'brown_terracotta', 'green_terracotta', 'red_terracotta', 'black_terracotta', 'slime_block', 'barrier', 'prismarine', 'prismarine_bricks', 'dark_prismarine', 'sea_lantern', 'hay_block', 'terracotta', 'coal_block', 'packed_ice', 'red_sandstone', 'chiseled_red_sandstone', 'cut_red_sandstone', 'smooth_stone', 'smooth_sandstone', 'smooth_quartz', 'smooth_red_sandstone', 'chorus_flower', 'purpur_block', 'purpur_pillar', 'end_stone_bricks', 'repeating_command_block', 'chain_command_block', 'frosted_ice', 'magma_block', 'nether_wart_block', 'red_nether_bricks', 'bone_block', 'observer', 'shulker_box', 'white_shulker_box', 'orange_shulker_box', 'magenta_shulker_box', 'light_blue_shulker_box', 'yellow_shulker_box', 'lime_shulker_box', 'pink_shulker_box', 'gray_shulker_box', 'light_gray_shulker_box', 'cyan_shulker_box', 'purple_shulker_box', 'blue_shulker_box', 'brown_shulker_box', 'green_shulker_box', 'red_shulker_box', 'black_shulker_box', 'white_glazed_terracotta', 'orange_glazed_terracotta', 'magenta_glazed_terracotta', 'light_blue_glazed_terracotta', 'yellow_glazed_terracotta', 'lime_glazed_terracotta', 'pink_glazed_terracotta', 'gray_glazed_terracotta', 'light_gray_glazed_terracotta', 'cyan_glazed_terracotta', 'purple_glazed_terracotta', 'blue_glazed_terracotta', 'brown_glazed_terracotta', 'green_glazed_terracotta', 'red_glazed_terracotta', 'black_glazed_terracotta', 'white_concrete', 'orange_concrete', 'magenta_concrete', 'light_blue_concrete', 'yellow_concrete', 'lime_concrete', 'pink_concrete', 'gray_concrete', 'light_gray_concrete', 'cyan_concrete', 'purple_concrete', 'blue_concrete', 'brown_concrete', 'green_concrete', 'red_concrete', 'black_concrete', 'white_concrete_powder', 'orange_concrete_powder', 'magenta_concrete_powder', 'light_blue_concrete_powder', 'yellow_concrete_powder', 'lime_concrete_powder', 'pink_concrete_powder', 'gray_concrete_powder', 'light_gray_concrete_powder', 'cyan_concrete_powder', 'purple_concrete_powder', 'blue_concrete_powder', 'brown_concrete_powder', 'green_concrete_powder', 'red_concrete_powder', 'black_concrete_powder', 'dried_kelp_block', 'dead_tube_coral_block', 'dead_brain_coral_block', 'dead_bubble_coral_block', 'dead_fire_coral_block', 'dead_horn_coral_block', 'tube_coral_block', 'brain_coral_block', 'bubble_coral_block', 'fire_coral_block', 'horn_coral_block', 'blue_ice', 'scaffolding', 'loom', 'barrel', 'smoker', 'blast_furnace', 'cartography_table', 'fletching_table', 'smithing_table', 'warped_stem', 'stripped_warped_stem', 'warped_hyphae', 'stripped_warped_hyphae', 'warped_nylium', 'warped_wart_block', 'crimson_stem', 'stripped_crimson_stem', 'crimson_hyphae', 'stripped_crimson_hyphae', 'crimson_nylium', 'shroomlight', 'crimson_planks', 'warped_planks', 'structure_block', 'jigsaw', 'composter', 'target', 'bee_nest', 'beehive', 'honeycomb_block', 'netherite_block', 'ancient_debris', 'crying_obsidian', 'respawn_anchor', 'lodestone', 'blackstone', 'polished_blackstone', 'polished_blackstone_bricks', 'cracked_polished_blackstone_bricks', 'chiseled_polished_blackstone', 'gilded_blackstone', 'chiseled_nether_bricks', 'cracked_nether_bricks', 'quartz_bricks', 'amethyst_block', 'budding_amethyst', 'tuff', 'calcite', 'tinted_glass', 'oxidized_copper', 'weathered_copper', 'exposed_copper', 'copper_block', 'copper_ore', 'deepslate_copper_ore', 'oxidized_cut_copper', 'weathered_cut_copper', 'exposed_cut_copper', 'cut_copper', 'waxed_copper_block', 'waxed_weathered_copper', 'waxed_exposed_copper', 'waxed_oxidized_copper', 'waxed_oxidized_cut_copper', 'waxed_weathered_cut_copper', 'waxed_exposed_cut_copper', 'waxed_cut_copper', 'dripstone_block', 'azalea', 'flowering_azalea', 'deepslate', 'cobbled_deepslate', 'polished_deepslate', 'deepslate_tiles', 'deepslate_bricks', 'chiseled_deepslate', 'cracked_deepslate_bricks', 'cracked_deepslate_tiles', 'infested_deepslate', 'smooth_basalt', 'raw_iron_block', 'raw_copper_block', 'raw_gold_block', 'dirt_path', 'oak_leaves', 'spruce_leaves', 'birch_leaves', 'jungle_leaves', 'acacia_leaves', 'dark_oak_leaves', 'azalea_leaves', 'flowering_azalea_leaves'};
flat_surface(block) ->
    has(global_flat_surface, str(block)) || 
    block ~ '_trapdoor$' && block_state(block, 'half') == 'top' && block_state(block, 'open') == 'false' ||
    block ~ '_stairs$' && block_state(block, 'half') == 'top' ||
    block ~ '_slab$' && (block_state(block, 'type') == 'top' || block_state(block, 'type') == 'double');

_item_to_block(item) ->
if(
    item == 'sweet_berries', 'sweet_berry_bush',
    item
);

_block_sound(block) -> if(block == 'sweet_berry_bush', 'sweet_berry_bush',
                          block == 'big_dripleaf', 'big_dripleaf',
                          block_sound(block));


_placeable(player, item_tuple, hand, block, face) -> (
    g = player ~ 'gamemode';
    if(g == 'spectator' || !item_tuple, return());
    [item, count, nbt] = item_tuple;
    if(item == 'wither_rose' && block ~ 'nether_brick', return());
    if(face != 'up' || !has(global_placeable_items, item) && !has(global_placeable_tall_items, item) || !flat_surface(block), return());
    if(g=='adventure' && !nbt:'CanPlaceOn' ~ str('"minecraft:%s"',block), return());
    b1 = pos_offset(block, face);
    if(!air(b1), return());
    without_updates(
        if(
            has(global_placeable_tall_items, item),
                b2 = pos_offset(b1, face);
                if(!air(b2), return());
                set(b1, item, 'half', 'lower');
                set(b2, item, 'half', 'upper'),
            set(b1, _item_to_block(item))
        )
    );
    modify(player, 'swing', hand);
    sound(str('block.%s.place', _block_sound(block(b1))), b1, 1, 1, 'block');
    if(g == 'creative', return()); 
    inventory_set(player, if(hand=='mainhand',player~'selected_slot',-1), count - 1, item ,nbt)
);

__on_player_right_clicks_block(player, item_tuple, hand, block, face, hitvec) -> (
    _placeable(player, item_tuple, hand, block, face)
)
