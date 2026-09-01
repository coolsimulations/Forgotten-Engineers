package net.coolsimulations.ForgottenEngineers;

import net.coolsimulations.ForgottenEngineers.client.FEClientEvents;
import net.coolsimulations.ForgottenEngineers.item.CompressorItem;
import net.coolsimulations.ForgottenEngineers.item.InductionFurnaceItem;
import net.coolsimulations.ForgottenEngineers.network.CompressorRecipeSyncPayload;
import net.coolsimulations.ForgottenEngineers.network.InductionRecipeSyncPayload;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ForgottenEngineersClientCommon extends ForgottenEngineersCommon {

    @Override
    public void init() {
        if (FEServices.REGISTRY.getPlatformType() != FERegistration.PlatformType.FABRIC)
            super.init();
        FEClientEvents.init();
    }

    public static void handleInductionRecipes(InductionRecipeSyncPayload payload) {
        InductionFurnaceItem.INDUCTION_RECIPES.clear();

        for (InductionRecipeSyncPayload.RecipeData data : payload.recipes()) {

            Item input = BuiltInRegistries.ITEM.get(BuiltInRegistries.ITEM.getKey(data.input())).get().value();
            Item output = BuiltInRegistries.ITEM.get(BuiltInRegistries.ITEM.getKey(data.output())).get().value();

            if (input == null || output == null)
                continue;

            InductionFurnaceItem.INDUCTION_RECIPES.put(input, new InductionFurnaceItem.InductionRecipe(new ItemStack(output), data.cookingTime(), data.experience()));
        }
    }

    public static void handleCompressorRecipes(CompressorRecipeSyncPayload payload) {
        CompressorItem.COMPRESSOR_RECIPES.clear();

        for (CompressorRecipeSyncPayload.RecipeData recipe : payload.recipes())
            CompressorItem.COMPRESSOR_RECIPES.put(recipe.input().copy(), recipe.output().copy());
    }
}
