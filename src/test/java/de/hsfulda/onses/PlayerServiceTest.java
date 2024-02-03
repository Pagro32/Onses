
package de.hsfulda.onses;

import com.sun.tools.jconsole.JConsoleContext;
import de.hsfulda.onses.models.Card;
import de.hsfulda.onses.services.GameService;
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
    @Test
    @DisplayName("RelationShip_Player_PlayerService")
    void Relationship_Player_PlayerService() {
        // arrange
        PlayerService expected = new PlayerService();
        // act
        PlayerService answer1 = expected.getPlayerList().getFirst().getPlayerService();
        PlayerService answer2 = expected.getPlayerList().getLast().getPlayerService();
        // assert
        assertEquals(expected, answer1);
        assertEquals(expected, answer2);
    }

    @Test
    @DisplayName("CheckForCardRemoval")
    void CheckForCardRemoval() {
        PlayerService playerService = new PlayerService();

        Card card1 = new Card().setColor(Card.Color.YELLOW).setValue(Card.Value.TWO);
        Card card2 = new Card().setColor(Card.Color.RED).setValue(Card.Value.ONE);

        playerService.getPlayerList().getFirst().addCardToPlayerDeck(card1);
        playerService.getPlayerList().getFirst().addCardToPlayerDeck(card2);
        int before = playerService.getPlayerList().getFirst().getPlayerDeck().size();

        playerService.removeCardFromPlayerDeck(card1);
        int after = playerService.getPlayerList().getFirst().getPlayerDeck().size();

        assertEquals(before - 1, after);
    }

    @Test
    @DisplayName("CheckCardRemovalFromBotDeck")
    void CheckCardRemovalFromBotDeck() {
        GameService gameService = new GameService();

        Card card1 = new Card().setColor(Card.Color.YELLOW).setValue(Card.Value.TWO);
        Card card2 = new Card().setColor(Card.Color.RED).setValue(Card.Value.ONE);

        gameService.getGame().setLastPlayedCard(new Card().setColor(Card.Color.RED).setValue(Card.Value.FIVE));
        gameService.getGame().getPlayerService().setCurrentTurn(false);

        gameService.getGame().getPlayerService().getPlayerList().getLast().addCardToPlayerDeck(card1);
        gameService.getGame().getPlayerService().getPlayerList().getLast().addCardToPlayerDeck(card2);
        int before = gameService.getGame().getPlayerService().getPlayerList().getLast().getPlayerDeck().size();

        gameService.getGame().getPlayerService().botMove();
        int after = gameService.getGame().getPlayerService().getPlayerList().getLast().getPlayerDeck().size();

        assertEquals(before - 1, after);
    }
}
