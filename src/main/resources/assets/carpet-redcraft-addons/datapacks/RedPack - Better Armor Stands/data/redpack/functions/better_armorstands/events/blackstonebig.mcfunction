# Handle the event for the BlacktoneBig event

    # Add the tag to the armor stand and merge the data
        tag @s add gb.BlackstoneBig
        function redpack:better_armorstands/events/big
        kill @e[type=minecraft:item,tag=!global.ignore,tag=!global.ignore.kill,nbt=!{Age:6000s},nbt={Item:{id:"minecraft:polished_blackstone_pressure_plate",Count:1b}},distance=..1,limit=1]
