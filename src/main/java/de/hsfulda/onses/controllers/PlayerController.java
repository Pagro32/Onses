package de.hsfulda.onses.controllers;

import de.hsfulda.onses.Main;
import de.hsfulda.onses.models.Player;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;

import java.io.IOException;
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

        playerNameLabel.setText("Test");

        return parent;
    }
}
