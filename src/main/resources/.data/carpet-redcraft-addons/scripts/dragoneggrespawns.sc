__config() -> {'stay_loaded' -> true, 'scope' -> 'global'};

_drop_egg_on_death(e, new) -> entity_event(e, 'on_removed', _(entity) -> spawn_egg());
entity_load_handler('ender_dragon', '_drop_egg_on_death');
for(entity_list('ender_dragon'), _drop_egg_on_death(_, null));

spawn_egg() -> (
	scan(0, 128, 1, 0, 128, 0, if(_=='end_portal', y_level = _y; break()));
	if(y_level,
		loc = [0, y_level+4, 0];
		if(air(loc), set(loc, 'dragon_egg'))
	)
)

