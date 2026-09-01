package net.coolsimulations.ForgottenEngineers.data;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.VanillaBlockTagsProvider;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ForgottenEngineersBlockTagProvider extends VanillaBlockTagsProvider {

    public ForgottenEngineersBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, ForgottenEngineersCommon.MOD_ID, existingFileHelper);
    }

    @Override
    public void addTags(HolderLookup.@NonNull Provider lookupProvider) {
        FETags.generateCompressorBlockTag((blocks) -> getOrCreateRawBuilder(FETags.COMPRESSOR_IGNORE_BLOCKS).addTag(blocks.location()), (singles) -> getOrCreateRawBuilder(FETags.COMPRESSOR_IGNORE_BLOCKS).addElement(singles));
        getOrCreateRawBuilder(FETags.COMPRESSOR_IGNORE_BLOCKS).addTag(Tags.Blocks.PLAYER_WORKSTATIONS_CRAFTING_TABLES.location()).addTag(Tags.Blocks.PLAYER_WORKSTATIONS_FURNACES.location()).addTag(Tags.Blocks.GLASS_PANES.location()).addTag(Tags.Blocks.CHESTS.location()).addTag(Tags.Blocks.BARS.location()).addTag(Tags.Blocks.VILLAGER_JOB_SITES.location());
    }
}
