__config() -> {
    'stay_loaded' -> true,
    'scope' -> 'global'};


_prevent_placing(player, item, count, nbt, hand, face, block) -> (
    inventory_set(player, if(hand == 'mainhand', player ~ 'selected_slot', -1), 0);

    global_prevent = true;
    global_prevent_block_pos = pos_offset(pos(block),face);
    global_prevent_block = block(global_prevent_block_pos);
    global_prevent_block_data = block_data(global_prevent_block);
    global_prevent_block_state = block_state(global_prevent_block);
    without_updates(set(global_prevent_block_pos, 'barrier'));

    schedule(0, _(outer(player), outer(hand), outer(count), outer(item)) -> (
        inventory_set(player, if(hand == 'mainhand', player ~ 'selected_slot', -1), count-if(player ~ 'gamemode' == 'creative',0,1), item);
        if(global_prevent,
            global_prevent = false;
            without_updates(set(global_prevent_block_pos, global_prevent_block, global_prevent_block_state, global_prevent_block_data));
        )
    ));
);

_empty_cauldron(player, item, count, nbt, hand, block, posPlayer, face) -> (
    _prevent_placing(player, item, count, nbt, hand, face, block);
    set(pos(block), 'cauldron');
    //inventory_set(player, if(hand == 'mainhand', player ~ 'selected_slot', -1), if(player ~ 'gamemode' == 'creative', count, count - 1), item, nbt);
    spawn('item', posPlayer, '{Item:{id:"minecraft:wet_sponge",Count:1b}}');
);

_fill_cauldron(player, item, count, nbt, hand, block, posPlayer, face) -> (
    _prevent_placing(player, item, count, nbt, hand, face, block);
    level = block_state(block):'level';
    randomNumber = floor(rand(3)) +1;
    levelFinal = number(level) + randomNumber;
    if(levelFinal >= 3, (
        set(pos(block), 'water_cauldron', 'level', 3);
        //inventory_set(player, if(hand == 'mainhand', player ~ 'selected_slot', -1), if(player ~ 'gamemode' == 'creative', count, count - 1), item, nbt);
        spawn('item', posPlayer, '{Item:{id:"minecraft:sponge",Count:1b}}');
    ), (
        set(pos(block), 'water_cauldron', 'level', levelFinal);
        //inventory_set(player, if(hand == 'mainhand', player ~ 'selected_slot', -1), if(player ~ 'gamemode' == 'creative', count, count - 1), item, nbt);
        spawn('item', posPlayer, '{Item:{id:"minecraft:sponge",Count:1b}}');
    ))
);

__on_player_right_clicks_block(player, item_tuple, hand, block, face, hitvec) -> (
    if((block != 'cauldron' && block != 'water_cauldron') || player ~ 'gamemode' == 'spectator' || item_tuple:0 ~ 'sponge$' == null, return());
    [item, count, nbt] = item_tuple;
    if(block == 'water_cauldron' && item == 'sponge',  _empty_cauldron(player, item, count, nbt, hand, block, pos(player), face),
       (block == 'water_cauldron' || block == 'cauldron') && item == 'wet_sponge', _fill_cauldron(player, item, count, nbt, hand, block, pos(player), face)
    );
);

__on_player_places_block(player, item_tuple, hand, block) ->
if(global_prevent,
    global_prevent = false;
    without_updates(set(global_prevent_block_pos, global_prevent_block, global_prevent_block_state, global_prevent_block_data));
)
