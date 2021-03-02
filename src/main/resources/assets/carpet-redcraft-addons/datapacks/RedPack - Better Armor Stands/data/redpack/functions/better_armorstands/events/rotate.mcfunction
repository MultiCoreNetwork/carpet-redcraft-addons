# Handle the event for the Rotate event

    # Add the tag to the armor stand and merge the data
        tag @s add gb.Rotate
        data merge entity @s {NoGravity:1b}
        kill @e[type=minecraft:item,tag=!global.ignore,tag=!global.ignore.kill,nbt=!{Age:6000s},nbt={Item:{id:"minecraft:flower_pot",Count:1b}},distance=..1,limit=1]
