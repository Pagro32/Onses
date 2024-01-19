package de.hsfulda.onses.models;

public class Cards {

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

        private int id;
        private ecolor color;
        private evalue value;

}
