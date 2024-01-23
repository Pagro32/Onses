
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
        Card testcard = new Card(ID, Card.ecolor.BLACK, Card.evalue.FIVE);
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
        Card testcard = new Card(ID, Card.ecolor.BLUE, Card.evalue.CHOOSEDRAW);
        int answer = testcard.getId();
        // assert
        assertEquals(expected, answer);
    }


    @Test
    @DisplayName("CardGetColorBlue")
    public void CardTestGetColorBlue() {

        // arrange
        Card.ecolor expected = Card.ecolor.BLUE;
        Card.ecolor color = Card.ecolor.BLUE;
        // act
        Card testcard = new Card(9, color, Card.evalue.FIVE);
        Card.ecolor answer = testcard.getColor();
        // assert
        assertEquals(expected, answer);
    }
    @Test
    @DisplayName("CardGetColorBlack")
    public void CardTestGetColorBlack() {

        // arrange
        Card.ecolor expected = Card.ecolor.BLACK;
        Card.ecolor color = Card.ecolor.BLACK;
        // act
        Card testcard = new Card(15, color, Card.evalue.CHOOSE);
        Card.ecolor answer = testcard.getColor();
        // assert
        assertEquals(expected, answer);
    }


    @Test
    @DisplayName("CardGetValueThree")
    public void CardTestGetValueThree() {

        // arrange
        Card.evalue expected = Card.evalue.THREE;
        Card.evalue value = Card.evalue.THREE;
        // act
        Card testcard = new Card(2, Card.ecolor.BLUE, value);
        Card.evalue answer = testcard.getValue();
        // assert
        assertEquals(expected, answer);
    }
    @Test
    @DisplayName("CardGetValueSkip")
    public void CardTestGetValueSkip() {

        // arrange
        Card.evalue expected = Card.evalue.SKIP;
        Card.evalue value = Card.evalue.SKIP;
        // act
        Card testcard = new Card(19, Card.ecolor.BLACK, value);
        Card.evalue answer = testcard.getValue();
        // assert
        assertEquals(expected, answer);
    }

}