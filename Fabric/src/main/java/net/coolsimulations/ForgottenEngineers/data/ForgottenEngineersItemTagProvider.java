package net.coolsimulations.ForgottenEngineers.data;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
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
        FETags.generateNamedTags((items, tag) -> items.forEach(this.getOrCreateRawBuilder(tag)::addElement));
        FETags.generateEmptyTags(this::getOrCreateRawBuilder);
        FETags.generateRouterTag(item -> getOrCreateRawBuilder(FETags.ROUTERS).addElement(item));
        FETags.generateCompressorItemTag((items) -> getOrCreateRawBuilder(FETags.COMPRESSOR_IGNORE_ITEMS).addOptionalTag(items.location()), (singles) -> getOrCreateRawBuilder(FETags.COMPRESSOR_IGNORE_ITEMS).addElement(singles));
        getOrCreateRawBuilder(FETags.COMPRESSOR_IGNORE_ITEMS).addOptionalTag(ConventionalItemTags.ARMORS.location()).addOptionalTag(ConventionalItemTags.PLAYER_WORKSTATIONS_CRAFTING_TABLES.location()).addOptionalTag(ConventionalItemTags.PLAYER_WORKSTATIONS_FURNACES.location()).addOptionalTag(ConventionalItemTags.GLASS_PANES.location()).addOptionalTag(ConventionalItemTags.CHESTS.location()).addOptionalTag(ConventionalItemTags.BARS.location()).addOptionalTag(ConventionalItemTags.VILLAGER_JOB_SITES.location()).addOptionalTag(ConventionalItemTags.TOOLS.location());
    }
}
