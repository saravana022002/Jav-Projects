package Leetcode.Ch04FrequencyAndMultisetReasoning;

import java.util.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 49. Group Anagrams   [Medium]
 * https://leetcode.com/problems/group-anagrams/
 * Topics: Array, Hash Table, String, Sorting
 * Book: Algorithmic Thinking - ch. 4 Frequency and multiset reasoning, Practice #5
 *       also: ch. 3 Hashing as a search-to-lookup transform (#6)
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
 *   Two words are anagrams exactly when sorting their letters gives the same string: "eat",
 *   "tea" and "ate" all become "aet". So use the SORTED word as a key - anagrams collide on
 *   it on purpose, words that are not anagrams never do. A HashMap from that key to the list
 *   of words that have it does the grouping, and the map's values are the groups.
 *
 * APPROACH (as implemented below)
 *   1. map: sorted-letters key -> list of the words that have it.
 *   2. For each word s: arr = s.toCharArray(), Arrays.sort(arr), key = new String(arr).
 *   3. map.computeIfAbsent(key, k -> new ArrayList<>()).add(s): the first word with a key
 *      creates the group, every word is then added to it.
 *   4. Return new ArrayList<>(map.values()): one list per key = one group per set of anagrams.
 *
 * DRY RUN   ["eat","tea","tan","ate","nat","bat"]   (what main() runs)
 *   eat -> key "aet"   new group [eat]
 *   tea -> key "aet"   [eat, tea]
 *   tan -> key "ant"   new group [tan]
 *   ate -> key "aet"   [eat, tea, ate]
 *   nat -> key "ant"   [tan, nat]
 *   bat -> key "abt"   new group [bat]
 *   three groups; main() prints [[eat, tea, ate], [bat], [tan, nat]] (HashMap order)
 *
 * COMPLEXITY
 *   Time : O(N * K log K) - N words of length at most K; sorting one word costs K log K and
 *          hashing its K-character key costs K
 *   Space: O(N * K) - every word is stored in some group, and the keys hold the same letters again
 *
 * GOTCHAS
 *   - The order of the groups follows HashMap order (decided by the key hashes, not by the
 *     input order); LeetCode accepts any order. Inside a group the words keep their input
 *     order, because they are added one by one.
 *   - The empty string sorts to the empty key "", so [""] -> [[""]]. Equal words ("a", "a")
 *     share a key and stay together in one group.
 *   - new String(arr) builds the key text from the sorted chars; arr.toString() would give
 *     an identity string like [C@1b6d3586 and break the grouping.
 *   - Sorting works for any characters, not only a..z (a 26-slot count key would not).
 *
 * RELATED
 *   - LC 242 Valid Anagram - the two-string version of the same test; solved in
 *     LC242ValidAnagram.java in this folder.
 *   - LC 2273 Find Resultant Array After Removing Anagrams - decides whether neighbouring
 *     words are anagrams; a sorted key turns that test into a plain equality check.
 *
 * SEE ALSO
 *   src/Leetcode/Ch03HashingAsASearchToLookupTransform/LC49GroupAnagrams.java
 *       - the same grouping with a letter-COUNT key (26 counters turned into a String): O(K)
 *         per word, no sorting
 *   src/Practice/HashMap/LC49GroupAnagramsSortedKey.java
 *       - the same sorted key (written with Arrays.toString), plus a 26-slot count key
 *   src/Practice/HashMap/LC49GroupAnagramsPrimeProduct.java
 *       - key = product of one prime per letter; the long product can overflow on long words
 * </pre>
 */
public class LC49GroupAnagrams {
    public static void main(String[] args) {
        // Example 1 - prints three groups; their order follows HashMap order
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        System.out.println(groupAnagrams(strs));
        // run the test cases (TESTS section at the end of this class)
        runTests();
    }

    // Key = the word's letters in sorted order, so anagrams land in the same list.
    // O(N * K log K)
    public static List<List<String>> groupAnagrams(String[] strs) {
        // sorted-letters key -> all words with those letters
        Map<String, List<String>> map = new HashMap<>();
        for (String s : strs) {
            // sort the letters of the word ("eat", "tea" and "ate" all become "aet")
            char[] arr = s.toCharArray();
            Arrays.sort(arr);

            // the first word with this key creates the group; every word is added to it
            String key = new String(arr);
            map.computeIfAbsent(key, k -> new ArrayList<>()).add(s);
        }
        // each map value is one group of anagrams
        return new ArrayList<>(map.values());
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 49. Group Anagrams)
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Cases: the 3 official examples + edge cases (no two words are anagrams, equal words, empty
    // strings next to a letter, words of different lengths, 100-letter words, 10^4 words in one
    // group). The problem accepts any order, so both sides are normalised first: every group
    // is sorted, then the groups are sorted. Expected values come from the statement or from a
    // sort-the-letters brute force done outside the solution.
    //   Expected result: 9/9 passed
    // ==========================================================================
    private static void runTests() {
        // official examples
        check("example 1: eat tea tan ate nat bat",
                normalise(groups(words("bat"), words("nat", "tan"), words("ate", "eat", "tea"))),
                () -> normalise(LC49GroupAnagrams.groupAnagrams(
                        new String[]{"eat", "tea", "tan", "ate", "nat", "bat"})));
        check("example 2: [\"\"]", normalise(groups(words(""))),
                () -> normalise(LC49GroupAnagrams.groupAnagrams(new String[]{""})));
        check("example 3: [a]", normalise(groups(words("a"))),
                () -> normalise(LC49GroupAnagrams.groupAnagrams(new String[]{"a"})));
        // edge: no two words are anagrams - every word is its own group
        check("no anagrams: abc def ghi",
                normalise(groups(words("abc"), words("def"), words("ghi"))),
                () -> normalise(LC49GroupAnagrams.groupAnagrams(
                        new String[]{"abc", "def", "ghi"})));
        // edge: equal words are anagrams of each other and stay in one group
        check("equal words: a a a", normalise(groups(words("a", "a", "a"))),
                () -> normalise(LC49GroupAnagrams.groupAnagrams(new String[]{"a", "a", "a"})));
        // edge: several empty strings and one letter
        check("empty strings: \"\" \"\" a", normalise(groups(words("", ""), words("a"))),
                () -> normalise(LC49GroupAnagrams.groupAnagrams(new String[]{"", "", "a"})));
        // edge: words of different lengths in interleaved order
        check("different lengths: ab ba abc cab bca bac",
                normalise(groups(words("ab", "ba"), words("abc", "bac", "bca", "cab"))),
                () -> normalise(LC49GroupAnagrams.groupAnagrams(
                        new String[]{"ab", "ba", "abc", "cab", "bca", "bac"})));
        // edge: the longest words, 100 letters
        String z100 = "z".repeat(100), yz = "y" + "z".repeat(99), zy = "z".repeat(99) + "y";
        check("100-letter words", normalise(groups(words(z100), words(yz, zy))),
                () -> normalise(LC49GroupAnagrams.groupAnagrams(new String[]{z100, zy, yz})));
        // edge: the limit of 10^4 words, all anagrams of "ab" - a single group
        String[] many = new String[10000];
        String[] expectedGroup = new String[10000];
        for (int i = 0; i < 10000; i++) {
            many[i] = i % 2 == 0 ? "ab" : "ba";
            expectedGroup[i] = many[i];
        }
        check("10^4 words in one group", normalise(groups(expectedGroup)),
                () -> normalise(LC49GroupAnagrams.groupAnagrams(many)));
        System.out.println(passed + "/" + total + " passed");
    }

    private static int passed = 0, total = 0;

    private static String[] words(String... w) {
        return w;
    }

    private static List<List<String>> groups(String[]... g) {
        List<List<String>> out = new ArrayList<>();
        for (String[] one : g) out.add(Arrays.asList(one));
        return out;
    }

    // any order is accepted: sort every group, then sort the groups
    private static List<List<String>> normalise(List<List<String>> groups) {
        List<List<String>> out = new ArrayList<>();
        for (List<String> g : groups) {
            List<String> copy = new ArrayList<>(g);
            Collections.sort(copy);
            out.add(copy);
        }
        out.sort(Comparator.comparing((List<String> g) -> String.join("|", g)));
        return out;
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
