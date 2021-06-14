package it.multicoredev.carpetredcraftaddons.mixins;

import carpet.network.CarpetClient;
import it.multicoredev.carpetredcraftaddons.CarpetRedCraftSettings;
import net.minecraft.entity.Entity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(Entity.class)
public class EntityMixin {
    @Shadow
    private Vec3d pos;
    @Shadow
    public World world;

    @Redirect(method = "tickNetherPortal", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/MinecraftServer;getWorld(Lnet/minecraft/util/registry/RegistryKey;)Lnet/minecraft/server/world/ServerWorld;"))
    private ServerWorld getCorrectWorld(MinecraftServer minecraftServer, RegistryKey<World> registryKey){
        if(!CarpetRedCraftSettings.redcraft) return minecraftServer.getWorld(registryKey);
        if (this.world.getRegistryKey() == World.NETHER && (Math.abs(pos.x) < 1000 && Math.abs(pos.z) < 1000))
            registryKey = RegistryKey.of(Registry.WORLD_KEY, new Identifier("redcraft2", "overworld"));
        return minecraftServer.getWorld(registryKey);
    }
}
