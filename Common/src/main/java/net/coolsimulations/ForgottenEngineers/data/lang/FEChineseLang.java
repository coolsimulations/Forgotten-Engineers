package net.coolsimulations.ForgottenEngineers.data.lang;

import com.github.houbb.opencc4j.util.ZhTwConverterUtil;
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

public class FEChineseLang {

    public static String MOD_NAME = "失落的工程";

    public static String RESTORATION = "修复";
    public static String DISTRIBUTION = "分拣";
    public static String COMPRESSION = "压缩";
    public static String UNIVERSAL = "普世";
    public static String WISDOM = "之智";
    public static String ENDER = "末影";

    public static String RESTORER = "物品修复器";
    public static String ROUTER = "便携分类器";
    public static String COMPRESSOR = "便携压缩器";
    public static String INDUCTION = "感应加热";
    public static String MENDER = "便携修缮器";
    public static String STRIPPER = "便携剥皮器";
    public static String COMBUSTOR = "燃烧加农炮";

    public static String DISCOVERS_ITEMS = "已发现物品";
    public static String DISCOVERS = "发现";
    public static String EMPTIES = "：倒空";

    public static void generateItems(BiConsumer<Item, String> items) {
        items.accept(FEItems.RESTORATION_WISDOM, getPairing(RESTORATION, WISDOM));
        items.accept(FEItems.DISTRIBUTION_WISDOM, getPairing(DISTRIBUTION, WISDOM));
        items.accept(FEItems.COMPRESSION_WISDOM, getPairing(COMPRESSION, WISDOM));
        items.accept(FEItems.INDUCTION_WISDOM, getPairing(INDUCTION, WISDOM));
        items.accept(FEItems.ENGINEERS_SEAL, "工程师印章");
        items.accept(FEItems.UNIVERSAL_WISDOM, getPairing(UNIVERSAL, WISDOM));

        items.accept(FEItems.RESTORER, RESTORER);
        items.accept(FEItems.ROUTER, ROUTER);
        ColorCollection.zipApply(ColorCollection.VALUES, FEItems.DYED_ROUTER, (color, item) -> items.accept(item, getPairing(getDyeName(color), ROUTER)));
        items.accept(FEItems.COMPRESSOR, COMPRESSOR);
        items.accept(FEItems.FUEL_CARRIER, "煤箱");
        items.accept(FEItems.INDUCTION_FURNACE, getPairing("便携感应", "炉"));

        items.accept(FEItems.MENDER, MENDER);
        items.accept(FEItems.ENDER_ROUTER, getPairing(ENDER, ROUTER));
        items.accept(FEItems.STRIPPER, STRIPPER);
        items.accept(FEItems.COMBUSTOR, COMBUSTOR);
    }

    public static void generateItemTags(BiConsumer<TagKey<Item>, String> tags) {
        tags.accept(FETags.PAPERS, "纸");
        tags.accept(FETags.ROUTERS, ROUTER);
        tags.accept(FETags.RESTORATION_WISDOM_DISCOVERS, getPairing(getPairing(RESTORATION, WISDOM), DISCOVERS));
        tags.accept(FETags.DISTRIBUTION_WISDOM_DISCOVERS, getPairing(getPairing(DISTRIBUTION, WISDOM), DISCOVERS));
        tags.accept(FETags.COMPRESSION_WISDOM_DISCOVERS, getPairing(getPairing(COMPRESSION, WISDOM), DISCOVERS));
        tags.accept(FETags.INDUCTION_WISDOM_DISCOVERS, getPairing(getPairing(INDUCTION, WISDOM), DISCOVERS));
        tags.accept(FETags.UNIVERSAL_WISDOM_DISCOVERS, getPairing(getPairing(UNIVERSAL, WISDOM), DISCOVERS));
        tags.accept(FETags.ENGINEERS_SEAL_DISCOVERS, getPairing("工程师印章", DISCOVERS));
        tags.accept(FETags.RESTORER_IGNORE_MATERIALS, getPairing(RESTORER, "忽略材料"));
        tags.accept(FETags.RESTORER_IGNORE_TOOLS, getPairing(RESTORER, "忽略工具"));
        tags.accept(FETags.ROUTER_IGNORE_ITEMS, getPairing(ROUTER, "忽略物品"));
        tags.accept(FETags.COMPRESSOR_IGNORE_ITEMS, getPairing(COMPRESSOR, "忽略物品"));
        tags.accept(FETags.FUEL_CARRIER_IGNORE_ITEMS, getPairing("煤箱", "忽略物品"));
        tags.accept(FETags.MENDER_IGNORE_TOOLS, getPairing(MENDER, "忽略工具"));
        tags.accept(FETags.STRIPPER_IGNORE_ITEMS, getPairing(STRIPPER, "忽略物品"));
        tags.accept(FETags.ENDER_ROUTER_IGNORE_ITEMS, getPairing(getPairing(ENDER, ROUTER), "忽略物品"));
    }

    public static void generateBlockTags(BiConsumer<TagKey<Block>, String> tags) {
        tags.accept(FETags.COMPRESSOR_IGNORE_BLOCKS, getPairing(COMPRESSOR, "忽略方块"));
    }

    public static void generateSounds(BiConsumer<SoundEvent, String> sounds) {
        sounds.accept(FESounds.RESTORER_DROP_CONTENTS, getPairing(RESTORER, EMPTIES));
        sounds.accept(FESounds.ROUTER_DROP_CONTENTS, getPairing(ROUTER, EMPTIES));
        sounds.accept(FESounds.COMPRESSOR_DROP_CONTENTS, getPairing(COMPRESSOR, EMPTIES));
        sounds.accept(FESounds.FUEL_CARRIER_DROP_CONTENTS, getPairing("煤箱", EMPTIES));
        sounds.accept(FESounds.INDUCTION_FURNACE_DROP_CONTENTS, getPairing(getPairing("便携感应", "炉"), EMPTIES));
        sounds.accept(FESounds.MENDER_DROP_CONTENTS, getPairing(MENDER, EMPTIES));
        sounds.accept(FESounds.STRIPPER_DROP_CONTENTS, getPairing(STRIPPER, EMPTIES));
        sounds.accept(FESounds.COMBUSTOR_DROP_CONTENTS, getPairing(COMBUSTOR, EMPTIES));
    }

    public static void generateCustom(BiConsumer<String, String> custom) {
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom", getPairing("工程", WISDOM));
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom.discovers", DISCOVERS_ITEMS + "：");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.RESTORER_ID.getPath() + ".empty.description", "可容纳一组混合的维修材料");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".device.empty.description", "可容纳一组混合的筛选物品");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.FUEL_CARRIER_ID.getPath() + ".empty.description", "可容纳一组混合的燃料物品");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.MENDER_ID.getPath() + ".empty.description", "可容纳一组混合的维修工具或玻璃瓶");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.COMBUSTOR_ID.getPath() + ".empty.description", "可容纳一组混合的火药、煤炭和烈焰粉");

        custom.accept("advancements."  + ForgottenEngineersCommon.MOD_ID + ".adventure." + ForgottenEngineersItems.ENGINEERS_SEAL_ID.getPath() + ".title", "往昔的回响");
        custom.accept("advancements."  + ForgottenEngineersCommon.MOD_ID + ".adventure." + ForgottenEngineersItems.ENGINEERS_SEAL_ID.getPath() + ".description", "寻得所有工程之智所自出的源流");
    }

    public static String getPairing(String name, String type, String joiner) {
        return name + joiner + type;
    }

    public static String getPairing(String name, String type) {
        return getPairing(name, type, "");
    }

    public static String getDyeName(DyeColor color) {
        return switch (color) {
            case BLACK -> "黑色";
            case BLUE -> "蓝色";
            case BROWN -> "棕色";
            case CYAN -> "青色";
            case GRAY -> "灰色";
            case GREEN -> "绿色";
            case LIGHT_BLUE -> "淡蓝色";
            case LIGHT_GRAY -> "淡灰色";
            case LIME -> "黄绿色";
            case MAGENTA -> "品红色";
            case ORANGE -> "橙色";
            case PINK -> "粉红色";
            case PURPLE -> "紫色";
            case RED -> "红色";
            case WHITE -> "白色";
            case YELLOW -> "黄色";
        };
    }

    public static String convertToTraditional(String translation) {
        if (ZhTwConverterUtil.isChinese(translation))
            return ZhTwConverterUtil.toTraditional(translation);
        return translation;
    }
}
