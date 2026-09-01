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

public class FEKoreanLang {

    public static String MOD_NAME = "잊혀진 공학";

    public static String RESTORATION = "수리의";
    public static String DISTRIBUTION = "분류의";
    public static String COMPRESSION = "압축의";
    public static String UNIVERSAL = "보편적";
    public static String WISDOM = "예지";
    public static String ENDER = "엔더";

    public static String RESTORER = "휴대용 수리기";
    public static String ROUTER = "휴대용 분류기";
    public static String COMPRESSOR = "휴대용 압축기";
    public static String INDUCTION = "유도 가열의";
    public static String MENDER = "휴대용 수선기";
    public static String STRIPPER = "휴대용 탈피기";
    public static String COMBUSTOR = "연소 캐논포";

    public static String DISCOVERS_ITEMS = "발견한 아이템";
    public static String DISCOVERS = "발견";
    public static String EMPTIES = "를 비움";

    public static void generateItems(BiConsumer<Item, String> items) {
        items.accept(FEItems.RESTORATION_WISDOM, getPairing(RESTORATION, WISDOM));
        items.accept(FEItems.DISTRIBUTION_WISDOM, getPairing(DISTRIBUTION, WISDOM));
        items.accept(FEItems.COMPRESSION_WISDOM, getPairing(COMPRESSION, WISDOM));
        items.accept(FEItems.INDUCTION_WISDOM, getPairing(INDUCTION, WISDOM));
        items.accept(FEItems.ENGINEERS_SEAL, "엔지니어 인증 마크");
        items.accept(FEItems.UNIVERSAL_WISDOM, getPairing(UNIVERSAL, WISDOM));

        items.accept(FEItems.RESTORER, RESTORER);
        items.accept(FEItems.ROUTER, ROUTER);
        ColorCollection.zipApply(ColorCollection.VALUES, FEItems.DYED_ROUTER, (color, item) -> items.accept(item, getPairing(getDyeName(color), ROUTER)));
        items.accept(FEItems.COMPRESSOR, COMPRESSOR);
        items.accept(FEItems.FUEL_CARRIER, "석탄통");
        items.accept(FEItems.INDUCTION_FURNACE, getPairing("휴대용 유도", "로", ""));

        items.accept(FEItems.MENDER, MENDER);
        items.accept(FEItems.ENDER_ROUTER, getPairing(ENDER, ROUTER));
        items.accept(FEItems.STRIPPER, STRIPPER);
        items.accept(FEItems.COMBUSTOR, COMBUSTOR);
    }

    public static void generateItemTags(BiConsumer<TagKey<Item>, String> tags) {
        tags.accept(FETags.PAPERS, "종이들");
        tags.accept(FETags.ROUTERS, ROUTER + "들");
        tags.accept(FETags.RESTORATION_WISDOM_DISCOVERS, getPairing(getPairing(RESTORATION, WISDOM), DISCOVERS));
        tags.accept(FETags.DISTRIBUTION_WISDOM_DISCOVERS, getPairing(getPairing(DISTRIBUTION, WISDOM), DISCOVERS));
        tags.accept(FETags.COMPRESSION_WISDOM_DISCOVERS, getPairing(getPairing(COMPRESSION, WISDOM), DISCOVERS));
        tags.accept(FETags.INDUCTION_WISDOM_DISCOVERS, getPairing(getPairing(INDUCTION, WISDOM), DISCOVERS));
        tags.accept(FETags.UNIVERSAL_WISDOM_DISCOVERS, getPairing(getPairing(UNIVERSAL, WISDOM), DISCOVERS));
        tags.accept(FETags.ENGINEERS_SEAL_DISCOVERS, getPairing("엔지니어 인증 마크", DISCOVERS));
        tags.accept(FETags.RESTORER_IGNORE_MATERIALS, getPairing(RESTORER, "는 재료를 무시합니다", ""));
        tags.accept(FETags.RESTORER_IGNORE_TOOLS, getPairing(RESTORER, "는 도구를 무시합니다", ""));
        tags.accept(FETags.ROUTER_IGNORE_ITEMS, getPairing(ROUTER, "는 품목을 무시합니다", ""));
        tags.accept(FETags.COMPRESSOR_IGNORE_ITEMS, getPairing(COMPRESSOR, "는 품목을 무시합니다", ""));
        tags.accept(FETags.FUEL_CARRIER_IGNORE_ITEMS, getPairing("석탄통", "는 품목을 무시합니다", ""));
        tags.accept(FETags.MENDER_IGNORE_TOOLS, getPairing(MENDER, "는 도구를 무시합니다", ""));
        tags.accept(FETags.STRIPPER_IGNORE_ITEMS, getPairing(STRIPPER, "는 품목을 무시합니다", ""));
        tags.accept(FETags.ENDER_ROUTER_IGNORE_ITEMS, getPairing(getPairing(ENDER, ROUTER), "는 품목을 무시합니다", ""));
    }

    public static void generateBlockTags(BiConsumer<TagKey<Block>, String> tags) {
        tags.accept(FETags.COMPRESSOR_IGNORE_BLOCKS, getPairing(COMPRESSOR, "는 블록을 무시합니다", ""));
    }

    public static void generateSounds(BiConsumer<SoundEvent, String> sounds) {
        sounds.accept(FESounds.RESTORER_DROP_CONTENTS, getPairing(RESTORER, EMPTIES, ""));
        sounds.accept(FESounds.ROUTER_DROP_CONTENTS, getPairing(ROUTER, EMPTIES, ""));
        sounds.accept(FESounds.COMPRESSOR_DROP_CONTENTS, getPairing(COMPRESSOR, EMPTIES, ""));
        sounds.accept(FESounds.FUEL_CARRIER_DROP_CONTENTS, getPairing("석탄통", EMPTIES, ""));
        sounds.accept(FESounds.INDUCTION_FURNACE_DROP_CONTENTS, getPairing(getPairing("휴대용 유도", "로", ""), EMPTIES, ""));
        sounds.accept(FESounds.MENDER_DROP_CONTENTS, getPairing(MENDER, EMPTIES, ""));
        sounds.accept(FESounds.STRIPPER_DROP_CONTENTS, getPairing(STRIPPER, EMPTIES, ""));
        sounds.accept(FESounds.COMBUSTOR_DROP_CONTENTS, getPairing(COMBUSTOR, EMPTIES, ""));
    }

    public static void generateCustom(BiConsumer<String, String> custom) {
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom", getPairing("공학의", WISDOM));
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom.discovers", DISCOVERS_ITEMS + ":");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.RESTORER_ID.getPath() + ".empty.description", "여러 종류의 수리 재료를 섞어 한 묶음 보관할 수 있습니다");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".device.empty.description", "여러 종류의 항목 필터를 섞어 한 묶음 보관할 수 있습니다");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.FUEL_CARRIER_ID.getPath() + ".empty.description", "여러 종류의 연료 재료를 섞어 한 묶음 보관할 수 있습니다.");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.MENDER_ID.getPath() + ".empty.description", "수리 도구 또는 유리병을 보관할 수 있습니다");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.COMBUSTOR_ID.getPath() + ".empty.description", "화약, 석탄, 블레이즈 가루를 보관할 수 있습니다");

        custom.accept("advancements."  + ForgottenEngineersCommon.MOD_ID + ".adventure." + ForgottenEngineersItems.ENGINEERS_SEAL_ID.getPath() + ".title", "과거의 메아리");
        custom.accept("advancements."  + ForgottenEngineersCommon.MOD_ID + ".adventure." + ForgottenEngineersItems.ENGINEERS_SEAL_ID.getPath() + ".description", "모든 공학의 예지가 흘러내려 온 그 원류를 찾아라");
    }

    public static String getPairing(String name, String type, String joiner) {
        return name + joiner + type;
    }

    public static String getPairing(String name, String type) {
        return getPairing(name, type, " ");
    }

    public static String getDyeName(DyeColor color) {
        return switch (color) {
            case BLACK -> "검은색";
            case BLUE -> "파란색";
            case BROWN -> "갈색";
            case CYAN -> "청록색";
            case GRAY -> "회색";
            case GREEN -> "초록색";
            case LIGHT_BLUE -> "하늘색";
            case LIGHT_GRAY -> "회백색";
            case LIME -> "연두색";
            case MAGENTA -> "자홍색";
            case ORANGE -> "주황색";
            case PINK -> "분홍색";
            case PURPLE -> "보라색";
            case RED -> "빨간색";
            case WHITE -> "하얀색";
            case YELLOW -> "노란색";
        };
    }
}
