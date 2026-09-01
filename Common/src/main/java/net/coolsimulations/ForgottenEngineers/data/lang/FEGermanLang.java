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

public class FEGermanLang {

    public static String ENGINEERING = "Ingenieurkunst";

    public static String MOD_NAME = getPairing("Vergessene", ENGINEERING);

    public static String RESTORATION = "Restauration";
    public static String DISTRIBUTION = "Sortierung";
    public static String COMPRESSION = "Kompression";
    public static String UNIVERSAL = "Universelle";
    public static String WISDOM = "Weisheit der";
    public static String ENDER = "Ender";

    public static String RESTORER = "Restaurator";
    public static String ROUTER = "Sortierer";
    public static String COMPRESSOR = "Kompressor";
    public static String INDUCTION = "Induktion";
    public static String MENDER = "Instandsetzer";
    public static String STRIPPER = "Entrinder";
    public static String COMBUSTOR = "Verbrennungskanone";

    public static String DISCOVERS = "Entdeckungen";
    public static String EMPTIES = "entleert";

    public static void generateItems(BiConsumer<Item, String> items) {
        items.accept(FEItems.RESTORATION_WISDOM, getPairing(WISDOM, RESTORATION));
        items.accept(FEItems.DISTRIBUTION_WISDOM, getPairing(WISDOM, DISTRIBUTION));
        items.accept(FEItems.COMPRESSION_WISDOM, getPairing(WISDOM, COMPRESSION));
        items.accept(FEItems.INDUCTION_WISDOM, getPairing(WISDOM, INDUCTION));
        items.accept(FEItems.ENGINEERS_SEAL, "Ingenieursiegel");
        items.accept(FEItems.UNIVERSAL_WISDOM, getPairing(UNIVERSAL, WISDOM.replace(" der", "")));

        items.accept(FEItems.RESTORER, RESTORER);
        items.accept(FEItems.ROUTER, ROUTER);
        ColorCollection.zipApply(ColorCollection.VALUES, FEItems.DYED_ROUTER, (color, item) -> items.accept(item, getPairing(getDyeName(color), ROUTER)));
        items.accept(FEItems.COMPRESSOR, COMPRESSOR);
        items.accept(FEItems.FUEL_CARRIER, "Kohleschütte");
        items.accept(FEItems.INDUCTION_FURNACE, "Induktionsofen");

        items.accept(FEItems.MENDER, MENDER);
        items.accept(FEItems.ENDER_ROUTER, ENDER + ROUTER.toLowerCase());
        items.accept(FEItems.STRIPPER, STRIPPER);
        items.accept(FEItems.COMBUSTOR, COMBUSTOR);
    }

    public static void generateItemTags(BiConsumer<TagKey<Item>, String> tags) {
        tags.accept(FETags.PAPERS, "Papiere");
        tags.accept(FETags.ROUTERS, ROUTER);
        tags.accept(FETags.RESTORATION_WISDOM_DISCOVERS, getPairing(getPairing(DISCOVERS, "der"), getPairing(WISDOM, RESTORATION)));
        tags.accept(FETags.DISTRIBUTION_WISDOM_DISCOVERS, getPairing(getPairing(DISCOVERS, "der"), getPairing(WISDOM, DISTRIBUTION)));
        tags.accept(FETags.COMPRESSION_WISDOM_DISCOVERS, getPairing(getPairing(DISCOVERS, "der"), getPairing(WISDOM, COMPRESSION)));
        tags.accept(FETags.INDUCTION_WISDOM_DISCOVERS, getPairing(getPairing(DISCOVERS, "der"), getPairing(WISDOM, INDUCTION)));
        tags.accept(FETags.UNIVERSAL_WISDOM_DISCOVERS, getPairing(getPairing(DISCOVERS, "der"), getPairing(UNIVERSAL, WISDOM.replace(" der", ""))));
        tags.accept(FETags.ENGINEERS_SEAL_DISCOVERS, getPairing(getPairing(DISCOVERS, "der"), "Ingenieursiegel"));
        tags.accept(FETags.RESTORER_IGNORE_MATERIALS, getPairing(RESTORER, "ignoriert Materialien"));
        tags.accept(FETags.RESTORER_IGNORE_TOOLS, getPairing(RESTORER, "ignoriert Werkzeuge"));
        tags.accept(FETags.ROUTER_IGNORE_ITEMS, getPairing(ROUTER, "ignoriert Gegenstände"));
        tags.accept(FETags.COMPRESSOR_IGNORE_ITEMS, getPairing(COMPRESSOR, "ignoriert Gegenstände"));
        tags.accept(FETags.FUEL_CARRIER_IGNORE_ITEMS, getPairing("Kohleschütte", "ignoriert Gegenstände"));
        tags.accept(FETags.MENDER_IGNORE_TOOLS, getPairing(MENDER, "ignoriert Werkzeuge"));
        tags.accept(FETags.STRIPPER_IGNORE_ITEMS, getPairing(STRIPPER, "ignoriert Gegenstände"));
        tags.accept(FETags.ENDER_ROUTER_IGNORE_ITEMS, getPairing(ENDER + ROUTER.toLowerCase(), "ignoriert Gegenstände"));
    }

    public static void generateBlockTags(BiConsumer<TagKey<Block>, String> tags) {
        tags.accept(FETags.COMPRESSOR_IGNORE_BLOCKS, getPairing(COMPRESSOR, "ignoriert Blöcke"));
    }

    public static void generateSounds(BiConsumer<SoundEvent, String> sounds) {
        sounds.accept(FESounds.RESTORER_DROP_CONTENTS, getPairing(RESTORER, EMPTIES));
        sounds.accept(FESounds.ROUTER_DROP_CONTENTS, getPairing(ROUTER, EMPTIES));
        sounds.accept(FESounds.COMPRESSOR_DROP_CONTENTS, getPairing(COMPRESSOR, EMPTIES));
        sounds.accept(FESounds.FUEL_CARRIER_DROP_CONTENTS, getPairing("Kohleschütte", EMPTIES));
        sounds.accept(FESounds.INDUCTION_FURNACE_DROP_CONTENTS, getPairing("Induktionsofen", EMPTIES));
        sounds.accept(FESounds.MENDER_DROP_CONTENTS, getPairing(MENDER, EMPTIES));
        sounds.accept(FESounds.STRIPPER_DROP_CONTENTS, getPairing(STRIPPER, EMPTIES));
        sounds.accept(FESounds.COMBUSTOR_DROP_CONTENTS, getPairing(COMBUSTOR, EMPTIES));
    }

    public static void generateCustom(BiConsumer<String, String> custom) {
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom", getPairing(WISDOM, ENGINEERING));
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom.discovers", DISCOVERS + ":");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.RESTORER_ID.getPath() + ".empty.description", "Nimmt einen gemischten Stapel Reparaturmaterialien auf");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".device.empty.description", "Nimmt einen gemischten Stapel Filtergegenstände auf");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.FUEL_CARRIER_ID.getPath() + ".empty.description", "Nimmt einen gemischten Stapel Brennstoffartikel auf");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.MENDER_ID.getPath() + ".empty.description", "Nimmt Reparaturwerkzeuge oder Glasflaschen auf");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.COMBUSTOR_ID.getPath() + ".empty.description", "Nimmt Schworzpuiva, Kohle und Lohnstaub auf");

        custom.accept("advancements."  + ForgottenEngineersCommon.MOD_ID + ".adventure." + ForgottenEngineersItems.ENGINEERS_SEAL_ID.getPath() + ".title", "Echos der Vergangenheit");
        custom.accept("advancements."  + ForgottenEngineersCommon.MOD_ID + ".adventure." + ForgottenEngineersItems.ENGINEERS_SEAL_ID.getPath() + ".description", "Finde die Quelle, aus der alle " + getPairing(WISDOM, ENGINEERING) + " entsprang");
    }

    public static String getPairing(String name, String type) {
        return name + " " + type;
    }

    public static String getDyeName(DyeColor color) {
        return switch (color) {
            case BLACK -> "Schwarzer";
            case BLUE -> "Blauer";
            case BROWN -> "Brauner";
            case CYAN -> "Türkiser";
            case GRAY -> "Grauer";
            case GREEN -> "Grüner";
            case LIGHT_BLUE -> "Hellblauer";
            case LIGHT_GRAY -> "Hellgrauer";
            case LIME -> "Hellgrüner";
            case MAGENTA -> "Magenta";
            case ORANGE -> "Oranger";
            case PINK -> "Rosa";
            case PURPLE -> "Violetter";
            case RED -> "Roter";
            case WHITE -> "Weißer";
            case YELLOW -> "Gelber";
        };
    }
}
