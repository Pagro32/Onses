package de.hsfulda.onses.models;

public class Card {

        public enum Color {
            RED, BLUE, GREEN, YELLOW, BLACK;
        };
        public enum evalue {
            ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, DRAWTWO, SKIP, REVERSE, CHOOSE, CHOOSEDRAW;

            private static final evalue[] values = evalue.values();
            public static evalue getValue(int n) {
                return evalue.values[n];
            }
        };

        private final int id;
        private final Color color;
        private final evalue value;


    public Card(int id, Color color, evalue value)
    {
        this.id = id;
        this.color = color;
        this.value = value;
    }


    public int getId() {
        return this.id;
    }

    public Color getColor() {
        return this.color;
    }

    public evalue getValue() {
        return this.value;
    }
}
