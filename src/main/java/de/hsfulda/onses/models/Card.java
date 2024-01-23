package de.hsfulda.onses.models;

public class Card {
    private int id;
    private Color color;
    private Value value;

    public enum Color {
        RED, BLUE, GREEN, YELLOW, BLACK;
    };
    public enum Value {
        ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, DRAWTWO, SKIP, REVERSE, CHOOSE, CHOOSEDRAW;
    };

    public Card setId(int id) {
        this.id = id;
        return this;
    }

    public Card setColor(Color color) {
        this.color = color;
        return this;
    }

    public Card setValue(Value value) {
        this.value = value;
        return this;
    }

    public int getId() {
        return this.id;
    }

    public Color getColor() {
        return this.color;
    }

    public Value getValue() {
        return this.value;
    }
}
