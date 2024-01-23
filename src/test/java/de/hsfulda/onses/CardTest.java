
package de.hsfulda.onses;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import de.hsfulda.onses.models.Card;

public class CardTest {

    @Test
    @DisplayName("CardGetID5")
    public void CardTestGetID5() {

        // arrange
        int expected = 5;
        int ID = 5;
        // act
        Card testcard = new Card().setId(ID).setColor(Card.Color.BLACK).setValue(Card.Value.FIVE);
        int answer = testcard.getId();
        // assert
        assertEquals(expected, answer);
    }

    @Test
    @DisplayName("CardGetID112")
    public void CardTestGetID112() {

        // arrange
        int expected = 112;
        int ID = 112;
        // act
        Card testcard = new Card().setId(ID).setColor(Card.Color.BLUE).setValue(Card.Value.CHOOSEDRAW);
        int answer = testcard.getId();
        // assert
        assertEquals(expected, answer);
    }


    @Test
    @DisplayName("CardGetColorBlue")
    public void CardTestGetColorBlue() {

        // arrange
        Card.Color expected = Card.Color.BLUE;
        Card.Color color = Card.Color.BLUE;
        // act
        Card testcard = new Card().setId(9).setColor(color).setValue(Card.Value.FIVE);
        Card.Color answer = testcard.getColor();
        // assert
        assertEquals(expected, answer);
    }
    @Test
    @DisplayName("CardGetColorBlack")
    public void CardTestGetColorBlack() {

        // arrange
        Card.Color expected = Card.Color.BLACK;
        Card.Color color = Card.Color.BLACK;
        // act
        Card testcard = new Card().setId(15).setColor(color).setValue(Card.Value.CHOOSE);
        Card.Color answer = testcard.getColor();
        // assert
        assertEquals(expected, answer);
    }


    @Test
    @DisplayName("CardGetValueThree")
    public void CardTestGetValueThree() {

        // arrange
        Card.Value expected = Card.Value.THREE;
        Card.Value value = Card.Value.THREE;
        // act
        Card testcard = new Card().setId(2).setColor(Card.Color.BLUE).setValue(value);
        Card.Value answer = testcard.getValue();
        // assert
        assertEquals(expected, answer);
    }
    @Test
    @DisplayName("CardGetValueSkip")
    public void CardTestGetValueSkip() {

        // arrange
        Card.Value expected = Card.Value.SKIP;
        Card.Value value = Card.Value.SKIP;
        // act
        Card testcard = new Card().setId(19).setColor(Card.Color.BLACK).setValue(value);
        Card.Value answer = testcard.getValue();
        // assert
        assertEquals(expected, answer);
    }

}