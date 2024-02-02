
package de.hsfulda.onses;

import de.hsfulda.onses.services.PlayerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import de.hsfulda.onses.models.Player;

public class PlayerServiceTest {

    @Test
    @DisplayName("addPlayerToPlayerList")
    void addPlayerToPlayerList() {
        //arrange
        Player input = new Player();
        Player expected = input;
        // act
        PlayerService playerservice = new PlayerService();
        playerservice.addPlayerToList(input);
        Player answer = playerservice.getPlayerList().getFirst();
        // assert
        assertEquals(expected, answer);
    }

    @Test
    @DisplayName("addMultiplePlayerToPlayerList")
    void addMultiplePlayerToPlayerList() {
        //arrange
        Player input1 = new Player();
        Player expected1 = input1;
        Player input2 = new Player();
        Player expected2 = input2;
        // act
        PlayerService playerservice = new PlayerService();
        playerservice.addPlayerToList(input1);
        playerservice.addPlayerToList(input2);

        Player answer1 = playerservice.getPlayerList().getFirst();
        Player answer2 = playerservice.getPlayerList().get(1);
        // assert
        assertEquals(expected1, answer1);
        assertEquals(expected2, answer2);
    }
    @Test
    @DisplayName("checkCurrentTurnAfterNextTurn")
    void checkCurrentTurnAfterNextTurn() {
        //arrange
        boolean expected = true;
        // act
        PlayerService playerservice = new PlayerService();
        playerservice.setCurrentTurn(false);
        playerservice.nextTurn();
        boolean answer = playerservice.getCurrentTurn();
        // assert
        assertEquals(expected, answer);
    }
    @Test
    @DisplayName("checkCurrentTurnAfterTwoTurns")
    void checkCurrentTurnAfterTwoTurns() {
        //arrange
        boolean expected = false;
        // act
        PlayerService playerservice = new PlayerService();
        playerservice.setCurrentTurn(false);
        playerservice.nextTurn();
        playerservice.nextTurn();
        boolean answer = playerservice.getCurrentTurn();
        // assert
        assertEquals(expected, answer);
    }
}
