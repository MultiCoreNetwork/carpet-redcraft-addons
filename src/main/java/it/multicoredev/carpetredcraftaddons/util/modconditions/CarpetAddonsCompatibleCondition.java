package it.multicoredev.carpetredcraftaddons.util.modconditions;

import carpet.settings.Condition;
import net.fabricmc.loader.api.FabricLoader;

public class CarpetAddonsCompatibleCondition implements Condition {

    @Override
    public boolean isTrue() {
        return !FabricLoader.getInstance().isModLoaded("carpet-addons");
    }
}
