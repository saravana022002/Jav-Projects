package Leetcode.Ch12CyclicSortAndIndexAsAddress;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 442. Find All Duplicates in an Array   [Medium]
 * https://leetcode.com/problems/find-all-duplicates-in-an-array/
 * Topics: Array, Hash Table, Sorting
 * Book: Algorithmic Thinking - ch. 12 Cyclic sort and index-as-address, Practice #2
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   nums has length n and every value lies in [1, n]; each value occurs once or twice.
 *   Return all values that occur twice. The intended solution needs linear time and only
 *   O(1) memory beyond the answer list itself.
 *
 * EXAMPLES
 *   nums = [4,3,2,7,8,2,3,1]  ->  [2,3]
 *   nums = [1,1,2]            ->  [1]
 *   nums = [1]                ->  []
 *
 * CONSTRAINTS
 *   n == nums.length,   1 <= n <= 10^5,   1 <= nums[i] <= n,
 *   each element of nums appears once or twice
 * </pre>
 */
public class LC442FindAllDuplicatesInAnArray {

    /**
     * Variant A - remember the values seen so far in a boolean array, a second sighting repeats
     * <br>Target: time O(n), space O(n)
     */
    public static List<Integer> findDuplicatesSeen(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - this chapter's technique: cyclic sort, then a value in a wrong slot is a repeat
     * <br>Target: time O(n), space O(1) extra (the output list does not count)
     */
    public static List<Integer> findDuplicates(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - index-as-address: negate slot v - 1 for each v, a slot already negated repeats
     * <br>Target: time O(n), space O(1) extra (the output list does not count)
     */
    public static List<Integer> findDuplicatesMarking(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC442FindAllDuplicatesInAnArray::findDuplicatesSeen);
        runAll("B", LC442FindAllDuplicatesInAnArray::findDuplicates);
        runAll("C", LC442FindAllDuplicatesInAnArray::findDuplicatesMarking);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 442. Find All Duplicates in an Array)
    // https://leetcode.com/problems/find-all-duplicates-in-an-array/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases checked against an independent reference (value counts).
    // The order of the returned numbers does not matter: the result is sorted before comparing.
    //   Expected result: 0/18 passed (every case fails until you implement the methods)
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
        check(v + " example 1", Arrays.asList(2, 3),
                () -> sorted(s.solve(new int[]{4, 3, 2, 7, 8, 2, 3, 1})));
        check(v + " example 2", Arrays.asList(1), () -> sorted(s.solve(new int[]{1, 1, 2})));
        check(v + " example 3", Collections.emptyList(), () -> sorted(s.solve(new int[]{1})));
        // edge: the shortest input that has a repeat
        check(v + " two equal", Arrays.asList(2), () -> sorted(s.solve(new int[]{2, 2})));
        // edge: every value occurs twice
        check(v + " all twice", Arrays.asList(1, 2),
                () -> sorted(s.solve(new int[]{1, 1, 2, 2})));
        // edge: repeats scattered over the array, and some values do not occur at all
        check(v + " scattered", Arrays.asList(5, 6, 9),
                () -> sorted(s.solve(new int[]{5, 4, 6, 7, 9, 3, 10, 9, 5, 6})));
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
