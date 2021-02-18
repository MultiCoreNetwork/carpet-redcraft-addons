package it.multicoredev.carpetredcraftaddons;

import carpet.CarpetExtension;
import carpet.CarpetServer;
import carpet.script.CarpetExpression;
import carpet.script.CarpetScriptServer;
import carpet.script.bundled.BundledModule;
import carpet.settings.ParsedRule;
import com.google.common.collect.Lists;
import com.mojang.brigadier.CommandDispatcher;
import it.multicoredev.carpetredcraftaddons.commands.DefaultConfigCommand;
import it.multicoredev.carpetredcraftaddons.commands.OpCommand;
import it.multicoredev.carpetredcraftaddons.commands.PublishCommand;
import it.multicoredev.carpetredcraftaddons.functions.OfflineStatisticFunction;
import net.minecraft.resource.ResourcePackManager;
import net.minecraft.resource.ResourcePackProfile;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ReloadCommand;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.WorldSavePath;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import static java.util.stream.Collectors.toList;

public class CarpetRedCraftExtension implements CarpetExtension {
    public static final String MOD_ID = "carpet-redcraft-addons";
    public static final String MOD_NAME = "Carpet RedCraft Addons";
    public static final String MOD_VERSION = "1.4.24";

    static {
        CarpetServer.manageExtension(new CarpetRedCraftExtension());
    }

    @Override
    public void onGameStarted() {
        CarpetServer.settingsManager.parseSettingsClass(CarpetRedCraftSettings.class);

        // SCRIPTS
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("colorableshulkers", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("dragoneggrespawns", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("floatingladders", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("glowingsquid", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("graves", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("handwaxing", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("horsestats", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("invisibleitemframe", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("lastdeathcompass", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("morewanderingtrades", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("placeableplants", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("playerme", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("prunedplants", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("redcrafttwoteleport", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("ropes", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("silkyblockstates", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("sitanywhere", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("skull", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("specialnametags", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("stats", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("timebar", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("treecapitator", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("villagerleash", false));
        CarpetScriptServer.registerSettingsApp(redcraftDefaultScript("waystone", false));
    }

    @Override
    public void onServerLoadedWorlds(MinecraftServer server) {
        // DATAPACKS
        registerDatapackRule(server, "betterArmorStands", "RedPack - Better Armor Stands.zip");
        registerDatapackRule(server, "craftableCobwebs", "RedPack - Craftable Cobwebs.zip");
        registerDatapackRule(server, "craftableColoredBlocks", "RedPack - Craftable Colored Blocks.zip");
        registerDatapackRule(server, "craftableCorals", "RedPack - Craftable Corals.zip");
        registerDatapackRule(server, "craftableDeadBushes", "RedPack - Craftable Dead Bushes.zip");
        registerDatapackRule(server, "craftableElytra", "RedPack - Craftable Elytra.zip");
        registerDatapackRule(server, "craftableIce", "RedPack - Craftable Ice.zip");
        registerDatapackRule(server, "craftableLargeFern", "RedPack - Craftable Large Fern.zip");
        registerDatapackRule(server, "craftableNetherWarts", "RedPack - Craftable Nether Warts.zip");
        registerDatapackRule(server, "craftablePackedIce", "RedPack - Craftable Packed Ice.zip");
        registerDatapackRule(server, "craftablePlayerHead", "RedPack - Craftable Player Heads.zip");
        registerDatapackRule(server, "craftableQuartz", "RedPack - Craftable Quartz.zip");
        registerDatapackRule(server, "craftableShulkerShells", "RedPack - Craftable Shulker Shells.zip");
        registerDatapackRule(server, "craftableStrippedWood", "RedPack - Craftable Stripped Wood.zip");
        registerDatapackRule(server, "craftableTallGrass", "RedPack - Craftable Tall Grass.zip");
        registerDatapackRule(server, "craftableTuffAndCalcite", "RedPack - Craftable Tuff and Calcite.zip");
        registerDatapackRule(server, "endermanNoGrief", "RedPack - Enderman No Grief.zip");
        registerDatapackRule(server, "quartzCraftingCompatibility", "RedPack - Quartz Crafting Compatibility.zip");
        registerDatapackRule(server, "stonecutterQuartz", "RedPack - Quartz Stonecutter.zip");
        registerDatapackRule(server, "redSandstoneCraftingCompatibility", "RedPack - Red Sandstone Crafting Compatibility.zip");
        registerDatapackRule(server, "stonecutterRedSandstone", "RedPack - Red Sandstone Stonecutter.zip");
        registerDatapackRule(server, "craftableRedSand", "RedPack - Red Sandstone to Red Sand.zip");
        registerDatapackRule(server, "sandstoneCraftingCompatibility", "RedPack - Sandstone Crafting Compatibility.zip");
        registerDatapackRule(server, "stonecutterSandstone", "RedPack - Sandstone Stonecutter.zip");
        registerDatapackRule(server, "craftableSand", "RedPack - Sandstone to Sand.zip");
        registerDatapackRule(server, "slabToBlockCrafing", "RedPack - Slab to Full Block.zip");
        registerDatapackRule(server, "stonecutterStone", "RedPack - Stone Stonecutter.zip");
        registerDatapackRule(server, "stonecutterWood", "RedPack - Wood Stonecutter.zip");
        initializeDatapackRules(server);
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
    }

    @Override
    public String version() {
        return MOD_ID;
    }

    public static Map<String, String> datapackRules = new HashMap<>();

    public void initializeDatapackRules(MinecraftServer server) {
        ResourcePackManager resourcePackManager = server.getCommandSource().getMinecraftServer().getDataPackManager();
        resourcePackManager.scanPacks();
        List<ResourcePackProfile> list = Lists.newArrayList(resourcePackManager.getEnabledProfiles());
        datapackRules.forEach((ruleName, datapackName) -> {
            ParsedRule<?> rule = CarpetServer.settingsManager.getRule(ruleName);
            ResourcePackProfile resourcePackProfile = resourcePackManager.getProfile("file/" + datapackName);
            if (rule.getBoolValue() || (rule.type == String.class && !rule.get().equals("false"))) {
                list.add(0, resourcePackProfile);
            } else {
                list.remove(resourcePackProfile);
            }
        });
        ReloadCommand.method_29480(list.stream().map(ResourcePackProfile::getName).collect(toList()), server.getCommandSource());
    }


    public void registerDatapackRule(MinecraftServer server, String ruleName, String datapackName) {
        copyDatapackFolder(server, datapackName);
        datapackRules.put(ruleName, datapackName);
        CarpetServer.settingsManager.addRuleObserver((source, rule, s) -> {
            if (rule.categories.contains("crafting") && rule.name.equals(ruleName)) {
                ResourcePackManager resourcePackManager = source.getMinecraftServer().getDataPackManager();
                ResourcePackProfile resourcePackProfile = resourcePackManager.getProfile("file/" + datapackName);
                List<ResourcePackProfile> list = Lists.newArrayList(resourcePackManager.getEnabledProfiles());
                if (rule.getBoolValue() || (rule.type == String.class && !rule.get().equals("false"))) {
                    list.add(0, resourcePackProfile);
                } else {
                    list.remove(resourcePackProfile);
                }
                ReloadCommand.method_29480(list.stream().map(ResourcePackProfile::getName).collect(toList()), source);
            }
        });
    }

    private void copyDatapackFolder(MinecraftServer server, String datapackName) {
        try {
            String datapacks = server.getSavePath(WorldSavePath.DATAPACKS).toString();
            Files.copy(
                    BundledModule.class.getClassLoader().getResourceAsStream("assets/" + MOD_ID + "/datapacks/" + datapackName),
                    new File(datapacks, datapackName).toPath()
            );
        } catch (IOException ignored) {
        }
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