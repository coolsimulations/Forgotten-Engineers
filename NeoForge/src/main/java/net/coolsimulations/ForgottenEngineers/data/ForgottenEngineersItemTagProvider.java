package net.coolsimulations.ForgottenEngineers.data;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ItemTagsProvider;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ForgottenEngineersItemTagProvider extends ItemTagsProvider {

    public ForgottenEngineersItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, ForgottenEngineersCommon.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider registries) {
        FETags.generateNamedSingleTags((item, tag) -> this.getOrCreateRawBuilder(tag).addElement(item));
        FETags.generateEmptyTags(this::getOrCreateRawBuilder);
    }
}
