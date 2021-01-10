__config() -> {'stay_loaded' -> true, 'scope' -> 'global'};

__on_player_changes_dimension(player, from_pos, from_dimension, to_pos, to_dimension) -> if(
    from_dimension == 'redcraft2:overworld' && to_dimension == 'the_end',
    run(str('execute in redcraft2:the_end run tp %s 100.5 49.00 0.5 90 0',player~'command_name'))
)
