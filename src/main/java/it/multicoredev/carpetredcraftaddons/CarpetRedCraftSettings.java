package it.multicoredev.carpetredcraftaddons;

import carpet.settings.Condition;
import carpet.settings.Rule;
import net.fabricmc.loader.api.FabricLoader;

import static carpet.settings.RuleCategory.*;

public class CarpetRedCraftSettings {
    public static final String REDCRAFT = "redcraft";
    public static final String CRAFTING = "crafting";
    public static final String DATAPACK = "datapack";

    @Rule(
            desc = "Portals in RedCraft2 area will link to redcraft2 dimensions",
            appSource = "redcraft",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean redcraft = false;

    @Rule(
            desc = "Sponges work on lava too, but they will burn",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean lavaSponges = false;

    // ------------------------------------------------ //

    @Rule(
            desc = "Deopped players can do /player on themselves",
            extra = {
                    "and you can create Fake Player on themselves",
                    "If commandPlayer is set to False you can't create Fake Player on themselves"},
            category = {EXPERIMENTAL, FEATURE, REDCRAFT, COMMAND},
            appSource = "playerme"
    )
    public static boolean commandPlayerMe = true;

    @Rule(
            desc = "Disable Command Feedback for OPs level 1",
            category = {FEATURE, REDCRAFT}
    )
    public static boolean disableOPsCommandFeedback = true;

    @Rule(
            desc = "Allows to place plants on all blocks with flat top surface",
            appSource = "placeableplants",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean placeablePlants = true;

    @Rule(
            desc = "Allows to mine an entire tree mining a single block if using a TreeCapitator axe",
            appSource = "treecapitator",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean treecapitator = true;

    @Rule(
            desc = "Allows to leash a rope beetween two fences",
            appSource = "ropes",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean ropes = true;

    @Rule(
            desc = "Allows to right click a compass to make it point towards the last death point",
            appSource = "lastdeathcompass",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean lastDeathCompass = true;

    @Rule(
            desc = "Allows to place ladders under other ladders",
            appSource = "floatingladders",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean floatingLadders = true;

    @Rule(
            desc = "Allows to leash villagers",
            appSource = "villagerleash",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean villagerLeash = true;

    @Rule(
            desc = "Allows to place saddles on the ground",
            appSource = "sitanywhere",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean sitAnywhere = true;

    @Rule(
            desc = "Ender Dragon always spawns a new dragon egg",
            appSource = "dragoneggrespawns",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean dragonEggRespawns = true;

    @Rule(
            desc = "Create personal graves when the player dies",
            appSource = "graves",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean graves = true;

    @Rule(
            desc = "Allows to mine a block keeping the blockstate if using a Silky Blocstate tool",
            appSource = "silkyblockstates",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean silkyBlockstates = true;

    @Rule(
            desc = "Right clicking with a Phantom Membrane or Glass Pane on an Item Frame will make it invisible or fixed",
            appSource = "betteritemframes",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean betterItemFrames = true;

    @Rule(
            desc = "Right clicking while sneaking on a horse/donkey/mule, will display the stats of the mob in the chat",
            appSource = "horsestats",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean horseStats = true;

    @Rule(
            desc = "Allows to dye Shulkers",
            appSource = "colorableshulkers",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean colorableShulkers = true;

    @Rule(
            desc = "Allows to have access to the /stats command",
            appSource = "stats",
            category = {SURVIVAL, FEATURE, REDCRAFT, COMMAND}
    )
    public static boolean commandStats = true;

    @Rule(
            desc = "Allows to have access to the /timebar command",
            appSource = "timebar",
            category = {SURVIVAL, FEATURE, REDCRAFT, COMMAND}
    )
    public static boolean commandTimebar = true;

    @Rule(
            desc = "Adds many special Name Tags",
            extra = "'freeze', 'silent', 'invulnerable', 'nogravity', 'baby' ... ",
            appSource = "specialnametags",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean specialNameTags = true;

    @Rule(
            desc = "Allows to have access to the /waystone command",
            appSource = "waystone",
            category = {SURVIVAL, FEATURE, REDCRAFT, COMMAND}
    )
    public static boolean commandWaystone = true;

    @Rule(
            desc = "Allows to have access to the /skull command",
            appSource = "skull",
            category = {SURVIVAL, FEATURE, REDCRAFT, COMMAND}
    )
    public static boolean commandSkull = true;

    @Rule(
            desc = "Allows to prune plants with shears",
            appSource = "prunedplants",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean prunedPlants = true;

    @Rule(
            desc = "Adds more trades to the Wandering Trader",
            extra = "Mini Blocks, TreeCapitator Axes and Silky Blockstate tools",
            appSource = "morewanderingtrades",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean moreWanderingTrades = true;

    @Rule(
            desc = "Allow to revive dead coral with water bottle",
            appSource = "revivecoral",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean reviveCoral = true;

    @Rule(
            desc = "Allow you to locate player",
            appSource = "locateplayer",
            category = {SURVIVAL, FEATURE, REDCRAFT, COMMAND}
    )
    public static boolean commandLocatePlayer = true;

    @Rule(
            desc = "Allow you to transform concrete to concrete powder filling a glass bottle",
            appSource = "crumbleconcrete",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean crumbleConcrete = true;


    @Rule(
            desc = "Adds the /lookme command to rotate entities",
            appSource = "lookme",
            category = {SURVIVAL, FEATURE, REDCRAFT, COMMAND}
    )
    public static boolean commandLookme = true;

    @Rule(
            desc = "Allows ArmorStand editing with Hoe",
            appSource = "armorstandeditor",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean armorStandEditor = true;

    @Rule(
            desc = "Allows to tie pillager with empty hands",
            appSource = "pillagerleash",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean pillagerLeash = true;

    // ------------------------------------------------ //

    @Rule(
            desc = "Drop items to Armor Stands to apply them some proprieties",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING, DATAPACK}
    )
    public static boolean betterArmorStands = true;

    public static class craftableCobwebsCondition implements Condition {
        @Override
        public boolean isTrue() {
            return !FabricLoader.getInstance().isModLoaded("rug");
        }
    }

    @Rule(
            desc = "Allows to craft Cobwebs",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING, DATAPACK},
            condition = craftableCobwebsCondition.class
    )
    public static boolean craftableCobwebs = true;

    @Rule(
            desc = "Allows to use colored blocks to craft other color variants",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING, DATAPACK}
    )
    public static boolean craftableColoredBlocks = true;

    @Rule(
            desc = "Allows to craft Coral Blocks with 4 Coral Fans and Corals",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING, DATAPACK}
    )
    public static boolean craftableCorals = true;

    @Rule(
            desc = "Allows to smelt saplings to get Dead Bushes",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING, DATAPACK}
    )
    public static boolean craftableDeadBushes = true;

    @Rule(
            desc = "Allows to craft Elytras",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING, DATAPACK}
    )
    public static boolean craftableElytra = true;

    @Rule(
            desc = "Allows to craft Ice from Packed Ice",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING, DATAPACK}
    )
    public static boolean craftableIce = true;

    @Rule(
            desc = "Allows to craft Large Ferns",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING, DATAPACK}
    )
    public static boolean craftableLargeFern = true;

    @Rule(
            desc = "Allows to craft Nether Warts from Nether Warts Block",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING, DATAPACK}
    )
    public static boolean craftableNetherWarts = false;

    @Rule(
            desc = "Allows to craft Packed Ice from Blue Ice",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING, DATAPACK}
    )
    public static boolean craftablePackedIce = true;

    @Rule(
            desc = "Allows to craft Player Head from any kind of head",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING, DATAPACK}
    )
    public static boolean craftablePlayerHead = true;

    @Rule(
            desc = "Allows to craft Quartz from any kind of Quartz Block",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING, DATAPACK}
    )
    public static boolean craftableQuartz = true;

    @Rule(
            desc = "Allows to craft Shulker Shells from Turle Shells and Popped Chorus Fruits",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING, DATAPACK}
    )
    public static boolean craftableShulkerShells = true;

    @Rule(
            desc = "Allows to craft Stripped Woods/Hyphaes from Logs/Steams",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING, DATAPACK}
    )
    public static boolean craftableStrippedWood = true;

    @Rule(
            desc = "Allows to craft Tall Grass",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING, DATAPACK}
    )
    public static boolean craftableTallGrass = true;

    @Rule(
            desc = "Allows to craft Tuff and Calite",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING, DATAPACK}
    )
    public static boolean craftableTuffAndCalcite = true;

    @Rule(
            desc = "Reduced the blocks that an Enderman can hold",
            extra = "Podzol, Pumpkins, Melons and Mycelium",
            category = {SURVIVAL, FEATURE, REDCRAFT, DATAPACK}
    )
    public static boolean endermanNoGrief = true;

    @Rule(
            desc = "Adds crafting compatibility with all types of Quartz Block",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING, DATAPACK}
    )
    public static boolean quartzCraftingCompatibility = true;

    @Rule(
            desc = "Adds stonecutter craftings for all types of Quartz Block",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING, DATAPACK}
    )
    public static boolean stonecutterQuartz = true;

    @Rule(
            desc = "Adds crafting compatibility with all types of Red Sandstone",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING, DATAPACK}
    )
    public static boolean redSandstoneCraftingCompatibility = true;

    @Rule(
            desc = "Adds stonecutter craftings for all types of Red Sandstone",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING, DATAPACK}
    )
    public static boolean stonecutterRedSandstone = true;

    @Rule(
            desc = "Allows to craft Red Sand from Red Sandstone",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING, DATAPACK}
    )
    public static boolean craftableRedSand = true;

    @Rule(
            desc = "Adds crafting compatibility with all types of Sandstone",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING, DATAPACK}
    )
    public static boolean sandstoneCraftingCompatibility = true;

    @Rule(
            desc = "Adds stonecutter craftings for all types of Sandstone",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING, DATAPACK}
    )
    public static boolean stonecutterSandstone = true;

    @Rule(
            desc = "Allows to craft Red Sand from Sandstone",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING, DATAPACK}
    )
    public static boolean craftableSand = true;

    @Rule(
            desc = "Allows to use 2 slabs to craft the block back",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING, DATAPACK}
    )
    public static boolean slabToBlockCrafing = true;

    @Rule(
            desc = "Adds stonecutter craftings for all variants of Stone",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING, DATAPACK}
    )
    public static boolean stonecutterStone = false;

    @Rule(
            desc = "Adds stonecutter craftings for all types of Wooden Block",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING, DATAPACK}
    )
    public static boolean stonecutterWood = true;

    @Rule(
            desc = "Adds reverse crafting of Prismarine, Prismarine Bricks, Bricks and Nether Bricks",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING, DATAPACK}
    )
    public static boolean craftableBricks = true;

    @Rule(
            desc = "Adds fast crafting for Dispenser and Repeater",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING, DATAPACK}
    )
    public static boolean fastRedstoneCrafting = true;

    @Rule(
            desc = "Husks drop sand on death",
            category = {SURVIVAL, FEATURE, REDCRAFT, DATAPACK}
    )
    public static boolean huskDropsSand = true;

    @Rule(
            desc = "Allow to craft Sculk Sensor with endeperals and observers",
            category = {SURVIVAL, FEATURE, REDCRAFT, CRAFTING, DATAPACK}
    )
    public static boolean craftableSculkSensor = true;

    @Rule(
            desc = "You can craft deepslate ore in smithing table",
            category = {SURVIVAL, FEATURE, REDCRAFT, DATAPACK}
    )
    public static boolean craftableDeepslateOres = true;

    @Rule(
            desc = "Allows to craft light block",
            appSource = "light",
            category = {SURVIVAL, FEATURE, REDCRAFT, DATAPACK}
    )
    public static boolean craftableLight = true;

    // ------------------------------------------------ //

    public static final String EXTRA = "extras";
    public static final String ADDONS = "carpetaddons";
    @Rule(
            desc = "Client can provide alternative block placement.",
            extra = {"From Gnembon's Carpet Extra"},
            category = {EXTRA, SURVIVAL}
    )
    public static boolean accurateBlockPlacement = true;

    @Rule(
            desc = "Fixes updates suppression causing server crashes.",
            extra = {"From Gnembon's Carpet Extra"},
            category = {BUGFIX, EXTRA}
    )
    public static boolean updateSuppressionCrashFix = true;

    @Rule(
            desc = "Fixes block states in F3 debug mode not updating for some blocks.",
            category = {EXTRA, EXPERIMENTAL},
            extra = {"May cause increased network traffic.", "Works with cactus, sugar cane, saplings, hoppers, dispensers and droppers.", "From Gnembon's Carpet Extra" }
    )
    public static boolean blockStateSyncing = true;

    @Rule(
            desc = "Places the mined block in the player inventory when sneaking",
            category = {SURVIVAL, FEATURE, EXPERIMENTAL, ADDONS}
    )
    public static boolean carefulBreak = true;
}
