package de.hsfulda.onses.controllers;

import de.hsfulda.onses.Main;
import de.hsfulda.onses.models.Card;
import de.hsfulda.onses.services.GameService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

public class GameController implements Controller {
    private final GameService gameService;
    public GameController(GameService gameService) {
        this.gameService = gameService;
    }
    @Override
    public Parent render() throws IOException {
        final Parent parent = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("views/game.fxml")));
        CardController lastPlayedCardController = new CardController(new Card().setValue(Card.Value.FIVE).setColor(Card.Color.BLUE));

        Pane lastPlayedCardPane = (Pane) parent.lookup("#lastPlayedCardPane");
        lastPlayedCardPane.getChildren().add(lastPlayedCardController.render());

        return parent;
    }
}
