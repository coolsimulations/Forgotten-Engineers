package net.coolsimulations.ForgottenEngineers.data.lang;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.data.FETags;
import net.coolsimulations.ForgottenEngineers.item.FEItems;
import net.coolsimulations.ForgottenEngineers.sounds.FESounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.function.BiConsumer;

public class FEGermanLang {

    public static String ENGINEERING = "Ingenieurkunst";

    public static String MOD_NAME = getPairing("Vergessene", ENGINEERING);

    public static String RESTORATION = "Restauration";
    public static String DISTRIBUTION = "Sortierung";
    public static String WISDOM = "Weisheit der";

    public static String RESTORER = "Restaurator";
    public static String ROUTER = "Sortierer";

    public static String DISCOVERS = "Entdeckungen";
    public static String EMPTIES = "entleert";

    public static void generateItems(BiConsumer<Item, String> items) {
        items.accept(FEItems.RESTORATION_WISDOM, getPairing(WISDOM, RESTORATION));
        items.accept(FEItems.DISTRIBUTION_WISDOM, getPairing(WISDOM, DISTRIBUTION));
        //items.accept(FEItems.COMPRESSION_WISDOM, getPairing(WISDOM, "Kompression"));
        //items.accept(FEItems.INDUCTION_WISDOM, getPairing(WISDOM, "Induktion"));

        items.accept(FEItems.RESTORER, RESTORER);
        items.accept(FEItems.ROUTER, ROUTER);
        //items.accept(FEItems.COMPRESSOR, "Kompressor");
        //items.accept(FEItems.INDUCTION_FURNACE, "Induktionsofen");
    }

    public static void generateItemTags(BiConsumer<TagKey<Item>, String> tags) {
        tags.accept(FETags.PAPERS, "Papiere");
        tags.accept(FETags.RESTORATION_WISDOM_DISCOVERS, getPairing(getPairing(DISCOVERS, "der"), getPairing(WISDOM, RESTORATION)));
        tags.accept(FETags.DISTRIBUTION_WISDOM_DISCOVERS, getPairing(getPairing(DISCOVERS, "der"), getPairing(WISDOM, DISTRIBUTION)));
        tags.accept(FETags.RESTORER_IGNORE_MATERIALS, getPairing(RESTORER, "ignoriert Materialien"));
        tags.accept(FETags.RESTORER_IGNORE_TOOLS, getPairing(RESTORER, "ignoriert Werkzeuge"));
        tags.accept(FETags.ROUTER_IGNORE_ITEMS, getPairing(ROUTER, "ignoriert Gegenstände"));
    }

    public static void generateSounds(BiConsumer<SoundEvent, String> sounds) {
        sounds.accept(FESounds.RESTORER_DROP_CONTENTS, getPairing(RESTORER, EMPTIES));
        sounds.accept(FESounds.ROUTER_DROP_CONTENTS, getPairing(ROUTER, EMPTIES));
    }

    public static void generateCustom(BiConsumer<String, String> custom) {
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom", getPairing(WISDOM, ENGINEERING));
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom.discovers", DISCOVERS + ":");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".restorer.empty.description", "Nimmt einen gemischten Stapel Reparaturmaterialien auf");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".router.empty.description", "Nimmt einen gemischten Stapel Filtergegenstände auf");
    }

    public static String getPairing(String name, String type) {
        return name + " " + type;
    }
}
