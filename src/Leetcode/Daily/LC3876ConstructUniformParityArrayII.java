package Leetcode.Daily;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

public class LC3876ConstructUniformParityArrayII {
    public static void main(String[] args) {
        int[] nums1 = {1,4,7};
        System.out.println(uniformArray(nums1));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }
    public static boolean uniformArray(int[] nums1) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums1.length; i++) {
            if(nums1[i] <= min){
                min = nums1[i];
            }
        }

        boolean isEven = min % 2 == 0;

        for (int i = 0; i < nums1.length; i++) {
            if(isEven){
                if((nums1[i] % 2) == 0){

                }else if((nums1[i] - min) >= 1 && (nums1[i] - min) % 2 == 0 ){

                }else {
                    return false;
                }
            }else {
                if((nums1[i] % 2) == 1){

                }else if((nums1[i] - min) >= 1 && (nums1[i] - min) % 2 == 1){

                }else {
                    return false;
                }
            }
        }
        return true;
    }

    // ================================ TESTS =================================
    // Tests for this class 
    // (LC 3876. Construct Uniform Parity Array II)
    // Problem: https://leetcode.com/problems/construct-uniform-parity-array-ii/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the 3 official examples + edge cases (one element, an unsorted array whose smallest
    // value is even and which also has odd values, values near 10^9, a 100000-element array).
    // Expected values come from the statement or from a brute force that follows the statement
    // literally (for each index list the values nums1[i] and nums1[i] - nums1[j] >= 1, and ask
    // whether all indices can reach the same parity).
    //   Expected result: 7/7 passed
    // ==========================================================================
    private static void runTests() {
        // official examples
        check("example 1: [1,4,7] -> all odd", true, () -> run(1, 4, 7));
        check("example 2: [2,3] -> false", false, () -> run(2, 3));
        check("example 3: [4,6] -> all even", true, () -> run(4, 6));
        // edge: a single element is always uniform
        check("single element [7]", true, () -> run(7));
        // edge: smallest value 6 is even and odd values exist -> nothing can fix them
        check("unsorted [11,6,9] -> false", false, () -> run(11, 6, 9));
        // edge: values near 10^9, 10^9 - 999999999 = 1 is odd -> [1, 999999999]
        check("[10^9, 999999999] -> true", true, () -> run(1000000000, 999999999));
        // edge: maximum length 100000, 1..100000: smallest value 1 is odd, evens become odd
        int[] longArray = new int[100000];
        for (int i = 0; i < longArray.length; i++) longArray[i] = i + 1;
        check("1..100000 -> true", true, () -> run(longArray));
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

    private static boolean run(int... nums1) {
        return LC3876ConstructUniformParityArrayII.uniformArray(nums1);
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
