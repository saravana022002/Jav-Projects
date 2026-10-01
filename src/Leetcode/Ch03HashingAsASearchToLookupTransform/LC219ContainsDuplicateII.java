package Leetcode.Ch03HashingAsASearchToLookupTransform;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 219. Contains Duplicate II   [Easy]
 * https://leetcode.com/problems/contains-duplicate-ii/
 * Topics: Array, Hash Table, Sliding Window
 * Book: Algorithmic Thinking - ch. 3 Hashing as a search-to-lookup transform, Practice #2
 *       also: ch. 41 Ordered maps in practice (#1)
 *
 * PROBLEM
 *   Given an int array nums and an int k, is there a pair of different positions i and j that
 *   hold the same value and are at most k places apart (|i - j| <= k)? Return true if there
 *   is, false otherwise. In plain words: some value repeats, and two of its copies sit no
 *   more than k positions from each other.
 *
 * EXAMPLES
 *   nums = [1,2,3,1],     k = 3  ->  true     the 1s are at indices 0 and 3, 3 apart
 *   nums = [1,0,1,1],     k = 1  ->  true     the last two 1s are neighbours
 *   nums = [1,2,3,1,2,3], k = 2  ->  false    equal values are always 3 apart, more than k
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 10^5,   -10^9 <= nums[i] <= 10^9,   0 <= k <= 10^5
 *
 * INTUITION
 *   For the current number the only earlier copy worth checking is the MOST RECENT one: it is
 *   the closest, so if even that one is more than k away, every older copy is too. So remember,
 *   for each value, the index where it was last seen (a HashMap value -> last index). One pass:
 *   look up the previous index of the current value; if it exists and is within k, we are
 *   done. Either way, store the current index as the new "last seen".
 *   (An alternative keeps a HashSet of only the last k values and slides it along - the same
 *   O(n) time with O(min(n, k)) space.)
 *
 * APPROACH (as implemented below)
 *   1. ints: value -> the index where that value was last seen. i is the current index
 *      (starts at 0).
 *   2. For each num: prev = ints.put(num, i). put stores the NEW index and returns the OLD one
 *      (null if num is new), so one call both finds the previous copy and records this one.
 *   3. prev != null and |i - prev| <= k -> return true.
 *   4. i++ and go on; when the loop ends return false.
 *
 * DRY RUN   nums = [1,0,1,1], k = 1   (what main() runs)
 *   i=0 num=1: put returns null                    -> ints = {1=0}
 *   i=1 num=0: put returns null                    -> ints = {1=0, 0=1}
 *   i=2 num=1: put returns 0, |2 - 0| = 2 > 1      -> ints = {1=2, 0=1}   (last index of 1 moved)
 *   i=3 num=1: put returns 2, |3 - 2| = 1 <= 1     -> return true
 *
 * COMPLEXITY
 *   Time : O(n) - one pass, O(1) average per HashMap put
 *   Space: O(n) - up to one entry per distinct value
 *
 * GOTCHAS
 *   - put overwrites even when the check fails, and that is on purpose: the map must hold the
 *     LAST index, so the next copy is compared with the closest one. [1,2,3,1,1] with k = 1:
 *     the first 1 is too far from index 3, but the entry then moves to 3 and index 4 matches it.
 *   - k = 0 can never succeed: two different positions are at least 1 apart.
 *   - Math.abs is not really needed (i is always larger than prev) but it is harmless.
 *
 * STATUS
 *   Correct (checked against an all-pairs reference on random arrays). The import HashSet is
 *   unused. Test file: main().java in this folder.
 *
 * RELATED
 *   - LC 217 Contains Duplicate - the same question without any distance limit (solved in
 *     this folder).
 *   - LC 220 Contains Duplicate III - values only need to be NEAR each other, not equal, inside
 *     the index window (solved in this folder).
 * </pre>
 */
public class LC219ContainsDuplicateII {
    public static void main(String[] args) {
        // Example 2 - the last two 1s are neighbours. Expected output: true
        int[] nums = {1,0,1,1};
        int k = 1;
        System.out.println(containsNearbyDuplicate(nums , k));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }
    // True when some value occurs at two positions at most k apart. One pass that remembers
    // the last index of every value. O(n).
    public static boolean containsNearbyDuplicate(int[] nums, int k) {
        // value -> the index where it was last seen
        Map<Integer, Integer> ints = new HashMap<>();
        // index of the current number
        int i = 0;
        for (int num : nums) {
            // put() stores the current index and returns the previous one (null if num is new)
            Integer prev = ints.put(num, i);
            // the closest earlier copy is within k positions -> found
            if(prev != null && Math.abs(i - prev) <= k){
                return true;
            }
            i++;
        }
        return false;
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 219. Contains Duplicate II)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the 3 official examples + edge cases (k = 0, a single element, distance exactly k and
    // k + 1, a nearer copy that only appears after the first one was too far); expected values
    // from the statement or checked against an all-pairs brute force.
    //   Expected result: 8/8 passed
    // ==========================================================================
    private static void runTests() {
        // official examples
        check("example 1: 1s are 3 apart, k=3", true, () -> near(3, 1, 2, 3, 1));
        check("example 2: neighbouring 1s, k=1", true, () -> near(1, 1, 0, 1, 1));
        check("example 3: equal values 3 apart, k=2", false, () -> near(2, 1, 2, 3, 1, 2, 3));
        // edge: k = 0 never succeeds, two positions are at least 1 apart
        check("k = 0", false, () -> near(0, 1, 1));
        // edge: a single element has no pair
        check("single element", false, () -> near(5, 1));
        // edge: distance exactly k is allowed, k + 1 is not
        check("distance equal to k", true, () -> near(2, 1, 2, 1));
        check("distance k + 1", false, () -> near(1, 1, 2, 1));
        // edge: the first 1 is too far from index 3, but index 4 is next to the copy at 3
        check("last index must be kept", true, () -> near(1, 1, 2, 3, 1, 1));
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

    // k first, then the array elements
    private static boolean near(int k, int... nums) {
        return LC219ContainsDuplicateII.containsNearbyDuplicate(nums, k);
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
