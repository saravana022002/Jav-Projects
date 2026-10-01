package Leetcode.Ch05BitManipulationAsASetEncoding;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 78. Subsets   [Medium]
 * https://leetcode.com/problems/subsets/
 * Topics: Array, Backtracking, Bit Manipulation
 * Book: Algorithmic Thinking - ch. 5 Bit manipulation as a set encoding, Practice #8
 *       also: ch. 56 Recursion and the shape of a subproblem (#9), ch. 57 Backtracking (#1),
 *             ch. 60 Meet in the middle (#1), ch. 71 Bitmask DP (#1)
 *
 * PROBLEM
 *   Given an int array nums whose values are all different, return every possible subset
 *   (the power set): the empty subset, each single element, each pair, ... up to nums
 *   itself. No subset may be listed twice, and the subsets may come back in any order.
 *
 * EXAMPLES
 *   nums = [1,2,3]  ->  [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]    2^3 = 8 subsets
 *   nums = [0]      ->  [[],[0]]                                       without 0 / with 0
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 10,   -10 <= nums[i] <= 10,   all numbers in nums are unique
 *
 * INTUITION
 *   A subset is one yes / no decision per element, so n elements give 2^n subsets - and a
 *   binary number with n digits is exactly n yes / no decisions. Write i in binary and let
 *   bit j of i say whether nums[j] is in the subset. Counting i from 0 to 2^n - 1 then
 *   visits every possible choice pattern exactly once: every subset appears, none twice
 *   (different i means a different bit pattern, and the elements are unique). A set of up
 *   to 10 items becomes a single int - the "set encoding" of this chapter.
 *
 * APPROACH (as implemented below)
 *   1. n = nums.length, ans = an empty list of subsets.
 *   2. For every i from 0 to (1 << n) - 1, add a new empty list to ans: the subset for i.
 *   3. For every j from 0 to n - 1: if bit j of i is set, ((i >> j) & 1) == 1, append nums[j]
 *      to the list just added, ans.get(ans.size() - 1).
 *   4. Return ans.
 *
 * DRY RUN   nums = [1,2,3]   (the input main() uses; bit 0 = 1, bit 1 = 2, bit 2 = 3)
 *   i = 0  (000)  -> []
 *   i = 1  (001)  -> [1]
 *   i = 2  (010)  -> [2]
 *   i = 3  (011)  -> [1,2]
 *   i = 4  (100)  -> [3]
 *   i = 5  (101)  -> [1,3]
 *   i = 6  (110)  -> [2,3]
 *   i = 7  (111)  -> [1,2,3]
 *   the result lists the subsets in exactly the order of the first example
 *
 * COMPLEXITY
 *   Time : O(n * 2^n) - 2^n values of i, n bit tests each; this is also the size of the output
 *          (n * 2^(n-1) numbers in total, as every element is in half of the subsets)
 *   Space: O(1) besides the output itself (2^n lists)
 *
 * GOTCHAS
 *   - The loop starts at i = 0 on purpose: that is the empty subset, which must be in the answer.
 *   - Subsets come out in "binary counting" order (nums[0] flips fastest), not grouped by
 *     size; the problem accepts any order.
 *   - 1 << n is safe because n <= 10; it would overflow int from n = 31 on.
 *   - Every subset gets its own new ArrayList, so no list is shared between subsets - unlike
 *     the recursive version, which must copy its working list.
 *
 * SEE ALSO
 *   src/Practice/Backtracking/LC78Subsets.java - the same problem by recursion (take or skip
 *   each element, then backtrack); its subsets come out in a different order
 *
 * RELATED
 *   - LC 90 Subsets II (in this repo) - the same task when nums may contain duplicates.
 *   - LC 1863 Sum of All Subset XOR Totals (in this repo) - the same mask loop, XOR-ing each
 *     subset instead of listing it.
 *   - LC 2044 Count Number of Maximum Bitwise-OR Subsets - the same enumeration, OR-ing
 *     each subset.
 * </pre>
 */
public class LC78Subsets {
    public static void main(String[] args) {
        // example 1: prints [[], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3]]
       int[] nums = {1,2,3};
        System.out.println(subsets(nums));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }

    // Returns all 2^n subsets: bit j of the counter i says whether nums[j] is in subset i
    public static List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        // every i from 0 to 2^n - 1 is one subset (i = 0 is the empty subset)
        for (int i = 0; i < (1 << n); i++) {
            // start the list for subset i
            ans.add(new ArrayList<>());
            for (int j = 0; j < n; j++) {
                // bit j of i is set: nums[j] belongs to this subset, so add it to the list
                // that was just started
                if(((i >> j) & 1) == 1){
                    ans.get(ans.size() - 1).add(nums[j]);
                }
            }
        }
        return ans;
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 78. Subsets)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the two official examples + edge cases (one element, the extreme values -10 and 10,
    // an unsorted array, the largest allowed length 10). "Return the answer in any order": both
    // sides are normalised first (each subset sorted, then the list of subsets sorted by size
    // and elements). The expected subsets are written by hand, or for length 10 produced by an
    // independent take / skip recursion in this file.
    //   Expected result: 6/6 passed
    // ==========================================================================
    private static void runTests() {
        // official examples
        check("example 1: [1,2,3]",
                normalise(lists(new int[]{}, new int[]{1}, new int[]{2}, new int[]{1, 2},
                        new int[]{3}, new int[]{1, 3}, new int[]{2, 3}, new int[]{1, 2, 3})),
                () -> normalise(LC78Subsets.subsets(new int[]{1, 2, 3})));
        check("example 2: [0]",
                normalise(lists(new int[]{}, new int[]{0})),
                () -> normalise(LC78Subsets.subsets(new int[]{0})));
        // edge: one element
        check("single element [5]",
                normalise(lists(new int[]{}, new int[]{5})),
                () -> normalise(LC78Subsets.subsets(new int[]{5})));
        // edge: the extreme values of the constraints
        check("[-10, 10]",
                normalise(lists(new int[]{}, new int[]{-10}, new int[]{10}, new int[]{-10, 10})),
                () -> normalise(LC78Subsets.subsets(new int[]{-10, 10})));
        // edge: an unsorted array gives the same 8 subsets as [1,2,3] (as sets of values)
        check("unsorted [3,1,2]",
                normalise(lists(new int[]{}, new int[]{1}, new int[]{2}, new int[]{3},
                        new int[]{1, 2}, new int[]{1, 3}, new int[]{2, 3}, new int[]{1, 2, 3})),
                () -> normalise(LC78Subsets.subsets(new int[]{3, 1, 2})));
        // edge: the largest allowed length, 10 -> 1024 subsets, compared with a recursion
        int[] ten = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        List<List<Integer>> reference = new ArrayList<>();
        generate(ten, 0, new ArrayList<>(), reference);
        check("length 10: 1024 subsets", normalise(reference),
                () -> normalise(LC78Subsets.subsets(ten)));
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

    // independent reference: take / skip every element in turn (recursion)
    private static void generate(int[] nums, int index, List<Integer> current,
                                 List<List<Integer>> out) {
        if (index == nums.length) {
            out.add(new ArrayList<>(current));
            return;
        }
        current.add(nums[index]);
        generate(nums, index + 1, current, out);
        current.remove(current.size() - 1);
        generate(nums, index + 1, current, out);
    }

    // builds a list of subsets from int arrays: lists(new int[]{}, new int[]{1}) -> [[], [1]]
    private static List<List<Integer>> lists(int[]... subsets) {
        List<List<Integer>> out = new ArrayList<>();
        for (int[] subset : subsets) {
            List<Integer> one = new ArrayList<>();
            for (int v : subset) {
                one.add(v);
            }
            out.add(one);
        }
        return out;
    }

    // "any order": sort every subset, then sort the list of subsets (by size, then by elements)
    private static List<List<Integer>> normalise(List<List<Integer>> in) {
        List<List<Integer>> out = new ArrayList<>();
        for (List<Integer> subset : in) {
            List<Integer> copy = new ArrayList<>(subset);
            Collections.sort(copy);
            out.add(copy);
        }
        out.sort((x, y) -> {
            if (x.size() != y.size()) return Integer.compare(x.size(), y.size());
            for (int k = 0; k < x.size(); k++) {
                int c = Integer.compare(x.get(k), y.get(k));
                if (c != 0) return c;
            }
            return 0;
        });
        return out;
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
