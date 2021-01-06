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
        dispatcher.register((LiteralArgumentBuilder) ((LiteralArgumentBuilder) CommandManager.literal("publish")
                .requires((serverCommandSource) -> serverCommandSource.hasPermissionLevel(4)))
                .executes((commandContext) -> execute((ServerCommandSource) commandContext.getSource(), NetworkUtils.findLocalPort()))
                .then(CommandManager.argument("port", IntegerArgumentType.integer(0, 65535))
                        .executes((commandContext) -> execute(commandContext.getSource(), IntegerArgumentType.getInteger(commandContext, "port")))
                        .then(CommandManager.argument("allowCheats", BoolArgumentType.bool())
                                .executes((commandContext) -> execute(commandContext.getSource(), IntegerArgumentType.getInteger(commandContext, "port"), BoolArgumentType.getBool(commandContext, "allowCheats")))
                        )
                )
        );
    }

    private static int execute(ServerCommandSource source, int port) throws CommandSyntaxException {
        return execute(source, port, false);
    }

    private static int execute(ServerCommandSource source, int port, boolean allowCheats) throws CommandSyntaxException {
        if (source.getMinecraftServer().isRemote()) {
            throw ALREADY_PUBLISHED_EXCEPTION.create(source.getMinecraftServer().getServerPort());
        } else if (!source.getMinecraftServer().openToLan(null, allowCheats, port)) {
            throw FAILED_EXCEPTION.create();
        } else {
            source.sendFeedback(new TranslatableText("commands.publish.success", port), true);
            return port;
        }
    }
}
