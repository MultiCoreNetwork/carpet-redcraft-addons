package it.multicoredev.carpetredcraftaddons.mixins;

import carpet.CarpetServer;
import carpet.commands.ScriptCommand;
import carpet.script.CarpetScriptHost;
import carpet.script.CarpetScriptServer;
import it.multicoredev.carpetredcraftaddons.CarpetRedCraftSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.math.BlockPointer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.ArrayList;

@Mixin(targets = "net/minecraft/block/dispenser/DispenserBehavior$11")
public class DispenserBehaviorBoneMealMixin extends FallibleItemDispenserBehavior {

    @SuppressWarnings("UnresolvedMixinReference")
    @Inject(
            method = "dispenseSilently(Lnet/minecraft/util/math/BlockPointer;Lnet/minecraft/item/ItemStack;)Lnet/minecraft/item/ItemStack;",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/item/BoneMealItem;useOnFertilizable(Lnet/minecraft/item/ItemStack;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)Z"),
            cancellable = true,
            locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void useOnSpore(BlockPointer pointer, ItemStack stack, CallbackInfoReturnable<ItemStack> cir, World world, BlockPos blockPos) {
        Block block = world.getBlockState(blockPos).getBlock();
        if (CarpetRedCraftSettings.renewableSporeblossom && block.equals(Blocks.SPORE_BLOSSOM)) {
            this.setSuccess(true);
            MinecraftServer server = CarpetServer.minecraft_server;
            try {
                CarpetServer.scriptServer.getAppHostByName("renewable_spore_blossom").callLegacy(
                        server.getCommandSource(), "_duplicate_spore", null,
                        String.format("%d %d %d", blockPos.getX(), blockPos.getY(), blockPos.getZ())
                );
                stack.decrement(1);
            } catch (Exception ignored){}
            cir.setReturnValue(stack);
        }
    }

}
