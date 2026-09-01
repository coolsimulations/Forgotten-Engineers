package net.coolsimulations.ForgottenEngineers.data.lang;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.data.FETags;
import net.coolsimulations.ForgottenEngineers.item.FEItems;
import net.coolsimulations.ForgottenEngineers.item.ForgottenEngineersItems;
import net.coolsimulations.ForgottenEngineers.sounds.FESounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ColorCollection;

import java.util.function.BiConsumer;

public class FEJapaneseLang {

    public static String MOD_NAME = "忘れ去られし工学";

    public static String RESTORATION = "修復";
    public static String DISTRIBUTION = "仕分け";
    public static String COMPRESSION = "圧縮";
    public static String UNIVERSAL = "普遍的";
    public static String WISDOM = "叡智";
    public static String ENDER = "エンダー";

    public static String RESTORER = "物品修復器";
    public static String ROUTER = "携帯仕分け器";
    public static String COMPRESSOR = "携帯圧縮器";
    public static String INDUCTION = "誘導加熱";
    public static String MENDER = " 携帯修繕器";
    public static String STRIPPER = "携帯皮むき器";
    public static String COMBUSTOR = "燃焼カノン砲";

    public static String DISCOVERS = "発見";
    public static String EMPTIES = "が空になる";

    public static void generateItems(BiConsumer<Item, String> items) {
        items.accept(FEItems.RESTORATION_WISDOM, getJapanesePairing(RESTORATION, WISDOM));
        items.accept(FEItems.DISTRIBUTION_WISDOM, getJapanesePairing(DISTRIBUTION, WISDOM));
        items.accept(FEItems.COMPRESSION_WISDOM, getJapanesePairing(COMPRESSION, WISDOM));
        items.accept(FEItems.INDUCTION_WISDOM, getJapanesePairing(INDUCTION, WISDOM));
        items.accept(FEItems.ENGINEERS_SEAL, getJapanesePairing("技術者", "印章"));
        items.accept(FEItems.UNIVERSAL_WISDOM, getPairing(UNIVERSAL, WISDOM));

        items.accept(FEItems.RESTORER, RESTORER);
        items.accept(FEItems.ROUTER, ROUTER);
        ColorCollection.zipApply(ColorCollection.VALUES, FEItems.DYED_ROUTER, (color, item) -> items.accept(item, getJapanesePairing(getDyeName(color), ROUTER)));
        items.accept(FEItems.COMPRESSOR, COMPRESSOR);
        items.accept(FEItems.FUEL_CARRIER, "石炭箱");
        items.accept(FEItems.INDUCTION_FURNACE, getPairing("携帯誘導", "炉"));

        items.accept(FEItems.MENDER, MENDER);
        items.accept(FEItems.ENDER_ROUTER, getPairing(ENDER, ROUTER));
        items.accept(FEItems.STRIPPER, STRIPPER);
        items.accept(FEItems.COMBUSTOR, COMBUSTOR);
    }

    public static void generateItemTags(BiConsumer<TagKey<Item>, String> tags) {
        tags.accept(FETags.PAPERS, "紙");
        tags.accept(FETags.ROUTERS, ROUTER);
        tags.accept(FETags.RESTORATION_WISDOM_DISCOVERS, getJapanesePairing(getPairing(RESTORATION, WISDOM), DISCOVERS));
        tags.accept(FETags.DISTRIBUTION_WISDOM_DISCOVERS, getJapanesePairing(getPairing(DISTRIBUTION, WISDOM), DISCOVERS));
        tags.accept(FETags.COMPRESSION_WISDOM_DISCOVERS, getJapanesePairing(getPairing(COMPRESSION, WISDOM), DISCOVERS));
        tags.accept(FETags.INDUCTION_WISDOM_DISCOVERS, getJapanesePairing(getPairing(INDUCTION, WISDOM), DISCOVERS));
        tags.accept(FETags.UNIVERSAL_WISDOM_DISCOVERS, getJapanesePairing(getPairing(UNIVERSAL, WISDOM), DISCOVERS));
        tags.accept(FETags.ENGINEERS_SEAL_DISCOVERS, getJapanesePairing(getJapanesePairing("技術者", "印章"), DISCOVERS));
        tags.accept(FETags.RESTORER_IGNORE_MATERIALS, getPairing(RESTORER, "は材料を無視します"));
        tags.accept(FETags.RESTORER_IGNORE_TOOLS, getPairing(RESTORER, "は工具を無視します"));
        tags.accept(FETags.ROUTER_IGNORE_ITEMS, getPairing(ROUTER, "はアイテムを無視します"));
        tags.accept(FETags.COMPRESSOR_IGNORE_ITEMS, getPairing(COMPRESSOR, "はアイテムを無視します"));
        tags.accept(FETags.FUEL_CARRIER_IGNORE_ITEMS, getPairing("石炭箱", "はアイテムを無視します"));
        tags.accept(FETags.MENDER_IGNORE_TOOLS, getPairing(MENDER, "は工具を無視します"));
        tags.accept(FETags.STRIPPER_IGNORE_ITEMS, getPairing(STRIPPER, "はアイテムを無視します"));
        tags.accept(FETags.ENDER_ROUTER_IGNORE_ITEMS, getPairing(getPairing(ENDER, ROUTER), "はアイテムを無視します"));
    }

    public static void generateBlockTags(BiConsumer<TagKey<Block>, String> tags) {
        tags.accept(FETags.COMPRESSOR_IGNORE_BLOCKS, getPairing(COMPRESSOR, "ブロックを無視します"));
    }

    public static void generateSounds(BiConsumer<SoundEvent, String> sounds) {
        sounds.accept(FESounds.RESTORER_DROP_CONTENTS, getPairing(RESTORER, EMPTIES, ""));
        sounds.accept(FESounds.ROUTER_DROP_CONTENTS, getPairing(ROUTER, EMPTIES, ""));
        sounds.accept(FESounds.COMPRESSOR_DROP_CONTENTS, getPairing(COMPRESSOR, EMPTIES, ""));
        sounds.accept(FESounds.FUEL_CARRIER_DROP_CONTENTS, getPairing("石炭箱", EMPTIES, ""));
        sounds.accept(FESounds.INDUCTION_FURNACE_DROP_CONTENTS, getPairing(getPairing("携帯誘導", "炉"), EMPTIES, ""));
        sounds.accept(FESounds.MENDER_DROP_CONTENTS, getPairing(MENDER, EMPTIES, ""));
        sounds.accept(FESounds.STRIPPER_DROP_CONTENTS, getPairing(STRIPPER, EMPTIES, ""));
        sounds.accept(FESounds.COMBUSTOR_DROP_CONTENTS, getPairing(COMBUSTOR, EMPTIES, ""));
    }

    public static void generateCustom(BiConsumer<String, String> custom) {
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom", getPairing("工学", WISDOM));
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom.discovers", getPairing(DISCOVERS, "：", ""));
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.RESTORER_ID.getPath() + ".empty.description", "複数種類の修理材料を1スタック分まで一緒にしまうことができます");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".device.empty.description", "複数種類の絞り込むアイテムを1スタック分まで一緒にしまうことができます");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.FUEL_CARRIER_ID.getPath() + ".empty.description", "複数種類の燃料アイテムを1スタック分まで一緒にしまうことができます");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.MENDER_ID.getPath() + ".empty.description", "修理道具やガラス瓶を収納できます");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.COMBUSTOR_ID.getPath() + ".empty.description", "火薬、石炭、ブレイズパウダーを収納できます");

        custom.accept("advancements."  + ForgottenEngineersCommon.MOD_ID + ".adventure." + ForgottenEngineersItems.ENGINEERS_SEAL_ID.getPath() + ".title", "過去のこだま");
        custom.accept("advancements."  + ForgottenEngineersCommon.MOD_ID + ".adventure." + ForgottenEngineersItems.ENGINEERS_SEAL_ID.getPath() + ".description", "工学の叡智、そのすべての源流を見出せ");
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

    public static String getDyeName(DyeColor color) {
        return switch (color) {
            case BLACK -> "黒色";
            case BLUE -> "青色";
            case BROWN -> "茶色";
            case CYAN -> "青緑色";
            case GRAY -> "灰色";
            case GREEN -> "緑色";
            case LIGHT_BLUE -> "空色";
            case LIGHT_GRAY -> "薄灰色";
            case LIME -> "黄緑色";
            case MAGENTA -> "赤紫色";
            case ORANGE -> "橙色";
            case PINK -> "桃色";
            case PURPLE -> "紫色";
            case RED -> "赤色";
            case WHITE -> "白色";
            case YELLOW -> "黄色";
        };
    }
}
