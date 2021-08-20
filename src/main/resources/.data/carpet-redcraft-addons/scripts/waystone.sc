__config() -> {
    'stay_loaded' -> true,
    'scope' -> 'global',
    'commands' -> {'' -> '_command'}
};

_command() -> (
    player = player();
    if(
      (v=query(player,'trace',5,'entities')) && v~'nbt':'VillagerData.profession'=='minecraft:none',
        modify(v,'tag','gb.waystone','global.ignore','global.ignore.pos','global.ignore.gui','global.kill');
        modify(v,'custom_name','Waystone Merchant');
        modify(v,'nbt_merge','{VillagerData:{profession:"minecraft:nitwit",level:5}},Offers:{Recipes:[]}}');
        print(player,format('l Waystone Villager summoned successfully.'));
        sound('block.respawn_anchor.charge', pos(v), 1, 2, 'neutral'),
    // elif
      item_tuple = player~'holds',
        [item, count, nbt] = item_tuple;
        if(nbt:'waystone'==-1,
            print(player,format('n The selected item already has a stored position in it.')),
        // else
            nbt = _store_waystone(player, nbt);
            inventory_set(player, player~'selected_slot', count, item, nbt);
            print(player,format('l The current position has been saved in the selected item.'));
            sound('block.respawn_anchor.set_spawn', pos(player), 1, 2, 'neutral')
        ),
    // else
        print(player,format('d Hold an item to create a new Waystone item.\n', 'd Look a villager without profession to convert it into a Waystone Merchant.'))
    )
);

_store_waystone(player, nbt) -> (
    if(!nbt, nbt = nbt('{}'));
    nbt:'waystone_pos' = encode_nbt(player~'pos');
    nbt:'waystone' = nbt('-1b');
    nbt:'waystone_dimension' = encode_nbt(player~'dimension');
    if(lore=nbt:'display.Lore', lore = parse_nbt(lore), lore = []);
    lore += str('{"text":"Coordinates: %.02f %.02f %.02f","color":"white","italic":false}', player~'pos');
    lore += str('{"text":"Dimension: %s","color":"white","italic":false}', title(replace(player~'dimension','_',' ')));
    nbt:'display.Lore' = encode_nbt(lore);
    nbt:'waystone_rotation' = nbt('['+player~'yaw'+'f,'+player~'pitch'+'f]');
    nbt
);

_add_trade(player, villager, trade) -> (
    if(trades = villager~'nbt':'Offers.Recipes',
        trades = slice(str(trades),0,length(trades)-1)+','+str(trade)+']',
        trades = nbt('['+str(trade)+']')
    );
    nbt_merge = nbt('{}');
    nbt_merge:'Offers.Recipes' = nbt(trades);
    modify(villager,'nbt_merge',nbt_merge);
    print(player,format('l Selected item has been memorized by the villager.'));
    sound('block.respawn_anchor.charge', pos(villager), 1, 2, 'neutral')
);

_remove_trade(player, villager) -> (
    trades = nbt(villager~'nbt':'Offers.Recipes');
    delete(trades:'[-1]');
    nbt_merge = nbt('{}');
    nbt_merge:'Offers.Recipes' = trades;
    modify(villager,'nbt_merge',nbt_merge);
    print(player,format('d The last position was forgotten by the villager.'));
    sound('entity.sheep.shear', pos(villager), 1, 0.5, 'neutral')
);

__on_player_interacts_with_entity(player, entity, hand) -> (
    if(!player~'sneaking' || entity~'type' != 'villager' || !query(entity,'has_scoreboard_tag','gb.waystone') || !(item_tuple=query(player,'holds',hand)), return());
    [item, count, nbt] = item_tuple;
    if(trades = entity~'nbt':'Offers.Recipes', trades = parse_nbt(trades), trades = []);
    if(
      nbt:'waystone'==-1,
        trade = nbt('{maxUses:2147483647,xp:0,rewardExp:0b,buy:{id:"minecraft:emerald",Count:0b,tag:{unoccupied:1b}},buyB:{id:"minecraft:diamond",Count:1b},sell:{id:"minecraft:oak_planks",Count:1b,tag:{unoccupied:1b}}}');
        trade:'sell' = player~'nbt':str('Inventory[{Slot:%db}]',if(hand=='mainhand',player~'selected_slot',-106));
        trade:'sell.Count' = 1;
        trade:'sell.tag.waystone' = nbt('1b');
        _add_trade(player, entity, trade),
    // elif
      item=='shears' && entity~'nbt':'Offers.Recipes',
        _remove_trade(player, entity)
    );
);

__on_player_trades(player, entity, buy_left, buy_right, sell) -> if(sell:2:'waystone'==1,
    run(str('execute in %s run tp %s %f %f %f %f %f',
        sell:2:'waystone_dimension',
        player~'command_name',
        sell:2:'waystone_pos[0]',
        sell:2:'waystone_pos[1]',
        sell:2:'waystone_pos[2]',
        sell:2:'waystone_rotation[0]',
        sell:2:'waystone_rotation[1]'
    ));
    print(player,format('l The teleportation has been completed successfully.'));
    sound('entity.enderman.teleport', pos(player), 1.0, 1, 'player');
    schedule(0, _(outer(player),outer(sell)) -> (
        run(str('clear %s %s{waystone:1b}',player~'command_name',sell:0));
        run('kill @e[type=item,nbt={Item:{tag:{waystone:1b}}}]')
    ))
)
