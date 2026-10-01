package Leetcode.Ch06SortingAsAPreprocessingStep;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 217. Contains Duplicate   [Easy]
 * https://leetcode.com/problems/contains-duplicate/
 * Topics: Array, Hash Table, Sorting
 * Book: Algorithmic Thinking - ch. 6 Sorting as a preprocessing step, Practice #3
 *       also: ch. 3 Hashing as a search-to-lookup transform (#1), ch. 83 What n tells you (#1)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Return true if some value appears at least twice in the integer array nums, and false
 *   if every element is distinct.
 *
 * EXAMPLES
 *   nums = [1,2,3,1]                ->  true     1 occurs at the indices 0 and 3
 *   nums = [1,2,3,4]                ->  false    all elements are distinct
 *   nums = [1,1,1,3,3,4,3,2,4,2]    ->  true
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 10^5,   -10^9 <= nums[i] <= 10^9
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Practice/HashMap/LC217ContainsDuplicate.java
 *   src/Leetcode/Ch03HashingAsASearchToLookupTransform/LC217ContainsDuplicate.java
 *   (earlier attempts - look at them only after you have tried this one yourself)
 * </pre>
 */
public class LC217ContainsDuplicate {

    /**
     * Variant A - brute force: compare every pair of elements
     * <br>Target: time O(n^2), space O(1)
     */
    public static boolean containsDuplicateBruteForce(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - hash set: remember what you have seen, stop at the first repeat
     * <br>Target: time O(n), space O(n)
     */
    public static boolean containsDuplicateHashSet(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: sort nums, then look for two equal values side by side
     * <br>Target: time O(n log n), space O(1) extra (nums is sorted in place)
     */
    public static boolean containsDuplicate(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC217ContainsDuplicate::containsDuplicateBruteForce);
        runAll("B", LC217ContainsDuplicate::containsDuplicateHashSet);
        runAll("C", LC217ContainsDuplicate::containsDuplicate);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 217. Contains Duplicate)
    // https://leetcode.com/problems/contains-duplicate/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases checked against a brute-force pair comparison. Every call gets
    // a fresh array, so a variant that sorts its input in place does not affect other cases.
    //   Expected result: 0/18 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        boolean solve(int[] nums);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", true, () -> s.solve(new int[]{1, 2, 3, 1}));
        check(v + " example 2", false, () -> s.solve(new int[]{1, 2, 3, 4}));
        check(v + " example 3", true, () -> s.solve(new int[]{1, 1, 1, 3, 3, 4, 3, 2, 4, 2}));
        // edge: a single element cannot repeat
        check(v + " single element", false, () -> s.solve(new int[]{5}));
        // edge: the smallest input that does repeat
        check(v + " equal pair", true, () -> s.solve(new int[]{2, 2}));
        // edge: the two equal values sit at the far ends, extremes elsewhere
        check(v + " far apart", true,
                () -> s.solve(new int[]{1000000000, -3, 8, 0, -1000000000, 1000000000}));
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
