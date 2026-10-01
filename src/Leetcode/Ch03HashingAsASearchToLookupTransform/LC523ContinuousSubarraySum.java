package Leetcode.Ch03HashingAsASearchToLookupTransform;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 523. Continuous Subarray Sum   [Medium]
 * https://leetcode.com/problems/continuous-subarray-sum/
 * Topics: Array, Hash Table, Math, Prefix Sum, Pigeonhole Principle
 * Book: Algorithmic Thinking - ch. 3 Hashing as a search-to-lookup transform, Practice #9
 *       also: ch. 2 Prefix sums and difference arrays (#7)
 *
 * PROBLEM
 *   Given an int array nums and an int k, is there a "good" subarray? A good subarray is a
 *   contiguous part of nums with at least two elements whose sum is a multiple of k
 *   (0 counts as a multiple of k, so a subarray that sums to 0 is fine). Return true if one
 *   exists, false otherwise.
 *
 * EXAMPLES
 *   nums = [23,2,4,6,7], k = 6   ->  true     [2,4] sums to 6
 *   nums = [23,2,6,4,7], k = 6   ->  true     the whole array sums to 42 = 7 * 6
 *   nums = [23,2,6,4,7], k = 13  ->  false    no run of two or more sums to a multiple of 13
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 10^5,   0 <= nums[i] <= 10^9,   0 <= sum(nums[i]) <= 2^31 - 1,
 *   1 <= k <= 2^31 - 1
 *
 * INTUITION
 *   Let P[i] be the sum of the first i numbers (P[0] = 0). The subarray nums[l..r] sums to
 *   P[r+1] - P[l], and that is a multiple of k exactly when P[r+1] and P[l] leave the SAME
 *   remainder when divided by k. So the question becomes: do two prefix sums with equal
 *   remainder exist that are at least 2 positions apart (so the subarray between them has
 *   two or more elements)?
 *   A HashMap remembers, for each remainder, the EARLIEST position where it appeared - the
 *   earliest one gives the longest subarray, the best chance to reach length 2. The map
 *   starts with {0 -> -1}: the empty prefix before nums[0] has sum 0 and ends at index -1.
 *
 * APPROACH (as implemented below)
 *   1. map: remainder of a prefix sum -> the first index i where it appeared. Seed: put(0, -1).
 *   2. count is the running prefix sum. For each i: count += nums[i], remainder = count % k.
 *   3. remainder is already in the map, at index l: nums[l+1..i] sums to a multiple of k and
 *      has i - l elements. If i - l >= 2 return true. (If i - l is 1, do nothing and keep
 *      the old, earlier index.)
 *   4. remainder is not in the map: map.put(remainder, i).
 *   5. The loop ended without a hit -> return false.
 *
 * DRY RUN   nums = [23,2,4,6,7], k = 6   (what main() runs)
 *   start       map = {0=-1}
 *   i=0: count=23, remainder=5 -> not in map           map = {0=-1, 5=0}
 *   i=1: count=25, remainder=1 -> not in map           map = {0=-1, 5=0, 1=1}
 *   i=2: count=29, remainder=5 -> in map at l=0, and i - l = 2 >= 2 -> return true
 *   (the subarray is nums[1..2] = [2,4], sum 6)
 *
 * COMPLEXITY
 *   Time : O(n) - one pass, O(1) average per HashMap operation
 *   Space: O(min(n, k)) - at most one entry per distinct remainder, and never more than n + 1
 *
 * GOTCHAS
 *   - Length at least 2: a repeated remainder only one step apart means a SINGLE element that
 *     is a multiple of k, which does not count. The map then keeps the old index, so later
 *     hits are still measured from the earliest position. Example: [0,1,0] with k = 2 -> false.
 *   - The seed {0 -> -1} covers subarrays that start at index 0 (the prefix sum itself is a
 *     multiple of k). Without it, [2,4] with k = 6 would be missed.
 *   - 0 is a multiple of every k, so [0,0] -> true.
 *   - nums[i] >= 0, so every remainder is >= 0 (LC 974 has negative numbers and must
 *     normalise the remainder).
 *   - count is an int: the constraints cap the total sum at 2^31 - 1, so it cannot overflow.
 *
 * STATUS
 *   Correct (checked against an all-subarrays reference on random arrays, plus sums near
 *   2^31 - 1). main() calls checkSubarraySum but does not print the result (it is true).
 *   Test file: main().java in this folder.
 *
 * RELATED
 *   - LC 560 Subarray Sum Equals K - the same prefix-sums-in-a-map idea, but the difference
 *     of two prefix sums must equal k exactly instead of being a multiple of it.
 *   - LC 974 Subarray Sums Divisible by K - the same remainder trick, counting every such
 *     subarray (solved in this folder).
 *
 * SEE ALSO
 *   src/Leetcode/Ch02PrefixSumsAndDifferenceArrays/LC523ContinuousSubarraySum.java
 *       - the same algorithm; only its main() differs (it prints the result)
 * </pre>
 */
public class LC523ContinuousSubarraySum {

    public static void main(String[] args) {
        // Example 1 - nothing is printed; the method returns true ([2,4] sums to 6)
        int[] nums = {23,2,4,6,7};
        int k = 6;
        checkSubarraySum(nums, k);
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }
    // True when some subarray of length >= 2 has a sum that is a multiple of k, i.e. two
    // prefix sums with the same remainder (mod k) sit at least 2 positions apart. O(n).
    public static boolean checkSubarraySum(int[] nums, int k) {
        // remainder of a prefix sum -> the first index where it occurred
        Map<Integer, Integer> map = new HashMap<>();
        // the empty prefix (before index 0) has sum 0 and "ends" at index -1
        map.put(0, -1);
        // running prefix sum
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += nums[i];
            int remainder = count % k;
            if(map.containsKey(remainder)){
                // same remainder as an earlier prefix -> nums[l + 1 .. i] sums to a multiple of k
                int l = map.get(remainder);
                // it needs at least two elements; if not, keep the earlier index (no update)
                if(i - l >= 2){
                    return true;
                }
            }else {
                // first time this remainder shows up: remember where
                map.put(remainder, i);
            }
        }
        return false;
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 523. Continuous Subarray Sum)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the 3 official examples + edge cases (a single element, zeros, equal remainders
    // only one step apart, a subarray starting at index 0, sums at the 2^31 - 1 limit);
    // expected values from the statement or checked against an all-subarrays brute force.
    //   Expected result: 8/8 passed
    // ==========================================================================
    private static void runTests() {
        // official examples
        check("example 1: [2,4] sums to 6", true, () -> good(6, 23, 2, 4, 6, 7));
        check("example 2: whole array sums to 42", true, () -> good(6, 23, 2, 6, 4, 7));
        check("example 3: k = 13", false, () -> good(13, 23, 2, 6, 4, 7));
        // edge: one element that is a multiple of k does not count (length must be >= 2)
        check("single element equal to k", false, () -> good(6, 6));
        // edge: two zeros sum to 0, a multiple of any k
        check("two zeros", true, () -> good(1, 0, 0));
        // edge: equal remainders one step apart are only a single element, not enough
        check("remainders one step apart", false, () -> good(2, 0, 1, 0));
        // edge: the good subarray starts at index 0 (needs the {0 -> -1} seed)
        check("starts at index 0", true, () -> good(6, 2, 4));
        // edge: sum = 2^31 - 1 = k, the largest allowed values
        check("sum and k at 2^31 - 1", true, () -> good(2147483647, 1073741823, 1073741824));
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

    // k first, then the array elements
    private static boolean good(int k, int... nums) {
        return LC523ContinuousSubarraySum.checkSubarraySum(nums, k);
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
