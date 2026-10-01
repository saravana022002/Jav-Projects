package Leetcode.Contest191;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

public class LC191CountSpecialIntegers1 {
    public static void main(String[] args) {
        int[] nums = {1,8,1,5,1,5,8,5};
        System.out.println(countSpecialIntegers(nums));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }

    public static int countSpecialIntegers(int[] nums) {
        int[][] can = new int[101][3];
        for (int i = 0; i < nums.length; i++) {
            if(can[nums[i]][0] == -1){
                continue;
            }
            can[nums[i]][0]++;
            if(can[nums[i]][0] == 3 && can[nums[i]][1] != i - can[nums[i]][2]) {
                can[nums[i]][0] = -1;
                continue;
            }
            can[nums[i]][1] =  i - can[nums[i]][2];
            can[nums[i]][2] =  i;
        }

        int count = 0;
        for (int i = 1; i < 101; i++) {
            if(can[i][0] == 3){
                count++;
            }
        }
        return count;
    }

    // ================================ TESTS =================================
    // Tests for this class 
    // (LC 4048. Count Values With Equally Spaced Occurrences I)
    // Problem: https://leetcode.com/problems/count-values-with-equally-spaced-occurrences-i/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the 3 official examples + edge cases (shortest input, largest value 100, a value with
    // four occurrences, a longer input). Expected values come from the statement or from a brute
    // force (collect the indices of every value, then test "exactly 3 and equal gaps").
    //   Expected result: 7/7 passed
    // ==========================================================================
    private static void runTests() {
        // official examples
        check("example 1", 2, () -> run(1, 8, 1, 5, 1, 5, 8, 5));
        check("example 2: four 8s -> not exactly three", 0, () -> run(8, 8, 8, 8));
        check("example 3: 8 at 0,3,4 is not evenly spaced", 0, () -> run(8, 6, 6, 8, 8));
        // edge: shortest input, one value three times in a row
        check("shortest input [7,7,7]", 1, () -> run(7, 7, 7));
        // edge: largest allowed value (100)
        check("largest value [100,100,100]", 1, () -> run(100, 100, 100));
        // edge: four occurrences - the first three are evenly spaced but 2 appears 4 times
        check("four occurrences [2,2,2,5,2]", 0, () -> run(2, 2, 2, 5, 2));
        // edge: length 99, values 1..33 repeated three times -> every value has gaps 33, 33
        int[] threeBlocks = new int[99];
        for (int i = 0; i < threeBlocks.length; i++) threeBlocks[i] = i % 33 + 1;
        check("1..33 three times in a row (length 99)", 33, () -> run(threeBlocks));
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

    private static int run(int... nums) {
        return LC191CountSpecialIntegers1.countSpecialIntegers(nums);
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
