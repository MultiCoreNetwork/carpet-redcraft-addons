__config() -> {'stay_loaded' -> true, 'scope' -> 'global'};

__on_player_interacts_with_entity(player, entity, hand) -> (
    if(player ~ 'gamemode' == 'spectator' || entity~'type' != 'item_frame' || !(entity~'nbt':'Item') || entity~'nbt':'Invisible', return());
    item_tuple = query(player, 'holds', hand);
    if(item_tuple == null, return());
    [item, count, nbt] = item_tuple;
    if(item != 'phantom_membrane', return());
    rotation = (7 + entity~'nbt':'ItemRotation') % 8;
    modify(entity, 'nbt_merge', str('{ItemRotation:%s,Invisible:true}', rotation));
    if(player ~ 'gamemode' != 'creative',
        inventory_set(player, if(hand == 'mainhand', player~'selected_slot', -1), count - 1, item, nbt)
    )
)
