package de.hsfulda.onses.services;

import de.hsfulda.onses.models.Card;
import de.hsfulda.onses.models.Game;
import de.hsfulda.onses.models.Player;

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

    public boolean allowedMove(Player player, Card card)
    {
        boolean isAllowed = false;
        Card lastCard = game.getLastPlayedCard();
        // rules:
        if (card.getColor() == lastCard.getColor()) isAllowed = true; // same color

        return isAllowed;
    }

}
