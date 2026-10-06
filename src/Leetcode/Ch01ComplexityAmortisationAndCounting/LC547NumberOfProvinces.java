package Leetcode.Ch01ComplexityAmortisationAndCounting;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 547. Number of Provinces   [Medium]
 * https://leetcode.com/problems/number-of-provinces/
 * Topics: Depth-First Search, Breadth-First Search, Union-Find, Graph Theory
 * Book: Algorithmic Thinking - ch. 1 Complexity, amortisation, and counting, Practice #11
 *       also: ch. 42 Graph representation and DFS (#6),
 *             ch. 47 Union-find and dynamic connectivity (#1),
 *             ch. 53 Bridges, articulation points, SCC (#2)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   There are n cities. isConnected[i][j] is 1 when cities i and j are linked directly and 0
 *   otherwise. Links chain together: if a is linked to b and b is linked to c, then a is
 *   connected to c indirectly. A province is a group of cities that are all connected,
 *   directly or indirectly, with no city outside the group connected to any of them.
 *   Return the number of provinces.
 *
 * EXAMPLES
 *   isConnected = [[1,1,0],[1,1,0],[0,0,1]]  ->  2     provinces {0,1} and {2}
 *   isConnected = [[1,0,0],[0,1,0],[0,0,1]]  ->  3     every city is a province of its own
 *
 * CONSTRAINTS
 *   1 <= n <= 200,   n == isConnected.length,   n == isConnected[i].length,
 *   isConnected[i][j] is 1 or 0,   isConnected[i][i] == 1,
 *   isConnected[i][j] == isConnected[j][i]
 * </pre>
 */
public class LC547NumberOfProvinces {

    /**
     * Variant A
     * <br>Target: time O(n^2), space O(n)
     * <br>Complete first: ch. 42 Graph representation and DFS (later chapter;
     *       needs ch. 15, 34, 35 first)
     */
    public static int findCircleNumA(int[][] isConnected) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n^2), space O(n)
     * <br>Complete first: ch. 43 BFS and unweighted shortest paths (later chapter;
     *       needs ch. 15, 34, 35, 42 first)
     */
    public static int findCircleNumB(int[][] isConnected) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n^2 * alpha(n)), space O(n)
     * <br>Complete first: ch. 1 Complexity, amortisation, and counting (this chapter)
     *     + ch. 47 Union-find and dynamic connectivity (later chapter)
     */
    public static int findCircleNumC(int[][] isConnected) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC547NumberOfProvinces::findCircleNumA);
        runAll("B", LC547NumberOfProvinces::findCircleNumB);
        runAll("C", LC547NumberOfProvinces::findCircleNumC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 547. Number of Provinces)
    // https://leetcode.com/problems/number-of-provinces/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the two official
    // examples plus edge cases. The edge-case matrices are built from a city count and a list
    // of direct links (with the diagonal set to 1 and the matrix kept symmetric); their
    // expected counts are worked out from those links.
    //   Expected result: 0/15 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        int solve(int[][] isConnected);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", 2, () -> s.solve(new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}}));
        check(v + " example 2", 3, () -> s.solve(new int[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}}));
        // edge: a single city
        check(v + " single city", 1, () -> s.solve(graph(1)));
        // edge: three cities all linked to each other (redundant links)
        check(v + " all linked", 1, () -> s.solve(graph(3, new int[]{0, 1}, new int[]{0, 2},
                new int[]{1, 2})));
        // edge: a chain listed back to front (0-3 only connected indirectly), a pair, a loner
        check(v + " chain, pair, loner", 3, () -> s.solve(graph(7, new int[]{2, 3},
                new int[]{1, 2}, new int[]{0, 1}, new int[]{4, 5})));
    }

    // n x n matrix: 1 on the diagonal, and 1 in both [a][b] and [b][a] for every link {a, b}
    private static int[][] graph(int n, int[]... links) {
        int[][] m = new int[n][n];
        for (int i = 0; i < n; i++) m[i][i] = 1;
        for (int[] link : links) {
            m[link[0]][link[1]] = 1;
            m[link[1]][link[0]] = 1;
        }
        return m;
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
        if (o instanceof Object[]) return Arrays.deepToString((Object[]) o);
        return String.valueOf(o);
    }

    // ============================== END TESTS ===============================
}
