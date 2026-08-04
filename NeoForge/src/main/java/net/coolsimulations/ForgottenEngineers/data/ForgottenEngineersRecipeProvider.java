package net.coolsimulations.ForgottenEngineers.data;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.Tags;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ForgottenEngineersRecipeProvider extends RecipeProvider.Runner {

    protected ForgottenEngineersRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected @NonNull RecipeProvider createRecipeProvider(HolderLookup.@NonNull Provider registries, @NonNull RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                HolderLookup.RegistryLookup<Item> items = registries.lookupOrThrow(Registries.ITEM);
                FERecipes.generateShapedRecipes(output, items, this::has, Tags.Items.LEATHERS);
            }
        };
    }

    @Override
    public @NonNull String getName() {
        return getClass().getSimpleName();
    }
}
