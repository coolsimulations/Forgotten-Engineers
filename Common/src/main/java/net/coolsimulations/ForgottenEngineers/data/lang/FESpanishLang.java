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

public class FESpanishLang {

    public static String MOD_NAME = "Ingeniería olvidada";

    public static String RESTORATION = "restauradora";
    public static String DISTRIBUTION = "clasificación";
    public static String WISDOM = "Sabiduría de";

    public static String RESTORER = "Restaurador";
    public static String ROUTER = "Clasificador";
    public static String INDUCTION = "inducción";

    public static String DISCOVERS = "Descubres";
    public static String EMPTIES = "vaciado";

    public static void generateItems(BiConsumer<Item, String> items) {
        items.accept(FEItems.RESTORATION_WISDOM, getPairing(WISDOM, RESTORATION));
        items.accept(FEItems.DISTRIBUTION_WISDOM, getPairing(WISDOM, DISTRIBUTION));
        //items.accept(FEItems.COMPRESSION_WISDOM, getPairing(WISDOM, "compresión"));
        //items.accept(FEItems.INDUCTION_WISDOM, getPairing(WISDOM, INDUCTION));

        items.accept(FEItems.RESTORER, RESTORER);
        items.accept(FEItems.ROUTER, ROUTER);
        //items.accept(FEItems.COMPRESSOR, "Compresor");
        //items.accept(FEItems.INDUCTION_FURNACE, getPairing("Horno de", INDUCTION));
    }

    public static void generateItemTags(BiConsumer<TagKey<Item>, String> tags) {
        tags.accept(FETags.PAPERS, "Papeles");
        tags.accept(FETags.RESTORATION_WISDOM_DISCOVERS, "La " + WISDOM.toLowerCase() + " la restauración descubre");
        tags.accept(FETags.DISTRIBUTION_WISDOM_DISCOVERS, "La " + WISDOM.toLowerCase() + " la clasificación descubre");
        tags.accept(FETags.RESTORER_IGNORE_MATERIALS, "El " + RESTORATION.toLowerCase() + " ignora los materiales");
        tags.accept(FETags.RESTORER_IGNORE_TOOLS, "El " + RESTORATION.toLowerCase() + " ignora los herramientas");
        tags.accept(FETags.ROUTER_IGNORE_ITEMS, "El " + ROUTER.toLowerCase() + " ignora los objetos");
    }

    public static void generateSounds(BiConsumer<SoundEvent, String> sounds) {
        sounds.accept(FESounds.RESTORER_DROP_CONTENTS, getPairing(RESTORER, EMPTIES));
        sounds.accept(FESounds.ROUTER_DROP_CONTENTS, getPairing(ROUTER, EMPTIES));
    }

    public static void generateCustom(BiConsumer<String, String> custom) {
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom", getPairing(WISDOM, "ingeniería"));
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom.discovers", DISCOVERS + ":");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".restorer.empty.description", "Puede contener una pila mixta de materiales de reparación");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".router.empty.description", "Puede contener una pila mixta de objetos de filtro");
    }

    public static String getPairing(String name, String type) {
        return name + " " + type;
    }
}
