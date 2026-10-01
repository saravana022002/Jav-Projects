package Leetcode.Ch03HashingAsASearchToLookupTransform;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 454. 4Sum II   [Medium]
 * https://leetcode.com/problems/4sum-ii/
 * Topics: Array, Hash Table
 * Book: Algorithmic Thinking - ch. 3 Hashing as a search-to-lookup transform, Practice #10
 *       also: ch. 60 Meet in the middle (#4)
 *
 * PROBLEM
 *   Four int arrays nums1, nums2, nums3, nums4 of the same length n are given. Count the
 *   index tuples (i, j, k, l), each index in 0..n-1, for which
 *   nums1[i] + nums2[j] + nums3[k] + nums4[l] is 0. Tuples are counted by position, so equal
 *   values at different positions count separately.
 *
 * EXAMPLES
 *   nums1=[1,2], nums2=[-2,-1], nums3=[-1,2], nums4=[0,2]  ->  2
 *       the tuples (0,0,0,1): 1 + -2 + -1 + 2 = 0   and   (1,1,0,0): 2 + -1 + -1 + 0 = 0
 *   nums1=[0], nums2=[0], nums3=[0], nums4=[0]             ->  1
 *
 * CONSTRAINTS
 *   n == nums1.length == nums2.length == nums3.length == nums4.length,   1 <= n <= 200,
 *   -2^28 <= nums1[i], nums2[i], nums3[i], nums4[i] <= 2^28
 *
 * INTUITION
 *   Trying all four indices is O(n^4). Split the equation in the middle instead:
 *       nums1[i] + nums2[j]  =  -(nums3[k] + nums4[l])
 *   First tabulate every pair sum of (nums1, nums2) in a HashMap, together with HOW MANY pairs
 *   give that sum (at most n^2 entries). Then, for each pair from (nums3, nums4), the number
 *   of matching first-half pairs is just the map's count for -(nums3[k] + nums4[l]). Two
 *   double loops (2 * n^2 steps) replace the four nested ones: "meet in the middle", with the
 *   hash table as the meeting point.
 *
 * APPROACH (as implemented below)
 *   1. map1: pair sum -> how many (i, j) pairs of nums1 x nums2 make it.
 *      For every i in nums1 and j in nums2: map1.put(i + j, map1.getOrDefault(i + j, 0) + 1).
 *      (i and j are VALUES taken from the arrays, not indices.)
 *   2. count = 0. For every k in nums3 and l in nums4: target = -(k + l), and
 *      count += map1.getOrDefault(target, 0) - all first-half pairs that complete a zero sum.
 *   3. Return count.
 *
 * DRY RUN   nums1=[1,2], nums2=[-2,-1], nums3=[-1,2], nums4=[0,2]   (what main() runs)
 *   step 1: pair sums 1+(-2)=-1, 1+(-1)=0, 2+(-2)=0, 2+(-1)=1   -> map1 = {-1=1, 0=2, 1=1}
 *   step 2: (k=-1, l=0): target = 1    map1 has 1  -> +1   count = 1
 *           (k=-1, l=2): target = -1   map1 has 1  -> +1   count = 2
 *           (k= 2, l=0): target = -2   absent      -> +0
 *           (k= 2, l=2): target = -4   absent      -> +0
 *   return 2
 *
 * COMPLEXITY
 *   Time : O(n^2) - two double loops of n * n steps with O(1) average hash work each
 *          (n = 200: 2 * 40,000 steps instead of 200^4 = 1.6 * 10^9)
 *   Space: O(n^2) - map1 can hold up to n^2 distinct pair sums
 *
 * GOTCHAS
 *   - Tuples are counted by POSITION, so the map must store a COUNT per sum, not just
 *     presence: four arrays of [0,0] give 2 * 2 * 2 * 2 = 16, not 1.
 *   - A 2 + 2 split balances the work. A 1 + 3 split would need n^3 stored triple sums.
 *   - int is enough: a pair sum is at most 2 * 2^28 = 2^29 in size, and the answer is at
 *     most 200^4 = 1,600,000,000, below the int limit 2,147,483,647.
 *
 * STATUS
 *   Correct (checked against an n^4 brute force on random arrays, including values of +-2^28;
 *   the all-zero n = 200 input gives 1,600,000,000 as it should).
 *   Test file: main().java in this folder.
 *
 * RELATED
 *   - LC 18 4Sum - one array, and the answer lists the distinct quadruplets, so it needs
 *     sorting and two pointers instead of this counting trick.
 *   - LC 1 Two Sum - the same "look up the complement" idea, here applied to pair sums
 *     (solved in this folder).
 * </pre>
 */
public class LC454FourSumII {
    public static void main(String[] args) {
        // Example 1 - the zero-sum tuples are (0,0,0,1) and (1,1,0,0). Expected output: 2
        int[] nums1 = {1, 2};
        int[] nums2 = {-2, -1};
        int[] nums3 = {-1, 2};
        int[] nums4 = {0, 2};
        System.out.println(fourSumCount(nums1, nums2, nums3, nums4));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }
    // Counts the tuples (i, j, k, l) that sum to 0. Meet in the middle: store the pair sums of
    // nums1 x nums2, then look up the negated pair sums of nums3 x nums4. O(n^2).
    public static int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        // first-half pair sum -> how many (i, j) pairs produce it
        Map<Integer, Integer> map1 = new HashMap<>();
        int count = 0;
        for (int i : nums1) {
            for (int j : nums2) {
                map1.put(i + j, map1.getOrDefault(i + j, 0) + 1);
            }
        }
        // each (k, l) needs a first half that sums to -(k + l); add how many pairs have it
        for (int k : nums3) {
            for (int l : nums4) {
                int target = -(k + l);
                count += map1.getOrDefault(target, 0);
            }
        }

        return count;
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 454. 4Sum II)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the 2 official examples + edge cases (no matching tuple, equal values counted per
    // position, values at +-2^28, the largest possible answer for n = 200); expected values from
    // the statement or checked against an n^4 brute force.
    //   Expected result: 6/6 passed
    // ==========================================================================
    private static void runTests() {
        // official examples
        check("example 1: two zero-sum tuples", 2,
                () -> count(new int[]{1, 2}, new int[]{-2, -1}, new int[]{-1, 2}, new int[]{0, 2}));
        check("example 2: all zeros, n = 1", 1,
                () -> count(new int[]{0}, new int[]{0}, new int[]{0}, new int[]{0}));
        // edge: n = 1 and 1 + 1 + 1 + 1 is not 0
        check("no tuple at all", 0,
                () -> count(new int[]{1}, new int[]{1}, new int[]{1}, new int[]{1}));
        // edge: equal values are counted per position, 2 * 2 * 2 * 2 tuples
        check("equal values count per position", 16,
                () -> count(new int[]{0, 0}, new int[]{0, 0}, new int[]{0, 0}, new int[]{0, 0}));
        // edge: values at +-2^28; the tuples with two +2^28 and two -2^28 are C(4,2) = 6
        int[] m = {268435456, -268435456};
        check("values at +-2^28", 6, () -> count(m, m, m, m));
        // edge: n = 200, every value 0 -> all 200^4 tuples count; still fits in an int
        int[] zeros = new int[200];
        check("n = 200, all zeros (largest answer)", 1600000000,
                () -> count(zeros, zeros, zeros, zeros));
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

    private static int count(int[] a, int[] b, int[] c, int[] d) {
        return LC454FourSumII.fourSumCount(a, b, c, d);
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
