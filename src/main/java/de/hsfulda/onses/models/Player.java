package de.hsfulda.onses.models;

import de.hsfulda.onses.services.PlayerService;

import java.util.ArrayList;

public class Player {

    private PlayerService playerService;
    private final ArrayList<Card> playerDeck = new ArrayList<>();

    public ArrayList<Card> getPlayerDeck() {
        return playerDeck;
    }

    public void addCardToPlayerDeck(Card card) {
        playerDeck.add(card);
    }



    public PlayerService getPlayerService() {
        return playerService;
    }

    public Player setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
        return this;
    }
}
