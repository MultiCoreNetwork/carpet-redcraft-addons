package it.multicoredev.carpetredcraftaddons.mixins;

import it.multicoredev.carpetredcraftaddons.CarpetRedCraftSettings;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.raid.RaiderEntity;
import net.minecraft.village.raid.Raid;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.Arrays;

@Mixin(Raid.Member.class)
public class RaidMembrerMixin {
    @SuppressWarnings("InvokerTarget")
    @Invoker("<init>")
    private static Raid.Member newVariant(String internalName, int internalId, EntityType<? extends RaiderEntity> type, int[] countInWave) {
        throw new AssertionError();
    }

    @SuppressWarnings("ShadowTarget")
    @Shadow
    private static @Final
    @Mutable
    Raid.Member[] field_16632;

    @SuppressWarnings("UnresolvedMixinReference")
    @Inject(method = "<clinit>", at = @At(value = "FIELD",
            opcode = Opcodes.PUTSTATIC,
            target = "Lnet/minecraft/village/raid/Raid$Member;field_16632:[Lnet/minecraft/village/raid/Raid$Member;",
            shift = At.Shift.AFTER))
    private static void addCustomVariant(CallbackInfo ci) {
        if(CarpetRedCraftSettings.illusionersSpawnInRaids) {
            var variants = new ArrayList<>(Arrays.asList(field_16632));
            Raid.Member last = variants.get(variants.size() - 1);
            var illusioner = newVariant("ILLUSIONER", last.ordinal() + 1, EntityType.ILLUSIONER, new int[]{0, 0, 0, 0, 0, 1, 1, 2});
            variants.add(illusioner);
            field_16632 = variants.toArray(new Raid.Member[0]);
        }
    }
}
