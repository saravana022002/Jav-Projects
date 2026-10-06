package Leetcode.Ch16TheMonotonicStack;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 496. Next Greater Element I   [Easy]
 * https://leetcode.com/problems/next-greater-element-i/
 * Topics: Array, Hash Table, Stack, Monotonic Stack
 * Book: Algorithmic Thinking - ch. 16 The monotonic stack, Practice #2
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   nums2 has distinct values, and nums1 consists of some of those values in any order (its
 *   own values are distinct too). For every x in nums1, find x inside nums2 and look to its
 *   right in nums2 for the first value that is strictly greater than x. That value is the
 *   answer for x, or -1 when there is none. Return the answers in the order of nums1.
 *
 * EXAMPLES
 *   nums1 = [4,1,2], nums2 = [1,3,4,2]  ->  [-1,3,-1]    4 and 2 have nothing bigger on their right
 *   nums1 = [2,4],   nums2 = [1,2,3,4]  ->  [3,-1]
 *
 * CONSTRAINTS
 *   1 <= nums1.length <= nums2.length <= 1000,   0 <= nums1[i], nums2[i] <= 10^4,
 *   all values of nums1 are distinct and all values of nums2 are distinct,
 *   every value of nums1 also appears in nums2
 *   Follow-up: can you do it in O(nums1.length + nums2.length) time?
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Practice/Stack/LC496NextGreaterElementI.java
 *   (an earlier attempt - look at it only after you have tried this one yourself)
 * </pre>
 */
public class LC496NextGreaterElementI {

    /**
     * Variant A
     * <br>Target: time O(m * n), space O(1)    (m = nums1.length, n = nums2.length)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting
     */
    public static int[] nextGreaterElementA(int[] nums1, int[] nums2) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(m + n), space O(n)
     * <br>Complete first: ch. 3 Hashing as a search-to-lookup transform
     *     + ch. 16 The monotonic stack (this chapter)
     */
    public static int[] nextGreaterElementB(int[] nums1, int[] nums2) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC496NextGreaterElementI::nextGreaterElementA);
        runAll("B", LC496NextGreaterElementI::nextGreaterElementB);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 496. Next Greater Element I)
    // https://leetcode.com/problems/next-greater-element-i/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B) is checked separately with the same cases: the two official
    // examples plus edge cases confirmed with an independent brute-force reference.
    //   Expected result: 0/10 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int[] solve(int[] nums1, int[] nums2);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", new int[]{-1, 3, -1},
                () -> s.solve(new int[]{4, 1, 2}, new int[]{1, 3, 4, 2}));
        check(v + " example 2", new int[]{3, -1},
                () -> s.solve(new int[]{2, 4}, new int[]{1, 2, 3, 4}));
        // edge: the smallest input, value 0
        check(v + " smallest", new int[]{-1}, () -> s.solve(new int[]{0}, new int[]{0}));
        // edge: nums2 only falls, so every answer is -1
        check(v + " falling", new int[]{-1, -1, -1},
                () -> s.solve(new int[]{3, 1, 2}, new int[]{3, 2, 1}));
        // edge: queries in another order than nums2; the answer is not the next neighbour
        check(v + " other order", new int[]{7, -1, 7},
                () -> s.solve(new int[]{2, 7, 5}, new int[]{5, 2, 7, 3, 6, 4}));
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
