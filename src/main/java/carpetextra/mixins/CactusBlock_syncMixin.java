package carpetextra.mixins;

import it.multicoredev.carpetredcraftaddons.CarpetRedCraftSettings;
import net.minecraft.block.CactusBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(CactusBlock.class)
public abstract class CactusBlock_syncMixin
{
    @ModifyConstant(method = "randomTick", require = 2, constant = @Constant(intValue = 4))
    private int onOnScheduledTick(int original)
    {
        if (CarpetRedCraftSettings.blockStateSyncing)
            return 6;
        else
            return original;
    }
}