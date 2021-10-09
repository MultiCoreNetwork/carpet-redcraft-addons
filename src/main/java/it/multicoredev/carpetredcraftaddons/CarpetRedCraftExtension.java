package it.multicoredev.carpetredcraftaddons;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.script.CarpetExpression;
import carpet.script.CarpetScriptServer;
import carpet.script.bundled.BundledModule;
import carpet.settings.Rule;
import carpet.settings.SettingsManager;
import com.mojang.brigadier.CommandDispatcher;
import it.multicoredev.carpetredcraftaddons.commands.DefaultConfigCommand;
import it.multicoredev.carpetredcraftaddons.commands.OpCommand;
import it.multicoredev.carpetredcraftaddons.commands.PublishCommand;
import it.multicoredev.carpetredcraftaddons.events.CarpetRedCraftEvents;
import it.multicoredev.carpetredcraftaddons.functions.OfflineStatisticFunction;
import it.multicoredev.carpetredcraftaddons.functions.StructuresFunctions;
import net.fabricmc.api.ModInitializer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import org.apache.commons.io.IOUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import static it.multicoredev.carpetredcraftaddons.util.DatapackUtil.*;

public class CarpetRedCraftExtension implements CarpetExtension, ModInitializer {
    public static final String MOD_ID = "carpet-redcraft-addons";
    public static final String MOD_NAME = "Carpet RedCraft Addons";
    public static final String MOD_VERSION = "1.4.34";
    public static final Logger LOG = LogManager.getLogger(MOD_ID);
    public static boolean DATAPACK_RULES_RELOAD = true;

    @Override
    public void onGameStarted() {
        CarpetServer.settingsManager.parseSettingsClass(CarpetRedCraftSettings.class);
        for(Field f : CarpetRedCraftSettings.class.getDeclaredFields()){
            Rule rule = f.getAnnotation(Rule.class);
            if(rule == null) continue;
            CarpetScriptServer.registerSettingsApp(redcraftDefaultScript(rule.appSource(), false));
        }
        // CarpetServer.settingsManager.printAllRulesToLog(CarpetRedCraftSettings.REDCRAFT);
    }

    @Override
    public void onServerLoadedWorlds(MinecraftServer server) {
        initializeDatapack();
    }

    @Override
    public void onServerClosed(MinecraftServer server) {
        deleteFile(datapackFile("data"));
    }

    @Override
    public void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher) {
        DefaultConfigCommand.register(dispatcher);
        OpCommand.register(dispatcher);
        PublishCommand.register(dispatcher);
    }

    @Override
    public void scarpetApi(CarpetExpression expression) {
        OfflineStatisticFunction.apply(expression.getExpr());
        StructuresFunctions.apply(expression.getExpr());
    }

    @Override
    public String version() {
        return MOD_ID;
    }

    private static BundledModule redcraftDefaultScript(String scriptName, boolean isLibrary) {
        BundledModule module = new BundledModule(scriptName.toLowerCase(Locale.ROOT), null, false);
        try {
            module = new BundledModule(scriptName.toLowerCase(Locale.ROOT),
                    IOUtils.toString(
                            BundledModule.class.getClassLoader().getResourceAsStream(".data/" + MOD_ID + "/scripts/" + scriptName + (isLibrary ? ".scl" : ".sc")),
                            StandardCharsets.UTF_8
                    ), isLibrary);
        } catch (NullPointerException | IOException ignored) {
        }
        return module;
    }

    @Override
    public void onInitialize() {
        CarpetRedCraftEvents.noop();
        CarpetServer.manageExtension(new CarpetRedCraftExtension());
    }
}
