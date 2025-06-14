package de.hsfulda.onses.services;

import de.hsfulda.onses.models.Card;
import de.hsfulda.onses.models.Game;
import de.hsfulda.onses.models.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GameService {
    private final Game game;

    private boolean test = false;

    public GameService setTest(boolean test) {
        this.test = test;
        return this;
    }

    public boolean getTest() {
        return this.test;
    }
    public GameService(Game game) {
        this.game = game;
        this.game.setGameService(this);
        this.game.getPlayerService().getPlayerList().getLast().setEnemy(true);
        fillDrawDeck();
        shuffleDeck();
        setFirstCard();
        fillPlayerDecks();
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
        if (!game.getDrawCardDeck().isEmpty()) {
            boolean isFaceDown = false;
            Player player = null;
            if (game.getPlayerService().getCurrentTurn()) {
                player = game.getPlayerService().getPlayerList().getFirst();
            } else {
                isFaceDown = true;
                player = game.getPlayerService().getPlayerList().getLast();
            }
            for (int i = 0; i < amount; i++) {
                Card newCard = game.getDrawCardDeck().getFirst().setFacedown(isFaceDown).setPlayer(player);
                player.addCardToPlayerDeck(newCard);
                game.getDrawCardDeck().removeFirst();
            }
            if (amount == 1 && !player.getPlayerDeck().isEmpty()) {
                nextPlayer();
                if (!game.getPlayerService().getCurrentTurn()) game.getPlayerService().botMove();
            }
        }
        if (amount == 7) {
            nextPlayer();
        }
    }

    public void addLastPlayedCardToDrawCardDeck() {
        Card lastCard = game.getLastPlayedCard();
        if (lastCard.getValue() == Card.Value.CHOOSE || lastCard.getValue() == Card.Value.CHOOSEDRAW) {
            lastCard.setColor(Card.Color.BLACK);
        }
        game.addCardToDrawCardDeck(lastCard.setFacedown(true));
    }

    public void chooseColor() {
        // Abfrage Farbe
        if (game.getPlayerService().getCurrentTurn()) {
            Card.Color color = Card.Color.BLUE; //Vorübergehend Blau
            game.changeLastPlayedCardColor(color);
        }
    }

    public void playSeven() {
        ArrayList<Card> übergangBot = new ArrayList<>(game.getPlayerService().getPlayerList().getLast().getPlayerDeck());
        ArrayList<Card> übergangSpieler = new ArrayList<>(game.getPlayerService().getPlayerList().getFirst().getPlayerDeck());

        game.getPlayerService().getPlayerList().getFirst().getPlayerDeck().clear();
        for (int i = 0; i < übergangBot.size(); i++) {
            game.getPlayerService().getPlayerList().getFirst().getPlayerDeck().add(übergangBot.get(i));
        }

        game.getPlayerService().getPlayerList().getLast().getPlayerDeck().clear();
        for (int i = 0; i < übergangSpieler.size(); i++) {
            game.getPlayerService().getPlayerList().getLast().getPlayerDeck().add(übergangSpieler.get(i));
        }
    }

    public void checkForWin() {
        if (this.game.getPlayerService().getPlayerList().getFirst().getPlayerDeck().isEmpty() || this.game.getPlayerService().getPlayerList().getLast().getPlayerDeck().isEmpty()) {
            this.game.setGameOver(true);
        }
    }

    public void playCard(Card card) {
        if(card.getPlayer() != null) {
            card.getPlayer().removeCardFromPlayerDeck(card);
        }
        // add lastPlayedCard back to drawCardDeck
        this.addLastPlayedCardToDrawCardDeck();
        game.setLastPlayedCard(card);
        // check for special rules (draw, colorchoose, skip,...)
        // Skip

        switch (card.getValue()) {
            case SKIP, REVERSE:
                nextPlayer();
                nextPlayer();
                break;
            case CHOOSE:
                //chooseColor();
                nextPlayer();
                break;
            case CHOOSEDRAW:
                //chooseColor();
                nextPlayer();
                drawCard(4);
                break;
            case DRAWTWO:
                nextPlayer();
                drawCard(2);
                break;
            default:
                nextPlayer();
                break;
        }
        checkForWin();
        if (!this.test) {
            if (!game.getPlayerService().getCurrentTurn()) {
                game.getPlayerService().botMove();
            }
        }
    }

    public boolean legalMove(Card card) {
        boolean legalMoveFound = false;
        Card lastCard = game.getLastPlayedCard();
        // rules:
        if (card.getColor() == lastCard.getColor()) legalMoveFound = true; // same color

        if (card.getValue() == lastCard.getValue()) legalMoveFound = true; // same value

        return legalMoveFound;
    }

    public void fillDrawDeck() {
        for (Card.Color i : Card.Color.values()) {
            for (Card.Value j : Card.Value.values()) {
                if (i != Card.Color.BLACK && j != Card.Value.CHOOSE && j != Card.Value.CHOOSEDRAW) {
                    game.addCardToDrawCardDeck(new Card().setColor(i).setValue(j).setFacedown(true));
                    game.addCardToDrawCardDeck(new Card().setColor(i).setValue(j).setFacedown(true));
                }
            }
        }

        for (int i = 0; i != 4; i++) {
            game.addCardToDrawCardDeck(new Card().setColor(Card.Color.BLACK).setValue(Card.Value.CHOOSE).setFacedown(true));
            game.addCardToDrawCardDeck(new Card().setColor(Card.Color.BLACK).setValue(Card.Value.CHOOSEDRAW).setFacedown(true));
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
                this.game.getDrawCardDeck().remove(card);
                break;
            }
        }
    }

    public void fillPlayerDecks() {
        this.drawCard(7);
        this.drawCard(7);
    }

}