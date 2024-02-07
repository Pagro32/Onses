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
        final Pane mainPane = (Pane) parent.lookup("#cardPane");
        final Label cardName = (Label) parent.lookup("#labelName");

        if(!card.isFacedown()) {
            switch(card.getColor()) {
                case RED -> mainPane.setStyle(addStyle(mainPane.getStyle(), "-fx-background-color: red"));
                case BLUE -> mainPane.setStyle(addStyle(mainPane.getStyle(), "-fx-background-color: blue"));
                case GREEN -> mainPane.setStyle(addStyle(mainPane.getStyle(), "-fx-background-color: green"));
                case YELLOW -> mainPane.setStyle(addStyle(mainPane.getStyle(), "-fx-background-color: yellow"));
                default -> mainPane.setStyle(addStyle(mainPane.getStyle(), "-fx-background-color: black"));
            }
        }

        if(card.isFacedown()) {
            cardName.setText("Card");
        } else {
            switch(card.getValue()) {
                case ONE -> cardName.setText("1");
                case TWO -> cardName.setText("2");
                case THREE -> cardName.setText("3");
                case FOUR -> cardName.setText("4");
                case FIVE -> cardName.setText("5");
                case SIX -> cardName.setText("6");
                case SEVEN -> cardName.setText("7");
                case EIGHT -> cardName.setText("8");
                case NINE -> cardName.setText("9");
                case SKIP -> cardName.setText("skip player");
                case CHOOSE -> cardName.setText("wish card");
                case DRAWTWO -> cardName.setText("+2");
                case REVERSE -> cardName.setText("reverse");
            }
        }


        return parent;
    }

    private String addStyle(String oldStyle, String newStyle) {
        return oldStyle + "; " + newStyle;
    }
}
