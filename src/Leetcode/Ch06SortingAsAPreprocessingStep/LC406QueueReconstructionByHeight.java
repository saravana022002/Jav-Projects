package Leetcode.Ch06SortingAsAPreprocessingStep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 406. Queue Reconstruction by Height   [Medium]
 * https://leetcode.com/problems/queue-reconstruction-by-height/
 * Topics: Array, Binary Indexed Tree, Segment Tree, Sorting
 * Book: Algorithmic Thinking - ch. 6 Sorting as a preprocessing step, Practice #12
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   The people of a queue lost their order. people[i] = [h_i, k_i] says that person i has
 *   height h_i and that exactly k_i people standing in front of them are at least as tall
 *   (height >= h_i). Rebuild the queue: return the people in queue order, front person
 *   first, each written as [h, k]. The input is guaranteed to describe a valid queue.
 *
 * EXAMPLES
 *   people = [[7,0],[4,4],[7,1],[5,0],[6,1],[5,2]]  ->  [[5,0],[7,0],[5,2],[6,1],[4,4],[7,1]]
 *   people = [[6,0],[5,0],[4,0],[3,2],[2,2],[1,4]]  ->  [[4,0],[5,0],[2,2],[3,2],[1,4],[6,0]]
 *
 * CONSTRAINTS
 *   1 <= people.length <= 2000,   0 <= h_i <= 10^6,   0 <= k_i < people.length,
 *   it is guaranteed that the queue can be reconstructed
 * </pre>
 */
public class LC406QueueReconstructionByHeight {

    /**
     * Variant A
     * <br>Target: time O(n^2), space O(n)
     * <br>Complete first: ch. 6 Sorting as a preprocessing step (this chapter)
     */
    public static int[][] reconstructQueueA(int[][] people) {
        // TODO: write your solution
        Arrays.sort(people, (a, b) -> a[0] != b[0]
                ? Integer.compare(b[0], a[0])
                : Integer.compare(a[1], b[1]));

        List<int[]> q = new ArrayList<>();
        for (int[] p : people) {
            q.add(p[1], p);              // stand at index k
        }
        return q.toArray(new int[q.size()][]);
    }

    /**
     * Variant B
     * <br>Target: time O(n log n), space O(n)
     * <br>Complete first: ch. 6 Sorting as a preprocessing step (this chapter)
     *     + ch. 39 Fenwick trees (later chapter)
     */
    public static int[][] reconstructQueueB(int[][] people) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n^2), space O(n)
     * <br>Complete first: ch. 6 Sorting as a preprocessing step (this chapter)
     */
    public static int[][] reconstructQueueC(int[][] people) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        int[][] ans = {{7,0}, {7,1}, {6,2}, {5,1}, {5,4}, {4,4}};
        reconstructQueueA(ans);
//        runAll("A", LC406QueueReconstructionByHeight::reconstructQueueA);
//        runAll("B", LC406QueueReconstructionByHeight::reconstructQueueB);
//        runAll("C", LC406QueueReconstructionByHeight::reconstructQueueC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 406. Queue Reconstruction by Height)
    // https://leetcode.com/problems/queue-reconstruction-by-height/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases. A valid input has exactly one valid queue (confirmed for every
    // case here by trying all orderings), so the result is compared as it is, in queue order.
    // Every call gets a fresh array, so sorting or reordering the input in place is fine.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int[][] solve(int[][] people);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", new int[][]{{5, 0}, {7, 0}, {5, 2}, {6, 1}, {4, 4}, {7, 1}},
                () -> s.solve(new int[][]{{7, 0}, {4, 4}, {7, 1}, {5, 0}, {6, 1}, {5, 2}}));
        check(v + " example 2", new int[][]{{4, 0}, {5, 0}, {2, 2}, {3, 2}, {1, 4}, {6, 0}},
                () -> s.solve(new int[][]{{6, 0}, {5, 0}, {4, 0}, {3, 2}, {2, 2}, {1, 4}}));
        // edge: a single person
        check(v + " single person", new int[][]{{3, 0}},
                () -> s.solve(new int[][]{{3, 0}}));
        // edge: everybody has the same height, so k alone fixes the order
        check(v + " same height", new int[][]{{5, 0}, {5, 1}, {5, 2}},
                () -> s.solve(new int[][]{{5, 2}, {5, 0}, {5, 1}}));
        // edge: nobody has a taller-or-equal person in front, height 0 included
        check(v + " all k zero", new int[][]{{0, 0}, {6, 0}, {9, 0}},
                () -> s.solve(new int[][]{{9, 0}, {0, 0}, {6, 0}}));
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
