package net.coolsimulations.ForgottenEngineers.data.lang;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.data.FETags;
import net.coolsimulations.ForgottenEngineers.item.FEItems;
import net.coolsimulations.ForgottenEngineers.sounds.FESounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.function.BiConsumer;

public class FEFrenchLang {

    public static String ENGINEERING = "Ingénierie";

    public static String MOD_NAME = getPairing(ENGINEERING, "Oubliée");

    public static String RESTORATION = "la Restauration";
    public static String DISTRIBUTION = "Tri";
    public static String WISDOM = "Sagesse de";

    public static String RESTORER = "Restaurateur";
    public static String ROUTER = "Trieur";
    public static String INDUCTION = "Induction";

    public static String DISCOVERS = "Découvertes";
    public static String EMPTIES = "vidé";


    public static void generateItems(BiConsumer<Item, String> items) {
        items.accept(FEItems.RESTORATION_WISDOM, getPairing(WISDOM, RESTORATION));
        items.accept(FEItems.DISTRIBUTION_WISDOM, getPairing(WISDOM.replace("de", "du"), DISTRIBUTION));
        //items.accept(FEItems.COMPRESSION_WISDOM, getPairing(WISDOM, "Compression"));
        //items.accept(FEItems.INDUCTION_WISDOM, getPairing(WISDOM, "l'Induction"));

        items.accept(FEItems.RESTORER, RESTORER);
        items.accept(FEItems.ROUTER, ROUTER);
        //items.accept(FEItems.COMPRESSOR, "Compresseur");
        //items.accept(FEItems.INDUCTION_FURNACE, "Four à Induction");
    }

    public static void generateItemTags(BiConsumer<TagKey<Item>, String> tags) {
        tags.accept(FETags.PAPERS, "Papiers");
        tags.accept(FETags.RESTORATION_WISDOM_DISCOVERS, getPairing(getPairing(DISCOVERS, "de la"), getPairing(WISDOM, RESTORATION)));
        tags.accept(FETags.DISTRIBUTION_WISDOM_DISCOVERS, getPairing(getPairing(DISCOVERS, "de la"), getPairing(WISDOM.replace("de", "du"), DISTRIBUTION)));
        tags.accept(FETags.RESTORER_IGNORE_MATERIALS, getPairing(RESTORER, "ignore les matériaux"));
        tags.accept(FETags.RESTORER_IGNORE_TOOLS, getPairing(RESTORER, "ignore les outils"));
        tags.accept(FETags.ROUTER_IGNORE_ITEMS, getPairing(ROUTER, "ignore les objets"));
    }

    public static void generateSounds(BiConsumer<SoundEvent, String> sounds) {
        sounds.accept(FESounds.RESTORER_DROP_CONTENTS, getPairing(RESTORER, EMPTIES));
        sounds.accept(FESounds.ROUTER_DROP_CONTENTS, getPairing(ROUTER, EMPTIES));
    }

    public static void generateCustom(BiConsumer<String, String> custom) {
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom", getPairing(WISDOM, "l'" + ENGINEERING));
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom.discovers", DISCOVERS + ":");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".restorer.empty.description", "Peut contenir une pile de différents matériaux de réparation");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".router.empty.description", "Peut contenir une pile de différents objets de filtre");
    }

    public static String getPairing(String name, String type) {
        return name + " " + type;
    }
}
