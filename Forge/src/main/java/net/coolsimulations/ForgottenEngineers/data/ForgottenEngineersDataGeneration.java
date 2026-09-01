package net.coolsimulations.ForgottenEngineers.data;

import net.coolsimulations.ForgottenEngineers.event.FERenderEvents;
import net.minecraft.client.renderer.item.properties.conditional.ConditionalItemModelProperties;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.data.event.GatherDataEvent;

import java.util.List;

public class ForgottenEngineersDataGeneration {

    public static void gatherData(GatherDataEvent event) {
        DataGenerator gen = event.getGenerator();
        PackOutput packOutput = gen.getPackOutput();

        FERenderEvents.ITEM_PROPERTIES.post().handle(ConditionalItemModelProperties.ID_MAPPER::put);

        gen.addProvider(event.includeClient(), new ForgottenEngineersModelProvider.ItemModels(packOutput));
        gen.addProvider(event.includeClient(), new ForgottenEngineersSoundProvider(packOutput, event.getExistingFileHelper()));
        gen.addProvider(event.includeClient(), new ForgottenEngineersItemTagProvider(packOutput, event.getLookupProvider(), event.getExistingFileHelper()));
        gen.addProvider(event.includeClient(), new ForgottenEngineersBlockTagProvider(packOutput, event.getLookupProvider(), event.getExistingFileHelper()));
        gen.addProvider(event.includeClient(), new ForgottenEngineersRecipeProvider(packOutput, event.getLookupProvider()));
        gen.addProvider(event.includeClient(), new ForgottenEngineersGlobalLootModifierProvider(packOutput, event.getLookupProvider()));
        gen.addProvider(event.includeClient(), new ForgottenEngineersAdvancementProvider(packOutput, event.getLookupProvider(), event.getExistingFileHelper(), List.of(new ForgottenEngineersAdvancementProvider.AdvancementGenerator())));

        gen.addProvider(event.includeClient(), new ForgottenEngineersLanguageProvider.EnglishProvider(packOutput));
        gen.addProvider(event.includeClient(), new ForgottenEngineersLanguageProvider.EnglishCommonwealthProvider(packOutput, "en_au"));
        gen.addProvider(event.includeClient(), new ForgottenEngineersLanguageProvider.EnglishCommonwealthProvider(packOutput, "en_ca"));
        gen.addProvider(event.includeClient(), new ForgottenEngineersLanguageProvider.EnglishCommonwealthProvider(packOutput, "en_gb"));
        gen.addProvider(event.includeClient(), new ForgottenEngineersLanguageProvider.EnglishCommonwealthProvider(packOutput, "en_nz"));
        gen.addProvider(event.includeClient(), new ForgottenEngineersLanguageProvider.UpsideDownProvider(packOutput));
        gen.addProvider(event.includeClient(), new ForgottenEngineersLanguageProvider.ShakespeareanProvider(packOutput));
        gen.addProvider(event.includeClient(), new ForgottenEngineersLanguageProvider.PirateProvider(packOutput));
        gen.addProvider(event.includeClient(), new ForgottenEngineersLanguageProvider.LOLCatProvider(packOutput));
        gen.addProvider(event.includeClient(), new ForgottenEngineersLanguageProvider.SpanishProvider(packOutput, "es_es"));
        gen.addProvider(event.includeClient(), new ForgottenEngineersLanguageProvider.SpanishProvider(packOutput, "es_ar"));
        gen.addProvider(event.includeClient(), new ForgottenEngineersLanguageProvider.SpanishProvider(packOutput, "es_cl"));
        gen.addProvider(event.includeClient(), new ForgottenEngineersLanguageProvider.SpanishProvider(packOutput, "es_ec"));
        gen.addProvider(event.includeClient(), new ForgottenEngineersLanguageProvider.SpanishProvider(packOutput, "es_mx"));
        gen.addProvider(event.includeClient(), new ForgottenEngineersLanguageProvider.SpanishProvider(packOutput, "es_uy"));
        gen.addProvider(event.includeClient(), new ForgottenEngineersLanguageProvider.SpanishProvider(packOutput, "es_ve"));
        gen.addProvider(event.includeClient(), new ForgottenEngineersLanguageProvider.JapaneseProvider(packOutput));
        gen.addProvider(event.includeClient(), new ForgottenEngineersLanguageProvider.RussianProvider(packOutput));
        gen.addProvider(event.includeClient(), new ForgottenEngineersLanguageProvider.ChineseSimplifiedProvider(packOutput));
        gen.addProvider(event.includeClient(), new ForgottenEngineersLanguageProvider.ChineseTraditionalProvider(packOutput));
        gen.addProvider(event.includeClient(), new ForgottenEngineersLanguageProvider.KoreanProvider(packOutput));
        gen.addProvider(event.includeClient(), new ForgottenEngineersLanguageProvider.GermanProvider(packOutput));
        gen.addProvider(event.includeClient(), new ForgottenEngineersLanguageProvider.FrenchProvider(packOutput));
        gen.addProvider(event.includeClient(), new ForgottenEngineersLanguageProvider.PortugueseBrazilProvider(packOutput));
        gen.addProvider(event.includeClient(), new ForgottenEngineersLanguageProvider.ItalianProvider(packOutput));
    }
}
