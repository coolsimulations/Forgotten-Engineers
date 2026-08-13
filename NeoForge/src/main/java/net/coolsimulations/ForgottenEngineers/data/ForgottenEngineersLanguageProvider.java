package net.coolsimulations.ForgottenEngineers.data;

import com.github.houbb.opencc4j.util.ZhTwConverterUtil;
import net.coolsimulations.ForgottenEngineers.ForgottenEngineersCommon;
import net.coolsimulations.ForgottenEngineers.data.lang.*;
import net.minecraft.data.PackOutput;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.common.data.LanguageProvider;

public class ForgottenEngineersLanguageProvider {

    public static class ForgottenEngineersLangProvider extends LanguageProvider {

        public ForgottenEngineersLangProvider(PackOutput output, String locale) {
            super(output, ForgottenEngineersCommon.MOD_ID, locale);
        }

        @Override
        protected void addTranslations() {}

        protected void add(SoundEvent sound, String value) {
            this.add("subtitles." + sound.location().getNamespace() + "." + sound.location().getPath(), value);
        }
    }

    public static class EnglishProvider extends ForgottenEngineersLangProvider {

        public EnglishProvider(PackOutput output) {
            super(output, "en_us");
        }

        @Override
        protected void addTranslations() {
            FEEnglishLang.generateItems(this::add);
            FEEnglishLang.generateItemTags(this::add);
            FEEnglishLang.generateSounds(this::add);
            FEEnglishLang.generateCustom(this::add);
        }
    }

    public static class UpsideDownProvider extends ForgottenEngineersLangProvider {

        public UpsideDownProvider(PackOutput output) {
            super(output, "en_ud");
            FEUpsideDownLang.init();
        }

        @Override
        protected void addTranslations() {
            FEUpsideDownLang.generateItems(this::add);
            FEUpsideDownLang.generateItemTags(this::add);
            FEUpsideDownLang.generateSounds(this::add);
            FEUpsideDownLang.generateCustom(this::add);
        }
    }

    public static class ShakespeareanProvider extends ForgottenEngineersLangProvider {

        public ShakespeareanProvider(PackOutput output) {
            super(output, "enws");
        }

        @Override
        protected void addTranslations() {
            FEShakespeareanLang.generateItems(this::add);
            FEShakespeareanLang.generateItemTags(this::add);
            FEShakespeareanLang.generateSounds(this::add);
            FEShakespeareanLang.generateCustom(this::add);
        }
    }

    public static class PirateProvider extends ForgottenEngineersLangProvider {

        public PirateProvider(PackOutput output) {
            super(output, "en_pt");
        }

        @Override
        protected void addTranslations() {
            FEPirateLang.generateItems(this::add);
            FEPirateLang.generateItemTags(this::add);
            FEPirateLang.generateSounds(this::add);
            FEPirateLang.generateCustom(this::add);
        }
    }

    public static class LOLCatProvider extends ForgottenEngineersLangProvider {

        public LOLCatProvider(PackOutput output) {
            super(output, "lol_us");
        }

        @Override
        protected void addTranslations() {
            FELOLCatLang.generateItems(this::add);
            FELOLCatLang.generateItemTags(this::add);
            FELOLCatLang.generateSounds(this::add);
            FELOLCatLang.generateCustom(this::add);
        }
    }

    public static class SpanishProvider extends ForgottenEngineersLangProvider {

        public SpanishProvider(PackOutput output, String locale) {
            super(output, locale);
        }

        @Override
        protected void addTranslations() {
            FESpanishLang.generateItems(this::add);
            FESpanishLang.generateItemTags(this::add);
            FESpanishLang.generateSounds(this::add);
            FESpanishLang.generateCustom(this::add);
        }
    }

    public static class JapaneseProvider extends ForgottenEngineersLangProvider {

        public JapaneseProvider(PackOutput output) {
            super(output, "ja_jp");
        }

        @Override
        protected void addTranslations() {
            FEJapaneseLang.generateItems(this::add);
            FEJapaneseLang.generateItemTags(this::add);
            FEJapaneseLang.generateSounds(this::add);
            FEJapaneseLang.generateCustom(this::add);
        }
    }

    public static class RussianProvider extends ForgottenEngineersLangProvider {

        public RussianProvider(PackOutput output) {
            super(output, "ru_ru");
        }

        @Override
        protected void addTranslations() {
            FERussianLang.generateItems(this::add);
            FERussianLang.generateItemTags(this::add);
            FERussianLang.generateSounds(this::add);
            FERussianLang.generateCustom(this::add);
        }
    }

    public static class ChineseSimplifiedProvider extends ForgottenEngineersLangProvider {

        public ChineseSimplifiedProvider(PackOutput output) {
            super(output, "zh_cn");
        }

        @Override
        protected void addTranslations() {
            FEChineseLang.generateItems(this::add);
            FEChineseLang.generateItemTags(this::add);
            FEChineseLang.generateSounds(this::add);
            FEChineseLang.generateCustom(this::add);
        }
    }

    public static class ChineseTraditionalProvider extends ForgottenEngineersLangProvider {

        public ChineseTraditionalProvider(PackOutput output) {
            super(output, "zh_tw");
        }

        @Override
        protected void addTranslations() {
            FEChineseLang.generateItems((item, translation) -> this.add(item, convertToTraditional(translation)));
            FEChineseLang.generateItemTags((tag, translation) -> this.add(tag, convertToTraditional(translation)));
            FEChineseLang.generateSounds((sound, translation) -> this.add(sound, convertToTraditional(translation)));
            FEChineseLang.generateCustom((custom, translation) -> this.add(custom, convertToTraditional(translation)));
        }

        public static String convertToTraditional(String translation) {
            if (ZhTwConverterUtil.isChinese(translation))
                return ZhTwConverterUtil.toTraditional(translation);
            return translation;
        }
    }

    public static class KoreanProvider extends ForgottenEngineersLangProvider {

        public KoreanProvider(PackOutput output) {
            super(output, "ko_kr");
        }

        @Override
        protected void addTranslations() {
            FEKoreanLang.generateItems(this::add);
            FEKoreanLang.generateItemTags(this::add);
            FEKoreanLang.generateSounds(this::add);
            FEKoreanLang.generateCustom(this::add);
        }
    }

    public static class GermanProvider extends ForgottenEngineersLangProvider {

        public GermanProvider(PackOutput output) {
            super(output, "de_de");
        }

        @Override
        protected void addTranslations() {
            FEGermanLang.generateItems(this::add);
            FEGermanLang.generateItemTags(this::add);
            FEGermanLang.generateSounds(this::add);
            FEGermanLang.generateCustom(this::add);
        }
    }

    public static class FrenchProvider extends ForgottenEngineersLangProvider {

        public FrenchProvider(PackOutput output) {
            super(output, "fr_fr");
        }

        @Override
        protected void addTranslations() {
            FEFrenchLang.generateItems(this::add);
            FEFrenchLang.generateItemTags(this::add);
            FEFrenchLang.generateSounds(this::add);
            FEFrenchLang.generateCustom(this::add);
        }
    }

    public static class PortugueseBrazilProvider extends ForgottenEngineersLangProvider {

        public PortugueseBrazilProvider(PackOutput output) {
            super(output, "pt_br");
        }

        @Override
        protected void addTranslations() {
            FEPortugueseLang.generateItems(this::add);
            FEPortugueseLang.generateItemTags(this::add);
            FEPortugueseLang.generateSounds(this::add);
            FEPortugueseLang.generateCustom(this::add);
        }
    }
}
