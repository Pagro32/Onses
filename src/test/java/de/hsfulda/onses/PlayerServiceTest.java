
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
        boolean expected = true;
        boolean answer = false;
        // act
        PlayerService playerservice = new PlayerService();
        playerservice.addPlayerToList(input);
        for (Player i : playerservice.getPlayerList())
        {
            if (i == input) {answer = true; break;}
        }
        // assert
        assertEquals(expected, answer);
    }

    @Test
    @DisplayName("addMultiplePlayerToPlayerList")
    void addMultiplePlayerToPlayerList() {
        //arrange
        boolean expected = true;
        Player input1 = new Player();
        boolean answer1 = false;
        Player input2 = new Player();
        boolean answer2 = false;
        // act
        PlayerService playerservice = new PlayerService();
        playerservice.addPlayerToList(input1);
        playerservice.addPlayerToList(input2);

        for (Player i : playerservice.getPlayerList())
        {
            if (i == input1) {answer1 = true;}
            if (i == input2) {answer2 = true;}
        }
        // assert
        assertEquals(expected, answer1);
        assertEquals(expected, answer2);
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
