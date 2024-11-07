package com.javarush.kostenko.caesar.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * The {@code RussianFrequency} class provides a utility method for obtaining
 * the standard character frequency distribution of the Russian language.
 * This distribution is useful for statistical analysis and frequency-based
 * decryption techniques.
 */
public class RussianFrequency {

    /**
     * Returns a map containing the standard frequency of each character
     * in the Russian language, including letters and whitespace. Frequencies
     * are represented as decimal values, indicating the relative frequency
     * of each character in typical Russian text.
     *
     * @return a {@code Map<Character, Double>} containing Russian character
     * frequencies, with each character mapped to its corresponding frequency
     * as a percentage of total text.
     */
    public static Map<Character, Double> getStandardFrequencies() {
        Map<Character, Double> frequencies = new HashMap<>();
        frequencies.put('о', 0.1097);
        frequencies.put('е', 0.0845);
        frequencies.put('а', 0.0801);
        frequencies.put('и', 0.0735);
        frequencies.put('н', 0.0670);
        frequencies.put('т', 0.0626);
        frequencies.put('с', 0.0547);
        frequencies.put('р', 0.0473);
        frequencies.put('в', 0.0454);
        frequencies.put('л', 0.0440);
        frequencies.put('к', 0.0349);
        frequencies.put('м', 0.0321);
        frequencies.put('д', 0.0298);
        frequencies.put('п', 0.0281);
        frequencies.put('у', 0.0262);
        frequencies.put('я', 0.0201);
        frequencies.put('ы', 0.0190);
        frequencies.put('ь', 0.0174);
        frequencies.put('г', 0.0169);
        frequencies.put('з', 0.0165);
        frequencies.put('б', 0.0159);
        frequencies.put('ч', 0.0144);
        frequencies.put('й', 0.0121);
        frequencies.put('х', 0.0097);
        frequencies.put('ж', 0.0094);
        frequencies.put('ш', 0.0073);
        frequencies.put('ю', 0.0064);
        frequencies.put('ц', 0.0048);
        frequencies.put('щ', 0.0036);
        frequencies.put('э', 0.0032);
        frequencies.put('ф', 0.0026);
        frequencies.put('ъ', 0.0004);
        frequencies.put('ё', 0.0004);
        frequencies.put(' ', 0.1750);
        return frequencies;
    }
}
