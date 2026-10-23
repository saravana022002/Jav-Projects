package Leetcode.Ch08TwoPointersConverging;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 167. Two Sum II - Input Array Is Sorted   [Medium]
 * https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
 * Topics: Array, Two Pointers, Binary Search
 * Book: Algorithmic Thinking - ch. 8 Two pointers, converging, Practice #4
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   numbers is sorted in non-decreasing order and is 1-indexed. Find the two different
 *   positions whose values add up to target and return them as [index1, index2] with
 *   index1 < index2. There is always exactly one answer. Use only constant extra space.
 *
 * EXAMPLES
 *   numbers = [2,7,11,15], target = 9   ->  [1,2]     2 + 7 = 9
 *   numbers = [2,3,4],     target = 6   ->  [1,3]     2 + 4 = 6
 *   numbers = [-1,0],      target = -1  ->  [1,2]     -1 + 0 = -1
 *
 * CONSTRAINTS
 *   2 <= numbers.length <= 3 * 10^4,   -1000 <= numbers[i] <= 1000,   -1000 <= target <= 1000,
 *   numbers is sorted in non-decreasing order, exactly one solution exists
 * </pre>
 */
public class LC167TwoSumIIInputArrayIsSorted {

    /**
     * Variant A
     * <br>Target: time O(n^2), space O(1)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static int[] twoSumA(int[] numbers, int target) {
        // TODO: write your solution
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i +  1; j < numbers.length; j++) {
                if(numbers[i] + numbers[j] == target){
                    return new int[]{i + 1, j + 1};
                }
            }
        }
        return null;
    }

    /**
     * Variant B
     * <br>Target: time O(n log n), space O(1)
     * <br>Complete first: ch. 10 Binary search on a sorted array (later chapter)
     */
    public static int[] twoSumB(int[] numbers, int target) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n), space O(1)
     * <br>Complete first: ch. 8 Two pointers, converging (this chapter)
     */
    public static int[] twoSumC(int[] numbers, int target) {
        // TODO: write your solution
        int l = 0;
        int r = numbers.length - 1;
        while (l < r){
            int sum = numbers[l] + numbers[r];
            if(sum == target){
                return new int[]{l + 1, r + 1};
            }else if(sum > target){
                r--;
            }else {
                l++;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC167TwoSumIIInputArrayIsSorted::twoSumA);
        runAll("B", LC167TwoSumIIInputArrayIsSorted::twoSumB);
        runAll("C", LC167TwoSumIIInputArrayIsSorted::twoSumC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 167. Two Sum II - Input Array Is Sorted)
    // https://leetcode.com/problems/two-sum-ii-input-array-is-sorted/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases checked against a reference solution.
    //   Expected result: 0/18 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int[] solve(int[] numbers, int target);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", new int[]{1, 2}, () -> s.solve(new int[]{2, 7, 11, 15}, 9));
        check(v + " example 2", new int[]{1, 3}, () -> s.solve(new int[]{2, 3, 4}, 6));
        check(v + " example 3", new int[]{1, 2}, () -> s.solve(new int[]{-1, 0}, -1));
        // edge: equal values used as the pair
        check(v + " duplicates", new int[]{2, 3}, () -> s.solve(new int[]{1, 3, 3, 8}, 6));
        // edge: the pair is the first and the last element
        check(v + " ends", new int[]{1, 5}, () -> s.solve(new int[]{-1000, -5, 0, 7, 1000}, 0));
        // edge: all negative numbers
        check(v + " negatives", new int[]{2, 3}, () -> s.solve(new int[]{-9, -7, -4, -1}, -11));
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
