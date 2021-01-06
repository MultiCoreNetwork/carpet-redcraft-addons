package it.multicoredev.carpetredcraftaddons.commands;

import carpet.CarpetServer;
import carpet.script.bundled.BundledModule;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.WorldSavePath;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import static it.multicoredev.carpetredcraftaddons.CarpetRedCraftExtension.MOD_ID;
import static net.minecraft.server.command.CommandManager.literal;

;

public class DefaultConfigCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        LiteralArgumentBuilder<ServerCommandSource> command = literal("load-default-config")
                .requires((player) -> player.hasPermissionLevel(3))
                .executes(c ->
                {
                    try {
                        String world = c.getSource().getMinecraftServer().getSavePath(WorldSavePath.ROOT).toString();
                        Files.copy(BundledModule.class.getClassLoader().getResourceAsStream("assets/" + MOD_ID + "/default.conf"), new File(world, "carpet.conf").toPath());
                    } catch (IOException ignored) {
                    }
                    CarpetServer.settingsManager.attachServer(CarpetServer.minecraft_server);

                    // GAMERULES
                    dispatcher.execute("gamerule playersSleepingPercentage 0", c.getSource());
                    dispatcher.execute("gamerule doFireTick false", c.getSource());
                    dispatcher.execute("gamerule commandBlockOutput false", c.getSource());
                    dispatcher.execute("gamerule spectatorsGenerateChunks false", c.getSource());

                    return 1;
                });

        dispatcher.register(command);
    }
}
