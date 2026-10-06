package Leetcode.Ch16TheMonotonicStack;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1944. Number of Visible People in a Queue   [Hard]
 * https://leetcode.com/problems/number-of-visible-people-in-a-queue/
 * Topics: Array, Stack, Monotonic Stack
 * Book: Algorithmic Thinking - ch. 16 The monotonic stack, Practice #6
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   n people stand in a queue, numbered 0 to n - 1 from left to right, and all their heights are
 *   different. Person i can see person j (with i < j) when everybody standing between them is
 *   shorter than both of them, that is min(heights[i], heights[j]) is larger than every
 *   height strictly between positions i and j. Return an array whose entry i is the number of
 *   people that person i can see to their right.
 *
 * EXAMPLES
 *   heights = [10,6,8,5,11,9]  ->  [3,1,2,1,1,0]    person 0 sees persons 1, 2 and 4
 *   heights = [5,1,2,3,10]     ->  [4,1,1,1,0]
 *
 * CONSTRAINTS
 *   n == heights.length,   1 <= n <= 10^5,   1 <= heights[i] <= 10^5,
 *   all the values of heights are unique
 * </pre>
 */
public class LC1944NumberOfVisiblePeopleInAQueue {

    /**
     * Variant A
     * <br>Target: time O(n^2), space O(1)    (besides the answer array)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static int[] canSeePersonsCountA(int[] heights) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n), space O(n)
     * <br>Complete first: ch. 16 The monotonic stack (this chapter)
     */
    public static int[] canSeePersonsCountB(int[] heights) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC1944NumberOfVisiblePeopleInAQueue::canSeePersonsCountA);
        runAll("B", LC1944NumberOfVisiblePeopleInAQueue::canSeePersonsCountB);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 1944. Number of Visible People in a Queue)
    // https://leetcode.com/problems/number-of-visible-people-in-a-queue/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B) is checked separately with the same cases: the two official
    // examples plus edge cases confirmed with an independent brute-force reference written
    // straight from the definition of "can see".
    //   Expected result: 0/10 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int[] solve(int[] heights);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", new int[]{3, 1, 2, 1, 1, 0},
                () -> s.solve(new int[]{10, 6, 8, 5, 11, 9}));
        check(v + " example 2", new int[]{4, 1, 1, 1, 0}, () -> s.solve(new int[]{5, 1, 2, 3, 10}));
        // edge: a single person sees nobody
        check(v + " single person", new int[]{0}, () -> s.solve(new int[]{7}));
        // edge: falling heights, each person only sees the next one
        check(v + " falling", new int[]{1, 1, 1, 1, 0}, () -> s.solve(new int[]{5, 4, 3, 2, 1}));
        // edge: a dip, then a taller wall: person 0 sees three people
        check(v + " dip and wall", new int[]{3, 1, 2, 1, 0},
                () -> s.solve(new int[]{4, 1, 3, 2, 5}));
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
