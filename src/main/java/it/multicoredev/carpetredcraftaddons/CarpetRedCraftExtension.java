package it.multicoredev.carpetredcraftaddons;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.CarpetSettings;
import carpet.script.CarpetScriptServer;
import carpet.script.bundled.BundledModule;
import com.mojang.brigadier.CommandDispatcher;
import it.multicoredev.carpetredcraftaddons.commands.DefaultConfig;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
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
        CarpetServer.settingsManager.parseSettingsClass(CarpetRedCraftSettings.class);
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("placeable", false));
    }

    @Override
    public void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher) {
        DefaultConfig.register(dispatcher);
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