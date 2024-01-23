package de.hsfulda.onses.models;

public class Card {
    private int id;
    private Color color;
    private evalue value;

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

    public Card setId(int id) {
        this.id = id;
        return this;
    }

    public Card setColor(Color color) {
        this.color = color;
        return this;
    }

    public Card setValue(evalue value) {
        this.value = value;
        return this;
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
