package it.multicoredev.carpetredcraftaddons;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.script.CarpetScriptServer;
import carpet.script.bundled.BundledModule;
import net.minecraft.server.MinecraftServer;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class CarpetRedCraftExtension implements CarpetExtension {
    public static final String MOD_ID = "carpet-redcraft-addons";
    public static final String MOD_NAME = "Carpet RedCraft Addons";
    public static final String MOD_VERSION = "1.4.21";

    static {
        CarpetServer.manageExtension(new CarpetRedCraftExtension());
    }

    @Override
    public void onGameStarted() {
        // let's /carpet handle our few simple settings
        CarpetServer.settingsManager.parseSettingsClass(CarpetRedCraftSettings.class);
        CarpetScriptServer.registerBuiltInScript(redcraftDefaultScript("placeable", false));
    }

    @Override
    public void onServerLoadedWorlds(MinecraftServer server) {

    }

    @Override
    public String version() {
        return MOD_ID;
    }

    public static BundledModule redcraftDefaultScript(String scriptName, boolean isLibrary) {
        BundledModule module = new BundledModule(scriptName.toLowerCase(Locale.ROOT), null, false);
        try {
            module = new BundledModule(scriptName.toLowerCase(Locale.ROOT),
                    IOUtils.toString(
                            BundledModule.class.getClassLoader().getResourceAsStream("assets/" + MOD_ID + "/scripts/" + scriptName + (isLibrary ? ".scl" : ".sc")),
                            StandardCharsets.UTF_8
                    ), isLibrary);
        } catch (NullPointerException | IOException ignored) {
        }
        return module;
    }

    public static void noop() {
    }
}