package net.coolsimulations.ForgottenEngineers.data;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.item.ForgottenEngineersItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.references.ItemIds;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class FETags {

    public static final TagKey<Item> PAPERS = createCommonItemTag("papers");

    public static final TagKey<Item> RESTORATION_WISDOM_DISCOVERS = createWisdomTag("restoration");

    public static final TagKey<Item> RESTORER_IGNORE_MATERIALS = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "restorer_ignore_materials"));
    public static final TagKey<Item> RESTORER_IGNORE_TOOLS = TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "restorer_ignore_tools"));

    public static void generateNamedSingleTags(BiConsumer<Identifier, TagKey<Item>> singles) {
        singles.accept(ItemIds.PAPER.identifier(), PAPERS);
        singles.accept(ForgottenEngineersItems.RESTORER_ID, RESTORATION_WISDOM_DISCOVERS);
    }

    public static void generateEmptyTags(Consumer<TagKey<Item>> singles) {
        singles.accept(RESTORER_IGNORE_MATERIALS);
        singles.accept(RESTORER_IGNORE_TOOLS);
    }

    public static TagKey<Item> createCommonItemTag(String tag) {
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath("c", tag));
    }

    public static TagKey<Item> createWisdomTag(String tag) {
        return TagKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, tag + "_wisdom_discovers"));
    }
}
