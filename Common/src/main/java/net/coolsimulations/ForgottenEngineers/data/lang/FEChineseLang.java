package net.coolsimulations.ForgottenEngineers.data.lang;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.data.FETags;
import net.coolsimulations.ForgottenEngineers.item.FEItems;
import net.coolsimulations.ForgottenEngineers.sounds.FESounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.function.BiConsumer;

public class FEChineseLang {

    public static String MOD_NAME = "失落的工程";

    public static String RESTORATION = "修复";
    public static String DISTRIBUTION = "分拣";
    public static String WISDOM = "之智";

    public static String RESTORER = "物品修复器";
    public static String ROUTER = "便携分类器";

    public static String DISCOVERS_ITEMS = "已发现物品";
    public static String DISCOVERS = "发现";
    public static String EMPTIES = "：倒空";

    public static void generateItems(BiConsumer<Item, String> items) {
        items.accept(FEItems.RESTORATION_WISDOM, getPairing(RESTORATION, WISDOM));
        items.accept(FEItems.DISTRIBUTION_WISDOM, getPairing(DISTRIBUTION, WISDOM));
        //items.accept(FEItems.COMPRESSION_WISDOM, getPairing("压缩", WISDOM));
        //items.accept(FEItems.INDUCTION_WISDOM, getPairing("感应加热", WISDOM));

        items.accept(FEItems.RESTORER, RESTORER);
        items.accept(FEItems.ROUTER, ROUTER);
        //items.accept(FEItems.COMPRESSOR, "便携压缩器");
        //items.accept(FEItems.INDUCTION_FURNACE, getPairing("便携感应", "炉"));
    }

    public static void generateItemTags(BiConsumer<TagKey<Item>, String> tags) {
        tags.accept(FETags.PAPERS, "纸");
        tags.accept(FETags.RESTORATION_WISDOM_DISCOVERS, getPairing(getPairing(RESTORATION, WISDOM), DISCOVERS));
        tags.accept(FETags.DISTRIBUTION_WISDOM_DISCOVERS, getPairing(getPairing(DISTRIBUTION, WISDOM), DISCOVERS));
        tags.accept(FETags.RESTORER_IGNORE_MATERIALS, getPairing(RESTORER, "忽略材料"));
        tags.accept(FETags.RESTORER_IGNORE_TOOLS, getPairing(RESTORER, "忽略工具"));
        tags.accept(FETags.ROUTER_IGNORE_ITEMS, getPairing(ROUTER, "忽略物品"));
    }

    public static void generateSounds(BiConsumer<SoundEvent, String> sounds) {
        sounds.accept(FESounds.RESTORER_DROP_CONTENTS, getPairing(RESTORER, EMPTIES));
        sounds.accept(FESounds.ROUTER_DROP_CONTENTS, getPairing(ROUTER, EMPTIES));
    }

    public static void generateCustom(BiConsumer<String, String> custom) {
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom", getPairing("工程", WISDOM));
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom.discovers", DISCOVERS_ITEMS + "：");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".restorer.empty.description", "可容纳一组混合的维修材料");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".router.empty.description", "可容纳一组混合的筛选物品");
    }

    public static String getPairing(String name, String type, String joiner) {
        return name + joiner + type;
    }

    public static String getPairing(String name, String type) {
        return getPairing(name, type, "");
    }
}
