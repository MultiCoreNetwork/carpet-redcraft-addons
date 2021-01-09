__config() -> {
    'stay_loaded' -> true,
    'scope' -> 'global',
	'commands' -> {
		'<statistic>' -> '_show_stat',
		'hide' -> '_hide',
		'show' -> '_show',
	},
    'arguments' -> {
       'statistic' -> {'type' -> 'criterion'}
    }
};

scoreboard_add('redcraft.stats');

_show() -> scoreboard_display('sidebar', 'redcraft.stats');
_hide() -> scoreboard_display('sidebar', null);

_show_stat(stat) -> (
    _hide();
    if((stats = stat ~ '^minecraft.(\\w+):minecraft.(\\w+)$') == null, return());
    scoreboard_remove('redcraft.stats');
    scoreboard_add('redcraft.stats', stat);
    save();
    run(str('scoreboard objectives modify redcraft.stats displayname {"text":"%s","color":"#ff0000"}',
        if(stats:0 != 'custom', title(stats:0+' '), '') + title(replace(stats:1, '_', ' '))
    ));
    nbt = parse_nbt(storage('redcraft:players'));
    for(nbt,scoreboard('redcraft.stats', nbt:_, offline_statistic(_, stats:0, stats:1)));
    _show()
);

__on_player_connects(player) -> (
    if(player~'gamemode_id'==3,return());
    nbt = parse_nbt(storage('redcraft:players'));
    if(put(nbt, player~'uuid', player~'name') != null,
        storage('redcraft:players', encode_nbt(nbt))
    )
)
