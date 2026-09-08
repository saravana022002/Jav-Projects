package Leetcode.ArithMetic;

public class LC204CountPrimes {
    public static void main(String[] args) {
        System.out.println(countPrimes(5000000));
    }

    public static int countPrimes(int n) {
        int count = 0;
        for (int i = 2; i < n; i++) {
            boolean isPrime = true;
            if(i != 2 && i % 2 == 0){
                isPrime = false;
            }else {
                for (int j = 3; j * j <= i; j += 2) {
                    if (i % j == 0) {
                        isPrime = false;
                        break;
                    }
                }
            }
            if(isPrime){
                count++;
            }
        }
        return count;
    }
}
