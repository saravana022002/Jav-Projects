package Leetcode.Ch02PrefixSumsAndDifferenceArrays;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1991. Find the Middle Index in Array   [Easy]
 * https://leetcode.com/problems/find-the-middle-index-in-array/
 * Topics: Array, Prefix Sum
 * Book: not listed in Algorithmic Thinking - same problem as LC 724 (ch. 2, Practice #4)
 *
 * PROBLEM
 *   Find the leftmost index m of nums where the sum of everything before m equals the sum of
 *   everything after m (nums[m] itself belongs to neither side). A side with no elements
 *   sums to 0, so m = 0 and m = nums.length - 1 are allowed. Return -1 if no index works.
 *
 * EXAMPLES
 *   nums = [2,3,-1,8,4]  ->  3    left 2 + 3 + -1 = 4, right = 4
 *   nums = [1,-1,4]      ->  2    left 1 + -1 = 0, nothing on the right = 0
 *   nums = [2,5]         ->  -1   no index balances
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 100,   -1000 <= nums[i] <= 1000
 *   (LeetCode notes that this is the same question as LC 724 Find Pivot Index.)
 *
 * INTUITION
 *   For any index i the array splits as  total = leftSum + nums[i] + rightSum,  so
 *   rightSum = total - leftSum - nums[i]. Once the total is known the right side never has to
 *   be added up again: scan left to right, keep leftSum as a running sum, get rightSum in O(1)
 *   and compare. The first index where they match is the leftmost one, so return it at once.
 *
 * APPROACH (as implemented below)
 *   1. totalNum = the sum of all the numbers.
 *   2. leftSum = 0. For i = 0 .. n-1:
 *        rightSum = totalNum - leftSum - nums[i]
 *        if rightSum == leftSum -> return i
 *        leftSum += nums[i]
 *   3. After the loop no index matched. The code then returns 0, but the problem asks for -1
 *      here (see STATUS).
 *
 * DRY RUN   nums = [2,3,-1,8,4] (the input main() runs)
 *   totalNum = 16
 *   i=0  leftSum = 0   rightSum = 16 - 0 - 2    = 14   14 != 0    leftSum becomes 2
 *   i=1  leftSum = 2   rightSum = 16 - 2 - 3    = 11   11 != 2    leftSum becomes 5
 *   i=2  leftSum = 5   rightSum = 16 - 5 - (-1) = 12   12 != 5    leftSum becomes 4
 *   i=3  leftSum = 4   rightSum = 16 - 4 - 8    = 4    4 == 4     return 3
 *
 * COMPLEXITY
 *   Time : O(n) - one pass for the total, one pass for the scan
 *   Space: O(1) - three int variables
 *
 * GOTCHAS
 *   - An empty side needs no special case: at i = 0 leftSum is 0, and at the last index
 *     rightSum is always 0, so the last index qualifies when the numbers before it sum to 0.
 *   - Scanning left to right and returning at the first match gives the LEFTMOST index
 *     ([0,0,0] -> 0, although 1 and 2 qualify too).
 *   - Negative numbers and zeros are fine, nothing here relies on the signs.
 *   - The final `return 0` is wrong (see STATUS): it hides "no answer" behind a 0 that looks
 *     like a real index.
 *
 * STATUS
 *   Known issue: when no middle index exists the method falls out of the loop and returns 0
 *   instead of -1. Example 3, nums = [2,5], returns 0 (expected -1); [1,2,3] also returns 0
 *   (expected -1). Whenever an index exists, the result is right. The fix would be
 *   "return -1". main() calls findMiddleIndex but does not print the result (it is 3).
 *   The code is left unchanged on purpose.
 *
 * RELATED
 *   - LC 724 Find Pivot Index - the very same question (see the Book line); solved in this
 *     folder as LC724FindPivotIndex.java.
 *   - LC 2270 Number of Ways to Split Array - also compares a prefix sum with the rest.
 * </pre>
 */
public class LC1991FindTheMiddleIndexInArray {
    public static void main(String[] args) {
        // Example 1: the result 3 is not printed
        int[] nums = {2,3,-1,8,4};
        findMiddleIndex(nums);
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }

    // Returns the leftmost index whose left sum equals its right sum. O(n) time, O(1) space
    public static int findMiddleIndex(int[] nums) {
        // sum of the whole array
        int totalNum = 0;
        for (int num : nums) {
            totalNum += num;
        }

        //totalSum = leftSum + rightSum + num[i]

        //rightSum = totalSum - leftsum - num[i]
        int leftSum = 0;
        int rightSum = 0;
        for (int i = 0; i < nums.length; i++) {
            // what is left of the total after the left part and nums[i]
            rightSum = totalNum - leftSum - nums[i];
            // balanced: the first such i is the leftmost middle index
            if(rightSum == leftSum){
                return i;
            }
            // nums[i] joins the left part for the next index
            leftSum += nums[i];
        }
        // BUG: no index matched, so the answer should be -1 (see STATUS); this returns 0
        return 0;
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 1991. Find the Middle Index in Array)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the three official examples + edge cases. Expected values come from the statement,
    // from the reasoning noted per case, and (checked offline) from a brute force that adds up
    // both sides from scratch for every index.
    //   Expected result: 6/7 passed
    //   The 1 failure is the known issue described in the STATUS of
    //   LC1991FindTheMiddleIndexInArray.java (with no middle index it returns 0, not -1).
    // ==========================================================================
    private static void runTests() {
        // official examples
        check("example 1: [2,3,-1,8,4]", 3,
                () -> LC1991FindTheMiddleIndexInArray.findMiddleIndex(new int[]{2, 3, -1, 8, 4}));
        check("example 2: [1,-1,4]", 2,
                () -> LC1991FindTheMiddleIndexInArray.findMiddleIndex(new int[]{1, -1, 4}));
        // example 3 has no middle index: the answer must be -1 (known issue: the code gives 0)
        check("example 3: [2,5] has no middle index", -1,
                () -> LC1991FindTheMiddleIndexInArray.findMiddleIndex(new int[]{2, 5}));
        // edge: one element - both sides are empty (0 == 0)
        check("single element [7]", 0,
                () -> LC1991FindTheMiddleIndexInArray.findMiddleIndex(new int[]{7}));
        // edge: every index qualifies - the leftmost one must win
        check("[0,0,0] -> leftmost", 0,
                () -> LC1991FindTheMiddleIndexInArray.findMiddleIndex(new int[]{0, 0, 0}));
        // edge: negative numbers - index 1 has -1 on both sides
        check("all negative [-1,-1,-1]", 1,
                () -> LC1991FindTheMiddleIndexInArray.findMiddleIndex(new int[]{-1, -1, -1}));
        // edge: 99 equal numbers - the exact middle, index 49, balances (49000 on each side)
        int[] equal = new int[99];
        Arrays.fill(equal, 1000);
        check("99 x 1000 -> index 49", 49,
                () -> LC1991FindTheMiddleIndexInArray.findMiddleIndex(equal));
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
