import('math','_euclidean');

__config() -> {
    'stay_loaded' -> true,
    'scope' -> 'global',
    'commands' -> {
        '' -> _() -> _lookme([player() ~ ['trace', 5, 'entities']]),
        '<entities>' -> '_lookme'
    }
};

_lookme(entities) -> 
if(entities:0 == null,
    print(player(), format('r No entity found'))
, player() ~ 'permission_level' >= 2, // elif
    for(entities,
        i += 1;
        run(str(
            'execute as %s at @s run teleport @s ~ ~ ~ facing entity %s', 
            _ ~ 'command_name', 
            player() ~ 'command_name'
        ))
    );
    print(player(), str('Affected %d entit%s', i, if(i==1,'y','ies')));
, // else
    for(entities,
        if(_euclidean(pos(player()), pos(_)) <= 5,
            i += 1;
            run(str(
                'execute as %s at @s run teleport @s ~ ~ ~ facing entity %s', 
                _ ~ 'command_name', 
                player() ~ 'command_name'
            ))
        , // else
            j += 1
        )
    );
    print(player(), str('Affected %d entit%s', i, if(i==1,'y','ies')));
    print(player(), format(str('r %d entit%s too far', j, if(j==1,'y was','ies were'))))
)
