package Leetcode.Ch10BinarySearchOnASortedArray;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 278. First Bad Version   [Easy]
 * https://leetcode.com/problems/first-bad-version/
 * Topics: Binary Search, Interactive
 * Book: Algorithmic Thinking - ch. 10 Binary search on a sorted array, Practice #4
 *
 * STATUS
 *   Not solved yet. Implement the methods inside Solution (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Versions 1..n of a product were released in order. From some version on, every version is
 *   bad. You may only ask the API isBadVersion(version), which says whether one version is bad.
 *   Find the first bad version using as few API calls as possible.
 *   (On LeetCode Solution extends VersionControl, which provides isBadVersion. Here the test
 *   plays the judge: it overrides isBadVersion in a subclass of Solution, and you call
 *   isBadVersion(...) inside your methods exactly as on LeetCode.)
 *
 * EXAMPLES
 *   n = 5, bad = 4  ->  4    isBadVersion(3) is false, isBadVersion(5) and (4) are true
 *   n = 1, bad = 1  ->  1
 *
 * CONSTRAINTS
 *   1 <= bad <= n <= 2^31 - 1
 * </pre>
 */
public class LC278FirstBadVersion {

    // Stand-in for the judge's parent class. Leave it alone: the test overrides isBadVersion.
    public static class VersionControl {
        public boolean isBadVersion(int version) {
            throw new UnsupportedOperationException("the test supplies the API");
        }
    }

    public static class Solution extends VersionControl {

        /**
         * Variant A - brute force: ask about version 1, 2, 3, ... until one is bad
         * <br>Target: time O(n), space O(1)    (up to n API calls)
         */
        public int firstBadVersionBruteForce(int n) {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        /**
         * Variant B - this chapter's technique: binary search for the good/bad boundary
         * <br>Target: time O(log n), space O(1)    (O(log n) API calls)
         */
        public int firstBadVersion(int n) {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", (api, n) -> api.firstBadVersionBruteForce(n), false);
        runAll("B", (api, n) -> api.firstBadVersion(n), true);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 278. First Bad Version)
    // https://leetcode.com/problems/first-bad-version/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // The test plays the judge: it subclasses Solution and overrides isBadVersion, counting the
    // calls and stopping a runaway loop (an exception after too many calls turns into a FAIL).
    // Each variant (A, B) is checked separately with the same cases: the two official examples
    // plus both ends. The huge-n case (n = 2^31 - 1: overflow-safe middle, at most 32 API calls)
    // runs for Variant B only - asking about 2 billion versions one by one is not an option.
    //   Expected result: 0/9 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(LC278FirstBadVersion.Solution api, int n);
    }

    private static void runAll(String v, Solver s, boolean withHugeN) {
        // official examples
        check(v + " example 1", 4, () -> s.solve(judge(4, new int[1], 1000), 5));
        check(v + " example 2", 1, () -> s.solve(judge(1, new int[1], 1000), 1));
        // edge: the very first version is already bad
        check(v + " first version bad", 1, () -> s.solve(judge(1, new int[1], 1000), 10));
        // edge: only the very last version is bad
        check(v + " last version bad", 10, () -> s.solve(judge(10, new int[1], 1000), 10));
        if (!withHugeN) return;
        // edge: n = 2^31 - 1 and only the last version is bad; (lo + hi) overflows an int
        check(v + " huge n: [answer, at most 32 calls]", Arrays.asList(Integer.MAX_VALUE, true),
                () -> {
                    int[] calls = new int[1];
                    int answer = s.solve(judge(Integer.MAX_VALUE, calls, 100), Integer.MAX_VALUE);
                    return Arrays.asList(answer, calls[0] <= 32);
                });
    }

    // the judge: version >= firstBad is bad; counts the calls and gives up after maxCalls
    private static LC278FirstBadVersion.Solution judge(int firstBad, int[] calls, int maxCalls) {
        return new LC278FirstBadVersion.Solution() {
            @Override
            public boolean isBadVersion(int version) {
                if (++calls[0] > maxCalls) {
                    throw new IllegalStateException("more than " + maxCalls + " API calls");
                }
                return version >= firstBad;
            }
        };
    }

    // one case: a wrong value or an exception counts as FAIL; the run always continues
    private static void check(String name, Object expected, Supplier<Object> actual) {
        total++;
        Object got;
        try {
            got = actual.get();
        } catch (Throwable t) {
            got = "threw " + t.getClass().getSimpleName() + ": " + t.getMessage();
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
