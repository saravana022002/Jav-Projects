package Leetcode.Ch05BitManipulationAsASetEncoding;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 137. Single Number II   [Medium]
 * https://leetcode.com/problems/single-number-ii/
 * Topics: Array, Bit Manipulation
 * Book: Algorithmic Thinking - ch. 5 Bit manipulation as a set encoding, Practice #7
 *
 * PROBLEM
 *   In the int array nums every value appears exactly three times, except for one value that
 *   appears only once. Return that value. The task asks for linear time and constant extra
 *   space.
 *
 * EXAMPLES
 *   nums = [2,2,3,2]          ->  3     2 comes three times, 3 only once
 *   nums = [0,1,0,1,0,1,99]   ->  99    0 and 1 come three times each
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 3 * 10^4,   -2^31 <= nums[i] <= 2^31 - 1
 *   every element appears exactly three times except for one element, which appears once
 *
 * INTUITION
 *   Plain XOR (LC 136) fails here: x ^ x ^ x = x, so the triples do not cancel. Look at ONE
 *   bit position at a time instead. A value that appears three times adds its bit to that
 *   position's total three times, so it adds 0 or 3, which is 0 modulo 3. Only the single
 *   value is left over: the number of ones at the position is 3k, or 3k + 1 when the single
 *   value has that bit set. So (ones at this position) % 3 is exactly the single value's
 *   bit. Do it for all 32 positions and glue the bits together.
 *
 * APPROACH (as implemented below)
 *   1. ans = 0.
 *   2. For every bit position i from 0 to 31: count = the number of elements with bit i set,
 *      summed as (num >> i) & 1 over the array.
 *   3. If count % 3 == 1 the single value has bit i set: ans |= (1 << i).
 *   4. Return ans.
 *
 * DRY RUN   nums = [2,2,3,2]   (binary: 2 = 10, 3 = 11; the input main() uses)
 *   i = 0   bits are 0,0,1,0   count = 1   1 % 3 = 1 -> set bit 0    ans = 1
 *   i = 1   bits are 1,1,1,1   count = 4   4 % 3 = 1 -> set bit 1    ans = 1 | 2 = 3
 *           (the three 2s add 3, the 3 adds 1)
 *   i = 2 .. 31   no element has these bits set, count = 0           ans stays 3
 *   return 3
 *
 * COMPLEXITY
 *   Time : O(32 * n) = O(n) - 32 passes over the array, one shift and one AND per element
 *   Space: O(1) - count and ans; the input is not touched
 *
 * GOTCHAS
 *   - Bit 31 is the sign bit and the loop must reach it. (num >> 31) & 1 is 1 for a negative
 *     number (the & 1 discards the sign extension of >>), and 1 << 31 is Integer.MIN_VALUE,
 *     so a negative single value comes out right with no special case.
 *   - Negative values that appear three times are fine too: bit 31 is counted 3 times, which
 *     is 0 modulo 3.
 *   - count never overflows: it is at most nums.length <= 3 * 10^4.
 *
 * RELATED
 *   - LC 136 Single Number (in this repo) - the others appear twice, so plain XOR works.
 *   - LC 260 Single Number III (in this repo) - two values appear once; XOR plus splitting the
 *     numbers by one differing bit.
 * </pre>
 */
public class LC137SingleNumberII {
    public static void main(String[] args) {
        // example 1: 2 appears three times, 3 once - prints 3
        int[] nums = {2,2,3,2};
        System.out.println(singleNumber(nums));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }
    // Returns the element that appears once; every other element appears exactly three times
    public static int singleNumber(int[] nums) {
        int count;
        int ans = 0;
        // rebuild the answer one bit position at a time (an int has 32 bits)
        for (int i = 0; i < 32; i++) {
            // count = how many elements have bit i set
            count = 0;
            for (int num : nums) {
                count += (num >> i) & 1;
            }

            // each triple adds 0 or 3 to count (0 mod 3), so a remainder of 1 can only come
            // from the single value: it has bit i set
            if(count % 3 == 1){
                ans |= (1 << i);
            }
        }
        return ans;
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 137. Single Number II)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the two official examples + edge cases (one element, negative single value, negative
    // triples, Integer.MIN_VALUE / MAX_VALUE, the largest allowed array); expected values from
    // the statement or known by construction (checked with a HashMap count in the scratch dir).
    //   Expected result: 9/9 passed
    // ==========================================================================
    private static void runTests() {
        // official examples
        check("example 1: [2,2,3,2]", 3, () -> solve(2, 2, 3, 2));
        check("example 2: [0,1,0,1,0,1,99]", 99, () -> solve(0, 1, 0, 1, 0, 1, 99));
        // edge: the smallest array, just the single value
        check("one element", 7, () -> solve(7));
        // edge: negative single value (bit 31 must be rebuilt)
        check("negative single value", -9, () -> solve(-9, 5, 5, 5));
        // edge: negative triple, positive single value (bit 31 is counted 3 times -> 0)
        check("negative triple", 6, () -> solve(-4, 6, -4, -4));
        // edge: the extreme int values in both roles
        int min = Integer.MIN_VALUE, max = Integer.MAX_VALUE;
        check("single is Integer.MIN_VALUE", min, () -> solve(min, max, max, max));
        check("single is Integer.MAX_VALUE", max, () -> solve(max, min, min, min));
        // edge: the single value sits between the copies of the triple
        check("single in the middle", 4, () -> solve(8, 8, 4, 8));
        // edge: the largest allowed size, 29998 elements = 9999 values x 3 copies + 1 single;
        // the values are k * 7 - 30000 for k = 0..9998 (distinct), the single is 123456789
        int[] big = new int[29998];
        int p = 0;
        for (int copy = 0; copy < 3; copy++) {
            for (int k = 0; k < 9999; k++) {
                big[p++] = k * 7 - 30000;
            }
        }
        big[p] = 123456789;
        check("maximum size array", 123456789, () -> solve(big));
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

    // calls the solution (a static method) with the given numbers
    private static int solve(int... nums) {
        return LC137SingleNumberII.singleNumber(nums);
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
