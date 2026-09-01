package net.coolsimulations.ForgottenEngineers.data;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.client.CombustorHasFuel;
import net.coolsimulations.ForgottenEngineers.client.InductionFurnaceHasFuel;
import net.coolsimulations.ForgottenEngineers.item.FEItems;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.item.ItemModel;
import net.minecraft.client.resources.model.sprite.Material;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class FEModels {

    public static ModelTemplate FLAT_HANDHELD_HANDLE_ITEM = new ModelTemplate(Optional.of(Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "item/handheld_handle")), Optional.empty(), TextureSlot.LAYER0);
    public static ModelTemplate FLAT_HANDHELD_HANDLE_ITEM_ON = new ModelTemplate(Optional.of(Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "item/handheld_handle")), Optional.of("_on"), TextureSlot.LAYER0);
    public static ModelTemplate FLAT_HANDHELD_HANDLE_ITEM_OFF = new ModelTemplate(Optional.of(Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "item/handheld_handle")), Optional.of("_off"), TextureSlot.LAYER0);

    public static void generateFlatItems(Consumer<Item> flat) {
        flat.accept(FEItems.RESTORATION_WISDOM);
        flat.accept(FEItems.DISTRIBUTION_WISDOM);
        flat.accept(FEItems.COMPRESSION_WISDOM);
        flat.accept(FEItems.INDUCTION_WISDOM);
        flat.accept(FEItems.ENGINEERS_SEAL);
        flat.accept(FEItems.UNIVERSAL_WISDOM);
        flat.accept(FEItems.ROUTER);
        FEItems.DYED_ROUTER.forEach(flat);
        flat.accept(FEItems.COMPRESSOR);
        flat.accept(FEItems.ENDER_ROUTER);
        flat.accept(FEItems.STRIPPER);
    }

    public static void generateHandheldItems(Consumer<Item> handheld) {
        handheld.accept(FEItems.RESTORER);
        handheld.accept(FEItems.FUEL_CARRIER);
        handheld.accept(FEItems.MENDER);
    }

    public static void generateInductionFurnace(ItemModelGenerators itemModels) {
        ItemModel.Unbaked on = generateBooleanModel(FEItems.INDUCTION_FURNACE, FEModels.FLAT_HANDHELD_HANDLE_ITEM_ON, "_on", itemModels.modelOutput);
        ItemModel.Unbaked off = generateBooleanModel(FEItems.INDUCTION_FURNACE, FEModels.FLAT_HANDHELD_HANDLE_ITEM_OFF, "_off", itemModels.modelOutput);
        itemModels.generateBooleanDispatch(FEItems.INDUCTION_FURNACE, new InductionFurnaceHasFuel(), on, off);
    }

    public static void generateCombustor(ItemModelGenerators itemModels) {
        ItemModel.Unbaked on = generateBooleanModel(FEItems.COMBUSTOR, FEModels.FLAT_HANDHELD_HANDLE_ITEM_ON, "_on", itemModels.modelOutput);
        ItemModel.Unbaked off = generateBooleanModel(FEItems.COMBUSTOR, FEModels.FLAT_HANDHELD_HANDLE_ITEM_OFF, "_off", itemModels.modelOutput);
        itemModels.generateBooleanDispatch(FEItems.COMBUSTOR, new CombustorHasFuel(), on, off);
    }

    private static ItemModel.Unbaked generateBooleanModel(final Item item, final ModelTemplate template, final String suffix, BiConsumer<Identifier, ModelInstance> output) {
        Material texture = TextureMapping.getItemTexture(item, suffix);
        return ItemModelUtils.plainModel(template.create(item, TextureMapping.layer0(texture), output));
    }
}
