package it.multicoredev.carpetredcraftaddons.mixins;

import carpet.settings.ParsedRule;
import carpet.settings.Rule;
import carpet.settings.SettingsManager;
import it.multicoredev.carpetredcraftaddons.CarpetRedCraftSettings;
import net.fabricmc.loader.api.FabricLoader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.lang.reflect.Field;
import java.util.Map;

@Mixin(value = SettingsManager.class, remap = false)
public class CarpetSettingsManagerMixin {
    private Class<?> currentSettingsClass;
    private ParsedRule<?> currentParsedRule;

    @Inject(
        method = "parseSettingsClass",
        at = @At(
            value = "INVOKE",
            target = "Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"
        ),
        locals = LocalCapture.CAPTURE_FAILHARD
    )
    private void disableCraftableCobwebsWhenRugIsInstalled(
        Class<?> settingsClass,
        CallbackInfo ci,
        Field[] var2,
        int var3,
        int var4,
        Field f,
        Rule rule,
        ParsedRule<?> parsed
    ) {
        currentSettingsClass = settingsClass;
        currentParsedRule = parsed;
    }

    @Redirect(method = "parseSettingsClass", at = @At(value = "INVOKE", target = "Ljava/util/Map;put(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;"))
    private <K, V> V disableCraftableCobwebsWhenRugIsInstalled(Map<K, V> map, K key, V value) {
        if (currentParsedRule.name.equals("craftableCobwebs")
                && currentSettingsClass.getName().equals(CarpetRedCraftSettings.class.getName())
                && FabricLoader.getInstance().isModLoaded("rug")) {
            return null;
        }
        return map.put(key, value);
    }
}
