package it.multicoredev.carpetredcraftaddons.functions;

import carpet.script.CarpetContext;
import carpet.script.Expression;
import carpet.script.LazyValue;
import carpet.script.exception.InternalExpressionException;
import carpet.script.value.ListValue;
import carpet.script.value.NBTSerializableValue;
import carpet.script.value.StringValue;
import carpet.script.value.Value;
import net.minecraft.command.DataCommandStorage;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.Identifier;

import java.util.stream.Collectors;

import static carpet.script.value.NBTSerializableValue.nameFromRegistryId;

public class StorageCarpetFunction {
    public static void apply(Expression expression) {
        // storage()
        // storage(key)
        // storage(key, nbt)
        expression.addLazyFunction("storage", -1, (c, t, lv) -> {
            if (lv.size() > 2) throw new InternalExpressionException("'storage' requires 0, 1 or 2 arguments.");
            CarpetContext cc = (CarpetContext) c;
            DataCommandStorage storage = cc.s.getMinecraftServer().getDataCommandStorage();
            if (lv.size() == 0) {
                Value ret = ListValue.wrap(storage.getIds().map(i -> new StringValue(nameFromRegistryId(i))).collect(Collectors.toList()));
                return (_c, _t) -> ret;
            }
            String key = lv.get(0).evalValue(c).getString();
            CompoundTag old_nbt = storage.get(new Identifier(key));
            if (lv.size() == 2) {
                Value nbt = lv.get(1).evalValue(c);
                NBTSerializableValue new_nbt = (nbt instanceof NBTSerializableValue) ? (NBTSerializableValue) nbt
                        : NBTSerializableValue.parseString(nbt.getString(), true);
                storage.set(new Identifier(key), new_nbt.getCompoundTag());
            }
            if (old_nbt == null) return LazyValue.NULL;
            return (_c, _t) -> new NBTSerializableValue(old_nbt);
        });
    }
}
