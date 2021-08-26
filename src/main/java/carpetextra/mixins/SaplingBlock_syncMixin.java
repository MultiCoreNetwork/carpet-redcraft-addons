package carpetextra.mixins;

import it.multicoredev.carpetredcraftaddons.CarpetRedCraftSettings;
import net.minecraft.block.SaplingBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(priority = 999, value = SaplingBlock.class)
public abstract class SaplingBlock_syncMixin
{
    @ModifyConstant(method = "generate", constant = @Constant(intValue = 4))
    private int onGenerate(int original)
    {
        if (CarpetRedCraftSettings.blockStateSyncing)
            return 6;
        else
            return original;
    }
}