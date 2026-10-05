package Leetcode.Ch06SortingAsAPreprocessingStep;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 2418. Sort the People   [Easy]
 * https://leetcode.com/problems/sort-the-people/
 * Topics: Array, Hash Table, String, Sorting
 * Book: Algorithmic Thinking - ch. 6 Sorting as a preprocessing step, Practice #4
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   names[i] and heights[i] are the name and the height of the i-th person. All heights are
 *   distinct positive integers. Return the names ordered by height, tallest person first.
 *
 * EXAMPLES
 *   names = ["Mary","John","Emma"],  heights = [180,165,170]  ->  ["Mary","Emma","John"]
 *   names = ["Alice","Bob","Bob"],   heights = [155,185,150]  ->  ["Bob","Alice","Bob"]
 *
 * CONSTRAINTS
 *   n == names.length == heights.length,   1 <= n <= 10^3,   1 <= names[i].length <= 20,
 *   1 <= heights[i] <= 10^5,   names[i] consists of lower and upper case English letters,
 *   all the values of heights are distinct
 * </pre>
 */
public class LC2418SortThePeople {

    /**
     * Variant A - selection sort on the heights, swapping the names along with them
     * <br>Target: time O(n^2), space O(1)
     * <br>Complete first: ch. 6 Sorting as a preprocessing step (this chapter)
     */
    public static String[] sortPeopleSelectionSort(String[] names, int[] heights) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - map each height to its name, read the names off the sorted heights
     * <br>Target: time O(n log n), space O(n)
     * <br>Complete first: ch. 3 Hashing as a search-to-lookup transform
     *     + ch. 6 Sorting as a preprocessing step (this chapter)
     */
    public static String[] sortPeopleHeightMap(String[] names, int[] heights) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: sort the indices 0..n-1 using height as the key
     * <br>Target: time O(n log n), space O(n)
     * <br>Complete first: ch. 6 Sorting as a preprocessing step (this chapter)
     */
    public static String[] sortPeople(String[] names, int[] heights) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC2418SortThePeople::sortPeopleSelectionSort);
        runAll("B", LC2418SortThePeople::sortPeopleHeightMap);
        runAll("C", LC2418SortThePeople::sortPeople);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 2418. Sort the People)
    // https://leetcode.com/problems/sort-the-people/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases (expected order worked out by hand from the heights). Every
    // call gets fresh arrays, so a variant that reorders its inputs in place is fine.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        String[] solve(String[] names, int[] heights);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", new String[]{"Mary", "Emma", "John"},
                () -> s.solve(new String[]{"Mary", "John", "Emma"}, new int[]{180, 165, 170}));
        check(v + " example 2", new String[]{"Bob", "Alice", "Bob"},
                () -> s.solve(new String[]{"Alice", "Bob", "Bob"}, new int[]{155, 185, 150}));
        // edge: a single person
        check(v + " single person", new String[]{"Zed"},
                () -> s.solve(new String[]{"Zed"}, new int[]{1}));
        // edge: heights already in descending order, nobody moves
        check(v + " already sorted", new String[]{"A", "B", "C"},
                () -> s.solve(new String[]{"A", "B", "C"}, new int[]{30, 20, 10}));
        // edge: smallest and largest allowed heights, mixed-case names
        check(v + " extreme heights", new String[]{"B", "D", "c", "a"},
                () -> s.solve(new String[]{"a", "B", "c", "D"}, new int[]{1, 100000, 50, 99999}));
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
