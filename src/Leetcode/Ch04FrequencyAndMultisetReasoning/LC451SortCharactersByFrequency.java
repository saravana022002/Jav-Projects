package Leetcode.Ch04FrequencyAndMultisetReasoning;

import java.util.*;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 451. Sort Characters By Frequency   [Medium]
 * https://leetcode.com/problems/sort-characters-by-frequency/
 * Topics: Hash Table, String, Sorting, Heap (Priority Queue), Bucket Sort, Counting
 * Book: Algorithmic Thinking - ch. 4 Frequency and multiset reasoning, Practice #6
 *       also: ch. 7 Non-comparison sorts: counting, bucket, radix (#5)
 *
 * PROBLEM
 *   Rearrange the characters of s so that the characters that occur most often come first
 *   (a character's frequency is how many times it appears in s). Equal characters must stay
 *   together in one block. Return the rearranged string; if several arrangements fit, any
 *   of them is accepted. Case matters: 'A' and 'a' are two different characters.
 *
 * EXAMPLES
 *   s = "tree"    ->  "eert"    'e' occurs twice, 'r' and 't' once, so "e" first; "eetr" is
 *                               accepted as well
 *   s = "cccaaa"  ->  "aaaccc"  both occur three times, so "cccaaa" is also valid;
 *                               "cacaca" is not (equal characters must be together)
 *   s = "Aabb"    ->  "bbAa"    b twice, then A and a once each; "bbaA" is also valid
 *
 * CONSTRAINTS
 *   1 <= s.length <= 5 * 10^5,   s consists of uppercase and lowercase English letters and digits
 *
 * INTUITION
 *   Only the count of each character matters, not its position. So count every character,
 *   put the distinct characters in order of decreasing count, and write each one as many
 *   times as it occurs. The only real work is the ordering "by count". Counts are whole
 *   numbers between 1 and n, so a bucket per count (array index = count) can do it without
 *   comparison sorting; a TreeMap keyed by count does it too, and with at most 128 distinct
 *   characters it is just as cheap.
 *
 * APPROACHES IN THIS FILE
 *   A. frequencySort()    - freq[128] counts, characters grouped by count in a TreeMap that
 *                           iterates from the largest count down, each character written count
 *                           times - O(n)                    <- main() runs this one
 *   B. frequencySortOpt() - the same counts, then a bucket sort (array index = count) read from
 *                           the top - meant to be O(n); WRONG as written, see STATUS
 *
 * APPROACH (as implemented below, method A - frequencySort)
 *   1. freq = new int[128]; freq[ch]++ for every character (the char code is the index, so
 *      ASCII only).
 *   2. arr = TreeMap&lt;Integer, List&lt;Character>> built with Comparator.reverseOrder(): key =
 *      a count, value = the characters that have it. Reversed order = largest count first.
 *   3. For i = 0..127 with freq[i] > 0: arr.computeIfAbsent(freq[i], ...).add((char) i).
 *      Characters with the same count are listed by increasing character code.
 *   4. For each entry of arr (largest count first) and each character c in its list:
 *      out.append(c repeated entry.getKey() times).
 *   5. Return out.toString().
 *
 * DRY RUN   s = "tree"
 *   freq: e = 2, r = 1, t = 1
 *   arr = {2: [e], 1: [r, t]}        (key 2 comes first because of reverseOrder)
 *   key 2: "ee"      key 1: "r" then "t"      -> "eert"
 *   main() runs "cccaaa": a = 3 and c = 3 share one entry {3: [a, c]} -> "aaa" + "ccc" = "aaaccc"
 *
 * COMPLEXITY
 *   A: Time O(n) - counting n characters, one scan of the 128 slots with at most 128 TreeMap
 *      inserts (a constant), and writing n characters.  Space O(n) for the output; the
 *      table and the map hold at most 128 characters
 *   B: meant to be Time O(n + maxFreq) = O(n), Space O(n) - the bucket array has maxFreq + 1 slots
 *
 * GOTCHAS
 *   - Characters with the same count may come out in any order ("eert" or "eetr"); A lists
 *     them by increasing character code: digits, then uppercase, then lowercase letters.
 *   - Case matters: in "Aabb" the 'A' (code 65) and 'a' (code 97) are different characters
 *     with count 1, and A comes first.
 *   - Each character must stay in ONE block ("cacaca" is wrong for "cccaaa").
 *   - freq has 128 slots, so a character with code 128 or more would throw
 *     ArrayIndexOutOfBoundsException. Letters and digits are fine.
 *   - String.repeat needs Java 11 or newer; Math.max(0, ...) is redundant (a count is at
 *     least 1).
 *
 * STATUS
 *   Known issue in frequencySortOpt (method B, never called by main()): the bucket loop
 *   starts at maxFreq - 1, so the bucket of the MOST frequent character(s) is never written
 *   out. "tree" returns "rt" (correct "eert"), "cccaaa" returns "", "Aabb" returns "Aa".
 *   The output is always shorter than s, so it is wrong for every valid input. The fix is to
 *   start the loop at i = maxFreq. frequencySort (method A, run by main()) is correct
 *   (checked on random strings). The code is left unchanged on purpose.
 *
 * RELATED
 *   - LC 347 Top K Frequent Elements - the same count-then-bucket idea on integers; solved in
 *     LC347TopKFrequentElements.java in this folder.
 *   - LC 1636 Sort Array by Increasing Frequency - ordering by count again, on an int array:
 *     fewest occurrences first, ties broken by the larger value first.
 * </pre>
 */
public class LC451SortCharactersByFrequency {
    public static void main(String[] args) {
        // "cccaaa": a and c both occur 3 times, so "aaaccc" and "cccaaa" are both valid.
        // Prints aaaccc
        String s = "cccaaa";
        System.out.println(frequencySort(s));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }
    // Approach A: count the characters, group them by count in a TreeMap (largest count
    // first) and write every character count times. O(n)
    public static String frequencySort(String s) {
        // freq[ch] = how many times the character with code ch occurs
        int[] freq = new int[128];
        for (char ch : s.toCharArray()) {
            freq[ch]++;
        }
        // count -> characters with that count; reverseOrder() makes the largest count come first
        TreeMap<Integer, List<Character>> arr = new TreeMap<>(Comparator.reverseOrder());
        for (int i = 0; i < freq.length; i++) {
            if(freq[i] > 0){
                arr.computeIfAbsent(freq[i], k -> new ArrayList<>()).add((char) i);
            }
        }
        // write each character as many times as it occurs, most frequent group first
        StringBuilder out = new StringBuilder();
        for(Map.Entry<Integer, List<Character>> entry : arr.entrySet()){
            for (Character c : entry.getValue()) {
                out.append(String.valueOf(c).repeat(Math.max(0, entry.getKey())));
            }
        }
        return out.toString();
    }


    // Approach B: count, then bucket sort by count (index = count). Meant to be O(n) - but it
    // drops the most frequent characters, see the loop below and STATUS. Not called by main().
    public static String frequencySortOpt(String s) {
        // count the characters and remember the highest count
        int[] freq = new int[128];
        int maxFreq = Integer.MIN_VALUE;
        for (char ch : s.toCharArray()) {
            freq[ch]++;
            maxFreq = Math.max(maxFreq, freq[ch]);
        }

        // buckets[f] = the characters that occur exactly f times
        List<Character>[] buckets = new List[maxFreq + 1];
        for (int i = 0; i < freq.length; i++) {
            if (freq[i] > 0) {
                if (buckets[freq[i]] == null) {
                    buckets[freq[i]] = new ArrayList<>();
                }
                buckets[freq[i]].add((char) i);
            }
        }
        StringBuilder out = new StringBuilder();
        // BUG: this should start at i = maxFreq; starting at maxFreq - 1 skips the bucket of
        // the most frequent character(s), so they are missing from the result (see STATUS)
        for (int i = maxFreq - 1; i > 0; i--) {
            if (buckets[i] != null) {
                for (char c : buckets[i]) {
                    for (int j = 0; j < i; j++) {
                        out.append(c);
                    }
                }
            }
        }

        return out.toString();
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 451. Sort Characters By Frequency)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the 3 official examples + edge cases (one character, all characters different,
    // digits, ties between several characters, mixed case, s.length = 5 * 10^5). Every input is
    // run through BOTH methods of the solution, frequencySort and frequencySortOpt.
    // Several answers are valid ("tree" -> "eert" or "eetr"), so the result is not compared with
    // one string: verdict() checks that it is a rearrangement of the input, that equal characters
    // form one block each, and that the blocks never get longer from left to right.
    //   Expected result: 9/18 passed
    //   The 9 failures are the known issue described in the STATUS of
    //   LC451SortCharactersByFrequency.java: frequencySortOpt skips the bucket of the most
    //   frequent characters, so its result is always too short.
    // ==========================================================================
    private static void runTests() {
        // official examples
        both("example 1: tree", "tree");
        both("example 2: cccaaa", "cccaaa");
        both("example 3: Aabb", "Aabb");
        // edge: a single character
        both("single character", "a");
        // edge: all characters different - any order is valid
        both("all different: abc", "abc");
        // edge: digits and letters together
        both("digits and letters: 1a1b1", "1a1b1");
        // edge: several characters tied for the same count
        both("ties: aabbcc", "aabbcc");
        // edge: upper and lower case are different characters
        both("mixed case: AaBbb", "AaBbb");
        // edge: the length limit 5 * 10^5, three groups of different sizes
        both("5 * 10^5 characters", "x".repeat(300000) + "y".repeat(150000) + "z".repeat(50000));
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

    // run the same input through both methods of the solution
    private static void both(String name, String s) {
        check(name + "  [frequencySort]", "valid",
                () -> verdict(s, LC451SortCharactersByFrequency.frequencySort(s)));
        check(name + "  [frequencySortOpt]", "valid",
                () -> verdict(s, LC451SortCharactersByFrequency.frequencySortOpt(s)));
    }

    // "valid" if out is an accepted answer for s, otherwise the reason (with the output)
    private static String verdict(String s, String out) {
        if (out == null) return "null";
        int[] want = new int[128];
        for (char c : s.toCharArray()) want[c]++;
        int[] got = new int[128];
        for (char c : out.toCharArray()) {
            if (c >= 128) return "unexpected character in \"" + clip(out) + "\"";
            got[c]++;
        }
        if (!Arrays.equals(want, got)) {
            return "not a rearrangement of the input: \"" + clip(out) + "\"";
        }
        // walk the blocks: every character in ONE block, block sizes never grow
        boolean[] closed = new boolean[128];
        int previous = Integer.MAX_VALUE;
        int i = 0;
        while (i < out.length()) {
            char c = out.charAt(i);
            int j = i;
            while (j < out.length() && out.charAt(j) == c) j++;
            if (closed[c]) return "a character is split into several blocks: \"" + clip(out) + "\"";
            closed[c] = true;
            if (j - i > previous) return "blocks not in decreasing order: \"" + clip(out) + "\"";
            previous = j - i;
            i = j;
        }
        return "valid";
    }

    private static String clip(String s) {
        return s.length() <= 30 ? s : s.substring(0, 30) + "...";
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
