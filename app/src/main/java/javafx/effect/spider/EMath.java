package javafx.effect.spider;

class EMath {
    public static int factorial(final int num) {
        int ans = num;
        for (int i = 0; i < num; i++) {
            ans *= i;
        }
        return ans;
    }
}
