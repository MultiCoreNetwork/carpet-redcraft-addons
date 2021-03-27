__config() -> {'stay_loaded' -> true, 'scope' -> 'global'};

__on_player_right_clicks_block(player, item_tuple, hand, block, face, hitvec) -> (
    if( !item_tuple, return());
    [item, count, nbt] = item_tuple;
    if (item == 'bone_meal' && block~'mossy_' != null,
        positionBlock = pos(block);
        schedule(0, _(outer(positionBlock)) -> set(positionBlock, 'moss_block'));
        if (player()~'gamemode' != 'creative',
            inventory_set(player, if(hand=='mainhand',player~'selected_slot',-1), count - 1, item)
        )
    )
)
