package Leetcode.Ch12CyclicSortAndIndexAsAddress;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 448. Find All Numbers Disappeared in an Array   [Easy]
 * https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
 * Topics: Array, Hash Table
 * Book: Algorithmic Thinking - ch. 12 Cyclic sort and index-as-address, Practice #1
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   nums holds n integers, each between 1 and n (repeats are possible). Return every
 *   integer in the range [1, n] that does not occur in nums.
 *
 * EXAMPLES
 *   nums = [4,3,2,7,8,2,3,1]  ->  [5,6]
 *   nums = [1,1]              ->  [2]
 *
 * CONSTRAINTS
 *   n == nums.length,   1 <= n <= 10^5,   1 <= nums[i] <= n
 *   Follow up: O(n) time without extra space (the returned list does not count as extra).
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Practice/Arrays/LC448FindAllNumbersDisappearedInAnArray.java
 * </pre>
 */
public class LC448FindAllNumbersDisappearedInAnArray {

    /**
     * Variant A
     * <br>Target: time O(n), space O(n)
     */
    public static List<Integer> findDisappearedNumbersA(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n), space O(1) extra (the output list does not count)
     */
    public static List<Integer> findDisappearedNumbersB(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n), space O(1) extra (the output list does not count)
     */
    public static List<Integer> findDisappearedNumbersC(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC448FindAllNumbersDisappearedInAnArray::findDisappearedNumbersA);
        runAll("B", LC448FindAllNumbersDisappearedInAnArray::findDisappearedNumbersB);
        runAll("C", LC448FindAllNumbersDisappearedInAnArray::findDisappearedNumbersC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class 
    // (LC 448. Find All Numbers Disappeared in an Array)
    // https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases checked against an independent reference (set difference).
    // The order of the returned numbers does not matter: the result is sorted before comparing.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    private static int hung = 0;   // cases of the current variant that never finished
    private static final long TIMEOUT_MS = 5000;

    interface Solver {
        List<Integer> solve(int[] nums);
    }

    private static void runAll(String v, Solver s) {
        hung = 0;
        // official examples
        check(v + " example 1", Arrays.asList(5, 6),
                () -> sorted(s.solve(new int[]{4, 3, 2, 7, 8, 2, 3, 1})));
        check(v + " example 2", Arrays.asList(2), () -> sorted(s.solve(new int[]{1, 1})));
        // edge: the smallest input, nothing is missing
        check(v + " single element", Collections.emptyList(), () -> sorted(s.solve(new int[]{1})));
        // edge: a permutation of 1..n, nothing is missing
        check(v + " permutation", Collections.emptyList(),
                () -> sorted(s.solve(new int[]{3, 1, 2})));
        // edge: one value repeated everywhere
        check(v + " all equal", Arrays.asList(1, 2, 3),
                () -> sorted(s.solve(new int[]{4, 4, 4, 4})));
    }

    private static List<Integer> sorted(List<Integer> list) {
        List<Integer> copy = new ArrayList<>(list);
        Collections.sort(copy);
        return copy;
    }

    // one case: a wrong value, an exception or an endless loop counts as FAIL; the run continues
    private static void check(String name, Object expected, Supplier<Object> actual) {
        total++;
        Object got = hung > 0 ? "skipped (an earlier case of this variant never finished)"
                : callWithTimeout(actual);
        boolean ok = Objects.deepEquals(expected, got);
        if (ok) passed++;
        System.out.println((ok ? "PASS  " : "FAIL  ") + name
                + (ok ? "" : "   expected " + show(expected) + ", got " + show(got)));
    }

    // runs the case on its own thread, so that an endless loop cannot block the whole run
    private static Object callWithTimeout(Supplier<Object> actual) {
        Object[] box = new Object[1];
        Thread worker = new Thread(null, () -> {
            try {
                box[0] = actual.get();
            } catch (Throwable t) {
                box[0] = "threw " + t.getClass().getSimpleName();
            }
        }, "case", 1L << 26);
        worker.setDaemon(true);
        worker.start();
        try {
            worker.join(TIMEOUT_MS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        if (worker.isAlive()) {
            hung++;
            return "no result after " + TIMEOUT_MS + " ms (endless loop?)";
        }
        return box[0];
    }

    private static String show(Object o) {
        if (o instanceof int[]) return Arrays.toString((int[]) o);
        if (o instanceof Object[]) return Arrays.deepToString((Object[]) o);
        return String.valueOf(o);
    }

    // ============================== END TESTS ===============================
}
