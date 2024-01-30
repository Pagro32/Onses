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

}
