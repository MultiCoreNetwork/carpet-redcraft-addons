__config() -> {'stay_loaded' -> true, 'scope' -> 'global'};

__on_player_right_clicks_block(player, item_tuple, hand, block, face, hitvec) -> (
item = (item_tuple):0;
if (item == 'bone_meal' && block~'mossy_' != null,
positionBlock = pos(block);
inventory_remove(player()~'command_name', 'bone_meal');
schedule(0, _(outer(positionBlock)) -> set(positionBlock, 'moss_block')),
return
)
)