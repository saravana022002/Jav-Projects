package Leetcode.Ch05BitManipulationAsASetEncoding;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1863. Sum of All Subset XOR Totals   [Easy]
 * https://leetcode.com/problems/sum-of-all-subset-xor-totals/
 * Topics: Array, Math, Backtracking, Bit Manipulation, Combinatorics, Enumeration
 * Book: Algorithmic Thinking - ch. 5 Bit manipulation as a set encoding, Practice #9
 *       also: ch. 60 Meet in the middle (#2), ch. 71 Bitmask DP (#2)
 *
 * PROBLEM
 *   The XOR total of an array is the XOR of all its elements (0 for the empty array); for
 *   example [2,5,6] has the XOR total 2 ^ 5 ^ 6 = 1. Take every subset of nums - what is left
 *   after deleting zero or more elements, so the empty subset is included - and add up all
 *   their XOR totals. Subsets with the same elements are counted multiple times: for
 *   nums = [5,5] the two one-element subsets [5] (different positions) both count.
 *
 * EXAMPLES
 *   nums = [1,3]            ->  6     the subsets {}, [1], [3], [1,3] have XOR totals 0, 1, 3, 2
 *   nums = [5,1,6]          ->  28    the 8 totals are 0, 5, 1, 4, 6, 3, 7, 2 (see DRY RUN)
 *   nums = [3,4,5,6,7,8]    ->  480
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 12,   1 <= nums[i] <= 20
 *
 * INTUITION
 *   With n at most 12 there are at most 2^12 = 4096 subsets, so we can simply visit all of
 *   them. A subset is a yes / no choice per element - exactly the bits of a number: write i in
 *   binary and let bit j of i say whether nums[j] is in the subset. Counting i from 0 to
 *   2^n - 1 then walks through every subset exactly once (i = 0 is the empty subset). For
 *   each one, XOR its members and add the result to the running total.
 *
 *   Faster idea (NOT in this file): look at one bit position b at a time. If some number has
 *   bit b set, then exactly half of the 2^n subsets (2^(n-1) of them) have bit b set in their
 *   XOR total, because putting one such number in or out of a subset flips that bit. So the
 *   answer is (OR of all numbers) * 2^(n-1), in O(n) time. For [5,1,6]: OR = 7, 7 * 4 = 28.
 *
 * APPROACHES IN THIS FILE
 *   A. subsetXORSum()        - visit all 2^n bitmasks, XOR the chosen numbers, add the totals
 *                              - O(n * 2^n)       <- main() runs this one
 *   B. subsetXORSumOptimal() - despite its name it does not compute the sum: it returns all
 *                              2^n subsets as a list of lists (the LC 78 loop) - see STATUS
 *   (APPROACH and DRY RUN below describe A.)
 *
 * APPROACH (as implemented below, method A)
 *   1. n = nums.length, finalAns = 0.
 *   2. For i from 0 to (1 << n) - 1: this i is one subset; bit j of i is set <=> nums[j] is in it.
 *   3. ans = 0; for every j whose bit is set (((i >> j) & 1) == 1): ans ^= nums[j].
 *   4. finalAns += ans (the XOR total of this subset).
 *   5. Return finalAns.
 *
 * DRY RUN   nums = [5,1,6]   (the input main() uses; bit 0 = 5, bit 1 = 1, bit 2 = 6)
 *   i  (binary)   subset     XOR total
 *   0  (000)      {}         0
 *   1  (001)      {5}        5
 *   2  (010)      {1}        1
 *   3  (011)      {5,1}      5 ^ 1 = 4
 *   4  (100)      {6}        6
 *   5  (101)      {5,6}      5 ^ 6 = 3
 *   6  (110)      {1,6}      1 ^ 6 = 7
 *   7  (111)      {5,1,6}    5 ^ 1 ^ 6 = 2
 *   finalAns = 0 + 5 + 1 + 4 + 6 + 3 + 7 + 2 = 28
 *
 * COMPLEXITY
 *   A: Time O(n * 2^n) - 2^n subsets, each rebuilt with n bit tests (at most 12 * 4096 =
 *      49152 steps here)
 *      Space O(1) - a few ints
 *   B: Time O(n * 2^n), Space O(n * 2^n) because it stores every subset
 *   (The bit-by-bit idea above would need only O(n) time.)
 *
 * GOTCHAS
 *   - Equal values are different elements: [5,5] gives 10 (subsets {}, [5], [5], [5,5] have
 *     totals 0, 5, 5, 0), not 5.
 *   - The sum cannot overflow an int: every XOR total is below 32 (values are at most 20)
 *     and there are at most 4096 subsets.
 *   - 1 << n is safe here because n <= 12; it would overflow int for n >= 31.
 *
 * STATUS
 *   Known issue: subsetXORSumOptimal() does not compute the XOR sum. Despite its name it is
 *   the LC 78 Subsets loop: it returns the 2^n subsets as a List&lt;List&lt;Integer>> (for [1,3]:
 *   [[], [1], [3], [1, 3]]) instead of the int 6, and it never XORs anything.
 *   subsetXORSum() - the method main() runs, with the signature LeetCode expects - is correct.
 *   The code is left unchanged on purpose.
 *
 * RELATED
 *   - LC 78 Subsets (in this repo) - the same bitmask loop listing the subsets themselves;
 *     subsetXORSumOptimal above has exactly that code.
 * </pre>
 */
public class LC1863SumOfAllSubsetXORTotals {

    public static void main(String[] args) {
        // example 2: the 8 subset totals 0,5,1,4,6,3,7,2 add up to 28 - prints 28
        int[] nums = {5,1,6};
        System.out.println(subsetXORSum(nums));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }
    // Approach A: every subset is a bitmask i in [0, 2^n); XOR its members and add the totals
    // up - O(n * 2^n)
    public static int subsetXORSum(int[] nums) {
        int n = nums.length;
        // running sum of the XOR totals of all subsets
        int finalAns = 0;
        // each i is one subset: bit j of i set means nums[j] is in it (i = 0 is the empty subset)
        for (int i = 0; i < (1 << n); i++) {
            // XOR total of this subset
            int ans = 0;
            for (int j = 0; j < n; j++) {
                // is nums[j] in the subset?
                if(((i >> j) & 1) == 1){
                    ans ^= nums[j];
                }
            }
            finalAns += ans;
        }
        return finalAns;
    }

    // Despite its name this does NOT compute the XOR sum: it is the LC 78 Subsets loop and returns
    // every subset as a list (e.g. [[], [1], [3], [1, 3]] for [1,3]) - see STATUS
    public static List<List<Integer>> subsetXORSumOptimal(int[] nums) {
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < (1 << n); i++) {
            // start the subset for mask i
            ans.add(new ArrayList<>());
            for (int j = 0; j < n; j++) {
                if(((i >> j) & 1) == 1){
                    // nums[j] is in the subset: append it to the list just started
                    ans.get(ans.size() - 1).add(nums[j]);
                }
            }
        }
        return ans;
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 1863. Sum of All Subset XOR Totals)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the three official examples + edge cases (one element, equal values, the largest
    // allowed length); expected values from the statement or from an independent brute force
    // (itertools.combinations over positions, XOR of each subset) in the scratch run dir.
    // The last case calls subsetXORSumOptimal on example 1: by its name it should return the sum.
    //   Expected result: 7/8 passed
    //   The 1 failure is the known issue described in the STATUS of
    //   LC1863SumOfAllSubsetXORTotals.java: subsetXORSumOptimal returns the list of all subsets.
    // ==========================================================================
    private static void runTests() {
        // official examples
        check("example 1: [1,3]", 6, () -> sum(1, 3));
        check("example 2: [5,1,6]", 28, () -> sum(5, 1, 6));
        check("example 3: [3,4,5,6,7,8]", 480, () -> sum(3, 4, 5, 6, 7, 8));
        // edge: one element - subsets {} (0) and [7] (7)
        check("one element", 7, () -> sum(7));
        // edge: equal values are different elements - subsets {}, [5], [5], [5,5] -> 0+5+5+0
        check("equal values [5,5]", 10, () -> sum(5, 5));
        // edge: the largest allowed length, 12, with the largest value 20 everywhere
        check("12 x 20", 40960, () -> sum(20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20, 20));
        // edge: the largest allowed length with distinct values 1..12
        check("1..12", 30720, () -> sum(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12));
        // known issue: by its name this should also return 6, but it returns the subsets
        check("subsetXORSumOptimal([1,3])", 6,
                () -> LC1863SumOfAllSubsetXORTotals.subsetXORSumOptimal(new int[]{1, 3}));
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

    // calls the solution (a static method) with the given numbers
    private static int sum(int... nums) {
        return LC1863SumOfAllSubsetXORTotals.subsetXORSum(nums);
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
