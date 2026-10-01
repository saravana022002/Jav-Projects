package Leetcode.Ch15StacksAndExpressionStructure;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1047. Remove All Adjacent Duplicates In String   [Easy]
 * https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/
 * Topics: String, Stack
 * Book: Algorithmic Thinking - ch. 15 Stacks and expression structure, Practice #5
 *       also: ch. 1 Complexity, amortisation, and counting (#2)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   s consists of lowercase letters. One duplicate removal picks two neighbouring equal letters
 *   and deletes both of them. Keep removing until no two neighbouring letters are equal, and
 *   return the string that is left. The final string does not depend on which pair you remove
 *   first.
 *
 * EXAMPLES
 *   s = "abbaca"  ->  "ca"    remove "bb", then "aa"
 *   s = "azxxzy"  ->  "ay"    remove "xx", then "zz"
 *
 * CONSTRAINTS
 *   1 <= s.length <= 10^5,   s consists of lowercase English letters
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Leetcode/Ch01ComplexityAmortisationAndCounting/
 *       LC1047RemoveAllAdjacentDuplicatesInString.java
 *   (an earlier attempt - look at it only after you have tried this one yourself)
 * </pre>
 */
public class LC1047RemoveAllAdjacentDuplicatesInString {

    /**
     * Variant A - brute force: find a neighbouring equal pair, delete it, start scanning again
     * <br>Target: time O(n^2), space O(n)
     */
    public static String removeDuplicatesBruteForce(String s) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - this chapter's technique: stack of kept letters, compare each letter to the top
     * <br>Target: time O(n), space O(n)
     */
    public static String removeDuplicates(String s) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC1047RemoveAllAdjacentDuplicatesInString::removeDuplicatesBruteForce);
        runAll("B", LC1047RemoveAllAdjacentDuplicatesInString::removeDuplicates);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class 
    // (LC 1047. Remove All Adjacent Duplicates In String)
    // https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B) is checked separately with the same cases: the two official
    // examples plus edge cases confirmed with an independent brute-force reference.
    //   Expected result: 0/10 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        String solve(String s);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", "ca", () -> s.solve("abbaca"));
        check(v + " example 2", "ay", () -> s.solve("azxxzy"));
        // edge: the whole string disappears at once
        check(v + " all removed", "", () -> s.solve("aa"));
        // edge: removals cascade from the middle outwards
        check(v + " cascade", "", () -> s.solve("abccba"));
        // edge: an odd run leaves one letter behind
        check(v + " odd run", "a", () -> s.solve("aaa"));
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
