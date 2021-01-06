package it.multicoredev.carpetredcraftaddons.commands;

import com.mojang.authlib.GameProfile;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.builder.LiteralArgumentBuilder;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import net.minecraft.command.CommandSource;
import net.minecraft.command.argument.GameProfileArgumentType;
import net.minecraft.server.OperatorEntry;
import net.minecraft.server.PlayerManager;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.TranslatableText;

import java.util.Collection;

public class OpCommand {
    private static final SimpleCommandExceptionType ALREADY_OPPED_EXCEPTION = new SimpleCommandExceptionType(new TranslatableText("commands.op.failed"));

    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register((LiteralArgumentBuilder) ((LiteralArgumentBuilder)
                CommandManager.literal("op")
                        .requires((serverCommandSource) -> serverCommandSource.hasPermissionLevel(3)))
                .then(CommandManager.argument("targets", GameProfileArgumentType.gameProfile())
                        .suggests((commandContext, suggestionsBuilder) -> {
                            PlayerManager playerManager = commandContext.getSource().getMinecraftServer().getPlayerManager();
                            return CommandSource.suggestMatching(playerManager.getPlayerList().stream().filter((serverPlayerEntity) -> {
                                return !playerManager.isOperator(serverPlayerEntity.getGameProfile());
                            }).map((serverPlayerEntity) -> {
                                return serverPlayerEntity.getGameProfile().getName();
                            }), suggestionsBuilder);
                        })
                        .executes((commandContext) -> op(commandContext.getSource(), GameProfileArgumentType.getProfileArgument(commandContext, "targets")))
                        .then(CommandManager.argument("level", IntegerArgumentType.integer(1, 4))
                                .suggests((context, builder) -> CommandSource.suggestMatching(new String[]{"1", "2", "3", "4"}, builder))
                                .executes((commandContext) -> op(commandContext.getSource(), GameProfileArgumentType.getProfileArgument(commandContext, "targets"), IntegerArgumentType.getInteger(commandContext, "level")))
                        )
                )
        );
    }

    private static int op(ServerCommandSource source, Collection<GameProfile> targets) throws CommandSyntaxException {
        PlayerManager playerManager = source.getMinecraftServer().getPlayerManager();
        int i = 0;

        for (GameProfile gameProfile : targets) {
            if (!playerManager.isOperator(gameProfile)) {
                playerManager.addToOperators(gameProfile);
                ++i;
                source.sendFeedback(new TranslatableText("commands.op.success", targets.iterator().next().getName()), true);
            }
        }

        if (i == 0) {
            throw ALREADY_OPPED_EXCEPTION.create();
        } else {
            return i;
        }
    }

    private static int op(ServerCommandSource source, Collection<GameProfile> targets, int level) throws CommandSyntaxException {
        PlayerManager playerManager = source.getMinecraftServer().getPlayerManager();
        int i = 0;
        for (GameProfile gameProfile : targets) {
            if (!playerManager.isOperator(gameProfile)) {
                playerManager.getOpList().add(new OperatorEntry(gameProfile, level, playerManager.getOpList().isOp(gameProfile)));
                ServerPlayerEntity serverPlayerEntity = playerManager.getPlayer(gameProfile.getId());
                if (serverPlayerEntity != null) {
                    playerManager.sendCommandTree(serverPlayerEntity);
                }
                ++i;
                source.sendFeedback(new TranslatableText("commands.op.success", targets.iterator().next().getName()), true);
            }
        }

        if (i == 0) {
            throw ALREADY_OPPED_EXCEPTION.create();
        } else {
            return i;
        }
    }
}
