package net.coolsimulations.ForgottenEngineers.data.lang;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.item.FEItems;
import net.minecraft.world.item.Item;

import java.util.function.BiConsumer;

public class FEKoreanLang {

    public static String MOD_NAME = "잊혀진 공학";

    public static String WISDOM = "예지";

    public static void generateItems(BiConsumer<Item, String> items) {
        items.accept(FEItems.RESTORATION_WISDOM, getPairing("수리의", WISDOM));
        //items.accept(FEItems.DISTRIBUTION_WISDOM, getPairing("분류의", WISDOM));
        //items.accept(FEItems.COMPRESSION_WISDOM, getPairing("압축의", WISDOM));
        //items.accept(FEItems.INDUCTION_WISDOM, getPairing("유도 가열의", WISDOM));

        items.accept(FEItems.RESTORER, "휴대용 수리기");
        //items.accept(FEItems.ROUTER, "휴대용 분류기");
        //items.accept(FEItems.COMPRESSOR, "휴대용 압축기");
        //items.accept(FEItems.INDUCTION_FURNACE, getPairing(휴대용 유도, "로", ""));
    }

    public static void generateCustom(BiConsumer<String, String> custom) {
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom", getPairing("공학의", WISDOM));
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom.discovers", "발견한 아이템:");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".restorer.empty.description", "여러 종류의 수리 재료를 섞어 한 묶음 보관할 수 있습니다");
    }

    public static String getPairing(String name, String type, String joiner) {
        return name + joiner + type;
    }

    public static String getPairing(String name, String type) {
        return getPairing(name, type, " ");
    }
}
