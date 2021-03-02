# Handle the event for the SoulCampfireSmokeParticles event

    # Add the tag to the armor stand and merge the data
        tag @s add gb.SoulCampfireSmokeParticles
        function redpack:better_armorstands/events/smokeparticles
        kill @e[type=minecraft:item,tag=!global.ignore,tag=!global.ignore.kill,nbt=!{Age:6000s},nbt={Item:{id:"minecraft:soul_campfire",Count:1b}},distance=..1,limit=1]
