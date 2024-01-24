
package de.hsfulda.onses;

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
}
