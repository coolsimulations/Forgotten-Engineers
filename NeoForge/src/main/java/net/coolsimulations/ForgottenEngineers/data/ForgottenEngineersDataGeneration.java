package net.coolsimulations.ForgottenEngineers.data;

import net.neoforged.neoforge.data.event.GatherDataEvent;

import java.util.List;

public class ForgottenEngineersDataGeneration {

    public static void gatherData(GatherDataEvent.Client event) {
        event.createProvider(ForgottenEngineersModelProvider.ItemModels::new);
        event.createProvider(ForgottenEngineersSoundProvider::new);
        event.createProvider(ForgottenEngineersItemTagProvider::new);
        event.createProvider(ForgottenEngineersBlockTagProvider::new);
        event.createProvider(ForgottenEngineersRecipeProvider::new);
        event.createProvider(ForgottenEngineersGlobalLootModifierProvider::new);
        event.createProvider(output -> new ForgottenEngineersAdvancementProvider(output, event.getLookupProvider(), List.of(new ForgottenEngineersAdvancementProvider.AdvancementGenerator())));

        event.createProvider(ForgottenEngineersLanguageProvider.EnglishProvider::new);
        event.createProvider(output -> new ForgottenEngineersLanguageProvider.EnglishCommonwealthProvider(output, "en_au"));
        event.createProvider(output -> new ForgottenEngineersLanguageProvider.EnglishCommonwealthProvider(output, "en_ca"));
        event.createProvider(output -> new ForgottenEngineersLanguageProvider.EnglishCommonwealthProvider(output, "en_gb"));
        event.createProvider(output -> new ForgottenEngineersLanguageProvider.EnglishCommonwealthProvider(output, "en_nz"));
        event.createProvider(ForgottenEngineersLanguageProvider.UpsideDownProvider::new);
        event.createProvider(ForgottenEngineersLanguageProvider.ShakespeareanProvider::new);
        event.createProvider(ForgottenEngineersLanguageProvider.PirateProvider::new);
        event.createProvider(ForgottenEngineersLanguageProvider.LOLCatProvider::new);
        event.createProvider(output -> new ForgottenEngineersLanguageProvider.SpanishProvider(output, "es_es"));
        event.createProvider(output -> new ForgottenEngineersLanguageProvider.SpanishProvider(output, "es_ar"));
        event.createProvider(output -> new ForgottenEngineersLanguageProvider.SpanishProvider(output, "es_cl"));
        event.createProvider(output -> new ForgottenEngineersLanguageProvider.SpanishProvider(output, "es_ec"));
        event.createProvider(output -> new ForgottenEngineersLanguageProvider.SpanishProvider(output, "es_mx"));
        event.createProvider(output -> new ForgottenEngineersLanguageProvider.SpanishProvider(output, "es_uy"));
        event.createProvider(output -> new ForgottenEngineersLanguageProvider.SpanishProvider(output, "es_ve"));
        event.createProvider(ForgottenEngineersLanguageProvider.JapaneseProvider::new);
        event.createProvider(ForgottenEngineersLanguageProvider.RussianProvider::new);
        event.createProvider(ForgottenEngineersLanguageProvider.ChineseSimplifiedProvider::new);
        event.createProvider(ForgottenEngineersLanguageProvider.ChineseTraditionalProvider::new);
        event.createProvider(ForgottenEngineersLanguageProvider.KoreanProvider::new);
        event.createProvider(ForgottenEngineersLanguageProvider.GermanProvider::new);
        event.createProvider(ForgottenEngineersLanguageProvider.FrenchProvider::new);
        event.createProvider(ForgottenEngineersLanguageProvider.PortugueseBrazilProvider::new);
        event.createProvider(ForgottenEngineersLanguageProvider.ItalianProvider::new);
    }
}
