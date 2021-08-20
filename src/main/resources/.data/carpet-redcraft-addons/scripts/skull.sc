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
    if(trades = villager~'nbt':'Offers.Recipes',
        trades = slice(str(trades),0,length(trades)-1)+','+str(trade)+']',
        trades = nbt('['+str(trade)+']')
    );
    nbt_merge = nbt('{}');
    nbt_merge:'Offers.Recipes' = nbt(trades);
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

global_utf16 = {'"'->34,'.'->46,'/'->47,'0'->48,'1'->49,'2'->50,'3'->51,'4'->52,'5'->53,'6'->54,'7'->55,'8'->56,'9'->57,':'->58,'I'->73,'K'->75,'N'->78,'S'->83,'a'->97,'b'->98,'c'->99,'d'->100,'e'->101,'f'->102,'h'->104,'i'->105,'l'->108,'m'->109,'n'->110,'p'->112,'r'->114,'s'->115,'t'->116,'u'->117,'x'->120,'{'->123,'}'->125};
_utf16(c) -> (
    return(global_utf16:c)
);

global_base64chars = ['A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z','0','1','2','3','4','5','6','7','8','9','+','/'];
_encode_base64(s) -> (
    r = '';
    p = '';
    c = length(s) % 3;
    p += '='*(3-c);
    s += '\0'*(3-c);
    s_list=split('',s);
    c_for(c=0,c<length(s),c+=3,
        n = _utf16(s_list:c) * 2^16 + _utf16(s_list:(c+1)) * 2^8 + _utf16(s_list:(c+2));
        n = [floor(n / 2^18) % 64, floor(n / 2^12) % 64, floor(n / 2^6) % 64, n % 64];
        r += global_base64chars:(n:0) + global_base64chars:(n:1) + global_base64chars:(n:2) + global_base64chars:(n:3)
    );
    return(slice(r,0, length(r) - length(p)) + p);
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
    _value(_encode_base64(str('{"textures":{"SKIN":{"url":"http://textures.minecraft.net/texture/%s"}}}',texture))),
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
