package Leetcode.Ch16TheMonotonicStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 901. Online Stock Span   [Medium]
 * https://leetcode.com/problems/online-stock-span/
 * Topics: Stack, Design, Monotonic Stack, Data Stream
 * Book: Algorithmic Thinking - ch. 16 The monotonic stack, Practice #5
 *
 * STATUS
 *   Not solved yet. Implement the classes below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Design a StockSpanner. A new object starts with no history, and its method next(price)
 *   receives the stock price of the next day. next(price) returns the span of that price: the
 *   number of consecutive days, going backwards from today and counting today, on which the
 *   price was less than or equal to today's price.
 *   (After the prices 7, 34, 1, 2 a new price of 8 has span 3: today, the 2 and the 1.)
 *
 * EXAMPLES
 *   new StockSpanner(), then
 *   next(100) -> 1,  next(80) -> 1,  next(60) -> 1,  next(70) -> 2,
 *   next(60) -> 1,  next(75) -> 4,  next(85) -> 6
 *
 * CONSTRAINTS
 *   1 <= price <= 10^5,   at most 10^4 calls to next
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Practice/Stack/LC901OnlineStockSpan.java
 *   (an earlier attempt - look at it only after you have tried this one yourself)
 * </pre>
 */
public class LC901OnlineStockSpan {

    /**
     * Variant A
     * <br>Target: time O(n) per next() call (n = calls so far), space O(n)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static class StockSpannerA {

        public StockSpannerA() {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public int next(int price) {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }
    }

    /**
     * Variant B
     * <br>Target: time O(1) amortised per next() call, space O(n)
     * <br>Complete first: ch. 16 The monotonic stack (this chapter)
     */
    public static class StockSpannerB {

        public StockSpannerB() {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public int next(int price) {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", () -> new LC901OnlineStockSpan.StockSpannerA()::next);
        runAll("B", () -> new LC901OnlineStockSpan.StockSpannerB()::next);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 901. Online Stock Span)
    // https://leetcode.com/problems/online-stock-span/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A = StockSpannerA, B = StockSpannerB) is checked separately with the
    // same cases. A case creates a fresh object, replays a sequence of next() calls and compares
    // the list of results: the official example plus edge cases confirmed with an independent
    // brute-force reference.
    //   Expected result: 0/8 passed (every case fails until you implement the classes)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Spanner {
        int next(int price);
    }

    interface Factory {
        Spanner create();
    }

    private static void runAll(String v, Factory f) {
        // official example: the results of the seven calls
        check(v + " example", Arrays.asList(1, 1, 1, 2, 1, 4, 6),
                () -> replay(f, 100, 80, 60, 70, 60, 75, 85));
        // edge: equal prices count (price <= today's), so the span keeps growing
        check(v + " equal prices", Arrays.asList(1, 2, 3, 4), () -> replay(f, 7, 7, 7, 7));
        // edge: rising prices, every day spans the whole history
        check(v + " rising", Arrays.asList(1, 2, 3, 4, 5), () -> replay(f, 1, 2, 3, 4, 5));
        // edge: a fall of single-day spans, then one price that swallows all of it
        check(v + " fall then jump", Arrays.asList(1, 1, 1, 1, 5),
                () -> replay(f, 10, 9, 8, 7, 11));
    }

    // a fresh object, one next() call per price, the list of all results
    private static List<Integer> replay(Factory f, int... prices) {
        Spanner sp = f.create();
        List<Integer> out = new ArrayList<>();
        for (int p : prices) out.add(sp.next(p));
        return out;
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
