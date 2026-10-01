package Leetcode.Ch04FrequencyAndMultisetReasoning;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 169. Majority Element   [Easy]
 * https://leetcode.com/problems/majority-element/
 * Topics: Array, Hash Table, Divide and Conquer, Sorting, Counting, Boyer-Moore Majority Vote
 * Book: Algorithmic Thinking - ch. 4 Frequency and multiset reasoning, Practice #12
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Return the majority element of nums: the value that occurs more than floor(n / 2) times,
 *   where n is the length of the array. You may assume that such a value always exists.
 *   Follow-up: can you do it in linear time and O(1) extra space?
 *
 * EXAMPLES
 *   nums = [3,2,3]          ->  3     3 appears 2 times, more than floor(3 / 2) = 1
 *   nums = [2,2,1,1,1,2,2]  ->  2     2 appears 4 times, more than floor(7 / 2) = 3
 *
 * CONSTRAINTS
 *   n == nums.length,   1 <= n <= 5 * 10^4,   -10^9 <= nums[i] <= 10^9,
 *   the input is generated so that a majority element exists
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Practice/Arrays/LC169MajorityElementHashMap.java
 *   src/Practice/Arrays/LC169MajorityElementBoyerMoore.java
 *   (earlier attempts - look at them only after you have tried this one yourself)
 * </pre>
 */
public class LC169MajorityElement {

    /**
     * Variant A
     * <br>Target: time O(n^2), space O(1)
     */
    public static int majorityElementA(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n), space O(n)
     */
    public static int majorityElementB(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n), space O(1)
     */
    public static int majorityElementC(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC169MajorityElement::majorityElementA);
        runAll("B", LC169MajorityElement::majorityElementB);
        runAll("C", LC169MajorityElement::majorityElementC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 169. Majority Element)
    // https://leetcode.com/problems/majority-element/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases checked against a brute-force count.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int[] nums);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 3, () -> s.solve(new int[]{3, 2, 3}));
        check(v + " example 2", 2, () -> s.solve(new int[]{2, 2, 1, 1, 1, 2, 2}));
        // edge: a single element at the lowest allowed value
        check(v + " single element", -1000000000, () -> s.solve(new int[]{-1000000000}));
        // edge: the majority only shows up at the end, after other values led for a while
        check(v + " late winner", 3, () -> s.solve(new int[]{1, 2, 3, 3, 3}));
        // edge: even length, the winner has the smallest possible margin (4 of 6)
        check(v + " even length", 5, () -> s.solve(new int[]{6, 5, 5, 6, 5, 5}));
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
