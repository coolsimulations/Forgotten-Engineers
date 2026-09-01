package net.coolsimulations.ForgottenEngineers.data;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ForgottenEngineersRecipeProvider extends FabricRecipeProvider {

    public ForgottenEngineersRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected @NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider registries, @NonNull RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                HolderLookup.RegistryLookup<Item> items = registries.lookupOrThrow(Registries.ITEM);
                FERecipes.generateItemRecipes(output, items, this::has, ConventionalItemTags.LEATHERS, ConventionalItemTags.GLASS_BLOCKS_CHEAP, ConventionalItemTags.ENDER_CHESTS, ConventionalItemTags.BLAZE_RODS, ConventionalItemTags.COPPER_INGOTS, ConventionalItemTags.IRON_INGOTS);
                FERecipes.generateTagRecipes(output, items, this::has);
            }
        };
    }

    @Override
    public @NonNull String getName() {
        return getClass().getSimpleName();
    }
}
