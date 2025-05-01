public class Factorial {

    private static final int mod = 1000003;

    public static int factorialUsingLoop(int n) {
        int fact = 1;
        for(int i = 1; i <= n; i++) {
            fact *= i;
            fact %= mod;
        }
        return fact;
    }

    public static int factorialUsingRecursion(int n) {
        if(n <= 1) return 1;
        return ((n % mod) * (factorialUsingRecursion(n - 1) % mod)) % mod;
    }
}
