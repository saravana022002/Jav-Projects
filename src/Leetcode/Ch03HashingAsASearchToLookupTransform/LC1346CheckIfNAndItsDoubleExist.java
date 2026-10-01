package Leetcode.Ch03HashingAsASearchToLookupTransform;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1346. Check If N and Its Double Exist   [Easy]
 * https://leetcode.com/problems/check-if-n-and-its-double-exist/
 * Topics: Array, Hash Table, Two Pointers, Binary Search, Sorting
 * Book: Algorithmic Thinking - ch. 3 Hashing as a search-to-lookup transform, Practice #4
 *
 * PROBLEM
 *   arr is an array of ints. Is there a number in it that is exactly the double of another
 *   number in it? The two numbers must sit at different positions (i != j) and satisfy
 *   arr[i] == 2 * arr[j]. Return true if such a pair exists, false otherwise.
 *   (0 is its own double, so a single 0 does not count, but two zeros do.)
 *
 * EXAMPLES
 *   arr = [10,2,5,3]   ->  true     10 is 2 * 5
 *   arr = [3,1,7,11]   ->  false    no value is the double of another one
 *
 * CONSTRAINTS
 *   2 <= arr.length <= 500,   -10^3 <= arr[i] <= 10^3
 *
 * INTUITION
 *   Every number x has two possible partners: 2 * x (x is the half) or x / 2 (x is the
 *   double - only possible when x is even). A HashSet of the numbers seen so far answers
 *   "is that partner already there?" in O(1), so a single left-to-right pass is enough:
 *   each pair is noticed when its LATER member is reached, whichever of the two is the
 *   double. Checking BEFORE adding the current number stops a number pairing with itself.
 *
 * APPROACH (as implemented below)
 *   1. map (a HashSet, despite its name) holds the numbers visited so far.
 *   2. For each j in arr, look for a partner among the earlier numbers:
 *        - map contains j * 2                  (j is the half of an earlier number), or
 *        - j is even and map contains j / 2    (j is the double of an earlier number).
 *      Either one -> return true.
 *   3. Otherwise map.add(j) and move on - the add comes AFTER the check.
 *   4. The loop ended without a hit -> return false.
 *
 * DRY RUN   arr = [10,2,5,3]   (what main() runs)
 *   j=10: 20 not in map; 10 is even and 5 not in map   -> add     map = {10}
 *   j=2 : 4 not in map;  2 is even and 1 not in map    -> add     map = {10,2}
 *   j=5 : 10 is in map (5 * 2 = 10)                    -> return true
 *
 * COMPLEXITY
 *   Time : O(n) - one pass, O(1) average per HashSet operation
 *   Space: O(n) - the set can end up holding every number
 *
 * GOTCHAS
 *   - 0 == 2 * 0, but a lone 0 must not pair with itself. The check runs before map.add, so
 *     [0,3] -> false while [0,0] -> true (the second 0 finds the first).
 *   - The j % 2 == 0 guard matters: for odd j, j / 2 rounds down (11 / 2 == 5), so [5,11]
 *     would wrongly report true although 11 != 2 * 5.
 *   - Negatives work: -4 % 2 == 0 and -4 / 2 == -2. An odd negative gives -3 % 2 == -1, so
 *     it skips the half-check like any other odd number.
 *   - Overflow cannot happen: |arr[i]| <= 1000, so j * 2 is tiny.
 *
 * STATUS
 *   Correct (checked against a double-loop reference on random arrays). The imports
 *   HashMap and Map are unused, and the set is called "map".
 *   Test file: main().java in this folder.
 *
 * RELATED
 *   - LC 2154 Keep Multiplying Found Values by Two - another "is 2 * x in the array?"
 *     question, which a HashSet answers directly.
 *   - LC 1 Two Sum - same trick: remember what you have seen, then look up the partner
 *     you need (solved in this folder).
 * </pre>
 */
public class LC1346CheckIfNAndItsDoubleExist {
    public static void main(String[] args) {
        // Example 1 - 10 is the double of 5. Expected output: true
        int[] arr = {10,2,5,3};
        System.out.println(checkIfExist(arr));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }
    // Returns true when some number is exactly double another number at a different position.
    public static boolean checkIfExist(int[] arr) {
        // numbers visited so far (a set, despite the name)
        HashSet<Integer> map = new HashSet<>();
        for (int j : arr) {
            // a partner among the earlier numbers: j is their half (j * 2 is there) or j is
            // their double (j is even and j / 2 is there)
            if (map.contains(j * 2) || (j % 2 == 0 && map.contains(j / 2))) {
                return true;
            }
            // added only after the check, so j can never pair with itself (matters for 0)
            map.add(j);
        }
        return false;
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 1346. Check If N and Its Double Exist)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the 2 official examples + edge cases (zero as its own double, an odd number whose
    // rounded-down half is present, negatives with the double on either side); expected values
    // from the statement or checked against a double-loop brute force.
    //   Expected result: 8/8 passed
    // ==========================================================================
    private static void runTests() {
        // official examples
        check("example 1: 10 is 2 * 5", true, () -> exists(10, 2, 5, 3));
        check("example 2: no double anywhere", false, () -> exists(3, 1, 7, 11));
        // edge: 0 is its own double, but only with a second 0 at another position
        check("two zeros", true, () -> exists(0, 0));
        check("a single zero does not pair with itself", false, () -> exists(0, 3));
        // edge: 11 / 2 rounds down to 5, but 11 is not 2 * 5
        check("odd number, rounded-down half present", false, () -> exists(5, 11));
        // edge: negatives, the half first and the double first
        check("negatives, half first", true, () -> exists(-2, -4));
        check("negatives, double first", true, () -> exists(-4, -2));
        // edge: the double comes before the half in the array
        check("double before half", true, () -> exists(14, 7));
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

    private static boolean exists(int... arr) {
        return LC1346CheckIfNAndItsDoubleExist.checkIfExist(arr);
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
