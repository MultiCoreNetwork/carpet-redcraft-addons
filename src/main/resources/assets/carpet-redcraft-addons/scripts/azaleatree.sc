__config() -> {'stay_loaded' -> true, 'scope' -> 'global'};

__on_player_right_clicks_block(player, item_tuple, hand, block, face, hitvec) -> (
    if(!item_tuple || player~'gamemode' == 'spectator', return());
    [item, count, nbt] = item_tuple;
    if (item == 'bone_meal' && block == 'flowering_azalea',
        positionBlock = pos(block);
        particle('happy_villager', positionBlock + 0.5, 15);
        if(!rand(5),
            without_updates(set(positionBlock, 'air'));
            if(!plop(positionBlock,'azalea_tree'),
                without_updates(set(positionBlock, block))
            )
        );
        if (player~'gamemode' != 'creative',
            inventory_set(player, if(hand=='mainhand',player~'selected_slot',-1), count - 1, item)
        )
    )
)
