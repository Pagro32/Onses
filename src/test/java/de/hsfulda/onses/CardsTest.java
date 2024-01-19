
package de.hsfulda.onses;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import de.hsfulda.onses.models.Cards;

public class CardsTest {

    @Test
    @DisplayName("CardGetID5")
    public void CardTestGetID5() {

        // arrange
        int expected = 5;
        int ID = 5;
        // act
        Cards testcard = new Cards(ID, Cards.ecolor.BLACK, Cards.evalue.FIVE);
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
        Cards testcard = new Cards(ID, Cards.ecolor.BLUE, Cards.evalue.CHOOSEDRAW);
        int answer = testcard.getId();
        // assert
        assertEquals(expected, answer);
    }


    @Test
    @DisplayName("CardGetColorBlue")
    public void CardTestGetColorBlue() {

        // arrange
        Cards.ecolor expected = Cards.ecolor.BLUE;
        Cards.ecolor color = Cards.ecolor.BLUE;
        // act
        Cards testcard = new Cards(9, color, Cards.evalue.FIVE);
        Cards.ecolor answer = testcard.getColor();
        // assert
        assertEquals(expected, answer);
    }
    @Test
    @DisplayName("CardGetColorBlack")
    public void CardTestGetColorBlack() {

        // arrange
        Cards.ecolor expected = Cards.ecolor.BLACK;
        Cards.ecolor color = Cards.ecolor.BLACK;
        // act
        Cards testcard = new Cards(15, color, Cards.evalue.CHOOSE);
        Cards.ecolor answer = testcard.getColor();
        // assert
        assertEquals(expected, answer);
    }


    @Test
    @DisplayName("CardGetValueThree")
    public void CardTestGetValueThree() {

        // arrange
        Cards.evalue expected = Cards.evalue.THREE;
        Cards.evalue value = Cards.evalue.THREE;
        // act
        Cards testcard = new Cards(2, Cards.ecolor.BLUE, value);
        Cards.evalue answer = testcard.getValue();
        // assert
        assertEquals(expected, answer);
    }
    @Test
    @DisplayName("CardGetValueSkip")
    public void CardTestGetValueSkip() {

        // arrange
        Cards.evalue expected = Cards.evalue.SKIP;
        Cards.evalue value = Cards.evalue.SKIP;
        // act
        Cards testcard = new Cards(19, Cards.ecolor.BLACK, value);
        Cards.evalue answer = testcard.getValue();
        // assert
        assertEquals(expected, answer);
    }

}