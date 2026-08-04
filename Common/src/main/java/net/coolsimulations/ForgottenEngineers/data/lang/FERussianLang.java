package net.coolsimulations.ForgottenEngineers.data.lang;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.item.FEItems;
import net.minecraft.world.item.Item;

import java.util.function.BiConsumer;

public class FERussianLang {

    public static String MOD_NAME = "Забытая инженерия";

    public static String WISDOM = "Мудрость";

   public static String INDUCTION = "индукции";

    public static void generateItems(BiConsumer<Item, String> items) {
        items.accept(FEItems.RESTORATION_WISDOM, getPairing(WISDOM, "восстановления"));
        //items.accept(FEItems.DISTRIBUTION_WISDOM, getPairing(WISDOM, "распределения"));
        //items.accept(FEItems.COMPRESSION_WISDOM, getPairing(WISDOM, "сжатия"));
        //items.accept(FEItems.INDUCTION_WISDOM, getPairing(WISDOM, INDUCTION));

        items.accept(FEItems.RESTORER, "Реставратор");
        //items.accept(FEItems.ROUTER, "Сортировщик");
        //items.accept(FEItems.COMPRESSOR, "Компрессор");
        //items.accept(FEItems.INDUCTION_FURNACE, "Индукционная печь");
    }

    public static void generateCustom(BiConsumer<String, String> custom) {
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom", getPairing(WISDOM, "инженера"));
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom.discovers", "Открытия:");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".restorer.empty.description", "Вмещает стопку разных материалов для ремонта");
    }

    public static String getPairing(String name, String type) {
        return name + " " + type;
    }
}
