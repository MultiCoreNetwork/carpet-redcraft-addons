# Handle the event for the FlameParticles event

    # Add the tag to the armor stand and merge the data
        tag @s add gb.FlameParticles
        kill @e[type=minecraft:item,tag=!global.ignore,tag=!global.ignore.kill,nbt=!{Age:6000s},nbt={Item:{id:"minecraft:blaze_powder",Count:1b}},distance=..1,limit=1]
