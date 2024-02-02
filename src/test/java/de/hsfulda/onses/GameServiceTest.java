
package de.hsfulda.onses;

import de.hsfulda.onses.models.Game;
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
    @DisplayName("FillDrawCardDeck")
    public  void FillDrawCardDeck() {
        GameService gameService = new GameService();
        int expected = 112;

        gameService.fillDrawDeck();
        int answer = gameService.getGame().getDrawCardDeck().size();

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
    @Test
    @DisplayName("ShuffelDeck")
    public void ShuffelDeck() {
        GameService shuffled = new GameService();
        GameService notshuffled = new GameService();

        shuffled.fillDrawDeck();
        notshuffled.fillDrawDeck();
        shuffled.shuffleDeck();

        int counter = 0;

        for (int i = 0; i < notshuffled.getGame().getDrawCardDeck().size(); i++) {
            if (shuffled.getGame().getDrawCardDeck().get(i).getValue() == notshuffled.getGame().getDrawCardDeck().get(i).getValue() &&
                    shuffled.getGame().getDrawCardDeck().get(i).getColor() == notshuffled.getGame().getDrawCardDeck().get(i).getColor()) {
                counter ++;
            }
            if (counter == notshuffled.getGame().getDrawCardDeck().size()) {
                fail("Deck wurde nicht richtig gemischt");
            }
        }
    }

    @Test
    @DisplayName("RelationshipGameService_Game")
    public void RelationshipGameService_Game()
    {
        // arrange
        GameService expected = new GameService();
        //act
        GameService answer = expected.getGame().getGameService();
        // assert
        assertEquals(expected, answer);
    }
    @Test
    @DisplayName("allRelationsTest")
    public void allRelationsTest ()
    {
        // arrange
        GameService expected = new GameService();
        //act
        Player input1 = expected.getGame().getPlayerService().getPlayerList().getFirst();
        Player input2 = expected.getGame().getPlayerService().getPlayerList().getLast();
        GameService answer1 = input1.getPlayerService().getGame().getGameService();
        GameService answer2 = input2.getGame().getGameService();
        // assert
        assertEquals(expected, answer1);
        assertEquals(expected, answer2);
    }

    @Test
    @DisplayName("SkipTest")
    public void SkipTest ()
    {
        GameService gameservice = new GameService();
        boolean expected = gameservice.getGame().getPlayerService().getCurrentTurn();

        gameservice.playCard(new Player(), new Card().setValue(Card.Value.SKIP).setColor(Card.Color.BLACK));

        assertEquals(expected, gameservice.getGame().getPlayerService().getCurrentTurn());
    }

}
