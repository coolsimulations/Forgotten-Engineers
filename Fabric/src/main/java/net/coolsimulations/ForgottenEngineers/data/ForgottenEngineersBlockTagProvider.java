package net.coolsimulations.ForgottenEngineers.data;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalBlockTags;
import net.minecraft.core.HolderLookup;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ForgottenEngineersBlockTagProvider extends FabricTagsProvider.BlockTagsProvider {

    public ForgottenEngineersBlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider registries) {
        FETags.generateCompressorBlockTag((blocks) -> getOrCreateRawBuilder(FETags.COMPRESSOR_IGNORE_BLOCKS).addOptionalTag(blocks.location()), (singles) -> getOrCreateRawBuilder(FETags.COMPRESSOR_IGNORE_BLOCKS).addElement(singles));
        getOrCreateRawBuilder(FETags.COMPRESSOR_IGNORE_BLOCKS).addOptionalTag(ConventionalBlockTags.PLAYER_WORKSTATIONS_CRAFTING_TABLES.location()).addOptionalTag(ConventionalBlockTags.PLAYER_WORKSTATIONS_FURNACES.location()).addOptionalTag(ConventionalBlockTags.GLASS_PANES.location()).addOptionalTag(ConventionalBlockTags.CHESTS.location()).addOptionalTag(ConventionalBlockTags.BARS.location()).addOptionalTag(ConventionalBlockTags.VILLAGER_JOB_SITES.location());
    }
}
