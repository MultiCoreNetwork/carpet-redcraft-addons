__config() -> {'stay_loaded' -> true, 'scope' -> 'global'};

global_colorants = ['white_dye', 'orange_dye', 'magenta_dye', 'light_blue_dye', 'yellow_dye', 'lime_dye', 'pink_dye', 'gray_dye', 'light_gray_dye', 'cyan_dye', 'purple_dye', 'blue_dye', 'brown_dye', 'green_dye', 'red_dye', 'black_dye', 'popped_chorus_fruit'];
__on_player_interacts_with_entity(player, entity, hand) -> (
    if(player ~ 'gamemode' == 'spectator' || entity~'type' != 'shulker', return());
    item_tuple = query(player, 'holds', hand);
    if(item_tuple == null || (color=global_colorants~(item_tuple:0)) == null || entity~'nbt':'Color' == color, return());
    modify(entity, 'nbt_merge', str('{Color:%d}',color));
    if(player ~ 'gamemode' != 'creative',
        [item, count, nbt] = item_tuple;
        inventory_set(player, if(hand == 'mainhand', player~'selected_slot', -1), count - 1, item, nbt)
    )
)
