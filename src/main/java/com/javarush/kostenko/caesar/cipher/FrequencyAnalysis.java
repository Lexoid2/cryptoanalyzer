package com.javarush.kostenko.caesar.cipher;

import com.javarush.kostenko.caesar.utils.RussianFrequency;

import java.util.HashMap;
import java.util.Map;

/**
 * The {@code FrequencyAnalysis} class provides methods for decrypting a Caesar cipher encrypted text
 * using statistical frequency analysis. It compares character frequencies in the encrypted text
 * to standard frequencies in Russian text, determining the most likely decryption key.
 */
public class FrequencyAnalysis {
    private final CaesarCipher cipher;

    /**
     * Constructs a {@code FrequencyAnalysis} instance with a specified {@code CaesarCipher}.
     *
     * @param cipher the {@code CaesarCipher} instance used for decryption operations
     */
    public FrequencyAnalysis(CaesarCipher cipher) {
        this.cipher = cipher;
    }

    /**
     * Performs frequency analysis on the encrypted text and finds the decryption key
     * that minimizes the deviation between the observed character frequencies in the text
     * and the standard character frequencies for Russian text.
     *
     * @param encryptedText the encrypted text to analyze
     * @return the decrypted text with the key that best matches the frequency statistics
     */
    public String crackWithStatistics(String encryptedText) {
        int bestShift = 0;
        double minDeviation = Double.MAX_VALUE;
        String bestDecryption = "";

        Map<Character, Double> standardFrequencies = RussianFrequency.getStandardFrequencies();
        int alphabetLength = cipher.getAlphabetLength();

        for (int shift = 0; shift < alphabetLength; shift++) {
            String decryptedText = cipher.decrypt(encryptedText, shift);
            Map<Character, Double> observedFrequencies = calculateFrequencies(decryptedText);
            double deviation = calculateDeviation(standardFrequencies, observedFrequencies);

            if (deviation < minDeviation) {
                minDeviation = deviation;
                bestShift = shift;
                bestDecryption = decryptedText;
            }
        }

        System.out.println("Best shift found with statistics: " + bestShift);
        return bestDecryption;
    }

    /**
     * Calculates the frequency of each character in the given text.
     *
     * @param text the text for which character frequencies will be calculated
     * @return a map containing the frequency of each character in the text
     */
    private Map<Character, Double> calculateFrequencies(String text) {
        Map<Character, Integer> charCounts = new HashMap<>();
        int totalChars = 0;

        for (char c : text.toLowerCase().toCharArray()) {
            if (Character.isLetter(c) || c == ' ') {
                charCounts.put(c, charCounts.getOrDefault(c, 0) + 1);
                totalChars++;
            }
        }

        Map<Character, Double> frequencies = new HashMap<>();
        for (Map.Entry<Character, Integer> entry : charCounts.entrySet()) {
            frequencies.put(entry.getKey(), entry.getValue() / (double) totalChars);
        }
        return frequencies;
    }

    /**
     * Calculates the deviation between standard character frequencies and observed
     * character frequencies in a text. The deviation is calculated using the sum of
     * squared differences between corresponding frequencies.
     *
     * @param standard the standard character frequencies for comparison
     * @param observed the observed character frequencies in the text
     * @return the deviation between the standard and observed character frequencies
     */
    private double calculateDeviation(Map<Character, Double> standard, Map<Character, Double> observed) {
        double deviation = 0.0;

        for (Map.Entry<Character, Double> entry : standard.entrySet()) {
            char character = entry.getKey();
            double standardFreq = entry.getValue();
            double observedFreq = observed.getOrDefault(character, 0.0);
            deviation += Math.pow(standardFreq - observedFreq, 2);
        }
        return deviation;
    }
}
