package it.multicoredev.carpetredcraftaddons.functions;

import carpet.fakes.StatTypeInterface;
import carpet.script.CarpetContext;
import carpet.script.Expression;
import carpet.script.LazyValue;
import carpet.script.value.NumericValue;
import net.minecraft.stat.ServerStatHandler;
import net.minecraft.stat.Stat;
import net.minecraft.stat.StatType;
import net.minecraft.util.Identifier;
import net.minecraft.util.InvalidIdentifierException;
import net.minecraft.util.WorldSavePath;
import net.minecraft.util.registry.Registry;

import java.io.File;

public class OfflineStatisticFunction {
    public static void apply(Expression expression) {
        // offline_statistic(uuid, category, entry)
        expression.addLazyFunction("offline_statistic", 3, (c, t, lv) -> {
            CarpetContext cc = (CarpetContext) c;
            String uuid = lv.get(0).evalValue(c).getString();
            File worldFolder = new File(cc.s.getServer().getSavePath(WorldSavePath.ROOT).toFile(), "stats");
            File statFile = new File(worldFolder, uuid + ".json");
            if (!statFile.exists()) {
                return LazyValue.NULL;
            } else {
                Identifier category;
                Identifier statName;
                try {
                    category = new Identifier(lv.get(1).evalValue(c).getString());
                    statName = new Identifier(lv.get(2).evalValue(c).getString());
                } catch (InvalidIdentifierException var10) {
                    return LazyValue.NULL;
                }
                StatType<?> type = Registry.STAT_TYPE.get(category);
                if (type == null) {
                    return LazyValue.NULL;
                } else {
                    Stat<?> stat = getStat(type, statName);
                    if (stat == null) {
                        return LazyValue.NULL;
                    } else {
                        ServerStatHandler ssh = new ServerStatHandler(cc.s.getServer(), statFile);
                        return (_c, _t) -> new NumericValue(ssh.getStat(stat));
                    }
                }
            }
        });
    }

    private static <T> Stat<T> getStat(StatType<T> type, Identifier id) {
        T key = type.getRegistry().get(id);
        return key != null && ((StatTypeInterface) type).hasStatCreated(key) ? type.getOrCreateStat(key) : null;
    }
}
