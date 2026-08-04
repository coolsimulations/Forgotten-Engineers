package net.coolsimulations.ForgottenEngineers.data.lang;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.item.FEItems;
import net.minecraft.world.item.Item;

import java.util.function.BiConsumer;

public class FESpanishLang {

    public static String MOD_NAME = "Ingeniería olvidada";

    public static String WISDOM = "Sabiduría de";

    public static String INDUCTION = "inducción";

    public static void generateItems(BiConsumer<Item, String> items) {
        items.accept(FEItems.RESTORATION_WISDOM, getPairing(WISDOM, "restauradora"));
        //items.accept(FEItems.DISTRIBUTION_WISDOM, getPairing(WISDOM, "clasificación"));
        //items.accept(FEItems.COMPRESSION_WISDOM, getPairing(WISDOM, "compresión"));
        //items.accept(FEItems.INDUCTION_WISDOM, getPairing(WISDOM, INDUCTION));

        items.accept(FEItems.RESTORER, "Restaurador");
        //items.accept(FEItems.ROUTER, "Clasificador");
        //items.accept(FEItems.COMPRESSOR, "Compresor");
        //items.accept(FEItems.INDUCTION_FURNACE, getPairing("Horno de", INDUCTION));
    }

    public static void generateCustom(BiConsumer<String, String> custom) {
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom", getPairing(WISDOM, "ingeniería"));
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom.discovers", "Descubres:");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".restorer.empty.description", "Puede contener una pila mixta de materiales de reparación");
    }

    public static String getPairing(String name, String type) {
        return name + " " + type;
    }
}
