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
import org.apache.commons.lang3.text.WordUtils;

import java.util.function.BiConsumer;

public class FEEnglishLang {

    public static String MOD_NAME = ForgottenEngineersCommon.MOD_NAME;

    public static String ENGINEERING = "Engineering";
    public static String RESTORATION = "Restoration";
    public static String DISTRIBUTION = "Distribution";
    public static String COMPRESSION = "Compression";
    public static String UNIVERSAL = "Universal";
    public static String WISDOM = "Wisdom";
    public static String ENDER = "Ender";

    public static String RESTORER = "Restorer";
    public static String ROUTER = "Router";
    public static String COMPRESSOR = "Compressor";
    public static String INDUCTION = "Induction";
    public static String MENDER = "Mender";
    public static String STRIPPER = "Stripper";
    public static String COMBUSTOR = "Combustor";

    public static String DISCOVERS = "Discovers";
    public static String EMPTIES = "empties";

    public static void generateItems(BiConsumer<Item, String> items) {
        items.accept(FEItems.RESTORATION_WISDOM, getPairing(RESTORATION, WISDOM));
        items.accept(FEItems.DISTRIBUTION_WISDOM, getPairing(DISTRIBUTION, WISDOM));
        items.accept(FEItems.COMPRESSION_WISDOM, getPairing(COMPRESSION, WISDOM));
        items.accept(FEItems.INDUCTION_WISDOM, getPairing(INDUCTION, WISDOM));
        items.accept(FEItems.ENGINEERS_SEAL, getPairing("Engineers'", "Seal"));
        items.accept(FEItems.UNIVERSAL_WISDOM, getPairing(UNIVERSAL, WISDOM));

        items.accept(FEItems.RESTORER, RESTORER);
        items.accept(FEItems.ROUTER, ROUTER);
        ColorCollection.zipApply(ColorCollection.VALUES, FEItems.DYED_ROUTER, (color, item) -> items.accept(item, getPairing(getDyeName(color), ROUTER)));
        items.accept(FEItems.COMPRESSOR, COMPRESSOR);
        items.accept(FEItems.FUEL_CARRIER, "Fuel Carrier");
        items.accept(FEItems.INDUCTION_FURNACE, getPairing(INDUCTION, "Furnace"));

        items.accept(FEItems.MENDER, MENDER);
        items.accept(FEItems.ENDER_ROUTER, getPairing(ENDER, ROUTER));
        items.accept(FEItems.STRIPPER, STRIPPER);
        items.accept(FEItems.COMBUSTOR, COMBUSTOR);
    }

    public static void generateItemTags(BiConsumer<TagKey<Item>, String> tags) {
        tags.accept(FETags.PAPERS, "Papers");
        tags.accept(FETags.ROUTERS, ROUTER + "s");
        tags.accept(FETags.RESTORATION_WISDOM_DISCOVERS, getPairing(getPairing(RESTORATION, WISDOM), DISCOVERS));
        tags.accept(FETags.DISTRIBUTION_WISDOM_DISCOVERS, getPairing(getPairing(DISTRIBUTION, WISDOM), DISCOVERS));
        tags.accept(FETags.COMPRESSION_WISDOM_DISCOVERS, getPairing(getPairing(COMPRESSION, WISDOM), DISCOVERS));
        tags.accept(FETags.INDUCTION_WISDOM_DISCOVERS, getPairing(getPairing(INDUCTION, WISDOM), DISCOVERS));
        tags.accept(FETags.UNIVERSAL_WISDOM_DISCOVERS, getPairing(getPairing(UNIVERSAL, WISDOM), DISCOVERS));
        tags.accept(FETags.ENGINEERS_SEAL_DISCOVERS, getPairing(getPairing("Engineers'", "Seal"), DISCOVERS));
        tags.accept(FETags.RESTORER_IGNORE_MATERIALS, getPairing(RESTORER, "Ignore Materials"));
        tags.accept(FETags.RESTORER_IGNORE_TOOLS, getPairing(RESTORER, "Ignore Tools"));
        tags.accept(FETags.ROUTER_IGNORE_ITEMS, getPairing(ROUTER, "Ignore Items"));
        tags.accept(FETags.COMPRESSOR_IGNORE_ITEMS, getPairing(COMPRESSOR, "Ignore Items"));
        tags.accept(FETags.FUEL_CARRIER_IGNORE_ITEMS, getPairing("Fuel Carrier", "Ignore Items"));
        tags.accept(FETags.MENDER_IGNORE_TOOLS, getPairing(MENDER, "Ignore Tools"));
        tags.accept(FETags.STRIPPER_IGNORE_ITEMS, getPairing(STRIPPER, "Ignore Items"));
        tags.accept(FETags.ENDER_ROUTER_IGNORE_ITEMS, getPairing(getPairing(ENDER, ROUTER), "Ignore Items"));
    }

    public static void generateBlockTags(BiConsumer<TagKey<Block>, String> tags) {
        tags.accept(FETags.COMPRESSOR_IGNORE_BLOCKS, getPairing(COMPRESSOR, "Ignore Blocks"));
    }

    public static void generateSounds(BiConsumer<SoundEvent, String> sounds) {
        sounds.accept(FESounds.RESTORER_DROP_CONTENTS, getPairing(RESTORER, EMPTIES));
        sounds.accept(FESounds.ROUTER_DROP_CONTENTS, getPairing(ROUTER, EMPTIES));
        sounds.accept(FESounds.COMPRESSOR_DROP_CONTENTS, getPairing(COMPRESSOR, EMPTIES));
        sounds.accept(FESounds.FUEL_CARRIER_DROP_CONTENTS, getPairing("Fuel Carrier", EMPTIES));
        sounds.accept(FESounds.INDUCTION_FURNACE_DROP_CONTENTS, getPairing(getPairing(INDUCTION, "Furnace"), EMPTIES));
        sounds.accept(FESounds.MENDER_DROP_CONTENTS, getPairing(MENDER, EMPTIES));
        sounds.accept(FESounds.STRIPPER_DROP_CONTENTS, getPairing(STRIPPER, EMPTIES));
        sounds.accept(FESounds.COMBUSTOR_DROP_CONTENTS, getPairing(COMBUSTOR, EMPTIES));
    }

    public static void generateCustom(BiConsumer<String, String> custom) {
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom", getPairing(ENGINEERING, WISDOM));
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom.discovers", DISCOVERS + ":");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.RESTORER_ID.getPath() + ".empty.description", "Can hold a mixed stack of repair materials");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".device.empty.description", "Can hold a mixed stack of filter items");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.FUEL_CARRIER_ID.getPath() + ".empty.description", "Can hold a mixed stack of fuel items");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.MENDER_ID.getPath() + ".empty.description", "Can hold mending tools or glass bottles");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.COMBUSTOR_ID.getPath() + ".empty.description", "Can hold gunpowder, coal, and blaze powder");

        custom.accept("advancements."  + ForgottenEngineersCommon.MOD_ID + ".adventure." + ForgottenEngineersItems.ENGINEERS_SEAL_ID.getPath() + ".title", "Echoes of the Past");
        custom.accept("advancements."  + ForgottenEngineersCommon.MOD_ID + ".adventure." + ForgottenEngineersItems.ENGINEERS_SEAL_ID.getPath() + ".description", "Find the source from which all " + getPairing(ENGINEERING, WISDOM) + " descended");
    }

    public static String getPairing(String name, String type) {
        return name + " " + type;
    }

    public static String getDyeName(DyeColor color) {
        return WordUtils.capitalize(color.getName().replace('_', ' '));
    }

    public static String convertToCommonwealth(String translation) {
        return translation.replace("Gray", "Grey");
    }
}
