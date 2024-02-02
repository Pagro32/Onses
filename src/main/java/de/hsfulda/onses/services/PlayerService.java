package de.hsfulda.onses.services;

import de.hsfulda.onses.models.Game;
import de.hsfulda.onses.models.Player;

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

    public void nextTurn()
    {
        currentTurn = !currentTurn;
    }

    public boolean getCurrentTurn() {
        return currentTurn;
    }

    public PlayerService setCurrentTurn(boolean currentTurn) {
        this.currentTurn = currentTurn;
        return this;
    }

    public Game getGame() {
        return game;
    }

    public PlayerService setGame(Game game) {
        this.game = game;
        return this;
    }

    public PlayerService()
    {
        addPlayerToList(new Player().setPlayerService(this));
        addPlayerToList(new Player().setPlayerService(this));
    }
}
