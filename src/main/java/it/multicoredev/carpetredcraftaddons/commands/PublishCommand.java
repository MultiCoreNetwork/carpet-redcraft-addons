package it.multicoredev.carpetredcraftaddons.commands;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.client.util.NetworkUtils;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.TranslatableText;
import net.minecraft.world.GameMode;

public class PublishCommand {
    private static final SimpleCommandExceptionType FAILED_EXCEPTION = new SimpleCommandExceptionType(new TranslatableText("commands.publish.failed"));
    private static final DynamicCommandExceptionType ALREADY_PUBLISHED_EXCEPTION = new DynamicCommandExceptionType((object) -> new TranslatableText("commands.publish.alreadyPublished", object));

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(
                CommandManager.literal("publish").requires((serverCommandSource) -> serverCommandSource.hasPermissionLevel(4))
                        .executes((commandContext) -> execute(commandContext.getSource(), NetworkUtils.findLocalPort(), false, false))
                        .then(
                                CommandManager.argument("port", IntegerArgumentType.integer(0, 65535))
                                        .executes((commandContext) -> execute(commandContext.getSource(), IntegerArgumentType.getInteger(commandContext, "port"), true, false))
                        ).then(
                        CommandManager.literal("allowCheats")
                                .executes((commandContext) -> execute(commandContext.getSource(), NetworkUtils.findLocalPort(), true, false))
                                .then(
                                        CommandManager.argument("port", IntegerArgumentType.integer(0, 65535))
                                                .executes((commandContext) -> execute(commandContext.getSource(), IntegerArgumentType.getInteger(commandContext, "port"), true, false))
                                )
                ).then(
                        CommandManager.literal("offlineMode")
                                .executes((commandContext) -> execute(commandContext.getSource(), NetworkUtils.findLocalPort(), true, true))
                                .then(
                                        CommandManager.argument("port", IntegerArgumentType.integer(0, 65535))
                                                .executes((commandContext) -> execute(commandContext.getSource(), IntegerArgumentType.getInteger(commandContext, "port"), true, true))
                                ).then(
                                CommandManager.literal("allowCheats")
                                        .executes((commandContext) -> execute(commandContext.getSource(), NetworkUtils.findLocalPort(), true, true))
                                        .then(
                                                CommandManager.argument("port", IntegerArgumentType.integer(0, 65535))
                                                        .executes((commandContext) -> execute(commandContext.getSource(), IntegerArgumentType.getInteger(commandContext, "port"), true, true))
                                        )
                        )
                )
        );
    }

    private static int execute(ServerCommandSource source, int port, boolean allowCheats, boolean forceOfflineMode) throws CommandSyntaxException {
        if (source.getServer().isRemote()) {
            throw ALREADY_PUBLISHED_EXCEPTION.create(source.getServer().getServerPort());
        } else if (!source.getServer().openToLan(source.getServer().getDefaultGameMode(), allowCheats, port)) {
            if (forceOfflineMode) source.getServer().setOnlineMode(false);
            throw FAILED_EXCEPTION.create();
        } else {
            source.sendFeedback(new TranslatableText("commands.publish.success", port), true);
            return port;
        }
    }
}
