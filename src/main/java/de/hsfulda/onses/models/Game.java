package de.hsfulda.onses.models;

import java.util.ArrayList;

public class Game {
    private final ArrayList<Card> drawCardDeck = new ArrayList<>();

    private Card lastPlayedCard = null;

    public Card getLastPlayedCard() {
        return lastPlayedCard;
    }

    public Game setLastPlayedCard(Card lastPlayedCard) {
        this.lastPlayedCard = lastPlayedCard;
        return this;
    }

    public ArrayList<Card> getDrawCardDeck() {
        return drawCardDeck;
    }

    public void addCardToDrawCardDeck(Card card) {
        drawCardDeck.add(card);
    }

}
