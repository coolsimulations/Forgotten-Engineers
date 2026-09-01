package net.coolsimulations.ForgottenEngineers.data;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.model.ModelTemplates;
import net.minecraft.data.PackOutput;
import org.jspecify.annotations.NonNull;

public class ForgottenEngineersModelProvider {

    public static class ItemModels extends ModelProvider {

        public ItemModels(PackOutput output) {
            super(output, ForgottenEngineersCommon.MOD_ID);
        }

        @Override
        protected void registerModels(@NonNull BlockModelGenerators blockModels, @NonNull ItemModelGenerators itemModels) {
            FEModels.generateFlatItems(flat -> itemModels.generateFlatItem(flat, ModelTemplates.FLAT_ITEM));
            FEModels.generateHandheldItems(handheld -> itemModels.generateFlatItem(handheld, FEModels.FLAT_HANDHELD_HANDLE_ITEM));
            FEModels.generateInductionFurnace(itemModels);
            FEModels.generateCombustor(itemModels);
        }
    }
}
