package net.coolsimulations.ForgottenEngineers.data;

import net.minecraft.advancements.Advancement;
import net.minecraft.core.registries.SingleRegistryBootstrap;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.data.worldgen.BootstrapContext;

import java.util.List;

public class ForgottenEngineersAdvancementProvider {

    public static SingleRegistryBootstrap<Advancement> create() {
        return new AdvancementProvider(List.of(AdvancementGenerator::new));
    }

    public static class AdvancementGenerator extends AdvancementSubProvider {

        protected AdvancementGenerator(BootstrapContext<Advancement> output) {
            super(output);
        }

        @Override
        public void generate() {
            FEAdvancements.generateAdvancements((identifier, advancement) -> advancement.save(this.output, identifier));
        }
    }
}
