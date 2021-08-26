package carpetextra.mixins;


import it.multicoredev.carpetredcraftaddons.CarpetRedCraftSettings;
import net.minecraft.block.HopperBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(priority = 999, value = HopperBlock.class)
public abstract class HopperBlock_syncMixin
{
    @ModifyConstant(method = "updateEnabled", constant = @Constant(intValue = 4))
    private int onUpdateEnabled(int original)
    {
        if (CarpetRedCraftSettings.blockStateSyncing)
            return 6;
        else
            return original;
    }
}