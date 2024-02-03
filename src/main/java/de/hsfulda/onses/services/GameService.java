package de.hsfulda.onses.services;

import de.hsfulda.onses.models.Card;
import de.hsfulda.onses.models.Game;
import de.hsfulda.onses.models.Player;

import java.util.Collections;

public class GameService {
    private final Game game;

    public GameService(Game game) {
        this.game = game;
        this.game.setGameService(this);
        fillDrawDeck();
        shuffleDeck();
        setFirstCard();
    }

    public GameService() {
        this(new Game());
    }

    public Game getGame() {
        return game;
    }

    public void nextPlayer() {
        game.getPlayerService().nextTurn();
    }

    public void drawCard(int amount) {
        Player player = null;
        if (game.getPlayerService().getCurrentTurn()) {
            player = game.getPlayerService().getPlayerList().getFirst();
        } else {
            player = game.getPlayerService().getPlayerList().getLast();
        }
        for (int i = 0; i < amount; i++) {
            player.getPlayerDeck().add(game.getDrawCardDeck().getFirst());
            game.getDrawCardDeck().removeFirst();
        }
        if (amount != 1) {
            nextPlayer();
        }
    }

    public void addLastPlayedCardToDrawCardDeck() {
        Card lastCard = game.getLastPlayedCard();
        if (lastCard.getValue() == Card.Value.CHOOSE || lastCard.getValue() == Card.Value.CHOOSEDRAW) {
            lastCard.setColor(Card.Color.BLACK);
        }
        game.addCardToDrawCardDeck(lastCard);
    }

    public void playCard(Card card) {
        // add lastPlayedCard back to drawCardDeck
        this.addLastPlayedCardToDrawCardDeck();
        game.setLastPlayedCard(card);
        // check for special rules (draw, colorchoose, skip,...)
        // Skip
        if (card.getValue() == Card.Value.SKIP) {
            nextPlayer();
            nextPlayer();
        }
        // Reverse
        if (card.getValue() == Card.Value.REVERSE) {
            nextPlayer();
            nextPlayer();
        }
        // Choose
        if (card.getValue() == Card.Value.CHOOSE) {
            // Abfrage Farbe
            Card.Color color = Card.Color.BLUE; //Vorübergehend Blau
            game.changeLastPlayedCardColor(color);
            nextPlayer();
        }
        // Draw
        if (card.getValue() == Card.Value.DRAWTWO) {
            nextPlayer();
            drawCard(2);
        }
        // ChooseDraw
        if (card.getValue() == Card.Value.CHOOSEDRAW) {
            // Abfrage Farbe
            Card.Color color = Card.Color.BLUE; //Vorübergehend Blau
            game.changeLastPlayedCardColor(color);
            nextPlayer();
            drawCard(4);
        }
    }

    public boolean legalMove(Card card) {
        boolean legalMoveFound = false;
        Card lastCard = game.getLastPlayedCard();
        // rules:
        if (card.getColor() == lastCard.getColor()) legalMoveFound = true; // same color

        if (card.getValue() == lastCard.getValue()) legalMoveFound = true; // same value

        if (card.getColor() == Card.Color.BLACK) legalMoveFound = true; // Color Black

        return legalMoveFound;
    }

    public void fillDrawDeck() {
        for (Card.Color i : Card.Color.values()) {
            for (Card.Value j : Card.Value.values()) {
                if (i != Card.Color.BLACK && j != Card.Value.CHOOSE && j != Card.Value.CHOOSEDRAW) {
                    game.addCardToDrawCardDeck(new Card().setColor(i).setValue(j));
                    game.addCardToDrawCardDeck(new Card().setColor(i).setValue(j));
                }
            }
        }

        for (int i = 0; i != 4; i++) {
            game.addCardToDrawCardDeck(new Card().setColor(Card.Color.BLACK).setValue(Card.Value.CHOOSE));
            game.addCardToDrawCardDeck(new Card().setColor(Card.Color.BLACK).setValue(Card.Value.CHOOSEDRAW));
        }
    }

    public void shuffleDeck() {
        Collections.shuffle(game.getDrawCardDeck());
    }

    public void setFirstCard() {
        for (int i = 0; i < this.game.getDrawCardDeck().size(); i++) {
            Card card = this.game.getDrawCardDeck().get(i);
            if (card.getColor() != Card.Color.BLACK) {
                this.game.setLastPlayedCard(card);
                break;
            }
        }
    }

}
