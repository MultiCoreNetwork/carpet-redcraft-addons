__config() -> {
    'stay_loaded' -> true,
    'scope' -> 'global',
    'commands' -> {'' -> '_command'}
};

_command() -> (
    list = bossbar('redcraft:timebar','players');
    player = player();
    if((i = list ~ player) != null,
        delete(list:i),
        list += player
    );
    bossbar('redcraft:timebar','players', list)
);

bossbar('redcraft:timebar');
bossbar('redcraft:timebar', 'max', 24000);
bossbar('redcraft:timebar', 'visible', true);

_date(tick) -> str('%dd %02d:%02d:%02d',
    floor(tick/1728000),
    floor(tick%1728000/72000),
    floor(tick%72000/1200),
    floor(tick%1200/20)
);

fast_looper() -> (
    d = day_time()%24000;
    w = system_info('world_time');
    n = d>12540 && d <= 23458;
    bossbar('redcraft:timebar','name',_date(w));
    bossbar('redcraft:timebar','value',d);
    bossbar('redcraft:timebar','color',if(n, 'blue', 'yellow'));
    schedule(20, 'fast_looper')
);
fast_looper();

__on_player_connects(players) -> (
    bossbar('redcraft:timebar','add_player',players)
);

__on_close() -> (
    bossbar('redcraft:timebar', 'visible', false)
)
