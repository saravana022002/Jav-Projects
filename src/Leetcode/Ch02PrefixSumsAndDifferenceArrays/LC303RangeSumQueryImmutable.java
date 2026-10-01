package Leetcode.Ch02PrefixSumsAndDifferenceArrays;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 303. Range Sum Query - Immutable   [Easy]
 * https://leetcode.com/problems/range-sum-query-immutable/
 * Topics: Array, Design, Prefix Sum
 * Book: Algorithmic Thinking - ch. 2 Prefix sums and difference arrays, Practice #2
 *       also: ch. 39 Fenwick trees (#1), ch. 40 Segment trees (#1)
 *
 * PROBLEM
 *   Build an object from an integer array nums, then answer many queries
 *   sumRange(left, right): the sum of nums[left] + ... + nums[right], both ends included.
 *   The array never changes after construction, so work can be done up front.
 *   (LeetCode names this class NumArray; here the class is LC303RangeSumQueryImmutable.)
 *
 * EXAMPLES
 *   nums = [-2, 0, 3, -5, 2, -1]
 *   sumRange(0, 2)  ->  1     -2 + 0 + 3
 *   sumRange(2, 5)  ->  -1    3 + (-5) + 2 + (-1)
 *   sumRange(0, 5)  ->  -3    the whole array
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 10^4,   -10^5 <= nums[i] <= 10^5,   0 <= left <= right < nums.length,
 *   at most 10^4 calls to sumRange
 *
 * INTUITION
 *   Adding up the range for every query costs O(length) each. Because nums never changes,
 *   add it up once instead: prefixSum[k] = nums[0] + ... + nums[k-1] (prefixSum[0] = 0 means
 *   "nothing yet"). The sum of nums[left..right] is "everything up to right" minus "everything
 *   before left", i.e. prefixSum[right + 1] - prefixSum[left] - two lookups, O(1) per query.
 *
 * APPROACH (as implemented below)
 *   Constructor, once: prefixSum = new long[n + 1] with prefixSum[0] = 0; keep a running
 *   total count; for each i: count += nums[i]; prefixSum[i + 1] = count.
 *   sumRange(left, right): return prefixSum[right + 1] - prefixSum[left] (cast back to int).
 *
 * DRY RUN   nums = [-2, 0, 3, -5, 2, -1] (the array main() uses)
 *   prefixSum = [0, -2, -2, 1, -4, -2, -3]
 *   sumRange(0, 2) = prefixSum[3] - prefixSum[0] = 1 - 0       = 1
 *   sumRange(2, 5) = prefixSum[6] - prefixSum[2] = -3 - (-2)   = -1
 *   sumRange(0, 5) = prefixSum[6] - prefixSum[0] = -3 - 0      = -3
 *
 * COMPLEXITY
 *   Constructor: O(n) time, O(n) space for prefixSum
 *   sumRange   : O(1) time per query - so q queries cost O(n + q) instead of O(n * q)
 *
 * GOTCHAS
 *   - The extra leading 0 in prefixSum is what makes left = 0 work without an if:
 *     sumRange(0, r) = prefixSum[r + 1] - prefixSum[0].
 *   - Index shift by one: the sum UP TO AND INCLUDING right is prefixSum[right + 1]; the sum
 *     BEFORE left is prefixSum[left].
 *   - prefixSum is long, but totals never exceed 10^4 * 10^5 = 10^9, which still fits in an
 *     int, so long is a safety margin and the final (int) cast is safe.
 *   - Immutable means no update method. If values could change, prefix sums would need O(n)
 *     rebuilds - that is what Fenwick and segment trees (book ch. 39 and 40) are for.
 *
 * STATUS
 *   Correct. Signature differs from LeetCode: the class and constructor are named
 *   LC303RangeSumQueryImmutable (LeetCode expects NumArray).
 *
 * RELATED
 *   - LC 304 Range Sum Query 2D - Immutable - the same trick on a matrix.
 *   - LC 307 Range Sum Query - Mutable - the same question with updates between queries.
 * </pre>
 */
public class LC303RangeSumQueryImmutable {
    public static void main(String[] args) {
        // Example 1: prints 1, -1, -3
        int[] nums = {-2, 0, 3, -5, 2, -1};
        LC303RangeSumQueryImmutable numArray = new LC303RangeSumQueryImmutable(nums);
        System.out.println(numArray.sumRange(0, 2)); // return (-2) + 0 + 3 = 1
        System.out.println(numArray.sumRange(2, 5)); // return 3 + (-5) + 2 + (-1) = -1
        System.out.println(numArray.sumRange(0, 5)); // return (-2) + 0 + 3 + (-5) + 2 + (-1) = -3
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }

    // prefixSum[k] = nums[0] + ... + nums[k - 1]; prefixSum[0] = 0 stands for "nothing yet"
    long[] prefixSum;
    // builds the running totals once: O(n)
    public LC303RangeSumQueryImmutable(int[] nums) {
        prefixSum = new long[nums.length + 1];
        prefixSum[0] = 0L;
        // running total of the numbers seen so far
        long count = 0L;
        for(int i = 0; i < nums.length; i++){
            count += nums[i];
            prefixSum[i + 1] = count;
        }
    }

    // sum of nums[left..right] = (everything up to right) - (everything before left). O(1)
    public int sumRange(int left, int right) {
        return (int) (prefixSum[right + 1] - prefixSum[left]);
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 303. Range Sum Query - Immutable)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the official example (one check per sumRange call) + edge cases. Expected values
    // come from the statement, from the reasoning noted per case, and (checked offline) from
    // direct sums over random arrays and ranges.
    //   Expected result: 7/7 passed
    // ==========================================================================
    private static void runTests() {
        LC303RangeSumQueryImmutable numArray =
                new LC303RangeSumQueryImmutable(new int[]{-2, 0, 3, -5, 2, -1});
        // official example
        check("example: sumRange(0, 2)", 1, () -> numArray.sumRange(0, 2));
        check("example: sumRange(2, 5)", -1, () -> numArray.sumRange(2, 5));
        check("example: sumRange(0, 5)", -3, () -> numArray.sumRange(0, 5));
        // edge: a single-element array
        check("single element", 7,
                () -> new LC303RangeSumQueryImmutable(new int[]{7}).sumRange(0, 0));
        // edge: a range of length 1 in the middle of the example array (nums[2] = 3)
        check("one-element range", 3, () -> numArray.sumRange(2, 2));
        // edge: the largest total, 10^4 values of 10^5 = 10^9 (still fits in an int)
        int[] big = new int[10000];
        Arrays.fill(big, 100000);
        check("10^4 x 100000", 1000000000,
                () -> new LC303RangeSumQueryImmutable(big).sumRange(0, 9999));
        // edge: the most negative total, 10^4 values of -10^5
        int[] small = new int[10000];
        Arrays.fill(small, -100000);
        check("10^4 x -100000", -1000000000,
                () -> new LC303RangeSumQueryImmutable(small).sumRange(0, 9999));
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
