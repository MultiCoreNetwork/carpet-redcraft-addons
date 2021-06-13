import('math','_euclidean');

__config() -> {
    'stay_loaded' -> true,
    'scope' -> 'global',
    'commands' -> {
    '<locatedplayer>' -> _(locatedplayer) ->(
        executorname = player()~'command_name';
        executorposition = player()~'pos';
        executordimension = player()~'dimension';
        playerposition = player(locatedplayer)~'pos';
        posizione = floor(player(locatedplayer)~'x') + ' ' + floor(player(locatedplayer)~'y') + ' ' + floor(player(locatedplayer)~'z');
        dimensione = player(locatedplayer)~'dimension';
        distanza = floor(_euclidean(executorposition, playerposition));
        if (executordimension == dimensione,
            print(executorname, format('w The player ' + locatedplayer + ' is at ', 'l [' + posizione + ']', '^ Click to teleport', '!/execute in ' + dimensione + ' run tp ' + executorname + ' ' + locatedplayer, 'w  (' + distanza + ' blocks away)')),
            print(executorname, format('w The player ' + locatedplayer + ' is at ', 'l [' + posizione + ']', '^ Click to teleport', '!/execute in ' + dimensione + ' run tp ' + executorname + ' ' + locatedplayer, 'w  (' + dimensione  + ')'))
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