package de.hsfulda.onses.services;

import de.hsfulda.onses.models.Player;

import java.util.ArrayList;

public class PlayerService {

    private final ArrayList<Player> playerList = new ArrayList<>();

    public ArrayList<Player> getPlayerList() {
        return playerList;
    }
    public void addPlayerToList(Player player) {
        playerList.add(player);
    }


    // button on gui activates it
    public void addOpponents(int amount) {
        for (int i=0; i<amount; i++)
        {
            playerList.add(new Player());
        }
    }

}
