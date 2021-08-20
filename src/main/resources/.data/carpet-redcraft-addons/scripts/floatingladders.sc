__config() -> {'scope'->'global','stay_loaded'->true};

_rope_ladders(player, item_tuple, hand, block, face) -> (
    g = player ~ 'gamemode';
    if(g == 'spectator' || !item_tuple, return());
    [item, count, nbt] = item_tuple;
    if(item!='ladder' || block!='ladder' || block_state(block, 'facing') != face,return());
    if(g=='adventure' && !nbt:'CanPlaceOn' ~ '"minecraft:ladder"', return());
    b = neighbours(block):1;
    if(b == 'ladder' && block_state(b,'facing') == block_state(block, 'facing'),
        _rope_ladders(player, item_tuple, hand, b, face);
        return()
    );
    if(!air(b) && b != 'water', return());
    set(b, block, 'waterlogged', if(b == 'water', 'true', 'false'));
    sound('block.ladder.place', pos(b), 1.0, 1, 'block');
    modify(player, 'swing', hand);
    if(g == 'creative', return());
    inventory_set(player, if(hand=='mainhand',player~'selected_slot',-1), count - 1, item ,nbt)
);

__on_player_right_clicks_block(player, item_tuple, hand, block, face, hitvec) -> (
    _rope_ladders(player, item_tuple, hand, block, face)
)
