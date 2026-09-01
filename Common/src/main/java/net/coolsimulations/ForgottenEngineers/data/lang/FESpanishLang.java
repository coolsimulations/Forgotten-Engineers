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

import java.util.Locale;
import java.util.function.BiConsumer;

public class FESpanishLang {

    public static String MOD_NAME = "Ingeniería olvidada";

    public static String RESTORATION = "restauradora";
    public static String DISTRIBUTION = "clasificación";
    public static String COMPRESSION = "compresión";
    public static String UNIVERSAL = "universal";
    public static String WISDOM = "Sabiduría de";
    public static String ENDER = "de ender";

    public static String RESTORER = "Restaurador";
    public static String ROUTER = "Clasificador";
    public static String COMPRESSOR = "Compresor";
    public static String INDUCTION = "inducción";
    public static String MENDER = "Remendador";
    public static String STRIPPER = "Raspador";
    public static String COMBUSTOR = "Cañón de combustión";

    public static String DISCOVERS = "Descubres";
    public static String EMPTIES = "vaciado";

    public static void generateItems(BiConsumer<Item, String> items) {
        items.accept(FEItems.RESTORATION_WISDOM, getPairing(WISDOM, RESTORATION));
        items.accept(FEItems.DISTRIBUTION_WISDOM, getPairing(WISDOM, DISTRIBUTION));
        items.accept(FEItems.COMPRESSION_WISDOM, getPairing(WISDOM, COMPRESSION));
        items.accept(FEItems.INDUCTION_WISDOM, getPairing(WISDOM, INDUCTION));
        items.accept(FEItems.ENGINEERS_SEAL, "Sello de ingenieros");
        items.accept(FEItems.UNIVERSAL_WISDOM, getPairing(WISDOM, UNIVERSAL));

        items.accept(FEItems.RESTORER, RESTORER);
        items.accept(FEItems.ROUTER, ROUTER);
        ColorCollection.zipApply(ColorCollection.VALUES, FEItems.DYED_ROUTER, (color, item) -> items.accept(item, getPairing(ROUTER, getDyeName(color))));
        items.accept(FEItems.COMPRESSOR, COMPRESSOR);
        items.accept(FEItems.FUEL_CARRIER, "Caja de combustible");
        items.accept(FEItems.INDUCTION_FURNACE, getPairing("Horno de", INDUCTION));

        items.accept(FEItems.MENDER, MENDER);
        items.accept(FEItems.ENDER_ROUTER, getPairing(ROUTER, ENDER));
        items.accept(FEItems.STRIPPER, STRIPPER);
        items.accept(FEItems.COMBUSTOR, COMBUSTOR);
    }

    public static void generateItemTags(BiConsumer<TagKey<Item>, String> tags) {
        tags.accept(FETags.PAPERS, "Papeles");
        tags.accept(FETags.ROUTERS, ROUTER + "es");
        tags.accept(FETags.RESTORATION_WISDOM_DISCOVERS, "La " + WISDOM.toLowerCase() + " la restauración descubre");
        tags.accept(FETags.DISTRIBUTION_WISDOM_DISCOVERS, "La " + WISDOM.toLowerCase() + " la " + DISTRIBUTION + " descubre");
        tags.accept(FETags.COMPRESSION_WISDOM_DISCOVERS, "La " + WISDOM.toLowerCase() + " la " + COMPRESSION + " descubre");
        tags.accept(FETags.INDUCTION_WISDOM_DISCOVERS, "La " + WISDOM.toLowerCase() + " la " + INDUCTION + " descubre");
        tags.accept(FETags.UNIVERSAL_WISDOM_DISCOVERS, "La " + WISDOM.toLowerCase() + " " + UNIVERSAL + " descubre");
        tags.accept(FETags.ENGINEERS_SEAL_DISCOVERS, "EL" + "sello de ingenieros descubre");
        tags.accept(FETags.RESTORER_IGNORE_MATERIALS, "El " + RESTORATION.toLowerCase() + " ignora los materiales");
        tags.accept(FETags.RESTORER_IGNORE_TOOLS, "El " + RESTORATION.toLowerCase() + " ignora los herramientas");
        tags.accept(FETags.ROUTER_IGNORE_ITEMS, "El " + ROUTER.toLowerCase() + " ignora los objetos");
        tags.accept(FETags.COMPRESSOR_IGNORE_ITEMS, "El " + COMPRESSOR.toLowerCase() + " ignora los objetos");
        tags.accept(FETags.FUEL_CARRIER_IGNORE_ITEMS, "La caja de combustible ignora los objetos");
        tags.accept(FETags.MENDER_IGNORE_TOOLS, "El " + MENDER.toLowerCase() + " ignora los herramientas");
        tags.accept(FETags.STRIPPER_IGNORE_ITEMS, "El " + STRIPPER.toLowerCase() + " ignora los objetos");
        tags.accept(FETags.ENDER_ROUTER_IGNORE_ITEMS, "El " + getPairing(ROUTER, ENDER).toLowerCase() + " ignora los objetos");
    }

    public static void generateBlockTags(BiConsumer<TagKey<Block>, String> tags) {
        tags.accept(FETags.COMPRESSOR_IGNORE_BLOCKS, "El " + COMPRESSOR.toLowerCase() + " ignora los bloques");
    }

    public static void generateSounds(BiConsumer<SoundEvent, String> sounds) {
        sounds.accept(FESounds.RESTORER_DROP_CONTENTS, getPairing(RESTORER, EMPTIES));
        sounds.accept(FESounds.ROUTER_DROP_CONTENTS, getPairing(ROUTER, EMPTIES));
        sounds.accept(FESounds.COMPRESSOR_DROP_CONTENTS, getPairing(COMPRESSOR, EMPTIES));
        sounds.accept(FESounds.FUEL_CARRIER_DROP_CONTENTS, getPairing("Caja de combustible", EMPTIES));
        sounds.accept(FESounds.INDUCTION_FURNACE_DROP_CONTENTS, getPairing(getPairing("Horno de", INDUCTION), EMPTIES));
        sounds.accept(FESounds.MENDER_DROP_CONTENTS, getPairing(MENDER, EMPTIES));
        sounds.accept(FESounds.STRIPPER_DROP_CONTENTS, getPairing(STRIPPER, EMPTIES));
        sounds.accept(FESounds.COMBUSTOR_DROP_CONTENTS, getPairing(COMBUSTOR, EMPTIES));
    }

    public static void generateCustom(BiConsumer<String, String> custom) {
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom", getPairing(WISDOM, "ingeniería"));
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom.discovers", DISCOVERS + ":");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.RESTORER_ID.getPath() + ".empty.description", "Puede contener una pila mixta de materiales de reparación");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".device.empty.description", "Puede contener una pila mixta de objetos de filtro");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.FUEL_CARRIER_ID.getPath() + ".empty.description", "Puede contener una pila mixta de objetos de combustible");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.MENDER_ID.getPath() + ".empty.description", "Puede contener herramientas de reparación o frasco de cristal");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.COMBUSTOR_ID.getPath() + ".empty.description", "Puede contener pólvora, carbón y polvo de blaze");

        custom.accept("advancements."  + ForgottenEngineersCommon.MOD_ID + ".adventure." + ForgottenEngineersItems.ENGINEERS_SEAL_ID.getPath() + ".title", "Ecos del pasado");
        custom.accept("advancements."  + ForgottenEngineersCommon.MOD_ID + ".adventure." + ForgottenEngineersItems.ENGINEERS_SEAL_ID.getPath() + ".description", "Encuentra la fuente de la que desciende toda la sabiduría de la ingeniería");}

    public static String getPairing(String name, String type) {
        return name + " " + type;
    }

    public static String getDyeName(DyeColor color) {
        return switch (color) {
            case BLACK -> "negra";
            case BLUE -> "azul";
            case BROWN -> "marrón";
            case CYAN -> "cian";
            case GRAY -> "gris";
            case GREEN -> "verde";
            case LIGHT_BLUE -> "azul claro";
            case LIGHT_GRAY -> "gris claro";
            case LIME -> "verde lima";
            case MAGENTA -> "magenta";
            case ORANGE -> "naranja";
            case PINK -> "rosa";
            case PURPLE -> "morada";
            case RED -> "roja";
            case WHITE -> "blanca";
            case YELLOW -> "amarilla";
        };
    }
}
