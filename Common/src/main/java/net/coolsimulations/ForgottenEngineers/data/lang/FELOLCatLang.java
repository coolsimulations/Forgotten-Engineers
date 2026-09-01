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

import java.util.function.BiConsumer;

public class FELOLCatLang {

    public static String MOD_NAME = "Dez inginears we fogot, soz";

    public static String RESTORATION = "Fix dat";
    public static String DISTRIBUTION = "Dis all mine";
    public static String COMPRESSION = "Da crunch";
    public static String UNIVERSAL = "Unifursal";
    public static String WISDOM = "Brainz";
    public static String ENDER = "Ender";

    public static String RESTORER = "Fix dat";
    public static String ROUTER = "Go in box";
    public static String COMPRESSOR = "Ooo squishy";
    public static String INDUCTION = "Burnee";
    public static String MENDER = "Mentrd dat";
    public static String STRIPPER = "Streepze it";
    public static String COMBUSTOR = "Kapow, bye!";

    public static String DISCOVERS = "Makz dez";
    public static String EMPTIES = "emptied naww catrzzz";

    public static void generateItems(BiConsumer<Item, String> items) {
        items.accept(FEItems.RESTORATION_WISDOM, getPairing(RESTORATION, WISDOM));
        items.accept(FEItems.DISTRIBUTION_WISDOM, getPairing(DISTRIBUTION, WISDOM));
        items.accept(FEItems.COMPRESSION_WISDOM, getPairing(COMPRESSION, WISDOM));
        items.accept(FEItems.INDUCTION_WISDOM, getPairing(INDUCTION, WISDOM));
        items.accept(FEItems.ENGINEERS_SEAL, getPairing("Inginears'", "zeel"));
        items.accept(FEItems.UNIVERSAL_WISDOM, getPairing(UNIVERSAL, WISDOM));

        items.accept(FEItems.RESTORER, RESTORER);
        items.accept(FEItems.ROUTER, ROUTER);
        ColorCollection.zipApply(ColorCollection.VALUES, FEItems.DYED_ROUTER, (color, item) -> items.accept(item, getPairing(getDyeName(color), ROUTER)));
        items.accept(FEItems.COMPRESSOR, COMPRESSOR);
        items.accept(FEItems.FUEL_CARRIER, "Fool kary");
        items.accept(FEItems.INDUCTION_FURNACE, getPairing(INDUCTION, "thing"));

        items.accept(FEItems.MENDER, MENDER);
        items.accept(FEItems.ENDER_ROUTER, getPairing(ENDER, ROUTER));
        items.accept(FEItems.STRIPPER, STRIPPER);
        items.accept(FEItems.COMBUSTOR, COMBUSTOR);
    }

    public static void generateItemTags(BiConsumer<TagKey<Item>, String> tags) {
        tags.accept(FETags.PAPERS, "Papyruses");
        tags.accept(FETags.ROUTERS, ROUTER + "es");
        tags.accept(FETags.RESTORATION_WISDOM_DISCOVERS, getPairing(getPairing(RESTORATION, WISDOM), DISCOVERS.toLowerCase()));
        tags.accept(FETags.DISTRIBUTION_WISDOM_DISCOVERS, getPairing(getPairing(DISTRIBUTION, WISDOM), DISCOVERS.toLowerCase()));
        tags.accept(FETags.COMPRESSION_WISDOM_DISCOVERS, getPairing(getPairing(COMPRESSION, WISDOM), DISCOVERS.toLowerCase()));
        tags.accept(FETags.INDUCTION_WISDOM_DISCOVERS, getPairing(getPairing(INDUCTION, WISDOM), DISCOVERS.toLowerCase()));
        tags.accept(FETags.UNIVERSAL_WISDOM_DISCOVERS, getPairing(getPairing(UNIVERSAL, WISDOM), DISCOVERS.toLowerCase()));
        tags.accept(FETags.ENGINEERS_SEAL_DISCOVERS, getPairing(getPairing("Inginears'", "zeel"), DISCOVERS.toLowerCase()));
        tags.accept(FETags.RESTORER_IGNORE_MATERIALS, getPairing(RESTORER, "ignaws thingz"));
        tags.accept(FETags.RESTORER_IGNORE_TOOLS, getPairing(RESTORER, "ignaws toolz"));
        tags.accept(FETags.ROUTER_IGNORE_ITEMS, getPairing(ROUTER, "ignaws thingz"));
        tags.accept(FETags.COMPRESSOR_IGNORE_ITEMS, getPairing(COMPRESSOR, "ignaws thingz"));
        tags.accept(FETags.FUEL_CARRIER_IGNORE_ITEMS, getPairing("Fool kary", "ignaws thingz"));
        tags.accept(FETags.MENDER_IGNORE_TOOLS, getPairing(MENDER, "ignaws toolz"));
        tags.accept(FETags.STRIPPER_IGNORE_ITEMS, getPairing(STRIPPER, "ignaws thingz"));
        tags.accept(FETags.ENDER_ROUTER_IGNORE_ITEMS, getPairing(getPairing(ENDER, ROUTER), "ignaws thingz"));
    }

    public static void generateBlockTags(BiConsumer<TagKey<Block>, String> tags) {
        tags.accept(FETags.COMPRESSOR_IGNORE_BLOCKS, getPairing(COMPRESSOR, "ignaws blukz"));
    }

    public static void generateSounds(BiConsumer<SoundEvent, String> sounds) {
        sounds.accept(FESounds.RESTORER_DROP_CONTENTS, getPairing(RESTORER, EMPTIES));
        sounds.accept(FESounds.ROUTER_DROP_CONTENTS, getPairing(ROUTER, EMPTIES));
        sounds.accept(FESounds.COMPRESSOR_DROP_CONTENTS, getPairing(COMPRESSOR, EMPTIES));
        sounds.accept(FESounds.FUEL_CARRIER_DROP_CONTENTS, getPairing("Fool kary", EMPTIES));
        sounds.accept(FESounds.INDUCTION_FURNACE_DROP_CONTENTS, getPairing(getPairing(INDUCTION, "thing"), EMPTIES));
        sounds.accept(FESounds.MENDER_DROP_CONTENTS, getPairing(MENDER, EMPTIES));
        sounds.accept(FESounds.STRIPPER_DROP_CONTENTS, getPairing(STRIPPER, EMPTIES));
        sounds.accept(FESounds.COMBUSTOR_DROP_CONTENTS, getPairing(COMBUSTOR, EMPTIES));
    }

    public static void generateCustom(BiConsumer<String, String> custom) {
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom", getPairing("Big", WISDOM));
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom.discovers", DISCOVERS + ":");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.RESTORER_ID.getPath() + ".empty.description", "Kan hold a mikz stacc of fixin thingz :o");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".device.empty.description", "Kan hold a mikz stacc of filta thingz :o");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.FUEL_CARRIER_ID.getPath() + ".empty.description", "Kan hold a mikz stacc of fool thingz :o");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.MENDER_ID.getPath() + ".empty.description", "Kan hold mendin' toolz or GLAS BOTULS :o");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.COMBUSTOR_ID.getPath() + ".empty.description", "Kan hold ganpoudar, burned w00d, and HOT DUST :o");

        custom.accept("advancements."  + ForgottenEngineersCommon.MOD_ID + ".adventure." + ForgottenEngineersItems.ENGINEERS_SEAL_ID.getPath() + ".title", "Ekoes of long tam ago");
        custom.accept("advancements."  + ForgottenEngineersCommon.MOD_ID + ".adventure." + ForgottenEngineersItems.ENGINEERS_SEAL_ID.getPath() + ".description", "Find teh s0urce fr0m wich all " + getPairing("big", WISDOM) + " come");
    }

    public static String getPairing(String name, String type) {
        return name + " " + type;
    }

    public static String getDyeName(DyeColor color) {
        return switch (color) {
            case BLACK -> "Blak";
            case BLUE -> "Bloo";
            case BROWN -> "Brownish";
            case CYAN -> "Nyan";
            case GRAY -> "Greyh";
            case GREEN -> "Gren";
            case LIGHT_BLUE -> "Lite Blu";
            case LIGHT_GRAY -> "Lite Grehy";
            case LIME -> "Limeh";
            case MAGENTA -> "Majenta";
            case ORANGE -> "Orang";
            case PINK -> "Pinky";
            case PURPLE -> "Parpal";
            case RED -> "Reddish";
            case WHITE -> "Waite";
            case YELLOW -> "Banana";
        };
    }
}
