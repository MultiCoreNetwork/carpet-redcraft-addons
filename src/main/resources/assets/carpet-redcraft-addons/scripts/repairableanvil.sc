__config() -> {'stay_loaded' -> true, 'scope' -> 'global'};

__on_player_right_clicks_block(player, item_tuple, hand, block, face, hitvec) -> (
    if(player ~ 'gamemode' == 'spectator' || !item_tuple || block ~ '_anvil$' == null || !player ~ 'sneaking', return());
    [item, count, nbt] = item_tuple;
    if(item != 'iron_ingot', return());
    posBlock = pos(block);
    block2 = if(block == 'chipped_anvil', 'anvil', 'chipped_anvil');
    if(player ~ 'gamemode' == 'creative' || rand(100/88),
        set(posBlock, block2, block_state(posBlock));
        sound('minecraft:block.anvil.use', posBlock, 1, 1.5, 'block'),
        sound('minecraft:block.anvil.land', posBlock, 1, 2, 'block');
    );
    modify(player, 'swing', hand);
    if (player ~ 'gamemode' == 'creative', return());
    inventory_set(player, if(hand == 'mainhand', player ~ 'selected_slot', -1), count - 1, item, nbt);
)