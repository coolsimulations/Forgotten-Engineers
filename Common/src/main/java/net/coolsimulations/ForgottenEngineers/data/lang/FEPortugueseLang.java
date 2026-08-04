package net.coolsimulations.ForgottenEngineers.data.lang;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.item.FEItems;
import net.minecraft.world.item.Item;

import java.util.function.BiConsumer;

public class FEPortugueseLang {

    public static String MOD_NAME = "Engenharia Esquecida";

    public static String WISDOM = "Sabedoria de";

    public static String INDUCTION = "Indução";

    public static void generateItems(BiConsumer<Item, String> items) {
        items.accept(FEItems.RESTORATION_WISDOM, getPairing(WISDOM, "Restauração"));
        //items.accept(FEItems.DISTRIBUTION_WISDOM, getPairing(WISDOM, "Classificação"));
        //items.accept(FEItems.COMPRESSION_WISDOM, getPairing(WISDOM, "Compressão"));
        //items.accept(FEItems.INDUCTION_WISDOM, getPairing(WISDOM, INDUCTION));

        items.accept(FEItems.RESTORER, "Restaurador");
        //items.accept(FEItems.ROUTER, "Classificador");
        //items.accept(FEItems.COMPRESSOR, "Compressor");
        //items.accept(FEItems.INDUCTION_FURNACE, getPairing("Forno de", INDUCTION));
    }

    public static void generateCustom(BiConsumer<String, String> custom) {
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom", getPairing(WISDOM, "Engenharia"));
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom.discovers", "Descobertas:");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".restorer.empty.description", "Pode carregar um conjunto diverso de materiais de reparo");
    }

    public static String getPairing(String name, String type) {
        return name + " " + type;
    }
}
