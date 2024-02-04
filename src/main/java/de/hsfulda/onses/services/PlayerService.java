package de.hsfulda.onses.services;

import de.hsfulda.onses.models.Game;
import de.hsfulda.onses.models.Player;
import de.hsfulda.onses.models.Card;
import de.hsfulda.onses.services.GameService;

import java.util.ArrayList;

public class PlayerService {

    private Game game;
    private boolean currentTurn = true; // true --> real player, false --> Bot

    private final ArrayList<Player> playerList = new ArrayList<>();

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    public void addPlayerToList(Player player) {
        playerList.add(player);
    }

    public void nextTurn() {
        currentTurn = !currentTurn;
    }

    public boolean getCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(boolean currentTurn) {
        this.currentTurn = currentTurn;
    }

    public Game getGame() {
        return game;
    }

    public PlayerService setGame(Game game) {
        this.game = game;
        playerList.getFirst().setGame(this.game);
        playerList.getLast().setGame(this.game);
        return this;
    }

    public PlayerService() {
        addPlayerToList(new Player().setPlayerService(this));
        addPlayerToList(new Player().setPlayerService(this));
    }

    public void removeCardFromPlayerDeck(Card card) {
        Player player;
        if (this.currentTurn) {
            player = playerList.getFirst();
        } else {
            player = playerList.getLast();
        }
        player.getPlayerDeck().remove(card);
    }

    public void botMove() {
        for (int i = 0; i < this.playerList.getLast().getPlayerDeck().size(); i++) {
            if (getGame().getGameService().legalMove(this.playerList.getLast().getPlayerDeck().get(i))) {
                Card playCard = this.playerList.getLast().getPlayerDeck().get(i);
                this.removeCardFromPlayerDeck(playCard);
                getGame().getGameService().playCard(playCard);
                break;
            }
        }
    }
}
