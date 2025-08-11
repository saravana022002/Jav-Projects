package SDE.DailyChallenge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class RangeProductQueries {
    public static void main(String[] args) {
       int n = 2;
       int[][] queries = {{0,0}};
        System.out.println(Arrays.toString(productQueries(n, queries)));
    }
    public static int[] productQueries(int n, int[][] queries) {
        final long MOD = 1_000_000_007;
        List<Integer> powers = new ArrayList<>();
        while (n > 0){
            powers.add(n % 2);
            n /= 2;
        }
        int starter = 1;
        for (int i = 0; i < powers.size(); i++) {
            Integer power = powers.get(i) * starter;
            starter *= 2;
            powers.set(i, power);
        }
        powers.removeIf(num -> num == 0);

        int[] answers = new int[queries.length];

        for (int i = 0; i < queries.length; i++){
            int left = queries[i][0];
            int right = queries[i][1];
            long answer = 1;
            for(int j = left; j <= right; j++){
                answer = (answer * powers.get(j)) % MOD;
            }
            answers[i] = (int) answer;
        }

        return answers;
    }
}
