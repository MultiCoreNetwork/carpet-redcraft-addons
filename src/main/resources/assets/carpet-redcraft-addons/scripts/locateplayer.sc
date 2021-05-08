import('math','_euclidean');

__config() -> {
    'stay_loaded' -> true,
    'scope' -> 'global',
    'commands' -> {
    '<locatedplayer>' -> _(locatedplayer) ->
    (
    executorname = player()~'command_name';
    executorposition = player()~'pos';
    executrdimension = player()~'dimension';
    playerposition = player(locatedplayer)~'pos';
    posizione = floor(player(locatedplayer)~'x') + ' ' + floor(player(locatedplayer)~'y') + ' ' + floor(player(locatedplayer)~'z');
    dimensione = player(locatedplayer)~'dimension';
    distanza = floor(_euclidean(executorposition, playerposition));
    if (executrdimension == dimensione,
    print(executorname, format('w The player ' + locatedplayer + ' is at ', 'l [' + posizione + ']', '^di Click to tp', '!/execute in ' + dimensione + ' run tp ' + executorname + ' ' + locatedplayer, 'w  (' + distanza + ' blocks away)')),
    print(executorname, format('w The player ' + locatedplayer + ' is at ', 'l [' + posizione + ']', '^di Click to tp', '!/execute in ' + dimensione + ' run tp ' + executorname + ' ' + locatedplayer, 'w  (' + dimensione  + ')'))
    )
    )
    },
    'arguments' -> {
        'locatedplayer' -> {
           'type' -> 'players',
           'single' -> true,
        }
    }
}