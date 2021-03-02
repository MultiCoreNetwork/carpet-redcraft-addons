# Executes the functions every 20 game ticks starting after 3 game tick since the reload

    # Calling the main function
        execute as @e[type=minecraft:armor_stand,tag=!global.ignore,tag=!global.ignore.gui,tag=!global.ignore.pos] at @s run function redpack:better_armorstands/events/main

    # Scheduling the function 
        schedule function redpack:better_armorstands/events/scheduler 20t
