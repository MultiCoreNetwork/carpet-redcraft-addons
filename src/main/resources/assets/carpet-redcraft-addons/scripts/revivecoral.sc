__config() -> {'stay_loaded' -> true, 'scope' -> 'global'};

__on_player_right_clicks_block(player, item_tuple, hand, block, face, hitvec) -> (
    if(player~'gamemode' == 'spectator' || !item_tuple || block ~ '^dead_\\w+_coral' == null, return());
    [item, count, nbt] = item_tuple;
    if(item != 'potion' || nbt:'Potion' != 'minecraft:water', return());
    posBlock = pos(block);
    block2 = block - 'dead_';
    set(posBlock, block2, block_state(posBlock));
    if (player ~ 'gamemode' == 'creative', return());
    inventory_set(player, if(hand == 'mainhand', player ~ 'selected_slot', -1), count - 1, item, nbt);
    run('give ' + player ~ 'command_name' + ' glass_bottle')
)