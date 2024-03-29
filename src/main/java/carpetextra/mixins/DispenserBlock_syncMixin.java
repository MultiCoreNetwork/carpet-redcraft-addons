package carpetextra.mixins;

import it.multicoredev.carpetredcraftaddons.CarpetRedCraftSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.DispenserBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(priority = 999, value = DispenserBlock.class)
public abstract class DispenserBlock_syncMixin
{
    @Redirect(method = "neighborUpdate", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/world/World;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z"
    ))
    private boolean setWithFlags(World world, BlockPos pos, BlockState state, int flags)
    {
        if (CarpetRedCraftSettings.blockStateSyncing)
            return world.setBlockState(pos, state, flags | 2);
        return world.setBlockState(pos, state, flags);
    }
}