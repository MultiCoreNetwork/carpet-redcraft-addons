__config() -> {'stay_loaded' -> true, 'scope' -> 'global'};

__on_player_interacts_with_entity(player, entity, hand) -> (
    if(player~'sneaking' && (entity~'type' == 'horse' || entity~'type' == 'mule' || entity~'type' == 'donkey' || entity~'type' == 'skeleton_horse' || entity~'type' == 'zombie_horse'),
        print('-'*20);
        print(player, format(str('fb %s\'s Stats:', entity)));
        print(player, format(str('g Health: %.01f', query(entity, 'health')/2), '^ Health range: 15-30'));
        print(player, format(str('g Movement Speed: %.04f', query(entity, 'nbt', 'Attributes[{Name:"minecraft:generic.movement_speed"}].Base')), '^ Movement Speed range: 0.1125 – 0.3375'));
        print(player, format(str('g Jump Strength: %.04f', if(t=query(entity, 'nbt', 'Attributes[{Name:"minecraft:horse.jump_strength"}].Base'),t, 0.5)), '^ Jump Strength range: 0.400 – 1.000'));
        print('-'*20);
    )
)
