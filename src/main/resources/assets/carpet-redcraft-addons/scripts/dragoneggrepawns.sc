__config() -> {'stay_loaded' -> true, 'scope' -> 'global'};

// BUG: if the game crashes while a dragon is alive, it might deattach the event on restarting
entity_load_handler('ender_dragon', _(e) -> entity_event(e, 'on_removed', _(entity) -> spawn_egg()));

spawn_egg() -> (
	scan(0, 128, 1, 0, 128, 0, if(_=='end_portal', y_level = _y; break()));
	if(y_level,
		loc = [0, y_level+4, 0];
		if(air(loc), set(loc, 'dragon_egg'))
	)
)

