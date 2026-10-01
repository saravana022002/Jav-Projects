package Leetcode.Ch02PrefixSumsAndDifferenceArrays;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 724. Find Pivot Index   [Easy]
 * https://leetcode.com/problems/find-pivot-index/
 * Topics: Array, Prefix Sum
 * Book: Algorithmic Thinking - ch. 2 Prefix sums and difference arrays, Practice #4
 *
 * PROBLEM
 *   Find the leftmost "pivot" index of nums: an index where the sum of all numbers strictly to
 *   its left equals the sum of all numbers strictly to its right. The pivot element itself is
 *   in neither sum, and an empty side sums to 0. Return that index, or -1 if there is none.
 *
 * EXAMPLES
 *   nums = [1,7,3,6,5,6]  ->  3    left 1 + 7 + 3 = 11, right 5 + 6 = 11
 *   nums = [1,2,3]        ->  -1   no index balances
 *   nums = [2,1,-1]       ->  0    nothing on the left (0), right 1 + (-1) = 0
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 10^4,   -1000 <= nums[i] <= 1000
 *   (LeetCode notes that this is the same question as LC 1991 Find the Middle Index in Array.)
 *
 * INTUITION
 *   For every index i the array splits as  total = leftSum + nums[i] + rightSum.  Re-adding
 *   both sides for each i would cost O(n^2), so get one side from a running sum instead:
 *     - method A keeps the suffix sums in an array (ans[i] = nums[i] + ... + nums[n-1]) and
 *       compares them with the running prefix sum count1 = nums[0] + ... + nums[i]. Both
 *       contain nums[i], so count1 == ans[i] exactly when leftSum == rightSum.
 *     - method B needs no array: rightSum = total - leftSum - nums[i], with leftSum kept as a
 *       running sum.
 *   Scanning from the left and returning at the first hit gives the leftmost pivot.
 *
 * APPROACHES IN THIS FILE
 *   A. pivotIndex()             - array of suffix sums + running prefix sum - O(n) time,
 *                                 O(n) space
 *   B. pivotIndexWithoutSpace() - total sum, then rightSum = total - leftSum - nums[i] - O(n)
 *                                 time, O(1) space              <- main() runs this one
 *
 * APPROACH (as implemented below, method B)
 *   1. totalSum = the sum of all the numbers.
 *   2. leftSum = 0. For i = 0 .. n-1:
 *        rightSum = totalSum - leftSum - nums[i]
 *        if rightSum == leftSum -> return i
 *        leftSum += nums[i]
 *   3. No index matched -> return -1.
 *
 * DRY RUN   nums = [1,7,3,6,5,6], totalSum = 28
 *   i=0  leftSum = 0    rightSum = 28 - 0 - 1  = 27   27 != 0    leftSum becomes 1
 *   i=1  leftSum = 1    rightSum = 28 - 1 - 7  = 20   20 != 1    leftSum becomes 8
 *   i=2  leftSum = 8    rightSum = 28 - 8 - 3  = 17   17 != 8    leftSum becomes 11
 *   i=3  leftSum = 11   rightSum = 28 - 11 - 6 = 11   11 == 11    return 3
 *   main() runs nums = [0,0]: totalSum = 0, at i = 0 both sides are 0, so it prints 0 (index 1
 *   is a pivot too, but the leftmost wins).
 *
 * COMPLEXITY
 *   A: Time O(n) - one pass for the suffix sums, one for the scan.  Space O(n) - the array ans
 *   B: Time O(n) - one pass for the total, one for the scan.  Space O(1)
 *
 * GOTCHAS
 *   - An empty side needs no special case: at i = 0 leftSum is 0, and at the last index
 *     rightSum is always 0.
 *   - Method A compares two INCLUSIVE sums (both contain nums[i]); the pivot element cancels
 *     out. Comparing an inclusive sum with an exclusive one would be off by nums[i].
 *   - Scanning left to right and returning at the first match gives the LEFTMOST pivot.
 *   - Negative numbers and zeros are fine; the sums stay within 10^4 * 1000 = 10^7.
 *
 * STATUS
 *   Both methods are correct (checked against a brute force on random arrays, including
 *   arrays without a pivot).
 *
 * RELATED
 *   - LC 1991 Find the Middle Index in Array (same chapter) - the very same question, as the
 *     LeetCode note says; solved here as LC1991FindTheMiddleIndexInArray.java, whose final
 *     "return 0" is a known issue, while this file returns -1 correctly.
 *   - LC 2270 Number of Ways to Split Array - also compares a prefix sum with the rest.
 * </pre>
 */
public class LC724FindPivotIndex {
    public static void main(String[] args) {
        // [0,0]: index 0 and index 1 are both pivots; the leftmost, 0, is printed (method B)
        int[] nums = {0,0};
        System.out.println(pivotIndexWithoutSpace(nums));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }
    // Approach A: suffix sums in an array, compared with the running prefix sum. O(n) time/space
    public static int pivotIndex(int[] nums) {
        // ans[i] = nums[i] + nums[i+1] + ... + nums[n-1]  (suffix sum, nums[i] included)
        int[] ans = new int[nums.length];
        int count = 0;
        for(int i = nums.length - 1; i >= 0; i--){
            count += nums[i];
            ans[i] = count;
        }

        // count1 = nums[0] + ... + nums[i]  (prefix sum, nums[i] included)
        int count1 = 0;
        for(int i = 0; i < nums.length; i++){
            count1 += nums[i];
            // both sums contain nums[i], so equal means leftSum == rightSum
            if(count1 == ans[i]){
                return i;
            }
        }
        // no pivot
        return -1;
    }

    // without extra space
    // Sum = Leftsum + rightSum + num[i]
    // Sum - Leftsum - num[i] = rightSum

    // Approach B: total sum first, then rightSum = total - leftSum - nums[i]. O(n) time, O(1) space
    public static int pivotIndexWithoutSpace(int[] nums) {
        // sum of the whole array
        int totalSum = 0;
        for (int num : nums) {
            totalSum += num;
        }

        int leftSum = 0;
        int rightSum = 0;
        for(int i = 0; i < nums.length; i++){
            // what remains of the total after the left part and nums[i]
            rightSum = totalSum - leftSum - nums[i];
            // balanced: the first such i is the leftmost pivot
            if(rightSum == leftSum){
                return i;
            }
            // nums[i] joins the left part for the next index
            leftSum += nums[i];
        }
        // no pivot
        return -1;
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 724. Find Pivot Index)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the three official examples + edge cases; every input is run through both methods of
    // the solution (pivotIndex with the suffix-sum array, pivotIndexWithoutSpace). Expected values
    // come from the statement, from the reasoning noted per case, and (checked offline) from a
    // brute force that adds up both sides from scratch for every index.
    //   Expected result: 14/14 passed
    // ==========================================================================
    private static void runTests() {
        // official examples
        both("example 1: [1,7,3,6,5,6]", new int[]{1, 7, 3, 6, 5, 6}, 3);
        both("example 2: [1,2,3] has no pivot", new int[]{1, 2, 3}, -1);
        both("example 3: [2,1,-1]", new int[]{2, 1, -1}, 0);
        // edge: a single element - both sides are empty
        both("single element [1]", new int[]{1}, 0);
        // edge: the pivot is the last index (left sum 0, right side empty)
        both("pivot at the last index [1,-1,0]", new int[]{1, -1, 0}, 2);
        // edge: negative numbers, pivot in the middle (-1 on each side)
        both("all negative [-1,-1,-1]", new int[]{-1, -1, -1}, 1);
        // edge: 9999 equal numbers - the exact middle, index 4999, is the pivot
        int[] equal = new int[9999];
        Arrays.fill(equal, 1000);
        both("9999 x 1000", equal, 4999);
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

    // runs one input through both methods of the solution
    private static void both(String name, int[] nums, int expected) {
        check(name + "  [pivotIndex]", expected, () -> LC724FindPivotIndex.pivotIndex(nums));
        check(name + "  [pivotIndexWithoutSpace]", expected,
                () -> LC724FindPivotIndex.pivotIndexWithoutSpace(nums));
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
