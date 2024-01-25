package de.hsfulda.onses;

import de.hsfulda.onses.models.Card;
import de.hsfulda.onses.models.Game;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class GameTest {
    @Test
    @DisplayName("lastPlayedCard")
    public void lastPlayedCard() {
        Game game = new Game();
        Card card = new Card().setColor(Card.Color.RED).setValue(Card.Value.EIGHT);
        Card answer = card;

        game.setLastPlayedCard(card);

        assertEquals(game.getLastPlayedCard(), answer);
    }

    @Test
    @DisplayName("GetDrawCardDeck")
    public void GetDrawCardDeck() {
        Game game = new Game();
        Card card = new Card().setColor(Card.Color.RED).setValue(Card.Value.EIGHT);
        Card answer = card;

        game.addCardToDrawCardDeck(card);

        assertEquals(game.getDrawCardDeck().getFirst(), answer);
    }
}
