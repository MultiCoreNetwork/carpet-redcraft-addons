global_custom_stats = ['animals_bred','aviate_one_cm','bell_ring','boat_one_cm','clean_armor','clean_banner','clean_shulker_box','climb_one_cm','crouch_one_cm','damage_absorbed','damage_blocked_by_shield','damage_dealt','damage_dealt_absorbed','damage_dealt_resisted','damage_resisted','damage_taken','deaths','drop','eat_cake_slice','enchant_item','fall_one_cm','fill_cauldron','fish_caught','fly_one_cm','horse_one_cm','inspect_dispenser','inspect_dropper','inspect_hopper','interact_with_anvil','interact_with_beacon','interact_with_blast_furnace','interact_with_brewingstand','interact_with_campfire','interact_with_cartography_table','interact_with_crafting_table','interact_with_furnace','interact_with_grindstone','interact_with_lectern','interact_with_loom','interact_with_smithing_table','interact_with_smoker','interact_with_stonecutter','jump','leave_game','minecart_one_cm','mob_kills','open_barrel','open_chest','open_enderchest','open_shulker_box','pig_one_cm','play_noteblock','play_record','play_time','player_kills','pot_flower','raid_trigger','raid_win','sleep_in_bed','sneak_time','sprint_one_cm','strider_one_cm','swim_one_cm','talked_to_villager','target_hit','time_since_death','time_since_rest','total_world_time','traded_with_villager','trigger_trapped_chest','tune_noteblock','use_cauldron','walk_on_water_one_cm','walk_one_cm','walk_under_water_one_cm'];
global_scoreboard_stats = ['air','armor','deathCount','food','health','level','playerKillCount','totalKillCount','xp'];
__config() -> {
    'stay_loaded' -> true,
    'scope' -> 'global',
	'commands' -> {
		'' -> '_toggle',
        'broken <itemname>' -> _(in) -> _show_stat('minecraft.broken:minecraft.' + in),
		'crafted <itemname>' -> _(in) -> _show_stat('minecraft.crafted:minecraft.' + in),
        'custom <customstats>' -> _(cs) -> _show_stat('minecraft.custom:minecraft.' + cs),
		'dropped <itemname>' -> _(in) -> _show_stat('minecraft.dropped:minecraft.' + in),
        'killed <entityname>' -> _(en) -> _show_stat('minecraft.killed:minecraft.' + en),
		'killed_by <entityname>' -> _(en) -> _show_stat('minecraft.killed_by:minecraft.' + en),
        'mined <blockname>' -> _(bn) -> _show_stat('minecraft.mined:minecraft.' + bn),
        'picked_up <itemname>' -> _(in) -> _show_stat('minecraft.picked_up:minecraft.' + in),
        'used <itemname>' -> _(in) -> _show_stat('minecraft.used:minecraft.' + in),
        '<scoreboardstats>' -> '_show_stat'
	},
    'arguments' -> {
       'blockname' -> {'type' -> 'term', 'suggest' -> block_list(), 'option' -> block_list()},
       'itemname' -> {'type' -> 'term', 'suggest' -> item_list(), 'option' -> item_list()},
       'entityname' -> {'type' -> 'term', 'suggest' -> entity_types(), 'option' -> entity_types()},
       'customstats' -> {'type' -> 'term', 'suggest' -> global_custom_stats, 'option' -> global_custom_stats},
       'scoreboardstats' -> {'type' -> 'term', 'suggest' -> global_scoreboard_stats, 'option' -> global_scoreboard_stats}
    }
};

scoreboard_add('redcraft.stats');

global_show = false;
_show() -> (
    scoreboard_display('sidebar', 'redcraft.stats');
    global_show = true
);
_hide() -> (
    scoreboard_display('sidebar', null);
    global_show = false
);
_toggle() -> if(global_show = !global_show,
    scoreboard_display('sidebar', 'redcraft.stats'),
    scoreboard_display('sidebar', null)
);

_show_stat(stat) -> (
    _hide();
    if((stats = stat ~ '^minecraft.(\\w+):minecraft.(\\w+)$') == null,
        stats = ['', stat]
    );
    scoreboard_remove('redcraft.stats');
    try(
        scoreboard_add('redcraft.stats',stat),
    // catch
        exit(print(format('rbu Error:','r Unknown criterion. Please check again.')))
    );
    // save();
    name = if(stats:0 != 'custom' && stats != '', title(stats:0+' '), '') + title(replace(stats:1, '_', ' '));
    scoreboard_property('redcraft.stats', 'display_name', format('#ff0000 '+ name));
    if(stats:0 != '',
        players = parse_nbt(nbt_storage('redcraft:players'));
        for(players, scoreboard('redcraft.stats', players:_, offline_statistic(_, stats:0, stats:1)))
    );
    _show()
);

__on_player_connects(player) -> (
    if(player~'gamemode_id'==3,return());
    nbt = parse_nbt(nbt_storage('redcraft:players'));
    if(put(nbt, player~'uuid', player~'name') != null,
        nbt_storage('redcraft:players', encode_nbt(nbt))
    )
)
