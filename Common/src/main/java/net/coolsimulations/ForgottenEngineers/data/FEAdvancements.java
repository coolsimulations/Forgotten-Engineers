package net.coolsimulations.ForgottenEngineers.data;

import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.item.FEItems;
import net.coolsimulations.ForgottenEngineers.item.ForgottenEngineersItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.triggers.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import java.util.function.BiConsumer;

public class FEAdvancements {

    public static void generateAdvancements(HolderLookup.Provider registries, BiConsumer<Identifier, Advancement.Builder> advancements) {
        advancements.accept(Identifier.fromNamespaceAndPath(ForgottenEngineersCommon.MOD_ID, "adventure/" + ForgottenEngineersItems.ENGINEERS_SEAL_ID.getPath()), Advancement.Builder.advancement().parent(Identifier.withDefaultNamespace("adventure/root")).display(FEItems.ENGINEERS_SEAL, Component.translatable("advancements."  + ForgottenEngineersCommon.MOD_ID + ".adventure." + ForgottenEngineersItems.ENGINEERS_SEAL_ID.getPath() + ".title"), Component.translatable("advancements."  + ForgottenEngineersCommon.MOD_ID + ".adventure." + ForgottenEngineersItems.ENGINEERS_SEAL_ID.getPath() + ".description"), null, AdvancementType.TASK, true, true, false).addCriterion(ForgottenEngineersItems.ENGINEERS_SEAL_ID.getPath(), InventoryChangeTrigger.TriggerInstance.hasItems(FEItems.ENGINEERS_SEAL)));
    }
}
