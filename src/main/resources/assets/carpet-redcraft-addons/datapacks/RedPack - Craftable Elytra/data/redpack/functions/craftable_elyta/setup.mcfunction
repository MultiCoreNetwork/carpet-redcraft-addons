# Creating all the scoreboards that are needed by the datapack

    # Adding the pack name and version to the redpack storage
        data remove storage redpack:config versions[{id:128336}]
        data modify storage redpack:config versions append value {"id":128336,"name":"RedPack - Craftable Elytra","version":"0.1.0"}

    # Initializing the scoreboards
        scoreboard objectives add gb.configs dummy

    # Getting the version of the player and sending an error message if it is outdated
        execute store result score $gb.game_version gb.configs run data get entity @a[limit=1] DataVersion
        execute if score $gb.game_version gb.configs matches ..2576 run tellraw @a[tag=!global.ignore.gui] {"text":"[Craftable Elytra (RedPack)]> This datapack is not compatible with all the versions before 1.16.2!\n","color":"red"}

    # Checking if the server uses a different jar from the vanilla one
        execute if score $gb.check_jar gb.configs matches 0 store success score $gb.check_jar gb.configs run data get entity @a[limit=1] "Spigot.ticksLived"
        execute if score $gb.check_jar gb.configs matches 0 store success score $gb.check_jar gb.configs run data get entity @a[limit=1] "Bukkit.updateLevel"
        execute if score $gb.check_jar gb.configs matches 0 store success score $gb.check_jar gb.configs run data get entity @a[limit=1] "Paper.SpawnReason"
        execute if score $gb.check_jar gb.configs matches 1 run tellraw @a[tag=!global.ignore.gui] {"text":"Compatibility in non-vanilla servers is not guaranteed!","color":"red"}
