package Leetcode.Ch15StacksAndExpressionStructure;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 71. Simplify Path   [Medium]
 * https://leetcode.com/problems/simplify-path/
 * Topics: String, Stack
 * Book: Algorithmic Thinking - ch. 15 Stacks and expression structure, Practice #6
 *
 * STATUS
 *   Not solved yet. Implement the method below (Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   path is an absolute Unix-style path, so it starts with '/'. Rewrite it in canonical form:
 *   "." means the current directory and ".." the parent directory (the root has no parent, so
 *   ".." at the root changes nothing), a run of slashes counts as one slash, and every other
 *   dot sequence such as "..." is an ordinary name. The result starts with exactly one '/',
 *   joins the names with single slashes and does not end with '/' unless it is just "/".
 *
 * EXAMPLES
 *   path = "/home/"                            ->  "/home"
 *   path = "/home//foo/"                       ->  "/home/foo"
 *   path = "/home/user/Documents/../Pictures"  ->  "/home/user/Pictures"
 *   path = "/../"                              ->  "/"       the root has no parent
 *   path = "/.../a/../b/c/../d/./"             ->  "/.../b/d"    "..." is just a name
 *
 * CONSTRAINTS
 *   1 <= path.length <= 3000,   path consists of English letters, digits, '.', '/' and '_',
 *   path is a valid absolute Unix path
 * </pre>
 */
public class LC71SimplifyPath {

    /**
     * Variant A
     * <br>Target: time O(n), space O(n)
     * <br>Complete first: ch. 15 Stacks and expression structure (this chapter)
     */
    public static String simplifyPathA(String path) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC71SimplifyPath::simplifyPathA);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 71. Simplify Path)
    // https://leetcode.com/problems/simplify-path/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // The only variant (A) is checked with the five official examples plus edge cases
    // confirmed with an independent reference (Python's posixpath.normpath).
    //   Expected result: 0/8 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        String solve(String path);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", "/home", () -> s.solve("/home/"));
        check(v + " example 2", "/home/foo", () -> s.solve("/home//foo/"));
        check(v + " example 3", "/home/user/Pictures",
                () -> s.solve("/home/user/Documents/../Pictures"));
        check(v + " example 4", "/", () -> s.solve("/../"));
        check(v + " example 5", "/.../b/d", () -> s.solve("/.../a/../b/c/../d/./"));
        // edge: just the root
        check(v + " root only", "/", () -> s.solve("/"));
        // edge: ".." steps back over two names, "." changes nothing
        check(v + " back twice", "/c", () -> s.solve("/a/./b/../../c/"));
        // edge: names that merely contain dots are ordinary names
        check(v + " dotted names", "/..a/b..", () -> s.solve("/..a/b../.../.."));
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
