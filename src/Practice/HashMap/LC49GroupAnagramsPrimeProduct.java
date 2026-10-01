package Practice.HashMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
//   Give every word a KEY such that anagrams share a key and other words do not; a HashMap
//   from key to list of words then does the grouping. Here the key is arithmetic: give each
//   letter its own prime (a=2, b=3, c=5, ... z=101) and multiply the primes of the word's
//   letters. Multiplication ignores order, so anagrams get equal products, and since every
//   integer has only one prime factorisation, words with different letter counts get
//   different products - as long as the product fits in a long (see STATUS).
//
// APPROACH (as implemented below)
//   1. strToNums(str): value starts at 1 and is multiplied by prime[c - 'a'] for every
//      character c; prime[] holds the 26 primes 2 .. 101 in letter order.
//   2. groupAnagrams: for each str, val = strToNums(str), then
//      nums.computeIfAbsent(val, k -> new ArrayList<>()).add(str).
//   3. Return new ArrayList<>(nums.values()): one list per distinct product.
//
// DRY RUN   ["eat","tea","tan","ate","nat","bat"]   (what main() runs)
//   "eat" = 11*2*71 = 1562   "tea" = 1562   "ate" = 1562
//   "tan" = 71*2*43 = 6106   "nat" = 6106
//   "bat" = 3*2*71  = 426
//   nums = {1562=[eat,tea,ate], 6106=[tan,nat], 426=[bat]}  -> three groups
//   (the printed order of the groups follows HashMap order and may differ from example 1)
//
// COMPLEXITY
//   Time : O(N * K) - N words of length at most K, one multiplication per character
//   Space: O(N * K) - every word is stored in some list; the keys add O(N) longs
//
// GOTCHAS
//   - The empty string has the empty product 1, so [""] -> [[""]] works.
//   - Only a..z are handled: any other character gives an index outside prime[] and throws
//     ArrayIndexOutOfBoundsException.
//
// STATUS
//   Known issue: the product overflows a long for longer words (ten 'z' already give
//   101^10, more than 9.22 * 10^18), wraps around, and then different letter sets can share
//   a key. Example: "a" x 65 and "a" x 64 + "b" both get key 0 (2^64 wraps to 0), so they are
//   wrongly put in one group although they are not anagrams. Words of up to 9 letters can
//   never overflow; LeetCode allows 100. The code is left unchanged on purpose.
//
// RELATED
//   - LC 242 Valid Anagram - the two-string version of the same test (solved in
//     LC242ValidAnagramV1.java, V2 and V3 in this folder).
//
// SEE ALSO
//   LC49GroupAnagramsSortedKey.java - key = sorted letters, or a 26-slot letter count (no overflow)
//   src/Leetcode/HashMapAsLookup3/LC49GroupAnagrams.java - your curated version (count key)
//   src/Leetcode/FrequencyAndMultisetResoning4/LC49GroupAnagrams.java
//       - your curated version (sorted key)
// ============================================================================
public class LC49GroupAnagramsPrimeProduct {
    public static void main(String[] args) {
        // Example 1 - prints three groups; their order follows HashMap order
        String[] strs = {"eat","tea","tan","ate","nat","bat"};
        System.out.println(groupAnagrams(strs));
    }
    // Files each word under its prime-product key; anagrams share a key.
    public static List<List<String>> groupAnagrams(String[] strs) {
        // product -> all words with that product
        Map<Long, List<String>> nums = new HashMap<>();
        Long val;
        for (String str : strs) {
            val = strToNums(str);
            nums.computeIfAbsent(val, k -> new ArrayList<>()).add(str);
        }
        // one list per distinct key = one group per set of anagrams
        return new ArrayList<>(nums.values());
    }
    // Key of a word: product of one prime per letter (a=2 ... z=101), order-independent.
    // KNOWN ISSUE: the long product overflows for long words - see STATUS.
    public static Long strToNums(String str){
        // prime[0] belongs to 'a', prime[25] to 'z'
        int[] prime = {
                2, 3, 5, 7, 11, 13, 17, 19, 23, 29,
                31, 37, 41, 43, 47, 53, 59, 61, 67, 71,
                73, 79, 83, 89, 97, 101
        };
        char[] character = str.toCharArray();
        // the empty product: "" gets key 1
        long value = 1L;
        for (int c : character) {
            value *= prime[c - 'a'];
        }
        return value;
    }



}
