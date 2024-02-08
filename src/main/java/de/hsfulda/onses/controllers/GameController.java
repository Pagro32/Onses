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
        final Pane enemyPane = (Pane) parent.lookup("#enemyPane");
        final Pane playerPane = (Pane) parent.lookup("#playerPane");
        final Button playButton = (Button) parent.lookup("#playCardBtn");

        CardController lastPlayedCardController = new CardController(game.getLastPlayedCard(), null);
        PlayerController playerController = new PlayerController(gameService.getGame().getPlayerService().getPlayerList().getFirst());
        PlayerController enemyController = new PlayerController(gameService.getGame().getPlayerService().getPlayerList().getLast());

        game.listeners().addPropertyChangeListener(Game.PROPERTY_LAST_PLAYED_CARD, e -> {
            lastPlayedCardPane.getChildren().removeAll();
            try {
                lastPlayedCardPane.getChildren().add(new CardController((Card) e.getNewValue(), null).render());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        playButton.setOnAction(e -> {
            Card cardToPlay = game.getPlayerService().getPlayerList().getFirst().getCurrentCard();
            if(gameService.legalMove(cardToPlay)) {
                if(cardToPlay.getPlayer() != null) {
                    cardToPlay.getPlayer().removeCardFromPlayerDeck(cardToPlay);
                    gameService.playCard(cardToPlay);
                }
            }
        });


        lastPlayedCardPane.getChildren().add(lastPlayedCardController.render());
        playerPane.getChildren().add(playerController.render());
        enemyPane.getChildren().add(enemyController.render());

        return parent;
    }
}
