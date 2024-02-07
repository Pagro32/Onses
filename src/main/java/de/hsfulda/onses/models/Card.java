package de.hsfulda.onses.models;

import java.beans.PropertyChangeSupport;

public class Card {

    public final static String PROPERTY_COLOR = "color";
    public final static String PROPERTY_VALUE = "value";

    protected PropertyChangeSupport listeners;

    private Color color;
    private Value value;
    private boolean facedown = false;

    public enum Color {
        RED, BLUE, GREEN, YELLOW, BLACK;
    };
    public enum Value {
        ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, DRAWTWO, SKIP, REVERSE, CHOOSE, CHOOSEDRAW;
    };

    public Card setColor(Color color) {
        final Card.Color oldColor = this.color;
        this.color = color;
        this.firePropertyChange(PROPERTY_COLOR, oldColor, color);
        return this;
    }

    public Card setValue(Value value) {
        final Card.Value oldValue = this.value;
        this.value = value;
        this.firePropertyChange(PROPERTY_VALUE, oldValue, value);
        return this;
    }

    public Color getColor() {
        return this.color;
    }

    public Value getValue() {
        return this.value;
    }

    public boolean isFacedown() {
        return facedown;
    }

    public Card setFacedown(boolean facedown) {
        this.facedown = facedown;
        return this;
    }

    public PropertyChangeSupport listeners() {
        if(this.listeners == null) {
            this.listeners = new PropertyChangeSupport(this);
        }
        return this.listeners;
    }

    public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue) {
        if (this.listeners != null) {
            this.listeners.firePropertyChange(propertyName, oldValue, newValue);
            return true;
        }
        return false;
    }
}
