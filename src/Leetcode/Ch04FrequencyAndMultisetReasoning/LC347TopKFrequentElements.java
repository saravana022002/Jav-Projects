package Leetcode.Ch04FrequencyAndMultisetReasoning;

import java.util.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 347. Top K Frequent Elements   [Medium]
 * https://leetcode.com/problems/top-k-frequent-elements/
 * Topics: Array, Hash Table, Divide and Conquer, Sorting, Heap (Priority Queue),
 *         Bucket Sort, Counting, Quickselect
 * Book: Algorithmic Thinking - ch. 4 Frequency and multiset reasoning, Practice #7
 *       also: ch. 7 Non-comparison sorts: counting, bucket, radix (#6),
 *             ch. 13 Quickselect and partition (#8), ch. 18 Heaps and the k-selection family (#5),
 *             ch. 86 Randomisation as a defence against the adversary (#4)
 *
 * PROBLEM
 *   Given an integer array nums and a number k, return the k values that occur most often
 *   in nums. The answer may come back in any order, and it is guaranteed to be unique (there
 *   is no tie for the k-th place). The follow-up asks for better than O(n log n) time.
 *
 * EXAMPLES
 *   nums = [1,1,1,2,2,3], k = 2              ->  [1,2]   1 occurs 3 times, 2 twice, 3 once
 *   nums = [1], k = 1                        ->  [1]
 *   nums = [1,2,1,2,1,2,3,1,3,2], k = 2      ->  [1,2]   1 and 2 occur 4 times, 3 only twice
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 10^5,   -10^4 <= nums[i] <= 10^4
 *   k is in [1, number of distinct values],   the answer is unique
 *
 * INTUITION
 *   Count how often each value occurs (HashMap). A count can only be a whole number between
 *   1 and nums.length, so the count itself can serve as an array index: "bucket sort by
 *   count". buckets[f] holds the values that occur exactly f times. Reading the buckets from
 *   the highest index downwards lists the values from most to least frequent without any
 *   comparison sort, and we stop as soon as k values are collected. That is O(n) in total,
 *   which answers the follow-up (a full sort costs O(n log n), a size-k heap O(n log k)).
 *
 * APPROACH (as implemented below)
 *   1. map: value -> number of occurrences (a HashMap sized for nums.length entries).
 *   2. buckets = new List[nums.length + 1]. For every map entry, add its value (the key) to
 *      buckets[count], creating that list on first use. Index 0 is never used.
 *   3. result = new int[k]. Walk i from buckets.length - 1 down to 1, skip empty buckets
 *      and copy the values of buckets[i] into result[index++].
 *   4. As soon as index == k, return result.
 *
 * DRY RUN   nums = {1,2,1,2,1,2,3,1,3,2}, k = 2   (what main() runs)
 *   map = {1:4, 2:4, 3:2}
 *   buckets[4] = [1, 2]    buckets[2] = [3]    every other bucket is null
 *   i = 10 .. 5   all null, skipped
 *   i = 4         copy 1 -> result[0], index = 1;   copy 2 -> result[1], index = 2 == k
 *   return [1, 2]
 *
 * COMPLEXITY
 *   Time : O(n) - counting is n steps, filling the buckets is one step per distinct value
 *          (at most n), the downward walk touches at most n bucket slots and copies k values
 *   Space: O(n) - the map (one entry per distinct value), the bucket array (n + 1 slots) and
 *          the lists (together one entry per distinct value)
 *
 * GOTCHAS
 *   - buckets needs nums.length + 1 slots: the largest possible count is nums.length (all
 *     values equal) and array indexes start at 0. Slot 0 stays empty, so the walk stops at 1.
 *   - new List[...] is a raw generic array (Java cannot create List&lt;Integer>[] directly),
 *     hence the compiler's "unchecked" note. It is harmless here.
 *   - The guaranteed-unique answer means there is never a tie at the k-th place. With a
 *     tie, whichever of the tied values comes first in its bucket (HashMap order) would win.
 *   - The result is filled most frequent first; the order is not required by the problem.
 *   - k larger than the number of distinct values is outside the constraints: the tail of
 *     result would simply stay 0.
 *
 * RELATED
 *   - LC 451 Sort Characters By Frequency - the same count-then-bucket idea on characters;
 *     solved in LC451SortCharactersByFrequency.java in this folder.
 *   - LC 215 Kth Largest Element in an Array - the heap alternative for "best k items";
 *     solved in src/Practice/Heap (two versions).
 *   - LC 692 Top K Frequent Words - the same task on strings with alphabetical tie-breaks.
 *
 * SEE ALSO
 *   src/Practice/Heap/LC347TopKFrequentElements.java - the same problem with a size-k min-heap
 *     over the (value, count) entries, O(n + m log k) with m distinct values, plus a
 *     bubble-sort version. The bucket sort in this file is the O(n) way.
 * </pre>
 */
public class LC347TopKFrequentElements {

    public static void main(String[] args) {
        // Example 3 - prints [1, 2]: 1 and 2 occur four times each, 3 only twice
        int[] nums = {1,2,1,2,1,2,3,1,3,2};
        int k = 2;
        System.out.println(Arrays.toString(topKFrequent(nums, k)));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }

    // Bucket sort by count: returns the k most frequent values, most frequent first. O(n)
    public static int[] topKFrequent(int[] nums, int k) {
        // value -> number of occurrences
        Map<Integer, Integer> map = new HashMap<>(nums.length);
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // buckets[f] = the values that occur exactly f times (f is at most nums.length)
        List<Integer>[] buckets = new List[nums.length + 1];
        for (Map.Entry<Integer, Integer> mapEntry : map.entrySet()) {
            int freq = mapEntry.getValue();
            // create the bucket for this count the first time it is needed
            if (buckets[freq] == null) {
                buckets[freq] = new ArrayList<>();
            }
            buckets[mapEntry.getValue()].add(mapEntry.getKey());
        }


        // read the buckets from the highest count down; the first k values met are the answer
        int[] result = new int[k];
        int index = 0;
        for (int i = buckets.length - 1; i > 0; i--) {
            if (buckets[i] == null) {
                continue;
            }
            for (int l : buckets[i]) {
                result[index++] = l;
                // k values collected -> done (the unique-answer guarantee means no tie to break)
                if (index == k) {
                    return result;
                }
            }
        }
        return result;
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 347. Top K Frequent Elements)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the 3 official examples + edge cases (k = number of distinct values, negative and
    // boundary values, all values equal, n = 10^5). The problem accepts any order, so the result
    // is sorted before it is compared. Every input has a unique answer, as the problem promises;
    // the expected values were checked against a count-and-sort brute force done outside the solution.
    //   Expected result: 9/9 passed
    // ==========================================================================
    private static void runTests() {
        // official examples
        check("example 1: [1,1,1,2,2,3], k=2", new int[]{1, 2},
                () -> topK(new int[]{1, 1, 1, 2, 2, 3}, 2));
        check("example 2: [1], k=1", new int[]{1}, () -> topK(new int[]{1}, 1));
        check("example 3: [1,2,1,2,1,2,3,1,3,2], k=2", new int[]{1, 2},
                () -> topK(new int[]{1, 2, 1, 2, 1, 2, 3, 1, 3, 2}, 2));
        // edge: every value occurs once and k asks for all of them
        check("k = number of distinct values", new int[]{4, 5, 6},
                () -> topK(new int[]{4, 5, 6}, 3));
        // edge: negative numbers and the value bounds -10^4 and 10^4
        check("negative and boundary values", new int[]{-10000, 0},
                () -> topK(new int[]{-10000, -10000, 10000, 0, 0, 0}, 2));
        // edge: k = 1 with several runners-up
        check("k = 1, clear winner", new int[]{3},
                () -> topK(new int[]{3, 3, 3, 3, 1, 2, 2, 1, 5}, 1));
        // edge: all values equal
        check("all values equal", new int[]{7}, () -> topK(new int[]{7, 7, 7, 7}, 1));
        // edge: n = 10^5 and every element equal (the count equals nums.length)
        check("10^5 equal elements", new int[]{5}, () -> topK(filled(100000, 5), 1));
        // edge: n = 10^5 with three groups: 60000 zeros, 30000 ones, 10000 twos
        check("10^5 elements in three groups, k=2", new int[]{0, 1}, () -> {
            int[] nums = new int[100000];        // the first 60000 slots stay 0
            Arrays.fill(nums, 60000, 90000, 1);
            Arrays.fill(nums, 90000, 100000, 2);
            return topK(nums, 2);
        });
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

    // the problem accepts the answer in any order, so sort it before comparing
    private static int[] topK(int[] nums, int k) {
        int[] result = LC347TopKFrequentElements.topKFrequent(nums, k);
        Arrays.sort(result);
        return result;
    }

    private static int[] filled(int n, int value) {
        int[] a = new int[n];
        Arrays.fill(a, value);
        return a;
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
