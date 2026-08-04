package net.coolsimulations.ForgottenEngineers.data;

import net.coolsimulations.ForgottenEngineers.item.FEItems;
import net.coolsimulations.ForgottenEngineers.item.ForgottenEngineersItems;
import net.minecraft.advancements.triggers.Criterion;
import net.minecraft.advancements.triggers.InventoryChangeTrigger;
import net.minecraft.core.HolderGetter;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;

import java.util.function.Function;

public class FERecipes {

    public static void generateShapedRecipes(RecipeOutput output, HolderGetter<Item> items, Function<Item, Criterion<InventoryChangeTrigger.TriggerInstance>> has, TagKey<Item> leather) {

        ShapedRecipeBuilder.shaped(items, RecipeCategory.MISC, FEItems.RESTORER).pattern("#W#").pattern("#A#").pattern("#B#").define('W', FEItems.RESTORATION_WISDOM).define('#', leather).define('A', Items.ANVIL).define('B', ItemTags.BUNDLES).unlockedBy("has_" + ForgottenEngineersItems.RESTORATION_WISDOM_ID.getPath(), has.apply(FEItems.RESTORATION_WISDOM)).save(output);
        ShapedRecipeBuilder.shaped(items, RecipeCategory.MISC, FEItems.RESTORATION_WISDOM, 2).pattern("#A#").pattern("#W#").pattern("#A#").define('W', FEItems.RESTORATION_WISDOM).define('#', FETags.PAPERS).define('A', Items.ANVIL).unlockedBy("has_" + ForgottenEngineersItems.RESTORATION_WISDOM_ID.getPath(), has.apply(FEItems.RESTORATION_WISDOM)).save(output);
    }
}
