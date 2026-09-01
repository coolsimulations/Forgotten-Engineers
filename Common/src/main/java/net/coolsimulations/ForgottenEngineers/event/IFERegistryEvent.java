package net.coolsimulations.ForgottenEngineers.event;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.crafting.RecipeManager;

public interface IFERegistryEvent extends IFEEvent {

    interface PostRecipeLoad extends IFERegistryEvent {
        void handle(RecipeManager manager);
    }
}
