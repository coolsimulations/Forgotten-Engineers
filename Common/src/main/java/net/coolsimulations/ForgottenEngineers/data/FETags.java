package net.coolsimulations.ForgottenEngineers.data;

import net.coolsimulations.ForgottenEngineers.FEServices;
import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.item.FEItems;
import net.coolsimulations.ForgottenEngineers.item.ForgottenEngineersItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.references.BlockItemIds;
import net.minecraft.references.ItemIds;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.ColorCollection;

import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class FETags {

    public static final TagKey<Item> PAPERS = createCommonItemTag("papers");

    public static final TagKey<Item> ROUTERS = createModItemTag("routers");

    public static final TagKey<Item> RESTORATION_WISDOM_DISCOVERS = createWisdomTag("restoration");
    public static final TagKey<Item> DISTRIBUTION_WISDOM_DISCOVERS = createWisdomTag("distribution");
    public static final TagKey<Item> COMPRESSION_WISDOM_DISCOVERS = createWisdomTag("compression");
    public static final TagKey<Item> INDUCTION_WISDOM_DISCOVERS = createWisdomTag("induction");
    public static final TagKey<Item> UNIVERSAL_WISDOM_DISCOVERS = createWisdomTag("universal");
    public static final TagKey<Item> ENGINEERS_SEAL_DISCOVERS = createWisdomTag("engineers_seal_discovers");

    public static final TagKey<Item> RESTORER_IGNORE_MATERIALS = createModItemTag("restorer_ignore_materials");
    public static final TagKey<Item> RESTORER_IGNORE_TOOLS = createModItemTag("restorer_ignore_tools");
    public static final TagKey<Item> ROUTER_IGNORE_ITEMS = createModItemTag("router_ignore_items");
    public static final TagKey<Item> COMPRESSOR_IGNORE_ITEMS = createModItemTag("compressor_ignore_items");
    public static final TagKey<Block> COMPRESSOR_IGNORE_BLOCKS = createModBlockTag("compressor_ignore_blocks");
    public static final TagKey<Item> FUEL_CARRIER_IGNORE_ITEMS = createModItemTag("fuel_carrier_ignore_items");
    public static final TagKey<Item> INDUCTION_FURNACE_IGNORE_ITEMS = createModItemTag("induction_furnace_ignore_items");

    public static final TagKey<Item> MENDER_IGNORE_TOOLS = createModItemTag("mender_ignore_tools");
    public static final TagKey<Item> STRIPPER_IGNORE_ITEMS = createModItemTag("stripper_ignore_items");
    public static final TagKey<Item> ENDER_ROUTER_IGNORE_ITEMS = createModItemTag("ender_router_ignore_items");

    public static void generateNamedSingleTags(BiConsumer<Identifier, TagKey<Item>> singles) {
        singles.accept(ItemIds.PAPER.identifier(), PAPERS);
        singles.accept(ForgottenEngineersItems.RESTORER_ID, RESTORATION_WISDOM_DISCOVERS);
        singles.accept(ForgottenEngineersItems.ROUTER_ID, DISTRIBUTION_WISDOM_DISCOVERS);
        singles.accept(ForgottenEngineersItems.COMPRESSOR_ID, COMPRESSION_WISDOM_DISCOVERS);
        singles.accept(ForgottenEngineersItems.UNIVERSAL_WISDOM_ID, ENGINEERS_SEAL_DISCOVERS);
        ColorCollection.zipApply(ColorCollection.VALUES, FEItems.DYED_ROUTER, (color, item) -> singles.accept(BuiltInRegistries.ITEM.getKey(item), FEServices.REGISTRY.getDyedTag(color)));
    }

    public static void generateNamedTags(BiConsumer<List<Identifier>, TagKey<Item>> multiples) {
        multiples.accept(List.of(ForgottenEngineersItems.FUEL_CARRIER_ID, ForgottenEngineersItems.INDUCTION_FURNACE_ID), INDUCTION_WISDOM_DISCOVERS);
        multiples.accept(List.of(ForgottenEngineersItems.MENDER_ID, ForgottenEngineersItems.ENDER_ROUTER_ID, ForgottenEngineersItems.STRIPPER_ID, ForgottenEngineersItems.COMBUSTOR_ID), UNIVERSAL_WISDOM_DISCOVERS);
    }

    public static void generateEmptyTags(Consumer<TagKey<Item>> singles) {
        singles.accept(RESTORER_IGNORE_MATERIALS);
        singles.accept(RESTORER_IGNORE_TOOLS);
        singles.accept(ROUTER_IGNORE_ITEMS);
        singles.accept(FUEL_CARRIER_IGNORE_ITEMS);
        singles.accept(INDUCTION_FURNACE_IGNORE_ITEMS);
        singles.accept(MENDER_IGNORE_TOOLS);
        singles.accept(STRIPPER_IGNORE_ITEMS);
        singles.accept(ENDER_ROUTER_IGNORE_ITEMS);
    }

    public static void generateRouterTag(Consumer<Identifier> routers) {
        routers.accept(ForgottenEngineersItems.ROUTER_ID);
        ForgottenEngineersItems.DYED_ROUTER_ID.forEach(id -> routers.accept(Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, id)));
    }

    public static void generateCompressorItemTag(Consumer<TagKey<Item>> items, Consumer<Identifier> singles) {
        items.accept(ItemTags.BOATS);
        singles.accept(ItemIds.MINECART.identifier());
        singles.accept(ItemIds.LEAD.identifier());
        singles.accept(BlockItemIds.LADDER.item().identifier());
        singles.accept(BlockItemIds.DECORATED_POT.item().identifier());
        BlockItemIds.COPPER_GRATE.forEach(id -> singles.accept(id.item().identifier()));
    }

    public static void generateCompressorBlockTag(Consumer<TagKey<Block>> blocks, Consumer<Identifier> singles) {
        blocks.accept(BlockTags.DOORS);
        blocks.accept(BlockTags.WOODEN_SHELVES);
        blocks.accept(BlockTags.STAIRS);
        blocks.accept(BlockTags.TRAPDOORS);
        blocks.accept(BlockTags.WALLS);
    }

    public static TagKey<Item> createModItemTag(String tag) {
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, tag));
    }

    public static TagKey<Block> createModBlockTag(String tag) {
        return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, tag));
    }

    public static TagKey<Item> createCommonItemTag(String tag) {
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", tag));
    }

    public static TagKey<Block> createCommonBlockTag(String tag) {
        return TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("c", tag));
    }

    public static TagKey<Item> createWisdomTag(String tag) {
        return createModItemTag(tag + "_wisdom_discovers");
    }
}
