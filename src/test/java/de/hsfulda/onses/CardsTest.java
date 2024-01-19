
package de.hsfulda.onses;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import de.hsfulda.onses.models.Cards;

public class CardsTest {

    @Test
    @DisplayName("CardGetID5")
    public void CardTestGetID5() {

        Cards testcard = new Cards(5, Cards.ecolor.BLACK, Cards.evalue.FIVE);
        assertEquals(5, testcard.getId());
    }
    @Test
    @DisplayName("CardGetID112")
    public void CardTestGetID112() {

        Cards testcard = new Cards(112, Cards.ecolor.BLUE, Cards.evalue.CHOOSEDRAW);
        assertEquals(112, testcard.getId());
    }


    @Test
    @DisplayName("CardGetColorBlue")
    public void CardTestGetColorBlue() {

        Cards testcard = new Cards(9, Cards.ecolor.BLUE, Cards.evalue.FIVE);
        assertEquals(Cards.ecolor.BLUE, testcard.getColor());
    }
    @Test
    @DisplayName("CardGetColorBlack")
    public void CardTestGetColorBlack() {

        Cards testcard = new Cards(15, Cards.ecolor.BLACK, Cards.evalue.CHOOSE);
        assertEquals(Cards.ecolor.BLACK, testcard.getColor());
    }


    @Test
    @DisplayName("CardGetValueThree")
    public void CardTestGetValueThree() {

        Cards testcard = new Cards(2, Cards.ecolor.BLUE, Cards.evalue.THREE);
        assertEquals(Cards.evalue.THREE, testcard.getValue());
    }
    @Test
    @DisplayName("CardGetValueSkip")
    public void CardTestGetValueSkip() {

        Cards testcard = new Cards(19, Cards.ecolor.BLACK, Cards.evalue.SKIP);
        assertEquals(Cards.evalue.SKIP, testcard.getValue());
    }

}