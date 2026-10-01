package Leetcode.Daily;

import java.util.HashMap;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

public class LC3483Unique3DigitsEvenNumbers {
    public static void main(String[] args) {
        int[] digits = {6, 6, 6};
        System.out.println(totalNumbers(digits));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }

    public static int totalNumbers(int[] digits) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int digit : digits) {
            map.put(digit, map.getOrDefault(digit, 0) + 1);
        }
        for (int i = 100; i < 1000; i++) {
            count += isDigitFormable(i , map);
        }
        return count;
    }

    private static int isDigitFormable(int k, HashMap<Integer, Integer> map) {
        int third = k%10;
        int second = (k/10) % 10;
        int first = ((k/10) / 10) % 10;
        int thirdCounter = map.getOrDefault(third, 0);
        int secondCounter = map.getOrDefault(second, 0);
        int firstCounter = map.getOrDefault(first, 0);
        if(third % 2 != 0){
            return 0;
        }

        if(thirdCounter == 0 || secondCounter == 0 || firstCounter == 0){
            return 0;
        }

        if(third == second){
            if(thirdCounter < 2){
                return  0;
            }
        }

        if((second == first)){
            if(secondCounter < 2){
                return  0;
            }
        }

        if((first == third)){
            if(firstCounter < 2){
                return  0;
            }
        }

        if(first == third && second == first){
            if (firstCounter < 3){
                return 0;
            }
        }

        return 1;
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 3483. Unique 3-Digit Even Numbers)
    // Problem: https://leetcode.com/problems/unique-3-digit-even-numbers/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the 4 official examples + edge cases (zeros only, two digits with two copies each,
    // all ten digits, more copies than needed). Expected values come from the statement or from a
    // brute force (try every ordered triple of distinct positions, count the distinct valid numbers).
    //   Expected result: 8/8 passed
    // ==========================================================================
    private static void runTests() {
        // official examples
        check("example 1: [1,2,3,4]", 12, () -> run(1, 2, 3, 4));
        check("example 2: [0,2,2] -> 202, 220", 2, () -> run(0, 2, 2));
        check("example 3: [6,6,6] -> 666", 1, () -> run(6, 6, 6));
        check("example 4: [1,3,5] has no even digit", 0, () -> run(1, 3, 5));
        // edge: only zeros -> every arrangement has a leading zero
        check("[0,0,0]", 0, () -> run(0, 0, 0));
        // edge: two digits with two copies each -> 200, 202, 220
        check("[2,2,0,0]", 3, () -> run(2, 2, 0, 0));
        // edge: all ten digits (maximum length): 72 numbers end in 0, 64 for each of 2,4,6,8
        check("digits 0..9", 328, () -> run(0, 1, 2, 3, 4, 5, 6, 7, 8, 9));
        // edge: more copies than needed -> still only 222
        check("[2,2,2,2]", 1, () -> run(2, 2, 2, 2));
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

    private static int run(int... digits) {
        return LC3483Unique3DigitsEvenNumbers.totalNumbers(digits);
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
