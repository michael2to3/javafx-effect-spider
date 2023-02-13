package javafx.effect.spider;

class Random {
    public static double getRange(final double a, final double b) {
        var diff = b - a + 1;
        return Math.random() * diff + a;
    }
}
