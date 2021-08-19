package it.multicoredev.carpetredcraftaddons.mixins;

import it.multicoredev.carpetredcraftaddons.CarpetRedCraftSettings;
import net.minecraft.client.gui.screen.ingame.AnvilScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(AnvilScreen.class)
public class AnvilScreenMinin {
    @ModifyConstant(method = "drawForeground(Lnet/minecraft/client/util/math/MatrixStack;II)V",
                    constant = @Constant(intValue = 40)
    )
    private int tooExpansiveCost(int defaultValue) {
        return CarpetRedCraftSettings.anvilRepairCostLimit;
    }

}
