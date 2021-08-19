package it.multicoredev.carpetredcraftaddons.mixins;

import it.multicoredev.carpetredcraftaddons.CarpetRedCraftSettings;
import net.minecraft.client.gui.screen.ingame.AnvilScreen;
import net.minecraft.screen.AnvilScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(AnvilScreenHandler.class)
public class AnvilScreenHandlerMinin {
    @ModifyConstant(method = "updateResult()V",
                    constant = @Constant(intValue = 40)
    )
    private int tooExpansiveCost(int defaultValue) {
        return CarpetRedCraftSettings.anvilRepairCostLimit;
    }

    @ModifyConstant(method = "updateResult()V",
                    constant = @Constant(intValue = 39)
    )
    private int maxExpansiveCost(int defaultValue) {
        return Math.max(CarpetRedCraftSettings.anvilRepairCostLimit - 1, 0);
    }

}
