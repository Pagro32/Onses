
package de.hsfulda.onses;

import de.hsfulda.onses.services.PlayerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import de.hsfulda.onses.models.Player;

public class PlayerServiceTest {

    @Test
    @DisplayName("addPlayerToPlayerList")
    void addPlayerToPlayerList()
    {
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

}
