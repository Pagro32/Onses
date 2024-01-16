package de.hsfulda.onses.controllers;

import de.hsfulda.onses.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class MainController extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        final Parent parent = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("views/main.fxml")));

        stage.setTitle("Onses");
        stage.setScene(new Scene(parent));
        stage.show();
    }
}
