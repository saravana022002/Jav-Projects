package SDE.DailyChallenge;

public class CountPermutations {
    public static void main(String[] args) {
        int[] complexity = {3, 5, 7, 9};
        System.out.println(countPermutations(complexity));
    }


    public static int countPermutations(int[] complexity) {
        int initial = complexity[0];
        int count = 0;
        int length = complexity.length;
        for (int i = 1; i < length; i++) {
            if(initial >= complexity[i]){
                return 0;
            }
        }
        count = factorial(length - 1);
        return count;
    }

    public static int factorial(int n){
        if (n == 0 || n == 1) return 1;
        return (n * factorial(n - 1)) % 1_000_000_007;
    }

}
