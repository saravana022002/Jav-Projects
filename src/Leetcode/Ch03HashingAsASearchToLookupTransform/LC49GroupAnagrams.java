package Leetcode.Ch03HashingAsASearchToLookupTransform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 49. Group Anagrams   [Medium]
 * https://leetcode.com/problems/group-anagrams/
 * Topics: Array, Hash Table, String, Sorting
 * Book: Algorithmic Thinking - ch. 3 Hashing as a search-to-lookup transform, Practice #6
 *       also: ch. 4 Frequency and multiset reasoning (#5)
 *
 * PROBLEM
 *   strs is an array of strings. Put the strings that are anagrams of each other (the same
 *   letters with the same counts, in any order) into one group and return all the groups.
 *   The groups, and the strings inside a group, may come back in any order.
 *
 * EXAMPLES
 *   ["eat","tea","tan","ate","nat","bat"]  ->  [["bat"],["nat","tan"],["ate","eat","tea"]]
 *                                              (any order of the groups is fine)
 *   [""]   ->  [[""]]     the empty string forms a group of its own
 *   ["a"]  ->  [["a"]]
 *
 * CONSTRAINTS
 *   1 <= strs.length <= 10^4,   0 <= strs[i].length <= 100,   lowercase English letters only
 *
 * INTUITION
 *   Two words are anagrams exactly when they use the same letters the same number of times.
 *   So give every word a FINGERPRINT that ignores letter order and is equal only for anagrams,
 *   and let a HashMap group the words by fingerprint. Here the fingerprint is the letter
 *   COUNT: 26 counters, one per letter a..z. Counting a word is one pass over it (no
 *   sorting), and the 26 counters are turned into a 26-character String so they can be a
 *   HashMap key.
 *
 * APPROACH (as implemented below)
 *   1. map: fingerprint String -> list of the words that share it.
 *   2. For each word s: arr = new char[26] (all zero); for every character of s,
 *      arr[ch - 'a']++. Now arr[0] is the number of a's, arr[1] the number of b's, ...
 *   3. key = new String(arr): a 26-character String whose i-th character has the CODE
 *      "count of letter i". Same counts -> same key.
 *   4. map.computeIfAbsent(key, k -> new ArrayList<>()).add(s): create the group on first use,
 *      then add the word.
 *   5. Return new ArrayList<>(map.values()): one list per key = one group per set of anagrams.
 *
 * DRY RUN   ["eat","tea","tan","ate","nat","bat"]   (what main() runs)
 *   word  letter counts   key (shortened)   group after adding
 *   eat   a1 e1 t1        K1                new group [eat]
 *   tea   a1 e1 t1        K1                [eat, tea]
 *   tan   a1 n1 t1        K2                new group [tan]
 *   ate   a1 e1 t1        K1                [eat, tea, ate]
 *   nat   a1 n1 t1        K2                [tan, nat]
 *   bat   a1 b1 t1        K3                new group [bat]
 *   three groups; main() prints [[tan, nat], [eat, tea, ate], [bat]] (HashMap order)
 *
 * COMPLEXITY
 *   Time : O(N * K) - N words of length at most K; each is counted in one pass, and building
 *          and hashing the fixed 26-character key is O(26)
 *   Space: O(N * K) - every word is stored in some group; the keys add N strings of 26 chars
 *
 * GOTCHAS
 *   - A char[] works as the counter array: a char is an unsigned 16-bit number, so ++ is fine
 *     and counts (at most 100) fit. The key therefore shows control characters (code 1 for
 *     "once"), not digits - still a correct key, because every letter has its own fixed slot.
 *   - An int[] could not be the key itself: arrays hash by identity, not by content.
 *     new String(char[]) gives content-based equals/hashCode.
 *   - Only a..z: any other character makes arr[ch - 'a'] go out of range and throw (the
 *     constraints promise lowercase letters).
 *   - The empty string has 26 zero characters as its key, so [""] -> [[""]]; several empty
 *     strings land in the same group.
 *   - The order of the groups follows HashMap order; LeetCode accepts any order.
 *
 * STATUS
 *   Correct (compared with a sorted-letters reference on random words, and on 100-letter
 *   words that differ in one letter). Test file: main().java in this folder.
 *
 * RELATED
 *   - LC 242 Valid Anagram - the two-string version of the same test.
 *
 * SEE ALSO
 *   src/Leetcode/Ch04FrequencyAndMultisetReasoning/LC49GroupAnagrams.java
 *       - key = the word's letters SORTED instead of counted (O(K log K) per word)
 *   src/Practice/HashMap/LC49GroupAnagramsSortedKey.java
 *       - the sorted-letters key, plus a 26-slot count key written as Arrays.toString text
 *   src/Practice/HashMap/LC49GroupAnagramsPrimeProduct.java
 *       - key = product of one prime per letter; the long product can overflow on long words
 * </pre>
 */
public class LC49GroupAnagrams {
    public static void main(String[] args) {
        // Example 1 - three groups; their order follows HashMap order
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        System.out.println(groupAnagrams(strs));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }

    // Groups the words by their letter counts (anagrams share a count vector). O(N * K).
    public static List<List<String>> groupAnagrams(String[] strs) {
        // letter-count key -> all words with those counts
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            // arr[i] = how many times the letter ('a' + i) occurs in s
            char[] arr = new char[26];
            for (int j = 0; j < s.length(); j++) {
                arr[s.charAt(j) - 'a']++;
            }

            // the 26 counts as a String (char code = count): equal for anagrams, hashable
            String key = new String(arr);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }
        // one list per distinct key = one group per set of anagrams
        return new ArrayList<>(map.values());
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 49. Group Anagrams)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the 3 official examples + edge cases (all words anagrams of each other, no anagrams
    // at all, repeated words and empty strings, 100-letter words that differ in one letter);
    // expected values from the statement or checked against a sorted-letters brute force.
    // The answer may come in any order, so both sides are normalised first: every group is
    // sorted, then the list of groups is sorted.
    //   Expected result: 7/7 passed
    // ==========================================================================
    private static void runTests() {
        // official examples
        check("example 1", groups(g("bat"), g("nat", "tan"), g("ate", "eat", "tea")),
                () -> group("eat", "tea", "tan", "ate", "nat", "bat"));
        check("example 2: the empty string", groups(g("")), () -> group(""));
        check("example 3: one word", groups(g("a")), () -> group("a"));
        // edge: every word is an anagram of every other
        check("one big group", groups(g("abc", "bca", "cab")), () -> group("abc", "bca", "cab"));
        // edge: no two words are anagrams
        check("all different", groups(g("a"), g("b"), g("c")), () -> group("a", "b", "c"));
        // edge: repeated words stay together, and several empty strings form one group
        check("repeats and empty strings", groups(g("ab", "ab"), g("", "")),
                () -> group("ab", "ab", "", ""));
        // edge: the longest allowed words (100 letters); one differs in a single letter
        String a100 = rep('a', 100), a99b = rep('a', 99) + "b", ba99 = "b" + rep('a', 99);
        check("100-letter words", groups(g(a100), g(a99b, ba99)), () -> group(a100, a99b, ba99));
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

    // the solution's answer with every group sorted and the groups sorted (any order is valid)
    private static List<List<String>> group(String... strs) {
        return normalise(LC49GroupAnagrams.groupAnagrams(strs));
    }

    // expected side: the same normalisation applied to the groups written in the cases
    private static List<List<String>> groups(String[]... gs) {
        List<List<String>> in = new ArrayList<>();
        for (String[] words : gs) {
            in.add(Arrays.asList(words));
        }
        return normalise(in);
    }

    // one expected group
    private static String[] g(String... words) {
        return words;
    }

    private static List<List<String>> normalise(List<List<String>> in) {
        List<List<String>> out = new ArrayList<>();
        for (List<String> group : in) {
            List<String> copy = new ArrayList<>(group);
            Collections.sort(copy);
            out.add(copy);
        }
        out.sort(Comparator.comparing(Object::toString));
        return out;
    }

    private static String rep(char c, int n) {
        char[] a = new char[n];
        Arrays.fill(a, c);
        return new String(a);
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
