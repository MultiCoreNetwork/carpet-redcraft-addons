# Handle the event for the ShowArms event

    # Add the tag to the armor stand and merge the data
        tag @s add gb.ShowArms
        data merge entity @s {ShowArms:1b}
        kill @e[type=minecraft:item,tag=!global.ignore,tag=!global.ignore.kill,nbt=!{Age:6000s},nbt={Item:{id:"minecraft:stick",Count:2b}},distance=..1,limit=1]
