# Caesar Cipher Application

### Description
This is a JavaFX application implementing text encryption and decryption using the Caesar cipher. The Caesar Cipher is 
a simple and well-known encryption method that shifts each character in the text by a specified number of positions. 
The application also supports brute force and frequency analysis modes to decrypt text without knowing the exact key.

### Features
- **Encryption and Decryption with Key**: Encrypt and decrypt text using a specified shift value.
- **Brute Force Attack**: Attempts to decrypt text by trying all possible shifts, selecting the one with the highest 
match rate based on a dictionary.
- **Frequency Analysis**: Uses statistical character frequency analysis to identify the most likely decryption key based
on Russian language frequency statistics.

### Requirements
- **Java 17**: The application requires JDK 17 or newer. Ensure that Java 17 is installed and configured as the primary 
JDK.
- **Maven**: Maven is used to manage dependencies and build the project.

### Dependencies
The project uses the following dependencies:
- **JavaFX 17.0.2**: For creating the graphical user interface.
- **Maven**: For project management and dependency handling.

### Installation and Launch
1. **Clone the repository**:
    ```bash
    git clone https://github.com/Lexoid2/cryptoanalyzer.git
    cd cryptoanalyzer
    ```

2. **Set up JavaFX for your Maven project**:
   Ensure that the `pom.xml` includes dependencies for JavaFX and has the `javafx-maven-plugin` configured for running 
JavaFX applications with Maven.

3. **Build and Run the Project**:
    - Execute the following command:
      ```bash
      mvn clean javafx:run
      ```
   This will launch the application using the JavaFX Maven Plugin.

4. **Run from an IDE (e.g., IntelliJ IDEA)**:
    - Ensure **Java 17** is installed and selected as the project SDK.
    - Make sure **JavaFX 17.0.2** dependencies are correctly added.
    - Set the main class to `com.javarush.kostenko.caesar.ui.MainApp` for running.

### Usage
1. **Load a File**: Click "Open File" to select a file you want to encrypt or decrypt. The file contents will display in
the `Original Text` field.
2. **Select Mode**: Use the `Mode` dropdown to select the desired mode:
    - **Encrypt/Decrypt with Key**: Enter a `Shift` value, then click `Encrypt` or `Decrypt` to perform the operation.
    - **Brute Force**: Click `Brute Force` to automatically find a decryption key.
    - **Statistical Analysis**: Click `Statistical Analysis` to use frequency analysis to decrypt the text.
3. **Save the Result**: Click "Save Result" to save the result in a file.

### Project Structure
- **src/main/java/com/javarush/kostenko/caesar/cipher**: Contains encryption and decryption logic.
- **src/main/java/com/javarush/kostenko/caesar/ui**: Manages the user interface and interactions.
- **src/main/java/com/javarush/kostenko/caesar/utils**: Provides utility classes such as file handling and character
frequency statistics.

### Notes
- Ensure JavaFX libraries are correctly configured.
- When running from the command line, `mvn javafx:run` will automatically use the JavaFX Maven Plugin configuration.

### License
This project is licensed under the MIT License.
