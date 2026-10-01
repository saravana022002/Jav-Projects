package Leetcode.Ch12CyclicSortAndIndexAsAddress;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 765. Couples Holding Hands   [Hard]
 * https://leetcode.com/problems/couples-holding-hands/
 * Topics: Greedy, Depth-First Search, Breadth-First Search, Union-Find, Graph Theory
 * Book: Algorithmic Thinking - ch. 12 Cyclic sort and index-as-address, Practice #12
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   n couples sit in a row of 2n seats; row[i] is the ID of the person in seat i. Couple k
 *   is the pair of people 2k and 2k + 1. One swap takes any two people and exchanges their
 *   seats. Return the minimum number of swaps after which every couple sits side by side,
 *   i.e. in seats 2j and 2j + 1 for some j.
 *
 * EXAMPLES
 *   row = [0,2,1,3]  ->  1    swap the people in the second and third seat
 *   row = [3,2,0,1]  ->  0    every couple already sits together
 *
 * CONSTRAINTS
 *   2n == row.length,   2 <= n <= 30,   0 <= row[i] < 2n,   all elements of row are unique
 * </pre>
 */
public class LC765CouplesHoldingHands {

    /**
     * Variant A
     * <br>Target: time O(n^2), space O(1)
     */
    public static int minSwapsCouplesA(int[] row) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n), space O(n)
     */
    public static int minSwapsCouplesB(int[] row) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n * alpha(n)), space O(n)
     */
    public static int minSwapsCouplesC(int[] row) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC765CouplesHoldingHands::minSwapsCouplesA);
        runAll("B", LC765CouplesHoldingHands::minSwapsCouplesB);
        runAll("C", LC765CouplesHoldingHands::minSwapsCouplesC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 765. Couples Holding Hands)
    // https://leetcode.com/problems/couples-holding-hands/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases checked against an independent reference (an exhaustive
    // breadth-first search over all seatings for 3 and 4 couples; for the 30-couple case a
    // formula that was itself verified against that search).
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    private static int hung = 0;   // cases of the current variant that never finished
    private static final long TIMEOUT_MS = 5000;

    interface Solver {
        int solve(int[] row);
    }

    private static void runAll(String v, Solver s) {
        hung = 0;
        // official examples
        check(v + " example 1", 1, () -> s.solve(new int[]{0, 2, 1, 3}));
        check(v + " example 2", 0, () -> s.solve(new int[]{3, 2, 0, 1}));
        // edge: three couples mixed up in one ring, needs two swaps
        check(v + " ring of three", 2, () -> s.solve(new int[]{0, 2, 4, 1, 3, 5}));
        // edge: four couples, two separate mix-ups of two couples each
        check(v + " two separate pairs", 2, () -> s.solve(new int[]{1, 3, 5, 7, 0, 2, 4, 6}));
        // edge: the maximum number of couples, all in one ring
        check(v + " thirty couples", 29, () -> s.solve(oneBigRing(30)));
    }

    // couch i (seats 2i, 2i + 1) holds person 2i and the partner of person 2(i + 1)
    private static int[] oneBigRing(int couples) {
        int[] row = new int[2 * couples];
        for (int i = 0; i < couples; i++) {
            row[2 * i] = 2 * i;
            row[2 * i + 1] = 2 * ((i + 1) % couples) + 1;
        }
        return row;
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
