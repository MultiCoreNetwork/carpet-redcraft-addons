package it.multicoredev.carpetredcraftaddons.functions;

import carpet.CarpetSettings;
import carpet.script.CarpetContext;
import carpet.script.Expression;
import carpet.script.LazyValue;
import carpet.script.argument.BlockArgument;
import carpet.script.exception.InternalExpressionException;
import carpet.script.exception.ThrowStatement;
import carpet.script.exception.Throwables;
import carpet.script.value.BooleanValue;
import carpet.script.value.NumericValue;
import carpet.script.value.Value;
import net.minecraft.block.Block;
import net.minecraft.block.StructureBlock;
import net.minecraft.structure.Structure;
import net.minecraft.structure.StructureManager;
import net.minecraft.structure.StructurePlacementData;
import net.minecraft.util.Identifier;
import net.minecraft.util.InvalidIdentifierException;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.registry.Registry;

import java.util.List;
import java.util.Random;

import static java.lang.Math.max;
import static java.lang.Math.min;
import static java.util.stream.Collectors.toList;

public class StructuresFunctions {
    public static void apply(Expression expression) {
        // save_structure(id, from, to, includeEntities?, ignoredBlock?, saveStructure?)
        expression.addLazyFunction("save_structure", -1, (c, t, lv) -> {
            if (lv.size() < 3)
                throw new InternalExpressionException("'save_structure' requires at least three parameters");

            CarpetContext cc = (CarpetContext) c;
            List<Value> params = lv.stream().map(a -> a.evalValue(cc)).collect(toList());

            BlockArgument pos1Locator = BlockArgument.findIn(cc, params, 1);
            BlockArgument pos2Locator = BlockArgument.findIn(cc, params, pos1Locator.offset);

            if (lv.size() > pos2Locator.offset + 3)
                throw new InternalExpressionException("'save_structure' requires less parameters");

            if (lv.size() < pos2Locator.offset + 1) params.add(Value.FALSE);
            if (lv.size() < pos2Locator.offset + 2) params.add(Value.NULL);
            if (lv.size() < pos2Locator.offset + 3) params.add(Value.FALSE);

            StructureManager structureManager = cc.s.getMinecraftServer().getStructureManager();
            Structure structure;

            Identifier structureIdentifier;
            try {
                structureIdentifier = new Identifier(params.get(0).getString());
            } catch (InvalidIdentifierException ignored) {
                return LazyValue.NULL;
            }
            structure = structureManager.getStructureOrBlank(structureIdentifier);

            BlockPos pos1 = pos1Locator.block.getPos();
            BlockPos pos2 = pos2Locator.block.getPos();

            int x1 = pos1.getX();
            int y1 = pos1.getY();
            int z1 = pos1.getZ();
            int x2 = pos2.getX();
            int y2 = pos2.getY();
            int z2 = pos2.getZ();
            int minx = min(x1, x2);
            int miny = min(y1, y2);
            int minz = min(z1, z2);
            int maxx = max(x1, x2);
            int maxy = max(y1, y2);
            int maxz = max(z1, z2);
            int sizex = maxx - minx + 1;
            int sizey = maxy - miny + 1;
            int sizez = maxz - minz + 1;

            Block ignoredBlock = CarpetSettings.structureBlockIgnoredBlock;

            if (!params.get(pos2Locator.offset + 1).isNull()) {
                try {
                    Identifier blockIdentifier = new Identifier(params.get(pos2Locator.offset + 2).getString());
                    ignoredBlock = Registry.BLOCK.getOrEmpty(blockIdentifier).orElse(ignoredBlock);
                } catch (InvalidIdentifierException ignored) {
                }
            }

            structure.setAuthor(cc.s.getName());
            structure.saveFromWorld(cc.s.getWorld(), new BlockPos(x1, y1, z1), new BlockPos(x2, y2, z2), params.get(pos2Locator.offset).getBoolean(), ignoredBlock);

            if(params.get(pos2Locator.offset + 2).getBoolean())
                structureManager.saveStructure(structureIdentifier);

            return (_c, _t) -> new NumericValue(sizex * sizey * sizez);
        });

        // load_structure(id, pos, includeEntities?)
        expression.addLazyFunction("load_structure", -1, (c, t, lv) -> {
            if (lv.size() < 2)
                throw new InternalExpressionException("'load_structure' requires at least two parameters");

            CarpetContext cc = (CarpetContext) c;
            List<Value> params = lv.stream().map(a -> a.evalValue(cc)).collect(toList());

            BlockArgument pos1Locator = BlockArgument.findIn(cc, params, 1);

            if (lv.size() < pos1Locator.offset + 1) params.add(Value.FALSE);

            StructureManager structureManager = cc.s.getMinecraftServer().getStructureManager();
            Structure structure;

            try {
                Identifier identifier = new Identifier(params.get(0).getString());
                structure = structureManager.getStructure(identifier);
                if (structure == null) throw new ThrowStatement(identifier.toString(), Throwables.UNKNOWN_STRUCTURE);
            } catch (InvalidIdentifierException ignored) {
                return LazyValue.NULL;
            }

            BlockPos pos1 = pos1Locator.block.getPos();

            if (lv.size() > pos1Locator.offset + 1)
                throw new InternalExpressionException("'load_structure' requires less parameters");

            StructurePlacementData structurePlacementData = new StructurePlacementData().setIgnoreEntities(!params.get(pos1Locator.offset).getBoolean());

            if (!CarpetSettings.fillUpdates)
                CarpetSettings.impendingFillSkipUpdates = true;
            try {
                return (_c, _t) -> BooleanValue.of(structure.place(cc.s.getWorld(), pos1, pos1, structurePlacementData, new Random(Util.getMeasuringTimeMs()), 2));
            } finally {
                CarpetSettings.impendingFillSkipUpdates = false;
            }
        });
    }
}
