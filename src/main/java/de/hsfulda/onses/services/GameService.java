package de.hsfulda.onses.services;

import de.hsfulda.onses.models.Game;

public class GameService {
    private final Game game;
    public GameService(Game game) {
        this.game = game;
    }
    public GameService() {
        this(new Game());
    }

}
