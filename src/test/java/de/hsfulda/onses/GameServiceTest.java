
package de.hsfulda.onses;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import de.hsfulda.onses.models.Card;
import de.hsfulda.onses.models.Player;
import de.hsfulda.onses.services.GameService;

public class GameServiceTest {
    @Test
    @DisplayName("playCardRedEight")
    public void playCardRedEight() {
        // arrange
        Card input = new Card().setColor(Card.Color.RED).setValue(Card.Value.EIGHT);
        // act
        GameService gameService = new GameService();
        gameService.playCard(new Player(), input);
        Card answer = gameService.getGame().getLastPlayedCard();
        // assert
        assertEquals(input, answer);
    }
    @Test
    @DisplayName("playCardBlackChoose")
    public void playCardBlackChoose() {
        // arrange
        Card input = new Card().setColor(Card.Color.BLACK).setValue(Card.Value.CHOOSE);
        // act
        GameService gameService = new GameService();
        gameService.playCard(new Player(), input);
        Card answer = gameService.getGame().getLastPlayedCard();
        // assert
        assertEquals(input, answer);
    }

    @Test
    @DisplayName("legalMoveSameColorRed")
    public void legalMoveSameColorRed() {
        // arrange
        Card input = new Card().setColor(Card.Color.RED).setValue(Card.Value.TWO);
        boolean expected = true;
        // act
        GameService gameService = new GameService();
        gameService.getGame().setLastPlayedCard(new Card().setColor(Card.Color.RED).setValue(Card.Value.FIVE));

        boolean answer = gameService.legalMove(new Player(), input);
        // assert
        assertEquals(expected, answer);
    }
    @Test
    @DisplayName("legalMoveSameColorBlue")
    public void legalMoveSameColorBlue() {
        // arrange
        Card input = new Card().setColor(Card.Color.BLUE).setValue(Card.Value.THREE);
        boolean expected = true;
        // act
        GameService gameService = new GameService();
        gameService.getGame().setLastPlayedCard(new Card().setColor(Card.Color.BLUE).setValue(Card.Value.ONE));

        boolean answer = gameService.legalMove(new Player(), input);
        // assert
        assertEquals(expected, answer);
    }

    @Test
    @DisplayName("legalMoveSameValueOne")
    public void legalMoveSameValueOne() {
        // arrange
        Card input = new Card().setColor(Card.Color.RED).setValue(Card.Value.ONE);
        boolean expected = true;
        // act
        GameService gameService = new GameService();
        gameService.getGame().setLastPlayedCard(new Card().setColor(Card.Color.YELLOW).setValue(Card.Value.ONE));

        boolean answer = gameService.legalMove(new Player(), input);
        // assert
        assertEquals(expected, answer);
    }

    @Test
    @DisplayName("legalMoveSameValueThree")
    public void legalMoveSameValueThree() {
        // arrange
        Card input = new Card().setColor(Card.Color.GREEN).setValue(Card.Value.THREE);
        boolean expected = true;
        // act
        GameService gameService = new GameService();
        gameService.getGame().setLastPlayedCard(new Card().setColor(Card.Color.YELLOW).setValue(Card.Value.THREE));

        boolean answer = gameService.legalMove(new Player(), input);
        // assert
        assertEquals(expected, answer);
    }

    @Test
    @DisplayName("legalMoveColorBlack")
    public void legalMoveColorBlack() {
        // arrange
        Card input = new Card().setColor(Card.Color.BLACK).setValue(Card.Value.CHOOSE);
        boolean expected = true;
        // act
        GameService gameService = new GameService();
        gameService.getGame().setLastPlayedCard(new Card().setColor(Card.Color.YELLOW).setValue(Card.Value.THREE));

        boolean answer = gameService.legalMove(new Player(), input);
        // assert
        assertEquals(expected, answer);
    }

    @Test
    @DisplayName("legalMoveColorBlackCHOOSEDRAW")
    public void legalMoveColorBlackCHOOSEDRAW() {
        // arrange
        Card input = new Card().setColor(Card.Color.BLACK).setValue(Card.Value.CHOOSEDRAW);
        boolean expected = true;
        // act
        GameService gameService = new GameService();
        gameService.getGame().setLastPlayedCard(new Card().setColor(Card.Color.GREEN).setValue(Card.Value.FIVE));

        boolean answer = gameService.legalMove(new Player(), input);
        // assert
        assertEquals(expected, answer);
    }

}
