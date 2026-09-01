package net.coolsimulations.ForgottenEngineers.data;

import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ForgottenEngineersAdvancementProvider extends ForgeAdvancementProvider {

    public ForgottenEngineersAdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, ExistingFileHelper existingFileHelper, List<ForgeAdvancementProvider.AdvancementGenerator> subProviders) {
        super(output, registries, existingFileHelper, subProviders);
    }

    public static class AdvancementGenerator implements ForgeAdvancementProvider.AdvancementGenerator {

        @Override
        public void generate(HolderLookup.@NonNull Provider registries, @NonNull Consumer<AdvancementHolder> consumer, @NonNull ExistingFileHelper existingFileHelper) {
            FEAdvancements.generateAdvancements(registries, (identifier, advancement) -> advancement.save(consumer, identifier));
        }
    }
}
