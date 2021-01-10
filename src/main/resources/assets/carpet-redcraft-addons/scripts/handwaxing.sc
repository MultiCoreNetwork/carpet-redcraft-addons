__config() -> {'stay_loaded' -> true, 'scope' -> 'global'};

__on_player_right_clicks_block(player, item_tuple, hand, block, face, hitvec) -> (
    if(!item_tuple || (!block~'^(?!waxed_|weathered_)\\w*copper\\w*?$' && block!='copper_ore') || player ~ 'gamemode' == 'spectator', return());
    [item, count, nbt] = item_tuple;
    if(item!='honeycomb', return());
    set(block,'waxed_'+block-'_block',block_state(block));
    sound('minecraft:block.beehive.drip', pos(block), 1.0, 0.5, 'block');
    if(player ~ 'gamemode' != 'creative',
        inventory_set(player, if(hand == 'mainhand', player~'selected_slot', -1), count - 1, item, nbt)
    )
)