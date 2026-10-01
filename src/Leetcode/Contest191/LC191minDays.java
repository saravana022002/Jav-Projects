package Leetcode.Contest191;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

public class LC191minDays {
    public static void main(String[] args) {
        int n = 2;
        System.out.println(minDays(n));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }




    public static int minDays(int n) {

        if (n == 0) {
            return 0;
        }

        int ans = Integer.MAX_VALUE;

        for (int k = 1; k * (k + 1) / 2 <= n; k++) {

            int accumulatedCount = k * (k + 1) / 2;

            int days = k + 1 + minDays(n - accumulatedCount);

            ans = Math.min(ans, days);
        }

        return ans - 1;
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 4050. Minimum Days to Score Exactly N Points)
    // Problem: https://leetcode.com/problems/minimum-days-to-score-exactly-n-points/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the 3 official examples + edge cases (n = 1, 3, 5, 36). Expected values come from the
    // statement or from two independent references (a day-by-day BFS over (score, streak) states
    // and a DP over triangular numbers) that agree for every n from 1 to 48.
    //   Expected result: 3/7 passed
    //   The 4 failures are a real bug in minDays: it returns "ans - 1" at EVERY recursion level, so
    //   the skip days between streaks are never counted (it returns the number of earning days
    //   only): n = 2 gives 2, expected 3; n = 9 gives 5, expected 6; n = 12 gives 6, expected 7;
    //   n = 5 gives 4, expected 6. It is right only when n is a triangular number (a single streak:
    //   1, 3, 6, 10, ...).
    //   Large n (up to 10^5) is left out on purpose: the plain recursion is exponential (n = 49
    //   already takes seconds), so such a case would hang the test instead of failing it.
    // ==========================================================================
    private static void runTests() {
        // official examples
        check("example 1: n = 2 (earn, skip, earn)", 3, () -> LC191minDays.minDays(2));
        check("example 2: n = 9 (1+2+3, skip, 1+2)", 6, () -> LC191minDays.minDays(9));
        check("example 3: n = 12 (1+2+3, skip, 1+2+3)", 7, () -> LC191minDays.minDays(12));
        // edge: smallest n, one earning day
        check("n = 1", 1, () -> LC191minDays.minDays(1));
        // edge: n = 3 = 1 + 2 is one streak of 2 days, no skip needed
        check("n = 3 (one streak of 2 days)", 2, () -> LC191minDays.minDays(3));
        // edge: n = 5 = 3 + 1 + 1 needs three streaks (2 + 1 + 1 days) and two skip days
        check("n = 5 (three streaks, two skips)", 6, () -> LC191minDays.minDays(5));
        // edge: n = 36 = 1 + 2 + ... + 8, one streak of 8 days
        check("n = 36 (one streak of 8 days)", 8, () -> LC191minDays.minDays(36));
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
