__config() -> {'stay_loaded' -> true, 'scope' -> 'player'};

global_name = 'Last Death Compass';
global_months = ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'];
global_days = ['Monday', 'Tuesday', 'Wednesday', 'Thursday', 'Friday', 'Saturday',  'Sunday'];
_date(unix_time) -> (
    date = convert_date(unix_time);
    str('%s, %d%s %s %d, %02d:%02d:%02d',
        global_days:(date:6-1), date:2,
        if(
            date:2%10 == 1, 'st',
            date:2%10 == 2, 'nd',
            date:2%10 == 3, 'rd',
            'th'
        ),
        global_months:(date:1-1), date:0, date:3, date:4, date:5)
);

__on_player_uses_item(player, item_tuple, hand) -> (
    g = player ~ 'gamemode';
    if(g == 'spectator' || !player ~ 'sneaking' || !item_tuple, return());
    [item, count, nbt] = item_tuple;
    if(item != 'compass' || nbt:'LodestoneTracked', return());
    storage_nbt_map = parse_nbt(nbt_storage('redcraft:deaths')):(player~'uuid');
    if (storage_nbt_map == null,
        sound('block.note_block.hat',pos(player),1.0,0.5,'player');
        return(),
        sound('block.note_block.hat',pos(player),1.0,2,'player');
    );
    nbt = _compass_nbt(storage_nbt_map, nbt);
    inventory_set(player,if(hand=='mainhand',player~'selected_slot',-1),count,item,nbt);
);

_compass_nbt(storage_nbt_map, nbt) -> (
    nbt = encode_nbt(if(nbt,parse_nbt(nbt),{}) + {
        'LodestonePos'-> pos=storage_nbt_map:'Pos',
        'LodestoneDimension'-> storage_nbt_map:'Dimension',
        'LodestoneTracked'-> false
    });
    lore = [];
    lore += str('{"text":"%s","color":"gray"}', storage_nbt_map:'Dimension');
    lore += str('{"text":"%d %d %d","color":"gray"}', pos:'X', pos:'Y', pos:'Z');
    lore += str('{"text":"%s","color":"gray"}', _date(storage_nbt_map:'Date'));
    nbt:'display.Lore' = encode_nbt(lore);
    nbt:'display.Name' = nbt(str('\'{"text":"%s","italic":false}\'',global_name));
    nbt
);

__on_player_dies(player) -> (
    if(player~'gamemode_id'==3,return());
    nbt = parse_nbt(nbt_storage('redcraft:deaths')) + {player~'uuid' -> {
        'Pos' -> {
            'X' -> floor(player~'x'),
            'Y' -> floor(player~'y'),
            'Z' -> floor(player~'z')
        },
        'Dimension' -> player~'dimension',
        'Date' -> unix_time(),
        'Tick' -> system_info('world_time')
    }};
    nbt_storage('redcraft:deaths', encode_nbt(nbt))
);

__on_player_respawns(player) -> schedule(0,_(outer(player)) -> if(
    player ~ 'gamemode' != 'spectator',
    storage_nbt_map = parse_nbt(nbt_storage('redcraft:deaths')):(player~'uuid');
    if (storage_nbt_map == null, return());
    nbt = _compass_nbt(storage_nbt_map, null);
    nbt:'Enchantments' = nbt('[{id:"vanishing_curse",lvl:1}]');
    if(!inventory_get(player, 8),
        inventory_set(player, 8, 1, 'compass', nbt)
    )
))
