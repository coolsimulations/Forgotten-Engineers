package net.coolsimulations.ForgottenEngineers.data;

import net.minecraft.advancements.Advancement;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.MultiRegistryBootstrap;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.packs.VanillaRecipeProvider;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Recipe;
import net.neoforged.neoforge.common.Tags;
import org.jspecify.annotations.NonNull;

import java.util.Set;
import java.util.concurrent.CompletableFuture;

public class ForgottenEngineersRecipeProvider extends VanillaRecipeProvider {

    protected ForgottenEngineersRecipeProvider(@NonNull BootstrapContext<Recipe<?>> recipes, @NonNull BootstrapContext<Advancement> advancements) {
        super(recipes, advancements);
    }

    @Override
    public void buildRecipes() {
        HolderGetter<Item> items = this.output.lookup(Registries.ITEM);
        FERecipes.generateItemRecipes(output, items, this::has, Tags.Items.LEATHERS, Tags.Items.GLASS_BLOCKS_CHEAP, Tags.Items.CHESTS_ENDER, Tags.Items.RODS_BLAZE, Tags.Items.INGOTS_COPPER, Tags.Items.INGOTS_IRON);
        FERecipes.generateTagRecipes(output, items, this::has);
    }

    public static MultiRegistryBootstrap create() {
        return new MultiRegistryBootstrap() {
            @Override
            public @NonNull Set<ResourceKey<? extends Registry<?>>> requestedRegistries() {
                return Set.of(Registries.RECIPE, Registries.ADVANCEMENT);
            }

            @Override
            public void run(final MultiRegistryBootstrap.@NonNull BootstrapGetter registries) {
                new ForgottenEngineersRecipeProvider(registries.get(Registries.RECIPE), registries.get(Registries.ADVANCEMENT)).buildRecipes();
            }
        };
    }
}
