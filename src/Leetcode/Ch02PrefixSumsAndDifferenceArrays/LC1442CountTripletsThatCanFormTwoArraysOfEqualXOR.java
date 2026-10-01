package Leetcode.Ch02PrefixSumsAndDifferenceArrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1442. Count Triplets That Can Form Two Arrays of Equal XOR   [Medium]
 * https://leetcode.com/problems/count-triplets-that-can-form-two-arrays-of-equal-xor/
 * Topics: Array, Hash Table, Math, Bit Manipulation, Prefix Sum
 * Book: Algorithmic Thinking - ch. 2 Prefix sums and difference arrays, Practice #9
 *
 * PROBLEM
 *   For an integer array arr, count the index triples (i, j, k) with 0 <= i < j <= k < n
 *   for which
 *        a = arr[i] ^ ... ^ arr[j-1]   equals   b = arr[j] ^ ... ^ arr[k]
 *   (^ is XOR). In words: cut the range i..k into a non-empty left part and a non-empty right
 *   part, and the two parts must have the same XOR. Different (i, j, k) are different
 *   triplets even when they cover the same range.
 *
 * EXAMPLES
 *   arr = [2,3,1,6,7]  ->  4    (0,1,2) (0,2,2) (2,3,4) (2,4,4)
 *   arr = [1,1,1,1,1]  ->  10
 *
 * CONSTRAINTS
 *   1 <= arr.length <= 300,   1 <= arr[i] <= 10^8
 *
 * INTUITION
 *   x == y exactly when x ^ y == 0, and a ^ b is simply the XOR of the whole range i..k. So a
 *   triplet works iff arr[i..k] XORs to 0 - WHERE the cut j is does not matter, and every
 *   cut j = i+1 .. k works. A range with XOR 0 therefore contributes k - i triplets.
 *   With prefix XORs P[0] = 0 and P[t] = arr[0] ^ ... ^ arr[t-1], the range i..k has XOR
 *   P[i] ^ P[k+1], which is 0 iff P[i] == P[k+1]. So: remember where each prefix value has
 *   appeared, and whenever it appears again, every earlier occurrence closes one such range.
 *
 * APPROACHES IN THIS FILE
 *   A. countTriplets()        - map prefix XOR -> list of the places it occurred; every
 *                               earlier occurrence adds j - i - 1. O(n^2) worst case, O(n)
 *                               when prefix values rarely repeat   <- main() runs this one
 *   B. countTripletsOptimal() - the O(n) idea: per prefix value keep only HOW OFTEN it
 *                               occurred and the SUM of those places, and add
 *                               count * (j - 1) - sum in one step. It has a bug - see STATUS.
 *
 * APPROACH (as implemented below, method A)
 *   1. index maps a prefix-XOR value to the list of indexes after which it occurred; it
 *      starts as {0 -> [-1]} (the empty prefix, "after index -1"). prefixXor = 0, count = 0.
 *   2. For j = 0 .. n-1: prefixXor ^= arr[j]  (now the XOR of arr[0..j]).
 *   3. If this value was seen before, then for every stored index i the range arr[i+1..j]
 *      XORs to 0 and can be cut at j - i - 1 places, so count += j - i - 1.
 *   4. Add j to the list of prefixXor.
 *   5. Return count.
 *
 * DRY RUN   arr = [2,3,1,6,7] (the input main() runs)
 *   j=0  prefixXor = 2            new                                    {0:[-1], 2:[0]}
 *   j=1  prefixXor = 2^3 = 1      new                                    ... 1:[1]
 *   j=2  prefixXor = 1^1 = 0      seen at -1: count += 2 - (-1) - 1 = 2  0:[-1,2]
 *        (range arr[0..2] = 2^3^1 = 0, cut after 0 or after 1: triplets (0,1,2), (0,2,2))
 *   j=3  prefixXor = 0^6 = 6      new                                    ... 6:[3]
 *   j=4  prefixXor = 6^7 = 1      seen at 1: count += 4 - 1 - 1 = 2
 *        (range arr[2..4] = 1^6^7 = 0: triplets (2,3,4), (2,4,4))
 *   return 4
 *
 * COMPLEXITY
 *   A: Time O(n^2) worst case - the loop over the stored list can grow with j (e.g. an
 *      array of equal numbers makes the prefix value alternate between two values, so each
 *      list holds about j/2 entries); n <= 300, so this is tiny. Space O(n) for the lists.
 *   B (if it were correct): Time O(n), Space O(n) - two maps with one entry per prefix value.
 *
 * GOTCHAS
 *   - Only the XOR of the whole range matters, not where it is cut. That is why one equal
 *     pair of prefixes gives many triplets (j - i - 1 of them), not one.
 *   - The -1 entry for the empty prefix is what lets ranges that start at index 0 be found.
 *   - Adjacent prefixes give j - i - 1 = 0: a range of length 1 cannot be cut (and a single
 *     number >= 1 never XORs to 0 anyway).
 *   - In B, countTerm is read from totalIndexMap, i.e. the SUM of indices; the formula
 *     count * (j - 1) - sum needs the NUMBER of occurrences, countMap.get(prefixXor).
 *
 * STATUS
 *   Known issue in countTripletsOptimal (method B): the line
 *   "int countTerm = totalIndexMap.get(prefixXor);" reads the sum of indices where the
 *   occurrence count is needed (countMap.get(prefixXor)). It returns wrong totals, e.g.
 *   2 for [2,3,1,6,7] (correct: 4), 5 for [1,1,1,1,1] (correct: 10) and 0 for [1,2,3]
 *   (correct: 2); it is only right when the count happens to equal the index sum.
 *   countTriplets (method A), which main() runs, is correct.
 *   The code is left unchanged on purpose.
 *
 * RELATED
 *   - LC 2433 Find The Original Array of Prefix Xor - the opposite direction: from the prefix
 *     XORs back to the array.
 *   - LC 560 Subarray Sum Equals K (solved in src/Practice/PrefixSum) - the same "equal prefix
 *     values mark a qualifying range" idea, with sums instead of XOR.
 * </pre>
 */
public class LC1442CountTripletsThatCanFormTwoArraysOfEqualXOR {
    public static void main(String[] args) {
        // Example 1: prints 4 (method A)
        int[] arr = {2,3,1,6,7};
        System.out.println(countTriplets(arr));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }
    // Approach A: prefix XOR -> list of earlier places; each match adds j - i - 1. O(n^2) worst
    public static int countTriplets(int[] arr) {
        // value of a prefix XOR -> the indexes after which it occurred
        Map<Integer, List<Integer>> index = new HashMap<>();
        List<Integer> arrList = new ArrayList<>();
        // the empty prefix (XOR 0) counts as "after index -1"
        arrList.add(-1);
        index.put(0, arrList);
        int count = 0;
        int prefixXor = 0;
        for (int j = 0; j < arr.length; j++) {
            prefixXor ^= arr[j];
            // equal earlier prefix at i: arr[i+1..j] XORs to 0 and can be cut in j - i - 1 places
            if(index.containsKey(prefixXor)){
                for (Integer i : index.get(prefixXor)) {
                    count += j - i - 1;
                }
            }
            // remember where this prefix value occurred
            index.computeIfAbsent(prefixXor, k -> new ArrayList<>()).add(j);
        }
        return count;
    }


    // Approach B: O(n) idea - per prefix value keep the occurrence count and the sum of the
    // places, then add count * (j - 1) - sum at once. BUG: countTerm below is wrong (see STATUS)
    public static int countTripletsOptimal(int[] arr) {
        // prefix value -> sum of the indexes where it occurred
        Map<Integer, Integer> totalIndexMap = new HashMap<>();
        // prefix value -> how many times it occurred
        Map<Integer, Integer> countMap = new HashMap<>();
        totalIndexMap.put(0, -1);
        countMap.put(0, 1);
        int count = 0;
        int prefixXor = 0;
        for (int j = 0; j < arr.length; j++) {
            prefixXor ^= arr[j];
            if(countMap.containsKey(prefixXor)){
                // sum of the earlier indexes i
                int minusTerm = totalIndexMap.get(prefixXor);
                // BUG: this should be the NUMBER of earlier occurrences, countMap.get(prefixXor)
                int countTerm = totalIndexMap.get(prefixXor);
                // sum over the earlier i of (j - i - 1) = number * (j - 1) - sum of the i's
                count += countTerm * (j - 1) - minusTerm;
            }
            totalIndexMap.put(prefixXor, totalIndexMap.getOrDefault(prefixXor, 0) + j);
            countMap.put(prefixXor, countMap.getOrDefault(prefixXor, 0) + 1);
        }
        return count;
    }

    // ================================ TESTS =================================
    // Tests for this class 
    // (LC 1442. Count Triplets That Can Form Two Arrays of Equal XOR)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the two official examples + edge cases; every input is run through both methods of
    // the solution (countTriplets and countTripletsOptimal). Expected values come from the
    // statement, from the reasoning noted per case, and (checked offline) from a brute force
    // that XORs both parts of every triplet directly.
    //   Expected result: 8/12 passed
    //   The 4 failures are the known issue described in the STATUS of
    //   LC1442CountTripletsThatCanFormTwoArraysOfEqualXOR.java (countTripletsOptimal is wrong).
    // ==========================================================================
    private static void runTests() {
        // official examples
        both("example 1: [2,3,1,6,7]", new int[]{2, 3, 1, 6, 7}, 4);
        both("example 2: [1,1,1,1,1]", new int[]{1, 1, 1, 1, 1}, 10);
        // edge: one element - no triplet fits (i < j <= k needs two elements)
        both("single element", new int[]{1}, 0);
        // edge: the smallest array with an answer - (0,1,1), since 5 == 5
        both("[5,5]", new int[]{5, 5}, 1);
        // edge: [1,2,3] - the whole array XORs to 0, so both cuts work: (0,1,2) and (0,2,2)
        both("[1,2,3] cut in two places", new int[]{1, 2, 3}, 2);
        // edge: the largest input, 300 ones (O(n^3) brute force gives 2250050)
        int[] ones = new int[300];
        Arrays.fill(ones, 1);
        both("300 ones", ones, 2250050);
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

    // runs one input through both methods of the solution
    private static void both(String name, int[] arr, int expected) {
        check(name + "  [countTriplets]", expected,
                () -> LC1442CountTripletsThatCanFormTwoArraysOfEqualXOR.countTriplets(arr));
        check(name + "  [countTripletsOptimal]", expected,
                () -> LC1442CountTripletsThatCanFormTwoArraysOfEqualXOR.countTripletsOptimal(arr));
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
