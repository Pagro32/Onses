package de.hsfulda.onses.controllers;

import de.hsfulda.onses.Main;
import de.hsfulda.onses.models.Game;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.Objects;

public class AppController implements Controller {

    private final Game game;

    public AppController(Game game) {
         this.game = game;
    }
    @Override
    public Parent render() throws IOException {
        final Parent parent = FXMLLoader.load(Main.class.getResource("views/app.fxml"));
        Button button = (Button) parent.lookup("#startGameBtn");

        button.setOnAction(e -> {
            System.out.println("Pressed");
        });
        return parent;
    }
}
