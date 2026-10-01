package Leetcode.Daily;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

public class LC3498ReverseDegreeString {
    public static void main(String[] args) {
        String s = "abc";
        System.out.println(reverseDegree(s));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }
    public static int reverseDegree(String s) {
        char[] chars = s.toCharArray();
        int score = 0;
        for (int i = 0; i < chars.length; i++) {
            score += ((i + 1) * ('z' - chars[i] + 1));
        }
        return score;
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 3498. Reverse Degree of a String)
    // Problem: https://leetcode.com/problems/reverse-degree-of-a-string/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the 2 official examples + edge cases (a single 'a' and a single 'z', the whole
    // alphabet, 1000 letters 'a' = the largest possible score). Expected values come from the
    // statement or from a brute force (sum of (position in string) * (26 - letter index)).
    //   Expected result: 6/6 passed
    // ==========================================================================
    private static void runTests() {
        // official examples
        check("example 1: abc", 148, () -> LC3498ReverseDegreeString.reverseDegree("abc"));
        check("example 2: zaza", 160, () -> LC3498ReverseDegreeString.reverseDegree("zaza"));
        // edge: shortest strings with the largest and the smallest letter value
        check("single 'a' = 26", 26, () -> LC3498ReverseDegreeString.reverseDegree("a"));
        check("single 'z' = 1", 1, () -> LC3498ReverseDegreeString.reverseDegree("z"));
        // edge: whole alphabet: sum of i * (27 - i) for i = 1..26
        check("whole alphabet", 3276,
                () -> LC3498ReverseDegreeString.reverseDegree("abcdefghijklmnopqrstuvwxyz"));
        // edge: 1000 letters 'a' (maximum length): 26 * (1 + 2 + ... + 1000) = 26 * 500500
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 1000; i++) sb.append('a');
        String thousandAs = sb.toString();
        check("1000 times 'a'", 13013000,
                () -> LC3498ReverseDegreeString.reverseDegree(thousandAs));
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

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
