
package de.hsfulda.onses;

import de.hsfulda.onses.models.Game;
import de.hsfulda.onses.services.PlayerService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import de.hsfulda.onses.models.Card;
import de.hsfulda.onses.models.Player;

import java.util.ArrayList;

public class PlayerTest {

    @Test
    @DisplayName("AddCardToPlayerDeck")
    public void AddCardToPlayerDeck() {

        // arrange
        Player player = new Player();
        Card testCard = new Card().setColor(Card.Color.BLACK).setValue(Card.Value.FIVE);
        // act
        player.addCardToPlayerDeck(testCard);
        Card firstCardPlayerDeck = player.getPlayerDeck().getFirst();
        // assert
        assertEquals(testCard, firstCardPlayerDeck);
    }

    @Test
    @DisplayName("AddMultipleCardsToPlayerDeck")
    public void AddMultipleCardsToPlayerDeck() {

        // arrange
        Player player = new Player();
        Card testCard1 = new Card().setColor(Card.Color.BLACK).setValue(Card.Value.FIVE);
        Card testCard2 = new Card().setColor(Card.Color.YELLOW).setValue(Card.Value.FIVE);
        // act
        player.addCardToPlayerDeck(testCard1);
        player.addCardToPlayerDeck(testCard2);
        Card firstCardPlayerDeck = player.getPlayerDeck().getFirst();
        Card secondCardPlayerDeck = player.getPlayerDeck().get(1);
        // assert
        assertEquals(testCard1, firstCardPlayerDeck);
        assertEquals(testCard2, secondCardPlayerDeck);
    }
    @Test
    @DisplayName("Relationship_Player_Game")
    void Relationship_Player_Game() {
        // arrange
        PlayerService input = new PlayerService();
        Game expected = input.getGame();
        // act
        Game answer1 = input.getPlayerList().getFirst().getPlayerService().getGame();
        Game answer2 = input.getPlayerList().getLast().getPlayerService().getGame();
        // assert
        assertEquals(expected, answer1);
        assertEquals(expected, answer2);
    }

    @Test
    @DisplayName("remove card from player card deck")
    void removeCardFromPlayerCardDeck() {
        Player player = new Player();
        Card card1 = new Card().setColor(Card.Color.RED).setValue(Card.Value.FIVE);
        Card card2 = new Card().setColor(Card.Color.GREEN).setValue(Card.Value.ONE);

        player.addCardToPlayerDeck(card1);
        player.addCardToPlayerDeck(card2);

        assertEquals(2, player.getPlayerDeck().size());

        player.removeCardFromPlayerDeck(card1);
        assertEquals(1, player.getPlayerDeck().size());
        assertEquals(card2, player.getPlayerDeck().getFirst());
    }
}
