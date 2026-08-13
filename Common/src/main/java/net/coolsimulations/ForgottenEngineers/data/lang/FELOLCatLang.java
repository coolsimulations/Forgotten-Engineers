package net.coolsimulations.ForgottenEngineers.data.lang;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.data.FETags;
import net.coolsimulations.ForgottenEngineers.item.FEItems;
import net.coolsimulations.ForgottenEngineers.sounds.FESounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.function.BiConsumer;

public class FELOLCatLang {

    public static String MOD_NAME = "Dez inginears we fogot, soz";

    public static String RESTORATION = "Fix dat";
    public static String DISTRIBUTION = "Dis all mine";
    public static String WISDOM = "Brainz";

    public static String RESTORER = "Fix dat";
    public static String ROUTER = "Go in box";

    public static String DISCOVERS = "Makz dez";
    public static String EMPTIES = "emptied naww catrzzz";

    public static void generateItems(BiConsumer<Item, String> items) {
        items.accept(FEItems.RESTORATION_WISDOM, getPairing(RESTORATION, WISDOM));
        items.accept(FEItems.DISTRIBUTION_WISDOM, getPairing(DISTRIBUTION, WISDOM));
        //items.accept(FEItems.COMPRESSION_WISDOM, getPairing("Da crunch", WISDOM));
        //items.accept(FEItems.INDUCTION_WISDOM, getPairing("Burnee", WISDOM));

        items.accept(FEItems.RESTORER, RESTORER);
        items.accept(FEItems.ROUTER, ROUTER);
        //items.accept(FEItems.COMPRESSOR, "Ooo squishy");
        //items.accept(FEItems.INDUCTION_FURNACE, "Burnee thing");
    }

    public static void generateItemTags(BiConsumer<TagKey<Item>, String> tags) {
        tags.accept(FETags.PAPERS, "Papyruses");
        tags.accept(FETags.RESTORATION_WISDOM_DISCOVERS, getPairing(getPairing(RESTORATION, WISDOM), DISCOVERS.toLowerCase()));
        tags.accept(FETags.DISTRIBUTION_WISDOM_DISCOVERS, getPairing(getPairing(DISTRIBUTION, WISDOM), DISCOVERS.toLowerCase()));
        tags.accept(FETags.RESTORER_IGNORE_MATERIALS, getPairing(RESTORER, "ignaws thingz"));
        tags.accept(FETags.RESTORER_IGNORE_TOOLS, getPairing(RESTORER, "ignaws toolz"));
        tags.accept(FETags.ROUTER_IGNORE_ITEMS, getPairing(ROUTER, "ignaws thingz"));
    }

    public static void generateSounds(BiConsumer<SoundEvent, String> sounds) {
        sounds.accept(FESounds.RESTORER_DROP_CONTENTS, getPairing(RESTORER, EMPTIES));
        sounds.accept(FESounds.ROUTER_DROP_CONTENTS, getPairing(ROUTER, EMPTIES));
    }

    public static void generateCustom(BiConsumer<String, String> custom) {
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom", getPairing("Big", WISDOM));
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom.discovers", DISCOVERS + ":");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".restorer.empty.description", "Kan hold a mikz stacc of fixin thingz :o");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".router.empty.description", "Kan hold a mikz stacc of filta thingz :o");
    }

    public static String getPairing(String name, String type) {
        return name + " " + type;
    }
}
