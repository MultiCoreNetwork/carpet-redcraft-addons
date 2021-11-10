package carpetaddons.mixins;

import carpetaddons.utils.CarefulBreakUtils;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(priority = 999, value = Block.class)
public abstract class BlockMixin_carefulBreak {

    @Inject(method = "afterBreak", at = @At(value = "INVOKE", shift=At.Shift.BEFORE, target = "Lnet/minecraft/block/Block;dropStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;)V"), cancellable = true)
    private void preDropStacks(World world, PlayerEntity player, BlockPos pos, BlockState state, BlockEntity blockEntity, ItemStack stack, CallbackInfo ci) {
        if (CarefulBreakUtils.validCarefulBreak(player)) {
            CarefulBreakUtils.placeItemInInventory(state, world, pos, blockEntity, player, stack);
            ci.cancel();
        }
    }

    @Inject(method = "onBreak", at = @At("HEAD"))
    private void onBreak1(World world, BlockPos pos, BlockState state, PlayerEntity player, CallbackInfo ci) {
        if (CarefulBreakUtils.validCarefulBreak(player)) {
            if (state.getBlock() == Blocks.PISTON_HEAD) {
                Direction direction = state.get(FacingBlock.FACING).getOpposite();
                pos = pos.offset(direction);
                BlockState blockState = world.getBlockState(pos);
                Block block = world.getBlockState(pos).getBlock();
                if (block == Blocks.PISTON || block == Blocks.STICKY_PISTON && blockState.get(PistonBlock.EXTENDED)) {
                    CarefulBreakUtils.placeItemInInventory(blockState, world, pos, null, player, player.getMainHandStack());
                    world.removeBlock(pos, false);
                }
            }
        }
    }
}
