package Leetcode.Ch05BitManipulationAsASetEncoding;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 338. Counting Bits   [Easy]
 * https://leetcode.com/problems/counting-bits/
 * Topics: Dynamic Programming, Bit Manipulation
 * Book: Algorithmic Thinking - ch. 5 Bit manipulation as a set encoding, Practice #3
 *
 * PROBLEM
 *   Given n, return an array ans of length n + 1 in which ans[i] is the number of 1 bits in
 *   the binary form of i, for every i from 0 to n. Built-in bit-count functions (such as
 *   __builtin_popcount in C++) are not meant to be used.
 *
 * EXAMPLES
 *   n = 2  ->  [0,1,1]           0 = 0,  1 = 1,  2 = 10
 *   n = 5  ->  [0,1,1,2,1,2]     3 = 11 has two 1s, 4 = 100 has one, 5 = 101 has two
 *
 * CONSTRAINTS
 *   0 <= n <= 10^5
 *   Follow up: an O(n log n) answer is easy; can you do it in O(n), in a single pass?
 *
 * INTUITION
 *   Simple version: count the 1 bits of each number on its own. Erasing the lowest 1 bit with
 *   x & (x - 1) takes one loop turn per 1 bit, so this costs O(log n) per number.
 *   Linear version: do not recount, reuse. i & (i - 1) is i without its lowest 1 bit, so it
 *   has exactly one 1 bit fewer, and it is smaller than i, so its answer already sits in the
 *   array: ans[i] = ans[i & (i - 1)] + 1, starting from ans[0] = 0. For i = 5 (101):
 *   5 & 4 = 4, so ans[5] = ans[4] + 1 = 2. That is approach B below (it has a bug, see STATUS).
 *
 * APPROACHES IN THIS FILE
 *   A. countBits()    - hammingWeight(i) for every i, one Kernighan loop each - O(n log n)
 *                       <- main() runs this one
 *   B. countBitsOpt() - ans[i] = ans[i & (i - 1)] + 1, reusing earlier answers - O(n) idea,
 *                       but its loop bounds are wrong (see STATUS)
 *   (APPROACH and DRY RUN below describe A.)
 *
 * APPROACH (as implemented below, method A)
 *   1. ans = new int[n + 1]: one slot for each of 0..n.
 *   2. For every i from 0 to n: ans[i] = hammingWeight(i).
 *   3. hammingWeight(x) counts loop turns of "x = x & (x - 1)" until x is 0, one turn per
 *      1 bit (the same method as LC 191).
 *   4. Return ans.
 *
 * DRY RUN   n = 5   (main() runs n = 2, which gives the first three values)
 *   i = 0   0     no turn                                        -> 0
 *   i = 1   1     1 & 0 = 0                           1 turn     -> 1
 *   i = 2   10    10 & 01 = 0                         1 turn     -> 1
 *   i = 3   11    11 & 10 = 10, then 10 & 01 = 0      2 turns    -> 2
 *   i = 4   100   100 & 011 = 0                       1 turn     -> 1
 *   i = 5   101   101 & 100 = 100, then 100 & 011 = 0 2 turns    -> 2
 *   ans = [0,1,1,2,1,2]
 *
 * COMPLEXITY
 *   A: Time O(n log n) - hammingWeight(i) takes one turn per 1 bit, at most 16 for i <= 10^5
 *      (about 815,000 turns in total for n = 10^5);  Space O(1) besides the answer array
 *   B: Time O(n) - one O(1) step per index;  Space O(1) besides the answer array
 *
 * GOTCHAS
 *   - The array has n + 1 slots, because both 0 and n are included.
 *   - hammingWeight(0) returns 0 without a turn (while(n > 0) is false), so ans[0] = 0.
 *
 * STATUS
 *   Known issue: countBitsOpt() (approach B, not called by main()) returns a wrong array for
 *   every n > 0. Two loop mistakes: it starts at i = 0, where 0 & -1 = 0 makes
 *   ans[0] = ans[0] + 1 = 1 (it should stay 0), and every later entry inherits that extra 1;
 *   and it stops at i < n, so ans[n] is never written and stays 0. Results:
 *   countBitsOpt(1) = [1, 0] (correct [0, 1]), countBitsOpt(2) = [1, 2, 0] (correct [0, 1, 1]),
 *   countBitsOpt(5) = [1, 2, 2, 3, 2, 0] (correct [0, 1, 1, 2, 1, 2]). Only n = 0 is right.
 *   Starting the loop at i = 1 and running it to i <= n would make it correct.
 *   countBits() (approach A, the one main() runs) is correct; the code is left unchanged on
 *   purpose.
 *
 * RELATED
 *   - LC 191 Number of 1 Bits (in this repo) - hammingWeight here is that solution.
 *   - LC 2859 Sum of Values at Indices With K Set Bits - needs exactly this table of bit
 *     counts for the indices.
 * </pre>
 */
public class LC338CountingBits {
    public static void main(String[] args) {
        // example 1: 0, 1 and 10 have 0, 1 and 1 set bits - prints [0, 1, 1]
        int n = 2;
        System.out.println(Arrays.toString(countBits(n)));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }

    // Approach A: count the set bits of every number from 0 to n separately - O(n log n)
    public static int[] countBits(int n) {
        // one slot for each of 0..n
        int[] ans = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            ans[i] = hammingWeight(i);
        }
        return ans;
    }

    // Number of 1 bits in n (the same code as LC 191): n & (n - 1) erases the lowest 1 bit
    public static int hammingWeight(int n) {
        int temp;
        int count = 0;
        while(n > 0){
            temp = n - 1;
            n = n & temp;
            count++;
        }
        return count;
    }
    // Approach B: ans[i] = ans[i & (i - 1)] + 1 reuses an earlier answer - an O(n) idea, but
    // the loop bounds are wrong: it should run from i = 1 to i <= n (see STATUS)
    public static int[] countBitsOpt(int n) {
        int[] ans = new int[n + 1];
        // i & (i - 1) is i without its lowest 1 bit: smaller than i, so already filled in.
        // BUG: starting at i = 0 sets ans[0] = 1, and stopping at i < n leaves ans[n] = 0
        for (int i = 0; i < n; i++) {
            ans[i] = ans[i & (i - 1)] +  1;
        }
        return ans;
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 338. Counting Bits)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the two official examples + edge cases (n = 0, n = 1, n = 16, the maximum
    // n = 10^5), each run through BOTH methods: countBits ("A", one bit count per number) and
    // countBitsOpt ("B", the ans[i & (i - 1)] + 1 recurrence). Expected arrays: the statement,
    // hand-checked small ones, and for n = 10^5 a reference built with Integer.bitCount (fine
    // here: the test may use the built-in, the solution may not).
    //   Expected result: 7/12 passed
    //   The 5 failures are the known issue described in the STATUS of LC338CountingBits.java:
    //   countBitsOpt is wrong for every n > 0 (wrong loop bounds).
    // ==========================================================================
    private static void runTests() {
        // official examples
        both("example 1: n = 2", new int[]{0, 1, 1}, 2);
        both("example 2: n = 5", new int[]{0, 1, 1, 2, 1, 2}, 5);
        // edge: n = 0, an array with the single entry 0
        both("n = 0", new int[]{0}, 0);
        // edge: n = 1
        both("n = 1", new int[]{0, 1}, 1);
        // edge: n = 16, a power of two (the last entry is 1)
        both("n = 16", new int[]{0, 1, 1, 2, 1, 2, 2, 3, 1, 2, 2, 3, 2, 3, 3, 4, 1}, 16);
        // edge: the maximum n = 10^5
        int[] reference = new int[100001];
        for (int i = 0; i <= 100000; i++) {
            reference[i] = Integer.bitCount(i);
        }
        both("n = 100000", reference, 100000);
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

    // runs one input through both methods; the expected array is the same for both
    private static void both(String name, int[] expected, int n) {
        check("A countBits:    " + name, expected, () -> LC338CountingBits.countBits(n));
        check("B countBitsOpt: " + name, expected, () -> LC338CountingBits.countBitsOpt(n));
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

    // arrays are printed in full except very long ones, which are cut to keep the output readable
    private static String show(Object o) {
        if (o instanceof int[]) {
            int[] a = (int[]) o;
            if (a.length <= 20) return Arrays.toString(a);
            return Arrays.toString(Arrays.copyOf(a, 20)) + "... (length " + a.length + ")";
        }
        if (o instanceof long[]) return Arrays.toString((long[]) o);
        if (o instanceof char[]) return Arrays.toString((char[]) o);
        if (o instanceof boolean[]) return Arrays.toString((boolean[]) o);
        if (o instanceof Object[]) return Arrays.deepToString((Object[]) o);
        return String.valueOf(o);
    }

    // ============================== END TESTS ===============================
}
