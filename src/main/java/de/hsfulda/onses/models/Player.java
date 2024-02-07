package de.hsfulda.onses.models;

import de.hsfulda.onses.services.PlayerService;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class Player {
    public final static String PROPERTY_PLAYER_DECK = "playerDeck";

    protected PropertyChangeSupport listeners;

    private PlayerService playerService;
    private Game game;
    private String playerName;
    private final ArrayList<Card> playerDeck = new ArrayList<>();

    public ArrayList<Card> getPlayerDeck() {
        return playerDeck;
    }

    public void addCardToPlayerDeck(Card card) {
        final ArrayList<Card> oldplayerDeck = new ArrayList<>(this.playerDeck);
        playerDeck.add(card);
        this.firePropertyChange(PROPERTY_PLAYER_DECK, oldplayerDeck, playerDeck);
    }

    public PlayerService getPlayerService() {
        return playerService;
    }

    public Player setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
        return this;
    }

    public Game getGame() {
        return game;
    }

    public Player setGame(Game game) {
        this.game = game;
        return this;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Player setPlayerName(String playerName) {
        this.playerName = playerName;
        return this;
    }

    public PropertyChangeSupport listeners() {
        if(this.listeners == null) {
            this.listeners = new PropertyChangeSupport(this);
        }
        return this.listeners;
    }

    public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        if (this.listeners != null) {
            this.listeners.firePropertyChange(propertyName, oldValue, newValue);
            return true;
        }
        return false;
    }
}
