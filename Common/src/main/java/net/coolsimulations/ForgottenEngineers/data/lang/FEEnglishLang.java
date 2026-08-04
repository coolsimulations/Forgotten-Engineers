package net.coolsimulations.ForgottenEngineers.data.lang;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.item.FEItems;
import net.minecraft.world.item.Item;

import java.util.function.BiConsumer;

public class FEEnglishLang {

    public static String MOD_NAME = ForgottenEngineersCommon.MOD_NAME;

    public static String WISDOM = "Wisdom";

    public static String INDUCTION = "Induction";

    public static void generateItems(BiConsumer<Item, String> items) {
        items.accept(FEItems.RESTORATION_WISDOM, getPairing("Restoration", WISDOM));
        //items.accept(FEItems.DISTRIBUTION_WISDOM, getPairing("Distribution", WISDOM));
        //items.accept(FEItems.COMPRESSION_WISDOM, getPairing("Compression", WISDOM));
        //items.accept(FEItems.INDUCTION_WISDOM, getPairing(INDUCTION, WISDOM));

        items.accept(FEItems.RESTORER, "Restorer");
        //items.accept(FEItems.ROUTER, "Router");
        //items.accept(FEItems.COMPRESSOR, "Compressor");
        //items.accept(FEItems.INDUCTION_FURNACE, getPairing(INDUCTION, "Furnace"));
    }

    public static void generateCustom(BiConsumer<String, String> custom) {
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom", getPairing("Engineering", WISDOM));
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom.discovers", "Discovers:");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".restorer.empty.description", "Can hold a mixed stack of repair materials");
    }

    public static String getPairing(String name, String type) {
        return name + " " + type;
    }
}
