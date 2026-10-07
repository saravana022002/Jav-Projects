package Leetcode.Ch06SortingAsAPreprocessingStep;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 881. Boats to Save People   [Medium]
 * https://leetcode.com/problems/boats-to-save-people/
 * Topics: Array, Two Pointers, Greedy, Sorting
 * Book: Algorithmic Thinking - ch. 6 Sorting as a preprocessing step, Practice #8
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   people[i] is the weight of the i-th person. There are unlimited boats, each able to carry
 *   a total weight of at most limit and at most two people at once. Return the smallest
 *   number of boats needed to carry everybody.
 *
 * EXAMPLES
 *   people = [1,2],      limit = 3  ->  1     one boat: (1, 2)
 *   people = [3,2,2,1],  limit = 3  ->  3     boats: (1, 2), (2), (3)
 *   people = [3,5,3,4],  limit = 5  ->  4     boats: (3), (3), (4), (5)
 *
 * CONSTRAINTS
 *   1 <= people.length <= 5 * 10^4,   1 <= people[i] <= limit <= 3 * 10^4
 * </pre>
 */
public class LC881BoatsToSavePeople {

    /**
     * Variant A
     * <br>Target: time O(n^2), space O(n)
     * <br>Complete first: ch. 6 Sorting as a preprocessing step (this chapter)
     */
    public static int numRescueBoatsA(int[] people, int limit) {
        // TODO: write your solution
        boolean[] took = new boolean[people.length];
        int max;
        int needIdx = -1;
        int boat = 0;
        for (int i = 0; i < people.length; i++) {
            if(took[i]) continue;
            max = Integer.MIN_VALUE;
            needIdx = -1;
            for (int j = 0; j < people.length; j++) {
                if(took[j] || i == j) continue;
                int needs = limit - people[i] ;
                if (needs >= people[j] && max < people[j]) {
                    max = people[j];
                    needIdx = j;
                }
            }
            took[i] = true;
            if(needIdx >= 0) {
                took[needIdx] = true;
            }
            boat++;
        }
        return boat;
    }

    /**
     * Variant B
     * <br>Target: time O(n + limit), space O(limit)
     * <br>Complete first: ch. 7 Non-comparison sorts: counting, bucket, radix (later chapter)
     *     + ch. 8 Two pointers, converging (later chapter)
     */
    public static int numRescueBoatsB(int[] people, int limit) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n log n), space O(1)
     * <br>Complete first: ch. 6 Sorting as a preprocessing step (this chapter)
     *     + ch. 8 Two pointers, converging (later chapter)
     */
    public static int numRescueBoatsC(int[] people, int limit) {
        // TODO: write your solution
        Arrays.sort(people);
        int i = 0;
        int j = people.length - 1;
        int boats = 0;
        while (i <= j){
            if((people[i] + people[j]) <= limit) i++;
            j--;
            boats++;
        }
        return boats;
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        numRescueBoatsC(new int[]{5}, 5);
        runAll("A", LC881BoatsToSavePeople::numRescueBoatsA);
        runAll("B", LC881BoatsToSavePeople::numRescueBoatsB);
        runAll("C", LC881BoatsToSavePeople::numRescueBoatsC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 881. Boats to Save People)
    // https://leetcode.com/problems/boats-to-save-people/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases whose answers were found by an exhaustive search over all
    // ways to group the people. Every call gets a fresh array, so sorting in place is fine.
    //   Expected result: 0/18 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int[] people, int limit);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 1, () -> s.solve(new int[]{1, 2}, 3));
        check(v + " example 2", 3, () -> s.solve(new int[]{3, 2, 2, 1}, 3));
        check(v + " example 3", 4, () -> s.solve(new int[]{3, 5, 3, 4}, 5));
        // edge: a single person
        check(v + " single person", 1, () -> s.solve(new int[]{5}, 5));
        // edge: everybody weighs exactly the limit, nobody can share
        check(v + " nobody can share", 3, () -> s.solve(new int[]{5, 5, 5}, 5));
        // edge: three perfect pairs, but only if each heavy person gets a light partner
        check(v + " perfect pairs", 3, () -> s.solve(new int[]{3, 3, 4, 5, 1, 2}, 6));
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
        if (o instanceof Object[]) return Arrays.deepToString((Object[]) o);
        return String.valueOf(o);
    }

    // ============================== END TESTS ===============================
}
