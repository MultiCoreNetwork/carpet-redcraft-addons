__config() -> {
    'stay_loaded' -> true,
    'scope' -> 'global',
    'commands' -> {
        '' -> '_command',
        'player <player>' -> '_player', //'
        'texture <texture>' -> '_texture',
        'value <value>' -> '_value'
    },
    'arguments' -> {
        'player' -> {'type' -> 'players', 'single' -> true},
        'texture' -> {'type' -> 'term', 'suggest' -> ['20c05597fb279fdba990e3d3e344cfe3a2da106004dcb394267a9af9f6d49e99']},
        'value' -> {'type' -> 'text', 'suggest' -> ['eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjBjMDU1OTdmYjI3OWZkYmE5OTBlM2QzZTM0NGNmZTNhMmRhMTA2MDA0ZGNiMzk0MjY3YTlhZjlmNmQ0OWU5OSJ9fX0=']}
    }
};

_get_villager(player) -> if((v=query(player,'trace',5,'entities')) && query(v,'has_scoreboard_tag','gb.skull_villager'),v,null);
_new_trade() -> nbt('{maxUses:2147483647,xp:0,rewardExp:0b,buy:{id:"minecraft:player_head",Count:1b},buyB:{id:"minecraft:emerald",Count:1b},sell:{id:"minecraft:player_head",Count:1b}}');

_add_trade(player, villager, trade) -> (
    nbt_merge = villager~'nbt';
    put(nbt_merge,'Offers.Recipes',trade,-1);
    modify(villager,'nbt_merge',nbt_merge);
    print(player,format('l Custom skull has been memorized by the villager.'));
    sound('block.respawn_anchor.charge', pos(villager), 1, 2, 'neutral')
);

_remove_trade(player, villager) -> (
    trades = nbt(villager~'nbt':'Offers.Recipes');
    delete(trades:'[-1]');
    nbt_merge = nbt('{}');
    nbt_merge:'Offers.Recipes' = trades;
    modify(villager,'nbt_merge',nbt_merge);
    print(player,format('d The last head was forgotten by the villager.'));
    sound('entity.sheep.shear', pos(villager), 1, 0.5, 'neutral')
);

_command() -> (
    player = player();
    if(
      (v=query(player,'trace',5,'entities')) && v~'nbt':'VillagerData.profession'=='minecraft:none',
        modify(v,'tag','gb.skull_villager','global.ignore','global.ignore.pos','global.ignore.gui','global.kill');
        modify(v,'custom_name','Skull Merchant');
        modify(v,'nbt_merge','{VillagerData:{profession:"minecraft:nitwit",level:5}},Offers:{Recipes:[]}}');
        print(player,format('l Skull Villager summoned successfully.'));
        sound('block.respawn_anchor.charge', pos(v), 1, 2, 'neutral'),
    // else
        _command_error(player)
    )
);

_player(player_name) -> if(v=_get_villager(player=player()),
    trade = _new_trade();
    trade:'sell.tag.SkullOwner' = player_name;
    _add_trade(player, v, trade),
// else
    _command_error(player)
);

_value(value) -> if(v=_get_villager(player=player()),
    trade = _new_trade();
    trade:'sell.tag' = nbt(str('{SkullOwner:{Id:[I;%d,%d,%d,%d],Properties:{textures:[{Value:"%s"}]}}}',
        rand(2147483647),
        rand(2147483647),
        rand(2147483647),
        rand(2147483647),
        value
    ));
    _add_trade(player, v, trade),
// else
    _command_error(player)
);

_texture(texture) -> if(v=_get_villager(player=player()),
    _value(encode_b64(str('{"textures":{"SKIN":{"url":"http://textures.minecraft.net/texture/%s"}}}',texture))),
// else
    _command_error(player)
);

_command_error(player) -> (
    print(player,format(
        'd Look a villager without profession to convert it into a Skull Merchant.\n',
        'd Use this command to tech to villagers how to trade a new head.'
    ));
    run(str('tellraw %s ["",{"text":"Visit ","color":"gold"},{"text":"minecraft-heads.com","underlined":true,"color":"blue","clickEvent":{"action":"open_url","value":"https://minecraft-heads.com/custom-heads/"}},{"text":" to get values and textures.","color":"gold"}]',player~'command_name'))
);

__on_player_interacts_with_entity(player, entity, hand) -> (
    if(!player~'sneaking' || entity~'type' != 'villager' || !query(entity,'has_scoreboard_tag','gb.skull_villager') || !(item_tuple=query(player,'holds',hand)), return());
    [item, count, nbt] = item_tuple;
    if(item!='shears' || !entity~'nbt':'Offers.Recipes', return());
    _remove_trade(player, entity)
)
