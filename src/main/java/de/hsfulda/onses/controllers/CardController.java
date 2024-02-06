package de.hsfulda.onses.controllers;

import de.hsfulda.onses.Main;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.Objects;

public class CardController implements Controller {
    @Override
    public Parent render() throws IOException {
        final Parent parent = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("views/card.fxml")));

        return parent;
    }
}
