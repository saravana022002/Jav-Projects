package Leetcode.Ch02PrefixSumsAndDifferenceArrays;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 523. Continuous Subarray Sum   [Medium]
 * https://leetcode.com/problems/continuous-subarray-sum/
 * Topics: Array, Hash Table, Math, Prefix Sum, Pigeonhole Principle
 * Book: Algorithmic Thinking - ch. 2 Prefix sums and difference arrays, Practice #7
 *       also: ch. 3 Hashing as a search-to-lookup transform (#9)
 *
 * PROBLEM
 *   Does nums contain a "good" subarray: a contiguous piece of at least two elements whose
 *   sum is a multiple of k? Zero counts as a multiple of k, so a piece that sums to 0 also
 *   qualifies. Return true if there is one, false otherwise.
 *
 * EXAMPLES
 *   nums = [23,2,4,6,7], k = 6   ->  true    [2,4] sums to 6
 *   nums = [23,2,6,4,7], k = 6   ->  true    the whole array sums to 42 = 7 * 6
 *   nums = [23,2,6,4,7], k = 13  ->  false   no piece of two or more sums to a multiple of 13
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 10^5,   0 <= nums[i] <= 10^9,   0 <= sum(nums[i]) <= 2^31 - 1,
 *   1 <= k <= 2^31 - 1
 *
 * INTUITION
 *   Let p[i] be the sum of the first i numbers (p[0] = 0). The piece nums[l..r] sums to
 *   p[r+1] - p[l], and a difference is a multiple of k exactly when both numbers leave the
 *   same remainder mod k. So we look for two prefix sums with equal remainder that are at
 *   least 2 positions apart (then at least two numbers lie between them).
 *   A HashMap remembers, for every remainder, the EARLIEST place where it appeared: the
 *   earliest gives the longest piece, the best chance of reaching length 2. The map starts
 *   with remainder 0 at index -1, which stands for the empty prefix before nums[0].
 *
 * APPROACH (as implemented below)
 *   1. map: remainder of a prefix sum -> the first index where it occurred. Seed: put(0, -1).
 *      count = the running prefix sum.
 *   2. For each i: count += nums[i]; remainder = count % k.
 *   3. If remainder is already in the map at index l, the piece nums[l+1..i] has i - l
 *      elements and a sum divisible by k. If i - l >= 2 return true. If i - l is 1, do
 *      nothing and keep the earlier index.
 *   4. Otherwise map.put(remainder, i).
 *   5. The loop ended without a hit -> return false.
 *
 * DRY RUN   nums = [23,2,4,6,7], k = 6 (the input main() runs)
 *   start  map = {0=-1}
 *   i=0  count = 23, remainder 5   not in map                 map = {0=-1, 5=0}
 *   i=1  count = 25, remainder 1   not in map                 map = {0=-1, 5=0, 1=1}
 *   i=2  count = 29, remainder 5   in map at l = 0, i - l = 2 >= 2  -> return true
 *   (the piece is nums[1..2] = [2,4], sum 6)
 *
 * COMPLEXITY
 *   Time : O(n) - one pass, O(1) average per HashMap operation
 *   Space: O(min(n, k)) - at most one entry per distinct remainder, never more than n + 1
 *
 * GOTCHAS
 *   - The map is written only when the remainder is NEW, so every remainder keeps its
 *     earliest index. A hit only one step away is a single element that is a multiple of k;
 *     it must not count, and it must not overwrite the stored index ([0,1,0], k = 2 -> false).
 *   - The seed {0 -> -1} lets a piece that starts at index 0 meet the empty prefix. Without
 *     it [2,4] with k = 6 would be missed.
 *   - Zero is a multiple of every k: [0,0] -> true, but a single [0] is too short.
 *   - nums[i] >= 0, so remainders are never negative (LC 974 has negative numbers and has to
 *     fix the sign of the remainder).
 *   - count is an int; the constraints cap the total sum at 2^31 - 1, so it cannot overflow
 *     ([1073741823, 1073741824] with k = 2147483647 -> true).
 *
 * STATUS
 *   Correct (checked against an all-subarrays brute force on random arrays).
 *
 * SEE ALSO
 *   src/Leetcode/Ch03HashingAsASearchToLookupTransform/LC523ContinuousSubarraySum.java
 *     - the same algorithm, documented there from the hashing chapter's side; only its main()
 *       differs (it does not print the result)
 *
 * RELATED
 *   - LC 560 Subarray Sum Equals K (solved in src/Practice/PrefixSum) - the same prefix sums
 *     in a map, but the difference must equal k instead of being a multiple of it.
 *   - LC 974 Subarray Sums Divisible by K (solved in this chapter) - the same remainder trick,
 *     counting every such subarray.
 * </pre>
 */
public class LC523ContinuousSubarraySum {
    public static void main(String[] args) {
        // Example 1: prints true ([2,4] sums to 6)
        int[] nums = {23,2,4,6,7};
        int k = 6;
        System.out.println(checkSubarraySum(nums, k));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }

    // p[r] - p[l] = x , // where t is multiple of k , //x = n * k , n can be 0 also, r - l >= 2
    // True when two prefix sums share a remainder mod k and sit at least 2 positions apart. O(n)
    public static boolean checkSubarraySum(int[] nums, int k) {
        // remainder of a prefix sum -> the FIRST index where it occurred
        Map<Integer, Integer> map = new HashMap<>();
        // the empty prefix has sum 0 and "ends" at index -1
        map.put(0, -1);
        // running prefix sum
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += nums[i];
            int remainder = count % k;
            if(map.containsKey(remainder)){
                // same remainder as an earlier prefix: nums[l + 1 .. i] sums to a multiple of k
                int l = map.get(remainder);
                // it needs two or more elements; otherwise ignore the hit and keep the old index
                if(i - l >= 2){
                    return true;
                }
            }else {
                // first time this remainder appears: remember where
                map.put(remainder, i);
            }
        }
        // no two equal remainders far enough apart
        return false;
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 523. Continuous Subarray Sum)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the three official examples + edge cases. Expected values come from the statement,
    // from the reasoning noted per case, and (checked offline) from a brute force that tries
    // every subarray of length two or more.
    //   Expected result: 7/7 passed
    // ==========================================================================
    private static void runTests() {
        // official examples
        check("example 1: [23,2,4,6,7], k=6", true,
                () -> LC523ContinuousSubarraySum.checkSubarraySum(new int[]{23, 2, 4, 6, 7}, 6));
        check("example 2: [23,2,6,4,7], k=6", true,
                () -> LC523ContinuousSubarraySum.checkSubarraySum(new int[]{23, 2, 6, 4, 7}, 6));
        check("example 3: [23,2,6,4,7], k=13", false,
                () -> LC523ContinuousSubarraySum.checkSubarraySum(new int[]{23, 2, 6, 4, 7}, 13));
        // edge: the good piece starts at index 0 (needs the {0 -> -1} seed)
        check("piece from index 0: [2,4], k=6", true,
                () -> LC523ContinuousSubarraySum.checkSubarraySum(new int[]{2, 4}, 6));
        // edge: a single element that is a multiple of k is too short
        check("one element is not enough: [6], k=6", false,
                () -> LC523ContinuousSubarraySum.checkSubarraySum(new int[]{6}, 6));
        // edge: 0 is a multiple of every k, so two zeros are a good subarray
        check("zeros: [0,0], k=5", true,
                () -> LC523ContinuousSubarraySum.checkSubarraySum(new int[]{0, 0}, 5));
        // edge: the sum is exactly 2^31 - 1 and k is 2^31 - 1 (no int overflow)
        check("sum at the int limit", true, () -> LC523ContinuousSubarraySum
                .checkSubarraySum(new int[]{1073741823, 1073741824}, 2147483647));
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

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
