__on_player_right_clicks_block(player, item_tuple, hand, block, face, hitvec) -> (
    if(!item_tuple || block!='spore_blossom' || player ~ 'gamemode' == 'spectator', return());
    [item, count, nbt] = item_tuple;
    if(item!='bone_meal', return());
    _duplicate_spore(...pos(block));
     modify(player, 'swing', hand);
        if (player ~ 'gamemode' == 'creative', return());
        inventory_set(player, if(hand == 'mainhand', player ~ 'selected_slot', -1), count - 1, item, nbt);
);

_duplicate_spore(x, y, z) -> (
    pos = [x, y, z];
    particle('happy_villager', pos+0.5, 15, 0.25, 0.25, 0.25, 0);
    loop(10,
        random = [rand(9)-4, rand(5)-2, rand(9)-4];
        block = block(pos + random);
        if(_condition(block),
            set(block, 'spore_blossom');
            break();
        )
    )
);

_condition(block) -> (
    up = block(pos_offset(block, 'up'));
    air(block) && solid(up) && block_tags(up, 'lush_ground_replaceable')
);
