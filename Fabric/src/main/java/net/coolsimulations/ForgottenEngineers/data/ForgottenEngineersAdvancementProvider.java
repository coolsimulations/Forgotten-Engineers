package net.coolsimulations.ForgottenEngineers.data;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.core.HolderLookup;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ForgottenEngineersAdvancementProvider extends FabricAdvancementProvider {

    public ForgottenEngineersAdvancementProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    public void generateAdvancement(HolderLookup.@NonNull Provider registries, @NonNull Consumer<AdvancementHolder> consumer) {
        FEAdvancements.generateAdvancements(registries, (identifier, advancement) -> advancement.save(consumer, identifier));
    }
}
