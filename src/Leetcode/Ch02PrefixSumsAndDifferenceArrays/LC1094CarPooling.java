package Leetcode.Ch02PrefixSumsAndDifferenceArrays;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1094. Car Pooling   [Medium]
 * https://leetcode.com/problems/car-pooling/
 * Topics: Array, Sorting, Heap (Priority Queue), Simulation, Prefix Sum
 * Book: Algorithmic Thinking - ch. 2 Prefix sums and difference arrays, Practice #11
 *       also: ch. 20 Intervals and sweep line (#6)
 *
 * PROBLEM
 *   A car with `capacity` empty seats drives east only. Each trip is
 *   [numPassengers, from, to]: that many people get in at kilometre `from` and get out at
 *   kilometre `to`. At a kilometre where one trip ends and another begins, the leaving
 *   passengers get out BEFORE the new ones get in. Return true if the car never holds more
 *   than `capacity` people at any moment, false otherwise.
 *
 * EXAMPLES
 *   trips = [[2,1,5],[3,3,7]], capacity = 4  ->  false   at km 3 the car holds 2 + 3 = 5 > 4
 *   trips = [[2,1,5],[3,3,7]], capacity = 5  ->  true    the peak is 5, which is allowed
 *
 * CONSTRAINTS
 *   1 <= trips.length <= 1000,   trips[i].length == 3,   1 <= numPassengers <= 100,
 *   0 <= from < to <= 1000,   1 <= capacity <= 10^5
 *
 * INTUITION
 *   Only the changes matter: a trip adds its passengers at `from` and removes them at `to`.
 *   Record those changes in a difference array (diff[from] += p, diff[to] -= p). The running
 *   sum of diff up to kilometre x is then exactly the number of people on board at x. Just
 *   check that this running sum never goes above capacity.
 *   Because a drop-off and a pick-up at the same kilometre land in the same cell, only their
 *   net effect is ever compared - which is the "drop off first" rule for free.
 *
 * APPROACHES IN THIS FILE
 *   A. carPooling()    - the difference array is an ArrayList&lt;Integer> that grows up to
 *                        the largest `to` - O(n + L)                  <- main() runs this one
 *   B. carPoolingOpt() - the same idea on a fixed int[1001] (locations never exceed 1000)
 *                        - O(n + 1001)
 *   (n = trips.length, L = the largest drop-off kilometre.)
 *
 * APPROACH (as implemented below, method A)
 *   1. diff = [0]. For every trip: grow diff with zeros until index `to` exists, then
 *      diff[from] += passengers and diff[to] -= passengers.
 *   2. Walk diff from the left keeping prefixSum += diff[x]; prefixSum is the occupancy
 *      at kilometre x. If it ever exceeds capacity, return false.
 *   3. Otherwise return true.
 *
 * DRY RUN   trips = [[2,1,5],[3,5,7]], capacity = 3 (the input main() runs)
 *   trip [2,1,5]: diff[1] += 2, diff[5] -= 2   diff = [0,2,0,0,0,-2]
 *   trip [3,5,7]: grow to 8 slots, diff[5] += 3, diff[7] -= 3
 *                 diff = [0,2,0,0,0,1,0,-3]      (index 5: -2 and +3 merged into +1)
 *   prefix sums   km: 0  1  2  3  4  5  6  7
 *                     0  2  2  2  2  3  3  0     the peak 3 does not exceed capacity 3
 *   -> true. Note km 5: the 2 passengers leave and the 3 new ones enter, so the car holds 3,
 *   not 5.
 *
 * COMPLEXITY
 *   Time : O(n + L) - one pass over the trips and one pass over the difference array (L <= 1000)
 *   Space: O(L) for A (the list grows to the largest `to`); B uses a fixed 1001 slots
 *
 * GOTCHAS
 *   - The test is `prefixSum > capacity`: a car that is exactly full is fine.
 *   - The passengers leave at `to`, so subtract at index `to` (not `to + 1`); they are not
 *     on board at km `to` any more.
 *   - Method B's size 1001 relies on to <= 1000; a bigger `to` would throw
 *     ArrayIndexOutOfBoundsException (method A would still work).
 *   - B loops with `for (Integer dif : diff)` over an int[] - it works, each int is just
 *     boxed on the way.
 *
 * STATUS
 *   Both methods are correct (checked against a brute force on random trips).
 *   main() calls carPooling but does not print the result (it is true).
 *
 * RELATED
 *   - LC 1109 Corporate Flight Bookings (same chapter) - the same difference array: add at
 *     the start of a range, subtract just after its end, then take running sums.
 *   - LC 253 Meeting Rooms II - the same "how many intervals overlap at the busiest moment"
 *     question, solved with a sweep over start and end events.
 * </pre>
 */
public class LC1094CarPooling {
    public static void main(String[] args) {
        // trip 2 starts at km 5 where trip 1 ends, so 3 fit; the result (true) is not printed
        int[][] trips = {{2,1,5},{3,5,7}};
        int capacity = 3;
        carPooling(trips, capacity);
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }
    // Approach A: difference array in a growing list, then a prefix-sum scan. O(n + L)
    public static boolean carPooling(int[][] trips, int capacity) {
        // diff[x] = change in the number of passengers at kilometre x
        List<Integer> diff = new ArrayList<>();
        diff.add(0);
        for (int[] trip : trips) {
            int passengers = trip[0];
            int from = trip[1];
            int to = trip[2];
            // make sure index `to` exists
            while (diff.size() <= to) {
                diff.add(0);
            }
            // get in at `from`, get out at `to`
            diff.set(from, diff.get(from) + passengers);
            diff.set(to, diff.get(to) - passengers);
        }

        // running sum = passengers on board at each kilometre
        int prefixSum = 0;
        for (Integer dif : diff) {
            prefixSum += dif;
            if (prefixSum > capacity){
                return false;
            }
        }
        return true;
    }


    // Approach B: the same idea on a fixed int[1001], since locations are at most 1000
    public static boolean carPoolingOpt(int[][] trips, int capacity) {
        int[] diff = new int[1001];
        for (int[] trip : trips) {
            int passengers = trip[0];
            int from = trip[1];
            int to = trip[2];
            diff[from] = diff[from] + passengers;
            diff[to] = diff[to] - passengers;
        }
        int prefixSum = 0;
        for (Integer dif : diff) {
            prefixSum += dif;
            if (prefixSum > capacity){
                return false;
            }
        }
        return true;
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 1094. Car Pooling)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the two official examples + edge cases; every input is run through both methods of
    // the solution (carPooling with the growing list, carPoolingOpt with the int[1001]).
    // Expected values come from the statement, from the reasoning noted per case, and (checked
    // offline) from a brute force that counts the passengers on board at every kilometre.
    //   Expected result: 12/12 passed
    // ==========================================================================
    private static void runTests() {
        // official examples (same trips, different capacity)
        both("example 1: capacity 4", new int[][]{{2, 1, 5}, {3, 3, 7}}, 4, false);
        both("example 2: capacity 5", new int[][]{{2, 1, 5}, {3, 3, 7}}, 5, true);
        // edge: a trip ends at km 5 exactly where the next one starts - drop off first, so only 3
        both("drop-off before pick-up at km 5", new int[][]{{2, 1, 5}, {3, 5, 7}}, 3, true);
        // edge: a single trip from km 0 that is bigger than the capacity
        both("one trip too big, from km 0", new int[][]{{3, 0, 1}}, 2, false);
        // edge: exactly full is allowed (3 passengers, capacity 3)
        both("exactly full", new int[][]{{1, 0, 3}, {1, 0, 3}, {1, 0, 3}}, 3, true);
        // edge: the last kilometre allowed (1000): 100 + 100 = 200 > 199
        both("overload at km 999..1000",
                new int[][]{{100, 999, 1000}, {100, 999, 1000}}, 199, false);
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

    // runs one input through both methods of the solution
    private static void both(String name, int[][] trips, int capacity, boolean expected) {
        check(name + "  [carPooling]", expected,
                () -> LC1094CarPooling.carPooling(trips, capacity));
        check(name + "  [carPoolingOpt]", expected,
                () -> LC1094CarPooling.carPoolingOpt(trips, capacity));
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
