package Leetcode.Ch03HashingAsASearchToLookupTransform;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1. Two Sum   [Easy]
 * https://leetcode.com/problems/two-sum/
 * Topics: Array, Hash Table
 * Book: Algorithmic Thinking - ch. 3 Hashing as a search-to-lookup transform, Practice #3
 *       also: ch. 83 What n tells you (#2)
 *
 * PROBLEM
 *   Given an int array nums and an int target, find the two entries (at different positions)
 *   whose values add up to target and return their two indices, in any order. The input is
 *   guaranteed to contain exactly one such pair, and one element cannot be used twice.
 *   The statement's follow-up: do better than O(n^2).
 *
 * EXAMPLES
 *   nums = [2,7,11,15], target = 9  ->  [0,1]    2 + 7 = 9
 *   nums = [3,2,4],     target = 6  ->  [1,2]    2 + 4 = 6; the lone 3 cannot pair with itself
 *   nums = [3,3],       target = 6  ->  [0,1]    equal values are fine at two positions
 *
 * CONSTRAINTS
 *   2 <= nums.length <= 10^4,  -10^9 <= nums[i] <= 10^9,  -10^9 <= target <= 10^9,
 *   exactly one valid answer exists
 *
 * INTUITION
 *   For a number x the partner we need is target - x. Searching the array for it costs O(n)
 *   per number (O(n^2) overall); a HashMap that remembers the numbers already visited
 *   (value -> index) answers "is the partner there?" in O(1). Walk left to right: if the
 *   partner of nums[i] is already in the map, the pair is complete; if not, store nums[i]
 *   and go on. Every pair is found when its LATER member is reached, so one pass suffices.
 *   Looking up BEFORE storing keeps a number from pairing with itself.
 *
 * APPROACH (as implemented below)
 *   1. map: value -> index of every number visited so far.
 *   2. For each position i, complement = target - nums[i] is the partner we need.
 *   3. value = map.get(complement). Not null -> the partner was visited earlier, at index
 *      value, so return {value, i}.
 *   4. Otherwise map.put(nums[i], i) and continue.
 *   5. The final return {0, 0} is never reached on valid input (a pair always exists).
 *
 * DRY RUN   nums = [2,7,11,15], target = 9   (what main() runs)
 *   i=0: complement = 7, map is empty -> miss;  put(2, 0)        map = {2=0}
 *   i=1: complement = 2, map.get(2) = 0 -> hit; return {0, 1}
 *   Another trace, nums = [3,2,4], target = 6: the 3 is stored, the 2 (which needs 4) is
 *   stored, then the 4 needs 2 -> found at index 1 -> returns [1,2]. The 3 never meets itself.
 *
 * COMPLEXITY
 *   Time : O(n) - one pass, and each HashMap get/put is O(1) on average
 *   Space: O(n) - the map can end up holding one entry per number
 *
 * GOTCHAS
 *   - Look up first, store second. Storing first would let a number match its own entry
 *     (a single 3 with target 6). [3,3] still works: the second 3 finds the first.
 *   - value is an Integer, not an int, so map.get can return null for "not visited yet".
 *     Index 0 is a legitimate answer, so 0 cannot be the "not found" marker.
 *   - target - nums[i] can reach -2*10^9 or +2*10^9, which still fits in an int.
 *   - The answer is {earlier index, current index}; LeetCode accepts any order.
 *
 * STATUS
 *   Correct (every pair returned by the code was checked against a brute-force reference
 *   on random arrays). The final "return new int[]{0, 0}" is unreachable on valid input.
 *   Test file: main().java in this folder.
 *
 * RELATED
 *   - LC 167 Two Sum II - Input Array Is Sorted - same task on sorted input, where two
 *     pointers replace the map (O(1) extra space).
 *   - LC 15 3Sum - fix one number, then run a two-sum search for the other two.
 *   - LC 560 Subarray Sum Equals K - the same "remember what you have seen in a map" trick,
 *     applied to running prefix sums.
 *
 * SEE ALSO
 *   src/Practice/HashMap/LC1TwoSumHashMap.java - the same one-pass value -> index map
 *   src/Practice/HashMap/LC1TwoSumComplementMap.java
 *       - map keyed by the partner each number is waiting for (target - x), not by the value
 *   src/Practice/HashMap/LC1TwoSumThreeApproaches.java
 *       - brute force, hash map, and sort + two pointers (the sort version has a known bug)
 * </pre>
 */
public class LC1TwoSum {
    public static void main(String[] args) {
        // Example 1 - 2 + 7 = 9. Expected output: [0, 1]
        int[] nums = {2,7,11,15};
        int target = 9;
        System.out.println(Arrays.toString(twoSum(nums, target)));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }

    // One pass with a HashMap of visited value -> index. Returns {earlier index, current index}.
    public static int[] twoSum(int[] nums, int target) {
        // value -> index of every number visited so far
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // the partner that would complete the sum together with nums[i]
            int complement = target - nums[i];
            // Integer, not int: null means "not visited yet" (0 is a valid index)
            Integer value = map.get(complement);
            if(value != null){
                // the partner came earlier, at index value
                return new int[]{value, i};
            }
            // stored only AFTER the lookup, so a number cannot pair with itself
            map.put(nums[i], i);
        }
        // not reached: the problem guarantees exactly one valid pair
        return new int[]{0, 0};
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 1. Two Sum)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the 3 official examples + edge cases (negatives, zeros, the complement reaching
    // 2 * 10^9, the pair at the end of the array). Every input has exactly one valid pair, as the
    // problem promises; expected values from the statement or checked against an all-pairs brute
    // force. The answer may come in any order, so both sides are sorted before comparing.
    //   Expected result: 7/7 passed
    // ==========================================================================
    private static void runTests() {
        // official examples
        check("example 1", new int[]{0, 1}, () -> testTwoSum(new int[]{2, 7, 11, 15}, 9));
        check("example 2: the lone 3 cannot pair with itself", new int[]{1, 2},
                () -> testTwoSum(new int[]{3, 2, 4}, 6));
        check("example 3: equal values at two positions", new int[]{0, 1},
                () -> testTwoSum(new int[]{3, 3}, 6));
        // edge: a negative number and its positive twin
        check("negatives", new int[]{0, 2}, () -> testTwoSum(new int[]{-3, 4, 3, 90}, 0));
        // edge: two zeros with target 0
        check("zeros", new int[]{0, 3}, () -> testTwoSum(new int[]{0, 4, 3, 0}, 0));
        // edge: for -10^9 the complement is 10^9 - (-10^9) = 2 * 10^9, still inside int
        check("complement of 2 * 10^9", new int[]{1, 2},
                () -> testTwoSum(new int[]{-1000000000, 5, 999999995}, 1000000000));
        // edge: the pair is the last two entries
        check("pair at the end", new int[]{2, 3}, () -> testTwoSum(new int[]{5, 1, 8, 4}, 12));
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

    // any order is accepted by the problem, so sort the returned pair first
    private static int[] testTwoSum(int[] nums, int target) {
        int[] r = LC1TwoSum.twoSum(nums, target).clone();
        Arrays.sort(r);
        return r;
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
