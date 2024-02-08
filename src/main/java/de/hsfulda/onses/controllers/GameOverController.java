package de.hsfulda.onses.controllers;

import de.hsfulda.onses.App;
import de.hsfulda.onses.Main;
import de.hsfulda.onses.services.GameService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.Objects;

public class GameOverController implements Controller {

    private final App app;

    public GameOverController(App app) {
        this.app = app;
    }
    @Override
    public Parent render() throws IOException {
        final Parent parent = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("views/gameOver.fxml")));
        final Button newGameButton = (Button) parent.lookup("#newGameBtn");

        newGameButton.setOnAction(e -> {
            app.show(new GameController(app, new GameService()));
        });
        return parent;
    }

    @Override
    public void destroy() {

    }

    @Override
    public String getTitle() {
        return null;
    }
}
