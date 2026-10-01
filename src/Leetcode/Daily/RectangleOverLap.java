package Leetcode.Daily;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

public class RectangleOverLap {
    public static void main(String[] args) {
        int[] rec1 = {0,0,1,1};
        int[] rec2 = {1,0,2,1};
        System.out.println(isRectangleOverlap(rec1, rec2));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }

    public static boolean isRectangleOverlap(int[] rec1, int[] rec2) {
        // if R rectangle are completely right
        if(rec1[2] <= rec2[0]){
            return false;

        }
        // if R rectangle are completely Up
        if(rec1[3] <= rec2[1]){
            return false;
        }
        // if R rectangle are completely left
        if(rec1[0] >= rec2[2]){
            return false;
        }
        // if R rectangle are completely down
        if(rec1[1] >= rec2[3]){
            return false;
        }
        return true;
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 836. Rectangle Overlap)
    // Problem: https://leetcode.com/problems/rectangle-overlap/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the 3 official examples + edge cases that reach the other two "no overlap" checks
    // (rec1 to the right of / above rec2, only touching), a cross shape (no corner of one inside
    // the other) and a containment with coordinates of +-10^9. Expected values come from the
    // statement or from a brute force (the intersection width and height must both be positive).
    //   Expected result: 7/7 passed
    // ==========================================================================
    private static void runTests() {
        // official examples
        check("example 1: partial overlap", true,
                () -> run(new int[]{0, 0, 2, 2}, new int[]{1, 1, 3, 3}));
        check("example 2: sharing only an edge", false,
                () -> run(new int[]{0, 0, 1, 1}, new int[]{1, 0, 2, 1}));
        check("example 3: far apart", false,
                () -> run(new int[]{0, 0, 1, 1}, new int[]{2, 2, 3, 3}));
        // edge: example 2 with the arguments swapped -> rec1 is to the right of rec2, touching
        check("touching, rec1 right of rec2", false,
                () -> run(new int[]{1, 0, 2, 1}, new int[]{0, 0, 1, 1}));
        // edge: rec1 directly above rec2, sharing the edge y = 1
        check("touching, rec1 above rec2", false,
                () -> run(new int[]{0, 1, 1, 2}, new int[]{0, 0, 1, 1}));
        // edge: a wide and a tall rectangle cross each other; no corner lies inside the other
        check("cross shape", true, () -> run(new int[]{0, 1, 3, 2}, new int[]{1, 0, 2, 3}));
        // edge: rec2 lies completely inside rec1, coordinates at the +-10^9 limits
        check("containment at +-10^9", true,
                () -> run(new int[]{-1000000000, -1000000000, 1000000000, 1000000000},
                        new int[]{-1, -1, 1, 1}));
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

    private static boolean run(int[] rec1, int[] rec2) {
        return RectangleOverLap.isRectangleOverlap(rec1, rec2);
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
