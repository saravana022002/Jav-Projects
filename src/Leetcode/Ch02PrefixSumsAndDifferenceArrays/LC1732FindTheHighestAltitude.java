package Leetcode.Ch02PrefixSumsAndDifferenceArrays;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1732. Find the Highest Altitude   [Easy]
 * https://leetcode.com/problems/find-the-highest-altitude/
 * Topics: Array, Prefix Sum
 * Book: Algorithmic Thinking - ch. 2 Prefix sums and difference arrays, Practice #1
 *
 * PROBLEM
 *   A biker rides through n + 1 points in a row and starts at point 0, altitude 0. The array
 *   gain (length n) gives the change on each leg: gain[i] is the net altitude change between
 *   point i and point i + 1. Return the highest altitude reached at any point - the starting
 *   point included.
 *
 * EXAMPLES
 *   gain = [-5,1,5,0,-7]        ->  1   altitudes 0,-5,-4,1,1,-6
 *   gain = [-4,-3,-2,-1,4,3,2]  ->  0   altitudes 0,-4,-7,-9,-10,-6,-3,-1: the start is the peak
 *
 * CONSTRAINTS
 *   n == gain.length,   1 <= n <= 100,   -100 <= gain[i] <= 100
 *
 * INTUITION
 *   A point's altitude is the sum of all the gains before it, i.e. a prefix sum of gain, where
 *   the empty prefix (the start) is 0. The answer is therefore the largest prefix sum, so one
 *   pass with a running total and a running maximum is enough.
 *
 * APPROACH (as implemented below)
 *   1. prefixSum has n + 1 slots; prefixSum[0] = 0 is the starting altitude.
 *      count = the running altitude, max = best so far = prefixSum[0] = 0.
 *   2. For each i: count += gain[i]; prefixSum[i + 1] = count;
 *      max = max(max, prefixSum[i + 1]).
 *   3. Return max.
 *
 * DRY RUN   gain = [-5,1,5,0,-7] (the input main() runs)
 *   start     count = 0                         max = 0
 *   i=0  -5   count = -5   prefixSum[1] = -5    max = 0
 *   i=1  +1   count = -4   prefixSum[2] = -4    max = 0
 *   i=2  +5   count = 1    prefixSum[3] = 1     max = 1
 *   i=3   0   count = 1    prefixSum[4] = 1     max = 1
 *   i=4  -7   count = -6   prefixSum[5] = -6    max = 1
 *   return 1
 *
 * COMPLEXITY
 *   Time : O(n) - one pass over gain
 *   Space: O(n) for the prefixSum array. Only count and max are ever read back, so O(1)
 *          would be enough.
 *
 * GOTCHAS
 *   - The start counts: max begins at prefixSum[0] = 0, not at a very small number. If the
 *     biker only goes downhill the answer is 0 (example 2), not the least negative altitude.
 *   - n gains describe n + 1 points, hence the array of n + 1 slots.
 *   - prefixSum[0] = 0 is redundant (new int[] is zero-filled already) but harmless.
 *   - Altitudes stay within 100 * 100 = 10^4 in absolute value, so int never overflows.
 *
 * STATUS
 *   Correct (checked against a brute force on random gain arrays).
 *
 * RELATED
 *   - LC 303 Range Sum Query - Immutable (same chapter) - the same prefix-sum array, used to
 *     answer range sums instead of finding a maximum.
 *   - LC 724 Find Pivot Index (same chapter) - prefix sums compared with the sum on the right.
 * </pre>
 */
public class LC1732FindTheHighestAltitude {

    public static void main(String[] args) {
        // Example 1: the altitudes are 0,-5,-4,1,1,-6, so this prints 1
        int[] gain = {-5,1,5,0,-7};
        System.out.println(largestAltitude(gain));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }
    // Returns the largest prefix sum of gain, the start (0) included. O(n)
    public static int largestAltitude(int[] gain) {
        // prefixSum[p] = altitude of point p; point 0 is the start
        int[] prefixSum = new int[gain.length + 1];
        prefixSum[0] = 0;
        // the running altitude
        int count = 0;
        // the start is already a candidate for the highest point
        int max = prefixSum[0];
        for(int i = 0; i < gain.length; i++){
            // ride leg i to reach point i + 1
            count += gain[i];
            prefixSum[i + 1] = count;
            max = Math.max(max, prefixSum[i + 1]);
        }
        return max;
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 1732. Find the Highest Altitude)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the two official examples + edge cases. Expected values come from the statement,
    // from the reasoning noted per case, and (checked offline) from a brute force that adds up
    // the gains before every point from scratch.
    //   Expected result: 6/6 passed
    // ==========================================================================
    private static void runTests() {
        // official examples
        check("example 1: [-5,1,5,0,-7]", 1,
                () -> LC1732FindTheHighestAltitude.largestAltitude(new int[]{-5, 1, 5, 0, -7}));
        int[] downThenUp = {-4, -3, -2, -1, 4, 3, 2};
        check("example 2: [-4,-3,-2,-1,4,3,2]", 0,
                () -> LC1732FindTheHighestAltitude.largestAltitude(downThenUp));
        // edge: a single climb - the last point is the highest
        check("single climb [5]", 5,
                () -> LC1732FindTheHighestAltitude.largestAltitude(new int[]{5}));
        // edge: a single drop - the start (0) is the highest
        check("single drop [-3]", 0,
                () -> LC1732FindTheHighestAltitude.largestAltitude(new int[]{-3}));
        // edge: up 50 times by 100, then down 50 times by 100 - the peak is in the middle (5000)
        int[] hill = new int[100];
        for (int i = 0; i < hill.length; i++) hill[i] = i < 50 ? 100 : -100;
        check("hill: 50 x +100 then 50 x -100", 5000,
                () -> LC1732FindTheHighestAltitude.largestAltitude(hill));
        // edge: the largest possible climb, 100 legs of +100
        int[] climb = new int[100];
        Arrays.fill(climb, 100);
        check("100 x +100", 10000, () -> LC1732FindTheHighestAltitude.largestAltitude(climb));
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

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


