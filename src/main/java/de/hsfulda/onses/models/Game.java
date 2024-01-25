package de.hsfulda.onses.models;

public class Game {

    private Card lastPlayedCard = null;

    public Card getLastPlayedCard() {
        return lastPlayedCard;
    }

    public Game setLastPlayedCard(Card lastPlayedCard) {
        this.lastPlayedCard = lastPlayedCard;
        return this;
    }
}
