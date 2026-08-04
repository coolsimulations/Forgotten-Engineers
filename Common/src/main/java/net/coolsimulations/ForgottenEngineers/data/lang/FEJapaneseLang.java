package net.coolsimulations.ForgottenEngineers.data.lang;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.item.FEItems;
import net.minecraft.world.item.Item;

import java.util.function.BiConsumer;

public class FEJapaneseLang {

    public static String MOD_NAME = "忘れ去られし工学";

    public static String WISDOM = "叡智";

    public static void generateItems(BiConsumer<Item, String> items) {
        items.accept(FEItems.RESTORATION_WISDOM, getJapanesePairing("修復", WISDOM));
        //items.accept(FEItems.DISTRIBUTION_WISDOM, getJapanesePairing("仕分け", WISDOM));
        //items.accept(FEItems.COMPRESSION_WISDOM, getJapanesePairing("圧縮", WISDOM));
        //items.accept(FEItems.INDUCTION_WISDOM, getJapanesePairing("誘導加熱", WISDOM));

        items.accept(FEItems.RESTORER, "物品修復器");
        //items.accept(FEItems.ROUTER, "携帯仕分け器");
        //items.accept(FEItems.COMPRESSOR, "携帯圧縮器");
        //items.accept(FEItems.INDUCTION_FURNACE, getPairing("携帯誘導", "炉"));
    }

    public static void generateCustom(BiConsumer<String, String> custom) {
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom", getPairing("工学", WISDOM));
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom.discovers", "発見：");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".restorer.empty.description", "複数種類の修理材料を1スタック分まで一緒にしまうことができます");
    }

    public static String getPairing(String name, String type, String joiner) {
        return name + joiner + type;
    }

    public static String getPairing(String name, String type) {
        return getPairing(name, type, " ");
    }

    public static String getJapanesePairing(String name, String type) {
        return getPairing(name, type, "の");
    }
}
