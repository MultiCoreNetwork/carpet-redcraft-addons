__config() -> {'stay_loaded' -> true, 'scope' -> 'global'};

global_tall_plants = ['sunflower','lilac','rose_bush','peony','tall_grass','large_fern'];
global_extra_plants = ['big_dripleaf'];

__on_player_right_clicks_block(player, item_tuple, hand, block, face, hitvec) -> (
    if(!item_tuple || (global_tall_plants~block==null && global_extra_plants~block==null) || player ~ 'gamemode' == 'spectator', return());
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
    // elif
      block == 'big_dripleaf',
        without_updates(set(block,'air'));
        spawn('item', pos(block)+0.5, str('{Item:{id:"minecraft:small_dripleaf",Count:1b},PickupDelay:10,Motion:[%f,.2,%f]}', rand(0.2) - 0.1, rand(0.2) - 0.1)),
    // else
        return()
    );
    modify(player, 'swing', hand);
    sound('minecraft:entity.sheep.shear', pos(block), 1.0, 1, 'block');
    if(player ~ 'gamemode' != 'creative',
        nbt:'Damage' = d = nbt:'Damage'+ 1;
        if(d>237, count+=-1; sound('entity.item.break', pos(player), 1, 1, 'player'));
        inventory_set(player, if(hand == 'mainhand', player~'selected_slot', -1), count, item, nbt)
    )
)
