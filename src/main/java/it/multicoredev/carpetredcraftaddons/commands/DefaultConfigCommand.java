package it.multicoredev.carpetredcraftaddons.commands;

import carpet.CarpetServer;
import carpet.script.bundled.BundledModule;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.WorldSavePath;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

import static it.multicoredev.carpetredcraftaddons.CarpetRedCraftExtension.DATAPACK_RULES_RELOAD;
import static it.multicoredev.carpetredcraftaddons.CarpetRedCraftExtension.MOD_ID;
import static it.multicoredev.carpetredcraftaddons.util.DatapackUtil.reload;
import static net.minecraft.server.command.CommandManager.literal;

;

public class DefaultConfigCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralArgumentBuilder<ServerCommandSource> command = literal("load-redcraft-config")
                .requires((player) -> player.hasPermissionLevel(3))
                .executes(c ->
                {
                    try {
                        String world = c.getSource().getServer().getSavePath(WorldSavePath.ROOT).toString();
                        Files.copy(
                                Module.class.getClassLoader().getResourceAsStream("assets/" + MOD_ID + "/default.conf"),
                                new File(world, "carpet.conf").toPath(),
                                StandardCopyOption.REPLACE_EXISTING
                        );
                    } catch (IOException ignored) {
                    }
                    DATAPACK_RULES_RELOAD = false;
                    CarpetServer.settingsManager.attachServer(CarpetServer.minecraft_server);
                    DATAPACK_RULES_RELOAD = true;
                    reload();

                    // GAMERULES
                    dispatcher.execute("gamerule playersSleepingPercentage 0", c.getSource());
                    dispatcher.execute("gamerule doFireTick false", c.getSource());
                    dispatcher.execute("gamerule commandBlockOutput false", c.getSource());
                    dispatcher.execute("gamerule spectatorsGenerateChunks false", c.getSource());
                    // HEALTH
                    dispatcher.execute("scoreboard objectives add health health", c.getSource());
                    dispatcher.execute("scoreboard objectives setdisplay list health", c.getSource());
                    // REDCRAFT2 RULE
                    try {
                        ServerWorld world = CarpetServer.minecraft_server.getWorld(RegistryKey.of(Registry.WORLD_KEY, new Identifier("redcraft2", "overworld")));
                        if(world != null) dispatcher.execute("carpet setDefault redcraft true", c.getSource());
                    } catch (NullPointerException ignored){ }

                    return 1;
                });

        dispatcher.register(command);
    }
}
