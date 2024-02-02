package de.hsfulda.onses.models;

import de.hsfulda.onses.services.GameService;
import de.hsfulda.onses.services.PlayerService;

import java.util.ArrayList;

public class Game {

    private GameService gameService;
    private PlayerService playerService;

    private final ArrayList<Card> drawCardDeck = new ArrayList<>();

    private Card lastPlayedCard = null;

    public Card getLastPlayedCard() {
        return lastPlayedCard;
    }

    public void changeLastPlayedCardColor(Card.Color color) {
        lastPlayedCard.setColor(color);
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
}
