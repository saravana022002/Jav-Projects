package Leetcode.ArithMetic;

public class LC3658GCDofOddandEvenSums {
    public static void main(String[] args) {
        gcdOfOddEvenSums(8);
    }

    // Arithmetic series

    // Sn = n/2 *( 2 * a + (n - 1)*d)

    // for oddSum  =  n/2 * (2 + (2n - 2)) ==>  oddSum = n*n
    // for evenSum  =  n/2 * (4 + (2n - 2)) ==> evenSum = n*(n + 1)

    public static int gcdOfOddEvenSums(int n) {
        int oddSum = n * n;
        int evenSum = n * (n + 1);
        int temp;
        while(oddSum != 0){
            temp = evenSum;
            evenSum = oddSum;
            oddSum = temp % oddSum;
        }
        return evenSum;
    }
}
