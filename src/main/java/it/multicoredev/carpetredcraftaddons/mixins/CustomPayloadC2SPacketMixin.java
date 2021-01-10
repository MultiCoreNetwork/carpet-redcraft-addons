package it.multicoredev.carpetredcraftaddons.mixins;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.c2s.play.CustomPayloadC2SPacket;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.io.IOException;

@Mixin(CustomPayloadC2SPacket.class)
public class CustomPayloadC2SPacketMixin {
    @Shadow
    private Identifier channel;
    @Shadow
    private PacketByteBuf data;

    /**
     * @author BisUmTo
     * @reason Prevent legacy:fml|hs error
     */
    @Overwrite
    public void read(PacketByteBuf buf) throws IOException {
        this.channel = new Identifier(buf.readString(32767).toLowerCase().replaceAll("[^a-z0-9_/.-]",""));
        int i = buf.readableBytes();
        if (i >= 0 && i <= 32767) {
            this.data = new PacketByteBuf(buf.readBytes(i));
        } else {
            throw new IOException("Payload may not be larger than 32767 bytes");
        }
    }
}
