package de.hsfulda.onses.controllers;

import de.hsfulda.onses.App;
import de.hsfulda.onses.Main;
import de.hsfulda.onses.services.GameService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;

import java.io.IOException;
import java.util.Objects;

public class AppController implements Controller {

    private final GameService gameService;
    private final App app;

    public AppController(App app, GameService gameService) {
        this.app = app;
        this.gameService = gameService;
    }

    @Override
    public Parent render() throws IOException {
        GameController gameController = new GameController(app, gameService);
        final Parent parent = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("views/app.fxml")));
        Button button = (Button) parent.lookup("#startGameBtn");

        button.setOnAction(e -> {
            app.show(gameController);
        });
        return parent;
    }

    @Override
    public String getTitle() {
        return "Onses - Uno";
    }

    @Override
    public void destroy() {

    }
}
