package de.hsfulda.onses.services;

import de.hsfulda.onses.models.Card;
import de.hsfulda.onses.models.Game;
import de.hsfulda.onses.models.Player;

import java.util.Collections;

public class GameService {
    private final Game game;
    public GameService(Game game) {
        this.game = game;
    }
    public GameService() {
        this(new Game());
    }

    public Game getGame() {
        return game;
    }

    public void playCard(Player player, Card card)
    {
     // add lastPlayedCard back to drawCardDeck
         game.setLastPlayedCard(card);
     // check for special rules (draw, colorchoose, skip,...)
    }

    public boolean legalMove(Player player, Card card)
    {
        boolean legalMoveFound = false;
        Card lastCard = game.getLastPlayedCard();
        // rules:
        if (card.getColor() == lastCard.getColor()) legalMoveFound = true; // same color

        return legalMoveFound;
    }

    public void fillDrawDeck() {
        for (Card.Color i : Card.Color.values()){
            for (Card.Value j : Card.Value.values()){
                if (i != Card.Color.BLACK && j != Card.Value.CHOOSE && j != Card.Value.CHOOSEDRAW){
                    game.addCardToDrawCardDeck(new Card().setColor(i).setValue(j));
                    game.addCardToDrawCardDeck(new Card().setColor(i).setValue(j));
                }
            }
        }

        for (int i = 0; i != 4; i++){
            game.addCardToDrawCardDeck(new Card().setColor(Card.Color.BLACK).setValue(Card.Value.CHOOSE));
            game.addCardToDrawCardDeck(new Card().setColor(Card.Color.BLACK).setValue(Card.Value.CHOOSEDRAW));
        }
    }

    public void shuffleDeck() {
        Collections.shuffle(game.getDrawCardDeck());
    }

}
