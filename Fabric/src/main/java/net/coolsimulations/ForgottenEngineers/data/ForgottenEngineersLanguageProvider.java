package net.coolsimulations.ForgottenEngineers.data;

import com.github.houbb.opencc4j.util.ZhTwConverterUtil;
import net.coolsimulations.ForgottenEngineers.data.lang.*;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;
import org.jspecify.annotations.NonNull;

import java.util.concurrent.CompletableFuture;

public class ForgottenEngineersLanguageProvider {

    public static class EnglishProvider extends FabricLanguageProvider {

        public EnglishProvider(FabricPackOutput output, CompletableFuture<HolderLookup.@NonNull Provider> registriesFuture) {
            super(output, "en_us", registriesFuture);
        }

        @Override
        public void generateTranslations(HolderLookup.@NonNull Provider registryLookup, TranslationBuilder builder) {
            FEEnglishLang.generateItems(builder::add);
            FEEnglishLang.generateItemTags(builder::add);
            FEEnglishLang.generateSounds(builder::add);
            FEEnglishLang.generateCustom(builder::add);
        }
    }

    public static class UpsideDownProvider extends FabricLanguageProvider {

        public UpsideDownProvider(FabricPackOutput output, CompletableFuture<HolderLookup.@NonNull Provider> registriesFuture) {
            super(output, "en_ud", registriesFuture);
            FEUpsideDownLang.init();
        }

        @Override
        public void generateTranslations(HolderLookup.@NonNull Provider registryLookup, TranslationBuilder builder) {
            FEUpsideDownLang.generateItems(builder::add);
            FEUpsideDownLang.generateItemTags(builder::add);
            FEUpsideDownLang.generateSounds(builder::add);
            FEUpsideDownLang.generateCustom(builder::add);
        }
    }

    public static class ShakespeareanProvider extends FabricLanguageProvider {

        public ShakespeareanProvider(FabricPackOutput output, CompletableFuture<HolderLookup.@NonNull Provider> registriesFuture) {
            super(output, "enws", registriesFuture);
        }

        @Override
        public void generateTranslations(HolderLookup.@NonNull Provider registryLookup, TranslationBuilder builder) {
            FEShakespeareanLang.generateItems(builder::add);
            FEShakespeareanLang.generateItemTags(builder::add);
            FEShakespeareanLang.generateSounds(builder::add);
            FEShakespeareanLang.generateCustom(builder::add);
        }
    }

    public static class PirateProvider extends FabricLanguageProvider {

        public PirateProvider(FabricPackOutput output, CompletableFuture<HolderLookup.@NonNull Provider> registriesFuture) {
            super(output, "en_pt", registriesFuture);
        }

        @Override
        public void generateTranslations(HolderLookup.@NonNull Provider registryLookup, TranslationBuilder builder) {
            FEPirateLang.generateItems(builder::add);
            FEPirateLang.generateItemTags(builder::add);
            FEPirateLang.generateSounds(builder::add);
            FEPirateLang.generateCustom(builder::add);
        }
    }

    public static class LOLCatProvider extends FabricLanguageProvider {

        public LOLCatProvider(FabricPackOutput output, CompletableFuture<HolderLookup.@NonNull Provider> registriesFuture) {
            super(output, "lol_us", registriesFuture);
        }

        @Override
        public void generateTranslations(HolderLookup.@NonNull Provider registryLookup, TranslationBuilder builder) {
            FELOLCatLang.generateItems(builder::add);
            FELOLCatLang.generateItemTags(builder::add);
            FELOLCatLang.generateSounds(builder::add);
            FELOLCatLang.generateCustom(builder::add);
        }
    }

    public static class SpanishProvider extends FabricLanguageProvider {

        public SpanishProvider(FabricPackOutput output, String locale, CompletableFuture<HolderLookup.@NonNull Provider> registriesFuture) {
            super(output, locale, registriesFuture);
        }

        @Override
        public void generateTranslations(HolderLookup.@NonNull Provider registryLookup, TranslationBuilder builder) {
            FESpanishLang.generateItems(builder::add);
            FESpanishLang.generateItemTags(builder::add);
            FESpanishLang.generateSounds(builder::add);
            FESpanishLang.generateCustom(builder::add);
        }
    }

    public static class JapaneseProvider extends FabricLanguageProvider {

        public JapaneseProvider(FabricPackOutput output, CompletableFuture<HolderLookup.@NonNull Provider> registriesFuture) {
            super(output, "ja_jp", registriesFuture);
        }

        @Override
        public void generateTranslations(HolderLookup.@NonNull Provider registryLookup, TranslationBuilder builder) {
            FEJapaneseLang.generateItems(builder::add);
            FEJapaneseLang.generateItemTags(builder::add);
            FEJapaneseLang.generateSounds(builder::add);
            FEJapaneseLang.generateCustom(builder::add);
        }
    }

    public static class RussianProvider extends FabricLanguageProvider {

        public RussianProvider(FabricPackOutput output, CompletableFuture<HolderLookup.@NonNull Provider> registriesFuture) {
            super(output, "ru_ru", registriesFuture);
        }

        @Override
        public void generateTranslations(HolderLookup.@NonNull Provider registryLookup, TranslationBuilder builder) {
            FERussianLang.generateItems(builder::add);
            FERussianLang.generateItemTags(builder::add);
            FERussianLang.generateSounds(builder::add);
            FERussianLang.generateCustom(builder::add);
        }
    }

    public static class ChineseSimplifiedProvider extends FabricLanguageProvider {

        public ChineseSimplifiedProvider(FabricPackOutput output, CompletableFuture<HolderLookup.@NonNull Provider> registriesFuture) {
            super(output, "zh_cn", registriesFuture);
        }

        @Override
        public void generateTranslations(HolderLookup.@NonNull Provider registryLookup, TranslationBuilder builder) {
            FEChineseLang.generateItems(builder::add);
            FEChineseLang.generateItemTags(builder::add);
            FEChineseLang.generateSounds(builder::add);
            FEChineseLang.generateCustom(builder::add);
        }
    }

    public static class ChineseTraditionalProvider extends FabricLanguageProvider {

        public ChineseTraditionalProvider(FabricPackOutput output, CompletableFuture<HolderLookup.@NonNull Provider> registriesFuture) {
            super(output, "zh_tw", registriesFuture);
        }

        @Override
        public void generateTranslations(HolderLookup.@NonNull Provider registryLookup, @NonNull TranslationBuilder builder) {
            FEChineseLang.generateItems((item, translation) -> builder.add(item, convertToTraditional(translation)));
            FEChineseLang.generateItemTags((tag, translation) -> builder.add(tag, convertToTraditional(translation)));
            FEChineseLang.generateSounds((sound, translation) -> builder.add(sound, convertToTraditional(translation)));
            FEChineseLang.generateCustom((custom, translation) -> builder.add(custom, convertToTraditional(translation)));
        }

        public static String convertToTraditional(String translation) {
            if (ZhTwConverterUtil.isChinese(translation))
                return ZhTwConverterUtil.toTraditional(translation);
            return translation;
        }
    }

    public static class KoreanProvider extends FabricLanguageProvider {

        public KoreanProvider(FabricPackOutput output, CompletableFuture<HolderLookup.@NonNull Provider> registriesFuture) {
            super(output, "ko_kr", registriesFuture);
        }

        @Override
        public void generateTranslations(HolderLookup.@NonNull Provider registryLookup, TranslationBuilder builder) {
            FEKoreanLang.generateItems(builder::add);
            FEKoreanLang.generateItemTags(builder::add);
            FEKoreanLang.generateSounds(builder::add);
            FEKoreanLang.generateCustom(builder::add);
        }
    }

    public static class GermanProvider extends FabricLanguageProvider {

        public GermanProvider(FabricPackOutput output, CompletableFuture<HolderLookup.@NonNull Provider> registriesFuture) {
            super(output, "de_de", registriesFuture);
        }

        @Override
        public void generateTranslations(HolderLookup.@NonNull Provider registryLookup, TranslationBuilder builder) {
            FEGermanLang.generateItems(builder::add);
            FEGermanLang.generateItemTags(builder::add);
            FEGermanLang.generateSounds(builder::add);
            FEGermanLang.generateCustom(builder::add);
        }
    }

    public static class FrenchProvider extends FabricLanguageProvider {

        public FrenchProvider(FabricPackOutput output, CompletableFuture<HolderLookup.@NonNull Provider> registriesFuture) {
            super(output, "fr_fr", registriesFuture);
        }

        @Override
        public void generateTranslations(HolderLookup.@NonNull Provider registryLookup, TranslationBuilder builder) {
            FEFrenchLang.generateItems(builder::add);
            FEFrenchLang.generateItemTags(builder::add);
            FEFrenchLang.generateSounds(builder::add);
            FEFrenchLang.generateCustom(builder::add);
        }
    }

    public static class PortugueseBrazilProvider extends FabricLanguageProvider {

        public PortugueseBrazilProvider(FabricPackOutput output, CompletableFuture<HolderLookup.@NonNull Provider> registriesFuture) {
            super(output, "pt_br", registriesFuture);
        }

        @Override
        public void generateTranslations(HolderLookup.@NonNull Provider registryLookup, TranslationBuilder builder) {
            FEPortugueseLang.generateItems(builder::add);
            FEPortugueseLang.generateItemTags(builder::add);
            FEPortugueseLang.generateSounds(builder::add);
            FEPortugueseLang.generateCustom(builder::add);
        }
    }
}
