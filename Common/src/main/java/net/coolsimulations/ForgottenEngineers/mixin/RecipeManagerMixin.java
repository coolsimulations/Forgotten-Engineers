package net.coolsimulations.ForgottenEngineers.mixin;

import net.coolsimulations.ForgottenEngineers.event.FERegistryEvents;
import net.minecraft.world.item.crafting.RecipeManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RecipeManager.class)
public class RecipeManagerMixin {

    @Inject(method = "finalizeRecipeLoading", at = @At("TAIL"))
    public void loadPostRecipes(CallbackInfo ci) {
        FERegistryEvents.POST_RECIPE_LOAD.post().handle((RecipeManager) (Object)this);
    }
}
