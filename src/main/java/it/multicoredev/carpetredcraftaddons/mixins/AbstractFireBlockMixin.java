package it.multicoredev.carpetredcraftaddons.mixins;

import net.minecraft.block.AbstractFireBlock;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(AbstractFireBlock.class)
public class AbstractFireBlockMixin {
    /**
     * @author BisUmTo
     * @reason RedCraft2 World Compability
     */
    @Overwrite
    private static boolean method_30366(World world) {
        return world.getRegistryKey() == World.OVERWORLD || world.getRegistryKey() == World.NETHER || world.getRegistryKey() == RegistryKey.of(Registry.DIMENSION, new Identifier("redcraft2", "overworld"));
    }
}
