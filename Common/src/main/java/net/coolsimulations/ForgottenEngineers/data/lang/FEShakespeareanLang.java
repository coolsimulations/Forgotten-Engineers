package net.coolsimulations.ForgottenEngineers.data.lang;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.data.FETags;
import net.coolsimulations.ForgottenEngineers.item.FEItems;
import net.coolsimulations.ForgottenEngineers.sounds.FESounds;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.function.BiConsumer;

public class FEShakespeareanLang {

    public static String MOD_NAME = "Unremembered Mechanics";

    public static String RESTORATION = "Renewal";
    public static String DISTRIBUTION = "Allotment";
    public static String WISDOM = "Lore of";

    public static String RESTORER = "Restorer";
    public static String ROUTER = "Allotter";

    public static String DISCOVERS = "Discovers";
    public static String EMPTIES = "empties";

    public static void generateItems(BiConsumer<Item, String> items) {
        items.accept(FEItems.RESTORATION_WISDOM, getPairing(WISDOM, RESTORATION));
        items.accept(FEItems.DISTRIBUTION_WISDOM, getPairing(WISDOM, DISTRIBUTION));
        //items.accept(FEItems.COMPRESSION_WISDOM, getPairing(WISDOM, "Condensation"));
        //items.accept(FEItems.INDUCTION_WISDOM, getPairing(WISDOM, "Quickening Hearth"));

        items.accept(FEItems.RESTORER, RESTORER);
        items.accept(FEItems.ROUTER, ROUTER);
        //items.accept(FEItems.COMPRESSOR, "Condenser");
        //items.accept(FEItems.INDUCTION_FURNACE, "Quickening Forge");
    }

    public static void generateItemTags(BiConsumer<TagKey<Item>, String> tags) {
        tags.accept(FETags.PAPERS, "Papers");
        tags.accept(FETags.RESTORATION_WISDOM_DISCOVERS, getPairing(getPairing(WISDOM, RESTORATION), DISCOVERS));
        tags.accept(FETags.DISTRIBUTION_WISDOM_DISCOVERS, getPairing(getPairing(WISDOM, DISTRIBUTION), DISCOVERS));
        tags.accept(FETags.RESTORER_IGNORE_MATERIALS, getPairing(RESTORER, "Ignore Wares"));
        tags.accept(FETags.RESTORER_IGNORE_TOOLS, getPairing(RESTORER, "Ignore Tools"));
        tags.accept(FETags.ROUTER_IGNORE_ITEMS, getPairing(ROUTER, "Ignore Wares"));
    }

    public static void generateSounds(BiConsumer<SoundEvent, String> sounds) {
        sounds.accept(FESounds.RESTORER_DROP_CONTENTS, getPairing(RESTORER, EMPTIES));
        sounds.accept(FESounds.ROUTER_DROP_CONTENTS, getPairing(ROUTER, EMPTIES));
    }

    public static void generateCustom(BiConsumer<String, String> custom) {
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom", "Engine-Artistry");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom.discovers", DISCOVERS + ":");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".restorer.empty.description", "Canst bear a mingled heap of repair wares");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".router.empty.description", "Canst bear a mingled heap of sifting wares");
    }

    public static String getPairing(String name, String type) {
        return name + " " + type;
    }
}
