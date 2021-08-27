package it.multicoredev.carpetredcraftaddons.mixins;

import it.multicoredev.carpetredcraftaddons.CarpetRedCraftSettings;
import net.minecraft.entity.projectile.FireballEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(FireballEntity.class)
public class FireballEntityMixin {
    @ModifyVariable(method = "onCollision", ordinal = 0, at = @At("STORE"))
    private boolean ghastNoGrief(boolean previous){
        return previous && !CarpetRedCraftSettings.ghastNoGrief;
    }
}
