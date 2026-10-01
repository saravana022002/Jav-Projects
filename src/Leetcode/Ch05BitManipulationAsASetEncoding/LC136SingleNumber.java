package Leetcode.Ch05BitManipulationAsASetEncoding;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 136. Single Number   [Easy]
 * https://leetcode.com/problems/single-number/
 * Topics: Array, Bit Manipulation
 * Book: Algorithmic Thinking - ch. 5 Bit manipulation as a set encoding, Practice #4
 *
 * PROBLEM
 *   You get a non-empty int array in which every value appears exactly twice, except for
 *   one value that appears only once. Return that lonely value. The task asks for linear
 *   time and only constant extra space, so a HashMap of counts or a sort is not allowed.
 *
 * EXAMPLES
 *   nums = [2,2,1]      ->  1     the two 2s pair up, 1 has no partner
 *   nums = [4,1,2,1,2]  ->  4     1 and 2 come twice, 4 only once
 *   nums = [1]          ->  1     a one-element array is its own answer
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 3 * 10^4,   -3 * 10^4 <= nums[i] <= 3 * 10^4
 *   every element appears twice except for exactly one element, which appears once
 *
 * INTUITION
 *   XOR (^) is "different bits give 1, equal bits give 0", and it has three useful facts:
 *     x ^ x = 0            a value cancels itself
 *     x ^ 0 = x            0 changes nothing
 *     a ^ b = b ^ a        order and grouping do not matter: (a ^ b) ^ c = a ^ (b ^ c)
 *   So XOR-ing the whole array, in whatever order, wipes out every pair (two copies of a
 *   value give 0 wherever they sit) and what survives is the one value without a partner.
 *   One int is all the memory needed.
 *
 * APPROACH (as implemented below)
 *   1. Start with num = 0, the XOR identity.
 *   2. For every element j: num ^= j.
 *   3. Return num - every pair has cancelled, only the single value is left.
 *
 * DRY RUN   nums = [4,1,2,1,2]   (binary in brackets)
 *   start      num = 0                 (000)
 *   j = 4      num = 0 ^ 4 = 4         (100)
 *   j = 1      num = 4 ^ 1 = 5         (101)
 *   j = 2      num = 5 ^ 2 = 7         (111)
 *   j = 1      num = 7 ^ 1 = 6         (110)   <- the first pair (1, 1) has cancelled
 *   j = 2      num = 6 ^ 2 = 4         (100)   <- the pair (2, 2) has cancelled too
 *   return 4
 *
 * COMPLEXITY
 *   Time : O(n) - one pass over the array, one XOR per element
 *   Space: O(1) - a single int accumulator
 *
 * GOTCHAS
 *   - Negative values are fine: XOR works on the two's-complement bits, so -5 ^ -5 = 0 too.
 *   - The trick needs every other value to appear an EVEN number of times. With three copies
 *     (LC 137 Single Number II) plain XOR no longer isolates the single value.
 *   - Nothing checks the promise of the problem: if two values appear once, the result is
 *     their XOR (for [1,2] it returns 3), which is where LC 260 starts.
 *
 * STATUS
 *   The solution is correct. main() is empty (it prints nothing), and singleNumber is an
 *   instance method, so a caller needs an object: new LC136SingleNumber().singleNumber(nums).
 *   main().java runs the official examples and some edge cases.
 *
 * RELATED
 *   - LC 137 Single Number II (in this repo) - the others appear three times; count each
 *     bit position modulo 3 instead.
 *   - LC 260 Single Number III (in this repo) - two lonely values; XOR everything, then split
 *     the numbers by one set bit of that XOR.
 *   - LC 268 Missing Number - XOR the indices with the values; the missing number is left.
 *
 * SEE ALSO
 *   src/Practice/BitManipulation/LC136SingleNumber.java - the same XOR loop written as a
 *   static method (num = num ^ j instead of num ^= j)
 * </pre>
 */
public class LC136SingleNumber {
    public static void main(String[] args) {
        // intentionally empty: nothing runs from here (see STATUS)
        // e.g. new LC136SingleNumber().singleNumber(new int[]{4,1,2,1,2}) returns 4
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }

    // Returns the value that appears once: XOR of all elements, pairs cancel to 0
    public int singleNumber(int[] nums) {
        // 0 is the XOR identity (x ^ 0 = x), so it is a neutral starting value
        int num = 0;
        for (int j : nums) {
            // x ^ x = 0: a pair wipes itself out, wherever the two copies sit
            num ^= j;
        }
        // only the value without a partner is left
        return num;
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 136. Single Number)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the three official examples + edge cases (the answer is 0, negative values, the
    // largest allowed array); expected values from the statement or known by construction.
    //   Expected result: 6/6 passed
    // ==========================================================================
    private static void runTests() {
        // singleNumber is an instance method
        LC136SingleNumber s = new LC136SingleNumber();
        // official examples
        check("example 1: [2,2,1]", 1, () -> s.singleNumber(new int[]{2, 2, 1}));
        check("example 2: [4,1,2,1,2]", 4, () -> s.singleNumber(new int[]{4, 1, 2, 1, 2}));
        check("example 3: [1]", 1, () -> s.singleNumber(new int[]{1}));
        // edge: the lonely value is 0 (XOR with 0 changes nothing, so it must still be found)
        check("single value is 0", 0, () -> s.singleNumber(new int[]{3, 0, 3}));
        // edge: negative pairs and a negative answer at the lower bound of the constraints
        check("negatives, answer -30000", -30000,
                () -> s.singleNumber(new int[]{-5, 2, -5, 2, -30000}));
        // edge: the largest allowed size, 29999 elements; the pairs are the values -7500 ..
        // 7498 (each twice, in the same order both times) and 30000 sits alone in the middle
        int[] big = new int[29999];
        for (int k = 0; k < 14999; k++) {
            big[k] = k - 7500;
            big[15000 + k] = k - 7500;
        }
        big[14999] = 30000;
        check("maximum size array, answer 30000", 30000, () -> s.singleNumber(big));
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
