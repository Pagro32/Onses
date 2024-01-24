
package de.hsfulda.onses;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import de.hsfulda.onses.models.Card;
import de.hsfulda.onses.models.Player;
import de.hsfulda.onses.models.Game;
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
    @DisplayName("allowedMoveSameColorRed")
    public void allowedMoveSameColorRed() {
        // arrange
        Card input = new Card().setColor(Card.Color.RED).setValue(Card.Value.TWO);
        boolean expected = true;
        // act
        GameService gameService = new GameService();
        gameService.getGame().setLastPlayedCard(new Card().setColor(Card.Color.RED).setValue(Card.Value.FIVE));

        boolean answer = gameService.allowedMove(new Player(), input);
        // assert
        assertEquals(expected, answer);
    }
    @Test
    @DisplayName("allowedMoveSameColorBlue")
    public void allowedMoveSameColorBlue() {
        // arrange
        Card input = new Card().setColor(Card.Color.BLUE).setValue(Card.Value.THREE);
        boolean expected = true;
        // act
        GameService gameService = new GameService();
        gameService.getGame().setLastPlayedCard(new Card().setColor(Card.Color.BLUE).setValue(Card.Value.ONE));

        boolean answer = gameService.allowedMove(new Player(), input);
        // assert
        assertEquals(expected, answer);
    }

}
