package de.hsfulda.onses.services;

import de.hsfulda.onses.models.Player;

import java.util.ArrayList;

public class PlayerService {

    private int currentTurn = 0; // 0-3
    private int totalTurns = 0; // 1-4
    private final ArrayList<Player> playerList = new ArrayList<>();

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }
    public void addPlayerToList(Player player) {
        playerList.add(player);
        totalTurns++;
    }


    // decides which Player is currently at their turn -> 0 = main Player (skip -> 2x nextTurn())
    // possible: at start of game random int generator from 0-max to decide who starts first
    // currentTurn = 2 --> player.getPlayerList(currentTurn) --> Bot 2 (Player 3)
    public void nextTurn()
    {
        if (currentTurn != totalTurns-1) currentTurn++;
        else currentTurn = 0;
    }



    public int getCurrentTurn() {
        return currentTurn;
    }

    public PlayerService setCurrentTurn(int currentTurn) {
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
