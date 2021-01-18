package it.multicoredev.carpetredcraftaddons.mixins;

import it.multicoredev.carpetredcraftaddons.CarpetRedCraftSettings;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.UUID;

@Mixin(ServerCommandSource.class)
public abstract class ServerCommandSourceMixin {
    @Redirect(method = "sendToOps(Lnet/minecraft/text/Text;)V", at = @At(value = "INVOKE",
        target = "Lnet/minecraft/server/network/ServerPlayerEntity;sendSystemMessage(Lnet/minecraft/text/Text;Ljava/util/UUID;)V")
    )
    private static void sendSystemMessageMixin(ServerPlayerEntity serverPlayerEntity, Text message, UUID senderUuid){
        if(!CarpetRedCraftSettings.disableOPsCommandFeedback || serverPlayerEntity.hasPermissionLevel(2))
            serverPlayerEntity.sendSystemMessage(message,senderUuid);
    }
}
