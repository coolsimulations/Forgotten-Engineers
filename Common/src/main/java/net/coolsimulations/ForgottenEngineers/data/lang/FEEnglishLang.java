package net.coolsimulations.ForgottenEngineers.data.lang;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.data.FETags;
import net.coolsimulations.ForgottenEngineers.item.FEItems;
import net.coolsimulations.ForgottenEngineers.sounds.FESounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.function.BiConsumer;

public class FEEnglishLang {

    public static String MOD_NAME = ForgottenEngineersCommon.MOD_NAME;

    public static String RESTORATION = "Restoration";
    public static String DISTRIBUTION = "Distribution";
    public static String WISDOM = "Wisdom";

    public static String RESTORER = "Restorer";
    public static String ROUTER = "Router";
    public static String INDUCTION = "Induction";

    public static String DISCOVERS = "Discovers";
    public static String EMPTIES = "empties";

    public static void generateItems(BiConsumer<Item, String> items) {
        items.accept(FEItems.RESTORATION_WISDOM, getPairing(RESTORATION, WISDOM));
        items.accept(FEItems.DISTRIBUTION_WISDOM, getPairing(DISTRIBUTION, WISDOM));
        //items.accept(FEItems.COMPRESSION_WISDOM, getPairing("Compression", WISDOM));
        //items.accept(FEItems.INDUCTION_WISDOM, getPairing(INDUCTION, WISDOM));

        items.accept(FEItems.RESTORER, RESTORER);
        items.accept(FEItems.ROUTER, ROUTER);
        //items.accept(FEItems.COMPRESSOR, "Compressor");
        //items.accept(FEItems.INDUCTION_FURNACE, getPairing(INDUCTION, "Furnace"));
    }

    public static void generateItemTags(BiConsumer<TagKey<Item>, String> tags) {
        tags.accept(FETags.PAPERS, "Papers");
        tags.accept(FETags.RESTORATION_WISDOM_DISCOVERS, getPairing(getPairing(RESTORATION, WISDOM), DISCOVERS));
        tags.accept(FETags.DISTRIBUTION_WISDOM_DISCOVERS, getPairing(getPairing(DISTRIBUTION, WISDOM), DISCOVERS));
        tags.accept(FETags.RESTORER_IGNORE_MATERIALS, getPairing(RESTORER, "Ignore Materials"));
        tags.accept(FETags.RESTORER_IGNORE_TOOLS, getPairing(RESTORER, "Ignore Tools"));
        tags.accept(FETags.ROUTER_IGNORE_ITEMS, getPairing(ROUTER, "Ignore Items"));
    }

    public static void generateSounds(BiConsumer<SoundEvent, String> sounds) {
        sounds.accept(FESounds.RESTORER_DROP_CONTENTS, getPairing(RESTORER, EMPTIES));
        sounds.accept(FESounds.ROUTER_DROP_CONTENTS, getPairing(ROUTER, EMPTIES));
    }

    public static void generateCustom(BiConsumer<String, String> custom) {
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom", getPairing("Engineering", WISDOM));
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom.discovers", DISCOVERS + ":");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".restorer.empty.description", "Can hold a mixed stack of repair materials");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".router.empty.description", "Can hold a mixed stack of filter items");
    }

    public static String getPairing(String name, String type) {
        return name + " " + type;
    }
}
