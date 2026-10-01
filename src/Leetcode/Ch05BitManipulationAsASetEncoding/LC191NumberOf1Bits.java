package Leetcode.Ch05BitManipulationAsASetEncoding;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 191. Number of 1 Bits   [Easy]
 * https://leetcode.com/problems/number-of-1-bits/
 * Topics: Divide and Conquer, Bit Manipulation
 * Book: Algorithmic Thinking - ch. 5 Bit manipulation as a set encoding, Practice #1
 *
 * PROBLEM
 *   Given a positive integer n, return how many of the bits in its binary form are 1. This
 *   count is called the number of set bits, or the Hamming weight, of n.
 *
 * EXAMPLES
 *   n = 11          ->  3     binary 1011, three 1s
 *   n = 128         ->  1     binary 10000000, a single 1
 *   n = 2147483645  ->  30    binary 1111111111111111111111111111101, 31 bits with one 0
 *
 * CONSTRAINTS
 *   1 <= n <= 2^31 - 1
 *   Follow up: if the function is called many times, how would you optimize it?
 *
 * INTUITION
 *   Subtracting 1 from n flips its lowest 1 bit to 0 and turns every 0 below that bit into
 *   1. AND-ing the result with n therefore keeps everything above that bit as it was, and
 *   makes the bit itself and everything below it 0:
 *       n = 1100   n - 1 = 1011   n & (n - 1) = 1000      <- lowest 1 erased
 *   So n & (n - 1) is "n without its lowest set bit". Repeat until n becomes 0 and count the
 *   repeats: that is the number of set bits. The loop runs once per 1 bit, not once per bit
 *   position (128 needs 1 turn, not 8).
 *
 * APPROACH (as implemented below)
 *   1. count = 0.
 *   2. While n > 0: temp = n - 1; n = n & temp (erase the lowest set bit); count++.
 *   3. Return count.
 *
 * DRY RUN   n = 11   (binary 1011, the input main() uses)
 *   n = 1011   temp = 1010   n & temp = 1010   count = 1    <- lowest 1 erased
 *   n = 1010   temp = 1001   n & temp = 1000   count = 2
 *   n = 1000   temp = 0111   n & temp = 0000   count = 3
 *   n = 0 -> the loop stops, return 3
 *
 * COMPLEXITY
 *   Time : O(k) where k is the number of set bits (at most 31 here) - one loop turn per 1 bit
 *   Space: O(1) - count and temp
 *
 * GOTCHAS
 *   - The condition n > 0 is enough only because the constraints keep n between 1 and
 *     2^31 - 1. A negative int (sign bit set) is outside them: the loop would not even start
 *     and return 0 (for -1 it returns 0, not 32). n != 0 would also handle those.
 *   - n is the method's own copy, so shrinking it does not change the caller's variable.
 *   - The largest valid input, 2^31 - 1, has 31 set bits.
 *   - Follow-up (many calls): split n into bytes and read each byte's bit count from a table
 *     of 256 precomputed values (4 lookups per number). Not implemented in this file.
 *
 * RELATED
 *   - LC 231 Power of Two (in this repo) - a power of two has exactly one set bit, which is
 *     the same as n & (n - 1) == 0.
 *   - LC 338 Counting Bits (in this repo) - this count for every number from 0 to n; that
 *     file calls an identical hammingWeight.
 *   - LC 461 Hamming Distance - the number of set bits of x ^ y.
 * </pre>
 */
public class LC191NumberOf1Bits {
    public static void main(String[] args) {
        // example 1: 11 is 1011 in binary, three bits are set - prints 3
        int n = 11;
        System.out.println(hammingWeight(n));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }
    // Returns the number of 1 bits in n: n & (n - 1) erases the lowest set bit each turn
    public static int hammingWeight(int n) {
        int temp;
        // how many 1 bits have been erased so far
        int count = 0;
        // while n is not 0, at least one 1 bit is left
        while(n > 0){
            // n - 1: the lowest 1 becomes 0 and every 0 below it becomes 1
            temp = n - 1;
            // AND keeps the bits above the lowest 1 and clears that bit and everything below
            n = n & temp;
            count++;
        }
        return count;
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 191. Number of 1 Bits)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the three official examples + edge cases at the ends of the constraint range
    // (1 <= n <= 2^31 - 1) and two bit patterns; expected values from the statement or from
    // Python's bin(n).count("1") in the scratch run dir.
    //   Expected result: 8/8 passed
    // ==========================================================================
    private static void runTests() {
        // official examples
        check("example 1: n = 11", 3, () -> LC191NumberOf1Bits.hammingWeight(11));
        check("example 2: n = 128", 1, () -> LC191NumberOf1Bits.hammingWeight(128));
        check("example 3: n = 2147483645", 30, () -> LC191NumberOf1Bits.hammingWeight(2147483645));
        // edge: the smallest allowed value
        check("n = 1", 1, () -> LC191NumberOf1Bits.hammingWeight(1));
        // edge: the largest allowed value, 31 ones
        check("n = 2^31 - 1", 31, () -> LC191NumberOf1Bits.hammingWeight(2147483647));
        // edge: a power of two near the top, a single 1 followed by 30 zeros
        check("n = 2^30", 1, () -> LC191NumberOf1Bits.hammingWeight(1073741824));
        // edge: alternating bits 1010...101 (16 ones)
        check("n = 1431655765 (alternating bits)", 16,
                () -> LC191NumberOf1Bits.hammingWeight(1431655765));
        // edge: all ones in the lower 29 bits
        check("n = 2^29 - 1", 29, () -> LC191NumberOf1Bits.hammingWeight(536870911));
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
