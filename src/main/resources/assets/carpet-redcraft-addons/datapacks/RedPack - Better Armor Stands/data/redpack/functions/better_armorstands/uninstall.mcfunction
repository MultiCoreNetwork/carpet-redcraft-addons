# Removing all the scoreboards and disabling the datapack

    # Removing the scoreboards
        scoreboard objectives remove gb.configs

    # Removing the storage
        data remove storage redpack:config versions[{id:128324}]
        data remove storage redpack:config better_armorstands

    # Disabling the datapack
        datapack disable "file/RedPack - Better Armor Stands"
