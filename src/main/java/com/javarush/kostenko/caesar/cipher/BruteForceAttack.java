package com.javarush.kostenko.caesar.cipher;

import java.util.Set;

/**
 * The {@code BruteForceAttack} class is responsible for decrypting a Caesar cipher encrypted text
 * using a brute force approach. It attempts all possible shifts and selects the one that results
 * in the highest match rate with words from a specified dictionary.
 */
public class BruteForceAttack {
    private final CaesarCipher cipher;
    private final Set<String> dictionary;

    /**
     * Constructs a {@code BruteForceAttack} instance with a given {@code CaesarCipher} instance
     * and a dictionary of words.
     *
     * @param cipher     the {@code CaesarCipher} instance used for decryption
     * @param dictionary a set of known words used to validate decrypted text
     */
    public BruteForceAttack(CaesarCipher cipher, Set<String> dictionary) {
        this.cipher = cipher;
        this.dictionary = dictionary;
    }

    /**
     * Attempts to decrypt the given encrypted text by trying all possible shifts (from 0 to the length
     * of the cipher's alphabet). It returns the decrypted text that has the highest number of matches
     * with words in the dictionary.
     *
     * @param encryptedText the encrypted text to decrypt
     * @return the decrypted text that is most likely to be correct, based on dictionary word matches
     */
    public String crack(String encryptedText) {
        int bestShift = 0;
        int maxMatches = 0;
        String bestDecryption = "";

        int alphabetLength = cipher.getAlphabetLength();

        for (int shift = 0; shift < alphabetLength; shift++) {
            String decryptedText = cipher.decrypt(encryptedText, shift);
            int matches = countDictionaryMatches(decryptedText);

            if (matches > maxMatches) {
                maxMatches = matches;
                bestShift = shift;
                bestDecryption = decryptedText;
            }
        }

        System.out.println("Best shift found by brute force: " + bestShift);
        return bestDecryption;
    }

    /**
     * Counts the number of words in the given text that match words in the dictionary.
     * This method splits the text into individual words and checks each word against the dictionary.
     *
     * @param text the text to check for dictionary word matches
     * @return the number of words in the text that match words in the dictionary
     */
    private int countDictionaryMatches(String text) {
        int matches = 0;
        String[] words = text.split("\\s+");
        for (String word : words) {
            if (dictionary.contains(word.toLowerCase())) {
                matches++;
            }
        }
        return matches;
    }
}
