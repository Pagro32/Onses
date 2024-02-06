package de.hsfulda.onses.controllers;

import de.hsfulda.onses.Main;
import de.hsfulda.onses.models.Card;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.Objects;

public class CardController implements Controller {

    private Card card;

    public CardController(Card card) {
        this.card = card;
    }
    @Override
    public Parent render() throws IOException {
        final Parent parent = FXMLLoader.load(Objects.requireNonNull(Main.class.getResource("views/card.fxml")));
        Pane mainPane = (Pane) parent.lookup("#cardPane");

        mainPane.setStyle(addStyle(mainPane.getStyle(), "-fx-background-color: red"));

        final Label cardName = (Label) parent.lookup("#labelName");
        cardName.setText(this.card.getValue().name());


        return parent;
    }

    private String addStyle(String oldStyle, String newStyle) {
        return oldStyle + "; " + newStyle;
    }
}
