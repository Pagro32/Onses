package de.hsfulda.onses.controllers;

import de.hsfulda.onses.Main;
import de.hsfulda.onses.models.Card;
import de.hsfulda.onses.models.Player;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class PlayerController implements Controller {

    private final Player player;

    public PlayerController(Player player) {
        this.player = player;
    }
    @Override
    public Parent render() throws IOException {
        final Parent parent = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("views/player.fxml")));
        final Label playerNameLabel = (Label) parent.lookup("#playerNameLabel");
        final HBox cards = (HBox) parent.lookup("#cardsHBox");

        for(Card card : player.getPlayerDeck()) {
            cards.getChildren().add(new CardController(card).render());
        }

        player.listeners().addPropertyChangeListener(Player.PROPERTY_PLAYER_DECK, e -> {
            cards.getChildren().clear();
            for(Card card : player.getPlayerDeck()) {
                try {
                    cards.getChildren().add(new CardController(card).render());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        playerNameLabel.setText("Test");

        return parent;
    }
}
