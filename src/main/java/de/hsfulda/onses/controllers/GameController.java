package de.hsfulda.onses.controllers;

import de.hsfulda.onses.Main;
import de.hsfulda.onses.models.Card;
import de.hsfulda.onses.models.Game;
import de.hsfulda.onses.services.GameService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

public class GameController implements Controller {
    private final GameService gameService;
    private final Game game;
    public GameController(GameService gameService) {
        this.gameService = gameService;
        this.game = gameService.getGame();
    }
    @Override
    public Parent render() throws IOException {
        final Parent parent = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("views/game.fxml")));
        final Pane lastPlayedCardPane = (Pane) parent.lookup("#lastPlayedCardPane");

        final Button playButton = (Button) parent.lookup("#playCardBtn");
        CardController lastPlayedCardController = new CardController(new Card().setValue(Card.Value.FIVE).setColor(Card.Color.BLUE));

        game.listeners().addPropertyChangeListener(Game.PROPERTY_LAST_PLAYED_CARD, e -> {
            lastPlayedCardPane.getChildren().removeAll();
            try {
                lastPlayedCardPane.getChildren().add(new CardController((Card) e.getNewValue()).render());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        playButton.setOnAction(e -> {
            gameService.playCard(new Card().setColor(Card.Color.GREEN).setValue(Card.Value.ONE));
        });


        lastPlayedCardPane.getChildren().add(lastPlayedCardController.render());

        return parent;
    }
}
