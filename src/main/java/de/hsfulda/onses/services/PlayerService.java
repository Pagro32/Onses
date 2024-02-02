package de.hsfulda.onses.services;

import de.hsfulda.onses.models.Player;

import java.util.ArrayList;

public class PlayerService {

    private boolean currentTurn = true; // true --> real player, false --> Bot
    private int totalTurns = 0;
    private final ArrayList<Player> playerList = new ArrayList<>();

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }
    public void addPlayerToList(Player player) {
        playerList.add(player);
        totalTurns++;
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

    public int getTotalTurns() {
        return totalTurns;
    }

    public PlayerService setTotalTurns(int totalTurns) {
        this.totalTurns = totalTurns;
        return this;
    }
}
