// Recreate of carpet command /player <name> attack and use for yourself

__config() -> {
    'stay_loaded' -> true,
    'scope' -> 'global',
    'commands' -> {
        'attack' -> _() ->
        (name = player()~'command_name';
        run('player '+name+' attack')),

        'attack continuous' -> _() ->
        (name = player()~'command_name';
        run('player '+name+' attack continuous')),

        'attack interval <ticks>' -> _(tk) -> 
        (name = player()~'command_name';  
        run('player '+name+' attack interval '+ tk)),

        'attack once' -> _() ->
        (name = player()~'command_name';
        run('player '+name+' attack once')),

        'dismount' -> _() ->
        (name = player()~'command_name';
        run('player '+name+' dismount')),

        'drop' -> _() ->
        (name = player()~'command_name';
        run('player '+name+' drop')),

        'drop <hotbarslot>' -> _(so) -> 
        (name = player()~'command_name'; 
        st = so - 1; 
        run('player '+name+' drop '+ st)),

        'drop all' -> _() ->
        (name = player()~'command_name';
        run('player '+name+' drop all')),

        'drop continuous' -> _() ->
        (name = player()~'command_name';
        run('player '+name+' drop continuous')),

        'drop interval <ticks>' -> _(tk) -> 
        (name = player()~'command_name';  
        run('player '+name+' drop interval '+ tk)),

        'drop mainhand' -> _() ->
        (name = player()~'command_name';
        run('player '+name+' drop mainhand')),

        'drop offhand' -> _() ->
        (name = player()~'command_name';
        run('player '+name+' drop offhand')),

        'drop once' -> _() ->
        (name = player()~'command_name';
        run('player '+name+' drop once')),

        'dropStack' -> _() ->
        (name = player()~'command_name';
        run('player '+name+' dropStack')),

        'dropStack <hotbarslot>' -> _(so) -> 
        (name = player()~'command_name'; 
        st = so - 1; 
        run('player '+name+' dropStack '+ st)),

        'dropStack all' -> _() ->
        (name = player()~'command_name';
        run('player '+name+' dropStack all')),

        'dropStack continuous' -> _() ->
        (name = player()~'command_name';
        run('player '+name+' dropStack continuous')),

        'dropStack interval <ticks>' -> _(tk) -> 
        (name = player()~'command_name';  
        run('player '+name+' dropStack interval '+ tk)),

        'dropStack mainhand' -> _() ->
        (name = player()~'command_name';
        run('player '+name+' dropStack mainhand')),

        'dropStack offhand' -> _() ->
        (name = player()~'command_name';
        run('player '+name+' dropStack offhand')),

        'dropStack once' -> _() ->
        (name = player()~'command_name';
        run('player '+name+' dropStack once')),

        'hotbar <hotbarslot>' -> _(so) ->
        (name = player()~'command_name';
        run('player '+name+' hotbar '+ so)),

        'jump' -> _() ->
        (name = player()~'command_name';
        run('player '+name+' jump')),

        'jump continuous' -> _() ->
        (name = player()~'command_name';
        run('player '+name+' jump continuous')),

        'jump interval <ticks>' -> _(tk) -> 
        (name = player()~'command_name';  
        run('player '+name+' jump interval '+ tk)),

        'jump once' -> _() ->
        (name = player()~'command_name';
        run('player '+name+' jump once')),

        'look at <rotation>' -> _(rn) ->
        (name = player()~'command_name';
        run('player '+name+' look at'+ rn)),

        'look down' -> _() ->
        (name = player()~'command_name';
        run('player '+name+' look down')),

        'look east' -> _() ->
        (name = player()~'command_name';
        run('player '+name+' look east')),

        'look north' -> _() ->
        (name = player()~'command_name';
        run('player '+name+' look north')),

        'look south' -> _() ->
        (name = player()~'command_name';
        run('player '+name+' look south')),

        'look up' -> _() ->
        (name = player()~'command_name';
        run('player '+name+' look up')),

        'look west' -> _() ->
        (name = player()~'command_name';
        run('player '+name+' look west')),

        'mount' -> _() ->
        (name = player()~'command_name';
        run('player '+name+' mount')),

        'mount anything' -> _() ->
        (name = player()~'command_name';
        run('player '+name+' mount anything')),

        'shadow' -> _() ->
        if(system_info('world_carpet_rules'):'commandPlayer' == 'true',
        name = player()~'command_name';
        run('player '+name+' shadow')),

        'stop' -> _() ->
        (name = player()~'command_name';
        run('player '+name+' stop')),

        'swapHands' -> _() ->
        (name = player()~'command_name';
        run('player '+name+' swapHands')),

        'swapHands continuous' -> _() ->
        (name = player()~'command_name';
        run('player '+name+' swapHands continuous')),

        'swapHands interval <ticks>' -> _(tk) -> 
        (name = player()~'command_name';  
        run('player '+name+' swapHands interval '+ tk)),

        'swapHands once' -> _() ->
        (name = player()~'command_name';
        run('player '+name+' swapHands once')),

        'turn back' -> _() ->
        (name = player()~'command_name';
        run('player '+name+' turn back')),

        'turn left' -> _() ->
        (name = player()~'command_name';
        run('player '+name+' turn left')),

        'turn right' -> _() ->
        (name = player()~'command_name';
        run('player '+name+' turn right')),

        'turn <rotation>' -> _(rn) ->
        (name = player()~'command_name';
        run('player '+name+' turn '+ rn)),

        'use' -> _() ->
        (name = player()~'command_name';
        run('player '+name+' use')),

        'use continuous' -> _() ->
        (name = player()~'command_name';
        run('player '+name+' use continuous')),

        'use interval <ticks>' -> _(tk) -> 
        (name = player()~'command_name';  
        run('player '+name+' use interval '+ tk)),

        'use once' -> _() ->
        (name = player()~'command_name';
        run('player '+name+' use once')),
    },
    'arguments' -> {
        'ticks' -> {'type' -> 'int', 'min' -> 2, 'max' -> 72000, 'suggest' -> [20]},
        'hotbarslot' -> {'type' -> 'int', 'min' -> 1, 'max' -> 9, 'suggest' -> [1,2,3,4,5,6,7,8,9]},
    }
}