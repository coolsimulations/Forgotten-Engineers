package net.coolsimulations.ForgottenEngineers.data.lang;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.item.FEItems;
import net.minecraft.world.item.Item;

import java.util.function.BiConsumer;

public class FELOLCatLang {

    public static String MOD_NAME = "Dez inginears we fogot, soz";

    public static String WISDOM = "Brainz";

    public static void generateItems(BiConsumer<Item, String> items) {
        items.accept(FEItems.RESTORATION_WISDOM, getPairing("Fix dat", WISDOM));
        //items.accept(FEItems.DISTRIBUTION_WISDOM, getPairing("Dis all mine", WISDOM));
        //items.accept(FEItems.COMPRESSION_WISDOM, getPairing("Da crunch", WISDOM));
        //items.accept(FEItems.INDUCTION_WISDOM, getPairing("Burnee", WISDOM));

        items.accept(FEItems.RESTORER, "Fix dat");
        //items.accept(FEItems.ROUTER, "Go in box");
        //items.accept(FEItems.COMPRESSOR, "Ooo squishy");
        //items.accept(FEItems.INDUCTION_FURNACE, "Burnee thing");
    }

    public static void generateCustom(BiConsumer<String, String> custom) {
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom", getPairing("Big", WISDOM));
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom.discovers", "Makz dez:");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".restorer.empty.description", "Kan hold a mikz stacc of fixin thingz :o");
    }

    public static String getPairing(String name, String type) {
        return name + " " + type;
    }
}
