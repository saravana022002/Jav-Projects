package Leetcode.Ch02PrefixSumsAndDifferenceArrays;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 525. Contiguous Array   [Medium]
 * https://leetcode.com/problems/contiguous-array/
 * Topics: Array, Hash Table, Prefix Sum
 * Book: Algorithmic Thinking - ch. 2 Prefix sums and difference arrays, Practice #8
 *
 * PROBLEM
 *   nums holds only 0s and 1s. Find the longest contiguous piece that contains as many 0s
 *   as 1s and return its length (0 when no piece is balanced).
 *
 * EXAMPLES
 *   nums = [0,1]                 ->  2    the whole array
 *   nums = [0,1,0]               ->  2    [0,1] (or [1,0])
 *   nums = [0,1,1,1,1,1,0,0,0]   ->  6    [1,1,1,0,0,0]
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 10^5,   nums[i] is 0 or 1
 *
 * INTUITION
 *   Give every 0 the value -1 and every 1 the value +1. A piece has as many 0s as 1s exactly
 *   when its values add up to 0. With a running total (the "balance") that says: the balance
 *   before the piece equals the balance after it. So we want two positions with the SAME
 *   balance that are as far apart as possible. A HashMap stores the first index where each
 *   balance occurred; when a balance appears again at index i, the piece in between has
 *   length i - firstIndex. The empty prefix has balance 0 at index -1, so the map starts as
 *   {0 -> -1}.
 *
 * APPROACH (as implemented below)
 *   1. map: balance -> the first index where it occurred; seed put(0, -1).
 *      count = the running balance, max = 0.
 *   2. For each i: count += (nums[i] == 0 ? -1 : +1).
 *   3. If count is already in the map at leftIndex, the piece leftIndex+1 .. i is balanced:
 *      max = max(max, i - leftIndex). The stored (earlier) index stays.
 *   4. Otherwise map.put(count, i).
 *   5. Return max.
 *
 * DRY RUN   nums = [0,1,1,1,1,1,0,0,0] (the input main() runs)
 *   i  nums[i]  count  map                              max
 *   0  0        -1     new: put(-1, 0)                  0
 *   1  1         0     seen at -1: 1 - (-1) = 2         2
 *   2  1         1     new: put(1, 2)                   2
 *   3  1         2     new: put(2, 3)                   2
 *   4  1         3     new: put(3, 4)                   2
 *   5  1         4     new: put(4, 5)                   2
 *   6  0         3     seen at 4: 6 - 4 = 2             2
 *   7  0         2     seen at 3: 7 - 3 = 4             4
 *   8  0         1     seen at 2: 8 - 2 = 6             6
 *   return 6   (the piece is indexes 3..8 = [1,1,1,0,0,0])
 *
 * COMPLEXITY
 *   Time : O(n) - one pass, O(1) average per HashMap operation
 *   Space: O(n) - at most n + 1 different balances, one map entry each
 *
 * GOTCHAS
 *   - A balance is stored only the FIRST time it appears (the else branch), so every later hit
 *     is measured from the earliest index and gives the longest piece.
 *   - The seed {0 -> -1} covers pieces that start at index 0: [0,0,1,1] -> 4.
 *   - "count += nums[i] == 0 ? -1 : 1" parses as count += ((nums[i] == 0) ? -1 : 1).
 *   - If no piece is balanced (e.g. [1] or all equal values) max stays 0.
 *
 * STATUS
 *   Correct (checked against a brute force on random 0/1 arrays). main() calls findMaxLength
 *   but does not print the result (it is 6).
 *
 * RELATED
 *   - LC 325 Maximum Size Subarray Sum Equals k - the same "earliest index per prefix sum"
 *     map for any target k; this problem is the case k = 0 once the 0s become -1.
 *   - LC 523 Continuous Subarray Sum (same chapter) - the same first-index map, keyed by the
 *     prefix-sum remainder instead of the prefix sum.
 * </pre>
 */
public class LC525ContiguousArray {
    public static void main(String[] args) {
        // Example 3: the result 6 is not printed
        int[] nums = {0,1,1,1,1,1,0,0,0};
        findMaxLength(nums);
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }
    // Longest piece with equally many 0s and 1s: earliest index per running balance. O(n)
    public static int findMaxLength(int[] nums) {
        // running balance -> the first index where it occurred
        Map<Integer, Integer> map = new HashMap<>();
        // the empty prefix has balance 0 and "ends" at index -1
        map.put(0, -1);

        int max = 0;
        // balance: every 1 counts +1, every 0 counts -1
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += nums[i] == 0 ? -1 : 1;
            if (map.containsKey(count)) {
                // same balance as after leftIndex: the piece in between has as many 0s as 1s
                int leftIndex = map.get(count);
                max = Math.max(max, i - leftIndex);
            }else {
                // first time this balance appears: remember where
                map.put(count, i);
            }

        }
        return max;
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 525. Contiguous Array)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the three official examples + edge cases. Expected values come from the statement,
    // from the reasoning noted per case, and (checked offline) from a brute force that counts the
    // 0s and 1s of every subarray.
    //   Expected result: 7/7 passed
    // ==========================================================================
    private static void runTests() {
        // official examples
        check("example 1: [0,1]", 2,
                () -> LC525ContiguousArray.findMaxLength(new int[]{0, 1}));
        check("example 2: [0,1,0]", 2,
                () -> LC525ContiguousArray.findMaxLength(new int[]{0, 1, 0}));
        check("example 3: [0,1,1,1,1,1,0,0,0]", 6,
                () -> LC525ContiguousArray.findMaxLength(new int[]{0, 1, 1, 1, 1, 1, 0, 0, 0}));
        // edge: one element can never be balanced
        check("single element [1]", 0,
                () -> LC525ContiguousArray.findMaxLength(new int[]{1}));
        // edge: no 1s at all - nothing is balanced
        check("all zeros", 0,
                () -> LC525ContiguousArray.findMaxLength(new int[]{0, 0, 0, 0}));
        // edge: the balanced piece starts at index 0 (needs the {0 -> -1} seed) and is the
        // whole array
        check("[0,0,1,1] balanced from index 0", 4,
                () -> LC525ContiguousArray.findMaxLength(new int[]{0, 0, 1, 1}));
        // edge: 10^5 alternating values 0,1,0,1,... - the whole array is balanced
        int[] alternating = new int[100000];
        for (int i = 0; i < alternating.length; i++) alternating[i] = i % 2;
        check("10^5 alternating values", 100000,
                () -> LC525ContiguousArray.findMaxLength(alternating));
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
