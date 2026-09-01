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

public class FERussianLang {

    public static String MOD_NAME = "Забытая инженерия";

    public static String RESTORATION = "восстановления";
    public static String DISTRIBUTION = "распределения";
    public static String COMPRESSION = "сжатия";
    public static String UNIVERSAL = "всеобщая";
    public static String WISDOM = "Мудрость";
    public static String WISDOM_DISCOVERS = "мудрость";
    public static String ENDER = "Эндер-";

    public static String RESTORER = "Реставратор";
    public static String ROUTER = "Сортировщик";
    public static String COMPRESSOR = "Компрессор";
    public static String INDUCTION = "индукции";
    public static String MENDER = "Починитель";
    public static String STRIPPER = "Окорочник";
    public static String COMBUSTOR = "Пушка сгорания";

    public static String DISCOVERS = "Открытия";
    public static String EMPTIES = "опустошён";

    public static void generateItems(BiConsumer<Item, String> items) {
        items.accept(FEItems.RESTORATION_WISDOM, getPairing(WISDOM, RESTORATION));
        items.accept(FEItems.DISTRIBUTION_WISDOM, getPairing(WISDOM, DISTRIBUTION));
        items.accept(FEItems.COMPRESSION_WISDOM, getPairing(WISDOM, COMPRESSION));
        items.accept(FEItems.INDUCTION_WISDOM, getPairing(WISDOM, INDUCTION));
        items.accept(FEItems.ENGINEERS_SEAL, getPairing("Печать", "инженера"));
        items.accept(FEItems.UNIVERSAL_WISDOM, getPairing(WISDOM, UNIVERSAL));

        items.accept(FEItems.RESTORER, RESTORER);
        items.accept(FEItems.ROUTER, ROUTER);
        ColorCollection.zipApply(ColorCollection.VALUES, FEItems.DYED_ROUTER, (color, item) -> items.accept(item, getPairing(getDyeName(color), ROUTER.toLowerCase())));
        items.accept(FEItems.COMPRESSOR, COMPRESSOR);
        items.accept(FEItems.FUEL_CARRIER, "Угольница");
        items.accept(FEItems.INDUCTION_FURNACE, "Индукционная печь");

        items.accept(FEItems.MENDER, MENDER);
        items.accept(FEItems.ENDER_ROUTER, ENDER + ROUTER.toLowerCase());
        items.accept(FEItems.STRIPPER, STRIPPER);
        items.accept(FEItems.COMBUSTOR, COMBUSTOR);
    }

    public static void generateItemTags(BiConsumer<TagKey<Item>, String> tags) {
        tags.accept(FETags.PAPERS, "Бумаги");
        tags.accept(FETags.ROUTERS, ROUTER + "и");
        tags.accept(FETags.RESTORATION_WISDOM_DISCOVERS, getPairing(DISCOVERS, getPairing(WISDOM_DISCOVERS, RESTORATION)));
        tags.accept(FETags.DISTRIBUTION_WISDOM_DISCOVERS, getPairing(DISCOVERS, getPairing(WISDOM_DISCOVERS, DISTRIBUTION)));
        tags.accept(FETags.COMPRESSION_WISDOM_DISCOVERS, getPairing(DISCOVERS, getPairing(WISDOM_DISCOVERS, COMPRESSION)));
        tags.accept(FETags.INDUCTION_WISDOM_DISCOVERS, getPairing(DISCOVERS, getPairing(WISDOM_DISCOVERS, INDUCTION)));
        tags.accept(FETags.UNIVERSAL_WISDOM_DISCOVERS, getPairing(DISCOVERS, getPairing(WISDOM_DISCOVERS, UNIVERSAL)));
        tags.accept(FETags.ENGINEERS_SEAL_DISCOVERS, getPairing(DISCOVERS, getPairing(WISDOM_DISCOVERS, getPairing("печать", "инженера"))));
        tags.accept(FETags.RESTORER_IGNORE_MATERIALS, getPairing(RESTORER, "игнорирует материалы"));
        tags.accept(FETags.RESTORER_IGNORE_TOOLS, getPairing(RESTORER, "игнорирует инструменты"));
        tags.accept(FETags.ROUTER_IGNORE_ITEMS, getPairing(ROUTER, "игнорирует предметы"));
        tags.accept(FETags.COMPRESSOR_IGNORE_ITEMS, getPairing(COMPRESSOR, "игнорирует предметы"));
        tags.accept(FETags.FUEL_CARRIER_IGNORE_ITEMS, getPairing("Угольница", "игнорирует предметы"));
        tags.accept(FETags.MENDER_IGNORE_TOOLS, getPairing(MENDER, "игнорирует инструменты"));
        tags.accept(FETags.STRIPPER_IGNORE_ITEMS, getPairing(STRIPPER, "игнорирует предметы"));
        tags.accept(FETags.ENDER_ROUTER_IGNORE_ITEMS, getPairing(ENDER + ROUTER.toLowerCase(), "игнорирует предметы"));
    }

    public static void generateBlockTags(BiConsumer<TagKey<Block>, String> tags) {
        tags.accept(FETags.COMPRESSOR_IGNORE_BLOCKS, getPairing(COMPRESSOR, "игнорирует блоки"));
    }

    public static void generateSounds(BiConsumer<SoundEvent, String> sounds) {
        sounds.accept(FESounds.RESTORER_DROP_CONTENTS, getPairing(RESTORER, EMPTIES));
        sounds.accept(FESounds.ROUTER_DROP_CONTENTS, getPairing(ROUTER, EMPTIES));
        sounds.accept(FESounds.COMPRESSOR_DROP_CONTENTS, getPairing(COMPRESSOR, EMPTIES));
        sounds.accept(FESounds.FUEL_CARRIER_DROP_CONTENTS, getPairing("Угольница", EMPTIES));
        sounds.accept(FESounds.INDUCTION_FURNACE_DROP_CONTENTS, getPairing("Индукционная печь", EMPTIES));
        sounds.accept(FESounds.MENDER_DROP_CONTENTS, getPairing(MENDER, EMPTIES));
        sounds.accept(FESounds.STRIPPER_DROP_CONTENTS, getPairing(STRIPPER, EMPTIES));
        sounds.accept(FESounds.COMBUSTOR_DROP_CONTENTS, getPairing(COMBUSTOR, EMPTIES));
    }

    public static void generateCustom(BiConsumer<String, String> custom) {
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom", getPairing(WISDOM, "инженера"));
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom.discovers", DISCOVERS + ":");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.RESTORER_ID.getPath() + ".empty.description", "Вмещает стопку разных материалов для ремонта");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".device.empty.description", "Вмещает стопку разных фильтрующих предметов");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.FUEL_CARRIER_ID.getPath() + ".empty.description", "Вмещает стопку различных топливных элементов");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.MENDER_ID.getPath() + ".empty.description", "Можно хранить инструменты для ремонта или бутылки");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.COMBUSTOR_ID.getPath() + ".empty.description", "Может вмещать порох, уголь и огнеупорный порох");

        custom.accept("advancements."  + ForgottenEngineersCommon.MOD_ID + ".adventure." + ForgottenEngineersItems.ENGINEERS_SEAL_ID.getPath() + ".title", "Отголоски прошлого");
        custom.accept("advancements."  + ForgottenEngineersCommon.MOD_ID + ".adventure." + ForgottenEngineersItems.ENGINEERS_SEAL_ID.getPath() + ".description", "Найдите источник, от которого произошла вся инженерная мудрость");
    }

    public static String getPairing(String name, String type) {
        return name + " " + type;
    }

    public static String getDyeName(DyeColor color) {
        return switch (color) {
            case BLACK -> "Чёрная";
            case BLUE -> "Синяя";
            case BROWN -> "Коричневая";
            case CYAN -> "Бирюзовая";
            case GRAY -> "Серая";
            case GREEN -> "Зелёная";
            case LIGHT_BLUE -> "Голубая";
            case LIGHT_GRAY -> "Светло-серая";
            case LIME -> "Лаймовая";
            case MAGENTA -> "Пурпурная";
            case ORANGE -> "Оранжевая";
            case PINK -> "Розовая";
            case PURPLE -> "Фиолетовая";
            case RED -> "Красная";
            case WHITE -> "Белая";
            case YELLOW -> "Жёлтая";
        };
    }
}
