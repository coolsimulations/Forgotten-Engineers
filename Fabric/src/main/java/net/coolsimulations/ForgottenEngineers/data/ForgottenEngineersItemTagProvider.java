package net.coolsimulations.ForgottenEngineers.data;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.minecraft.core.HolderLookup;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ForgottenEngineersItemTagProvider extends FabricTagsProvider.ItemTagsProvider {

    public ForgottenEngineersItemTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider registries) {
        FETags.generateNamedSingleTags((item, tag) -> this.getOrCreateRawBuilder(tag).addElement(item));
        FETags.generateEmptyTags(this::getOrCreateRawBuilder);
    }
}
