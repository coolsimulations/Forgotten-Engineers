package net.coolsimulations.ForgottenEngineers.data.lang;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.data.FETags;
import net.coolsimulations.ForgottenEngineers.item.FEItems;
import net.coolsimulations.ForgottenEngineers.sounds.FESounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.function.BiConsumer;

public class FEJapaneseLang {

    public static String MOD_NAME = "忘れ去られし工学";

    public static String RESTORATION = "修復";
    public static String DISTRIBUTION = "仕分け";
    public static String WISDOM = "叡智";

    public static String RESTORER = "物品修復器";
    public static String ROUTER = "携帯仕分け器";

    public static String DISCOVERS = "発見";
    public static String EMPTIES = "が空になる";

    public static void generateItems(BiConsumer<Item, String> items) {
        items.accept(FEItems.RESTORATION_WISDOM, getJapanesePairing(RESTORATION, WISDOM));
        items.accept(FEItems.DISTRIBUTION_WISDOM, getJapanesePairing(DISTRIBUTION, WISDOM));
        //items.accept(FEItems.COMPRESSION_WISDOM, getJapanesePairing("圧縮", WISDOM));
        //items.accept(FEItems.INDUCTION_WISDOM, getJapanesePairing("誘導加熱", WISDOM));

        items.accept(FEItems.RESTORER, RESTORER);
        items.accept(FEItems.ROUTER, ROUTER);
        //items.accept(FEItems.COMPRESSOR, "携帯圧縮器");
        //items.accept(FEItems.INDUCTION_FURNACE, getPairing("携帯誘導", "炉"));
    }

    public static void generateItemTags(BiConsumer<TagKey<Item>, String> tags) {
        tags.accept(FETags.PAPERS, "紙");
        tags.accept(FETags.RESTORATION_WISDOM_DISCOVERS, getJapanesePairing(getPairing(RESTORATION, WISDOM), DISCOVERS));
        tags.accept(FETags.DISTRIBUTION_WISDOM_DISCOVERS, getJapanesePairing(getPairing(DISTRIBUTION, WISDOM), DISCOVERS));
        tags.accept(FETags.RESTORER_IGNORE_MATERIALS, getPairing(RESTORER, "は材料を無視します"));
        tags.accept(FETags.RESTORER_IGNORE_TOOLS, getPairing(RESTORER, "は工具を無視します"));
        tags.accept(FETags.ROUTER_IGNORE_ITEMS, getPairing(ROUTER, "はアイテムを無視します"));
    }

    public static void generateSounds(BiConsumer<SoundEvent, String> sounds) {
        sounds.accept(FESounds.RESTORER_DROP_CONTENTS, getPairing(RESTORER, EMPTIES, ""));
        sounds.accept(FESounds.ROUTER_DROP_CONTENTS, getPairing(ROUTER, EMPTIES, ""));
    }

    public static void generateCustom(BiConsumer<String, String> custom) {
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom", getPairing("工学", WISDOM));
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom.discovers", getPairing(DISCOVERS, "：", ""));
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".restorer.empty.description", "複数種類の修理材料を1スタック分まで一緒にしまうことができます");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".router.empty.description", "複数種類の絞り込むアイテムを1スタック分まで一緒にしまうことができます");
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
