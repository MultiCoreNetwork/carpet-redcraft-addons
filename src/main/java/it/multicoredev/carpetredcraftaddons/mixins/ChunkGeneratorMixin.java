package it.multicoredev.carpetredcraftaddons.mixins;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.util.collection.Pool;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.SpawnSettings;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.List;

import static it.multicoredev.carpetredcraftaddons.CarpetRedCraftSettings.doZombieHorse;
import static it.multicoredev.carpetredcraftaddons.CarpetRedCraftSettings.zombieHorseHalloween;

@Mixin(ChunkGenerator.class)
public class ChunkGeneratorMixin {
    @Inject(method = "getEntitySpawnList", at = @At("HEAD"), cancellable = true)
    private void isHalloween(RegistryEntry<Biome> biome, StructureAccessor accessor, SpawnGroup group, BlockPos pos, CallbackInfoReturnable<Pool<SpawnSettings.SpawnEntry>> cir) {
        if (doZombieHorse && group == SpawnGroup.CREATURE) {
            LocalDate localDate = LocalDate.now();
            int i = localDate.get(ChronoField.DAY_OF_MONTH);
            int j = localDate.get(ChronoField.MONTH_OF_YEAR);
            if (!zombieHorseHalloween || (j == 10 && i == 31)) {
                Pool<SpawnSettings.SpawnEntry> p = biome.value().getSpawnSettings().getSpawnEntries(group);
                List<SpawnSettings.SpawnEntry> spawnEntries = new ArrayList<>(p.getEntries());
                spawnEntries.add(new SpawnSettings.SpawnEntry(EntityType.ZOMBIE_HORSE, 1, 1, 3));
                cir.setReturnValue(Pool.of(spawnEntries));
            }
        }
    }
}
