package net.coolsimulations.ForgottenEngineers.data.lang;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.data.FETags;
import net.coolsimulations.ForgottenEngineers.item.FEItems;
import net.coolsimulations.ForgottenEngineers.sounds.FESounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.Locale;
import java.util.function.BiConsumer;

public class FERussianLang {

    public static String MOD_NAME = "Забытая инженерия";

    public static String RESTORATION = "восстановления";
    public static String DISTRIBUTION = "распределения";
    public static String WISDOM = "Мудрость";
    public static String WISDOM_DISCOVERS = "мудрость";

    public static String RESTORER = "Реставратор";
    public static String ROUTER = "Сортировщик";
    public static String INDUCTION = "индукции";

    public static String DISCOVERS = "Открытия";
    public static String EMPTIES = "опустошён";

    public static void generateItems(BiConsumer<Item, String> items) {
        items.accept(FEItems.RESTORATION_WISDOM, getPairing(WISDOM, RESTORATION));
        items.accept(FEItems.DISTRIBUTION_WISDOM, getPairing(WISDOM, DISTRIBUTION));
        //items.accept(FEItems.COMPRESSION_WISDOM, getPairing(WISDOM, "сжатия"));
        //items.accept(FEItems.INDUCTION_WISDOM, getPairing(WISDOM, INDUCTION));

        items.accept(FEItems.RESTORER, RESTORER);
        items.accept(FEItems.ROUTER, ROUTER);
        //items.accept(FEItems.COMPRESSOR, "Компрессор");
        //items.accept(FEItems.INDUCTION_FURNACE, "Индукционная печь");
    }

    public static void generateItemTags(BiConsumer<TagKey<Item>, String> tags) {
        tags.accept(FETags.PAPERS, "Бумаги");
        tags.accept(FETags.RESTORATION_WISDOM_DISCOVERS, getPairing(DISCOVERS, getPairing(WISDOM_DISCOVERS, RESTORATION)));
        tags.accept(FETags.DISTRIBUTION_WISDOM_DISCOVERS, getPairing(DISCOVERS, getPairing(WISDOM_DISCOVERS, DISTRIBUTION)));
        tags.accept(FETags.RESTORER_IGNORE_MATERIALS, getPairing(RESTORER, "игнорирует материалы"));
        tags.accept(FETags.RESTORER_IGNORE_TOOLS, getPairing(RESTORER, "игнорирует инструменты"));
        tags.accept(FETags.ROUTER_IGNORE_ITEMS, getPairing(ROUTER, "игнорирует предметы"));
    }

    public static void generateSounds(BiConsumer<SoundEvent, String> sounds) {
        sounds.accept(FESounds.RESTORER_DROP_CONTENTS, getPairing(RESTORER, EMPTIES));
        sounds.accept(FESounds.ROUTER_DROP_CONTENTS, getPairing(ROUTER, EMPTIES));
    }

    public static void generateCustom(BiConsumer<String, String> custom) {
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom", getPairing(WISDOM, "инженера"));
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom.discovers", DISCOVERS + ":");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".restorer.empty.description", "Вмещает стопку разных материалов для ремонта");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".router.empty.description", "Вмещает стопку разных фильтрующих предметов");
    }

    public static String getPairing(String name, String type) {
        return name + " " + type;
    }
}
