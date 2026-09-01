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

public class FEPortugueseLang {

    public static String ENGINEERING = "Engenharia";

    public static String MOD_NAME = getPairing(ENGINEERING, "Esquecida");

    public static String RESTORATION = "Restauração";
    public static String DISTRIBUTION = "Classificação";
    public static String COMPRESSION = "Compressão";
    public static String UNIVERSAL = "Universal";
    public static String WISDOM = "Sabedoria de";
    public static String ENDER = "de Ender";

    public static String RESTORER = "Restaurador";
    public static String ROUTER = "Classificador";
    public static String COMPRESSOR = "Compressor";
    public static String INDUCTION = "Indução";
    public static String MENDER = "Remendador";
    public static String STRIPPER = "Descascador";
    public static String COMBUSTOR = "Canhão de Combustão";

    public static String DISCOVERS = "Descobertas";
    public static String EMPTIES = "esvaziada";

    public static void generateItems(BiConsumer<Item, String> items) {
        items.accept(FEItems.RESTORATION_WISDOM, getPairing(WISDOM, RESTORATION));
        items.accept(FEItems.DISTRIBUTION_WISDOM, getPairing(WISDOM, DISTRIBUTION));
        items.accept(FEItems.COMPRESSION_WISDOM, getPairing(WISDOM, COMPRESSION));
        items.accept(FEItems.INDUCTION_WISDOM, getPairing(WISDOM, INDUCTION));
        items.accept(FEItems.ENGINEERS_SEAL, "Selo dos Engenheiros");
        items.accept(FEItems.UNIVERSAL_WISDOM, getPairing(WISDOM.replace(" de", ""), UNIVERSAL));

        items.accept(FEItems.RESTORER, RESTORER);
        items.accept(FEItems.ROUTER, ROUTER);
        ColorCollection.zipApply(ColorCollection.VALUES, FEItems.DYED_ROUTER, (color, item) -> items.accept(item, getPairing(ROUTER, getDyeName(color))));
        items.accept(FEItems.COMPRESSOR, "Compressor");
        items.accept(FEItems.FUEL_CARRIER, "Balde de Carvão");
        items.accept(FEItems.INDUCTION_FURNACE, getPairing("Forno de", INDUCTION));

        items.accept(FEItems.MENDER, MENDER);
        items.accept(FEItems.ENDER_ROUTER, getPairing(ROUTER, ENDER));
        items.accept(FEItems.STRIPPER, STRIPPER);
        items.accept(FEItems.COMBUSTOR, COMBUSTOR);
    }

    public static void generateItemTags(BiConsumer<TagKey<Item>, String> tags) {
        tags.accept(FETags.PAPERS, "Papéis");
        tags.accept(FETags.ROUTERS, ROUTER + "es");
        tags.accept(FETags.RESTORATION_WISDOM_DISCOVERS, getPairing(getPairing(DISCOVERS, "de"), getPairing(WISDOM.replace("de", "da"), RESTORATION)));
        tags.accept(FETags.DISTRIBUTION_WISDOM_DISCOVERS, getPairing(getPairing(DISCOVERS, "de"), getPairing(WISDOM.replace("de", "da"), DISTRIBUTION)));
        tags.accept(FETags.COMPRESSION_WISDOM_DISCOVERS, getPairing(getPairing(DISCOVERS, "de"), getPairing(WISDOM.replace("de", "da"), COMPRESSION)));
        tags.accept(FETags.INDUCTION_WISDOM_DISCOVERS, getPairing(getPairing(DISCOVERS, "de"), getPairing(WISDOM.replace("de", "da"), INDUCTION)));
        tags.accept(FETags.UNIVERSAL_WISDOM_DISCOVERS, getPairing(getPairing(DISCOVERS, "de"), getPairing(WISDOM.replace(" de", ""), UNIVERSAL)));
        tags.accept(FETags.ENGINEERS_SEAL_DISCOVERS, getPairing(getPairing(DISCOVERS, "de"), "Selo dos Engenheiros"));
        tags.accept(FETags.RESTORER_IGNORE_MATERIALS, getPairing(RESTORER, "ignora materiais"));
        tags.accept(FETags.RESTORER_IGNORE_TOOLS, getPairing(RESTORER, "ignora ferramentas"));
        tags.accept(FETags.ROUTER_IGNORE_ITEMS, getPairing(ROUTER, "ignora itens"));
        tags.accept(FETags.COMPRESSOR_IGNORE_ITEMS, getPairing(COMPRESSOR, "ignora itens"));
        tags.accept(FETags.FUEL_CARRIER_IGNORE_ITEMS, getPairing("Balde de Carvão", "ignora itens"));
        tags.accept(FETags.MENDER_IGNORE_TOOLS, getPairing(MENDER, "ignora ferramentas"));
        tags.accept(FETags.STRIPPER_IGNORE_ITEMS, getPairing(STRIPPER, "ignora itens"));
        tags.accept(FETags.ENDER_ROUTER_IGNORE_ITEMS, getPairing(getPairing(ENDER, ROUTER), "ignora itens"));
    }

    public static void generateBlockTags(BiConsumer<TagKey<Block>, String> tags) {
        tags.accept(FETags.COMPRESSOR_IGNORE_BLOCKS, getPairing(COMPRESSOR, "ignora blocos"));
    }

    public static void generateSounds(BiConsumer<SoundEvent, String> sounds) {
        sounds.accept(FESounds.RESTORER_DROP_CONTENTS, getPairing(RESTORER, EMPTIES));
        sounds.accept(FESounds.ROUTER_DROP_CONTENTS, getPairing(ROUTER, EMPTIES));
        sounds.accept(FESounds.COMPRESSOR_DROP_CONTENTS, getPairing(COMPRESSOR, EMPTIES));
        sounds.accept(FESounds.FUEL_CARRIER_DROP_CONTENTS, getPairing("Balde de Carvão", EMPTIES));
        sounds.accept(FESounds.INDUCTION_FURNACE_DROP_CONTENTS, getPairing(getPairing("Forno de", INDUCTION), EMPTIES));
        sounds.accept(FESounds.MENDER_DROP_CONTENTS, getPairing(MENDER, EMPTIES));
        sounds.accept(FESounds.STRIPPER_DROP_CONTENTS, getPairing(STRIPPER, EMPTIES));
        sounds.accept(FESounds.COMBUSTOR_DROP_CONTENTS, getPairing(COMBUSTOR, EMPTIES));
    }

    public static void generateCustom(BiConsumer<String, String> custom) {
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom", getPairing(WISDOM, "Engenharia"));
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".engineering_wisdom.discovers", DISCOVERS + ":");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.RESTORER_ID.getPath() + ".empty.description", "Pode carregar um conjunto diverso de materiais de reparo");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + ".device.empty.description", "Pode carregar um conjunto diverso de itens filtrantes");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.FUEL_CARRIER_ID.getPath() + ".empty.description", "Pode carregar um conjunto diverso de itens de combustível");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.MENDER_ID.getPath() + ".empty.description", "Pode carregar ferramentas de remendo ou garrafas de vidro");
        custom.accept("item." + ForgottenEngineersCommon.MOD_ID + "." + ForgottenEngineersItems.COMBUSTOR_ID.getPath() + ".empty.description", "Pode carregar pólvora, carvão e pó de blaze");

        custom.accept("advancements."  + ForgottenEngineersCommon.MOD_ID + ".adventure." + ForgottenEngineersItems.ENGINEERS_SEAL_ID.getPath() + ".title", "Ecos do Passado");
        custom.accept("advancements."  + ForgottenEngineersCommon.MOD_ID + ".adventure." + ForgottenEngineersItems.ENGINEERS_SEAL_ID.getPath() + ".description", "Encontre a fonte de onde toda a " + getPairing(WISDOM.replace("de", "da"), ENGINEERING) + " emanou");
    }

    public static String getPairing(String name, String type) {
        return name + " " + type;
    }

    public static String getDyeName(DyeColor color) {
        return switch (color) {
            case BLACK -> "Preta";
            case BLUE -> "Azul";
            case BROWN -> "Marrom";
            case CYAN -> "Ciano";
            case GRAY -> "Cinza";
            case GREEN -> "Verde";
            case LIGHT_BLUE -> "Azul-Clara";
            case LIGHT_GRAY -> "Cinza-Clara";
            case LIME -> "Verde-Limão";
            case MAGENTA -> "Magenta";
            case ORANGE -> "Laranja";
            case PINK -> "Rosa";
            case PURPLE -> "Roxa";
            case RED -> "Vermelha";
            case WHITE -> "Branca";
            case YELLOW -> "Amarela";
        };
    }
}
