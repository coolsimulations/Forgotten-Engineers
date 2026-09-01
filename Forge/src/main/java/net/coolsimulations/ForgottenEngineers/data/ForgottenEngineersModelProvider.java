package net.coolsimulations.ForgottenEngineers.data;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.jspecify.annotations.NonNull;

public class ForgottenEngineersModelProvider {

    public static class ItemModels extends ModelProvider {

        public ItemModels(PackOutput output) {
            super(output);
        }

        @Override
        protected java.util.stream.@NonNull Stream<Item> getKnownItems() {
            return BuiltInRegistries.ITEM.stream().filter(item -> ForgottenEngineersCommon.MOD_ID.equals(item.builtInRegistryHolder().key().identifier().getNamespace()));
        }

        @Override
        protected java.util.stream.@NonNull Stream<Block> getKnownBlocks() {
            return BuiltInRegistries.BLOCK.stream().filter(block -> ForgottenEngineersCommon.MOD_ID.equals(block.builtInRegistryHolder().key().identifier().getNamespace()));
        }

        @Override
        protected @NonNull ItemModelGenerators getItemModelGenerators(@NonNull ItemInfoCollector items, @NonNull SimpleModelCollector models) {
            return new ItemModelGenerators(items, models) {
                @Override
                public void run() {
                    FEModels.generateFlatItems(flat -> this.generateFlatItem(flat, ModelTemplates.FLAT_ITEM));
                    FEModels.generateHandheldItems(handheld -> this.generateFlatItem(handheld, FEModels.FLAT_HANDHELD_HANDLE_ITEM));
                    FEModels.generateInductionFurnace(this);
                    FEModels.generateCombustor(this);
                }
            };
        }

        @Override
        protected @NonNull BlockModelGenerators getBlockModelGenerators(@NonNull BlockStateGeneratorCollector blocks, @NonNull ItemInfoCollector items, @NonNull SimpleModelCollector models) {
            return new BlockModelGenerators(blocks, items, models) {
                @Override
                public void run() {
                }
            };
        }
    }
}
