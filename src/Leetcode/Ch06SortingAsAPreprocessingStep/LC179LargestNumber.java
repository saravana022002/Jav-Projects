package Leetcode.Ch06SortingAsAPreprocessingStep;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 179. Largest Number   [Medium]
 * https://leetcode.com/problems/largest-number/
 * Topics: Array, String, Greedy, Sorting
 * Book: Algorithmic Thinking - ch. 6 Sorting as a preprocessing step, Practice #11
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   nums holds non-negative integers. Write them one after another, in an order of your
 *   choice, so that the digits form the largest possible number, and return that number as
 *   a string (it can be far too big for an int). The result has no leading zeros, so an
 *   input made only of zeros gives "0".
 *
 * EXAMPLES
 *   nums = [10,2]         ->  "210"
 *   nums = [3,30,34,5,9]  ->  "9534330"
 *
 * CONSTRAINTS
 *   1 <= nums.length <= 100,   0 <= nums[i] <= 10^9
 * </pre>
 */
public class LC179LargestNumber {

    /**
     * Variant A
     * <br>Target: time O(n! * n), space O(n)
     * <br>Complete first: ch. 57 Backtracking (later chapter; needs ch. 56 first)
     */
    public static String largestNumberA(int[] nums) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }


    /**
     * Variant C
     * <br>Target: time O(n^2), space O(n)
     * <br>Complete first: ch. 57 Backtracking (later chapter; needs ch. 56 first)
     */
    public static String largestNumberC(int[] nums) {
        // TODO: write your solution
        String[] str = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            str[i] = ""+nums[i];
        }
        boolean changed = true;
        while (changed) {
            changed = false;
            String temp1;
            String temp2;
            String temp;
            for (int i = 1; i < nums.length; i++) {
                temp1 = str[i - 1] + str[i];
                temp2 = str[i] + str[i - 1];
                if(temp2.compareTo(temp1) > 0){
                    temp = str[i - 1];
                    str[i - 1] = str[i];
                    str[i] = temp;
                    changed = true;
                }
            }
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (String s : str) {
            stringBuilder.append(s);
        }
        String ans = stringBuilder.toString();
        if(ans.charAt(0) == '0') return "0";
        return ans;
    }

    /**
     * Variant B
     * <br>Target: time O(n log n), space O(n)
     * <br>Complete first: ch. 6 Sorting as a preprocessing step (this chapter)
     */
    public static String largestNumberB(int[] nums) {
        // TODO: write your solution
        String[] str = new String[nums.length];
        for (int i = 0; i < nums.length; i++) str[i] = ""+nums[i];
        Arrays.sort(str, (a, b) -> (b + a).compareTo(a + b));
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < nums.length; i++) stringBuilder.append(str[i]);
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods

        largestNumberB(new int[]{3, 30, 34, 5, 9});
//        runAll("A", LC179LargestNumber::largestNumberA);
//        runAll("B", LC179LargestNumber::largestNumberB);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 179. Largest Number)
    // https://leetcode.com/problems/largest-number/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B) is checked separately with the same cases: the two official examples
    // plus edge cases checked against a try-every-ordering script. All inputs have at most
    // five numbers, so the factorial brute force of variant A finishes instantly.
    //   Expected result: 0/10 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        String solve(int[] nums);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", "210", () -> s.solve(new int[]{10, 2}));
        check(v + " example 2", "9534330", () -> s.solve(new int[]{3, 30, 34, 5, 9}));
        // edge: only zeros give "0", not "000"
        check(v + " all zeros", "0", () -> s.solve(new int[]{0, 0, 0}));
        // edge: a single number, the largest allowed value
        check(v + " single number", "1000000000", () -> s.solve(new int[]{1000000000}));
        // edge: numbers that are prefixes of one another, so the order depends on later digits
        check(v + " prefix trap", "34343343234323",
                () -> s.solve(new int[]{34323, 3432, 343, 34}));
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
