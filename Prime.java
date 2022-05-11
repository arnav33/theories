import java.util.ArrayList;

public class Prime {

    public static boolean isPrime(int num) {
        boolean ans = true;
        for(int i = 2; i <= (int)Math.sqrt(num); i++) {
            if(num % i == 0) {
                ans = false;
                break;
            }
        }
        return ans;
    }

    public static ArrayList<Integer> getPrimeNumbers(int max) {
        final boolean isNumberAPrime[] = new boolean[(int)(1e6+1)];
        final ArrayList<Integer> primeList = new ArrayList<>();
        for(int i = 2; i <= max; i++) {
            isNumberAPrime[i] = true;
        }
        for(int i = 2; i <= max; i++) {
            if(isNumberAPrime[i]) {
                for(int j = 2 * i; j <= max; j += i) {
                    isNumberAPrime[j] = false;
                }
            }
        }
        for(int i = 2; i <= max; i++) {
            if(isNumberAPrime[i]) primeList.add(i);
        }
        return primeList;
    }
}
