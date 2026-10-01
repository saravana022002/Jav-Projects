package Leetcode.Ch03HashingAsASearchToLookupTransform;

import java.util.HashSet;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 217. Contains Duplicate   [Easy]
 * https://leetcode.com/problems/contains-duplicate/
 * Topics: Array, Hash Table, Sorting
 * Book: Algorithmic Thinking - ch. 3 Hashing as a search-to-lookup transform, Practice #1
 *       also: ch. 6 Sorting as a preprocessing step (#3), ch. 83 What n tells you (#1)
 *
 * PROBLEM
 *   Given an int array nums, return true if some value occurs at least twice, and false if
 *   all the values are different.
 *
 * EXAMPLES
 *   nums = [1,2,3,1]                 ->  true     1 sits at indices 0 and 3
 *   nums = [1,2,3,4]                 ->  false    every value is distinct
 *   nums = [1,1,1,3,3,4,3,2,4,2]     ->  true     several values repeat
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 10^5,   -10^9 <= nums[i] <= 10^9
 *
 * INTUITION
 *   "Duplicate" just means "I have already seen this value". So remember the values seen so
 *   far while walking the array: if the current value is already remembered we are done,
 *   otherwise remember it. HashSet.add does both in one call - it returns false when the
 *   value was already in the set - and a hash operation is O(1), so one pass is enough.
 *   (Alternatives: sort first so equal values become neighbours - O(n log n), no extra
 *   memory - or compare every pair - O(n^2).)
 *
 * APPROACH (as implemented below)
 *   1. s: an empty HashSet for the values seen so far.
 *   2. For each num in nums: s.add(num). If that returns false, num was already in s ->
 *      return true.
 *   3. The loop ended without a repeat -> return false.
 *
 * DRY RUN   nums = [1,2,3,1]   (what main() runs)
 *   num=1: add returns true (new)        s = {1}
 *   num=2: add returns true (new)        s = {1,2}
 *   num=3: add returns true (new)        s = {1,2,3}
 *   num=1: add returns false (present)   -> return true
 *
 * COMPLEXITY
 *   Time : O(n) - one pass with O(1) average hash work per value (stops at the first repeat)
 *   Space: O(n) - when all values are distinct, every one of them is stored
 *
 * GOTCHAS
 *   - s.add(num) returns a boolean (true = the value was new), so "if (!s.add(num))" is a
 *     lookup and an insert in a single step.
 *   - Only equality matters, not distance: [1,2,3,1] is true although the 1s are far apart.
 *     (LC 219 adds a distance limit.)
 *   - A one-element array cannot contain a duplicate: [7] -> false.
 *
 * STATUS
 *   Correct (checked against an all-pairs reference on random arrays). main() calls
 *   containsDuplicate but does not print the result (it would be true).
 *   Test file: main().java in this folder.
 *
 * RELATED
 *   - LC 219 Contains Duplicate II - the repeats must also be at most k positions apart
 *     (solved in this folder).
 *   - LC 220 Contains Duplicate III - near-equal values within an index window (solved in
 *     this folder).
 *
 * SEE ALSO
 *   src/Practice/HashMap/LC217ContainsDuplicate.java
 *       - the same one-pass idea with a HashMap used as a set (containsKey, then put)
 * </pre>
 */
public class LC217ContainsDuplicate {
    public static void main(String[] args) {
        // Example 1 - nothing is printed; the method returns true (the 1 occurs twice)
        int[] nums = {1,2,3,1};
        containsDuplicate(nums);
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }
    // Returns true as soon as a value is seen for the second time (hash-based, O(n)).
    public static boolean containsDuplicate(int[] nums) {
        // the values seen so far
        HashSet<Integer> s = new HashSet<>();
        for (int num : nums) {
            // add() returns false when num was already in the set -> a duplicate
            if (!s.add(num)) {
                return true;
            }
        }
        // every value was new
        return false;
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 217. Contains Duplicate)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the 3 official examples + edge cases (one element, a repeat only in the last two
    // positions, values at the +-10^9 limits, distinct and repeated); expected values from the
    // statement or checked against an all-pairs brute force.
    //   Expected result: 7/7 passed
    // ==========================================================================
    private static void runTests() {
        // official examples
        check("example 1: 1 occurs twice", true, () -> dup(1, 2, 3, 1));
        check("example 2: all distinct", false, () -> dup(1, 2, 3, 4));
        check("example 3: several repeats", true, () -> dup(1, 1, 1, 3, 3, 4, 3, 2, 4, 2));
        // edge: one element cannot repeat
        check("single element", false, () -> dup(7));
        // edge: the repeat is only found at the very end of the array
        check("repeat in the last two positions", true, () -> dup(1, 2, 3, 4, 4));
        // edge: the extreme values -10^9 and 10^9, once each / the same extreme twice
        check("limit values, distinct", false, () -> dup(1000000000, -1000000000));
        check("limit value twice", true, () -> dup(-1000000000, 5, -1000000000));
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

    private static boolean dup(int... nums) {
        return LC217ContainsDuplicate.containsDuplicate(nums);
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
