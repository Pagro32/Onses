package de.hsfulda.onses.controllers;

import de.hsfulda.onses.Main;
import de.hsfulda.onses.models.Card;
import de.hsfulda.onses.models.Game;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class AppController implements Controller {

    private final Game game;
    private final Stage stage;

    public AppController(Game game, Stage stage) {
         this.game = game;
         this.stage = stage;
    }
    @Override
    public Parent render() throws IOException {
        GameController gameController = new GameController(game);
        final Parent parent = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("views/app.fxml")));
        Button button = (Button) parent.lookup("#startGameBtn");


        return parent;
    }
}
