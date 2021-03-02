# Handle the event for the StoneBig event

    # Add the tag to the armor stand and merge the data
        tag @s add gb.StoneBig
        function redpack:better_armorstands/events/big
        kill @e[type=minecraft:item,tag=!global.ignore,tag=!global.ignore.kill,nbt=!{Age:6000s},nbt={Item:{id:"minecraft:stone_pressure_plate",Count:1b}},distance=..1,limit=1]
