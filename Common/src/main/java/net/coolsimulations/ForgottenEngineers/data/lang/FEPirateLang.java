package net.coolsimulations.ForgottenEngineers.data.lang;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.item.FEItems;
import net.minecraft.world.item.Item;

import java.util.function.BiConsumer;

public class FEPirateLang {

    public static String MOD_NAME = "Lost Tinkers o' the Deep";

    public static String WISDOM = "Savvy";

    public static void generateItems(BiConsumer<Item, String> items) {
        items.accept(FEItems.RESTORATION_WISDOM, getPairing("Renewal", WISDOM));
        //items.accept(FEItems.DISTRIBUTION_WISDOM, getPairing("Share of the Booty", WISDOM));
        //items.accept(FEItems.COMPRESSION_WISDOM, getPairing("Squashin'", WISDOM));
        //items.accept(FEItems.INDUCTION_WISDOM, getPairing("Blastin' Heat", WISDOM));

        items.accept(FEItems.RESTORER, "Patch-Kit");
        //items.accept(FEItems.ROUTER, "Divvying Rod");
        //items.accept(FEItems.COMPRESSOR, "Booty Squeezer");
        //items.accept(FEItems.INDUCTION_FURNACE, "Hell-Fire Cooker");
    }

    public static void generateCustom(BiConsumer<String, String> custom) {
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom", getPairing("Tinkerin'", WISDOM));
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom.discovers", "Unearths:");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".restorer.empty.description", "Holds mixed amounts o' repair materials");
    }

    public static String getPairing(String name, String type) {
        return name + " " + type;
    }
}
