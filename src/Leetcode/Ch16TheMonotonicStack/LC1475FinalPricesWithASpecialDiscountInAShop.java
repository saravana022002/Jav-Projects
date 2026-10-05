package Leetcode.Ch16TheMonotonicStack;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1475. Final Prices With a Special Discount in a Shop   [Easy]
 * https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop/
 * Topics: Array, Stack, Monotonic Stack
 * Book: Algorithmic Thinking - ch. 16 The monotonic stack, Practice #3
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   prices[i] is the price of item i. Buying item i earns a discount equal to prices[j], where
 *   j is the smallest index after i with prices[j] <= prices[i]; when no such j exists there is
 *   no discount. Return, for every item, the price you really pay after the discount.
 *
 * EXAMPLES
 *   prices = [8,4,6,2,3]  ->  [4,2,4,2,3]    8-4, 4-2, 6-2, and no discount for the last two
 *   prices = [1,2,3,4,5]  ->  [1,2,3,4,5]    no discount for anybody
 *   prices = [10,1,1,6]   ->  [9,0,1,6]
 *
 * CONSTRAINTS
 *   1 <= prices.length <= 500,   1 <= prices[i] <= 1000
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Practice/Arrays/LC1475FinalPricesWithASpecialDiscountInAShop.java
 *   (an earlier attempt - look at it only after you have tried this one yourself)
 * </pre>
 */
public class LC1475FinalPricesWithASpecialDiscountInAShop {

    /**
     * Variant A - brute force: for every item, scan to its right for the first price not higher
     * <br>Target: time O(n^2), space O(1)    (besides the answer array)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static int[] finalPricesBruteForce(int[] prices) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - this chapter's technique: stack of the items that still wait for a discount
     * <br>Target: time O(n), space O(n)
     * <br>Complete first: ch. 16 The monotonic stack (this chapter)
     */
    public static int[] finalPrices(int[] prices) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC1475FinalPricesWithASpecialDiscountInAShop::finalPricesBruteForce);
        runAll("B", LC1475FinalPricesWithASpecialDiscountInAShop::finalPrices);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class 
    // (LC 1475. Final Prices With a Special Discount in a Shop)
    // https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B) is checked separately with the same cases: the three official
    // examples plus edge cases confirmed with an independent brute-force reference.
    //   Expected result: 0/12 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int[] solve(int[] prices);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", new int[]{4, 2, 4, 2, 3}, () -> s.solve(new int[]{8, 4, 6, 2, 3}));
        check(v + " example 2", new int[]{1, 2, 3, 4, 5}, () -> s.solve(new int[]{1, 2, 3, 4, 5}));
        check(v + " example 3", new int[]{9, 0, 1, 6}, () -> s.solve(new int[]{10, 1, 1, 6}));
        // edge: a single item has nothing to its right
        check(v + " single item", new int[]{7}, () -> s.solve(new int[]{7}));
        // edge: an equal price already earns the discount (prices[j] <= prices[i])
        check(v + " equal prices", new int[]{0, 2}, () -> s.solve(new int[]{2, 2}));
        // edge: strictly falling prices, every item is discounted by its right neighbour
        check(v + " falling", new int[]{1, 1, 1, 1, 1}, () -> s.solve(new int[]{5, 4, 3, 2, 1}));
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
        if (o instanceof Object[]) return Arrays.deepToString((Object[]) o);
        return String.valueOf(o);
    }

    // ============================== END TESTS ===============================
}
