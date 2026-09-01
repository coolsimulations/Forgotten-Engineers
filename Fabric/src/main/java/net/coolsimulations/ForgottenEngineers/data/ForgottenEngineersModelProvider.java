package net.coolsimulations.ForgottenEngineers.data;

import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;
import org.jspecify.annotations.NonNull;

public class ForgottenEngineersModelProvider extends FabricModelProvider {

    public ForgottenEngineersModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(@NonNull BlockModelGenerators blockModelGenerators) {

    }

    @Override
    public void generateItemModels(@NonNull ItemModelGenerators itemModelGenerators) {
        FEModels.generateFlatItems(flat -> itemModelGenerators.generateFlatItem(flat, ModelTemplates.FLAT_ITEM));
        FEModels.generateHandheldItems(handheld -> itemModelGenerators.generateFlatItem(handheld, FEModels.FLAT_HANDHELD_HANDLE_ITEM));
        FEModels.generateInductionFurnace(itemModelGenerators);
        FEModels.generateCombustor(itemModelGenerators);
    }
}
