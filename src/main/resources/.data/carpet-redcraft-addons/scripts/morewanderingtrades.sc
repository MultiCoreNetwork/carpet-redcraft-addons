__config() -> {'stay_loaded' -> true, 'scope' -> 'global'};

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

_new_trade(buy_left,buy_right,result) -> nbt(str('{maxUses:1,xp:50,rewardExp:1b,buy:{id:"minecraft:%s",Count:%db},buyB:{id:"minecraft:%s",Count:%db},sell:{id:"minecraft:%s",Count:%db}}',buy_left:0,buy_left:1,buy_right:0,buy_right:1,result:0,result:1));
_add_trade(villager, trade) -> (
    nbt_merge = villager~'nbt';
    put(nbt_merge,'Offers.Recipes',trade,-1);
    modify(villager,'nbt_merge',nbt_merge);
);

global_skull_trades = {'ender_chest'->'a6cc486c2be1cb9dfcb2e53dd9a3e9a883bfadb27cb956f1896d602b4067','enchanting_table'->'e5696373a187e6d2ddcdf1d67774cb11f3a52a9767a084589b23f19a7f23a71a','furnace'->'129577b8f40d614d2a8094615da1068cf12cb8f78345839bd0f7eaa77b06e27e','crafting_table'->'5e6ab3fdc2bcb07ae7690801ae00ac2fff542d79308961c2b579370ecf656c29','chest'->'d5c6dc2bbf51c36cfc7714585a6a5683ef2b14d47d8ff714654a893f5da622','shulker_box'->'39aa88a05fa5f33b632e55a6455c14fb0fa279e63147f9778d39df8f58a47922','jukebox'->'e8f567339f456cd79875c6df037425202122c8a416a4d4e98722cb01aa5f8898','white_concrete'->'d0925c48b05662848ec9f0685f858d889d3da11b071788eaa36ccd8f1f31de','orange_concrete'->'63f13e23ec23467ac5e6f65a83f2686ebecd9986df5f8cbcd6fafc42e662b38','magenta_concrete'->'65ef2d87f7c1def79523e956767b8284ca389b24299f5e465d457989d62df8','light_blue_concrete'->'f477f44389362c4c764c847a9739bc78c32467eab4e3830ae4c8beac3442ef9','yellow_concrete'->'fca5bff325ed71d97a2ddfc83acf05fe7fd9cb7cbdb15ebb4f05621907e9f2b','lime_concrete'->'4b599c618e914c25a37d69f541a22bebbf751615263756f2561fab4cfa39e','pink_concrete'->'22749d37c3f98d457bf54022a8b613a4353ed8dd2e149426fc42db3b7d','gray_concrete'->'c06d7befc82f201f83e193767e63f81037215afd483a38d36965982ca6d0','light_gray_concrete'->'6ba0c4a0fdce923a9048328d664147c5b924491f4ee5fea71f3e9ec314','cyan_concrete'->'f33b51fef1d7fdf19274bb76fcedecec7a77d010cb34fe029fb794cc59aba','purple_concrete'->'f0c05d560d8e13f0cb25c15c831bc595450c5e54ce35fa54e17e0495267c','blue_concrete'->'57c7a297103db084af6273b879805afc8577cc82c752ac266cf8d7a6eea60','brown_concrete'->'6538120f618f2ccd3bbdc18cd5782e6382ae9ee72d05f5f86276ada54ef7ed','green_concrete'->'dfab7daeb8f333c7886a70ef30caf4dec4a8cd10493f23802f1516bdd23fcd','red_concrete'->'61856c7b378d350262143843d1f9fbb21911a71983ba7b39a4d4ba5b66bedc6','black_concrete'->'4fc2372b457542c65483afa1442e1c33a5ff758d362ecec4348795172824d869','obsidian'->'84c30855f89b490c6ff232df3d3ec7433ab63610a9a957c88a6cd4327b06a49e','bricks'->'4d6abccdfdb5231f74710d778c2084f4c8e4cd6913a70e8a7213adb639192527','sandstone'->'1bae4119857bd82c7edec034820b77d5a83600c9dadcbab85a704313515601ac','quartz_block'->'e2106459d2247b43c628dcf6c68f74bb7066948ada11d33a804953cb5c60f67c','purpur_block'->'5745eb537a07acbba7fab5009051c42b82b7e877eb83689131d4876118f39300','snow_block'->'63a9e153828f5fe32c1c485aae15c0c1f516efe7f470fa8c0c3b94081b56e0a4','terracotta'->'fdd1bdb941bd6928fa01e3cdca637af5b61fbcbafae962d4143049153b0758a3','end_stone_bricks'->'b0c74e01b8b352565c71b4fb28121008f6284017532dcd91802338821f7db48f','red_mushroom_block'->'3ba1a3d876a07a300dac5150eb70f4da64186c6770d409c185ba8cb0902e8fab','sand'->'9178d81daf7e0df297bcb5bb9009fb663203f9e20736194df83aac8ea94083b1','red_sand'->'4393093e8ee64eae0e6cfa52f2988d0d076a425c44bfa3d43440f7931c54e56b','gravel'->'7e4a8e51e2899068e823c818db1a0db9447d2f16f4a1578e5d1461d714765e62','grass_block'->'f78499776ff2dab77a593db6077a6f7654931e55ff55e5dad2d2807bb0e37768','dirt'->'1ab43b8c3d34f125e5a3f8b92cd43dfd14c62402c33298461d4d4d7ce2d3aea','podzol'->'9046b8c430649083f864d40dfac85be0d4ddadbdde919e3626cc7f4174f54feb','mycelium'->'a3c077ed6d9962230d9d8670bad876c33941fc9cfb17e6ed0a80527c69185478','clay'->'5924eda72a07f56328e036f230e488817dd4d45681913d2fc9bf2d2a5f1941ae','packed_ice'->'31542286da03ab7eecc4e562c3f8b8a1cf781da308b079e3ac3314561b9c1d99','melon'->'283f785bf0a0054470d74ae2d1285252956fee0abd284afa3715404eec66eeef','pumpkin'->'b9be550531888d6325717b753e6252e380867369de01225fa402e1aeec6faf79','hay_block'->'8b596b7286eef2df275c0fb3f491664c6efd30ca7d4697b99849a0a46bde3d24','dark_prismarine'->'8215241a20edea0a64f682f38a69d1dcdfaa8d9cc668a78b3b72c088fd2291d9','prismarine_bricks'->'1bda3293ec6a00332e69862c72a4ff7b1d4a80ee65a4e0e515b57438b961871a','prismarine'->'e8622f3371558dc4c7431c32e35e74beaea66082c3e4cccf7006231f8f2383ae','sea_lantern'->'4f8503ef79135e0db46167c304aec9f781407ff86b09347d7f98fa148f939b10','tube_coral_block'->'39fc3cf5508d40c14699d5bf7f252759e1956af9a66d15a6c3854378c61f6d9a','brain_coral_block'->'957dd43e8f1cd13726c0f99ac404715047d12eb42a28ffc6ae9bfb3b70d7640a','bubble_coral_block'->'13f1805edcd32b97ab2f19a03baab1f8d4dc4db8ec7a004b14eb666d09fb87f2','fire_coral_block'->'9fe8ded3c74eacd78412a903b904f5577850d1e20d34878fd77597ad1633bf74','horn_coral_block'->'3491b6e6ae9451c47e09bf1ff23d50fff87ba5927a51f46ffedf926c5cbde77f','dried_kelp_block'->'b805caf3d02e35e4aadc19af15e82790177f1cd7b79f4f5b88d943ac6be203a1','nether_wart_block'->'a00623998a28709fcf053fc569687115867e0c7e5989a94b6e4bf51d8ed79b46','magma_block'->'a5d71026b8589125d4b979c9721c90b77584bd7b2094282d0f2fa35306442aa5','red_nether_bricks'->'d77699efd4b970a0d1c65aefbcbce8d3beba88f1bec29bfa8645088f6b25bc61','soul_sand'->'fd215c0690260210f981ca9bb81e642b1882ea3c7bf81930bdafffb1dae1fc9a','nether_bricks'->'7ce46fe3e758260e600a89c33d13e1f627fd061d5e0dd78bab8899551e84aa08','netherrack'->'9fc44f7f76ed12472f705e99bb5d79a446534e9dc06d228bb61941c3fb84947b','glowstone'->'c46d34958f87041b5e4af641238600a277f064ff22dc13f6af9b6be5547dd790','coal_ore'->'bf400577219333eb614a4d92bef239d30e2cb092d2ad7dfea5bd9d0f1ac40bea','iron_ore'->'9e8aa71f5991aa7dcf5748351e5f1ac4f5459686c4e476f85deb569244f35986','gold_ore'->'db04b94fd491ccc42c7626ee5d867a6a1126e91853f9492a149a61eb9dd82e58','lapis_ore'->'2cd962e5908eaa1a22bca668798f9aed33b37b48e16a2fc59eb4da0ab457cdbe','diamond_ore'->'a977bf5c1f1df40a99ae478d04e8c1ea86b881de0fefaefd7ba6d597c871ec44','redstone_ore'->'68133917589cf10c7fc46ef331209669bd7aa5244805193239243bd5a548fcc','emerald_ore'->'6e60f89c165a19ef914609e24343249212e51f6bde9214f69eb2e104d0f96edb','nether_quartz_ore'->'deac3afba67a5ba16a98313742a82b2bd84d91ac32a02ea87f15b146c790e48b','lapis_block'->'80f972ae5d78f5e305dfbed56c65b4a3fbade6b3a7790c7ea50f2443626aab78','gold_block'->'fdcf5f78bf1d9ade126b2c7f6b4980dcfe89b4e65cd532fc65ad8b7572530c5b','iron_block'->'faab7d608bfddac5d8e2caf37049f62f843ffa9f076c252c1dc0a44ee46d3206','diamond_block'->'7b15db493325dcce2908be93231664105adcbfa8cc0395796633815ea156fefa','emerald_block'->'aab8fa76555515e2dcbe38336c691841a9c23d99c88f65d96f4447775bcc16eb','coal_block'->'7682484ce6c0a81215031f96a7149bede98c9d215d16ec8df01dadff3d095db5','redstone_block'->'e4b250c3046fdcaabeb2fc28256a0f213a9cc6048d7fc740058df33832bcf16a','dispenser'->'49330dde4f9424b50f36d62c843efeacbccf5da3b9ce04abf14e175a9c4fdffa','dropper'->'f3f3e03f04e6fdfc00d91ec89422e9483caa67fc020fcacc910342dbe9cc80bb','observer'->'ba9c93a2a3644569084107b7b4d80261449884d9fbc34adbd88c6342045202b3','redstone_lamp'->'f72dc5d2cc34571ada805f9efae9f8c5cf08159f3acc08c0065269421b5cb38c','sticky_piston'->'5a6fee5b61efc63b9b44fc322067fb620dd5ea9a7fb2f85a0aadec8038bc1374','piston'->'b6691de9f56b39e68e87fdaa128c7ce713fd0064c578687b679ee6a8437502ef','tnt'->'f089f79e1f74e370dc3e61baab26ee7935a2a381380a4f2e9de0f5b0a526e0a8','stone'->'cc0930a1bec2487f6a6cb964d7313fb0abd04599d76ff6518d977e83d4613848','polished_granite'->'468151f213760eeac05fb863fe985c2c37388c905c831d805bc805890cf8e9e3','polished_diorite'->'5f2eca9827866ba1055f5663bf205473107c790b724eda21f5d17485f0fceeeb','polished_andesite'->'4c735e2893191dec0f7e1c90417bf8fdf8897e83ad033bab4d34535b7506736f','granite'->'8d838b2d04847001552e4bf2e53733ece70f555c6f4c66f174cc311f3091c329','diorite'->'55c1ef2d446571506b59f929103d1956fc3152fd9d4002026bce5b028c917fef','andesite'->'bc5e16c761e0aaa2dd5289e43f52cc471567cd8f8c8a475a4b290aee8cfa4538','cobblestone'->'33d51f4d06098bb1f4ceff619c6ddca976fc70e9f8871ebee4eef6580cf76b0f','mossy_cobblestone'->'e1b77291e1571fd27deca1c772ef4f917f59a9d59ef7026190f16673507f2ee7','stone_bricks'->'f9a312623e1a8aa75fd73fb25ca6209642bc5a2c0ea30b3be062ec4c8c4323fe','chiseled_stone_bricks'->'50ec9406ac085cd9578deab30cd7467046efc42f58212fb828439ee885f1be13','smooth_stone'->'a56246975be81616a59bce5b4e92bb615d0728cfe41be6cfb5ffdcde5792cb08','oak_planks'->'78192a1add4a11124ad1e9b63fa7d975b5500bff127424e75bf29b29f6afb265','spruce_planks'->'3ea3aaf194482a3975a18d1eece23c0b3ea0f258707fad75c5caa74532088db','birch_planks'->'6cc888773e9d11cc8ab14fc88e0cff94ccb16c89a58fec181e22f0fb194bb9fd','jungle_planks'->'63797f5aae9ee906eb5066e74167ac0b60d8576f0d4b23b412447fd0f290bc07','dark_oak_planks'->'89ca798fe3e4023971b786a64a72bd98a15ff75a7f8117b560103e234de052ac','acacia_planks'->'c016966f4f3dae6524861e6cc563174990701beae2627712718c510f333c6c83','oak_log'->'1a3ca177d2169313f16596439dcd60ebb4813b378b17ffb175354370d510fb0d','spruce_log'->'e73137c47ee189e9fdd81eda48c1126940a0e5d6d9ca4c59dd3b80ccd927adf9','birch_log'->'6c401199b783300cbd9cb1ceacfa5e293fe7575776188cd5aa6ca8bffb8e2f57','jungle_log'->'60029a52f945b3eaac72e9d3d997c1a0b50eff9544416ab2c3c4e4bb3977f5bf','dark_oak_log'->'8b2d536086348fdfc0811f9c6f6ec1c0a7d6050761f5921b2a7a5d3a0059ec00','acacia_log'->'267e9c74ab77c009154a9c737846b525109b8c317a716caedec92702afd0de65','crimson_nylium'->'18f0b8846bb22c2de6849483ae518fafbbe446a735a3e8806e02f1a47fc14d70','warped_nylium'->'be3e1bd2a1bb2dd773bcf1111c146e1004306ab995d51009e67d3a5c216f22e1','crimson_stem'->'74f9a4d8a04544cb2297be160340aee9d2151944f88a4543f7dc8be9a7b07d56','warped_stem'->'a22817ee52b75de8103f5af27a75bbcff87a453ece59350fb4196faabfb6b2bc','crimson_planks'->'f9aab2e175a7e77e3655f7692d1c6517316ba3783b5d5fc59b34954ff6ba6653','warped_planks'->'9d3c3cebe06e0ddd9cc4a90ec45cacdec3d1855dafc9b85a5234270f06ff7643','netherite_block'->'3011cd2cf1e32ae131d8e542403bea03c482fa395a727a37076904762840292d','ancient_debris'->'97114f5d390188df0477cdf9aecf5bc81416ce5e5c59cfcac581a43f3902c1e','nether_gold_ore'->'213279a19b65829ac74598a7647982549f7a1e10b354f395e23b0e8c30df28ee','blackstone'->'59074575d07014e92897210f52795ba8ac4a55ab59ef348e11e8da03012d6747','polished_blackstone'->'1bbcaa4117e350452593d2092713385c0a413cb62bb9c432a979dba4eb2d3bc2','polished_blackstone_bricks'->'797750a284ff4038cb035f1d6db0493ff539c8a93ae3cda6186df0be6162c304','chiseled_polished_blackstone'->'bbe82f0fed69578d25f153ff15aa0f4dd57ad69db6dcd77aa8dfafe056b6dcb6','gilded_blackstone'->'bbbed2fc6321ec4108316f1862cbf2cd81f389f2587b1f53602513b7a7ca54a','shroomlight'->'e46997fd93b5ebe71fae01d3f3f9760c22387af60d7ed5dbd15bb6ce404c809c','chiseled_nether_bricks'->'53180cb082d910c6208d43ac619fcb0b654ab6f70fd81c35991fca21aab908c1','basalt'->'71049bfba4f7ea93051ca1095a123e23fadb4abb2bd57bef8b5378a6696b8c94','polished_basalt'->'eed56105d0678ae40b66c443117e80a81ce2c82553366adc8d4476fdc5f181c9','warped_wart_block'->'3e7e1e0ad1ff8be3c17d61e16d94b6b2bce78022d88de2aa6c669bba5f5ec047','crying_obsidian'->'bdf82bdc8a4f7d026b0c4f17d06b8bf5dc526a254389866291e7b6b44f90b0e','target'->'d20e17ebcb1b183786dde25045b832b238f75a374aace0c5a4f69e9425ed89e7','slime_block'->'63bfc4f01a82060e8087a314a9fcb5ffc1eac9ac1d304da3c8b0d66ad715c5b0','honey_block'->'58da865aa83b6004380c1eda8cce3dd12eda36d71fd8d5fec9b18f0159ddcc76','sponge'->'ecd1cf1fb55d5055832455ade8fa7b0a68f445342399a27df1519e66f2a74ab0','white_wool'->'831a8701493c04627b1f0c499f4aa70e228672982e14f135ccf2e8f63d12a61c','end_stone'->'db6b6b32e9fe8f7cb790d42edf3df69d471dc6b9b616b1570ec8bc1bffa1432c','red_sandstone'->'bafe7b688fe4558c243ee95e07c2eec6060612971a39542fcf77fe32a2fdea8c','bone_block'->'cf4b9ed1b34fc4732f622bedec0c64ce2f2a790d7f63d592e557db602ff69b0b','barrel'->'decd22c4bb613bd7f69cb0231a130bb1035b6b0d46d672c07e8e2a035fe02e70','loom'->'ffc01448d6477e1f7a3d207f2350a066dfa95090d340d451553ce05e700bb731','smoker'->'e831ce8227bbc7b75c3689466cec4daf5da2d9c53bb83d117a9ba890daeaf406','blast_furnace'->'60f4d3601b24d6b371bb97a3f42779840a129487a304dab623469dca0287caf5','fletching_table'->'10b78e0961236c8da77d28fa9a1c6f0398ebb28ebfd7a1783eb2b6c8c4160346','cartography_table'->'9cb97c68236c6352362f3eb9194ab542f18720a34e8d09daa168a05ef650130e','smithing_table'->'ae279f05db836021859590a5b5f4349a60adf02bc1d309da840d9ebbef8f0e2b','bee_nest'->'d6f20006eeb4572efe19c3f16722690f735da9fea4cfc941eb4c3ed5743f1906','beehive'->'8ce0d3ca5c1136724b78787e0a547753dc8e9969651e7c6ee3dfe42b78fb18cf','honeycomb_block'->'83efcf2314aff7ff5d6dd9ac54d4277dd6098706f95f1f3480f4ec162d87057d','lodestone'->'c13951fd87c68cf8ca47d18daeca5ad3ad804b25176f2b4ef48bfc9686ab8064','respawn_anchor'->'1acd473496426413eebb6523dee4a2fc17c192aa301d2340672caba249c3e4f4'};
global_tools = ['_axe','_pickaxe','_hoe','_shovel'];
global_luck = 1/(1-4/7);

_add_random_trades(villager) -> (
    modify(villager, 'tag', 'gb.moretrades');

    // SPORE_BLOSSOMS -> 10%
    if(rand(1/(1-0.1)),
        trade = nbt(str('{maxUses:8,xp:50,rewardExp:1b,buy:{id:"minecraft:emerald",Count:%db},sell:{id:"minecraft:spore_blossom",Count:1b}}',floor(rand(4))+1));
        _add_trade(villager, trade);
    );

    // SKULLS
    trade_ids = {};
    trade_keys = keys(global_skull_trades);
    loop(5, trade_ids += trade_keys:floor(rand(length(trade_keys))));
    for(keys(trade_ids),
        trade = _new_trade([_,1],['emerald',1],['player_head',8]);
        value = _encode_base64(str('{"textures":{"SKIN":{"url":"http://textures.minecraft.net/texture/%s"}}}',global_skull_trades:_));
        trade:'sell.tag' = nbt(str('{SkullOwner:{Id:[I;%d,%d,%d,%d],Properties:{textures:[{Value:"%s"}]}}}',
            rand(2147483647),
            rand(2147483647),
            rand(2147483647),
            rand(2147483647),
            value
        ));
        _add_trade(villager, trade)
    );

    // SILKY -> 14%
    if(system_info('world_carpet_rules'):'silkyBlockstates' && rand(1/(1-0.14)),
        item = if(rand(global_luck),'iron','diamond') + global_tools:rand(4);
        trade = _new_trade([item,1],['emerald',floor(rand(16)+16)],[item,1]);
        trade:'sell.tag' = nbt('{display:{Lore:[\'{"text":"Silky Blockstate","color":"red","italic":false}\']},Enchantments:[{id:"redcraft:silkyblockstate",lvl:1s}]}');
        _add_trade(villager, trade)
    );

    // TREECAPITATOR -> 10.5%
    if(system_info('world_carpet_rules'):'treecapitator' && rand(1/(1-0.105)),
        item = if(rand(global_luck), 'iron_axe', 'diamond_axe');
        trade = _new_trade([item,1], ['emerald', floor(rand(8) + 8)], [item,1]);
        trade:'sell.tag' = nbt('{display:{Lore:[\'{"text":"TreeCapitator","color":"red","italic":false}\']},Enchantments:[{id:"redcraft:treecapitator",lvl:1s}]}');
        _add_trade(villager, trade)
    )
);

entity_load_handler('wandering_trader',
    _(e, new) -> if(new, _add_random_trades(e));
);
