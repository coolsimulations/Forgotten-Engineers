package net.coolsimulations.ForgottenEngineers.data.lang;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.item.FEItems;
import net.minecraft.world.item.Item;

import java.util.function.BiConsumer;

public class FEGermanLang {

    public static String ENGINEERING = "Ingenieurkunst";

    public static String MOD_NAME = getPairing("Vergessene", ENGINEERING);

    public static String WISDOM = "Weisheit der";

    public static void generateItems(BiConsumer<Item, String> items) {
        items.accept(FEItems.RESTORATION_WISDOM, getPairing(WISDOM, "Restauration"));
        //items.accept(FEItems.DISTRIBUTION_WISDOM, getPairing(WISDOM, "Sortierung"));
        //items.accept(FEItems.COMPRESSION_WISDOM, getPairing(WISDOM, "Kompression"));
        //items.accept(FEItems.INDUCTION_WISDOM, getPairing(WISDOM, "Induktion"));

        items.accept(FEItems.RESTORER, "Restaurator");
        //items.accept(FEItems.ROUTER, "Sortierer");
        //items.accept(FEItems.COMPRESSOR, "Kompressor");
        //items.accept(FEItems.INDUCTION_FURNACE, "Induktionsofen");
    }

    public static void generateCustom(BiConsumer<String, String> custom) {
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom", getPairing(WISDOM, ENGINEERING));
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom.discovers", "Entdeckungen:");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".restorer.empty.description", "Nimmt einen gemischten Stapel Reparaturmaterialien auf");
    }

    public static String getPairing(String name, String type) {
        return name + " " + type;
    }
}
