package Leetcode.Ch03HashingAsASearchToLookupTransform;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 220. Contains Duplicate III   [Hard]
 * https://leetcode.com/problems/contains-duplicate-iii/
 * Topics: Array, Sliding Window, Sorting, Bucket Sort, Ordered Set
 * Book: Algorithmic Thinking - ch. 3 Hashing as a search-to-lookup transform, Practice #12
 *       also: ch. 7 Non-comparison sorts: counting, bucket, radix (#10),
 *             ch. 41 Ordered maps in practice (#2)
 *
 * PROBLEM
 *   Given an int array nums and two ints indexDiff and valueDiff, decide whether two
 *   different positions i and j exist that are close in BOTH senses: at most indexDiff apart
 *   in position (|i - j| <= indexDiff) and at most valueDiff apart in value
 *   (|nums[i] - nums[j]| <= valueDiff). Return true if such a pair exists, false if not.
 *
 * EXAMPLES
 *   nums = [1,2,3,1],     indexDiff = 3, valueDiff = 0  ->  true    positions 0 and 3: both 1
 *   nums = [1,5,9,1,5,9], indexDiff = 2, valueDiff = 3  ->  false   values within 2 positions
 *                                                          always differ by 4 or more
 *
 * CONSTRAINTS
 *   2 <= nums.length <= 10^5,   -10^9 <= nums[i] <= 10^9,
 *   1 <= indexDiff <= nums.length,   0 <= valueDiff <= 10^9
 *
 * INTUITION
 *   Only the last indexDiff numbers can be partners of the current number x, so keep exactly
 *   those in a window. A partner y must also lie in [x - valueDiff, x + valueDiff]. If the
 *   window is a SORTED set, that is a single question: what is the smallest stored value that
 *   is >= x - valueDiff (the "ceiling")? If even that value is > x + valueDiff, nothing in
 *   the window is close enough. A TreeSet answers it in O(log k), and lets the oldest number
 *   leave when the window slides on. Brute force just tests every pair.
 *   (Not in this file: the "Bucket Sort" tag points to a third, O(n) approach - put each
 *   value in a bucket of width valueDiff + 1 and look only at its own and the two
 *   neighbouring buckets.)
 *
 * APPROACHES IN THIS FILE
 *   A. containsNearbyAlmostDuplicate()       - test every pair (i, j) with i < j
 *                                              - O(n^2) time, O(1) space   <- main() runs this one
 *   B. containsNearbyAlmostDuplicateBetter() - sliding window in a TreeSet&lt;Long>, ceiling()
 *                                              - O(n log k) time, O(k) space   (k = window size)
 *
 * APPROACH (as implemented below, method B)
 *   1. arr: a TreeSet&lt;Long> holding the window = the previous indexDiff numbers.
 *   2. For each i: current = nums[i] as a long, so current +- valueDiff cannot overflow.
 *   3. floorOrCeiling = arr.ceiling(current - valueDiff) - the smallest stored value that is
 *      >= current - valueDiff (despite the name it is the ceiling). If it exists and is
 *      <= current + valueDiff, it lies within valueDiff of current -> return true.
 *   4. Otherwise arr.add(current).
 *   5. If i >= indexDiff the window is one entry too long: remove nums[i - indexDiff].
 *   6. The loop ended without a hit -> return false.
 *
 * DRY RUN   nums = [1,5,9,1,5,9], indexDiff = 2, valueDiff = 3   (method B)
 *   i=0 cur=1: ceiling(-2) = none          add 1   arr = {1}
 *   i=1 cur=5: ceiling(2)  = none          add 5   arr = {1,5}
 *   i=2 cur=9: ceiling(6)  = none          add 9   arr = {1,5,9}; drop nums[0]=1 -> {5,9}
 *   i=3 cur=1: ceiling(-2) = 5 > 1+3       add 1   arr = {1,5,9}; drop nums[1]=5 -> {1,9}
 *   i=4 cur=5: ceiling(2)  = 9 > 5+3       add 5   arr = {1,5,9}; drop nums[2]=9 -> {1,5}
 *   i=5 cur=9: ceiling(6)  = none          add 9   arr = {1,5,9}; drop nums[3]=1 -> {5,9}
 *   loop ends -> false   (a ceiling that is too big for cur + valueDiff counts as no hit)
 *   Method A on example 1 (what main() runs): (0,1) values differ by 1 > 0; (0,2) differ by
 *   2 > 0; (0,3) positions 3 apart <= 3 and values differ by 0 <= 0 -> true.
 *
 * COMPLEXITY
 *   A. Time O(n^2) - n(n-1)/2 pairs (about 5 * 10^9 for n = 10^5, too slow there);
 *      Space O(1). The inner loop never stops early once j - i > indexDiff.
 *   B. Time O(n log k) - per number one ceiling, one add and one remove on a tree of at most
 *      k = min(indexDiff, n) entries;  Space O(k) for the window
 *
 * GOTCHAS
 *   - Why long: current - valueDiff and current + valueDiff can reach +-2 * 10^9, uncomfortably
 *     close to the int limit (2147483647), so the set and the arithmetic use long.
 *   - arr.remove((long) nums[i - indexDiff]): the cast boxes a Long, which matches the Long
 *     elements. A plain int would box to Integer, and the TreeSet would then throw a
 *     ClassCastException when comparing it with a Long.
 *   - The set never holds two equal values: an equal value in the window would have made the
 *     ceiling check succeed (valueDiff >= 0) and the method would already have returned.
 *   - Removal comes AFTER the check and the add, so while i is checked the set holds exactly
 *     the numbers at positions i - indexDiff .. i - 1.
 *   - In method A the test i != j is always true (j starts at i + 1) and abs(i - j) is just j - i.
 *     Math.abs(nums[i] - nums[j]) is an int subtraction: at most 2 * 10^9, so it cannot overflow.
 *
 * STATUS
 *   Both methods are correct (checked against a brute-force reference on random inputs,
 *   including values at +-10^9). main() runs only method A; method B is never called.
 *   The imports HashMap and Map are unused. The commented lines above method A are your own
 *   notes of the three conditions and were kept.
 *   Test file: main().java in this folder (it tests both methods).
 *
 * RELATED
 *   - LC 217 Contains Duplicate - the plain version: any two equal values (solved in this
 *     folder).
 *   - LC 219 Contains Duplicate II - the same problem with valueDiff = 0: equal values at most
 *     k positions apart (solved in this folder).
 * </pre>
 */
public class LC220ContainsDuplicateIII {
    public static void main(String[] args) {
        // Example 1 - the two 1s are 3 positions apart (<= indexDiff). Expected output: true
        // (this runs method A, the brute force; method B is not called here)
        int[] nums = {1,2,3,1};
        int indexDiff = 3, valueDiff = 0;
        System.out.println(containsNearbyAlmostDuplicate(nums, indexDiff, valueDiff));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }

    // Approach A: brute force - test every pair (i, j), i < j, against the three conditions
    // noted below. O(n^2) time, O(1) space. main() runs this one.
    ////

//    i != j

//    abs(i - j) <= indexDiff

//    abs(nums[i] - nums[j]) <= valueDiff
    public static boolean containsNearbyAlmostDuplicate(int[] nums, int indexDiff, int valueDiff) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                // all three conditions at once (j > i, so i != j always holds)
                if(i != j && Math.abs(i - j) <= indexDiff && Math.abs(nums[i] - nums[j]) <= valueDiff){
                    return true;
                }
            }
        }
        return false;
    }


    // Approach B: sliding window of the last indexDiff numbers in a sorted set; one ceiling()
    // call per number. O(n log k) time, O(k) space. Not called by main().
    public static boolean containsNearbyAlmostDuplicateBetter(int[] nums, int indexDiff, int valueDiff) {
        // the window: the previous indexDiff numbers, sorted (Long so the arithmetic stays safe)
        TreeSet<Long> arr = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            long current = nums[i];
            // smallest stored value >= current - valueDiff (the name says floor or ceiling,
            // but it is the ceiling)
            Long floorOrCeiling = arr.ceiling(current - valueDiff);
            // it is also <= current + valueDiff -> within valueDiff of current
            if(floorOrCeiling != null && floorOrCeiling <= current + valueDiff){
                return true;
            }
            arr.add(current);
            // the window is now one too long: drop the number indexDiff positions back
            if(i >= indexDiff){
                arr.remove((long) nums[i - indexDiff]);
            }
        }
        return false;
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 220. Contains Duplicate III)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // The file has two methods, so every case runs BOTH and compares the pair of answers
    // [brute force, TreeSet window] with the expected value.
    // Cases: the 2 official examples + edge cases (an index gap just too big, the shortest input,
    // a value gap exactly at / one above valueDiff, the +-10^9 limits, the window sliding on);
    // expected values from the statement or checked against an all-pairs brute force.
    //   Expected result: 8/8 passed
    // ==========================================================================
    private static void runTests() {
        // official examples
        check("example 1: both 1s are 3 apart", both(true), () -> run(new int[]{1, 2, 3, 1}, 3, 0));
        check("example 2: close in index, never close in value", both(false),
                () -> run(new int[]{1, 5, 9, 1, 5, 9}, 2, 3));
        // edge: the equal values are 3 apart but the window allows only 2
        check("index gap one too big", both(false), () -> run(new int[]{1, 2, 3, 1}, 2, 0));
        // edge: the shortest allowed array, two equal neighbours
        check("two equal neighbours", both(true), () -> run(new int[]{4, 4}, 1, 0));
        // edge: value gap exactly valueDiff (4) is allowed, one more (valueDiff 3) is not
        check("value gap equal to valueDiff", both(true), () -> run(new int[]{1, 5}, 1, 4));
        check("value gap one above valueDiff", both(false), () -> run(new int[]{1, 5}, 1, 3));
        // edge: -10^9 and 10^9 differ by 2 * 10^9, more than any allowed valueDiff
        check("limits -10^9 and 10^9", both(false),
                () -> run(new int[]{-1000000000, 1000000000}, 1, 1000000000));
        // edge: window of 1 - the first 8 has already left when the second 8 arrives
        check("window slides on", both(false), () -> run(new int[]{8, 1, 8}, 1, 0));
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

    // answers of [brute force, TreeSet window] for one input
    private static List<Boolean> run(int[] nums, int indexDiff, int valueDiff) {
        boolean brute = LC220ContainsDuplicateIII
                .containsNearbyAlmostDuplicate(nums.clone(), indexDiff, valueDiff);
        boolean window = LC220ContainsDuplicateIII
                .containsNearbyAlmostDuplicateBetter(nums.clone(), indexDiff, valueDiff);
        return Arrays.asList(brute, window);
    }

    // the expected answer pair when both methods must return the same value
    private static List<Boolean> both(boolean expected) {
        return Arrays.asList(expected, expected);
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
