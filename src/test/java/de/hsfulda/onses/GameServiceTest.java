
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
        gameService.playCard(input);
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
        gameService.playCard(input);
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

        boolean answer = gameService.legalMove(input);
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

        boolean answer = gameService.legalMove(input);
        // assert
        assertEquals(expected, answer);
    }

    @Test
    @DisplayName("FillDrawCardDeck")
    public void FillDrawCardDeck() {
        GameService gameService = new GameService();
        int expected = gameService.getGame().getDrawCardDeck().size() + 112;

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

        boolean answer = gameService.legalMove(input);
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

        boolean answer = gameService.legalMove(input);
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

        boolean answer = gameService.legalMove(input);
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

        boolean answer = gameService.legalMove(input);
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
                counter++;
            }
            if (counter == notshuffled.getGame().getDrawCardDeck().size()) {
                fail("Deck wurde nicht richtig gemischt");
            }
        }
    }

    @Test
    @DisplayName("RelationshipGameService_Game")
    public void RelationshipGameService_Game() {
        // arrange
        GameService expected = new GameService();
        //act
        GameService answer = expected.getGame().getGameService();
        // assert
        assertEquals(expected, answer);
    }

    @Test
    @DisplayName("allRelationsTest")
    public void allRelationsTest() {
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
    public void SkipTest() {
        GameService gameservice = new GameService();
        boolean expected = gameservice.getGame().getPlayerService().getCurrentTurn();

        gameservice.playCard(new Card().setValue(Card.Value.SKIP).setColor(Card.Color.BLACK));

        assertEquals(expected, gameservice.getGame().getPlayerService().getCurrentTurn());
    }

    @Test
    @DisplayName("ReverseTest")
    public void ReverseTest() {
        GameService gameservice = new GameService();
        boolean expected = gameservice.getGame().getPlayerService().getCurrentTurn();

        gameservice.playCard(new Card().setValue(Card.Value.REVERSE).setColor(Card.Color.BLACK));

        assertEquals(expected, gameservice.getGame().getPlayerService().getCurrentTurn());
    }

    @Test
    @DisplayName("ChooseTest")
    public void ChooseTest() {
        GameService gameservice = new GameService();
        boolean expected = true;
        boolean answer = false;

        gameservice.playCard(new Card().setValue(Card.Value.CHOOSE).setColor(Card.Color.BLACK));

        if (gameservice.getGame().getLastPlayedCard().getColor() != Card.Color.BLACK) {
            answer = true;
        }

        assertEquals(expected, answer);

    }

    @Test
    @DisplayName("ChooseDrawTest")
    public void ChooseDrawTest() {
        GameService gameservice = new GameService();
        boolean expected = true;
        boolean answer = false;

        gameservice.playCard(new Card().setValue(Card.Value.CHOOSEDRAW).setColor(Card.Color.BLACK));

        if (gameservice.getGame().getLastPlayedCard().getColor() != Card.Color.BLACK) {
            answer = true;
        }

        assertEquals(expected, answer);

    }
    @Test
    @DisplayName("DrawByChoiceForceKeep")
    public void DrawByChoiceForceKeep() {

        GameService gameService = new GameService();
        Card input = new Card().setColor(Card.Color.RED).setValue(Card.Value.ONE);

        gameService.getGame().getDrawCardDeck().clear();
        gameService.getGame().getPlayerService().setCurrentTurn(true);
        gameService.getGame().setLastPlayedCard(new Card().setColor(Card.Color.GREEN).setValue(Card.Value.SEVEN));
        gameService.getGame().addCardToDrawCardDeck(input);
        gameService.getGame().getPlayerService().getPlayerList().getFirst().addCardToPlayerDeck(new Card().setColor(Card.Color.BLUE).setValue(Card.Value.TWO));
        gameService.drawCard(1);

        Card answer = gameService.getGame().getPlayerService().getPlayerList().getFirst().getPlayerDeck().getLast();

        assertEquals(input, answer);
    }

    @Test
    @DisplayName("DrawCardPlayerDeckBot")
    public void DrawCardPlayerDeckBot() {
        GameService gameService = new GameService();
        gameService.getGame().getPlayerService().setCurrentTurn(false);
        int before = gameService.getGame().getPlayerService().getPlayerList().getLast().getPlayerDeck().size();
        gameService.drawCard(2);
        int after = gameService.getGame().getPlayerService().getPlayerList().getLast().getPlayerDeck().size();

        int expected = 2;
        int answer = after - before;
        assertEquals(expected, answer);
    }

    @Test
    @DisplayName("DrawCardDeckTest")
    public void DrawCardDeckTest() {
        GameService gameService = new GameService();
        int before = gameService.getGame().getDrawCardDeck().size();
        gameService.drawCard(4);
        int after = gameService.getGame().getDrawCardDeck().size();

        int expected = 4;
        int answer = before - after;
        assertEquals(expected, answer);
    }
    @Test
    @DisplayName("DrawCardPlayerDeck")
    public void DrawCardPlayerDeck() {
        GameService gameService = new GameService();
        gameService.getGame().getPlayerService().setCurrentTurn(true);
        int before = gameService.getGame().getPlayerService().getPlayerList().getFirst().getPlayerDeck().size();
        gameService.drawCard(4);
        int after = gameService.getGame().getPlayerService().getPlayerList().getFirst().getPlayerDeck().size();

        int expected = 4;
        int answer = after - before;
        assertEquals(expected, answer);
    }

    @Test
    @DisplayName("DrawByChoiceForcePlay")
    public void DrawByChoiceForcePlay() {

        GameService gameService = new GameService();
        Card input = new Card().setColor(Card.Color.RED).setValue(Card.Value.ONE);

        gameService.getGame().getDrawCardDeck().clear();
        gameService.getGame().getPlayerService().setCurrentTurn(true);
        gameService.getGame().setLastPlayedCard(new Card().setColor(Card.Color.RED).setValue(Card.Value.SEVEN));
        gameService.getGame().addCardToDrawCardDeck(input);
        gameService.getGame().getPlayerService().getPlayerList().getFirst().addCardToPlayerDeck(new Card().setColor(Card.Color.BLUE).setValue(Card.Value.TWO));
        gameService.drawCard(1);

        Card answer = gameService.getGame().getPlayerService().getPlayerList().getFirst().getPlayerDeck().getLast();

        assertNotEquals(input, answer);
    }

    @Test
    @DisplayName("CheckThatBotDeckHasSevenCardsAfterGameStart")
    public void CheckThatBotDeckHasSevenCardsAfterGameStart() {
        GameService gameService = new GameService();

        int expected = 7;
        int actual = gameService.getGame().getPlayerService().getPlayerList().getLast().getPlayerDeck().size();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("CheckFirstCardIsSet")
    public void CheckFirstCardIsSet() {
        GameService gameService = new GameService();
        Card card = gameService.getGame().getLastPlayedCard();

        boolean answer = card != null;

        assertTrue(answer);
    }

    @Test
    @DisplayName("CheckFirstCardIsNotBlack")
    public void CheckFirstCardIsNotBlack() {
        GameService gameService = new GameService();
        Card card = gameService.getGame().getLastPlayedCard();

        boolean answer = card.getColor() != Card.Color.BLACK;

        assertTrue(answer);
    }

    @Test
    @DisplayName("CheckResetOnBlackCards")
    public void CheckResetOnBlackCards() {
        GameService gameService = new GameService();
        gameService.getGame().setLastPlayedCard(new Card().setColor(Card.Color.RED).setValue(Card.Value.FIVE));
        gameService.playCard(new Card().setColor(Card.Color.BLACK).setValue(Card.Value.CHOOSE));
        gameService.playCard(new Card().setColor(Card.Color.YELLOW).setValue(Card.Value.FIVE));

        Card card = gameService.getGame().getDrawCardDeck().getLast();

        assertEquals(Card.Color.BLACK, card.getColor());
    }

    @Test
    @DisplayName("CheckThatPlayerReceivedSevenCards")
    public void CheckThatPlayerReceivedSevenCards() {
        GameService gameService = new GameService();

        int numberOfCards = gameService.getGame().getPlayerService().getPlayerList().getFirst().getPlayerDeck().size();

        assertEquals(7, numberOfCards);
    }

    @Test
    @DisplayName("CheckThatFirstCardGetsRemovedFromDrawCardDeck")
    public void CheckThatFirstCardGetsRemovedFromDrawCardDeck() {
        GameService gameService = new GameService();

        Card lastPlayedCard = gameService.getGame().getLastPlayedCard();

        boolean lastPlayedCardDoesNotExistInDrawCardDeck = true;

        for (int i = 0; i < gameService.getGame().getDrawCardDeck().size(); i++) {
            if (gameService.getGame().getDrawCardDeck().get(i) == lastPlayedCard) {
                lastPlayedCardDoesNotExistInDrawCardDeck = false;
                break;
            }
        }

        assertTrue(lastPlayedCardDoesNotExistInDrawCardDeck);
    }

    @Test
    @DisplayName("ChooseColorBotTest")
    public void ChooseColorBotTest() {
        GameService gameService = new GameService();
        Card card = new Card().setColor(Card.Color.BLACK).setValue(Card.Value.CHOOSE);
        boolean answer = false;

        gameService.getGame().getPlayerService().getPlayerList().getLast().getPlayerDeck().clear();
        gameService.getGame().getPlayerService().getPlayerList().getLast().getPlayerDeck().add(card);

        gameService.getGame().getPlayerService().setCurrentTurn(false);
        gameService.playCard(card);

        if (gameService.getGame().getLastPlayedCard().getColor() != Card.Color.BLACK){
            answer = true;
        }

        assertTrue(answer);
    }
}
