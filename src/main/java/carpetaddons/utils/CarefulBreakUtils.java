package carpetaddons.utils;

import carpet.CarpetServer;
import it.multicoredev.carpetredcraftaddons.CarpetRedCraftSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtList;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import static net.minecraft.block.Block.dropStack;
import static net.minecraft.block.Block.getDroppedStacks;

public class CarefulBreakUtils {
    public static void placeItemInInventory(BlockState state, World world, BlockPos pos, BlockEntity blockEntity, Entity entity, ItemStack stack) {
        if (world instanceof ServerWorld) {
            getDroppedStacks(state, (ServerWorld) world, pos, blockEntity, entity, stack).forEach((itemStack) -> {
                Item item = itemStack.getItem();
                int itemAmount = itemStack.getCount();
                if (((PlayerEntity) entity).getInventory().insertStack(itemStack)) {
                    world.playSound(null, entity.getX(), entity.getY(), entity.getZ(), SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 0.2f, (CarpetServer.rand.nextFloat() - CarpetServer.rand.nextFloat()) * 1.4F + 2.0F);
                    ((PlayerEntity) entity).increaseStat(Stats.PICKED_UP.getOrCreateStat(item), itemAmount);
                } else {
                    dropStack(world, pos, itemStack);
                }
            });
            state.onStacksDropped((ServerWorld) world, pos, stack, true);
        }
    }

    public static boolean validCarefulBreak(Entity entity){
        return CarpetRedCraftSettings.carefulBreak && entity instanceof PlayerEntity && entity.isInSneakingPose() && !validSilkyBlockstate((PlayerEntity) entity);
    }
    public static boolean validSilkyBlockstate(PlayerEntity player){
        NbtList nbtList = player.getMainHandStack().getEnchantments();

        for(int i = 0; i < nbtList.size(); ++i) {
            NbtCompound nbtCompound = nbtList.getCompound(i);
            Identifier identifier2 = Identifier.tryParse(nbtCompound.getString("id"));
            if (identifier2 != null && identifier2.equals(new Identifier("redcraft:silkyblockstate"))) {
               return true;
            }
        }
        return false;
    }
}