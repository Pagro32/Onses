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
            if (amount == 1 && !player.getPlayerDeck().isEmpty()) {
                Card drawnCard = player.getPlayerDeck().getLast();

                if (legalMove(drawnCard)) {
                    player.getPlayerService().removeCardFromPlayerDeck(drawnCard);
                    playCard(drawnCard);
                }
                else nextPlayer();
            }
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

    public void chooseColor() {
        // Abfrage Farbe
        Card.Color color = Card.Color.BLUE; //Vorübergehend Blau
        game.changeLastPlayedCardColor(color);
    }

    public void checkForWin() {
        if (this.game.getPlayerService().getPlayerList().getFirst().getPlayerDeck().isEmpty()) {
            System.out.println("Player wins");
        } else if (this.game.getPlayerService().getPlayerList().getLast().getPlayerDeck().isEmpty()) {
            System.out.println("Bot wins");
        }
    }

    public void playCard(Card card) {
        // add lastPlayedCard back to drawCardDeck
        this.addLastPlayedCardToDrawCardDeck();
        game.setLastPlayedCard(card);
        // check for special rules (draw, colorchoose, skip,...)
        // Skip

        switch (card.getValue()) {
            case SKIP:
                nextPlayer();
                nextPlayer();
                break;
            case REVERSE:
                nextPlayer();
                nextPlayer();
                break;
            case CHOOSE:
                chooseColor();
                nextPlayer();
                break;
            case CHOOSEDRAW:
                chooseColor();
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
