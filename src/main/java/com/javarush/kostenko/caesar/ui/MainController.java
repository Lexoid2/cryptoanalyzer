package com.javarush.kostenko.caesar.ui;

import com.javarush.kostenko.caesar.cipher.CaesarCipher;
import com.javarush.kostenko.caesar.cipher.BruteForceAttack;
import com.javarush.kostenko.caesar.cipher.FrequencyAnalysis;
import com.javarush.kostenko.caesar.utils.FileUtils;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Set;

/**
 * The {@code MainController} class manages the user interface and interactions for the Caesar Cipher application.
 * It provides methods for handling file input/output, switching between encryption modes, and applying encryption,
 * decryption, brute force, and statistical analysis.
 */
public class MainController {
    private final CaesarCipher cipher = new CaesarCipher();
    private File selectedFile;
    private String originalContent;

    @FXML
    private ComboBox<String> modeComboBox;

    @FXML
    private HBox shiftContainer, bruteForceButton, statisticalAnalysisButton;

    @FXML
    private TextField shiftField;

    @FXML
    private TextArea originalArea;

    @FXML
    private TextArea resultArea;

    /**
     * Initializes the controller by setting the default mode to "Encrypt/Decrypt with Key" and configuring the UI.
     */
    @FXML
    public void initialize() {
        modeComboBox.getSelectionModel().select("Encrypt/Decrypt with Key");
        configureUI("Encrypt/Decrypt with Key");
    }

    /**
     * Handles the selection of a different mode in the mode combo box, configuring the UI accordingly.
     */
    @FXML
    private void onModeSelected() {
        String selectedMode = modeComboBox.getValue();
        configureUI(selectedMode);
    }

    /**
     * Configures the user interface based on the selected mode, showing
     * or hiding specific UI elements to match the chosen functionality.
     *
     * @param mode the selected mode, which could be one of:
     *             "Encrypt/Decrypt with Key", "Brute Force",
     *             or "Statistical Analysis".
     */
    private void configureUI(String mode) {
        shiftContainer.setVisible(false);
        shiftContainer.setManaged(false);
        bruteForceButton.setVisible(false);
        bruteForceButton.setManaged(false);
        statisticalAnalysisButton.setVisible(false);
        statisticalAnalysisButton.setManaged(false);

        switch (mode) {
            case "Encrypt/Decrypt with Key":
                shiftContainer.setVisible(true);
                shiftContainer.setManaged(true);
                break;
            case "Brute Force":
                bruteForceButton.setVisible(true);
                bruteForceButton.setManaged(true);
                break;
            case "Statistical Analysis":
                statisticalAnalysisButton.setVisible(true);
                statisticalAnalysisButton.setManaged(true);
                break;
        }
    }

    /**
     * Opens a file chooser dialog to allow the user to select a file for encryption or decryption.
     * The contents of the file are displayed in the original text area.
     */
    @FXML
    public void handleOpenFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        selectedFile = fileChooser.showOpenDialog(new Stage());
        if (selectedFile != null) {
            try {
                originalContent = FileUtils.readFile(selectedFile.getAbsolutePath());
                originalArea.setText(originalContent);
                resultArea.clear();
            } catch (IOException e) {
                resultArea.setText("Error loading file: " + e.getMessage());
            }
        }
    }

    /**
     * Encrypts the text in the original content using the specified shift value and displays the result.
     */
    @FXML
    public void handleEncrypt() {
        if (validateInput()) {
            int shift = normalizeShift(Integer.parseInt(shiftField.getText()));
            String encryptedText = cipher.encrypt(originalContent, shift);
            resultArea.setText("Encrypted text:\n" + encryptedText);
        }
    }

    /**
     * Decrypts the text in the original content using the specified shift value and displays the result.
     */
    @FXML
    public void handleDecrypt() {
        if (validateInput()) {
            int shift = normalizeShift(Integer.parseInt(shiftField.getText()));
            String decryptedText = cipher.decrypt(originalContent, shift);
            resultArea.setText("Decrypted text:\n" + decryptedText);
        }
    }

    /**
     * Attempts to decrypt the text in the original content using brute force, checking each possible shift,
     * and selecting the decryption with the most dictionary word matches.
     */
    @FXML
    public void handleBruteForce() {
        if (originalContent == null || originalContent.isEmpty()) {
            resultArea.setText("No text to perform brute force.");
            return;
        }
        try {
            Set<String> dictionary = FileUtils.readDictionary();
            BruteForceAttack bruteForceAttack = new BruteForceAttack(cipher, dictionary);
            String crackedText = bruteForceAttack.crack(originalContent);
            resultArea.setText("Brute Force cracked text:\n" + crackedText);
        } catch (IOException e) {
            resultArea.setText("Error loading dictionary: " + e.getMessage());
        }
    }

    /**
     * Attempts to decrypt the text in the original content using statistical frequency analysis to
     * find the shift with the least deviation from expected character frequencies.
     */
    @FXML
    public void handleStatisticalAnalysis() {
        if (originalContent == null || originalContent.isEmpty()) {
            resultArea.setText("No text to perform statistical analysis.");
            return;
        }

        FrequencyAnalysis frequencyAnalysis = new FrequencyAnalysis(cipher);
        String crackedText = frequencyAnalysis.crackWithStatistics(originalContent);
        resultArea.setText("Statistical Analysis cracked text:\n" + crackedText);
    }

    /**
     * Opens a file chooser dialog to save the contents of the result text area to a file.
     */
    @FXML
    public void handleSaveFile() {
        if (resultArea.getText().isEmpty()) {
            resultArea.setText("No text to save.");
            return;
        }
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        File file = fileChooser.showSaveDialog(new Stage());
        if (file != null) {
            try {
                FileUtils.writeFile(file.getAbsolutePath(), resultArea.getText());
                resultArea.setText("Result successfully saved to file: " + file.getAbsolutePath());
            } catch (IOException e) {
                resultArea.setText("Error saving file: " + e.getMessage());
            }
        }
    }

    /**
     * Normalizes the shift value so that it wraps around the alphabet length.
     *
     * @param shift the original shift value
     * @return the normalized shift value, constrained within the alphabet length
     */
    private int normalizeShift(int shift) {
        int alphabetLength = cipher.getAlphabetLength();
        return (shift % alphabetLength + alphabetLength) % alphabetLength;
    }

    /**
     * Validates the user input, ensuring a file is selected and a valid shift value is entered.
     *
     * @return {@code true} if the input is valid; {@code false} otherwise
     */
    private boolean validateInput() {
        if (selectedFile == null) {
            resultArea.setText("Please select a file to process.");
            return false;
        }
        if (shiftField.getText().isEmpty()) {
            resultArea.setText("Please enter a shift value.");
            return false;
        }
        try {
            Integer.parseInt(shiftField.getText());
        } catch (NumberFormatException e) {
            resultArea.setText("Shift value must be a number.");
            return false;
        }
        return true;
    }
}
