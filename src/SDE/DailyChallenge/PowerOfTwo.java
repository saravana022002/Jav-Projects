package SDE.DailyChallenge;

public class PowerOfTwo {
    public static void main(String[] args) {
        int n = 8;
        System.out.println(isPowerOfTwo(n));
    }
    public static boolean isPowerOfTwo(int n) {
        if(n == 0){
            return false;
        }
        while(n > 0 && n % 2 == 0){
            n = n / 2;
        }
        return n == 1;
    }
}
