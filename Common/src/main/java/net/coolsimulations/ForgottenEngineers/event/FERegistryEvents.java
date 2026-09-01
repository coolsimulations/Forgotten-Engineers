package net.coolsimulations.ForgottenEngineers.event;

public class FERegistryEvents {

    public static final FEEvent<IFERegistryEvent.PostRecipeLoad> POST_RECIPE_LOAD = new FEEvent<>(listeners -> (manager) -> {
        for(var listener : listeners)
            listener.handle(manager);
    });
}
