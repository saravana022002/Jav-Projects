package Leetcode.Daily;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

public class LC3718missingMultiple {
    public static void main(String[] args) {
        int[] nums = {1,4,7,10,15};
        int k = 5;
        System.out.println(missingMultiple(nums, k));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }

    public static int missingMultiple(int[] nums, int k) {
        int[] space = new int[101];
        Arrays.fill(space, -1);
        for (int num : nums) {
            if (num % k == 0) {
                space[num / k] = 1;
            }
        }
        int lastIdx = -1;
        for (int i = 1; i < space.length; i++) {
           if(space[i] == 1){
               lastIdx = i;
           }else {
               return (i) * k;
           }
        }
        return (lastIdx + 1) * k;
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 3718. Smallest Missing Multiple of K)
    // Problem: https://leetcode.com/problems/smallest-missing-multiple-of-k/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the 2 official examples + edge cases (k = 1 with 1..100 all present, the largest k,
    // duplicates, unsorted input). Expected values come from the statement or from a brute force
    // (put nums in a set, walk k, 2k, 3k, ... until one is missing).
    //   Expected result: 6/6 passed
    // ==========================================================================
    private static void runTests() {
        // official examples
        check("example 1: multiples of 2 up to 8 are present", 10,
                () -> run(new int[]{8, 2, 3, 4, 6}, 2));
        check("example 2: 5 itself is missing", 5, () -> run(new int[]{1, 4, 7, 10, 15}, 5));
        // edge: k = 1 and 1..100 all present -> the answer 101 is beyond every value in nums
        int[] oneToHundred = new int[100];
        for (int i = 0; i < oneToHundred.length; i++) oneToHundred[i] = i + 1;
        check("k = 1, nums = 1..100", 101, () -> run(oneToHundred, 1));
        // edge: largest k, and 100 is present -> next multiple is 200
        check("k = 100, nums = [100]", 200, () -> run(new int[]{100}, 100));
        // edge: duplicates must not count twice
        check("duplicates [2,2,2], k = 2", 4, () -> run(new int[]{2, 2, 2}, 2));
        // edge: unsorted input, multiples of 3 present up to 12
        check("unsorted [6,3,12,9], k = 3", 15, () -> run(new int[]{6, 3, 12, 9}, 3));
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

    private static int run(int[] nums, int k) {
        return LC3718missingMultiple.missingMultiple(nums, k);
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
