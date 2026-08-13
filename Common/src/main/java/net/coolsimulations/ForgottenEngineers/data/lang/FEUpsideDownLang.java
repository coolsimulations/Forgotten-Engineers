package net.coolsimulations.ForgottenEngineers.data.lang;

import it.unimi.dsi.fastutil.chars.Char2CharMap;
import it.unimi.dsi.fastutil.chars.Char2CharOpenHashMap;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import org.apache.commons.lang3.StringUtils;

import java.util.function.BiConsumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FEUpsideDownLang {

    public static Char2CharMap MAPPINGS = new Char2CharOpenHashMap();

    public static void init() {
        MAPPINGS.put('a', 'ɐ');
        MAPPINGS.put('b', 'q');
        MAPPINGS.put('c', 'ɔ');
        MAPPINGS.put('d', 'p');
        MAPPINGS.put('e', 'ǝ');
        MAPPINGS.put('f', 'ɟ');
        MAPPINGS.put('g', 'ᵷ');
        MAPPINGS.put('h', 'ɥ');
        MAPPINGS.put('i', 'ᴉ');
        MAPPINGS.put('j', 'ɾ');
        MAPPINGS.put('k', 'ʞ');
        MAPPINGS.put('l', 'ꞁ');
        MAPPINGS.put('m', 'ɯ');
        MAPPINGS.put('n', 'u');
        MAPPINGS.put('p', 'd');
        MAPPINGS.put('q', 'b');
        MAPPINGS.put('r', 'ɹ');
        MAPPINGS.put('t', 'ʇ');
        MAPPINGS.put('u', 'n');
        MAPPINGS.put('v', 'ʌ');
        MAPPINGS.put('w', 'ʍ');
        MAPPINGS.put('x', 'x');
        MAPPINGS.put('y', 'ʎ');
        MAPPINGS.put('A', 'Ɐ');
        MAPPINGS.put('B', 'ᗺ');
        MAPPINGS.put('C', 'Ɔ');
        MAPPINGS.put('D', 'ᗡ');
        MAPPINGS.put('E', 'Ǝ');
        MAPPINGS.put('F', 'Ⅎ');
        MAPPINGS.put('G', '⅁');
        MAPPINGS.put('J', 'Ր');
        MAPPINGS.put('K', 'Ʞ');
        MAPPINGS.put('L', 'Ꞁ');
        MAPPINGS.put('M', 'W');
        MAPPINGS.put('P', 'Ԁ');
        MAPPINGS.put('Q', 'Ꝺ');
        MAPPINGS.put('R', 'ᴚ');
        MAPPINGS.put('T', '⟘');
        MAPPINGS.put('U', '∩');
        MAPPINGS.put('V', 'Ʌ');
        MAPPINGS.put('W', 'M');
        MAPPINGS.put('Y', '⅄');
        MAPPINGS.put('?', '¿');
        MAPPINGS.put('!', '¡');
        MAPPINGS.put('\'', ',');
        MAPPINGS.put(',', '\'');
        MAPPINGS.put('.', '˙');
        MAPPINGS.put('&', '⅋');
        MAPPINGS.put('>', '<');
        MAPPINGS.put('<', '>');
        MAPPINGS.put('[', ']');
        MAPPINGS.put(']', '[');
        MAPPINGS.put('(', ')');
        MAPPINGS.put(')', '(');
        MAPPINGS.put('↑', '↓');
        MAPPINGS.put('↓', '↑');
        MAPPINGS.put('←', '→');
        MAPPINGS.put('→', '←');
        MAPPINGS.put('1', '⥝');
        MAPPINGS.put('2', 'ᘔ');
        MAPPINGS.put('3', 'Ɛ');
        MAPPINGS.put('4', '߈');
        MAPPINGS.put('5', 'ϛ');
        MAPPINGS.put('6', '9');
        MAPPINGS.put('7', 'ㄥ');
        MAPPINGS.put('9', '6');
    }

    public static void generateItems(BiConsumer<Item, String> items) {
        FEEnglishLang.generateItems((item, translation) -> items.accept(item, convertToUpsideDown(translation)));
    }

    public static void generateItemTags(BiConsumer<TagKey<Item>, String> tags) {
        FEEnglishLang.generateItemTags((tag, translation) -> tags.accept(tag, convertToUpsideDown(translation)));
    }

    public static void generateSounds(BiConsumer<SoundEvent, String> sounds) {
        FEEnglishLang.generateSounds((sound, translation) -> sounds.accept(sound, convertToUpsideDown(translation)));
    }

    public static void generateCustom(BiConsumer<String, String> custom) {
        FEEnglishLang.generateCustom((key, translation) -> custom.accept(key, convertToUpsideDown(translation)));
    }

    public static String convertToUpsideDown(String translation) {
        StringBuilder upsideDownBuilder = new StringBuilder();
        for (int i = translation.length() - 1; i >= 0; i--) {
            char chr = translation.charAt(i);
            char upsideDownChar = getUpsideDownForChar(chr);
            upsideDownBuilder.append(upsideDownChar);
        }
        String finalTranslation = upsideDownBuilder.toString();
        if (finalTranslation.contains("s%")) {
            if (StringUtils.countMatches(finalTranslation, "s%") > 1) {
                Pattern pattern = Pattern.compile("s%");
                Matcher match = pattern.matcher(finalTranslation);
                StringBuilder builder = new StringBuilder();
                int counter = StringUtils.countMatches(finalTranslation, "s%");
                while (match.find()) {
                    match.appendReplacement(builder, Matcher.quoteReplacement("%" + counter + "$s"));
                    counter--;
                }
                match.appendTail(builder);
                return builder.toString();
            }
            else {
                return finalTranslation.replace("s%", "%s");
            }
        }
        return finalTranslation;
    }

    public static Character getUpsideDownForChar(Character chr) {
        return MAPPINGS.getOrDefault(chr, chr);
    }
}
