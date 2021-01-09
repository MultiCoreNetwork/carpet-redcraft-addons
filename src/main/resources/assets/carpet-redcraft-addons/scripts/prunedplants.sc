__config() -> {'stay_loaded' -> true, 'scope' -> 'global'};

global_tall_plants = ['sunflower','lilac','rose_bush','peony','tall_grass','large_fern'];

__on_player_right_clicks_block(player, item_tuple, hand, block, face, hitvec) -> (
    if(!item_tuple || global_tall_plants~(block)==null || player ~ 'gamemode' == 'spectator', return());
    [item, count, nbt] = item_tuple;
    if(item!='shears', return());
    if(
      block_state(block,'half')=='upper' && block_state(d=pos_offset(block,'down'),'half')=='lower',
        without_updates(set(block,'air')),
    // elif
      block_state(block,'half')=='lower' && block_state(u=pos_offset(block,'up'),'half')=='upper',
        without_updates(
            set(block,block(u),block_state(u));
            set(u,'air')
        ),
    // else
        return()
    );
    sound('minecraft:entity.sheep.shear', pos(block), 1.0, 1, 'block');
    if(player ~ 'gamemode' != 'creative',
        nbt:'Damage' = d = nbt:'Damage'+ 1;
        if(d>237, count+=-1; sound('entity.item.break', pos(player), 1, 1, 'player'));
        inventory_set(player, if(hand == 'mainhand', player~'selected_slot', -1), count, item, nbt)
    )
)
