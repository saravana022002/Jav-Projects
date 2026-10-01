package Leetcode.Ch03HashingAsASearchToLookupTransform;

import java.util.HashSet;
import java.util.Set;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 128. Longest Consecutive Sequence   [Medium]
 * https://leetcode.com/problems/longest-consecutive-sequence/
 * Topics: Array, Hash Table, Union-Find
 * Book: Algorithmic Thinking - ch. 3 Hashing as a search-to-lookup transform, Practice #11
 *
 * PROBLEM
 *   nums is an unsorted int array. Look for values that follow each other with no gap
 *   (x, x+1, x+2, ...) - they may sit anywhere in the array, and a value that occurs several
 *   times counts once. Return the length of the longest such run, or 0 for an empty array.
 *   The problem demands O(n) time, so "just sort it" (O(n log n)) is not the intended answer.
 *
 * EXAMPLES
 *   nums = [100,4,200,1,3,2]        ->  4    the run 1,2,3,4
 *   nums = [0,3,7,2,5,8,4,6,0,1]    ->  9    the run 0..8 (the second 0 adds nothing)
 *   nums = [1,0,1,2]                ->  3    the run 0,1,2
 *
 * CONSTRAINTS
 *   0 <= nums.length <= 10^5,   -10^9 <= nums[i] <= 10^9
 *
 * INTUITION
 *   Once the values sit in a HashSet, "is x in the array?" costs O(1), so a run can be
 *   walked upward cheaply: x, x+1, x+2, ... while the next value is in the set.
 *   The question is WHERE to start walking. If every value started a walk, a long run would
 *   be re-walked from each of its members and [1..n] would cost O(n^2). So only the smallest
 *   member of a run may walk: x is a run start exactly when x - 1 is NOT in the set.
 *   Every value is then stepped over by the one walk of its own run, so all the walking
 *   together is O(n), even though a while loop sits inside a for loop.
 *
 * APPROACH (as implemented below)
 *   1. Put every value of nums into the HashSet integers (duplicates collapse).
 *   2. max = 0. For each num in integers: if integers contains num - 1, num is inside a run
 *      that is counted from its start - skip it (continue).
 *   3. Otherwise num starts a run: counter = 1, and while integers contains num + counter,
 *      counter++. The run is num, num+1, ..., num+counter-1.
 *   4. max = Math.max(max, counter). After the loop return max (0 for an empty array).
 *
 * DRY RUN   nums = [100,4,200,1,3,2]   (what main() runs)
 *   integers = {1,2,3,4,100,200}   (the loop really visits them in HashSet order; the
 *                                   result does not depend on that order)
 *   num=1     : 0 is absent -> run start; 2,3,4 present, 5 absent -> counter = 4    max = 4
 *   num=2,3,4 : num - 1 is present -> skip
 *   num=100   : 99 absent -> run start; 101 absent -> counter = 1                   max = 4
 *   num=200   : 199 absent -> run start; 201 absent -> counter = 1                  max = 4
 *   return 4
 *
 * COMPLEXITY
 *   Time : O(n) expected - n set inserts, then the while loop runs only from run starts, so
 *          over the whole for loop each value is stepped over at most once
 *   Space: O(n) - the set holds every distinct value
 *
 * GOTCHAS
 *   - The "num - 1 present -> skip" check is what makes this O(n). Without it the answers
 *     stay correct but input such as [1..n] costs O(n^2): every value walks the rest of
 *     its run.
 *   - Duplicates are harmless, the set stores each value once ([1,0,1,2] -> 3).
 *   - An empty array never enters the loops, so max stays 0 - as the problem requires.
 *   - num + counter never leaves the int range here (at most 10^9 + 10^5).
 *
 * STATUS
 *   Correct (checked against a sort-and-scan reference on thousands of random arrays,
 *   including negatives, duplicates and the empty array).
 *   Test file: main().java in this folder.
 *
 * RELATED
 *   - LC 298 Binary Tree Longest Consecutive Sequence - the same "longest run" question,
 *     asked along the paths of a binary tree instead of in a set of numbers.
 *   - LC 217 Contains Duplicate - the same HashSet-as-instant-lookup building block
 *     (solved in this folder).
 *
 * SEE ALSO
 *   src/Practice/HashMap/LC128LongestConsecutiveSequenceV1.java
 *       - same set idea plus a sort + HashMap version; its set version returns MIN_VALUE for []
 *   src/Practice/HashMap/LC128LongestConsecutiveSequenceV2.java
 *       - same set idea plus a sort-and-scan version (which has a sentinel bug at -98)
 * </pre>
 */
public class LC128LongestConsecutiveSequence {
    public static void main(String[] args) {
        // Example 1 - the longest run is 1,2,3,4. Expected output: 4
        int[] nums = {100, 4, 200, 1, 3, 2};
        System.out.println(longestConsecutive(nums));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }

    // Returns the length of the longest run of consecutive integers (0 for an empty array).
    // O(n): a HashSet for instant lookups, and only run starts walk upward.
    public static int longestConsecutive(int[] nums) {
        // every distinct value, for O(1) "is x present?" checks
        Set<Integer> integers = new HashSet<>();
        for (int num : nums) {
            integers.add(num);
        }
        int max = 0;
        for (int num : integers) {
            // num - 1 exists -> num is not a run start; its run is counted from the start
            if (integers.contains(num - 1)) {
                continue;
            }
            // num is a run start: walk upward while the next value exists
            int counter = 1;
            while (integers.contains(num + counter)) {
                counter++;
            }
            // counter = length of the run that starts at num
            max = Math.max(max, counter);
        }
        return max;
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 128. Longest Consecutive Sequence)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the 3 official examples + edge cases (empty array, only duplicates, negatives
    // around zero, values at the +-10^9 limits); expected values from the statement or checked
    // against a sort-and-scan brute force.
    //   Expected result: 7/7 passed
    // ==========================================================================
    private static void runTests() {
        // official examples
        check("example 1: run 1,2,3,4", 4, () -> longest(100, 4, 200, 1, 3, 2));
        check("example 2: run 0..8", 9, () -> longest(0, 3, 7, 2, 5, 8, 4, 6, 0, 1));
        check("example 3: duplicate 1, run 0,1,2", 3, () -> longest(1, 0, 1, 2));
        // edge: empty array (nums.length can be 0) -> 0
        check("empty array", 0, () -> longest());
        // edge: one value repeated counts once
        check("only duplicates", 1, () -> longest(5, 5, 5, 5));
        // edge: a run that crosses zero, given out of order, plus a far-away value
        check("negatives around zero", 5, () -> longest(-3, -1, -2, 0, 1, 9));
        // edge: values at the +-10^9 limits (999999998, 999999999, 1000000000 is a run of 3)
        check("values at the limits", 3,
                () -> longest(1000000000, -1000000000, 999999999, 999999998));
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

    private static int longest(int... nums) {
        return LC128LongestConsecutiveSequence.longestConsecutive(nums);
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
        if (o instanceof long[]) return Arrays.toString((long[]) o);
        if (o instanceof char[]) return Arrays.toString((char[]) o);
        if (o instanceof boolean[]) return Arrays.toString((boolean[]) o);
        if (o instanceof Object[]) return Arrays.deepToString((Object[]) o);
        return String.valueOf(o);
    }

    // ============================== END TESTS ===============================
}
