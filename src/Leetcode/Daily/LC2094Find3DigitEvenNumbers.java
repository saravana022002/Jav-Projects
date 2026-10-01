package Leetcode.Daily;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

public class LC2094Find3DigitEvenNumbers {
    public static void main(String[] args) {
        int[] digits = {6, 6, 6};
        System.out.println(Arrays.toString(findEvenNumbers(digits)));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }
    public static int[] findEvenNumbers(int[] digits) {
        int[] map = new int[10];
        List<Integer> resultList = new ArrayList<>();
        for (int digit : digits) {
            map[digit]++;
        }
        for (int i = 100; i < 1000; i = i + 2) {
            if(isDigitFormable(i , map)){
                resultList.add(i);
            }
        }
        int[] result = new int[resultList.size()];
        for (int i = 0; i < resultList.size(); i++) {
            result[i] = resultList.get(i);
        }
        return result;
    }


    private static boolean isDigitFormable(int k, int[] map) {
        int third = k%10;
        int second = (k/10) % 10;
        int first = ((k/10) / 10) % 10;
        int[] required = new int[10];
        required[first]++;
        required[second]++;
        required[third]++;
        return required[first] <= map[first] && required[second] <= map[second] && required[third] <= map[third];
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 2094. Finding 3-Digit Even Numbers)
    // Problem: https://leetcode.com/problems/finding-3-digit-even-numbers/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the 3 official examples + edge cases (zeros only, a zero that must not lead, a zero
    // that must come last, the maximum length 100). Expected values come from the statement or from
    // a brute force (try every ordered triple of distinct positions, keep the valid numbers in a
    // sorted set). The result must be sorted ascending, so arrays are compared as they are.
    //   Expected result: 7/7 passed
    // ==========================================================================
    private static void runTests() {
        // official examples
        check("example 1", new int[]{102, 120, 130, 132, 210, 230, 302, 310, 312, 320},
                () -> run(2, 1, 3, 0));
        check("example 2: duplicates used as often as they appear",
                new int[]{222, 228, 282, 288, 822, 828, 882}, () -> run(2, 2, 8, 8, 2));
        check("example 3: no even digit", new int[]{}, () -> run(3, 7, 5));
        // edge: only zeros -> every arrangement has a leading zero
        check("[0,0,0]", new int[]{}, () -> run(0, 0, 0));
        // edge: 002 and 020 have leading zeros, only 200 is valid
        check("[0,0,2]", new int[]{200}, () -> run(0, 0, 2));
        // edge: the only even digit is 0, so it must be last
        check("[1,5,0]", new int[]{150, 510}, () -> run(1, 5, 0));
        // edge: maximum length 100: ninety-nine 9s and one 4 -> only 994
        int[] hundred = new int[100];
        Arrays.fill(hundred, 9);
        hundred[57] = 4;
        check("99 nines and one 4", new int[]{994}, () -> run(hundred));
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

    private static int[] run(int... digits) {
        return LC2094Find3DigitEvenNumbers.findEvenNumbers(digits);
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
