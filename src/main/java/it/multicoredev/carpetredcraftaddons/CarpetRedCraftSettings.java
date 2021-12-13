package it.multicoredev.carpetredcraftaddons;

import carpet.settings.ParsedRule;
import carpet.settings.Rule;
import carpet.settings.Validator;
import it.multicoredev.carpetredcraftaddons.util.CraftingRule;
import it.multicoredev.carpetredcraftaddons.util.LootTableRule;
import it.multicoredev.carpetredcraftaddons.util.TagRule;
import it.multicoredev.carpetredcraftaddons.util.modconditions.CarpetAddonsCompatibleCondition;
import it.multicoredev.carpetredcraftaddons.util.modconditions.CarpetExtraCompatibleCondition;
import it.multicoredev.carpetredcraftaddons.util.modconditions.RugCompatibleCondition;
import net.minecraft.server.command.ServerCommandSource;

import java.util.Locale;

import static carpet.settings.RuleCategory.*;

public class CarpetRedCraftSettings {
    public static final String REDCRAFT = "redcraft";
    public static final String CRAFTING = "crafting";
    public static final String EXTRA = "extras";
    public static final String ADDONS = "carpetaddons";

    @Rule(
            desc = "Client can provide alternative block placement.",
            extra = {"From Gnembon's Carpet Extra"},
            category = {EXTRA, SURVIVAL},
            condition = CarpetExtraCompatibleCondition.class
    )
    public static boolean accurateBlockPlacement = false;
    @Rule(
            desc = "Places the mined block in the player inventory when sneaking",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, ADDONS},
            condition = CarpetAddonsCompatibleCondition.class
    )
    public static boolean carefulBreak = false;
    @Rule(
            desc = "Fixes block states in F3 debug mode not updating for some blocks.",
            category = {EXTRA, EXPERIMENTAL},
            extra = {"May cause increased network traffic.", "Works with cactus, sugar cane, saplings, hoppers, dispensers and droppers.", "From Gnembon's Carpet Extra"},
            condition = CarpetExtraCompatibleCondition.class
    )
    public static boolean blockStateSyncing = false;

    // -------------------COMMANDS--------------------- //
    @Rule(
            desc = "Allow you to locate player",
            appSource = "locateplayer",
            category = {SURVIVAL, FEATURE, REDCRAFT, COMMAND}
    )
    public static boolean commandLocatePlayer = false;
    @Rule(
            desc = "Adds the /lookme command to rotate entities",
            appSource = "lookme",
            category = {SURVIVAL, FEATURE, REDCRAFT, COMMAND}
    )
    public static boolean commandLookme = false;
    @Rule(
            desc = "Deopped players can do /player on themselves",
            extra = {
                    "and you can create Fake Player on themselves",
                    "If commandPlayer is set to False you can't create Fake Player on themselves"},
            category = {EXPERIMENTAL, FEATURE, REDCRAFT, COMMAND},
            appSource = "playerme"
    )
    public static boolean commandPlayerMe = false;
    @Rule(
            desc = "Allows to have access to the /skull command",
            appSource = "skull",
            category = {SURVIVAL, FEATURE, REDCRAFT, COMMAND}
    )
    public static boolean commandSkull = false;
    @Rule(
            desc = "Allows to have access to the /stats command",
            appSource = "stats",
            category = {SURVIVAL, FEATURE, REDCRAFT, COMMAND}
    )
    public static boolean commandStats = false;
    @Rule(
            desc = "Allows to have access to the /swapitem command",
            appSource = "swapitem",
            category = {SURVIVAL, FEATURE, REDCRAFT, COMMAND}
    )
    public static boolean commandSwapitem = false;
    @Rule(
            desc = "Allows to have access to the /timebar command",
            appSource = "timebar",
            category = {SURVIVAL, FEATURE, REDCRAFT, COMMAND}
    )
    public static boolean commandTimebar = false;
    @Rule(
            desc = "Allows to have access to the /waystone command",
            appSource = "waystone",
            category = {SURVIVAL, FEATURE, REDCRAFT, COMMAND}
    )
    public static boolean commandWaystone = false;

    // ------------------CRAFTINGS--------------------- //
    @CraftingRule(recipes = "craftable_amethysts")
    @Rule(
            desc = "Allows to craft amethyst clusters from amethyst block",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING}
    )
    public static boolean craftableAmethysts = false;
    @TagRule(tags = "items/craftable_barrier")
    @CraftingRule(recipes = "craftable_barrier")
    @Rule(
            desc = "Allows to craft barrier block",
            appSource = "barrier",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING}
    )
    public static boolean craftableBarrier = false;
    @CraftingRule(recipes = "craftable_bricks")
    @Rule(
            desc = "Adds reverse crafting of Prismarine, Prismarine Bricks, Bricks and Nether Bricks",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING}
    )
    public static boolean craftableBricks = false;
    @CraftingRule(recipes = "craftable_cobwebs")
    @Rule(
            desc = "Allows to craft Cobwebs",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING},
            condition = RugCompatibleCondition.class
    )
    public static boolean craftableCobwebs = false;
    @TagRule(tags = "items/craftable_colored_blocks")
    @CraftingRule(recipes = "craftable_colored_blocks")
    @Rule(
            desc = "Allows to use colored blocks to craft other color variants",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING}
    )
    public static boolean craftableColoredBlocks = false;
    @TagRule(tags = "items/craftable_corals")
    @CraftingRule(recipes = "craftable_corals")
    @Rule(
            desc = "Allows to craft Coral Blocks with 4 Coral Fans and Corals",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING}
    )
    public static boolean craftableCorals = false;
    @CraftingRule(recipes = "craftable_dead_bushes")
    @Rule(
            desc = "Allows to smelt saplings to get Dead Bushes",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING}
    )
    public static boolean craftableDeadBushes = false;
    @TagRule(tags = "items/craftable_deepslate_ores")
    @CraftingRule(recipes = "craftable_deepslate_ores")
    @Rule(
            desc = "You can craft deepslate ore in smithing table",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean craftableDeepslateOres = false;
    @CraftingRule(recipes = "craftable_elytra")
    @Rule(
            desc = "Allows to craft Elytras",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING}
    )
    public static boolean craftableElytra = false;
    @CraftingRule(recipes = "craftable_large_fern")
    @Rule(
            desc = "Allows to craft Large Ferns",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING}
    )
    public static boolean craftableLargeFern = false;
    @TagRule(tags = "items/craftable_light")
    @CraftingRule(recipes = "craftable_light")
    @Rule(
            desc = "Allows to craft light block",
            appSource = "light",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING}
    )
    public static boolean craftableLight = false;
    @CraftingRule(recipes = "craftable_nether_warts")
    @Rule(
            desc = "Allows to craft Nether Warts from Nether Warts Block",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING}
    )
    public static boolean craftableNetherWarts = false;
    @TagRule(tags = "items/craftable_player_heads")
    @CraftingRule(recipes = "craftable_player_heads")
    @Rule(
            desc = "Allows to craft Player Head from any kind of head",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING}
    )
    public static boolean craftablePlayerHead = false;
    @TagRule(tags = "items/craftable_quartz")
    @CraftingRule(recipes = "craftable_quartz")
    @Rule(
            desc = "Allows to craft Quartz from any kind of Quartz Block",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING}
    )
    public static boolean craftableQuartz = false;
    @TagRule(tags = "items/craftable_red_sand")
    @CraftingRule(recipes = "craftable_red_sand")
    @Rule(
            desc = "Allows to craft Red Sand from Red Sandstone",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING}
    )
    public static boolean craftableRedSand = false;
    @CraftingRule(recipes = "craftable_rooted_dirt")
    @Rule(
            desc = "Allows to craft rooted dirt",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING}
    )
    public static boolean craftableRootedDirt = false;
    @TagRule(tags = "items/craftable_sand")
    @CraftingRule(recipes = "craftable_sand")
    @Rule(
            desc = "Allows to craft Red Sand from Sandstone",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING}
    )
    public static boolean craftableSand = false;
    @CraftingRule(recipes = "craftable_sculk_sensor")
    @Rule(
            desc = "Allow to craft Sculk Sensor with endeperals and observers",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING}
    )
    public static boolean craftableSculkSensor = false;
    @CraftingRule(recipes = "craftable_shulker_shells")
    @Rule(
            desc = "Allows to craft Shulker Shells from Turle Shells and Popped Chorus Fruits",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING}
    )
    public static boolean craftableShulkerShells = false;
    @CraftingRule(recipes = "craftable_tall_grass")
    @Rule(
            desc = "Allows to craft Tall Grass",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING}
    )
    public static boolean craftableTallGrass = false;
    @CraftingRule(recipes = "craftable_tuff_and_calcite")
    @Rule(
            desc = "Allows to craft Tuff and Calite",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING}
    )
    public static boolean craftableTuffAndCalcite = false;
    @TagRule(tags = "items/coarse_dirt_crafting_compatibility")
    @CraftingRule(recipes = "coarse_dirt_crafting_compatibility")
    @Rule(
            desc = "Adds crafting compatibility for coarse dirt with all dirts type",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING}
    )
    public static boolean coarseDirtCraftingCompatibility = false;
    @CraftingRule(recipes = "fast_redstone_crafting")
    @Rule(
            desc = "Adds fast crafting for Dispenser and Repeater",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING}
    )
    public static boolean fastRedstoneCrafting = false;
    @CraftingRule(recipes = "quartz_crafting_compatibility")
    @Rule(
            desc = "Adds crafting compatibility with all types of Quartz Block",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING}
    )
    public static boolean quartzCraftingCompatibility = false;
    @CraftingRule(recipes = "red_sandstone_crafting_compatibility")
    @Rule(
            desc = "Adds crafting compatibility with all types of Red Sandstone",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING}
    )
    public static boolean redSandstoneCraftingCompatibility = false;
    @CraftingRule(recipes = "sandstone_crafting_compatibility")
    @Rule(
            desc = "Adds crafting compatibility with all types of Sandstone",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING}
    )
    public static boolean sandstoneCraftingCompatibility = false;
    @CraftingRule(recipes = "slab_to_block_crafting")
    @Rule(
            desc = "Allows to use 2 slabs to craft the block back",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING}
    )
    public static boolean slabToBlockCrafting = false;
    @TagRule(tags = "items/stonecutter_quartz")
    @CraftingRule(recipes = "stonecutter_quartz")
    @Rule(
            desc = "Adds stonecutter craftings for all types of Quartz Block",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING}
    )
    public static boolean stonecutterQuartz = false;
    @TagRule(tags = "items/stonecutter_red_sandstone")
    @CraftingRule(recipes = "stonecutter_red_sandstone")
    @Rule(
            desc = "Adds stonecutter craftings for all types of Red Sandstone",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING}
    )
    public static boolean stonecutterRedSandstone = false;
    @TagRule(tags = "items/stonecutter_sandstone")
    @CraftingRule(recipes = "stonecutter_sandstone")
    @Rule(
            desc = "Adds stonecutter craftings for all types of Sandstone",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING}
    )
    public static boolean stonecutterSandstone = false;
    @CraftingRule(recipes = "stonecutter_stone")
    @Rule(
            desc = "Adds stonecutter craftings for all variants of Stone",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING}
    )
    public static boolean stonecutterStone = false;
    @CraftingRule(recipes = "stonecutter_wood")
    @Rule(
            desc = "Adds stonecutter craftings for all types of Wooden Block",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING}
    )
    public static boolean stonecutterWood = false;
    @CraftingRule(recipes = "unpackable_ice")
    @Rule(
            desc = "Allow to unpack PackedIce & BlueIce using the stonecutter",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING}
    )
    public static boolean unpackableIce = false;

    // ---------------------TAGS----------------------- //
    @TagRule(overrideTags = "blocks/enderman_holdable.json")
    @Rule(
            desc = "Reduced the blocks that an Enderman can hold",
            extra = "Podzol, Pumpkins, Melons and Mycelium",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean endermanNoGrief = false;

    // ------------------LOOT-TABLES------------------- //
    @LootTableRule(overrideLoottables = "entities/husk.json")
    @Rule(
            desc = "Husks drop sand on death",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean huskDropsSand = false;

    // ------------------SCRIPTS----------------------- //
    @LootTableRule(overrideLoottables = "entities/armor_stand.json")
    @Rule(
            desc = "Allows ArmorStand editing with Hoe",
            appSource = "armorstandeditor",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean armorStandEditor = false;
    @Rule(
            desc = "Right clicking with a Phantom Membrane or Glass Pane on an Item Frame will make it invisible or fixed",
            appSource = "betteritemframes",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean betterItemFrames = false;
    @Rule(
            desc = "Allows to dye Shulkers",
            appSource = "colorableshulkers",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean colorableShulkers = false;
    @Rule(
            desc = "Allow you to transform concrete to concrete powder filling a glass bottle",
            appSource = "crumbleconcrete",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean crumbleConcrete = false;
    @Rule(
            desc = "Ender Dragon always spawns a new dragon egg",
            appSource = "dragoneggrespawns",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean dragonEggRespawns = false;
    @Rule(
            desc = "Allows to place ladders under other ladders",
            appSource = "floatingladders",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean floatingLadders = false;
    @Rule(
            desc = "Holding a light source will light around the player",
            appSource = "dynamiclight",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean dynamicLight = false;
    @Rule(
            desc = "Create personal graves when the player dies",
            appSource = "graves",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean graves = false;
    @Rule(
            desc = "Right clicking while sneaking on a horse/donkey/mule, will display the stats of the mob in the chat",
            appSource = "horsestats",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean horseStats = false;
    @Rule(
            desc = "Allows to right click a compass to make it point towards the last death point",
            appSource = "lastdeathcompass",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean lastDeathCompass = false;
    @Rule(
            desc = "Sponges work on lava too, but they will burn",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean lavaSponges = false;
    @Rule(
            desc = "Adds more trades to the Wandering Trader",
            extra = "Mini Blocks, TreeCapitator Axes and Silky Blockstate tools",
            appSource = "morewanderingtrades",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean moreWanderingTrades = false;
    @Rule(
            desc = "Allows to tie pillager with empty hands",
            appSource = "pillagerleash",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean pillagerLeash = false;
    @Rule(
            desc = "Allows to place plants on all blocks with flat top surface",
            appSource = "placeableplants",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean placeablePlants = false;
    @Rule(
            desc = "Allows to prune plants with shears",
            appSource = "prunedplants",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean prunedPlants = false;
    @Rule(
            desc = "Allow to repair anvil with iron ingot",
            appSource = "repairableanvil",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean repairableAnvil = false;
    @Rule(
            desc = "Allow to revive dead coral with water bottle",
            appSource = "revivecoral",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean reviveCoral = false;
    @Rule(
            desc = "Allows to leash a rope beetween two fences",
            appSource = "ropes",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean ropes = false;
    @Rule(
            desc = "Allows to mine a block keeping the blockstate if using a Silky Blocstate tool",
            appSource = "silkyblockstates",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean silkyBlockstates = false;
    @Rule(
            desc = "Allows to place saddles on the ground",
            appSource = "sitanywhere",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean sitAnywhere = false;
    @Rule(
            desc = "Adds many special Name Tags",
            extra = "'freeze', 'silent', 'invulnerable', 'nogravity', 'baby' ... ",
            appSource = "specialnametags",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean specialNameTags = false;
    @Rule(
            desc = "Allows to mine an entire tree mining a single block if using a TreeCapitator axe",
            appSource = "treecapitator",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean treecapitator = false;
    @Rule(
            desc = "Allows to leash villagers",
            appSource = "villagerleash",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean villagerLeash = false;

    // ---------------------MIXINS--------------------- //
    private static class AnvilRepairCostLimitValidator extends Validator<Integer> {
        @Override
        public Integer validate(ServerCommandSource source, ParsedRule<Integer> currentRule, Integer newValue, String string) {
            return (newValue > 0 && newValue <= 32767) ? newValue : null;
        }

        @Override
        public String description() {
            return "You must choose a value from 1 to 32767";
        }
    }

    @Rule(
            desc = "Allows to choose the anvil repair cost limit",
            category = {SURVIVAL, FEATURE, REDCRAFT},
            options = {"40", "1024", "24791"},
            strict = false,
            validate = AnvilRepairCostLimitValidator.class
    )
    public static int anvilRepairCostLimit = 40;
    @Rule(
            desc = "Disable Command Feedback for OPs level 1",
            category = {FEATURE, REDCRAFT}
    )
    public static boolean disableOPsCommandFeedback = false;
    @Rule(
            desc = "Ghasts will no grief the world",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean ghastNoGrief = false;
    @Rule(
            desc = "Allows illusioners to spawn in raids (It needs restart to turn it off)",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean illusionersSpawnInRaids = false;

    private static class ZombieHorseSpawningSettings extends Validator<String> {
        @Override
        public String validate(ServerCommandSource source, ParsedRule<String> currentRule, String newValue, String string) {
            CarpetRedCraftSettings.doZombieHorse = !newValue.toLowerCase(Locale.ROOT).equals("false");
            CarpetRedCraftSettings.zombieHorseHalloween = newValue.toLowerCase(Locale.ROOT).equals("halloween");
            return newValue;
        }
    }

    @Rule(
            desc = "Zombie horses can spawn in the world",
            extra = {
                    "With halloween: it will spawn only during Halloween"
            },
            category = {EXPERIMENTAL, FEATURE},
            options = {"true", "false", "halloween"},
            validate = ZombieHorseSpawningSettings.class
    )
    public static String zombieHorseSpawning = "false";
    public static boolean zombieHorseHalloween = false;
    public static boolean doZombieHorse = false;

    @Rule(
            desc = "Allows to stack multiple protection types",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean protectionStacking = false;
    @LootTableRule(overrideLoottables = "entities/illusioner.json")
    @Rule(
            desc = "Portals in RedCraft2 area will link to redcraft2 dimensions",
            appSource = "redcraft",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean redcraft = false;
    @Rule(
            desc = "Allows to use bonemeal on Spore blossoms",
            appSource = "renewablesporeblossom",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean renewableSporeblossom = false;
}
