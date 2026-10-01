package Leetcode.Ch03HashingAsASearchToLookupTransform;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 2006. Count Number of Pairs With Absolute Difference K   [Easy]
 * https://leetcode.com/problems/count-number-of-pairs-with-absolute-difference-k/
 * Topics: Array, Hash Table, Counting
 * Book: Algorithmic Thinking - ch. 3 Hashing as a search-to-lookup transform, Practice #5
 *
 * PROBLEM
 *   Given an int array nums and an int k, count the pairs of positions (i, j) with i < j
 *   whose values differ by exactly k, i.e. |nums[i] - nums[j]| == k. Pairs are counted by
 *   position, so equal values sitting at different positions count separately.
 *
 * EXAMPLES
 *   nums = [1,2,2,1],   k = 1  ->  4    position pairs (0,1) (0,2) (1,3) (2,3): a 1 and a 2 each
 *   nums = [1,3],       k = 3  ->  0    the only pair differs by 2, not 3
 *   nums = [3,2,1,5,4], k = 2  ->  3    position pairs (0,2) (0,3) (1,4): values 3&1, 3&5, 2&4
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 200,   1 <= nums[i] <= 100,   1 <= k <= 99
 *
 * INTUITION
 *   When a number num arrives, the earlier numbers it can pair with are exactly those equal
 *   to num + k or to num - k. If we know how many times each value has appeared so far,
 *   the new pairs are count of (num + k) plus count of (num - k). Values are at most 100,
 *   so the "map" can be a plain array indexed by value. Updating it AFTER counting makes
 *   every pair count once - when its second member arrives. (k >= 1, so num + k and
 *   num - k are different values and nothing is counted twice.)
 *
 * APPROACH (as implemented below)
 *   1. arr = new int[101]: arr[v] = how many times the value v has appeared so far.
 *   2. For each num in nums, left to right:
 *        count += arr[num + k]    the earlier numbers that are k above num
 *        count += arr[num - k]    the earlier numbers that are k below num
 *      then arr[num]++ (num is recorded only after the counting).
 *   3. Return count.
 *   Neither index is range-checked - see STATUS.
 *
 * DRY RUN   nums = [1,2,2,1], k = 1   (what main() runs)
 *   num=1: arr[2]=0, arr[0]=0 -> +0    arr[1]=1    count = 0
 *   num=2: arr[3]=0, arr[1]=1 -> +1    arr[2]=1    count = 1
 *   num=2: arr[3]=0, arr[1]=1 -> +1    arr[2]=2    count = 2
 *   num=1: arr[2]=2, arr[0]=0 -> +2    arr[1]=2    count = 4
 *   return 4
 *
 * COMPLEXITY
 *   Time : O(n) - one pass, O(1) work per element
 *   Space: O(1) - a fixed array of 101 slots (O(V) for values up to V = 100)
 *
 * GOTCHAS
 *   - Read arr[...] BEFORE arr[num]++, otherwise the count would see the current element.
 *   - Pairs are of positions, not of distinct values: [1,2,2,1] with k = 1 gives 4, not 1.
 *   - arr[0] is a valid slot that is never filled (values start at 1), so reading it is
 *     harmless.
 *   - num + k and num - k are unchecked array indices - that is the bug described in STATUS.
 *
 * STATUS
 *   Known issue: countKDifference throws ArrayIndexOutOfBoundsException on valid input.
 *   arr has 101 slots (indices 0..100), but num + k can reach 199 and num - k can drop to
 *   -98, and neither index is checked. The code works only when EVERY element satisfies
 *   num - k >= 0 and num + k <= 100.
 *     - example 1 ([1,2,2,1], k = 1) stays in range and returns 4 - what main() prints
 *     - example 2 ([1,3], k = 3): num = 1 reads arr[-2] and throws; correct answer is 0
 *     - example 3 ([3,2,1,5,4], k = 2): num = 1 reads arr[-1] and throws; correct answer is 3
 *     - [100] with k = 1 reads arr[101] and throws; correct answer is 0
 *   Whenever it does not throw, the count is right (checked against a double-loop reference).
 *   Fix idea: read arr[num + k] only if num + k <= 100, and arr[num - k] only if num - k >= 0.
 *   The code is left unchanged on purpose.
 *   Test file: main().java in this folder.
 *
 * RELATED
 *   - LC 1 Two Sum - the same "look up the partner among the numbers already seen" idea
 *     (solved in this folder); here there are two partners, num + k and num - k.
 *   - LC 532 K-diff Pairs in an Array - another difference-of-k pair problem (a similar
 *     problem on LeetCode).
 * </pre>
 */
public class LC2006CountNumberOfPairsWithAbsoluteDifferenceK {
    public static void main(String[] args) {
        // Example 1 - stays inside arr's bounds. Expected output: 4
        // (examples 2 and 3 throw ArrayIndexOutOfBoundsException - see STATUS)
        int[] nums = {1,2,2,1};
        int k = 1;
        System.out.println(countKDifference(nums, k));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }

    // Counts the pairs of positions i < j with |nums[i] - nums[j]| == k in one pass.
    // KNOWN ISSUE: arr[num + k] and arr[num - k] are not range-checked - see STATUS.
    // num[i] - num[j] = k ==>
    // num[i] - num[j] = -k ==>
    public static int countKDifference(int[] nums, int k) {
        // arr[v] = how many times the value v has been seen so far (values are 1..100)
        int[] arr = new int[101];
        int count = 0;
        for (int num : nums) {
            // every earlier num + k or num - k forms one new pair with num
            // (throws when num + k > 100 or num - k < 0)
            count += arr[num + k];
            count += arr[num - k];
            // num is recorded only after counting, so each pair is counted exactly once
            arr[num]++;
        }
        return count;
    }

    // ================================ TESTS =================================
    // Tests for this class 
    // (LC 2006. Count Number of Pairs With Absolute Difference K)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the 3 official examples + edge cases (one element, all-equal values, the lowest and
    // highest values the constraints allow); expected values from the statement or checked
    // against a double-loop brute force.
    //   Expected result: 4/7 passed
    //   The 3 failures are the known issue described in the STATUS of
    //   LC2006CountNumberOfPairsWithAbsoluteDifferenceK.java: arr[num + k] / arr[num - k] are
    //   not range-checked, so official examples 2 and 3 and any input with num + k > 100 throw
    //   ArrayIndexOutOfBoundsException (reported below as "threw ..." and counted as FAIL).
    // ==========================================================================
    private static void runTests() {
        // official examples (examples 2 and 3 expose the known issue)
        check("example 1: [1,2,2,1], k=1", 4, () -> count(new int[]{1, 2, 2, 1}, 1));
        check("example 2: [1,3], k=3 (reads arr[-2])", 0, () -> count(new int[]{1, 3}, 3));
        check("example 3: [3,2,1,5,4], k=2 (reads arr[-1])", 3,
                () -> count(new int[]{3, 2, 1, 5, 4}, 2));
        // edge: a single element has no pair
        check("single element", 0, () -> count(new int[]{1}, 1));
        // edge: all values equal, none differs by k
        check("all values equal", 0, () -> count(new int[]{5, 5, 5}, 1));
        // edge: lowest value 1 with k=1 reads arr[0], which is still inside the array
        check("lowest value, k=1", 1, () -> count(new int[]{1, 2}, 1));
        // edge: highest value 100 with k=1 reads arr[101], one past the end
        check("highest value 100, k=1 (reads arr[101])", 1, () -> count(new int[]{100, 99}, 1));
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

    private static int count(int[] nums, int k) {
        return LC2006CountNumberOfPairsWithAbsoluteDifferenceK.countKDifference(nums, k);
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
