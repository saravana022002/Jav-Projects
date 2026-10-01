package Leetcode.Ch01ComplexityAmortisationAndCounting;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 204. Count Primes   [Medium]
 * https://leetcode.com/problems/count-primes/
 * Topics: Array, Math, Enumeration, Number Theory, Primality Test, Sieve Theory, Prime Number Sieve
 * Book: Algorithmic Thinking - ch. 1 Complexity, amortisation, and counting, Practice #12
 *       also: ch. 80 Number theory (#8)
 *
 * PROBLEM
 *   Count the primes that are smaller than n. The number n itself is never counted, even
 *   when it is prime. (A prime is a number above 1 that only 1 and itself divide evenly.)
 *
 * EXAMPLES
 *   n = 10  ->  4     the primes below 10 are 2, 3, 5 and 7
 *   n = 0   ->  0     nothing below 0 is prime
 *   n = 1   ->  0     the smallest prime is 2, so there is none below 1
 *
 * CONSTRAINTS
 *   0 <= n <= 5 * 10^6
 *
 * INTUITION
 *   A composite number i can be written as a * b with 1 < a <= b, so its smaller factor a
 *   is at most sqrt(i). To prove i composite it is therefore enough to try divisors up to
 *   sqrt(i); if none divides it, i is prime. Two cheap savings on top of that:
 *     - 2 is the only even prime, so every other even number is rejected at once;
 *     - an odd number has no even divisors, so only odd divisors (3, 5, 7, ...) are tried.
 *   Odd divisors that are themselves composite are harmless: they only divide i when a
 *   smaller prime already does, and that one is found first.
 *
 * APPROACH (as implemented below)
 *   1. count = 0. Look at every i from 2 up to n - 1.
 *   2. isPrime starts as true. If i is even and not 2, set isPrime = false and stop there.
 *   3. Otherwise (i is 2 or odd) try j = 3, 5, 7, ... while j * j <= i. The first j with
 *      i % j == 0 sets isPrime = false and breaks out.
 *   4. If isPrime is still true, count++.
 *   5. Return count.
 *
 * DRY RUN   n = 10 (the first example; main() runs the same code with n = 5000000)
 *   i=2  the "i != 2" guard lets it in; 3 * 3 = 9 > 2, no divisor tried  -> prime, count = 1
 *   i=3  odd; 9 > 3, no divisor tried                                    -> prime, count = 2
 *   i=4  even                                                            -> not prime
 *   i=5  odd; 9 > 5                                                      -> prime, count = 3
 *   i=6  even                                                            -> not prime
 *   i=7  odd; 9 > 7                                                      -> prime, count = 4
 *   i=8  even                                                            -> not prime
 *   i=9  odd; j = 3: 9 <= 9 and 9 % 3 == 0                               -> not prime
 *   i reaches 10, the loop ends, return 4
 *
 * COMPLEXITY
 *   Time : O(n * sqrt(n)) - each odd i tries at most about sqrt(i) / 2 divisors. Composites
 *          usually stop at their smallest factor, so mainly the primes (about n / ln n of
 *          them) pay the full cost. main() with n = 5000000 finishes in well under a second.
 *   Space: O(1) - a few int variables, no table
 *   (A Sieve of Eratosthenes would take O(n log log n) time and O(n) space - see RELATED.)
 *
 * GOTCHAS
 *   - "Strictly less than n": countPrimes(2) = 0 and countPrimes(3) = 1. For n = 0, 1 and 2
 *     the outer loop never runs.
 *   - 2 is even yet prime, which is why the even-number test reads "i != 2 && i % 2 == 0".
 *   - j * j <= i avoids Math.sqrt and floating point. With n <= 5 * 10^6, j stays below
 *     about 2240, so j * j cannot overflow an int.
 *
 * RELATED
 *   - Sieve of Eratosthenes (the hint on LeetCode): instead of testing every number, cross
 *     out the multiples of each prime found - the standard faster method for this problem.
 *   - LC 2761 Prime Pairs With Target Sum - needs all primes up to n, which a sieve gives.
 *   - LC 263 Ugly Number - a number-theory check built from simple divisibility tests.
 * </pre>
 */
public class LC204CountPrimes {
    public static void main(String[] args) {
        // largest n allowed by the constraints; prints 348513 (the primes below 5 million)
        System.out.println(countPrimes(5000000));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }

    // Returns how many primes are < n: trial division by odd numbers up to sqrt(i)
    public static int countPrimes(int n) {
        int count = 0;
        for (int i = 2; i < n; i++) {
            boolean isPrime = true;
            // every even number except 2 is composite - no divisor search needed
            if(i != 2 && i % 2 == 0){
                isPrime = false;
            }else {
                // i is 2 or odd: try odd divisors only, and stop once j * j > i because a
                // composite i always has a divisor <= sqrt(i)
                for (int j = 3; j * j <= i; j += 2) {
                    if (i % j == 0) {
                        isPrime = false;
                        break;
                    }
                }
            }
            if(isPrime){
                count++;
            }
        }
        return count;
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 204. Count Primes)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the three official examples + edge cases; expected values from the statement or
    // checked against an independent Sieve of Eratosthenes (written outside the repo).
    //   Expected result: 7/7 passed
    // ==========================================================================
    private static void runTests() {
        // official examples
        check("example 1: n = 10 -> 2, 3, 5, 7", 4, () -> LC204CountPrimes.countPrimes(10));
        check("example 2: n = 0", 0, () -> LC204CountPrimes.countPrimes(0));
        check("example 3: n = 1", 0, () -> LC204CountPrimes.countPrimes(1));
        // edge: n = 2 is prime but "strictly less than n" leaves nothing
        check("n = 2 is not counted itself", 0, () -> LC204CountPrimes.countPrimes(2));
        // edge: the first prime, 2, is the only even prime
        check("n = 3 -> only 2", 1, () -> LC204CountPrimes.countPrimes(3));
        // edge: a mid-size value (25 primes below 100)
        check("n = 100", 25, () -> LC204CountPrimes.countPrimes(100));
        // edge: the largest n allowed by the constraints (5 * 10^6)
        check("n = 5000000", 348513, () -> LC204CountPrimes.countPrimes(5000000));
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
