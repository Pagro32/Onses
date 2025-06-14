package de.hsfulda.onses;

import com.sun.jdi.ArrayReference;
import de.hsfulda.onses.models.Game;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import de.hsfulda.onses.models.Card;
import de.hsfulda.onses.models.Player;
import de.hsfulda.onses.services.GameService;

import java.util.ArrayList;

public class GameServiceTest {
    @Test
    @DisplayName("playCardRedEight")
    public void playCardRedEight() {
        // arrange
        Card input = new Card().setColor(Card.Color.RED).setValue(Card.Value.EIGHT);
        // act
        GameService gameService = new GameService();
        gameService.setTest(true);
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
        gameService.setTest(true);
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
        gameService.setTest(true);
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
        gameService.setTest(true);
        gameService.getGame().setLastPlayedCard(new Card().setColor(Card.Color.BLUE).setValue(Card.Value.ONE));

        boolean answer = gameService.legalMove(input);
        // assert
        assertEquals(expected, answer);
    }

    @Test
    @DisplayName("FillDrawCardDeck")
    public void FillDrawCardDeck() {
        GameService gameService = new GameService();
        gameService.setTest(true);
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
        gameService.setTest(true);
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
        gameService.setTest(true);
        gameService.getGame().setLastPlayedCard(new Card().setColor(Card.Color.YELLOW).setValue(Card.Value.THREE));

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
        GameService gameService = new GameService();
        gameService.setTest(true);
        boolean expected = gameService.getGame().getPlayerService().getCurrentTurn();

        gameService.playCard(new Card().setValue(Card.Value.SKIP).setColor(Card.Color.BLACK));

        assertEquals(expected, gameService.getGame().getPlayerService().getCurrentTurn());
    }

    @Test
    @DisplayName("ReverseTest")
    public void ReverseTest() {
        GameService gameService = new GameService();
        gameService.setTest(true);
        boolean expected = gameService.getGame().getPlayerService().getCurrentTurn();

        gameService.playCard(new Card().setValue(Card.Value.REVERSE).setColor(Card.Color.BLACK));

        assertEquals(expected, gameService.getGame().getPlayerService().getCurrentTurn());
    }

    @Test
    @DisplayName("DrawByChoiceForceKeep")
    public void DrawByChoiceForceKeep() {

        GameService gameService = new GameService();
        gameService.setTest(true);
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
        gameService.setTest(true);
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
        gameService.setTest(true);
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
        gameService.setTest(true);
        gameService.getGame().getPlayerService().setCurrentTurn(true);
        int before = gameService.getGame().getPlayerService().getPlayerList().getFirst().getPlayerDeck().size();
        gameService.drawCard(4);
        int after = gameService.getGame().getPlayerService().getPlayerList().getFirst().getPlayerDeck().size();

        int expected = 4;
        int answer = after - before;
        assertEquals(expected, answer);
    }

    @Test
    @DisplayName("CheckThatBotDeckHasSevenCardsAfterGameStart")
    public void CheckThatBotDeckHasSevenCardsAfterGameStart() {
        GameService gameService = new GameService();

        gameService.setTest(true);
        int expected = 7;
        int actual = gameService.getGame().getPlayerService().getPlayerList().getLast().getPlayerDeck().size();

        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("CheckFirstCardIsSet")
    public void CheckFirstCardIsSet() {
        GameService gameService = new GameService();
        gameService.setTest(true);
        Card card = gameService.getGame().getLastPlayedCard();

        boolean answer = card != null;

        assertTrue(answer);
    }

    @Test
    @DisplayName("CheckFirstCardIsNotBlack")
    public void CheckFirstCardIsNotBlack() {
        GameService gameService = new GameService();
        gameService.setTest(true);
        Card card = gameService.getGame().getLastPlayedCard();

        boolean answer = card.getColor() != Card.Color.BLACK;

        assertTrue(answer);
    }

    @Test
    @DisplayName("CheckResetOnBlackCards")
    public void CheckResetOnBlackCards() {
        GameService gameService = new GameService();
        gameService.setTest(true);
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

        gameService.setTest(true);
        int numberOfCards = gameService.getGame().getPlayerService().getPlayerList().getFirst().getPlayerDeck().size();

        assertEquals(7, numberOfCards);
    }

    @Test
    @DisplayName("CheckThatFirstCardGetsRemovedFromDrawCardDeck")
    public void CheckThatFirstCardGetsRemovedFromDrawCardDeck() {
        GameService gameService = new GameService();

        gameService.setTest(true);
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
        gameService.setTest(true);
        Card card = new Card().setColor(Card.Color.BLACK).setValue(Card.Value.CHOOSE);
        boolean answer = false;

        gameService.getGame().getPlayerService().getPlayerList().getLast().getPlayerDeck().clear();
        gameService.getGame().getPlayerService().getPlayerList().getLast().getPlayerDeck().add(card);

        gameService.getGame().getPlayerService().setCurrentTurn(false);
        gameService.getGame().getPlayerService().botMove();

        if (gameService.getGame().getLastPlayedCard().getColor() != Card.Color.BLACK){
            answer = true;
        }

        assertTrue(answer);
    }

    @Test
    @DisplayName("CheckIfTestWasInitializedWithFalse")
    public void CheckIfTestWasInitializedWithFalse() {
        GameService gameService = new GameService();

        assertFalse(gameService.getTest());
    }

    @Test
    @DisplayName("CheckIfTestCanBeSetToTrue")
    public void CheckIfTestCanBeSetToTrue() {
        GameService gameService = new GameService();

        gameService.setTest(true);

        assertTrue(gameService.getTest());
    }

    @Test
    @DisplayName("CheckIfBotMovesAutomaticallyAfterPlayer")
    public void CheckIfBotMovesAutomaticallyAfterPlayer() {
        GameService gameService = new GameService();

        Card card = new Card().setColor(Card.Color.BLUE).setValue(Card.Value.ONE);
        gameService.getGame().getPlayerService().getPlayerList().getLast().getPlayerDeck().add(new Card().setColor(Card.Color.BLUE).setValue(Card.Value.FIVE));

        gameService.playCard(card);

        boolean answer = gameService.getGame().getLastPlayedCard() != card;

        assertTrue(answer);
    }

    @Test
    @DisplayName("CheckIfCardsInDrawCardDeckAreFaceDown")
    public void CheckIfCardsInDrawCardDeckAreFaceDown() {
        GameService gameService = new GameService();

        for (int i = 0; i < gameService.getGame().getDrawCardDeck().size(); i++) {
            assertTrue(gameService.getGame().getDrawCardDeck().get(i).isFacedown());
        }
    }

    @Test
    @DisplayName("CheckIfCardsInPlayerDeckAreFaceUp")
    public void CheckIfCardsInPlayerDeckAreFaceUp() {
        GameService gameService = new GameService();

        for (int i = 0; i < gameService.getGame().getPlayerService().getPlayerList().getFirst().getPlayerDeck().size(); i++) {
            assertFalse(gameService.getGame().getPlayerService().getPlayerList().getFirst().getPlayerDeck().get(i).isFacedown());
        }
    }

    @Test
    @DisplayName("CheckIfCardsInBotDeckAreFaceDown")
    public void CheckIfCardsInBotDeckAreFaceDown() {
        GameService gameService = new GameService();

        for (int i = 0; i < gameService.getGame().getPlayerService().getPlayerList().getLast().getPlayerDeck().size(); i++) {
            assertTrue(gameService.getGame().getPlayerService().getPlayerList().getLast().getPlayerDeck().get(i).isFacedown());
        }
    }

    @Test
    @DisplayName("CheckIfCardsPlayedByBotWillTurnFaceUp")
    public void CheckIfCardsPlayedByBotWillTurnFaceUp() {
        GameService gameService = new GameService();

        Card card = new Card().setColor(Card.Color.GREEN).setValue(Card.Value.FOUR).setFacedown(true);

        gameService.getGame().getPlayerService().setCurrentTurn(false);
        gameService.getGame().setLastPlayedCard(new Card().setColor(Card.Color.GREEN).setValue(Card.Value.FIVE));
        gameService.getGame().getPlayerService().getPlayerList().getLast().getPlayerDeck().clear();
        gameService.getGame().getPlayerService().getPlayerList().getLast().getPlayerDeck().add(card);

        gameService.getGame().getPlayerService().botMove();

        assertFalse(gameService.getGame().getLastPlayedCard().isFacedown());
    }

    @Test
    @DisplayName("CheckIfCardsMovedToDrawCardDeckWillBeFaceDown")
    public void CheckIfCardsMovedToDrawCardDeckWillBeFaceDown() {
        GameService gameService = new GameService();

        Card card = new Card().setColor(Card.Color.GREEN).setValue(Card.Value.FOUR);

        gameService.getGame().setLastPlayedCard(card);
        gameService.addLastPlayedCardToDrawCardDeck();

        assertTrue(gameService.getGame().getDrawCardDeck().getLast().isFacedown());
    }

    @DisplayName("PlaySevenPlayerDeckIsNowBotDeck")
    public void PlaySevenPlayerDeckIsNowBotDeck() {
        GameService gameService = new GameService();
        ArrayList<Card> botDeck = new ArrayList<>(gameService.getGame().getPlayerService().getPlayerList().getLast().getPlayerDeck());
        gameService.playSeven();
        ArrayList<Card> answer = new ArrayList<>(gameService.getGame().getPlayerService().getPlayerList().getFirst().getPlayerDeck());
        assertEquals(botDeck, answer);
    }

    @Test
    @DisplayName("PlaySevenBotDeckIsNowPlayerDeck")
    public void PlaySevenBotDeckIsNowPlayerDeck() {
        GameService gameService = new GameService();
        ArrayList<Card> playerDeck = new ArrayList<>(gameService.getGame().getPlayerService().getPlayerList().getFirst().getPlayerDeck());
        gameService.playSeven();
        ArrayList<Card> answer = new ArrayList<>(gameService.getGame().getPlayerService().getPlayerList().getLast().getPlayerDeck());
        assertEquals(playerDeck, answer);
    }

    @Test
    @DisplayName("BotisEnemy")
    public void botIsEnemy() {
        GameService gameService = new GameService();
        boolean answer = gameService.getGame().getPlayerService().getPlayerList().getLast().isEnemy();
        boolean expected = true;
        assertEquals(expected, answer);
    }
}