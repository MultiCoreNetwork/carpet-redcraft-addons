package it.multicoredev.carpetredcraftaddons;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.script.CarpetExpression;
import carpet.script.CarpetScriptServer;
import carpet.script.bundled.BundledModule;
import com.mojang.brigadier.CommandDispatcher;
import it.multicoredev.carpetredcraftaddons.commands.DefaultConfigCommand;
import it.multicoredev.carpetredcraftaddons.commands.OpCommand;
import it.multicoredev.carpetredcraftaddons.commands.PublishCommand;
import it.multicoredev.carpetredcraftaddons.functions.OfflineStatisticFunction;
import net.minecraft.server.command.ServerCommandSource;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

public class CarpetRedCraftExtension implements CarpetExtension {
    public static final String MOD_ID = "carpet-redcraft-addons";

    static {
        CarpetServer.manageExtension(new CarpetRedCraftExtension());
    }

    @Override
    public void onGameStarted() {
        CarpetServer.settingsManager.parseSettingsClass(CarpetRedCraftSettings.class);

        // SCRIPTS
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("betterarmorstand", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("colorableshulkers", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("craftablebricks", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("craftablecobwebs", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("craftablecoloredblocks", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("craftablecorals", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("craftabledeadbushes", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("craftabledeepslateores", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("craftableelytra", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("craftableice", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("craftablelargefern", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("craftablenetherwarts", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("craftabelpackedice", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("craftableplayerhead", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("craftablequartz", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("craftableredsand", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("craftablesand", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("craftablesculksensor", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("craftableshulkershells", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("craftablestrippedwood", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("craftabletallgrass", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("craftabletuffandcalcite", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("dragoneggrespawns", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("endermannogrief", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("fastredstonecrafting", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("floatingladders", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("glowingsquid", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("graves", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("horsestats", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("huskdropsand", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("invisibleitemframe", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("lastdeathcompass", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("morewanderingtrades", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("moss", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("placeableplants", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("playerme", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("prunedplants", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("quartzcraftingcompatibility", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("redcrafttwoteleport", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("redsandstonecraftingcompatibility", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("ropes", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("sandstonecraftingcompatibility", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("silkyblockstates", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("sitanywhere", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("skull", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("slabtoblockcrafting", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("specialnametags", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("stats", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("stonecutterquartz", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("stonecutterredsandstone", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("stonecuttersandstone", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("stonecutterstone", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("stonecutterwood", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("timebar", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("treecapitator", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("villagerleash", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("waystone", false));
    }

    public void registerCommands(CommandDispatcher<ServerCommandSource> dispatcher) {
        DefaultConfigCommand.register(dispatcher);
        OpCommand.register(dispatcher);
        PublishCommand.register(dispatcher);
    }

    @Override
    public void scarpetApi(CarpetExpression expression) {
        OfflineStatisticFunction.apply(expression.getExpr());
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
