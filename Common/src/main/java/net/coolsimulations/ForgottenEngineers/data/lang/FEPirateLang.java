package net.coolsimulations.ForgottenEngineers.data.lang;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.data.FETags;
import net.coolsimulations.ForgottenEngineers.item.FEItems;
import net.coolsimulations.ForgottenEngineers.sounds.FESounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.function.BiConsumer;

public class FEPirateLang {

    public static String MOD_NAME = "Lost Tinkers o' the Deep";

    public static String RESTORATION = "Renewal";
    public static String DISTRIBUTION = "Share of the Booty";
    public static String WISDOM = "Savvy";

    public static String RESTORER = "Patch-Kit";
    public static String ROUTER = "Divvying Rod";

    public static String DISCOVERS = "Unearths";
    public static String EMPTIES = "be emptied";

    public static void generateItems(BiConsumer<Item, String> items) {
        items.accept(FEItems.RESTORATION_WISDOM, getPairing(RESTORATION, WISDOM));
        items.accept(FEItems.DISTRIBUTION_WISDOM, getPairing(DISTRIBUTION, WISDOM));
        //items.accept(FEItems.COMPRESSION_WISDOM, getPairing("Squashin'", WISDOM));
        //items.accept(FEItems.INDUCTION_WISDOM, getPairing("Blastin' Heat", WISDOM));

        items.accept(FEItems.RESTORER, RESTORER);
        items.accept(FEItems.ROUTER, ROUTER);
        //items.accept(FEItems.COMPRESSOR, "Booty Squeezer");
        //items.accept(FEItems.INDUCTION_FURNACE, "Hell-Fire Cooker");
    }

    public static void generateItemTags(BiConsumer<TagKey<Item>, String> tags) {
        tags.accept(FETags.PAPERS, "Parchments");
        tags.accept(FETags.RESTORATION_WISDOM_DISCOVERS, getPairing(getPairing(RESTORATION, WISDOM), DISCOVERS));
        tags.accept(FETags.DISTRIBUTION_WISDOM_DISCOVERS, getPairing(getPairing(DISTRIBUTION, WISDOM), DISCOVERS));
        tags.accept(FETags.RESTORER_IGNORE_MATERIALS, getPairing(RESTORER, "Ignore Materials"));
        tags.accept(FETags.RESTORER_IGNORE_TOOLS, getPairing(RESTORER, "Ignore Tools"));
        tags.accept(FETags.ROUTER_IGNORE_ITEMS, getPairing(ROUTER, "Ignore Plunder"));
    }

    public static void generateSounds(BiConsumer<SoundEvent, String> sounds) {
        sounds.accept(FESounds.RESTORER_DROP_CONTENTS, getPairing(RESTORER, EMPTIES));
        sounds.accept(FESounds.ROUTER_DROP_CONTENTS, getPairing(ROUTER, EMPTIES));
    }

    public static void generateCustom(BiConsumer<String, String> custom) {
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom", getPairing("Tinkerin'", WISDOM));
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom.discovers", DISCOVERS + ":");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".restorer.empty.description", "Holds mixed amounts o' repair materials");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".router.empty.description", "Holds mixed amounts o' sortin' plunder");
    }

    public static String getPairing(String name, String type) {
        return name + " " + type;
    }
}
