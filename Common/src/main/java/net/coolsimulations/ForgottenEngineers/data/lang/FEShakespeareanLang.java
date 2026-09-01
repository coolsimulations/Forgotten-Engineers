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

public class FEShakespeareanLang {

    public static String MOD_NAME = "Unremembered Mechanics";

    public static String RESTORATION = "Renewal";
    public static String DISTRIBUTION = "Allotment";
    public static String COMPRESSION = "Condensation";
    public static String UNIVERSAL = "General";
    public static String WISDOM = "Lore of";
    public static String ENDER = "Ender";

    public static String RESTORER = "Restorer";
    public static String ROUTER = "Allotter";
    public static String COMPRESSOR = "Condenser";
    public static String INDUCTION = "Quickening Heart";
    public static String MENDER = "Merlin's Restorer";
    public static String STRIPPER = "Barker";
    public static String COMBUSTOR = "Fire-drake";

    public static String DISCOVERS = "Discovers";
    public static String EMPTIES = "empties";

    public static void generateItems(BiConsumer<Item, String> items) {
        items.accept(FEItems.RESTORATION_WISDOM, getPairing(WISDOM, RESTORATION));
        items.accept(FEItems.DISTRIBUTION_WISDOM, getPairing(WISDOM, DISTRIBUTION));
        items.accept(FEItems.COMPRESSION_WISDOM, getPairing(WISDOM, COMPRESSION));
        items.accept(FEItems.INDUCTION_WISDOM, getPairing(WISDOM, INDUCTION));
        items.accept(FEItems.ENGINEERS_SEAL, getPairing("Mechanics'", "Seal"));
        items.accept(FEItems.UNIVERSAL_WISDOM, getPairing(UNIVERSAL, WISDOM));

        items.accept(FEItems.RESTORER, RESTORER);
        items.accept(FEItems.ROUTER, ROUTER);
        ColorCollection.zipApply(ColorCollection.VALUES, FEItems.DYED_ROUTER, (color, item) -> items.accept(item, getPairing(getDyeName(color), ROUTER)));
        items.accept(FEItems.COMPRESSOR, COMPRESSOR);
        items.accept(FEItems.FUEL_CARRIER, "Fuel Coffer");
        items.accept(FEItems.INDUCTION_FURNACE, "Quickening Forge");

        items.accept(FEItems.MENDER, MENDER);
        items.accept(FEItems.ENDER_ROUTER, getPairing(ENDER, ROUTER));
        items.accept(FEItems.STRIPPER, STRIPPER);
        items.accept(FEItems.COMBUSTOR, COMBUSTOR);
    }

    public static void generateItemTags(BiConsumer<TagKey<Item>, String> tags) {
        tags.accept(FETags.PAPERS, "Papers");
        tags.accept(FETags.ROUTERS, ROUTER + "s");
        tags.accept(FETags.RESTORATION_WISDOM_DISCOVERS, getPairing(getPairing(WISDOM, RESTORATION), DISCOVERS));
        tags.accept(FETags.DISTRIBUTION_WISDOM_DISCOVERS, getPairing(getPairing(WISDOM, DISTRIBUTION), DISCOVERS));
        tags.accept(FETags.COMPRESSION_WISDOM_DISCOVERS, getPairing(getPairing(WISDOM, COMPRESSION), DISCOVERS));
        tags.accept(FETags.INDUCTION_WISDOM_DISCOVERS, getPairing(getPairing(WISDOM, INDUCTION), DISCOVERS));
        tags.accept(FETags.UNIVERSAL_WISDOM_DISCOVERS, getPairing(getPairing(WISDOM, UNIVERSAL), DISCOVERS));
        tags.accept(FETags.ENGINEERS_SEAL_DISCOVERS, getPairing(getPairing("Mechanics'", "Seal"), DISCOVERS));
        tags.accept(FETags.RESTORER_IGNORE_MATERIALS, getPairing(RESTORER, "Ignore Wares"));
        tags.accept(FETags.RESTORER_IGNORE_TOOLS, getPairing(RESTORER, "Ignore Tools"));
        tags.accept(FETags.ROUTER_IGNORE_ITEMS, getPairing(ROUTER, "Ignore Wares"));
        tags.accept(FETags.COMPRESSOR_IGNORE_ITEMS, getPairing(COMPRESSOR, "Ignore Wares"));
        tags.accept(FETags.FUEL_CARRIER_IGNORE_ITEMS, getPairing("Fuel Coffer", "Ignore Wares"));
        tags.accept(FETags.MENDER_IGNORE_TOOLS, getPairing(MENDER, "Ignore Tools"));
        tags.accept(FETags.STRIPPER_IGNORE_ITEMS, getPairing(STRIPPER, "Ignore Wares"));
        tags.accept(FETags.ENDER_ROUTER_IGNORE_ITEMS, getPairing(getPairing(ENDER, ROUTER), "Ignore Wares"));
    }

    public static void generateBlockTags(BiConsumer<TagKey<Block>, String> tags) {
        tags.accept(FETags.COMPRESSOR_IGNORE_BLOCKS, getPairing(COMPRESSOR, "Ignore Blocks"));
    }

    public static void generateSounds(BiConsumer<SoundEvent, String> sounds) {
        sounds.accept(FESounds.RESTORER_DROP_CONTENTS, getPairing(RESTORER, EMPTIES));
        sounds.accept(FESounds.ROUTER_DROP_CONTENTS, getPairing(ROUTER, EMPTIES));
        sounds.accept(FESounds.COMPRESSOR_DROP_CONTENTS, getPairing(COMPRESSOR, EMPTIES));
        sounds.accept(FESounds.FUEL_CARRIER_DROP_CONTENTS, getPairing("Fuel Coffer", EMPTIES));
        sounds.accept(FESounds.INDUCTION_FURNACE_DROP_CONTENTS, getPairing("Quickening Forge", EMPTIES));
        sounds.accept(FESounds.MENDER_DROP_CONTENTS, getPairing(MENDER, EMPTIES));
        sounds.accept(FESounds.STRIPPER_DROP_CONTENTS, getPairing(STRIPPER, EMPTIES));
        sounds.accept(FESounds.COMBUSTOR_DROP_CONTENTS, getPairing(COMBUSTOR, EMPTIES));
    }

    public static void generateCustom(BiConsumer<String, String> custom) {
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom", "Engine-Artistry");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom.discovers", DISCOVERS + ":");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.RESTORER_ID.getPath() + ".empty.description", "Canst bear a mingled heap of repair wares");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".device.empty.description", "Canst bear a mingled heap of sifting wares");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.FUEL_CARRIER_ID.getPath() + ".empty.description", "Canst bear a mingled heap of fuels");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.MENDER_ID.getPath() + ".empty.description", "Canst bear Merlin's repair tools or glass bottles");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.COMBUSTOR_ID.getPath() + ".empty.description", "Canst bear gunpowder, coal, and brimstone powder");

        custom.accept("advancements."  + ForgottenEngineersCommon.MOD_ID + ".adventure." + ForgottenEngineersItems.ENGINEERS_SEAL_ID.getPath() + ".title", "The Echoes of Yore");
        custom.accept("advancements."  + ForgottenEngineersCommon.MOD_ID + ".adventure." + ForgottenEngineersItems.ENGINEERS_SEAL_ID.getPath() + ".description", "Seek out the fountainhead from whence all " + "Engine-Artistry" + " descended");
    }

    public static String getPairing(String name, String type) {
        return name + " " + type;
    }

    public static String getDyeName(DyeColor color) {
        return switch (color) {
            case CYAN -> "Turquoise";
            case GRAY -> "Grey";
            case LIGHT_BLUE -> "Whey";
            case LIGHT_GRAY -> "Pale Grey";
            case LIME -> "Lincoln";
            case MAGENTA -> "Mulberry";
            case ORANGE -> "Tawny";
            case PINK -> "Carnation";
            default -> WordUtils.capitalize(color.getName().replace('_', ' '));
        };
    }
}
