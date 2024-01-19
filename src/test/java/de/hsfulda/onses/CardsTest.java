
package de.hsfulda.onses;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import de.hsfulda.onses.models.Cards;

public class CardsTest {

    @Test
    @DisplayName("ModelCardsClass")
    public void CardTestGetID5() {

        Cards testcard = new Cards(5, Cards.ecolor.BLACK, Cards.evalue.FIVE);
        assertEquals(5, testcard.getId());
    }
    @Test
    @DisplayName("ModelCardsClass")
    public void CardTestGetID112() {

        Cards testcard = new Cards(112, Cards.ecolor.BLUE, Cards.evalue.CHOOSEDRAW);
        assertEquals(112, testcard.getId());
    }


}