package Leetcode.Daily;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

public class LC3550SmallestIndex {
    public static void main(String[] args) {
        int[] nums = {1,3,2};
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }

    public int smallestIndex(int[] nums) {
        int temp = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count = 0;
            while(nums[i] > 0){
                temp = nums[i] % 10;
                nums[i] = nums[i] / 10;
                count += temp;
            }
            if(count == i){
                return i;
            }
        }
        return -1;
    }

    // ================================ TESTS =================================
    // Tests for this class 
    // (LC 3550. Smallest Index With Digit Sum Equal to Index)
    // Problem: https://leetcode.com/problems/smallest-index-with-digit-sum-equal-to-index/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the 3 official examples + edge cases (a 0 at index 0, a single element, the largest
    // value 1000, a large digit sum). Expected values come from the statement or from a brute force
    // (digit sum through String.valueOf for every index). smallestIndex is an instance method and
    // it consumes (zeroes) the numbers of the array it is given, so every case passes a fresh array.
    //   Expected result: 7/7 passed
    // ==========================================================================
    private static void runTests() {
        // official examples
        check("example 1: nums[2] = 2", 2, () -> run(1, 3, 2));
        check("example 2: nums[1] = 10", 1, () -> run(1, 10, 11));
        check("example 3: no index matches", -1, () -> run(1, 2, 3));
        // edge: nums[0] = 0 has digit sum 0 = index 0
        check("[0,5,5] -> index 0", 0, () -> run(0, 5, 5));
        // edge: a single element can only match when it is 0
        check("single element [5]", -1, () -> run(5));
        // edge: largest value 1000 has digit sum 1 = index 1
        check("[5,1000] -> index 1", 1, () -> run(5, 1000));
        // edge: 28 times 999 (digit sum 27): only index 27 matches
        check("28 times 999 -> index 27", 27, () -> run(filled(999, 28)));
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

    private static int run(int... nums) {
        return new LC3550SmallestIndex().smallestIndex(nums);
    }

    private static int[] filled(int value, int length) {
        int[] a = new int[length];
        Arrays.fill(a, value);
        return a;
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
