package net.coolsimulations.ForgottenEngineers.data.lang;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.item.FEItems;
import net.minecraft.world.item.Item;

import java.util.function.BiConsumer;

public class FEChineseLang {

    public static String MOD_NAME = "失落的工程";

    public static String WISDOM = "之智";

    public static void generateItems(BiConsumer<Item, String> items) {
        items.accept(FEItems.RESTORATION_WISDOM, getPairing("修复", WISDOM));
        //items.accept(FEItems.DISTRIBUTION_WISDOM, getPairing("分拣", WISDOM));
        //items.accept(FEItems.COMPRESSION_WISDOM, getPairing("压缩", WISDOM));
        //items.accept(FEItems.INDUCTION_WISDOM, getPairing("感应加热", WISDOM));

        items.accept(FEItems.RESTORER, "物品修复器");
        //items.accept(FEItems.ROUTER, "便携分类器");
        //items.accept(FEItems.COMPRESSOR, "便携压缩器");
        //items.accept(FEItems.INDUCTION_FURNACE, getPairing("便携感应", "炉"));
    }

    public static void generateCustom(BiConsumer<String, String> custom) {
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom", getPairing("工程", WISDOM));
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom.discovers", "已发现物品：");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".restorer.empty.description", "可容纳一组混合的维修材料");
    }

    public static String getPairing(String name, String type, String joiner) {
        return name + joiner + type;
    }

    public static String getPairing(String name, String type) {
        return getPairing(name, type, "");
    }
}
