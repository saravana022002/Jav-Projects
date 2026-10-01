package Practice.HashMap;

import java.util.*;

// ============================================================================
// LC 49. Group Anagrams   [Medium]
// https://leetcode.com/problems/group-anagrams/
// Topics: Array, Hash Table, String, Sorting
// ============================================================================
//
// PROBLEM
//   strs is an array of strings. Put strings that are anagrams of each other (same letters,
//   same counts, any order) into the same group and return all the groups. The groups, and
//   the strings inside a group, may come back in any order.
//
// EXAMPLES
//   ["eat","tea","tan","ate","nat","bat"]  ->  [["bat"],["nat","tan"],["ate","eat","tea"]]
//                                              (any order of the groups is fine)
//   [""]   ->  [[""]]     the empty string forms a group by itself
//   ["a"]  ->  [["a"]]
//
// CONSTRAINTS
//   1 <= strs.length <= 10^4,   0 <= strs[i].length <= 100,   lowercase English letters only
//
// INTUITION
//   Two words are anagrams exactly when they have the same letters with the same counts, so
//   reduce every word to a canonical form that ignores order and group words whose forms are
//   equal. Two canonical forms are used here: the letters SORTED ("eat", "tea" and "ate" all
//   become a,e,t) or the letters COUNTED (a=1, e=1, t=1, the rest 0). The form, turned into
//   a String, is the HashMap key; the map's values are the groups.
//
// APPROACHES IN THIS FILE
//   A. groupAnagrams()     - key = Arrays.toString of the word's sorted chars
//                            - O(N * K log K) time     <- main() runs this one
//   B. groupAnagramsChar() - key = Arrays.toString of a 26-slot letter count (kept in a char[])
//                            - O(N * K) time
//   (N = number of words, K = longest word length.)
//
// APPROACH (as implemented below - groupAnagrams(), method A)
//   1. stringMap: key String -> list of the words that share it.
//   2. For each str: charArray = str.toCharArray(), then Arrays.sort(charArray).
//   3. key = Arrays.toString(charArray), e.g. "[a, e, t]";
//      stringMap.computeIfAbsent(key, k -> new ArrayList<>()).add(str).
//   4. Return new ArrayList<>(stringMap.values()).
//
// DRY RUN   ["eat","tea","tan","ate","nat","bat"]   (what main() runs)
//   "eat" -> sorted a,e,t -> key "[a, e, t]"   new list [eat]
//   "tea" -> key "[a, e, t]"                   [eat, tea]
//   "tan" -> key "[a, n, t]"                   new list [tan]
//   "ate" -> key "[a, e, t]"                   [eat, tea, ate]
//   "nat" -> key "[a, n, t]"                   [tan, nat]
//   "bat" -> key "[a, b, t]"                   new list [bat]
//   three groups; main() prints [[tan, nat], [bat], [eat, tea, ate]] (HashMap order)
//
// COMPLEXITY
//   A. Time O(N * K log K) - sorting each word;   Space O(N * K) - words and keys
//   B. Time O(N * K) - one counting pass per word plus a fixed 26-slot key;  Space O(N * K)
//
// GOTCHAS
//   - In B the counters live in a char[]. That works (a char is an unsigned 16-bit number and
//     counts are at most 100) but the key text then shows characters whose CODE is the count,
//     e.g. a control character for 1, not the digit. It is still a correct key: one char per
//     letter, fixed length, so different count vectors give different keys.
//   - B only handles a..z (other characters fall outside the 26 slots and throw); A works for
//     any characters.
//   - The empty string gives the key "[]" in A and 26 zero chars in B, so [""] -> [[""]].
//   - The order of the groups follows HashMap order; LeetCode accepts any order.
//
// STATUS
//   Both methods are correct (checked against a reference on random words). main() runs
//   only groupAnagrams() (A); groupAnagramsChar() (B) is never called.
//
// RELATED
//   - LC 242 Valid Anagram - the two-string version of the same test (solved in
//     LC242ValidAnagramV1.java, V2 and V3 in this folder).
//
// SEE ALSO
//   LC49GroupAnagramsPrimeProduct.java - key = product of one prime per letter (can overflow)
//   src/Leetcode/HashMapAsLookup3/LC49GroupAnagrams.java - your curated version (count key)
//   src/Leetcode/FrequencyAndMultisetResoning4/LC49GroupAnagrams.java
//       - your curated version (sorted key)
// ============================================================================
public class LC49GroupAnagramsSortedKey {
    public static void main(String[] args) {
        // Example 1 - prints three groups; their order follows HashMap order
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        System.out.println(groupAnagrams(strs));
    }

    // Approach A: key = the word's letters in sorted order, so anagrams collide on purpose.
    // O(N * K log K). main() runs this one.
    public static List<List<String>> groupAnagrams(String[] strs) {
        // sorted-letters key -> all words with those letters
        Map<String, List<String>> stringMap = new HashMap<>();
        for (String str : strs){
            char[] charArray = str.toCharArray();
            Arrays.sort(charArray);
            stringMap.computeIfAbsent(Arrays.toString(charArray), k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(stringMap.values());
    }

    // Approach B: key = how many times each letter occurs (26 counters). O(N * K).
    // Not called by main(). Only a..z are supported.
    public static List<List<String>> groupAnagramsChar(String[] strs) {
        Map<String, List<String>> stringMap = new HashMap<>();
        for (String str : strs){
            char[] charArray = str.toCharArray();
            // charArray26[i] = how many times letter ('a' + i) occurs in str
            char[] charArray26 = new char[26];
            for (char c : charArray) {
                charArray26[c - 'a']++;
            }

            stringMap.computeIfAbsent(Arrays.toString(charArray26), k -> new ArrayList<>()).add(str);
        }
        return new ArrayList<>(stringMap.values());
    }
}
