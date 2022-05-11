public class LCM {
    public static int lcm(int x, int y) {
        int gcd = 1;
        for(int i = 1; i <= x && i <= y; i++) {
            if(x % i == 0 && y % i == 0) gcd = i;
        }
        int lcm = (x * y) / gcd;
        return lcm;
    }
}