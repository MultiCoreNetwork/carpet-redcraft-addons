__config() -> {
    'stay_loaded' -> true,
    'scope' -> 'global',
    'command_permission' -> 'ops',
    'commands' -> {
        '' -> _() -> _enchant(player()),
        'creativeDrop' -> _() -> _changeCreativeDrop(player())
    }
};

global_drop_in_creative = true;
global_create_item_whitelist = ['^cake$'];
global_convert_item_whitelist = ['^dirt_path$', '^farmland$'];
global_preserve_block_state_blacklist = ['_bed$', '_door$', '^sticky_piston$', '^piston$', '^bee_nest$', '_banner$', '^beehive$', '^redstone_wire$', '^sunflower$', '^lilac$', '^rose_bush$', '^peony$', '^tall_grass$', '^large_fern$'];
global_preserve_block_data_blacklist = ['^bee_nest$', '^beehive$', '^campfire$', '^soul_campfire$', '^lectern$', '^jukebox$', '_banner$', '^player_head$', '_bed$'];
global_need_forced_placement = ['^spawner$','_sign$'];
global_plants = ['^kelp_plant$','^twisting_vines_plant$','^weeping_vines_plant$'];

// Change global_drop_in_creative boolean
_changeCreativeDrop(player) -> (
    if(player ~ 'permission_level' >= 1, (
    global_drop_in_creative = !global_drop_in_creative;
    print(player, str('SilkyBlockStates drop in creative is now set to: %s', global_drop_in_creative));
    ));
);




// Enchants player's tool with silkyblockstate
_enchant(player) -> (
    item_tuple = player ~ 'holds' || ['diamond_axe',1,null];
    [item, count, nbt] = item_tuple;
    if(!(item ~ '_axe$' || item ~ '_pickaxe$' || item ~ '_hoe$' || item ~ '_shovel$') || nbt:'Enchantments[{id:"redcraft:silkyblockstate"}]',
        print(format('r Unable to apply the silkyblockstate enchantment'));
        return()
    );
    if(!nbt, nbt = nbt('{}'));
    nbt:'display.Lore' = nbt:'display.Lore' || nbt('[]');
    nbt:'Enchantments' = nbt:'Enchantments' || nbt('[]');
    nbt_map = parse_nbt(nbt);
    nbt_map:'display':'Lore' += '{"text":"Silky Blockstate","color":"red","italic":false}';
    nbt_map:'Enchantments' += {'id' -> 'redcraft:silkyblockstate'};
    nbt = encode_nbt(nbt_map);
    nbt:'Enchantments[{id:"redcraft:silkyblockstate"}].lvl' = nbt('1s');
    inventory_set(player, player ~ 'selected_slot', count, item, nbt)
);

// returns the escaped version of the string (\->\\, d->\d)
escape_string(string,d) -> (
    replace(replace(string, '\\\\', '\\\\\\\\'), d, '\\\\'+d)
);

// returns true if the block is repleaceable while trying to place a block in its position
global_replaceable = {'air', 'bubble_column', 'cave_air', 'crimson_roots', 'dead_bush', 'fern', 'fire', 'grass', 'large_fern', 'lava','nether_sprout','sea_grass', 'soul_fire', 'structure_void', 'tall_grass', 'vines', 'void_air', 'warped_roots', 'water'};
replaceable(block) ->
    has(global_replaceable, str(block)) || block == 'snow' && property(block, 'layers') == 1;

// adds string as new Lore line to the dropped-item with the given uuid
_add_item_lore(uuid, string, color) -> (
    run(str('data modify entity %s Item.tag.display.Lore append value \'{"text":"%s","color":"%s"}\'',
        uuid,
        replace(escape_string(escape_string(string,'"'),'\''),'\\\\\\\\u00a7','\\u00a7'),
        color
    ));
);

// returns a formatted string of a block property (like in the F3 menu)
_formatted_property(key, value) -> (
    if(
        value == 'true', value='\\u00a7atrue\\u00a7r',
        value == 'false', value='\\u00a7cfalse\\u00a7r',
    );
    key + ': ' + value
);

// returns the list of dropped-items that could be dropped on this tick by mining the given block
_block_item(block) -> (
    block_str = str(block);
    block_str = replace(block_str, 'wall_', '');
    if(_match_any(block,global_plants),
        block_str = replace(block_str, '_plant', '')
    );
    if(
        block_str == 'farmland', block_str = 'dirt',
        block_str == 'dirt_path', block_str = 'dirt'
    );
    entity_selector(str(
        '@e[type=item,limit=1,x=%d,y=%d,z=%d,dx=1,dy=1,dz=1,sort=nearest,nbt={Item:{id:"minecraft:%s",Count:1b},Age:0s}]',
        pos(block):0-1, pos(block):1-1, pos(block):2-1, block_str
    ));
);

// returns the nbt to assign to spawn a dropped-item of the given block
_nbt_block_item(block) -> (
    block_str = str(block);
    block_str = replace(block_str, 'wall_', '');
    if(_match_any(block,global_plants),
        block_str = replace(block_str, '_plant', '')
    );
    str('{Item:{id:"minecraft:%s",Count:1b},PickupDelay:10,Motion:[%f,.2,%f]}', block_str, rand(0.2) - 0.1, rand(0.2) - 0.1)
);

// merges the block's blockstates into the dropped-item spawned
_preserve_block_state(item, player, block) -> (
    blockstate = block_state(block);
    if(block == 'composter' && block_state(block, 'level') == 8, return());
    if(block ~ 'wall_' != null, blockstate:'wall'='true');
    if(_match_any(block,global_plants), blockstate:'plant'='true');
    if(!blockstate && !_match_any(block, global_convert_item_whitelist), return());
    encode_blockstate = encode_nbt(if(blockstate, blockstate, {}));
    if(!item,
        if(_match_any(block, global_create_item_whitelist) || (global_drop_in_creative && player ~ 'gamemode' == 'creative'),
            item = spawn('item', block, _nbt_block_item(block)),
            return()
        )
    );
    modify(item, 'nbt_merge', str('{Item:{tag:{Silked:1b,BlockStateTag:%s}}}', encode_blockstate));
    uuid = item ~ 'command_name';
    if(_match_any(block, global_convert_item_whitelist),
        modify(item, 'nbt_merge', str('{Item:{id:"minecraft:%s"}}', block))
    );
    for(blockstate, _add_item_lore(uuid, _formatted_property(_, blockstate:_), 'gray'))
);

// merges the block's blockdata into the dropped-item spawned
_preserve_block_data(item, player, block, blockdata) -> (
    if(!blockdata, return());
    if(!item,
        if(_match_any(block, global_create_item_whitelist) || (global_drop_in_creative && player ~ 'gamemode' == 'creative'),
            item = spawn('item', block, _nbt_block_item(block)),
            return()
        )
    );
    modify(item, 'nbt_merge', str('{Item:{tag:{Silked:1b,BlockEntityTag:%s}}}', blockdata));
    uuid = item ~ 'command_name';
    //_add_item_lore(uuid, '\\u00a7lBlockEntityTag:\\u00a7r', 'gray');
    c_for(i = 0, i < length(blockdata), i += 50,
        _add_item_lore(uuid, slice(blockdata, i, i + 50), 'dark_gray')
    )
);

// moves item to player
_move_to_player(item, player) -> (
    modify(item, 'pos', pos(player));
    modify(item, 'pickup_delay', 0);
);

// returns item entities near the pos
_items_near_pos(pos) -> (
    entity_selector(str(
        '@e[type=item,limit=1,x=%d,y=%d,z=%d,dx=1,dy=1,dz=1,sort=nearest,nbt={Age:0s}]',
        pos - 1
    ));
);

// returns true if the player has space to pick up the given item
_inventory_space(item_entity, player) -> (
    slot = inventory_find(player, null);
    if(slot != null && slot < 36, return(true));
    item_entity_info = item_entity ~ 'nbt':'Item';
    item = item_entity_info:'id';
    count = item_entity_info:'Count';
    nbt = item_entity_info:'tag';

    slot = -1;
    while((slot = inventory_find(player, item, slot+1)) != null, 41,
        [item1, count1, nbt1] = inventory_get(player, slot);
        if(nbt1 == nbt, count += count1 - stack_limit(item))
    );
    count <= 0
);

// returns true if the player holds a tool enchanted with the given enchant
_holds_enchant(player, enchant) -> (
    item = player ~ 'holds';
    if(!item || !(nbt = item:2), return(false));
    max(nbt:str('Enchantments[{id:"%s"}].lvl', enchant))
);

// return true if the string match at least one regex of the given list
_match_any(string, list) -> (
    for(list, if(string ~ _, return(true)));
    false
);

// return true if that item_tuple needs a forced placement
__need_forced_placement(item_tuple) -> (
    [item, count, nbt] = item_tuple;
    if(_match_any(item, global_need_forced_placement) && nbt:'Silked', return(true));
    if(nbt:'Silked' && (nbt:'BlockStateTag{}':'wall' || nbt:'BlockStateTag{}':'plant'), true, false)
);

// return true if that item_tuple is a waterlogged silked item
__waterlogged_silked(item_tuple) -> (
    [item, count, nbt] = item_tuple;
    if(nbt:'Silked' && nbt:'BlockStateTag{}':'waterlogged'=='true', true, false)
);

// is ti a valid item to use this feature?
_valid_item(player) -> (
	[item, count, nbt] = if(h=player~'holds', h, return(false));
    if(max(nbt:'Enchantments[{id:"redcraft:silkyblockstate"}]'<1),
        if(str(nbt:'display.Lore[]') ~ '\\{"text":"Silky Blockstate","color":"red","italic":false\\}' == null,
            return(false),
            ench = parse_nbt(if(t=nbt:'Enchantments',t,'[]'));
            ench += {'id'->'redcraft:silkyblockstate', 'lvl'->1};
            nbt:'Enchantments' = encode_nbt(ench);
            inventory_set(player,player~'selected_slot',count,item,nbt);
            return(true)
        ),
        return(true)
    )
);

// inject into the dropped-item the custom nbt
__on_player_breaks_block(player, block) ->
if(_valid_item(player),
    blockdata = block_data(block);
    container_size = inventory_size(block);
    // wait for the dropped-item to spawn
    schedule(0, _(outer(player), outer(block), outer(blockdata), outer(container_size)) -> (
        if(item = _block_item(block),
            item = item:0;
            if(!_match_any(block, global_preserve_block_state_blacklist),
                _preserve_block_state(item, player, block);
            );
            if(!_match_any(block, global_preserve_block_data_blacklist) && !container_size,
                _preserve_block_data(item, player, block, blockdata);
            )
        );

        if(system_info('world_carpet_rules'):'carefulBreak' == 'true' && player ~ 'sneaking',
            items = _items_near_pos(pos(block) + 0.5);
            for(items,
                if(_inventory_space(_, player),
                    _move_to_player(_, player)
                )
            )
        )
    ))
);

// fixes the BlockEntityTag for deopped players and the wall_/_plant version of blocks
__on_player_places_block(player, item_tuple, hand, block) ->
if(
    __need_forced_placement(item_tuple),
        [item, count, nbt] = item_tuple;
        if(!nbt || (!nbt:'BlockStateTag{}' && !nbt:'BlockEntityTag{}'), return());
        // if not in blacklist, get the blockstate from item's nbt and format it correctly
        blockstate = if(_match_any(item, global_preserve_block_state_blacklist), encode_nbt(block_state(block)), nbt:'BlockStateTag{}');
        blockstate = if(blockstate, '[' + slice(replace(blockstate, ':', '='), 1, length(blockstate) - 1) + ']', '');
        // handles the wall_/_plant version of the block
        block_id = if(
            blockstate ~ ',?wall="true"' != null,
                blockstate = replace(blockstate, ',?wall="true"', '');
                replace(item,'(^|_)(?=[^_]+$)','$1wall_'),
            blockstate ~ ',?plant="true"' != null,
                blockstate = replace(blockstate, ',?plant="true"', '');
                item+'_plant',
            item
        );
        // if not in blacklist, get the data from item's nbt and format it correctly
        data = if(_match_any(item, global_preserve_block_data_blacklist), block_data(block), nbt:'BlockEntityTag{}');
        data = if(data, data, '');
        // sets the block with correct blockstate and data
        set(block, block_id + blockstate + data),
  __waterlogged_silked(item_tuple) && player~'dimension'=='the_nether',
        set(block,block,'waterlogged','false');
        sound('block.fire.extinguish', pos(block), 1, 1, 'block')
)
