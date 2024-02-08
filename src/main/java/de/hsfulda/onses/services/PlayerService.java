package de.hsfulda.onses.services;

import de.hsfulda.onses.models.Game;
import de.hsfulda.onses.models.Player;
import de.hsfulda.onses.models.Card;
import de.hsfulda.onses.services.GameService;

import java.util.ArrayList;
import java.util.Random;

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
        addPlayerToList(new Player().setPlayerService(this).setPlayerName("Player"));
        addPlayerToList(new Player().setPlayerService(this).setPlayerName("Bot"));
    }

    public void removeCardFromPlayerDeck(Card card) {
        Player player;
        if (this.currentTurn) {
            player = playerList.getFirst();
        } else {
            player = playerList.getLast();
        }
        player.removeCardFromPlayerDeck(card);
    }

    public void botMove() {
        Card lastPlayedCard = game.getLastPlayedCard();
        for (int i = 0; i < this.playerList.getLast().getPlayerDeck().size(); i++) {
            if (getGame().getGameService().legalMove(this.playerList.getLast().getPlayerDeck().get(i))) {
                Card playCard = this.playerList.getLast().getPlayerDeck().get(i);
                this.removeCardFromPlayerDeck(playCard);
                if (playCard.getValue() == Card.Value.CHOOSE || playCard.getValue() == Card.Value.CHOOSEDRAW)
                {
                    int min = 0;
                    int max = 3;
                    Random rand = new Random();
                    int randomNum = rand.nextInt((max - min) + 1) + min;
                    switch (randomNum){
                        case 0:
                            playCard.setColor(Card.Color.BLUE);
                            break;
                        case 1:
                            playCard.setColor(Card.Color.RED);
                            break;
                        case 2:
                            playCard.setColor(Card.Color.YELLOW);
                            break;
                        case 3:
                            playCard.setColor(Card.Color.GREEN);
                            break;
                    }
                }
                getGame().getGameService().playCard(playCard);
                break;
            }
        }
        if (lastPlayedCard == game.getLastPlayedCard() && !playerList.getLast().getPlayerDeck().isEmpty()) {
            game.getGameService().drawCard(1);
        }
    }
}