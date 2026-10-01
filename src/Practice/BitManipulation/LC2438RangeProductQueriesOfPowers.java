package Practice.BitManipulation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// ============================================================================
// LC 2438. Range Product Queries of Powers   [Medium]
// https://leetcode.com/problems/range-product-queries-of-powers/
// Topics: Array, Bit Manipulation, Prefix Sum
// ============================================================================
//
// PROBLEM
//   Write the positive integer n as a sum of powers of 2 using as few terms as possible and
//   list those terms in increasing order - that list is called powers (it is unique).
//   Each query [left, right] asks for powers[left] * powers[left+1] * ... * powers[right].
//   Return one answer per query, each taken modulo 10^9 + 7.
//
// EXAMPLES
//   n = 15, queries = [[0,1],[2,2],[0,3]]  ->  [2,4,64]
//       15 = 1111 in binary, so powers = [1,2,4,8]: 1*2 = 2, 4 = 4, 1*2*4*8 = 64
//   n = 2, queries = [[0,0]]               ->  [2]      powers = [2]
//
// CONSTRAINTS
//   1 <= n <= 10^9,   1 <= queries.length <= 10^5
//   0 <= start_i <= end_i < powers.length     (start/end = the left/right of a query)
//
// INTUITION
//   The fewest powers of 2 that add up to n are exactly the 1-bits of n: bit i set means 2^i
//   is in the list, and reading the bits from the lowest one up gives them already sorted.
//   Since n <= 10^9 < 2^30, powers has at most 30 entries, so every query multiplies at most
//   30 numbers - a plain loop per query is cheap enough (the official hint says the same).
//
// APPROACH (as implemented below)
//   1. Peel off n's binary digits, lowest first: powers.add(n % 2); n /= 2.
//      Now powers.get(i) is bit i (0 or 1).
//   2. Turn each bit into its place value: powers[i] = bit * starter, starter = 1, 2, 4, ...
//   3. powers.removeIf(num -> num == 0) drops the 0-bits, leaving the sorted powers of 2.
//   4. For each query multiply powers[left..right] into the long answer, taking % MOD after
//      every factor, and store it in answers[i].
//
// DRY RUN   n = 2, queries = [[0,0]]   (what main() runs)
//   step 1   n=2: add 2%2 = 0, n=1;  n=1: add 1%2 = 1, n=0     powers = [0,1]
//   step 2   i=0: 0 * 1 = 0;  i=1: 1 * 2 = 2                   powers = [0,2]
//   step 3   drop the zero                                     powers = [2]
//   step 4   query [0,0]: answer = 1 * 2 % MOD = 2             answers = [2]
//
// COMPLEXITY
//   Time : O(log n + q * log n) - n has at most 30 bits, and each of the q queries
//          multiplies at most that many powers
//   Space: O(log n) for powers, plus O(q) for the answers array
//
// GOTCHAS
//   - answer is a long and gets % MOD after every multiplication: MOD and every power are
//     below 2^30, so one product stays below 2^60 and fits a long (it would overflow an int).
//   - starter reaches 2^30 after the last round of step 2 - still inside the int range.
//   - Prefix Sum in the topics: an O(1)-per-query alternative keeps prefix sums of the
//     exponents, because 2^a * 2^b = 2^(a+b). Not needed here with at most 30 powers.
// ============================================================================
public class LC2438RangeProductQueriesOfPowers {
    public static void main(String[] args) {
       // Example 2 - n = 2 gives powers = [2]. Expected output: [2]
       int n = 2;
       int[][] queries = {{0,0}};
        System.out.println(Arrays.toString(productQueries(n, queries)));
    }
    // Returns, for every [left, right] query, the product of powers[left..right] mod 10^9 + 7.
    public static int[] productQueries(int n, int[][] queries) {
        final long MOD = 1_000_000_007;
        // step 1: binary digits of n, lowest bit first -> powers.get(i) = bit i (0 or 1)
        List<Integer> powers = new ArrayList<>();
        while (n > 0){
            powers.add(n % 2);
            n /= 2;
        }
        // step 2: replace bit i by its place value bit * 2^i (starter holds 2^i)
        int starter = 1;
        for (int i = 0; i < powers.size(); i++) {
            Integer power = powers.get(i) * starter;
            starter *= 2;
            powers.set(i, power);
        }
        // step 3: drop the 0-bits; the rest are the powers of 2 of n, already sorted
        powers.removeIf(num -> num == 0);

        int[] answers = new int[queries.length];

        // step 4: multiply each range directly - at most 30 factors per query
        for (int i = 0; i < queries.length; i++){
            int left = queries[i][0];
            int right = queries[i][1];
            long answer = 1;
            for(int j = left; j <= right; j++){
                // long + mod after every factor keeps the product from overflowing
                answer = (answer * powers.get(j)) % MOD;
            }
            answers[i] = (int) answer;
        }

        return answers;
    }
}
