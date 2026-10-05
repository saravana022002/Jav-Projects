package Leetcode.Ch18HeapsAndTheKSelectionFamily;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1046. Last Stone Weight   [Easy]
 * https://leetcode.com/problems/last-stone-weight/
 * Topics: Array, Heap (Priority Queue)
 * Book: Algorithmic Thinking - ch. 18 Heaps and the k-selection family, Practice #1
 *       also: ch. 78 Huffman and the merge-cost family (#1)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   stones[i] is the weight of stone i. In every turn the two heaviest stones are smashed
 *   together. If they weigh the same, both disappear; otherwise the lighter one disappears
 *   and the heavier one stays with the difference of the two weights. Repeat until at most
 *   one stone is left and return its weight, or 0 if no stone is left.
 *
 * EXAMPLES
 *   stones = [2,7,4,1,8,1]  ->  1    8 and 7 leave 1; 4 and 2 leave 2; 2 and 1 leave 1;
 *                                    the two 1s vanish; one stone of weight 1 remains
 *   stones = [1]            ->  1
 *
 * CONSTRAINTS
 *   1 <= stones.length <= 30,   1 <= stones[i] <= 1000
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Practice/Heap/LC1046LastStoneWeight.java
 *   (an earlier attempt - look at it only after you have tried this one yourself)
 * </pre>
 */
public class LC1046LastStoneWeight {

    /**
     * Variant A - simulate directly: sort the stones again in every turn
     * <br>Target: time O(n^2 log n), space O(1) extra
     * <br>Complete first: ch. 6 Sorting as a preprocessing step
     */
    public static int lastStoneWeightSorting(int[] stones) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - this chapter's technique: a max-heap; take two out, put the difference back
     * <br>Target: time O(n log n), space O(n)
     * <br>Complete first: ch. 18 Heaps and the k-selection family (this chapter)
     */
    public static int lastStoneWeight(int[] stones) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC1046LastStoneWeight::lastStoneWeightSorting);
        runAll("B", LC1046LastStoneWeight::lastStoneWeight);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 1046. Last Stone Weight)
    // https://leetcode.com/problems/last-stone-weight/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B) is checked separately with the same cases: the two official
    // examples plus edge cases whose answers were checked against a literal simulation.
    // The solutions may change the array they are given, so every call gets a fresh copy.
    //   Expected result: 0/10 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int[] stones);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 1, () -> s.solve(new int[]{2, 7, 4, 1, 8, 1}));
        check(v + " example 2", 1, () -> s.solve(new int[]{1}));
        // edge: everything cancels out, no stone is left
        check(v + " nothing left", 0, () -> s.solve(new int[]{3, 3, 3, 3}));
        // edge: the stone made by a smash is at once the heaviest again
        check(v + " new stone is heaviest", 2, () -> s.solve(new int[]{10, 3, 3, 2}));
        // edge: several smashes with repeated weights
        check(v + " repeated weights", 3, () -> s.solve(new int[]{7, 6, 7, 6, 9}));
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
