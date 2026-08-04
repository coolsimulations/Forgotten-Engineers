package net.coolsimulations.ForgottenEngineers.data.lang;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.item.FEItems;
import net.minecraft.world.item.Item;

import java.util.function.BiConsumer;

public class FEShakespeareanLang {

    public static String MOD_NAME = "Unremembered Mechanics";

    public static String WISDOM = "Lore of";

    public static void generateItems(BiConsumer<Item, String> items) {
        items.accept(FEItems.RESTORATION_WISDOM, getPairing(WISDOM, "Renewal"));
        //items.accept(FEItems.DISTRIBUTION_WISDOM, getPairing(WISDOM, "Allotment"));
        //items.accept(FEItems.COMPRESSION_WISDOM, getPairing(WISDOM, "Condensation"));
        //items.accept(FEItems.INDUCTION_WISDOM, getPairing(WISDOM, "Quickening Hearth"));

        items.accept(FEItems.RESTORER, "Restorer");
        //items.accept(FEItems.ROUTER, "Allotter");
        //items.accept(FEItems.COMPRESSOR, "Condenser");
        //items.accept(FEItems.INDUCTION_FURNACE, "Quickening Forge");
    }

    public static void generateCustom(BiConsumer<String, String> custom) {
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom", "Engine-Artistry");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom.discovers", "Discovers:");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".restorer.empty.description", "Canst bear a mingled heap of repair wares");
    }

    public static String getPairing(String name, String type) {
        return name + " " + type;
    }
}
