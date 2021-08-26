package carpetextra.mixins;

import carpetextra.utils.BlockPlacer;
import it.multicoredev.carpetredcraftaddons.CarpetRedCraftSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemPlacementContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(priority = 999, value = BlockItem.class)
public class BlockItemMixin_accurateBlockPlacement
{
    @Redirect(method = "getPlacementState", at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/block/Block;getPlacementState(Lnet/minecraft/item/ItemPlacementContext;)Lnet/minecraft/block/BlockState;"
    ))
    private BlockState getAlternatePlacement(Block block, ItemPlacementContext itemPlacementContext_1)
    {
        if (CarpetRedCraftSettings.accurateBlockPlacement)
        {
            BlockState tryAlternative = BlockPlacer.alternativeBlockPlacement(block, itemPlacementContext_1);
            if (tryAlternative != null)
                return tryAlternative;
        }
        return block.getPlacementState(itemPlacementContext_1);
    }

}