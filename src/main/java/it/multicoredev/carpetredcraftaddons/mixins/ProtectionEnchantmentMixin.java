package it.multicoredev.carpetredcraftaddons.mixins;

import it.multicoredev.carpetredcraftaddons.CarpetRedCraftSettings;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.ProtectionEnchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ProtectionEnchantment.class)
public class ProtectionEnchantmentMixin {
    @Shadow
    private ProtectionEnchantment.Type protectionType;

    @Inject(method = "canAccept(Lnet/minecraft/enchantment/Enchantment;)Z",
            at = @At(value = "RETURN", ordinal = 1),
            cancellable = true
    )
    void allwaysCanAccept(Enchantment other, CallbackInfoReturnable<Boolean> cir){
        ProtectionEnchantment protectionEnchantment = (ProtectionEnchantment)other;
        cir.setReturnValue(
                CarpetRedCraftSettings.protectionStacking ||
                this.protectionType == ProtectionEnchantment.Type.FALL ||
                protectionEnchantment.protectionType == ProtectionEnchantment.Type.FALL
        );
    }
}
