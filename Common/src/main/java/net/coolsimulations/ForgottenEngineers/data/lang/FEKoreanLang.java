package net.coolsimulations.ForgottenEngineers.data.lang;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.data.FETags;
import net.coolsimulations.ForgottenEngineers.item.FEItems;
import net.coolsimulations.ForgottenEngineers.sounds.FESounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.function.BiConsumer;

public class FEKoreanLang {

    public static String MOD_NAME = "잊혀진 공학";

    public static String RESTORATION = "수리의";
    public static String DISTRIBUTION = "분류의";
    public static String WISDOM = "예지";

    public static String RESTORER = "휴대용 수리기";
    public static String ROUTER = "휴대용 분류기";

    public static String DISCOVERS_ITEMS = "발견한 아이템";
    public static String DISCOVERS = "발견";
    public static String EMPTIES = "를 비움";

    public static void generateItems(BiConsumer<Item, String> items) {
        items.accept(FEItems.RESTORATION_WISDOM, getPairing(RESTORATION, WISDOM));
        items.accept(FEItems.DISTRIBUTION_WISDOM, getPairing(DISTRIBUTION, WISDOM));
        //items.accept(FEItems.COMPRESSION_WISDOM, getPairing("압축의", WISDOM));
        //items.accept(FEItems.INDUCTION_WISDOM, getPairing("유도 가열의", WISDOM));

        items.accept(FEItems.RESTORER, RESTORER);
        items.accept(FEItems.ROUTER, ROUTER);
        //items.accept(FEItems.COMPRESSOR, "휴대용 압축기");
        //items.accept(FEItems.INDUCTION_FURNACE, getPairing(휴대용 유도, "로", ""));
    }

    public static void generateItemTags(BiConsumer<TagKey<Item>, String> tags) {
        tags.accept(FETags.PAPERS, "종이들");
        tags.accept(FETags.RESTORATION_WISDOM_DISCOVERS, getPairing(getPairing(RESTORATION, WISDOM), DISCOVERS));
        tags.accept(FETags.DISTRIBUTION_WISDOM_DISCOVERS, getPairing(getPairing(DISTRIBUTION, WISDOM), DISCOVERS));
        tags.accept(FETags.RESTORER_IGNORE_MATERIALS, getPairing(RESTORER, "는 재료를 무시합니다", ""));
        tags.accept(FETags.RESTORER_IGNORE_TOOLS, getPairing(RESTORER, "는 도구를 무시합니다", ""));
        tags.accept(FETags.ROUTER_IGNORE_ITEMS, getPairing(ROUTER, "는 품목을 무시합니다", ""));
    }

    public static void generateSounds(BiConsumer<SoundEvent, String> sounds) {
        sounds.accept(FESounds.RESTORER_DROP_CONTENTS, getPairing(RESTORER, EMPTIES, ""));
        sounds.accept(FESounds.ROUTER_DROP_CONTENTS, getPairing(ROUTER, EMPTIES, ""));
    }

    public static void generateCustom(BiConsumer<String, String> custom) {
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom", getPairing("공학의", WISDOM));
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom.discovers", DISCOVERS_ITEMS + ":");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".restorer.empty.description", "여러 종류의 수리 재료를 섞어 한 묶음 보관할 수 있습니다");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".router.empty.description", "여러 종류의 항목 필터를 섞어 한 묶음 보관할 수 있습니다");
    }

    public static String getPairing(String name, String type, String joiner) {
        return name + joiner + type;
    }

    public static String getPairing(String name, String type) {
        return getPairing(name, type, " ");
    }
}
