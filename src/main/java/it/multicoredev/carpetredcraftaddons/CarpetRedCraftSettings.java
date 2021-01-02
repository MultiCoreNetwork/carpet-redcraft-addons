package it.multicoredev.carpetredcraftaddons;

import carpet.settings.Rule;

import static carpet.settings.RuleCategory.*;

public class CarpetRedCraftSettings {
    public static final String REDCRAFT = "redcraft";

    @Rule(
            desc = "Allows to place plants on all blocks with flat top surface.",
            appSource = "placeable",
            category = {SURVIVAL, SCARPET, FEATURE, REDCRAFT}
    )
    public static boolean placeable = true;

    @Rule(
            desc = "Allows to have access to the /timebar command.",
            //appSource = "timebar",
            category = {SURVIVAL, SCARPET, FEATURE, REDCRAFT}
    )
    public static boolean timebar = true;

    @Rule(
            desc = "Allows to mine an entire tree mining a single block.",
            appSource = "treecapitator",
            category = {SURVIVAL, SCARPET, FEATURE, REDCRAFT}
    )
    public static boolean treecapitator = true;
}
