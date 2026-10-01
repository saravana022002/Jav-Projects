package Leetcode.Ch05BitManipulationAsASetEncoding;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 231. Power of Two   [Easy]
 * https://leetcode.com/problems/power-of-two/
 * Topics: Math, Bit Manipulation, Recursion
 * Book: Algorithmic Thinking - ch. 5 Bit manipulation as a set encoding, Practice #2
 *
 * PROBLEM
 *   Given any 32-bit integer n (it may be 0 or negative), return true when n equals 2^x for
 *   some integer x >= 0 (1, 2, 4, 8, ...), otherwise false.
 *
 * EXAMPLES
 *   n = 1   ->  true     2^0
 *   n = 16  ->  true     2^4
 *   n = 3   ->  false
 *
 * CONSTRAINTS
 *   -2^31 <= n <= 2^31 - 1
 *   Follow up: could you solve it without loops / recursion?
 *
 * INTUITION
 *   In binary a power of two is a single 1 followed by zeros (8 = 1000). Subtracting 1 turns
 *   that 1 into 0 and every zero below it into 1 (7 = 0111), so n is a power of two exactly
 *   when n - 1 is a solid run of 1 bits (or 0, for n = 1). Approach A checks that run bit by
 *   bit; approach B notices that n & (n - 1) erases the lowest 1 bit, and a power of two has
 *   no other 1 bit, so the result is 0 - no loop needed.
 *
 * APPROACHES IN THIS FILE
 *   A. isPowerOfTwo()        - n - 1 must be all 1 bits, tested with a shift loop - O(log n);
 *                              wrong for negative n (see STATUS)  <- main() runs this one
 *   B. isPowerOfTwoOptimal() - n > 0 && (n & (n - 1)) == 0 - O(1), answers the follow-up
 *   (APPROACH and DRY RUN below describe A.)
 *
 * APPROACH (as implemented below, method A)
 *   1. n == 0 -> return false.
 *   2. n = n - 1.   (for a power of two this is a run of 1 bits)
 *   3. While n > 0: if the lowest bit is 0, (n & 1) == 0, return false - a 0 inside the run
 *      means n had another 1 bit; otherwise n = n >> 1 and look at the next bit.
 *   4. Return true.
 *
 * DRY RUN   n = 8, then n = 12
 *   n = 8 :  n - 1 = 7 (binary 111)
 *            7 & 1 = 1, n = 7 >> 1 = 3 ;  3 & 1 = 1, n = 1 ;  1 & 1 = 1, n = 0
 *            the loop ends                                                  -> true
 *   n = 12:  n - 1 = 11 (binary 1011)
 *            11 & 1 = 1, n = 5 ;  5 & 1 = 1, n = 2 ;  2 & 1 = 0
 *            -> return false (the run of 1s has a hole: 12 = 1100 has two 1 bits)
 *   n = -16 (what main() passes): n - 1 = -17, which is not > 0, so the loop is skipped and
 *            true comes back - wrong, see STATUS.
 *
 * COMPLEXITY
 *   A: Time O(log n) - at most 31 loop turns, one per bit of n - 1;  Space O(1)
 *   B: Time O(1) - one subtraction, one AND, two comparisons;        Space O(1)
 *
 * GOTCHAS
 *   - The n == 0 guard in A is needed: 0 - 1 = -1 is not > 0, so the loop would not run and
 *     0 would be reported as a power of two.
 *   - n = 1 works by itself: 1 - 1 = 0, the loop is skipped, true (1 = 2^0).
 *   - Integer.MIN_VALUE - 1 overflows to Integer.MAX_VALUE (31 one bits), so A answers true
 *     for it too. B is safe: n > 0 is false for it.
 *   - In B the n > 0 test matters: without it 0 would pass (0 & -1 == 0) and so would
 *     Integer.MIN_VALUE (a single 1 bit, the sign bit).
 *
 * STATUS
 *   Known issue: isPowerOfTwo() (approach A) returns true for EVERY negative n. After
 *   n - 1 the value is still negative, so while(n > 0) never runs and the final return true is
 *   reached. Negative n is valid input (-2^31 <= n): -16 -> true (correct: false, and that is
 *   exactly what main() prints), -1 -> true, Integer.MIN_VALUE -> true. Checked on all 2^32
 *   int values: A is right for 0 and every positive n and wrong for all 2^31 negative ones.
 *   isPowerOfTwoOptimal() (approach B) was right for every int. The code is left unchanged
 *   on purpose.
 *
 * RELATED
 *   - LC 191 Number of 1 Bits (in this repo) - a power of two has exactly one set bit.
 *   - LC 326 Power of Three / LC 342 Power of Four - the same question for other bases.
 *
 * SEE ALSO
 *   src/Practice/Math/LC231PowerOfTwo.java - the same problem with the halving approach (strip
 *   factors of 2 while n is positive and even, then n == 1); it answers false for negative n
 * </pre>
 */
public class LC231PowerOfTwo {
    public static void main(String[] args) {
        // -16 is not a power of two (correct answer: false), but approach A prints true -
        // see STATUS
        System.out.println(isPowerOfTwo(-16));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }
    // Approach A: n is a power of two exactly when n - 1 is all 1 bits (8 -> 7 = 111);
    // checked here bit by bit. KNOWN BUG: returns true for every negative n - see STATUS
    public static boolean isPowerOfTwo(int n) {
        // 0 is not a power of two (0 - 1 = -1 would skip the loop below and give true)
        if(n == 0){
            return false;
        }
        // a power of two minus 1 is a run of 1 bits (0 for n = 1)
        n =  n - 1;
        // peel bits off the low end; a negative n - 1 never enters this loop (the bug)
        while(n > 0){
            // a 0 bit inside the run: n had another 1 bit, so it is not a power of two
            if((n & 1) == 0){
                return false;
            }
            n = n >> 1;
        }
        return true;
    }


    // Approach B: a power of two has exactly one 1 bit and n & (n - 1) erases the lowest 1 bit,
    // so the result is 0 only for powers of two; n > 0 rules out 0 and the negatives.
    // No loop - O(1)
    public static boolean isPowerOfTwoOptimal(int n) {
        return n > 0 && (n & (n - 1)) == 0;
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 231. Power of Two)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the three official examples + edge cases (0, the largest power of two, the largest
    // int, and three negatives), each run through BOTH methods: isPowerOfTwo (the bit loop, "A")
    // and isPowerOfTwoOptimal (n > 0 && (n & (n - 1)) == 0, "B"). Expected values: the statement,
    // plus a reference over all 2^32 ints in the scratch run dir (n > 0 && one set bit).
    //   Expected result: 17/20 passed
    //   The 3 failures are the known issue described in the STATUS of LC231PowerOfTwo.java:
    //   isPowerOfTwo answers true for every negative n.
    // ==========================================================================
    private static void runTests() {
        // official examples
        both("example 1: n = 1", true, 1);
        both("example 2: n = 16", true, 16);
        both("example 3: n = 3", false, 3);
        // edge: zero is not a power of two
        both("n = 0", false, 0);
        // edge: 12 = 1100, two 1 bits
        both("n = 12", false, 12);
        // edge: the largest power of two that fits an int, 2^30
        both("n = 2^30", true, 1073741824);
        // edge: the largest int, 2^31 - 1 (all ones)
        both("n = 2^31 - 1", false, Integer.MAX_VALUE);
        // edge: negatives are never powers of two (-16 is the input of the file's main())
        both("n = -16", false, -16);
        both("n = -1", false, -1);
        both("n = Integer.MIN_VALUE", false, Integer.MIN_VALUE);
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

    // runs one input through both methods; the expected value is the same for both
    private static void both(String name, boolean expected, int n) {
        check("A isPowerOfTwo:        " + name, expected, () -> LC231PowerOfTwo.isPowerOfTwo(n));
        check("B isPowerOfTwoOptimal: " + name, expected,
                () -> LC231PowerOfTwo.isPowerOfTwoOptimal(n));
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
