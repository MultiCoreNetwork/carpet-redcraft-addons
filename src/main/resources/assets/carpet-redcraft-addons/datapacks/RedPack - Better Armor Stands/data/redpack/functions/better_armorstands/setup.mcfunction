# Creating all the scoreboards that are need by the datapack

    # Adding the pack name and version to the redpack storage
        data remove storage redpack:config versions[{id:128324}]
        data remove storage redpack:config better_armorstands
        data modify storage redpack:config versions append value {"id":128324,"name":"RedPack - Better Armor Stands","version":"1.0.1"}
    
    # Initializing the scoreboards
        scoreboard objectives add gb.configs dummy
            
    # Calling the config function on loading
        function redpack:better_armorstands/config

    # Storing the config inside a scoreboard
        execute store result score $gb.better_armorstands.ShowArms gb.configs run data get storage redpack:config better_armorstands.ShowArms
        execute store result score $gb.better_armorstands.NoBasePlate gb.configs run data get storage redpack:config better_armorstands.NoBasePlate
        execute store result score $gb.better_armorstands.NoGravity gb.configs run data get storage redpack:config better_armorstands.NoGravity
        execute store result score $gb.better_armorstands.Rotate gb.configs run data get storage redpack:config better_armorstands.Rotate
        execute store result score $gb.better_armorstands.Invisible gb.configs run data get storage redpack:config better_armorstands.Invisible
        execute store result score $gb.better_armorstands.Visible gb.configs run data get storage redpack:config better_armorstands.Visible
        execute store result score $gb.better_armorstands.SmokeParticles gb.configs run data get storage redpack:config better_armorstands.SmokeParticles
        execute store result score $gb.better_armorstands.Small gb.configs run data get storage redpack:config better_armorstands.Small
        execute store result score $gb.better_armorstands.Big gb.configs run data get storage redpack:config better_armorstands.Big
        execute store result score $gb.better_armorstands.FlameParticles gb.configs run data get storage redpack:config better_armorstands.FlameParticles

    # Clearing the schedule queue
        schedule clear redpack:better_armorstands/events/scheduler

    # Scheduling the scheduler
        schedule function redpack:better_armorstands/events/scheduler 4t

    # Getting the version of the player and sending an error message if it is outdated
        execute store result score $gb.game_version gb.configs run data get entity @a[limit=1] DataVersion
        execute if score $gb.game_version gb.configs matches ..2576 run tellraw @a[tag=!global.ignore.gui] {"text":"[Better ArmorStands (RedPack)]> This datapack is not compatible with all the versions before 1.16.2!\n","color":"red"}

    # Checking if the server uses a different jar from the vanilla one
        execute if score $gb.check_jar gb.configs matches 0 store success score $gb.check_jar gb.configs run data get entity @a[limit=1] "Spigot.ticksLived"
        execute if score $gb.check_jar gb.configs matches 0 store success score $gb.check_jar gb.configs run data get entity @a[limit=1] "Bukkit.updateLevel"
        execute if score $gb.check_jar gb.configs matches 0 store success score $gb.check_jar gb.configs run data get entity @a[limit=1] "Paper.SpawnReason"
        execute if score $gb.check_jar gb.configs matches 1 run tellraw @a[tag=!global.ignore.gui] {"text":"Compatibility in non-vanilla servers is not guaranteed!","color":"red"}
