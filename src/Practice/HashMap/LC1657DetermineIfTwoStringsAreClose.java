package Practice.HashMap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// ============================================================================
// LC 1657. Determine if Two Strings Are Close   [Medium]
// https://leetcode.com/problems/determine-if-two-strings-are-close/
// Topics: Hash Table, String, Sorting, Counting
// ============================================================================
//
// PROBLEM
//   Two lowercase strings are "close" if one can be turned into the other with these two
//   moves, used any number of times on either string:
//     - swap the positions of any two characters, and
//     - pick two letters that both occur and swap their roles everywhere (every a becomes
//       b and every b becomes a) - this trades the counts of the two letters.
//   Return true if word1 and word2 are close, false otherwise.
//
// EXAMPLES
//   word1 = "abc",    word2 = "bca"       ->  true    reordering alone is enough
//   word1 = "a",      word2 = "aa"        ->  false   no move changes the length
//   word1 = "cabbba", word2 = "abbccc"    ->  true    counts {a:2,b:3,c:1} vs {a:1,b:2,c:3} -
//                                                     same letters, same counts 1,2,3
//
// CONSTRAINTS
//   1 <= word1.length, word2.length <= 10^5,   only lowercase English letters
//
// INTUITION
//   Move 1 makes the order irrelevant, so only the letter counts matter. Move 2 lets the
//   counts be handed around between letters, but it never adds a new letter or removes
//   one. So two strings are close exactly when
//     (a) they use the same SET of letters, and
//     (b) their counts form the same multiset (the same numbers, in any letter order).
//
// APPROACH (as implemented below)
//   1. Different lengths -> false.
//   2. One pass fills charCount1 / charCount2 (count per letter) and set1 / set2 (letters).
//   3. Every letter of set1 must also be in set2, else false.
//   4. Compare the count multisets with one map: +1 at map[count] for each letter of
//      word1, -1 at map[count] for each letter of word2 (letters with count 0 are skipped).
//   5. Any non-zero value means some count appears a different number of times -> false;
//      otherwise true.
//
// DRY RUN   main(): "uau" vs "ssx"
//   lengths 3 == 3; set1 = {u,a}, set2 = {s,x}
//   'u' (or 'a') is not in set2 -> return false
//   (Both have counts {2,1}, so step 4 alone would have said true - step 3 catches it.)
//
//   Example 3: "cabbba" vs "abbccc" reaches step 4:
//   letter a: +map[2], -map[1]  -> {2:1, 1:-1}
//   letter b: +map[3], -map[2]  -> {2:0, 1:-1, 3:1}
//   letter c: +map[1], -map[3]  -> {2:0, 1:0, 3:0}    all zero -> true
//
// COMPLEXITY
//   Time : O(n) - one pass over the strings, then loops over the 26 letters
//   Space: O(1) - two 26-slot arrays, sets of at most 26 letters, at most 52 map keys
//
// GOTCHAS
//   - Same counts are not enough: main's "uau" / "ssx" have equal count multisets but
//     different letters, and move 2 cannot bring in a letter that is not there.
//   - Only set1 -> set2 is checked, not the reverse. That is still enough: step 4 forces
//     both words to have the same number of distinct letters, so the sets end up equal.
//   - Counts per letter do not have to match (that would be an anagram, LC 242 Valid
//     Anagram); only the collection of counts has to.
//
// RELATED
//   - LC 242 Valid Anagram (LC242ValidAnagramV1.java) - the stricter test: the same count
//     for every single letter.
//   - LC 1347 Minimum Number of Steps to Make Two Strings Anagram - also compares
//     26-letter counts of two strings.
// ============================================================================
public class LC1657DetermineIfTwoStringsAreClose {
    public static void main(String[] args) {
        // Same count pattern (one letter twice, one once) but different letters.
        // Expected output: false
        String word1 = "uau";
        String word2 = "ssx";
        System.out.println(closeStrings(word1, word2));
    }

    // Returns true when word1 can be turned into word2 with the two moves.
    public static boolean closeStrings(String word1, String word2) {
        // no move changes the length
        if(word1.length() != word2.length()){
            return false;
        }
        // count per letter, plus the set of letters used, for both words in one pass
        int[] charCount1 = new int[26];
        int[] charCount2 = new int[26];
        Set<Character> set1 = new HashSet<>();
        Set<Character> set2 = new HashSet<>();
        for (int i = 0; i < word1.length(); i++){
            charCount1[word1.charAt(i) - 'a']++;
            charCount2[word2.charAt(i) - 'a']++;
            set1.add(word1.charAt(i));
            set2.add(word2.charAt(i));
        }

        // rule (a): a letter of word1 missing from word2 can never be created
        for(Character c : set1){
            if(!set2.contains(c)){
                return false;
            }
        }


        // rule (b): count -> (how many letters of word1 have it) - (how many of word2 do)
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < 26; i++) {
            if(charCount1[i] != 0){
                map.put(charCount1[i], map.getOrDefault(charCount1[i], 0) + 1);
            }
            if(charCount2[i] != 0){
                map.put(charCount2[i], map.getOrDefault(charCount2[i], 0) - 1);
            }
        }

        // all zero <=> both words have the same multiset of counts
        for (Integer value : map.values()){
            if(value != 0){
                return false;
            }
        }
        return true;
    }
}
