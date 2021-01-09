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
bossbar('redcraft:timebar','max',24000);

__on_tick() -> (
    d = day_time();
    w = world_time();
    n = d>12540 && d <= 23458;
    bossbar('redcraft:timebar','name',_date(w));
    bossbar('redcraft:timebar','value',d);
    bossbar('redcraft:timebar','color',if(n, 'blue', 'yellow'));
);

_date(tick) -> (
    date = convert_date(tick*50);
    str('%dg %02d:%02d:%02d', floor(tick/1728000), date:3, date:4, date:5)
);

__on_player_connects(players) -> (
    bossbar('redcraft:timebar','add_player',players)
)
