package com.javarush.kostenko.caesar.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * The {@code MainApp} class is the entry point for the Caesar Cipher application.
 * It initializes the JavaFX application and loads the main user interface from an FXML file.
 */
public class MainApp extends Application {

    /**
     * Starts the JavaFX application and sets up the primary stage.
     * Loads the main UI layout from {@code main.fxml} and sets the application title.
     *
     * @param primaryStage the primary stage for this JavaFX application
     * @throws Exception if loading the FXML file fails
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/fxml/main.fxml")));
        primaryStage.setTitle("Caesar Cipher");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    /**
     * The main entry point for launching the Caesar Cipher application.
     * Calls {@code launch(args)} to initialize the JavaFX runtime.
     *
     * @param args the command-line arguments passed to the application
     */
    public static void main(String[] args) {
        launch(args);
    }
}
