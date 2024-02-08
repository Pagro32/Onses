package de.hsfulda.onses.controllers;

import de.hsfulda.onses.Main;
import de.hsfulda.onses.models.Card;
import de.hsfulda.onses.models.Player;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class PlayerController implements Controller {

    private final Player player;
    private final ArrayList<Controller> controllers = new ArrayList<>();
    private PropertyChangeListener playerDeckChangeListener;

    public PlayerController(Player player) {
        this.player = player;
    }

    @Override
    public Parent render() throws IOException {
        final Parent parent = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("views/player.fxml")));
        final Label playerNameLabel = (Label) parent.lookup("#playerNameLabel");
        final HBox cards = (HBox) parent.lookup("#cardsHBox");

        for(Card card : player.getPlayerDeck()) {
            CardController newCardController = new CardController(card, player);
            controllers.add(newCardController);
            cards.getChildren().add(newCardController.render());
        }

        playerDeckChangeListener = e -> {
            cards.getChildren().clear();
            for(Card card : player.getPlayerDeck()) {
                try {
                    cards.getChildren().add(new CardController(card, player).render());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        };
        player.listeners().addPropertyChangeListener(Player.PROPERTY_PLAYER_DECK, playerDeckChangeListener);

        playerNameLabel.setText(player.getPlayerName());

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
        player.listeners().removePropertyChangeListener(Player.PROPERTY_PLAYER_DECK, playerDeckChangeListener);
    }
}
