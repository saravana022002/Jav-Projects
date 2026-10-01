package Leetcode.Ch01ComplexityAmortisationAndCounting;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 992. Subarrays with K Different Integers   [Hard]
 * https://leetcode.com/problems/subarrays-with-k-different-integers/
 * Topics: Array, Hash Table, Sliding Window, Counting
 * Book: Algorithmic Thinking - ch. 1 Complexity, amortisation, and counting, Practice #7
 *       also: ch. 9 Sliding window, chasing (#11)
 *
 * PROBLEM
 *   Count the subarrays (contiguous parts) of nums that contain EXACTLY k different values.
 *   Values are compared by value, not by position: [1,2,1,2] has 2 different integers.
 *
 * EXAMPLES
 *   nums = [1,2,1,2,3], k = 2  ->  7   [1,2] [2,1] [1,2] [2,3] [1,2,1] [2,1,2] [1,2,1,2]
 *   nums = [1,2,1,3,4], k = 3  ->  3   [1,2,1,3] [2,1,3] [1,3,4]
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 2 * 10^4,   1 <= nums[i], k <= nums.length
 *
 * INTUITION
 *   "Exactly k" is awkward for a sliding window: shrinking a window that has k different
 *   values can drop it to k - 1, so the window is not monotone. "At most k" is easy: if
 *   nums[l..r] has at most k different values, so does every part of it. So for each right
 *   end r the good starts form a block l..r, and that block contributes r - l + 1 subarrays.
 *   Then
 *        exactly k  =  (at most k)  -  (at most k - 1)
 *   because subtracting the subarrays with fewer than k different values leaves exactly k.
 *
 * APPROACHES IN THIS FILE
 *   A. subarraysWithKDistinct()    - brute force: for every start l, grow r with a fresh
 *                                    HashMap of counts and count the windows with exactly k
 *                                    different values; stop when there are more - O(n^2)
 *   B. subarraysWithKDistinctOPT() - atMost(k) - atMost(k - 1) with the helper
 *                                    subarraysWithAtmostK - O(n)     <- main() runs this one
 *
 * APPROACH (as implemented below, method B)
 *   subarraysWithAtmostK(nums, k):
 *   1. If k <= 0 return 0 (no non-empty subarray has 0 different values).
 *   2. size[v] = how many times v is inside the window nums[l..r]. The array has
 *      nums.length + 1 slots because the values are 1..nums.length. distinct = how many v
 *      have size[v] > 0. target = running total.
 *   3. For each r: add nums[r] (distinct++ if it was not in the window yet).
 *   4. While distinct > k: remove nums[l] (distinct-- when its count drops to 0), l++.
 *   5. Every start in l..r now gives a window with at most k values: target += r - l + 1.
 *   subarraysWithKDistinctOPT returns subarraysWithAtmostK(nums, k)
 *                                   - subarraysWithAtmostK(nums, k - 1).
 *
 * DRY RUN   nums = [1,2,1,3,4], k = 3 (the input main() runs)
 *   atMost(3):  r  added  distinct  window (after shrinking)   += r - l + 1   target
 *               0  1      1         [1]                         1               1
 *               1  2      2         [1,2]                       2               3
 *               2  1      2         [1,2,1]                     3               6
 *               3  3      3         [1,2,1,3]                   4               10
 *               4  4      4 > 3     drop 1, drop 2 -> [1,3,4]   3               13
 *   atMost(2):  r=0 [1] +1 = 1;  r=1 [1,2] +2 = 3;  r=2 [1,2,1] +3 = 6;
 *               r=3 adds 3 -> distinct 3 > 2, drop 1, drop 2 -> [1,3] +2 = 8;
 *               r=4 adds 4 -> distinct 3 > 2, drop 1 -> [3,4] +2 = 10
 *   answer = 13 - 10 = 3
 *
 * COMPLEXITY
 *   A: Time O(n^2) - worst case e.g. all values equal with k = 1: every start runs to the
 *      end. Space O(k) - the map holds at most k + 1 different values.
 *   B: Time O(n) - each call moves l and r only forward, so at most 2n steps; two calls.
 *      Space O(n) - the count array has nums.length + 1 slots.
 *
 * GOTCHAS
 *   - Exactly k = at most k MINUS at most k - 1 (not plus, and not at-most-k alone).
 *   - For k = 1 the second call is atMost(0), which returns 0 straight away.
 *   - The count array is indexed by the value itself, which is only safe because the
 *     constraints keep every value in 1..nums.length (a value larger than that would throw
 *     ArrayIndexOutOfBoundsException).
 *   - The result fits in an int: at most n * (n + 1) / 2 = 200010000 for n = 2 * 10^4.
 *   - In method A, once map.size() exceeds k the loop breaks: extending the window can never
 *     bring the number of different values back down.
 *
 * STATUS
 *   Both methods are correct (checked against a brute force on random arrays).
 *   main() only runs the optimised version, subarraysWithKDistinctOPT.
 *
 * RELATED
 *   - LC 3 Longest Substring Without Repeating Characters (same chapter) - a window kept
 *     valid by counts of what is inside it, there with "no value twice".
 *   - LC 2799 Count Complete Subarrays in an Array - counts subarrays by how many different
 *     values they hold.
 * </pre>
 */
public class LC992SubarraysWithKDifferentIntegers {

    public static void main(String[] args) {
        // Example 2: prints 3 ([1,2,1,3], [2,1,3] and [1,3,4])
        int[] nums = {1,2,1,3,4};
        int k = 3;
        System.out.println(subarraysWithKDistinctOPT(nums, k));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }

    // Approach A (brute force): every start l, grow r, count windows with exactly k values. O(n^2)
    public static int subarraysWithKDistinct(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int l = 0; l < nums.length; l++) {
            // fresh counts for every new start
            map = new HashMap<>();
            for (int r = l; r < nums.length; r++) {
                map.put(nums[r] , map.getOrDefault(nums[r], 0) + 1);
                if(map.size() < k){
                    // fewer than k different values so far - keep extending
                    continue;
                }else if(map.size() == k){
                    // exactly k different values: nums[l..r] is a good subarray
                    ans++;
                }else {
                    // more than k: extending further can only add values - next start
                    break;
                }
            }
        }
        return ans;
    }



    // Approach B (optimal): exactly k = (at most k) - (at most k - 1). O(n)
    public static int subarraysWithKDistinctOPT(int[] nums, int k) {
        return subarraysWithAtmostK(nums, k) - subarraysWithAtmostK(nums, k - 1);
    }

    // Counts the subarrays with AT MOST k different integers (sliding window). O(n)
    public static int subarraysWithAtmostK(int[] nums, int k){
        // no non-empty subarray has 0 (or fewer) different integers
        if(k <= 0){
            return 0;
        }
        // size[v] = how many times v is in the window; values are 1..n, so n + 1 slots
        int[] size = new int[nums.length + 1];
        // number of different integers inside nums[l..r]
        int distinct = 0;
        int l = 0;
        // running total of the subarrays counted so far
        int target = 0;
        for (int r = 0; r < nums.length; r++) {
            // add nums[r] to the window
            int temp = nums[r];
            if(size[temp] == 0){
                distinct++;
            }
            size[temp]++;
            // too many different integers: shrink from the left until only k remain
            while (distinct > k){
                int leftNum = nums[l];
                size[leftNum]--;
                if(size[leftNum] == 0){
                    distinct--;
                }
                l++;
            }
            // every start in l..r gives a valid subarray ending at r
            target += r - l + 1;
        }
        return target;
    }

    // ================================ TESTS =================================
    // Tests for this class 
    // (LC 992. Subarrays with K Different Integers)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the two official examples + edge cases; every input is run through both methods of
    // the solution (the O(n^2) brute force and the O(n) atMost(k) - atMost(k - 1) version).
    // Expected values come from the statement, from the reasoning noted per case, and (checked
    // offline) from a brute force that puts every subarray into a HashSet.
    //   Expected result: 12/12 passed
    // ==========================================================================
    private static void runTests() {
        // official examples
        both("example 1: [1,2,1,2,3], k=2", new int[]{1, 2, 1, 2, 3}, 2, 7);
        both("example 2: [1,2,1,3,4], k=3", new int[]{1, 2, 1, 3, 4}, 3, 3);
        // edge: k equals the length and all values differ - only the whole array qualifies
        both("k = length, all different", new int[]{1, 2, 3, 4, 5}, 5, 1);
        // edge: more different values wanted than the array has
        both("k larger than the distinct count", new int[]{1, 1, 1}, 2, 0);
        // edge: all equal, k = 1 - every subarray counts: 4 * 5 / 2 = 10
        both("all equal, k = 1", new int[]{1, 1, 1, 1}, 1, 10);
        // edge: the largest input, 20000 equal values: 20000 * 20001 / 2 = 200010000 (fits an int)
        int[] big = new int[20000];
        Arrays.fill(big, 1);
        both("20000 equal values, k = 1", big, 1, 200010000);
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

    // runs one input through both methods of the solution
    private static void both(String name, int[] nums, int k, int expected) {
        check(name + "  [subarraysWithKDistinct]", expected,
                () -> LC992SubarraysWithKDifferentIntegers.subarraysWithKDistinct(nums, k));
        check(name + "  [subarraysWithKDistinctOPT]", expected,
                () -> LC992SubarraysWithKDifferentIntegers.subarraysWithKDistinctOPT(nums, k));
    }

    // one case: a wrong value or an exception counts as FAIL; the run always continues
    private static void check(String name, Object expected, Supplier<Object> actual) {
        total++;
        Object got;
        try {
            got = actual.get();
        } catch (Throwable t) {
            got = "threw " + t.getClass().getSimpleName();
        }
        boolean ok = Objects.deepEquals(expected, got);
        if (ok) passed++;
        System.out.println((ok ? "PASS  " : "FAIL  ") + name
                + (ok ? "" : "   expected " + show(expected) + ", got " + show(got)));
    }

    private static String show(Object o) {
        if (o instanceof int[]) return Arrays.toString((int[]) o);
        if (o instanceof long[]) return Arrays.toString((long[]) o);
        if (o instanceof char[]) return Arrays.toString((char[]) o);
        if (o instanceof boolean[]) return Arrays.toString((boolean[]) o);
        if (o instanceof Object[]) return Arrays.deepToString((Object[]) o);
        return String.valueOf(o);
    }

    // ============================== END TESTS ===============================
}
