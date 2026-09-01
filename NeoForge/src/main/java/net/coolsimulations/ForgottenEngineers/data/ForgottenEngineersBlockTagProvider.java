package net.coolsimulations.ForgottenEngineers.data;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ForgottenEngineersBlockTagProvider extends BlockTagsProvider {

    public ForgottenEngineersBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(output, lookupProvider, ForgottenEngineersCommon.MOD_ID);
    }

    @Override
    protected void addTags(HolderLookup.@NonNull Provider registries) {
        FETags.generateCompressorBlockTag((blocks) -> getOrCreateRawBuilder(FETags.COMPRESSOR_IGNORE_BLOCKS).addTag(blocks.location()), (singles) -> getOrCreateRawBuilder(FETags.COMPRESSOR_IGNORE_BLOCKS).addElement(singles));
        getOrCreateRawBuilder(FETags.COMPRESSOR_IGNORE_BLOCKS).addTag(Tags.Blocks.PLAYER_WORKSTATIONS_CRAFTING_TABLES.location()).addTag(Tags.Blocks.PLAYER_WORKSTATIONS_FURNACES.location()).addTag(Tags.Blocks.GLASS_PANES.location()).addTag(Tags.Blocks.CHESTS.location()).addTag(Tags.Blocks.BARS.location()).addTag(Tags.Blocks.VILLAGER_JOB_SITES.location());
    }
}
