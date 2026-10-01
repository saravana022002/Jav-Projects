package Leetcode.Ch02PrefixSumsAndDifferenceArrays;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1109. Corporate Flight Bookings   [Medium]
 * https://leetcode.com/problems/corporate-flight-bookings/
 * Topics: Array, Prefix Sum
 * Book: Algorithmic Thinking - ch. 2 Prefix sums and difference arrays, Practice #10
 *       also: ch. 20 Intervals and sweep line (#7), ch. 39 Fenwick trees (#10)
 *
 * PROBLEM
 *   Flights are numbered 1..n. Each booking [first, last, seats] reserves `seats` seats on
 *   every flight from `first` to `last`, both ends included. Return an array answer of length
 *   n in which answer[i] is the total number of seats reserved on flight i + 1 (the output
 *   is 0-based, the flight labels are 1-based).
 *
 * EXAMPLES
 *   bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5  ->  [10,55,45,25,25]
 *        flight:       1   2   3   4   5
 *        booking 1:   10  10
 *        booking 2:       20  20
 *        booking 3:       25  25  25  25
 *        total:       10  55  45  25  25
 *   bookings = [[1,2,10],[2,2,15]], n = 2  ->  [10,25]
 *
 * CONSTRAINTS
 *   1 <= n <= 2 * 10^4,   1 <= bookings.length <= 2 * 10^4,   bookings[i].length == 3,
 *   1 <= first <= last <= n,   1 <= seats <= 10^4
 *
 * INTUITION
 *   Adding `seats` to every flight in a range costs O(range) per booking - up to
 *   2 * 10^4 * 2 * 10^4 = 4 * 10^8 steps. Instead mark only the two ends of the range in a
 *   difference array: "+seats starts here" at the first flight, "-seats stops here" at the
 *   flight just after the last one. A single left-to-right running sum then turns the marks
 *   into totals, because each flight's total is the sum of every mark at or before it:
 *   a booking's +seats counts while we are inside its range and is cancelled right after it.
 *
 * APPROACH (as implemented below)
 *   1. diff = int[n], all zeros. For each booking [start, end, count]:
 *        diff[start - 1] += count        flight `start` is index start - 1
 *        diff[end] -= count              index `end` is the flight AFTER the range - but only
 *                                        if end < n (a range that reaches the last flight
 *                                        has nothing after it)
 *   2. Running sum: count += diff[j]; ans[j] = count for j = 0 .. n-1.
 *   3. Return ans.
 *
 * DRY RUN   bookings = [[1,2,10],[2,3,20],[2,5,25]], n = 5 (the input main() runs)
 *   diff starts   [ 0,  0,  0,  0, 0]
 *   [1,2,10]      diff[0] += 10, diff[2] -= 10        [10,  0, -10,   0, 0]
 *   [2,3,20]      diff[1] += 20, diff[3] -= 20        [10, 20, -10, -20, 0]
 *   [2,5,25]      diff[1] += 25, end = 5 = n: skip    [10, 45, -10, -20, 0]
 *   running sum   10, 10+45 = 55, 55-10 = 45, 45-20 = 25, 25+0 = 25
 *   -> [10, 55, 45, 25, 25]
 *
 * COMPLEXITY
 *   Time : O(n + m) for m bookings - O(1) per booking, then one sweep over n flights
 *   Space: O(n) for diff, plus the O(n) answer array
 *
 * GOTCHAS
 *   - Labels are 1-based, indexes 0-based: the "+" goes to start - 1, the "-" goes to `end`
 *     (which is the index of flight end + 1, the first flight after the range).
 *   - The `end < n` guard keeps diff[end] inside the array when a booking ends at flight n.
 *   - `count` is declared twice: the first one (the booking's seats) lives only inside the
 *     for-each body, the second (the running sum) comes after it - legal, but easy to mix up.
 *   - Totals fit in an int: at most 2 * 10^4 bookings * 10^4 seats = 2 * 10^8 on one flight.
 *
 * STATUS
 *   Correct (checked against a brute force on random bookings).
 *
 * RELATED
 *   - LC 1094 Car Pooling (same chapter) - the same difference array, over kilometres:
 *     +passengers where a trip starts, -passengers where it ends.
 *   - LC 3356 Zero Array Transformation II - another problem built on applying many range
 *     updates cheaply (a difference array).
 * </pre>
 */
public class LC1109CorporateFlightBookings {
    public static void main(String[] args) {
        // Example 1: prints [10, 55, 45, 25, 25]
        int[][] bookings = {{1,2,10},{2,3,20},{2,5,25}};
        int n = 5;
        System.out.println(Arrays.toString(corpFlightBookings(bookings, n)));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }

    // Difference array + one running sum. O(n + number of bookings)
    public static int[] corpFlightBookings(int[][] bookings, int n) {
        int[] ans = new int[n];
        // diff[i] = how much the seat total changes when stepping onto flight i + 1 (index i)
        int[] diff = new int[n];
        for(int[] arr : bookings){
            int start = arr[0];
            int end  = arr[1];
            int count = arr[2];
            // the booking starts at flight `start` (index start - 1): +count from there on
            diff[start - 1] += count;
            // it stops after flight `end`; index `end` is the first flight past the range,
            // and a range that reaches flight n has no flight after it
            if(end < n){
                diff[end] -= count;
            }
        }
        // running sum of the marks = seats reserved on each flight
        int count = 0;
        for(int j = 0; j < n; j++){
            count += diff[j];
            ans[j] = count;
        }
        return ans;
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 1109. Corporate Flight Bookings)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the two official examples + edge cases. Expected values come from the statement,
    // from the reasoning noted per case, and (checked offline) from a brute force that adds each
    // booking to every flight in its range one by one.
    //   Expected result: 6/6 passed
    // ==========================================================================
    private static void runTests() {
        // official examples
        check("example 1", new int[]{10, 55, 45, 25, 25}, () -> LC1109CorporateFlightBookings
                .corpFlightBookings(new int[][]{{1, 2, 10}, {2, 3, 20}, {2, 5, 25}}, 5));
        check("example 2", new int[]{10, 25}, () -> LC1109CorporateFlightBookings
                .corpFlightBookings(new int[][]{{1, 2, 10}, {2, 2, 15}}, 2));
        // edge: one flight, one booking (the range ends at n, so nothing is subtracted)
        check("n = 1", new int[]{7}, () -> LC1109CorporateFlightBookings
                .corpFlightBookings(new int[][]{{1, 1, 7}}, 1));
        // edge: a booking that covers every flight
        check("booking covers all flights", new int[]{3, 3, 3, 3, 3},
                () -> LC1109CorporateFlightBookings.corpFlightBookings(new int[][]{{1, 5, 3}}, 5));
        // edge: a booking for the last flight only
        check("last flight only", new int[]{0, 0, 0, 0, 4}, () -> LC1109CorporateFlightBookings
                .corpFlightBookings(new int[][]{{5, 5, 4}}, 5));
        // edge: the most extreme sums - 20000 bookings of 10^4 seats on all 20000 flights,
        // so every flight holds 20000 * 10000 = 200000000 seats (still fits in an int)
        int[][] many = new int[20000][];
        for (int i = 0; i < many.length; i++) many[i] = new int[]{1, 20000, 10000};
        int[] expected = new int[20000];
        Arrays.fill(expected, 200000000);
        check("20000 full-range bookings of 10000", expected,
                () -> LC1109CorporateFlightBookings.corpFlightBookings(many, 20000));
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
