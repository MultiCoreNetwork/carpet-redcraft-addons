
package it.multicoredev.carpetredcraftaddons.mixins;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.c2s.play.CustomPayloadC2SPacket;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(CustomPayloadC2SPacket.class)
public class CustomPayloadC2SPacketMixin {

    /**
     * @author BisUmTo
     * @reason Prevent legacy:fml|hs error
     */
    @Redirect(method = "<init>(Lnet/minecraft/network/PacketByteBuf;)V", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/network/PacketByteBuf;readIdentifier()Lnet/minecraft/util/Identifier;")
    )
    public Identifier readIdentifier(PacketByteBuf buf) {
        return new Identifier(buf.readString(32767).toLowerCase().replaceAll("[^a-z0-9_/.-:]",""));
    }
}