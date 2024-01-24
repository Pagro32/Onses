package de.hsfulda.onses.models;

import java.util.ArrayList;

public class Player {

    private final ArrayList<Card> playerDeck = new ArrayList<>();

    public ArrayList<Card> getPlayerDeck() {
        return playerDeck;
    }

    public void addCardToPlayerDeck(Card card) {
        playerDeck.add(card);
    }
}
