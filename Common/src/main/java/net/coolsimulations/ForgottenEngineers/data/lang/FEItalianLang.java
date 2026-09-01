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

public class FEItalianLang {

    public static String ENGINEERING = "Ingegneri";

    public static String MOD_NAME = getPairing(ENGINEERING, "dimenticati");

    public static String RESTORATION = "restauro";
    public static String DISTRIBUTION = "smistamento";
    public static String COMPRESSION = "compressione";
    public static String UNIVERSAL = "universale";
    public static String WISDOM = "Sapienza del";
    public static String ENDER = "di ender";

    public static String RESTORER = "Restauratore";
    public static String ROUTER = "Classificatore";
    public static String COMPRESSOR = "Compressore";
    public static String INDUCTION = "induzione";
    public static String MENDER = "Ripristinatore";
    public static String STRIPPER = "Scortecciatore";
    public static String COMBUSTOR = "Cannone a combustione";

    public static String DISCOVERS = "Scoprire";
    public static String EMPTIES = "Vuoti del";

    public static void generateItems(BiConsumer<Item, String> items) {
        items.accept(FEItems.RESTORATION_WISDOM, getPairing(WISDOM, RESTORATION));
        items.accept(FEItems.DISTRIBUTION_WISDOM, getPairing(WISDOM.replace("del", "dello"), DISTRIBUTION));
        items.accept(FEItems.COMPRESSION_WISDOM, getPairing(WISDOM.replace("del", "della"), COMPRESSION));
        items.accept(FEItems.INDUCTION_WISDOM, WISDOM.replace("del", "dell'" +  INDUCTION));
        items.accept(FEItems.ENGINEERS_SEAL, getPairing("Sigillo degli", ENGINEERING.toLowerCase()));
        items.accept(FEItems.UNIVERSAL_WISDOM, getPairing(WISDOM.replace(" del", ""), UNIVERSAL));

        items.accept(FEItems.RESTORER, RESTORER);
        items.accept(FEItems.ROUTER, ROUTER);
        ColorCollection.zipApply(ColorCollection.VALUES, FEItems.DYED_ROUTER, (color, item) -> items.accept(item, getPairing(ROUTER, getDyeName(color))));
        items.accept(FEItems.COMPRESSOR, COMPRESSOR);
        items.accept(FEItems.FUEL_CARRIER, "Secchio per il carbone");
        items.accept(FEItems.INDUCTION_FURNACE, getPairing("Forno a", INDUCTION));

        items.accept(FEItems.MENDER, MENDER);
        items.accept(FEItems.ENDER_ROUTER, getPairing(ROUTER, ENDER));
        items.accept(FEItems.STRIPPER, STRIPPER);
        items.accept(FEItems.COMBUSTOR, COMBUSTOR);
    }

    public static void generateItemTags(BiConsumer<TagKey<Item>, String> tags) {
        tags.accept(FETags.PAPERS, "Carte");
        tags.accept(FETags.ROUTERS, "Classificatori");
        tags.accept(FETags.RESTORATION_WISDOM_DISCOVERS, getPairing(DISCOVERS + " la", getPairing(WISDOM.toLowerCase(), RESTORATION)));
        tags.accept(FETags.DISTRIBUTION_WISDOM_DISCOVERS, getPairing(DISCOVERS + " la", getPairing(WISDOM.replace("del", "dello").toLowerCase(), DISTRIBUTION)));
        tags.accept(FETags.COMPRESSION_WISDOM_DISCOVERS, getPairing(DISCOVERS + " la", getPairing(WISDOM.replace("del", "della").toLowerCase(), COMPRESSION)));
        tags.accept(FETags.INDUCTION_WISDOM_DISCOVERS, getPairing(DISCOVERS + " la", WISDOM.replace("del", "dell'" +  INDUCTION).toLowerCase()));
        tags.accept(FETags.UNIVERSAL_WISDOM_DISCOVERS, getPairing(DISCOVERS + " la", getPairing(WISDOM.replace("del ", "").toLowerCase(), UNIVERSAL)));
        tags.accept(FETags.ENGINEERS_SEAL_DISCOVERS, getPairing(DISCOVERS + "la", getPairing("sigillo degli", ENGINEERING.toLowerCase())));
        tags.accept(FETags.RESTORER_IGNORE_MATERIALS, getPairing(RESTORER, "ignora i materiali"));
        tags.accept(FETags.RESTORER_IGNORE_TOOLS, getPairing(RESTORER, "ignora gli strumenti"));
        tags.accept(FETags.ROUTER_IGNORE_ITEMS, getPairing(ROUTER, "ignora gli oggetti"));
        tags.accept(FETags.COMPRESSOR_IGNORE_ITEMS, getPairing(COMPRESSOR, "ignora gli oggetti"));
        tags.accept(FETags.FUEL_CARRIER_IGNORE_ITEMS, getPairing("Secchio per il carbone", "ignora gli oggetti"));
        tags.accept(FETags.MENDER_IGNORE_TOOLS, getPairing(MENDER, "ignora gli strumenti"));
        tags.accept(FETags.STRIPPER_IGNORE_ITEMS, getPairing(STRIPPER, "ignora gli oggetti"));
        tags.accept(FETags.ENDER_ROUTER_IGNORE_ITEMS, getPairing(getPairing(ENDER, ROUTER), "ignora gli oggetti"));
    }

    public static void generateBlockTags(BiConsumer<TagKey<Block>, String> tags) {
        tags.accept(FETags.COMPRESSOR_IGNORE_BLOCKS, getPairing(COMPRESSOR, "ignora gli blocchi"));
    }

    public static void generateSounds(BiConsumer<SoundEvent, String> sounds) {
        sounds.accept(FESounds.RESTORER_DROP_CONTENTS, getPairing(EMPTIES, RESTORER.toLowerCase()));
        sounds.accept(FESounds.ROUTER_DROP_CONTENTS, getPairing(EMPTIES, ROUTER.toLowerCase()));
        sounds.accept(FESounds.COMPRESSOR_DROP_CONTENTS, getPairing(EMPTIES, COMPRESSOR.toLowerCase()));
        sounds.accept(FESounds.FUEL_CARRIER_DROP_CONTENTS, getPairing(EMPTIES, "secchio per il carbone"));
        sounds.accept(FESounds.INDUCTION_FURNACE_DROP_CONTENTS, getPairing(EMPTIES, getPairing("Forno a", INDUCTION).toLowerCase()));
        sounds.accept(FESounds.MENDER_DROP_CONTENTS, getPairing(EMPTIES, MENDER.toLowerCase()));
        sounds.accept(FESounds.STRIPPER_DROP_CONTENTS, getPairing(EMPTIES, STRIPPER.toLowerCase()));
        sounds.accept(FESounds.COMBUSTOR_DROP_CONTENTS, getPairing(EMPTIES, COMBUSTOR.toLowerCase()));
    }

    public static void generateCustom(BiConsumer<String, String> custom) {
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom", WISDOM.replace("del", "dell'") + ENGINEERING);
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom.discovers", DISCOVERS + ":");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.RESTORER_ID.getPath() + ".empty.description", "Può contenere una pila mista di materiali di riparazione");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".device.empty.description", "Può contenere una pila mista di oggetti filtro");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.FUEL_CARRIER_ID.getPath() + ".empty.description", "Può contenere una pila mista di oggetti combustibili");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.MENDER_ID.getPath() + ".empty.description", "Può contenere attrezzi da riparazione o un'ampolla");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.COMBUSTOR_ID.getPath() + ".empty.description", "Può contenere polvere da sparo, carbone e polvere di blaze");

        custom.accept("advancements."  + ForgottenEngineersCommon.MOD_ID + ".adventure." + ForgottenEngineersItems.ENGINEERS_SEAL_ID.getPath() + ".title", "Echi del passato");
        custom.accept("advancements."  + ForgottenEngineersCommon.MOD_ID + ".adventure." + ForgottenEngineersItems.ENGINEERS_SEAL_ID.getPath() + ".description", "Trova la fonte da cui discende tutta la saggezza ingegneristica");
    }

    public static String getPairing(String name, String type) {
        return name + " " + type;
    }

    public static String getDyeName(DyeColor color) {
        return switch (color) {
            case BLACK -> "nero";
            case BLUE -> "blu";
            case BROWN -> "marrone";
            case CYAN -> "ciano";
            case GRAY -> "grigio";
            case GREEN -> "verda";
            case LIGHT_BLUE -> "azzurro";
            case LIGHT_GRAY -> "grigio chiaro";
            case LIME -> "lime";
            case MAGENTA -> "magenta";
            case ORANGE -> "arancione";
            case PINK -> "rosa";
            case PURPLE -> "viola";
            case RED -> "rosso";
            case WHITE -> "bianco";
            case YELLOW -> "giallo";
        };
    }
}
