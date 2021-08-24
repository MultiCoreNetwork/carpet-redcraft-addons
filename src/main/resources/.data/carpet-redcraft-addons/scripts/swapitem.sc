global_slots = ['mainhand', 'feet', 'legs', 'chest', 'head', 'offhand'];
__config() -> {
    'stay_loaded' -> true,
	'commands' -> {
        '<fromslot>' -> _(fromSlot) -> swap(player(), fromSlot, player(), 'mainhand'),
        '<fromslot> <toslot>' -> _(fromSlot, toSlot) -> swap(player(), fromSlot, player(), toSlot),
        '<fromslot> entity' -> _(fromSlot) -> swap(player(), fromSlot, player() ~ ['trace',5,'entities'], 'mainhand'),
        '<fromslot> entity <toslot>' -> _(fromSlot, toSlot) -> swap(player(), fromSlot, player() ~ ['trace',5,'entities'], toSlot),
        'entity' -> _() -> swap(player() ~ ['trace',5,'entities'], 'mainhand', player(), 'mainhand'),
        'entity <fromslot>' -> _(fromslot) -> swap(player() ~ ['trace',5,'entities'], fromslot, player(), 'mainhand'),
        'entity <fromslot> <toslot>' -> _(fromSlot, toSlot) -> swap(player() ~ ['trace',5,'entities'], fromSlot, player(), toSlot),
        'entity <fromslot> entity' -> _(fromSlot) -> swap(player() ~ ['trace',5,'entities'], fromSlot, player() ~ ['trace',5,'entities'], 'mainhand'),
        'entity <fromslot> entity <toslot>' -> _(fromSlot, toSlot) -> swap(player() ~ ['trace',5,'entities'], fromSlot, player() ~ ['trace',5,'entities'], toSlot),
        'entity entity <toslot>' -> _(toSlot) -> swap(player() ~ ['trace',5,'entities'], 'mainhand', player() ~ ['trace',5,'entities'], toSlot),
    },
    'arguments' -> {
        'fromslot' -> {
            'type' -> 'term',
            'options' -> global_slots
        },
        'toslot' -> {
            'type' -> 'term',
            'options' -> global_slots
        }
    }
};

swap(fromEntity, fromSlot, toEntity, toSlot) -> (
    if(!fromEntity || !toEntity,
        return(print(player(), format('n Look an entity to run this command.')))
    );
    if(!player() ~ 'permission_level' && (isOtherPlayer(player(), fromEntity) || isOtherPlayer(player(), toEntity)),
        return(print(player(), format('n You can\'t swap items to another player.')))
    );
    if(!isValid(fromEntity) || !isValid(toEntity),
        return(print(player(), format('n The entity you\'re looking at is not a valid entity.')))
    );
    if(fromEntity == toEntity && fromSlot == toSlot,
        return(print(player(), format('n You can\'t swap items on the same slot of the same entity.')))
    );
    toItem = toEntity ~ ['holds', toSlot] || [null, null, null];
    fromItem = fromEntity ~ ['holds', fromSlot] || [null, null, null];
    if(toItem == fromItem,
        return(print(player(), format('n You can\'t swap two identical items.')))
    );
    success = _inventory_set(toEntity, toSlot, fromItem:1, fromItem:0, fromItem:2) &&
              _inventory_set(fromEntity, fromSlot, toItem:1, toItem:0, toItem:2);
    if(!success,
        _inventory_set(fromEntity, fromSlot, fromItem:1, fromItem:0, fromItem:2);
        _inventory_set(toEntity, toSlot, toItem:1, toItem:0, toItem:2);
        return(print(player(), format('n You can\'t swap this two items.')))
    );
    sound('minecraft:item.armor.equip_generic', pos(fromEntity), 1, 1, 'player');
);

isOtherPlayer(player1, player2) -> player2 ~ 'type' == 'player' && player1 != player2;

isValid(entity) ->
entity ~ 'type' == 'player' || (
    entity ~ 'nbt' : 'ArmorItems' &&
    entity ~ 'nbt' : 'HandItems'
);

getSlotNumber(entity, slot) ->
if(entity ~ 'type' == 'player',
    if(
        slot == 'mainhand', entity ~ 'selected_slot',
        slot == 'offhand', -1,
        35 +  global_slots ~ slot
    ),
// else
    global_slots ~ slot
);

_inventory_set(entity, slot, count, item, nbt) -> (
    item = if(!count || !item, 'air', item);
    count = count || 1;
    nbt = nbt || '';
    slot = if(slot ~ 'hand', 'weapon.' + slot, 'armor.' + slot);

    run(str('item replace entity %s %s with %s%s %d',
        entity ~ 'command_name',
        slot,
        item,
        nbt,
        count
    )):0
)
