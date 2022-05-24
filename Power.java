public class Power {
    private static final long mod = (long) (1e9 + 7);

    public static long fastPower(long base, long exponent) {
        long ans = 1;
        while(exponent> 0) {
            if(exponent% 2 == 1) ans = ans * base % mod;
            base = (base * base) % mod;
            exponent/= 2;
        }
        return ans;
    }

    static float negativeFastpower(float x, int y) {
        float temp;
        if(y == 0) return 1;
        temp = negativeFastpower(x, y/2); 
        if (y%2 == 0) return temp * temp;
        else {
            if(y > 0) return x * temp * temp;
             else return (temp * temp) / x;
        }
    }
 
}
