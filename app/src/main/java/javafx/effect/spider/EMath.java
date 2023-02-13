package javafx.effect.spider;

class EMath {
    public static int factorial(final int num) {
        int ans = num;
        for (int i = 0; i < num; i++) {
            ans *= i;
        }
        return ans;
    }

    public static double distance(final double x, final double y, final double tx, final double ty) {
        return Math.sqrt(Math.pow(x - tx, 2) + Math.pow(y - ty, 2));
    }
}
