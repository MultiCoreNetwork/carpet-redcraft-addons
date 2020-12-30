package it.multicoredev.carpetredcraftaddons;

import carpet.settings.Rule;

import static carpet.settings.RuleCategory.*;

public class CarpetRedCraftSettings {
    public static final String REDCRAFT = "redcraft";
    @Rule(
            desc = "Script placeable",
            appSource = "placeable",
            category = {SURVIVAL, SCARPET, FEATURE, REDCRAFT}
    )
    public static boolean placeableScript = false;
}
