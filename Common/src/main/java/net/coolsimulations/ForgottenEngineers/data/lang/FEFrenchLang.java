package net.coolsimulations.ForgottenEngineers.data.lang;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.item.FEItems;
import net.minecraft.world.item.Item;

import java.util.function.BiConsumer;

public class FEFrenchLang {

    public static String ENGINEERING = "Ingénierie";

    public static String MOD_NAME = getPairing(ENGINEERING, "Oubliée");

    public static String WISDOM = "Sagesse de";

    public static void generateItems(BiConsumer<Item, String> items) {
        items.accept(FEItems.RESTORATION_WISDOM, getPairing(WISDOM, "la Restauration"));
        //items.accept(FEItems.DISTRIBUTION_WISDOM, getPairing(WISDOM.replace("de", "du"), "Tri"));
        //items.accept(FEItems.COMPRESSION_WISDOM, getPairing(WISDOM, "Compression"));
        //items.accept(FEItems.INDUCTION_WISDOM, getPairing(WISDOM, "l'Induction"));

        items.accept(FEItems.RESTORER, "Restaurateur");
        //items.accept(FEItems.ROUTER, "Trieur");
        //items.accept(FEItems.COMPRESSOR, "Compresseur");
        //items.accept(FEItems.INDUCTION_FURNACE, "Four à Induction");
    }

    public static void generateCustom(BiConsumer<String, String> custom) {
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom", getPairing(WISDOM, "l'" + ENGINEERING));
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom.discovers", "Découvertes:");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".restorer.empty.description", "Peut contenir une pile de différents matériaux de réparation");
    }

    public static String getPairing(String name, String type) {
        return name + " " + type;
    }
}
