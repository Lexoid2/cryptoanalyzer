package com.javarush.kostenko.caesar.cipher;

import java.util.stream.Collectors;

/**
 * The {@code CaesarCipher} class provides methods for encrypting and decrypting text
 * using the Caesar cipher algorithm with a customizable alphabet. By default, the
 * Russian alphabet, some punctuation marks, and whitespace are included in the alphabet.
 */
public class CaesarCipher {
    /**
     * Default alphabet used in Caesar cipher operations. This includes uppercase and lowercase
     * Russian letters, space, and a set of punctuation symbols.
     */
    public static final String ALPHABET = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ" +
            "абвгдеёжзийклмнопрстуфхцчшщъыьэюя" +
            " .,\"':-!?";

    private final String alphabet;

    /**
     * Constructs a {@code CaesarCipher} instance with the default Russian alphabet
     * and specified punctuation.
     */
    public CaesarCipher() {
        this.alphabet = ALPHABET;
    }

    /**
     * Encrypts the given text by shifting each character in the text forward by the specified
     * shift value within the alphabet. Characters not in the alphabet are left unchanged.
     *
     * @param text  the plaintext to encrypt
     * @param shift the number of positions each character is shifted forward
     * @return the encrypted text
     */
    public String encrypt(String text, int shift) {
        return text.chars()
                .mapToObj(c -> shiftChar((char) c, shift, true))
                .collect(Collectors.joining());
    }

    /**
     * Decrypts the given text by shifting each character in the text backward by the specified
     * shift value within the alphabet. Characters not in the alphabet are left unchanged.
     *
     * @param text  the encrypted text to decrypt
     * @param shift the number of positions each character is shifted backward
     * @return the decrypted text
     */
    public String decrypt(String text, int shift) {
        return text.chars()
                .mapToObj(c -> shiftChar((char) c, shift, false))
                .collect(Collectors.joining());
    }

    /**
     * Returns the length of the alphabet used for encryption and decryption.
     *
     * @return the length of the alphabet
     */
    public int getAlphabetLength() {
        return alphabet.length();
    }

    /**
     * Shifts a character within the alphabet by a specified number of positions.
     * Characters not in the alphabet are returned unchanged.
     *
     * @param c       the character to shift
     * @param shift   the number of positions to shift
     * @param forward {@code true} for forward shift (encryption), {@code false} for backward shift (decryption)
     * @return the shifted character as a string, or the original character if not in the alphabet
     */
    private String shiftChar(char c, int shift, boolean forward) {
        int index = alphabet.indexOf(c);
        if (index == -1) {
            return String.valueOf(c);
        }

        int shiftedIndex;
        if (forward) {
            shiftedIndex = (index + shift) % alphabet.length();
        } else {
            shiftedIndex = (index - shift + alphabet.length()) % alphabet.length();
        }

        return String.valueOf(alphabet.charAt(shiftedIndex));
    }
}
