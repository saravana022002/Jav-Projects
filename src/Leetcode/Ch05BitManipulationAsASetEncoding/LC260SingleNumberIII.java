package Leetcode.Ch05BitManipulationAsASetEncoding;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 260. Single Number III   [Medium]
 * https://leetcode.com/problems/single-number-iii/
 * Topics: Array, Bit Manipulation
 * Book: Algorithmic Thinking - ch. 5 Bit manipulation as a set encoding, Practice #6
 *
 * PROBLEM
 *   In the int array nums exactly two values appear once and every other value appears
 *   exactly twice. Return the two values that appear once, in any order. The task asks for
 *   linear time and constant extra space.
 *
 * EXAMPLES
 *   nums = [1,2,1,3,2,5]  ->  [3,5]    1 and 2 come twice; [5,3] is accepted too
 *   nums = [-1,0]         ->  [-1,0]   both values are the lonely ones
 *   nums = [0,1]          ->  [1,0]
 *
 * CONSTRAINTS
 *   2 <= nums.length <= 3 * 10^4,   -2^31 <= nums[i] <= 2^31 - 1
 *   every value appears twice, except two values that appear once
 *
 * INTUITION
 *   XOR-ing the whole array cancels every pair and leaves a ^ b, where a and b are the two
 *   lonely values (this is LC 136 with two survivors instead of one). That alone does not
 *   tell a and b apart, but it says something: a != b, so a ^ b is not 0 and has at least
 *   one 1 bit, and at that bit exactly one of a and b has a 1.
 *   Use such a bit as a divider and split ALL numbers into two groups: bit set / bit clear.
 *     - the two copies of any paired value are equal, so they land in the SAME group;
 *     - a and b differ at that bit, so they land in DIFFERENT groups.
 *   Each group is now "pairs plus one lonely value". XOR-ing a group cancels its pairs and
 *   leaves its lonely value - once more the LC 136 trick, on each group.
 *   The lowest set bit of x is x & -x: in two's complement -x flips every bit above the
 *   lowest 1 and keeps that 1 and the zeros below it, so the AND leaves just that one bit
 *   (x = 110 gives 010).
 *
 * APPROACH (as implemented below)
 *   1. xor = XOR of all elements; the pairs cancel, so xor = a ^ b.
 *   2. diff = xor & -xor: the lowest set bit of xor, a bit where a and b differ.
 *   3. a = 0, b = 0. For every num: if (num & diff) == 0 then a ^= num, else b ^= num.
 *   4. Return {a, b}; each holds one of the lonely values.
 *
 * DRY RUN   nums = [1,2,1,3,2,5]   (the input main() uses)
 *   step 1  xor = 1^2^1^3^2^5 = 6 (binary 110)   - that is 3 ^ 5 = 011 ^ 101
 *   step 2  diff = 6 & -6 = 2 (binary 010)       - 3 has this bit, 5 does not
 *   step 3  num  num & 2   group   running value
 *           1    0         a       a = 1
 *           2    2         b       b = 2
 *           1    0         a       a = 1 ^ 1 = 0       <- the pair of 1s cancelled
 *           3    2         b       b = 2 ^ 3 = 1
 *           2    2         b       b = 1 ^ 2 = 3       <- the pair of 2s cancelled
 *           5    0         a       a = 0 ^ 5 = 5
 *   return [5, 3]
 *
 * COMPLEXITY
 *   Time : O(n) - two passes over the array
 *   Space: O(1) - xor, diff, a and b (the 2-element result array aside)
 *
 * GOTCHAS
 *   - The order of the result is not fixed: a is the lonely value whose diff bit is 0. For
 *     [-1,0] this returns [0,-1], the reverse of the example, which is accepted ("any order").
 *   - Integer.MIN_VALUE as the xor still works: -xor overflows back to MIN_VALUE and the AND
 *     is MIN_VALUE, the only set bit. E.g. nums = [MIN_VALUE, 0, 7, 7] splits on the sign bit.
 *   - The parentheses in (num & diff) == 0 are needed: == binds tighter than & in Java.
 *
 * RELATED
 *   - LC 136 Single Number (in this repo) - one lonely value, plain XOR.
 *   - LC 137 Single Number II (in this repo) - every other value appears three times.
 * </pre>
 */
public class LC260SingleNumberIII {
    public static void main(String[] args) {
        // example 1: the lonely values are 3 and 5 - prints [5, 3] (any order is accepted)
        int[] nums = {1,2,1,3,2,5};
        System.out.println(Arrays.toString(singleNumber(nums)));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }
    // Returns the two values that appear once (every other value appears twice), in no set order
    public static int[] singleNumber(int[] nums) {
        // the pairs cancel, so xor = a ^ b for the two lonely values a and b
        int xor = 0;
        for (int num : nums) xor ^= num;
        // lowest set bit of xor: a bit where a and b differ (xor is not 0 because a != b)
        int diff = xor & -xor;
        // split the numbers by that bit and XOR each group on its own
        int a = 0, b = 0;
        for (int num : nums) {
            if ((num & diff) == 0) {
                // bit clear: this group holds whole pairs and exactly one lonely value
                a ^= num;
            } else {
                // bit set: the other group
                b ^= num;
            }
        }
        return new int[]{a, b};
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 260. Single Number III)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the three official examples + edge cases (extreme int values, the two lonely values
    // differing only in the sign bit, negatives, the largest allowed array); expected values from
    // the statement or known by construction (checked with a HashMap count in the scratch dir).
    // The answer may come back in any order, so the returned pair is sorted before comparing and
    // every expected pair below is written in ascending order.
    //   Expected result: 8/8 passed
    // ==========================================================================
    private static void runTests() {
        // official examples
        check("example 1: [1,2,1,3,2,5]", new int[]{3, 5}, () -> solve(1, 2, 1, 3, 2, 5));
        check("example 2: [-1,0]", new int[]{-1, 0}, () -> solve(-1, 0));
        check("example 3: [0,1]", new int[]{0, 1}, () -> solve(0, 1));
        // edge: the two extreme ints, they differ in every bit
        check("MIN_VALUE and MAX_VALUE", new int[]{Integer.MIN_VALUE, Integer.MAX_VALUE},
                () -> solve(Integer.MAX_VALUE, Integer.MIN_VALUE));
        // edge: the lonely values differ only in the sign bit, so a ^ b = Integer.MIN_VALUE
        check("differ only in the sign bit", new int[]{Integer.MIN_VALUE, 0},
                () -> solve(Integer.MIN_VALUE, 0, 7, 7));
        // edge: negative pairs, one lonely value negative and one positive
        check("negatives", new int[]{-5, 9}, () -> solve(-3, -3, 9, -8, -8, -5));
        // edge: the lonely values 0 and 1 sit between two pairs (5, 5 and 6, 6)
        check("lonely 0 and 1 between pairs", new int[]{0, 1}, () -> solve(5, 5, 0, 6, 6, 1));
        // edge: the largest allowed size, 30000 elements = 14999 pairs + 2 lonely values;
        // the pairs are k * 3 - 20000 for k = 0..14998, the lonely values are -1000000 and 1000000
        int[] big = new int[30000];
        int p = 0;
        for (int copy = 0; copy < 2; copy++) {
            for (int k = 0; k < 14999; k++) {
                big[p++] = k * 3 - 20000;
            }
        }
        big[p++] = 1000000;
        big[p] = -1000000;
        check("maximum size array", new int[]{-1000000, 1000000}, () -> solve(big));
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

    // calls the solution (a static method) and sorts the pair: the answer may be in any order
    private static int[] solve(int... nums) {
        int[] pair = LC260SingleNumberIII.singleNumber(nums);
        int[] sorted = pair.clone();
        Arrays.sort(sorted);
        return sorted;
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
