package de.hsfulda.onses;

import de.hsfulda.onses.models.Card;
import de.hsfulda.onses.models.Game;
import de.hsfulda.onses.services.GameService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    @Test
    @DisplayName("lastPlayedCard")
    public void lastPlayedCard() {
        // arrange
        Game game = new Game();
        Card card = new Card();
        // act
        card.setColor(Card.Color.RED).setValue(Card.Value.EIGHT);
        Card answer = card;
        game.setLastPlayedCard(card);
        // assert
        assertEquals(game.getLastPlayedCard(), answer);
    }

    @Test
    @DisplayName("GetDrawCardDeck")
    public void GetDrawCardDeck() {
        // arrange
        Game game = new Game();
        Card card = new Card();
        // act
        card.setColor(Card.Color.RED).setValue(Card.Value.EIGHT);
        Card answer = card;
        game.addCardToDrawCardDeck(card);
        // assert
        assertEquals(game.getDrawCardDeck().getFirst(), answer);
    }

    @Test
    @DisplayName("GetTwoCardsFromDrawCardDeck")
    public void GetTwoCardsFromDrawCardDeck() {
        // arrange
        Game game = new Game();
        Card card1 = new Card();
        Card card2 = new Card();
        // act
        card1.setColor(Card.Color.RED).setValue(Card.Value.EIGHT);
        card2.setColor(Card.Color.BLUE).setValue(Card.Value.ONE);
        Card answer1 = card1;
        Card answer2 = card2;
        game.addCardToDrawCardDeck(card1);
        game.addCardToDrawCardDeck(card2);
        // assert
        assertEquals(game.getDrawCardDeck().getFirst(), answer1);
        assertEquals(game.getDrawCardDeck().get(1), answer2);
    }

    @Test
    @DisplayName("RelationshipGame_PlayerService")
    public void RelationshipGame_PlayerService() {
        // arrange
        Game expected = new Game();
        // act
        Game answer = expected.getPlayerService().getGame();
        // assert
        assertEquals(expected, answer);
    }

    @Test
    @DisplayName("CheckForGetGameOver")
    public void CheckForGetGameOver() {
        // arrange
        GameService gameService = new GameService();
        // act
        boolean gameOver = gameService.getGame().getGameOver();
        //assert
        assertFalse(gameOver);
    }


    @Test
    @DisplayName("CheckForSetGameOver")
    public void CheckForSetGameOver() {
        // arrange
        GameService gameService = new GameService();
        // act
        gameService.getGame().setGameOver(true);
        boolean gameOver = gameService.getGame().getGameOver();
        // assert
        assertTrue(gameOver);
    }
}
