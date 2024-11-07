package com.javarush.kostenko.caesar.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.HashSet;
import java.util.Set;

/**
 * The {@code FileUtils} class provides utility methods for reading from
 * and writing to files, including reading a dictionary from resources
 * and handling file input/output operations.
 */
public class FileUtils {

    /**
     * Reads a dictionary file from the resources directory and returns it
     * as a {@code Set<String>} of words.
     *
     * @return a {@code Set<String>} containing words from the dictionary
     * @throws IOException if the dictionary file is not found or cannot
     *                     be read
     */
    public static Set<String> readDictionary() throws IOException {
        Set<String> dictionary = new HashSet<>();

        try (InputStream inputStream = FileUtils.class.getResourceAsStream("/dictionary.txt")) {
            if (inputStream == null) {
                throw new IOException("Resource '/dictionary.txt' not found");
            }

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    dictionary.add(line.trim().toLowerCase());
                }
            }
        }

        return dictionary;
    }

    /**
     * Reads the contents of a text file specified by {@code filePath}
     * and returns it as a single {@code String}.
     *
     * @param filePath the path to the file to read
     * @return the content of the file as a {@code String}
     * @throws IOException if the file cannot be found or read
     */
    public static String readFile(String filePath) throws IOException {
        return Files.readString(Path.of(filePath));
    }

    /**
     * Writes the specified text content to a file at {@code filePath}.
     *
     * @param filePath the path to the file where content will be written
     * @param content  the text content to write to the file
     * @throws IOException if an I/O error occurs during writing
     */
    public static void writeFile(String filePath, String content) throws IOException {
        Files.writeString(Path.of(filePath), content, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }
}
