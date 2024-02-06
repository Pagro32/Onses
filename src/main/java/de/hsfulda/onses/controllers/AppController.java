package de.hsfulda.onses.controllers;

import de.hsfulda.onses.Main;
import de.hsfulda.onses.models.Game;
import de.hsfulda.onses.services.GameService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AppController implements Controller {

    private final GameService gameService;
    private final Stage stage;

    public AppController(GameService gameService, Stage stage) {
         this.gameService = gameService;
         this.stage = stage;
    }
    @Override
    public Parent render() throws IOException {
        GameController gameController = new GameController(gameService);
        final Parent parent = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("views/app.fxml")));
        Button button = (Button) parent.lookup("#startGameBtn");

        button.setOnAction(e -> {
            try {
                stage.setScene(new Scene(gameController.render()));
                stage.setTitle("Onses - Uno Game");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });
        return parent;
    }
}
