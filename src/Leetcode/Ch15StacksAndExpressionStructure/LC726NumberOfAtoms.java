package Leetcode.Ch15StacksAndExpressionStructure;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 726. Number of Atoms   [Hard]
 * https://leetcode.com/problems/number-of-atoms/
 * Topics: Hash Table, String, Stack, Sorting
 * Book: Algorithmic Thinking - ch. 15 Stacks and expression structure, Practice #12
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   formula is a chemical formula. An atom is an uppercase letter followed by any number of
 *   lowercase letters (its name). Digits right after an atom give its count; they are written
 *   only when the count is above 1, so "H1" never occurs. Formulas can be written one after
 *   another, and a formula in parentheses may be followed by a count that multiplies
 *   everything inside it. Return the total count of every atom as one string: the atom names in
 *   sorted order, each followed by its total when that total is above 1.
 *
 * EXAMPLES
 *   formula = "H2O"            ->  "H2O"         {H: 2, O: 1}
 *   formula = "Mg(OH)2"        ->  "H2MgO2"      {H: 2, Mg: 1, O: 2}
 *   formula = "K4(ON(SO3)2)2"  ->  "K4N2O14S4"   {K: 4, N: 2, O: 14, S: 4}
 *
 * CONSTRAINTS
 *   1 <= formula.length <= 1000,   formula consists of English letters, digits, '(' and ')',
 *   formula is valid,   every count in the answer fits in a 32-bit integer
 * </pre>
 */
public class LC726NumberOfAtoms {

    /**
     * Variant A
     * <br>Target: time O(n^2), space O(n)
     * <br>Complete first: ch. 3 Hashing as a search-to-lookup transform
     */
    public static String countOfAtomsA(String formula) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B
     * <br>Target: time O(n log n), space O(n)
     * <br>Complete first: ch. 3 Hashing as a search-to-lookup transform
     *     + ch. 15 Stacks and expression structure (this chapter)
     */
    public static String countOfAtomsB(String formula) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n^2), space O(n)
     * <br>Complete first: ch. 3 Hashing as a search-to-lookup transform
     *     + ch. 15 Stacks and expression structure (this chapter)
     */
    public static String countOfAtomsC(String formula) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC726NumberOfAtoms::countOfAtomsA);
        runAll("B", LC726NumberOfAtoms::countOfAtomsB);
        runAll("C", LC726NumberOfAtoms::countOfAtomsC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 726. Number of Atoms)
    // https://leetcode.com/problems/number-of-atoms/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases confirmed with an independent reference (the groups expanded
    // as plain text, then counted).
    //   Expected result: 0/18 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        String solve(String formula);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", "H2O", () -> s.solve("H2O"));
        check(v + " example 2", "H2MgO2", () -> s.solve("Mg(OH)2"));
        check(v + " example 3", "K4N2O14S4", () -> s.solve("K4(ON(SO3)2)2"));
        // edge: a single atom without a count
        check(v + " single atom", "H", () -> s.solve("H"));
        // edge: the order is alphabetical on whole names: H, He, Mg, O
        check(v + " name order", "H2He3Mg4O2", () -> s.solve("H2O2He3Mg4"));
        // edge: nested groups, a group without a count, one atom spread over several places
        check(v + " nested groups", "Ca4H7O7", () -> s.solve("(Ca(OH)2)3Ca(OH)"));
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
