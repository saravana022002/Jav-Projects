package Practice.Math;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

// ============================================================================
// LC 3289. The Two Sneaky Numbers of Digitville   [Easy]
// https://leetcode.com/problems/the-two-sneaky-numbers-of-digitville/
// Topics: Array, Hash Table, Math
// ============================================================================
//
// PROBLEM
//   nums should contain each of 0, 1, ..., n - 1 exactly once, but two different
//   values were added a second time, so nums has n + 2 elements. Return those two
//   duplicated values, in any order.
//
// EXAMPLES
//   nums = [0,1,1,0]                   ->  [0,1]
//   nums = [0,3,2,1,3,2]               ->  [2,3]
//   nums = [7,1,5,4,3,4,6,0,9,5,8,2]   ->  [4,5]    (main() prints [5, 4] - order is free)
//
// CONSTRAINTS
//   2 <= n <= 100,   nums.length == n + 2,   0 <= nums[i] < n
//   exactly two values are repeated
//
// INTUITION
//   Hash map: count as you go; a value is sneaky the moment its count hits 2.
//   Math (no extra memory): compare nums with the perfect list 0..n-1. The extra
//   copies a and b are exactly what is left over:
//     actual sum - perfect sum                 = a + b          (call it X)
//     actual squares - perfect squares         = a^2 + b^2      (call it Y)
//   Then 2Y - X^2 = 2a^2 + 2b^2 - (a + b)^2 = (a - b)^2, so Z = sqrt(2Y - X^2) = |a - b|
//   and a, b = (X + Z) / 2, (X - Z) / 2.
//
// APPROACHES IN THIS FILE
//   A. getSneakyNumbers()  - HashMap frequency count, stop after the 2nd duplicate
//                            - O(n) time, O(n) space
//   B. getSneakyNumbers1() - sum and sum of squares, then solve for a and b
//                            - O(n) time, O(1) space   <- main() runs this one
//
// APPROACH (as implemented below, method B)
//   1. N = nums.length, n = N - 2 (the values are 0..n-1).
//   2. One pass: S_actual = sum, S2_actual = sum of squares (long).
//   3. Perfect sums of 0..n-1: n_sum = n(n-1)/2,  n_sum2 = n(n-1)(2n-1)/6.
//   4. X = S_actual - n_sum (= a + b),  Y = S2_actual - n_sum2 (= a^2 + b^2).
//   5. zSquared = 2*Y - X*X (= (a - b)^2),  Z = round(sqrt(zSquared)) (= |a - b|).
//   6. A = (X + Z) / 2 (the larger one), B = (X - Z) / 2 (the smaller one). Return [A, B].
//
// DRY RUN   nums = [7,1,5,4,3,4,6,0,9,5,8,2]   (the input main() uses)
//   N = 12, n = 10
//   S_actual = 54,   S2_actual = 326
//   n_sum = 10*9/2 = 45,   n_sum2 = 10*9*19/6 = 285
//   X = 54 - 45 = 9 (4 + 5),   Y = 326 - 285 = 41 (16 + 25)
//   zSquared = 2*41 - 81 = 1  -> Z = 1
//   A = (9 + 1) / 2 = 5,  B = (9 - 1) / 2 = 4   -> prints [5, 4]
//
// COMPLEXITY
//   Time : O(n) for both methods - one pass over nums
//   Space: A: O(n) for the map;  B: O(1), a few longs
//
// GOTCHAS
//   - The values start at 0, so the perfect sums run over 0..n-1: n(n-1)/2, not
//     n(n+1)/2, with n = nums.length - 2.
//   - zSquared is a perfect square; Math.round protects against a sqrt result like
//     2.9999999 being cut down to 2.
//   - Output order differs: A reports values in the order their second copy shows up,
//     B returns [larger, smaller]. Both are accepted.
//
// RELATED
//   - LC 2965 Find Missing and Repeated Values - the same sum / sum-of-squares algebra,
//     with one value missing and one repeated (in this repo).
//   - LC 442 Find All Duplicates in an Array - find every value that appears twice.
// ============================================================================
public class LC3289TheTwoSneakyNumbersOfDigitville {
    public static void main(String[] args) {
        // Example 3 - prints [5, 4] (the official answer [4,5] in the other order)
        int[] nums = {7,1,5,4,3,4,6,0,9,5,8,2};
        System.out.println(Arrays.toString(getSneakyNumbers1(nums)));
    }

    // Approach A: count occurrences; a value whose count reaches 2 is a sneaky number.
    // O(n) time, O(n) space. Not called by main().
    public static int[] getSneakyNumbers(int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        int[] ans = new int[2];
        int count = 0;
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);

            // second sighting -> record it; both found -> stop early
            if (freqMap.get(num) == 2) {
                ans[count] = num;
                count++;
                if (count == 2) {
                    break;
                }
            }
        }
        return ans;
    }

    // Approach B: a + b and a^2 + b^2 from the gap to the perfect 0..n-1 list, then solve.
    // O(n) time, O(1) space. Returns [larger, smaller].
    public static int[] getSneakyNumbers1(int[] nums) {
        // the list has n + 2 slots for the values 0..n-1
        long N = nums.length;
        long n = N - 2;

        long S_actual = 0;
        long S2_actual = 0;
        for (int num : nums) {
            S_actual += num;
            S2_actual += (long)num * num;
        }
        // 0 + 1 + ... + (n - 1)
        long n_sum = (n * (n - 1)) / 2;


        // 0^2 + 1^2 + ... + (n - 1)^2
        long n_sum2 = (n * (n - 1) * (2 * n - 1)) / 6;

        // X = a + b,  Y = a^2 + b^2
        long X = S_actual - n_sum ;
        long Y = S2_actual - n_sum2;


        // 2(a^2 + b^2) - (a + b)^2 = (a - b)^2
        long zSquared = 2*Y - X*X;

        // Z = |a - b|; round() guards against sqrt landing just below an integer
        long Z = Math.round(Math.sqrt(zSquared));


        // from a + b and a - b: the larger and the smaller duplicate
        int A = (int) ((X + Z)/2);
        int B = (int) ((X - Z)/2);

        return new int[]{A, B};
    }


    // unused helper - not part of either solution
    public static boolean isNegative(int n) {
        return n < 0;
    }
}
