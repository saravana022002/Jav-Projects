package Leetcode.Contest191;

import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

public class LC191CountSpecialIntegers2 {
    public static void main(String[] args) {
        int[] nums = {1,8,1,5,1,5,8,5};
        System.out.println(countSpecialIntegers(nums));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }

    public static int countSpecialIntegers(int[] nums) {
        Map<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.putIfAbsent(nums[i], new int[3]);
            if(map.get(nums[i])[0] == -1){
                continue;
            }
            map.get(nums[i])[0]++;
            if(map.get(nums[i])[0] >= 3 && map.get(nums[i])[1] != i - map.get(nums[i])[2]) {
                map.get(nums[i])[0] = -1;
                continue;
            }
            map.get(nums[i])[1] =  i - map.get(nums[i])[2];
            map.get(nums[i])[2] =  i;
        }

        int count = 0;

        for (Map.Entry<Integer, int[]> entry : map.entrySet()){
            if(entry.getValue()[0] >= 3){
                count++;
            }
        }
        return count;
    }

    // ================================ TESTS =================================
    // Tests for this class 
    // (LC 4049. Count Values With Equally Spaced Occurrences II)
    // Problem: https://leetcode.com/problems/count-values-with-equally-spaced-occurrences-ii/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the 3 official examples + edge cases (shortest input, a later occurrence that breaks
    // the spacing, the upper bound 10^9 for a value, a 100000-element run with one break).
    // Expected values come from the statement or from a brute force (collect the indices of every
    // value, then test "at least 3 and all gaps equal").
    //   Expected result: 7/7 passed
    // ==========================================================================
    private static void runTests() {
        // official examples
        check("example 1", 2, () -> run(1, 8, 1, 5, 1, 5, 8, 5));
        check("example 2: four 8s are evenly spaced", 1, () -> run(8, 8, 8, 8));
        check("example 3: 8 at 0,3,4 is not evenly spaced", 0, () -> run(8, 6, 6, 8, 8));
        // edge: shortest input, one value three times in a row
        check("shortest input [7,7,7]", 1, () -> run(7, 7, 7));
        // edge: 2 sits at 0,1,2 (even) but the 4th occurrence at index 4 breaks the spacing
        check("later occurrence breaks spacing [2,2,2,5,2]", 0, () -> run(2, 2, 2, 5, 2));
        // edge: largest allowed value 10^9 at indices 0,2,4; the 5 appears only twice
        check("value 10^9", 1, () -> run(1000000000, 5, 1000000000, 5, 1000000000));
        // edge: maximum length 100000, all 7 except one 8 in the middle: the 7s have gaps
        // 1,1,...,2,1,... so they are not special, and the single 8 is not special either
        int[] longRun = new int[100000];
        Arrays.fill(longRun, 7);
        longRun[50000] = 8;
        check("100000 elements, one break in the middle", 0, () -> run(longRun));
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

    private static int run(int... nums) {
        return LC191CountSpecialIntegers2.countSpecialIntegers(nums);
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
