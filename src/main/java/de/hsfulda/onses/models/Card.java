package de.hsfulda.onses.models;

public class Card {

        public enum ecolor {
            RED, BLUE, GREEN, YELLOW, BLACK;

            private static final ecolor[] colors = ecolor.values();
            public static ecolor getColors (int n)
            {
                return ecolor.colors[n];
            }
        };
        public enum evalue {
            ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, DRAWTWO, SKIP, REVERSE, CHOOSE, CHOOSEDRAW;

            private static final evalue[] values = evalue.values();
            public static evalue getValue(int n) {
                return evalue.values[n];
            }
        };

        private final int id;
        private final ecolor color;
        private final evalue value;


    public Card(int id, ecolor color, evalue value)
    {
        this.id = id;
        this.color = color;
        this.value = value;
    }


    public int getId() {
        return this.id;
    }

    public ecolor getColor() {
        return this.color;
    }

    public evalue getValue() {
        return this.value;
    }
}
