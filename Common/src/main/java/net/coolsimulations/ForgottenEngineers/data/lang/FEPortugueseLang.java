package net.coolsimulations.ForgottenEngineers.data.lang;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.data.FETags;
import net.coolsimulations.ForgottenEngineers.item.FEItems;
import net.coolsimulations.ForgottenEngineers.sounds.FESounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.function.BiConsumer;

public class FEPortugueseLang {

    public static String MOD_NAME = "Engenharia Esquecida";

    public static String RESTORATION = "Restauração";
    public static String DISTRIBUTION = "Classificação";
    public static String WISDOM = "Sabedoria de";

    public static String RESTORER = "Restaurador";
    public static String ROUTER = "Classificador";
    public static String INDUCTION = "Indução";

    public static String DISCOVERS = "Descobertas";
    public static String EMPTIES = "esvaziada";

    public static void generateItems(BiConsumer<Item, String> items) {
        items.accept(FEItems.RESTORATION_WISDOM, getPairing(WISDOM, RESTORATION));
        items.accept(FEItems.DISTRIBUTION_WISDOM, getPairing(WISDOM, DISTRIBUTION));
        //items.accept(FEItems.COMPRESSION_WISDOM, getPairing(WISDOM, "Compressão"));
        //items.accept(FEItems.INDUCTION_WISDOM, getPairing(WISDOM, INDUCTION));

        items.accept(FEItems.RESTORER, RESTORER);
        items.accept(FEItems.ROUTER, ROUTER);
        //items.accept(FEItems.COMPRESSOR, "Compressor");
        //items.accept(FEItems.INDUCTION_FURNACE, getPairing("Forno de", INDUCTION));
    }

    public static void generateItemTags(BiConsumer<TagKey<Item>, String> tags) {
        tags.accept(FETags.PAPERS, "Papéis");
        tags.accept(FETags.RESTORATION_WISDOM_DISCOVERS, getPairing(getPairing(DISCOVERS, "de"), getPairing(WISDOM.replace("de", "da"), RESTORATION)));
        tags.accept(FETags.DISTRIBUTION_WISDOM_DISCOVERS, getPairing(getPairing(DISCOVERS, "de"), getPairing(WISDOM.replace("de", "da"), DISTRIBUTION)));
        tags.accept(FETags.RESTORER_IGNORE_MATERIALS, getPairing(RESTORER, "ignora materiais"));
        tags.accept(FETags.RESTORER_IGNORE_TOOLS, getPairing(RESTORER, "ignora ferramentas"));
        tags.accept(FETags.ROUTER_IGNORE_ITEMS, getPairing(ROUTER, "ignora itens"));
    }

    public static void generateSounds(BiConsumer<SoundEvent, String> sounds) {
        sounds.accept(FESounds.RESTORER_DROP_CONTENTS, getPairing(RESTORER, EMPTIES));
        sounds.accept(FESounds.ROUTER_DROP_CONTENTS, getPairing(ROUTER, EMPTIES));
    }

    public static void generateCustom(BiConsumer<String, String> custom) {
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom", getPairing(WISDOM, "Engenharia"));
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom.discovers", DISCOVERS + ":");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".restorer.empty.description", "Pode carregar um conjunto diverso de materiais de reparo");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".router.empty.description", "Pode carregar um conjunto diverso de itens filtrantes");
    }

    public static String getPairing(String name, String type) {
        return name + " " + type;
    }
}
