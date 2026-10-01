package Leetcode.ArithMetic;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

public class LC3658GCDofOddandEvenSums {
    public static void main(String[] args) {
        gcdOfOddEvenSums(8);
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }

    // Arithmetic series

    // Sn = n/2 *( 2 * a + (n - 1)*d)

    // for oddSum  =  n/2 * (2 + (2n - 2)) ==>  oddSum = n*n
    // for evenSum  =  n/2 * (4 + (2n - 2)) ==> evenSum = n*(n + 1)

    public static int gcdOfOddEvenSums(int n) {
        int oddSum = n * n;
        int evenSum = n * (n + 1);
        int temp;
        while(oddSum != 0){
            temp = evenSum;
            evenSum = oddSum;
            oddSum = temp % oddSum;
        }
        return evenSum;
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 3658. GCD of Odd and Even Sums)
    // Problem: https://leetcode.com/problems/gcd-of-odd-and-even-sums/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the 2 official examples + edge cases (n = 1, 2, 999, 1000). Expected values come from
    // the statement or from a brute force (add up the first n odd / even numbers, then gcd by
    // trial division): the answer is always n, because gcd(n*n, n*(n+1)) = n.
    //   Expected result: 6/6 passed
    // ==========================================================================
    private static void runTests() {
        // official examples
        check("example 1: n = 4", 4, () -> LC3658GCDofOddandEvenSums.gcdOfOddEvenSums(4));
        check("example 2: n = 5", 5, () -> LC3658GCDofOddandEvenSums.gcdOfOddEvenSums(5));
        // edge: smallest n (odd sum 1, even sum 2)
        check("n = 1 (sums 1 and 2)", 1, () -> LC3658GCDofOddandEvenSums.gcdOfOddEvenSums(1));
        // edge: smallest even n (sums 4 and 6)
        check("n = 2 (sums 4 and 6)", 2, () -> LC3658GCDofOddandEvenSums.gcdOfOddEvenSums(2));
        // edge: largest odd n and the maximum n of the constraints
        check("n = 999", 999, () -> LC3658GCDofOddandEvenSums.gcdOfOddEvenSums(999));
        check("n = 1000 (max)", 1000, () -> LC3658GCDofOddandEvenSums.gcdOfOddEvenSums(1000));
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
