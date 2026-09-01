package net.coolsimulations.ForgottenEngineers.data;

import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.advancements.AdvancementSubProvider;
import org.jspecify.annotations.NonNull;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ForgottenEngineersAdvancementProvider extends AdvancementProvider {

    public ForgottenEngineersAdvancementProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries, List<AdvancementSubProvider> subProviders) {
        super(output, registries, subProviders);
    }

    public static class AdvancementGenerator implements AdvancementSubProvider {

        @Override
        public void generate(HolderLookup.@NonNull Provider registries, @NonNull Consumer<AdvancementHolder> consumer) {
            FEAdvancements.generateAdvancements(registries, (identifier, advancement) -> advancement.save(consumer, identifier));
        }
    }
}
