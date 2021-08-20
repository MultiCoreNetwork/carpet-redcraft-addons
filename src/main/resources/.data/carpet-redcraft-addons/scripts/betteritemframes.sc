__config() -> {'stay_loaded' -> true, 'scope' -> 'global'};

__on_player_interacts_with_entity(player, entity, hand) -> (
    if(player ~ 'gamemode' == 'spectator' || entity~'type'~'item_frame$'==null || !(entity~'nbt':'Item'), return());
    item_tuple = query(player, 'holds', hand);
    if(item_tuple == null, return());
    [item, count, nbt] = item_tuple;
    rotation = (7 + entity~'nbt':'ItemRotation') % 8;
    if(
        item == 'phantom_membrane' && !entity~'nbt':'Invisible',
            modify(entity, 'nbt_merge', '{Invisible:true' + if(!entity~'nbt':'Fixed', ',ItemRotation:'+rotation, '') + '}');
            _drop_phantom_membrane_on_remove(entity),
        item == 'glass_pane' && !entity~'nbt':'Fixed',
            modify(entity, 'nbt_merge', '{Fixed:true}'),
        item ~ '_axe$' && entity~'nbt':'Fixed',
            modify(entity, 'nbt_merge', str('{ItemRotation:%s,Fixed:false}', rotation));
            spawn('item', pos(entity), str('{Item:{id:"minecraft:glass_pane",Count:1b},PickupDelay:10,Motion:[%f,.2,%f]}', rand(0.2) - 0.1, rand(0.2) - 0.1));
            return(),
    // else
        return()
    );
    if(player ~ 'gamemode' != 'creative',
        inventory_set(player, if(hand == 'mainhand', player~'selected_slot', -1), count - 1, item, nbt)
    )
);

// TODO: Custom LootTable

_drop_phantom_membrane_on_remove(entity) -> 
entity_event(entity, 'on_removed', 
    _(e) -> spawn('item', pos(e), str('{Item:{id:"minecraft:phantom_membrane",Count:1b},PickupDelay:10,Motion:[%f,.2,%f]}', rand(0.2) - 0.1, rand(0.2) - 0.1))
);

_check_custom_drop(entity, new) ->
if(entity~'nbt':'Invisible',
    _drop_phantom_membrane_on_remove(entity),
);

entity_load_handler('item_frame', '_check_custom_drop');
for(entity_list('item_frame'), _check_custom_drop(_, null));

entity_load_handler('glow_item_frame', '_check_custom_drop');
for(entity_list('glow_item_frame'), _check_custom_drop(_, null))
