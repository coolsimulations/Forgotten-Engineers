package net.coolsimulations.ForgottenEngineers.data;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class ForgottenEngineersDataGeneration implements DataGeneratorEntrypoint {

    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        FabricDataGenerator.Pack pack = generator.createPack();
        pack.addProvider(ForgottenEngineersModelProvider::new);
        pack.addProvider(ForgottenEngineersItemTagProvider::new);
        pack.addProvider(ForgottenEngineersRecipeProvider::new);

        pack.addProvider(ForgottenEngineersLanguageProvider.EnglishProvider::new);
        pack.addProvider(ForgottenEngineersLanguageProvider.UpsideDownProvider::new);
        pack.addProvider(ForgottenEngineersLanguageProvider.ShakespeareanProvider::new);
        pack.addProvider(ForgottenEngineersLanguageProvider.PirateProvider::new);
        pack.addProvider(ForgottenEngineersLanguageProvider.LOLCatProvider::new);
        pack.addProvider((output, registriesFuture) -> new ForgottenEngineersLanguageProvider.SpanishProvider(output, "es_es", registriesFuture));
        pack.addProvider((output, registriesFuture) -> new ForgottenEngineersLanguageProvider.SpanishProvider(output, "es_ar", registriesFuture));
        pack.addProvider((output, registriesFuture) -> new ForgottenEngineersLanguageProvider.SpanishProvider(output, "es_cl", registriesFuture));
        pack.addProvider((output, registriesFuture) -> new ForgottenEngineersLanguageProvider.SpanishProvider(output, "es_ec", registriesFuture));
        pack.addProvider((output, registriesFuture) -> new ForgottenEngineersLanguageProvider.SpanishProvider(output, "es_mx", registriesFuture));
        pack.addProvider((output, registriesFuture) -> new ForgottenEngineersLanguageProvider.SpanishProvider(output, "es_uy", registriesFuture));
        pack.addProvider((output, registriesFuture) -> new ForgottenEngineersLanguageProvider.SpanishProvider(output, "es_ve", registriesFuture));
        pack.addProvider(ForgottenEngineersLanguageProvider.JapaneseProvider::new);
        pack.addProvider(ForgottenEngineersLanguageProvider.RussianProvider::new);
        pack.addProvider(ForgottenEngineersLanguageProvider.ChineseSimplifiedProvider::new);
        pack.addProvider(ForgottenEngineersLanguageProvider.ChineseTraditionalProvider::new);
        pack.addProvider(ForgottenEngineersLanguageProvider.KoreanProvider::new);
        pack.addProvider(ForgottenEngineersLanguageProvider.GermanProvider::new);
        pack.addProvider(ForgottenEngineersLanguageProvider.FrenchProvider::new);
        pack.addProvider(ForgottenEngineersLanguageProvider.PortugueseBrazilProvider::new);
    }
}
