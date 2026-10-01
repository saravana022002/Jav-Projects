package Leetcode.Daily;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

public class LC1658MinimumOperationstoReduceXtoZero {
    public static void main(String[] args) {
        int[] nums = {3,2,20,1,1,3};
        int x = 10;
        System.out.println(minOperations(nums, x));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }

    public static int minOperations(int[] nums, int x) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        int sum = 0;

        for (int num : nums) {
            sum += num;
        }

        int k = sum - x;

        if (k == 0) return nums.length;
        if (k < 0) return -1;

        int max = -1;
        int runningSum = 0;
        for (int i = 0; i < nums.length; i++) {
            runningSum += nums[i];
            Integer index = map.get(runningSum - k);
            if(index != null){
                max = Math.max(max, i - index + 1);
            }
            map.put(runningSum, i + 1);
        }
        return max != -1 ? nums.length - max : -1;
    }

    // ================================ TESTS =================================
    // Tests for this class 
    // (LC 1658. Minimum Operations to Reduce X to Zero)
    // Problem: https://leetcode.com/problems/minimum-operations-to-reduce-x-to-zero/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the 3 official examples + edge cases (one element, x equal to the whole sum, x larger
    // than the sum, a 100000-element array). Expected values come from the statement or from a
    // brute force (try every "i from the left + j from the right" and keep the smallest i + j).
    //   Expected result: 7/7 passed
    // ==========================================================================
    private static void runTests() {
        // official examples
        check("example 1", 2, () -> run(new int[]{1, 1, 4, 2, 3}, 5));
        check("example 2: not reachable", -1, () -> run(new int[]{5, 6, 7, 8, 9}, 4));
        check("example 3", 5, () -> run(new int[]{3, 2, 20, 1, 1, 3}, 10));
        // edge: a single element equal to x
        check("single element [5], x = 5", 1, () -> run(new int[]{5}, 5));
        // edge: x equals the sum of the whole array -> every element must be removed
        check("x equals the total sum", 3, () -> run(new int[]{1, 2, 3}, 6));
        // edge: x larger than the sum of the whole array
        check("x larger than the total sum", -1, () -> run(new int[]{1, 2, 3}, 7));
        // edge: 100000 ones and x = 99999 -> remove all but one element, 99999 operations
        int[] ones = new int[100000];
        Arrays.fill(ones, 1);
        check("100000 ones, x = 99999", 99999, () -> run(ones, 99999));
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

    private static int run(int[] nums, int x) {
        return LC1658MinimumOperationstoReduceXtoZero.minOperations(nums, x);
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
