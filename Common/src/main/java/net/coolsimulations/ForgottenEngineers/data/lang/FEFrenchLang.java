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

public class FEFrenchLang {

    public static String ENGINEERING = "Ingénierie";

    public static String MOD_NAME = getPairing(ENGINEERING, "Oubliée");

    public static String RESTORATION = "la restauration";
    public static String DISTRIBUTION = "tri";
    public static String COMPRESSION = "compression";
    public static String UNIVERSAL = "universelle";
    public static String WISDOM = "Sagesse de";
    public static String ENDER = "l'Ender";

    public static String RESTORER = "Restaurateur";
    public static String ROUTER = "Trieur";
    public static String COMPRESSOR = "Compresseur";
    public static String INDUCTION = "l'induction";
    public static String MENDER = "Réparateur";
    public static String STRIPPER = "Écorcer";
    public static String COMBUSTOR = "Canon à combustion";

    public static String DISCOVERS = "Découvertes";
    public static String EMPTIES = "vidé";

    public static void generateItems(BiConsumer<Item, String> items) {
        items.accept(FEItems.RESTORATION_WISDOM, getPairing(WISDOM, RESTORATION));
        items.accept(FEItems.DISTRIBUTION_WISDOM, getPairing(WISDOM.replace("de", "du"), DISTRIBUTION));
        items.accept(FEItems.COMPRESSION_WISDOM, getPairing(WISDOM, COMPRESSION));
        items.accept(FEItems.INDUCTION_WISDOM, getPairing(WISDOM, INDUCTION));
        items.accept(FEItems.ENGINEERS_SEAL, getPairing("Sceau des", "ingénieurs"));
        items.accept(FEItems.UNIVERSAL_WISDOM, getPairing(WISDOM.replace(" de", ""), UNIVERSAL));

        items.accept(FEItems.RESTORER, RESTORER);
        items.accept(FEItems.ROUTER, ROUTER);
        ColorCollection.zipApply(ColorCollection.VALUES, FEItems.DYED_ROUTER, (color, item) -> items.accept(item, getPairing(ROUTER, getDyeName(color))));
        items.accept(FEItems.COMPRESSOR, COMPRESSOR);
        items.accept(FEItems.FUEL_CARRIER, "Boîte à combustible");
        items.accept(FEItems.INDUCTION_FURNACE, "Four à induction");

        items.accept(FEItems.MENDER, MENDER);
        items.accept(FEItems.ENDER_ROUTER, getPairing(ROUTER, ENDER));
        items.accept(FEItems.STRIPPER, STRIPPER);
        items.accept(FEItems.COMBUSTOR, COMBUSTOR);
    }

    public static void generateItemTags(BiConsumer<TagKey<Item>, String> tags) {
        tags.accept(FETags.PAPERS, "Papiers");
        tags.accept(FETags.ROUTERS, "Trieuses");
        tags.accept(FETags.RESTORATION_WISDOM_DISCOVERS, getPairing(getPairing(DISCOVERS, "de la"), getPairing(WISDOM.toLowerCase(), RESTORATION)));
        tags.accept(FETags.DISTRIBUTION_WISDOM_DISCOVERS, getPairing(getPairing(DISCOVERS, "de la"), getPairing(WISDOM.toLowerCase().replace("de", "du"), DISTRIBUTION)));
        tags.accept(FETags.COMPRESSION_WISDOM_DISCOVERS, getPairing(getPairing(DISCOVERS, "de la"), getPairing(WISDOM.toLowerCase(), COMPRESSION)));
        tags.accept(FETags.INDUCTION_WISDOM_DISCOVERS, getPairing(getPairing(DISCOVERS, "de la"), getPairing(WISDOM.toLowerCase(), INDUCTION)));
        tags.accept(FETags.UNIVERSAL_WISDOM_DISCOVERS, getPairing(getPairing(DISCOVERS, "de la"), getPairing(WISDOM.toLowerCase().replace(" de", ""), UNIVERSAL)));
        tags.accept(FETags.ENGINEERS_SEAL_DISCOVERS, getPairing(getPairing(DISCOVERS, "de la"), getPairing("sceau des", "ingénieurs")));
        tags.accept(FETags.RESTORER_IGNORE_MATERIALS, getPairing(RESTORER, "ignore les matériaux"));
        tags.accept(FETags.RESTORER_IGNORE_TOOLS, getPairing(RESTORER, "ignore les outils"));
        tags.accept(FETags.ROUTER_IGNORE_ITEMS, getPairing(ROUTER, "ignore les objets"));
        tags.accept(FETags.COMPRESSOR_IGNORE_ITEMS, getPairing(COMPRESSOR, "ignore les objets"));
        tags.accept(FETags.FUEL_CARRIER_IGNORE_ITEMS, getPairing("Boîte à combustible", "ignore les objets"));
        tags.accept(FETags.MENDER_IGNORE_TOOLS, getPairing(MENDER, "ignore les outils"));
        tags.accept(FETags.STRIPPER_IGNORE_ITEMS, getPairing(STRIPPER, "ignore les objets"));
        tags.accept(FETags.ENDER_ROUTER_IGNORE_ITEMS, getPairing(getPairing(ROUTER, ENDER), "ignore les objets"));
    }

    public static void generateBlockTags(BiConsumer<TagKey<Block>, String> tags) {
        tags.accept(FETags.COMPRESSOR_IGNORE_BLOCKS, getPairing(COMPRESSOR, "ignore les blocs"));
    }

    public static void generateSounds(BiConsumer<SoundEvent, String> sounds) {
        sounds.accept(FESounds.RESTORER_DROP_CONTENTS, getPairing(RESTORER, EMPTIES));
        sounds.accept(FESounds.ROUTER_DROP_CONTENTS, getPairing(ROUTER, EMPTIES));
        sounds.accept(FESounds.COMPRESSOR_DROP_CONTENTS, getPairing(COMPRESSOR, EMPTIES));
        sounds.accept(FESounds.FUEL_CARRIER_DROP_CONTENTS, getPairing("Boîte à combustible", EMPTIES));
        sounds.accept(FESounds.INDUCTION_FURNACE_DROP_CONTENTS, getPairing("Four à induction", EMPTIES));
        sounds.accept(FESounds.MENDER_DROP_CONTENTS, getPairing(MENDER, EMPTIES));
        sounds.accept(FESounds.STRIPPER_DROP_CONTENTS, getPairing(STRIPPER, EMPTIES));
        sounds.accept(FESounds.COMBUSTOR_DROP_CONTENTS, getPairing(COMBUSTOR, EMPTIES));
    }

    public static void generateCustom(BiConsumer<String, String> custom) {
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom", getPairing(WISDOM, "l'" + ENGINEERING.toLowerCase()));
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom.discovers", DISCOVERS + ":");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.RESTORER_ID.getPath() + ".empty.description", "Peut contenir une pile de différents matériaux de réparation");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".device.empty.description", "Peut contenir une pile de différents objets de filtre");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.FUEL_CARRIER_ID.getPath() + ".empty.description", "Peut contenir une pile de différents combustibles");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.MENDER_ID.getPath() + ".empty.description", "Peut contenir des outils de raccommodage ou des fioles en verre");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.COMBUSTOR_ID.getPath() + ".empty.description", "Peut contenir de la poudre à canon, du charbon et de la poudre de blaze");

        custom.accept("advancements."  + ForgottenEngineersCommon.MOD_ID + ".adventure." + ForgottenEngineersItems.ENGINEERS_SEAL_ID.getPath() + ".title", "Échos du passé");
        custom.accept("advancements."  + ForgottenEngineersCommon.MOD_ID + ".adventure." + ForgottenEngineersItems.ENGINEERS_SEAL_ID.getPath() + ".description", "Trouvez la source de toute la " + getPairing(WISDOM.replace("de", "en"), ENGINEERING.toLowerCase()));
    }

    public static String getPairing(String name, String type) {
        return name + " " + type;
    }

    public static String getDyeName(DyeColor color) {
        return switch (color) {
            case BLACK -> "noir";
            case BLUE -> "bleu";
            case BROWN -> "marron";
            case CYAN -> "cyan";
            case GRAY -> "gris";
            case GREEN -> "vert";
            case LIGHT_BLUE -> "bleu clair";
            case LIGHT_GRAY -> "gris clair";
            case LIME -> "vert clair";
            case MAGENTA -> "magenta";
            case ORANGE -> "orange";
            case PINK -> "rose";
            case PURPLE -> "violet";
            case RED -> "rouge";
            case WHITE -> "blanc";
            case YELLOW -> "jaune";
        };
    }
}
