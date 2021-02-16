// Recreate of carpet command /player <name> attack and use for yourself

__config() -> {
    'stay_loaded' -> true,
    'commands' -> {
        'attack <ticks>' -> _(tk) -> 
        (name = player()~'command_name'; 
        if( (tk) > 0, 
        run('player '+name+' attack interval '+ tk), 
        run('player '+name+' attack once'))),

        'use <ticks>' -> _(tk) -> 
        (name = player()~'command_name'; 
        if( (tk) > 0, 
        run('player '+name+' use interval '+ tk), 
        run('player '+name+' use once')))
    },
    'arguments' -> {
        'ticks' -> {'type' -> 'int', 'min' -> 0, 'max' -> 72000, 'suggest' -> [0]},
    }
}