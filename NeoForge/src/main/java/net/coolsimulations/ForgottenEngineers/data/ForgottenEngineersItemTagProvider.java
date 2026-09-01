package net.coolsimulations.ForgottenEngineers.data;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.Tags;
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
        FETags.generateNamedTags((items, tag) -> items.forEach(this.getOrCreateRawBuilder(tag)::addElement));
        FETags.generateEmptyTags(this::getOrCreateRawBuilder);
        FETags.generateRouterTag(item -> getOrCreateRawBuilder(FETags.ROUTERS).addElement(item));
        FETags.generateCompressorItemTag((items) -> getOrCreateRawBuilder(FETags.COMPRESSOR_IGNORE_ITEMS).addTag(items.location()), (singles) -> getOrCreateRawBuilder(FETags.COMPRESSOR_IGNORE_ITEMS).addElement(singles));
        getOrCreateRawBuilder(FETags.COMPRESSOR_IGNORE_ITEMS).addTag(Tags.Items.ARMORS.location()).addTag(Tags.Items.PLAYER_WORKSTATIONS_CRAFTING_TABLES.location()).addTag(Tags.Items.PLAYER_WORKSTATIONS_FURNACES.location()).addTag(Tags.Items.GLASS_PANES.location()).addTag(Tags.Items.CHESTS.location()).addTag(Tags.Items.BARS.location()).addTag(Tags.Items.VILLAGER_JOB_SITES.location()).addTag(Tags.Items.TOOLS.location());
    }
}
