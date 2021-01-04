package it.multicoredev.carpetredcraftaddons;

import carpet.settings.Rule;
import net.minecraft.screen.GrindstoneScreenHandler;

import static carpet.settings.RuleCategory.*;

public class CarpetRedCraftSettings {
    public static final String REDCRAFT = "redcraft";

    @Rule(
            desc = "Allows to place plants on all blocks with flat top surface.",
            appSource = "placeable",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean placeable = true;

    @Rule(
            desc = "Allows to mine an entire tree mining a single block.",
            appSource = "treecapitator",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean treecapitator = true;

    @Rule(
            desc = "Allows to leash a rope beetween two fences.",
            appSource = "ropes",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean ropes = true;

    @Rule(
            desc = "Allows to right click a compass to make it point towards the last death point.",
            appSource = "lastdeathcompass",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean lastDeathCompass = true;

    @Rule(
            desc = "Allows to place ladders under other ladders.",
            appSource = "floatingladders",
            category = {SURVIVAL, FEATURE, REDCRAFT}
    )
    public static boolean floatingLadders = true;

    @Rule(
            desc = "Allows to leash villagers.",
            appSource = "villagerleash",
            category = {SURVIVAL, SCARPET, FEATURE, REDCRAFT}
    )
    public static boolean villagerLeash = true;

    @Rule(
            desc = "Allows to place saddles on the ground.",
            appSource = "sitanywhere",
            category = {SURVIVAL, SCARPET, FEATURE, REDCRAFT}
    )
    public static boolean sitAnywhere = true;


   /* @Rule(
            desc = "Allows to have access to the /timebar command.",
            //appSource = "timebar",
            category = {SURVIVAL, SCARPET, FEATURE, REDCRAFT}
    )
    public static boolean timebar = true;*/
}
