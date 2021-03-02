# Handle the event for the BlacktoneSmall event

    # Add the tag to the armor stand and merge the data
        tag @s add gb.BlackstoneSmall
        function redpack:better_armorstands/events/small
        kill @e[type=minecraft:item,tag=!global.ignore,tag=!global.ignore.kill,nbt=!{Age:6000s},nbt={Item:{id:"minecraft:polished_blackstone_button",Count:1b}},distance=..1,limit=1]
