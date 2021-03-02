# Handle the event for the Invisible event

    # Add the tag to the armor stand and merge the data
        tag @s add gb.Invisible
        data merge entity @s {Invisible:1b,Marker:1b}
        kill @e[type=minecraft:item,tag=!global.ignore,tag=!global.ignore.kill,nbt=!{Age:6000s},nbt={Item:{id:"minecraft:phantom_membrane",Count:1b}},distance=..1,limit=1]
