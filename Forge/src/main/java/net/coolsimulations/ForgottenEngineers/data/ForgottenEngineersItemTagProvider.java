package net.coolsimulations.ForgottenEngineers.data;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.VanillaItemTagsProvider;
import net.minecraftforge.common.Tags;
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
        FETags.generateNamedTags((items, tag) -> items.forEach(this.getOrCreateRawBuilder(tag)::addElement));
        FETags.generateEmptyTags(this::getOrCreateRawBuilder);
        FETags.generateRouterTag(item -> getOrCreateRawBuilder(FETags.ROUTERS).addElement(item));
        FETags.generateCompressorItemTag((items) -> getOrCreateRawBuilder(FETags.COMPRESSOR_IGNORE_ITEMS).addTag(items.location()), (singles) -> getOrCreateRawBuilder(FETags.COMPRESSOR_IGNORE_ITEMS).addElement(singles));
        getOrCreateRawBuilder(FETags.COMPRESSOR_IGNORE_ITEMS).addTag(Tags.Items.ARMORS.location()).addTag(Tags.Items.PLAYER_WORKSTATIONS_CRAFTING_TABLES.location()).addTag(Tags.Items.PLAYER_WORKSTATIONS_FURNACES.location()).addTag(Tags.Items.GLASS_PANES.location()).addTag(Tags.Items.CHESTS.location()).addTag(Tags.Items.BARS.location()).addTag(Tags.Items.VILLAGER_JOB_SITES.location()).addTag(Tags.Items.TOOLS.location());
    }
}
