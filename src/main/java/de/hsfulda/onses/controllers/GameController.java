package de.hsfulda.onses.controllers;

import de.hsfulda.onses.App;
import de.hsfulda.onses.Main;
import de.hsfulda.onses.models.Card;
import de.hsfulda.onses.models.Game;
import de.hsfulda.onses.services.GameService;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class GameController implements Controller {
    private final GameService gameService;
    private final Game game;
    private final App app;

    private PropertyChangeListener lastPlayedCardPropertyChangeListener;
    private PropertyChangeListener gameOverListener;

    private final ArrayList<Controller> controllers = new ArrayList<>();
    public GameController(App app, GameService gameService) {
        this.app = app;
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
        final Button drawCardButton = (Button) parent.lookup("#drawCardBtn");
        final Button exitGameButton = (Button) parent.lookup("#exitBtn");

        CardController lastPlayedCardController = new CardController(game.getLastPlayedCard(), null);
        PlayerController playerController = new PlayerController(gameService.getGame().getPlayerService().getPlayerList().getFirst());
        PlayerController enemyController = new PlayerController(gameService.getGame().getPlayerService().getPlayerList().getLast());

        controllers.add(lastPlayedCardController);
        controllers.add(playerController);
        controllers.add(enemyController);

        lastPlayedCardPropertyChangeListener = e -> {
            lastPlayedCardPane.getChildren().removeAll();
            try {
                CardController tmp = new CardController((Card) e.getNewValue(), null);
                controllers.add(tmp);
                lastPlayedCardPane.getChildren().add(tmp.render());
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        };
        game.listeners().addPropertyChangeListener(Game.PROPERTY_LAST_PLAYED_CARD, lastPlayedCardPropertyChangeListener);

        gameOverListener = e -> {
            app.show(new GameOverController(app));
        };
        game.listeners().addPropertyChangeListener(Game.PROPERTY_GAME_OVER, gameOverListener);

        exitGameButton.setOnAction(e -> {
            app.show(new AppController(app, new GameService()));
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

        drawCardButton.setOnAction(e -> {
            gameService.drawCard(1);
        });


        lastPlayedCardPane.getChildren().add(lastPlayedCardController.render());
        playerPane.getChildren().add(playerController.render());
        enemyPane.getChildren().add(enemyController.render());

        return parent;
    }

    @Override
    public String getTitle() {
        return "Onses - Uno Game";
    }

    @Override
    public void destroy() {
        for (Controller controller : controllers) {
            controller.destroy();
        }
        game.listeners().removePropertyChangeListener(Game.PROPERTY_LAST_PLAYED_CARD, lastPlayedCardPropertyChangeListener);
        game.listeners().removePropertyChangeListener(Game.PROPERTY_GAME_OVER, gameOverListener);
    }
}
