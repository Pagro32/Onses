package de.hsfulda.onses.models;

import de.hsfulda.onses.services.GameService;
import de.hsfulda.onses.services.PlayerService;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;

public class Game {

    public final static String PROPERTY_LAST_PLAYED_CARD = "lastPlayedCard";
    public final static String PROPERTY_DRAW_CARD_DECK = "drawCardDeck";
    public final static String PROPERTY_GAME_OVER = "gameOver";

    protected PropertyChangeSupport listeners;

    private GameService gameService;
    private PlayerService playerService;

    private boolean gameOver = false;

    private final ArrayList<Card> drawCardDeck = new ArrayList<>();

    private Card lastPlayedCard = null;

    public Card getLastPlayedCard() {
        return lastPlayedCard;
    }

    public void changeLastPlayedCardColor(Card.Color color) {
        lastPlayedCard.setColor(color);
    }
    public Game setLastPlayedCard(Card lastPlayedCard) {
        final Card oldLastPlayedCard = this.lastPlayedCard;
        this.lastPlayedCard = lastPlayedCard.setFacedown(false);
        this.firePropertyChange(PROPERTY_LAST_PLAYED_CARD, oldLastPlayedCard, lastPlayedCard);
        return this;
    }

    public ArrayList<Card> getDrawCardDeck() {
        return drawCardDeck;
    }

    public void addCardToDrawCardDeck(Card card) {
        final ArrayList<Card> oldCards = new ArrayList<>(this.drawCardDeck);
        drawCardDeck.add(card.setFacedown(true));
        this.firePropertyChange(PROPERTY_DRAW_CARD_DECK, oldCards, drawCardDeck);
    }

    public GameService getGameService() {
        return gameService;
    }

    public Game setGameService(GameService gameService) {
        this.gameService = gameService;
        return this;
    }

    public PlayerService getPlayerService() {
        return playerService;
    }

    public Game setPlayerService(PlayerService playerService) {
        this.playerService = playerService;
        return this;
    }

    public Game()
    {
        this.playerService = new PlayerService().setGame(this);
    }

    public void setGameOver(boolean gameOver) {
        final boolean oldValue = this.gameOver;
        this.gameOver = gameOver;
        this.firePropertyChange(PROPERTY_GAME_OVER, oldValue, gameOver);
    }

    public boolean getGameOver() {
        return gameOver;
    }

    public PropertyChangeSupport listeners() {
        if(this.listeners == null) {
            this.listeners = new PropertyChangeSupport(this);
        }
        return this.listeners;
    }

    public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        if(this.listeners != null) {
            this.listeners.firePropertyChange(propertyName, oldValue, newValue);
            return true;
        }
        return false;
    }
}
