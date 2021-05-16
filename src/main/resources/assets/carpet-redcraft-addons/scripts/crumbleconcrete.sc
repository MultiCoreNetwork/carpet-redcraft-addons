__config() -> {
    'stay_loaded' -> true,
    'scope' -> 'global'};

__on_player_right_clicks_block(player, item_tuple, hand, block, face, hitvec) -> (
    if(player ~ 'gamemode' == 'spectator' || !item_tuple || !block ~ '_concrete$', return());
    [item, count, nbt] = item_tuple;
    if(!item ~ 'glass_bottle' , return());
    posBlock = pos(block);
    block2 = block + '_powder';
    set(posBlock, block2);
    if(player ~ 'gamemode' == 'creative', return());
    inventory_set(player, if(hand == 'mainhand', player ~ 'selected_slot', -1), count - 1, item, nbt);
    run('give ' + player ~ 'command_name' + ' minecraft:potion{"Potion":"minecraft:water"}')
)
