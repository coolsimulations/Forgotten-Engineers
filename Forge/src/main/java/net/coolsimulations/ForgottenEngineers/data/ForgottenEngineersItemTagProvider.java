package net.coolsimulations.ForgottenEngineers.data;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.VanillaItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ForgottenEngineersItemTagProvider extends VanillaItemTagsProvider {

    public ForgottenEngineersItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, ForgottenEngineersCommon.MOD_ID, existingFileHelper);
    }

    @Override
    public void addTags(HolderLookup.@NonNull Provider lookupProvider) {
        FETags.generateNamedSingleTags((item, tag) -> this.getOrCreateRawBuilder(tag).addElement(item));
        FETags.generateEmptyTags(this::getOrCreateRawBuilder);
    }
}
