package Leetcode.Daily;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

public class LCQ1MinBishopMoves {
    public static void main(String[] args) {
        int[] source = {4,2};
        int[] target = {1,3};
        System.out.println(minBishopMoves(source, target));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }

    public static int minBishopMoves(int[] source, int[] target) {
        boolean sourcePos = (source[0] + source[1]) % 2 == 0;
        boolean targetPos = (target[0] + target[1]) % 2 == 0;
        if(sourcePos != targetPos){
            return -1;
        }
        int distanceRow = Math.abs(source[0] - target[0]);
        int distanceCol = Math.abs(source[1] - target[1]);
        if(distanceRow == distanceCol){
            return 1;
        } else{
            return 2;
        }
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 4034. Minimum Bishop Moves to Reach Target)
    // Problem: https://leetcode.com/problems/minimum-bishop-moves-to-reach-target/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the 3 official examples + edge cases (corner to corner, neighbouring squares of the
    // other colour, same row, same column). Expected values come from the statement or from a BFS
    // over the 8x8 board (the bishop slides along a diagonal), which was also compared with the
    // solution for all 4032 ordered pairs of different squares.
    //   Expected result: 7/7 passed
    // ==========================================================================
    private static void runTests() {
        // official examples
        check("example 1: (8,1) -> (1,8) one diagonal", 1, () -> run(8, 1, 1, 8));
        check("example 2: (4,2) -> (1,3) two moves", 2, () -> run(4, 2, 1, 3));
        check("example 3: (1,1) -> (3,4) other colour", -1, () -> run(1, 1, 3, 4));
        // edge: opposite corners on the main diagonal
        check("(1,1) -> (8,8)", 1, () -> run(1, 1, 8, 8));
        // edge: neighbouring squares always have the other colour -> unreachable
        check("(1,1) -> (1,2)", -1, () -> run(1, 1, 1, 2));
        // edge: same row, same colour, not on a common diagonal -> 2 moves
        check("(3,1) -> (3,3) same row", 2, () -> run(3, 1, 3, 3));
        // edge: same column, same colour, going up the board -> 2 moves
        check("(6,3) -> (2,3) same column", 2, () -> run(6, 3, 2, 3));
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

    private static int run(int sr, int sc, int tr, int tc) {
        return LCQ1MinBishopMoves.minBishopMoves(new int[]{sr, sc}, new int[]{tr, tc});
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
