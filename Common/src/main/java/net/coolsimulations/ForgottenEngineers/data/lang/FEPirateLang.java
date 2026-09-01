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
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

import java.util.function.BiConsumer;

public class FEPirateLang {

    public static String MOD_NAME = "Lost Tinkers o' the Deep";

    public static String RESTORATION = "Renewal";
    public static String DISTRIBUTION = "Share of the booty";
    public static String COMPRESSION = "Squashin'";
    public static String UNIVERSAL = "Seven Seas";
    public static String WISDOM = "savvy";
    public static String ENDER = "Ender";

    public static String RESTORER = "Patch-kit";
    public static String ROUTER = "Divvying rod";
    public static String COMPRESSOR = "Booty squeezer";
    public static String INDUCTION = "Blastin' heat";
    public static String MENDER = "Renovator";
    public static String STRIPPER = "Barker";
    public static String COMBUSTOR = "Cannon";

    public static String DISCOVERS = "Unearths";
    public static String EMPTIES = "be emptied";

    public static void generateItems(BiConsumer<Item, String> items) {
        items.accept(FEItems.RESTORATION_WISDOM, getPairing(RESTORATION, WISDOM));
        items.accept(FEItems.DISTRIBUTION_WISDOM, getPairing(DISTRIBUTION, WISDOM));
        items.accept(FEItems.COMPRESSION_WISDOM, getPairing(COMPRESSION, WISDOM));
        items.accept(FEItems.INDUCTION_WISDOM, getPairing(INDUCTION, WISDOM));
        items.accept(FEItems.ENGINEERS_SEAL, getPairing("Tinkers'", "mark"));
        items.accept(FEItems.UNIVERSAL_WISDOM, getPairing(UNIVERSAL, WISDOM));

        items.accept(FEItems.RESTORER, RESTORER);
        items.accept(FEItems.ROUTER, ROUTER);
        ColorCollection.zipApply(ColorCollection.VALUES, FEItems.DYED_ROUTER, (color, item) -> items.accept(item, getPairing(getDyeName(color), ROUTER.toLowerCase())));
        items.accept(FEItems.COMPRESSOR, COMPRESSOR);
        items.accept(FEItems.FUEL_CARRIER, "Tinder-skiff");
        items.accept(FEItems.INDUCTION_FURNACE, "Hell-fire cooker");

        items.accept(FEItems.MENDER, MENDER);
        items.accept(FEItems.ENDER_ROUTER, getPairing(ENDER, ROUTER.toLowerCase()));
        items.accept(FEItems.STRIPPER, STRIPPER);
        items.accept(FEItems.COMBUSTOR, COMBUSTOR);
    }

    public static void generateItemTags(BiConsumer<TagKey<Item>, String> tags) {
        tags.accept(FETags.PAPERS, "Parchments");
        tags.accept(FETags.ROUTERS, ROUTER + "s");
        tags.accept(FETags.RESTORATION_WISDOM_DISCOVERS, getPairing(getPairing(RESTORATION, WISDOM), DISCOVERS));
        tags.accept(FETags.DISTRIBUTION_WISDOM_DISCOVERS, getPairing(getPairing(DISTRIBUTION, WISDOM), DISCOVERS));
        tags.accept(FETags.COMPRESSION_WISDOM_DISCOVERS, getPairing(getPairing(COMPRESSION, WISDOM), DISCOVERS));
        tags.accept(FETags.INDUCTION_WISDOM_DISCOVERS, getPairing(getPairing(INDUCTION, WISDOM), DISCOVERS));
        tags.accept(FETags.UNIVERSAL_WISDOM_DISCOVERS, getPairing(getPairing(UNIVERSAL, WISDOM), DISCOVERS));
        tags.accept(FETags.ENGINEERS_SEAL_DISCOVERS, getPairing(getPairing("Tinkers'", "mark"), DISCOVERS));
        tags.accept(FETags.RESTORER_IGNORE_MATERIALS, getPairing(RESTORER, "ignore materials"));
        tags.accept(FETags.RESTORER_IGNORE_TOOLS, getPairing(RESTORER, "ignore tools"));
        tags.accept(FETags.ROUTER_IGNORE_ITEMS, getPairing(ROUTER, "ignore plunder"));
        tags.accept(FETags.COMPRESSOR_IGNORE_ITEMS, getPairing(COMPRESSOR, "ignore plunder"));
        tags.accept(FETags.FUEL_CARRIER_IGNORE_ITEMS, getPairing("Tinder-skiff", "ignore plunder"));
        tags.accept(FETags.MENDER_IGNORE_TOOLS, getPairing(MENDER, "ignore tools"));
        tags.accept(FETags.STRIPPER_IGNORE_ITEMS, getPairing(STRIPPER, "ignore plunder"));
        tags.accept(FETags.ENDER_ROUTER_IGNORE_ITEMS, getPairing(getPairing(ENDER, ROUTER.toLowerCase()), "ignore plunder"));
    }

    public static void generateBlockTags(BiConsumer<TagKey<Block>, String> tags) {
        tags.accept(FETags.COMPRESSOR_IGNORE_BLOCKS, getPairing(COMPRESSOR, "ignore blocks"));
    }

    public static void generateSounds(BiConsumer<SoundEvent, String> sounds) {
        sounds.accept(FESounds.RESTORER_DROP_CONTENTS, getPairing(RESTORER, EMPTIES));
        sounds.accept(FESounds.ROUTER_DROP_CONTENTS, getPairing(ROUTER, EMPTIES));
        sounds.accept(FESounds.COMPRESSOR_DROP_CONTENTS, getPairing(COMPRESSOR, EMPTIES));
        sounds.accept(FESounds.FUEL_CARRIER_DROP_CONTENTS, getPairing("Tinder-skiff", EMPTIES));
        sounds.accept(FESounds.INDUCTION_FURNACE_DROP_CONTENTS, getPairing("Hell-fire cooker", EMPTIES));
        sounds.accept(FESounds.MENDER_DROP_CONTENTS, getPairing(MENDER, EMPTIES));
        sounds.accept(FESounds.STRIPPER_DROP_CONTENTS, getPairing(STRIPPER, EMPTIES));
        sounds.accept(FESounds.COMBUSTOR_DROP_CONTENTS, getPairing(COMBUSTOR, EMPTIES));
    }

    public static void generateCustom(BiConsumer<String, String> custom) {
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom", getPairing("Tinkerin'", WISDOM));
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom.discovers", DISCOVERS + ":");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.RESTORER_ID.getPath() + ".empty.description", "Holds mixed amounts o' repair materials");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".device.empty.description", "Holds mixed amounts o' sortin' plunder");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.FUEL_CARRIER_ID.getPath() + ".empty.description", "Holds mixed amounts o' fuels");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.MENDER_ID.getPath() + ".empty.description", "Holds renovation tools or cups o' glass");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.COMBUSTOR_ID.getPath() + ".empty.description", "Holds black powder, burnt timber, and blisterin' blaze powder");

        custom.accept("advancements."  + ForgottenEngineersCommon.MOD_ID + ".adventure." + ForgottenEngineersItems.ENGINEERS_SEAL_ID.getPath() + ".title", "Tales o' olden Days");
        custom.accept("advancements."  + ForgottenEngineersCommon.MOD_ID + ".adventure." + ForgottenEngineersItems.ENGINEERS_SEAL_ID.getPath() + ".description", "Hunt down the grand origin where all " + getPairing("Tinkerin'", WISDOM) + " flowed from");
    }

    public static String getPairing(String name, String type) {
        return name + " " + type;
    }

    public static String getDyeName(DyeColor color) {
        return switch (color) {
            case CYAN -> "Ocean blue";
            case LIME -> "Light green";
            case MAGENTA -> "Light purple";
            case RED -> "Scarlet";
            default -> StringUtils.capitalize(color.getName().replace('_', ' '));
        };
    }
}
