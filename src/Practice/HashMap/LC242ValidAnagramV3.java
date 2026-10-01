package Practice.HashMap;

import java.util.ArrayList;
import java.util.List;

// ============================================================================
// LC 242. Valid Anagram   [Easy]
// https://leetcode.com/problems/valid-anagram/
// Topics: Hash Table, String, Sorting
// ============================================================================
//
// PROBLEM
//   You get two strings, s and t. Return true when t can be made by rearranging the letters of
//   s (exactly the same letters, each used the same number of times, just in another order);
//   otherwise return false.
//
// EXAMPLES
//   s = "anagram", t = "nagaram"  ->  true     same letters: a three times, n, g, r, m once
//   s = "rat",     t = "car"      ->  false    t has a 'c' where s has a 't'
//
// CONSTRAINTS
//   1 <= s.length, t.length <= 5 * 10^4,   s and t consist of lowercase English letters
//   Follow-up: what if the inputs contain Unicode characters? (The 128-slot table below would
//   throw for them; counts keyed by character in a map would be needed.)
//
// INTUITION
//   Two strings are anagrams exactly when every letter occurs equally often in both. Count the
//   letters of s in a table, then "spend" one count for each letter of t. A letter of t whose
//   count is already 0 means t has more of it than s -> not an anagram. With equal lengths, if
//   every letter of t found a count, all the counts were used up, so the strings match.
//   Approach A spends the same way, slowly: it removes one copy from a list of s's letters.
//
// APPROACHES IN THIS FILE
//   A. isAnagram1() - list of s's characters; remove one copy per character of t; anagram
//                     iff nothing is missing and the list ends up empty
//                     - O(n * m) time, O(n) space. main() does not call it.
//   B. isAnagram()  - length check, int[128] counts of s, spend one count per character of t
//                     - O(n) time, O(1) space.   <- main() runs this one
//
// APPROACH (as implemented below - isAnagram(), method B)
//   1. Different lengths -> return false.
//   2. freq = new int[128]: the slot of a character is its character code (ASCII only).
//   3. For every character of s: freq[s.charAt(i)]++.
//   4. For every character of t: if its count is 0 -> return false (t has too many of it),
//      otherwise freq[t.charAt(i)]--.
//   5. Every character of t found a count -> return true (equal lengths, so all counts are 0).
//
// DRY RUN
//   main(): s = "ab", t = "a" - the lengths 2 and 1 differ -> false at step 1.
//   s = "rat", t = "car": freq after s is a=1 r=1 t=1; the first character of t is 'c' and
//   freq['c'] == 0 -> return false.
//   s = "rat", t = "tar": 't' 1->0, 'a' 1->0, 'r' 1->0, every count found -> true.
//
// COMPLEXITY
//   B. Time O(n) - two passes (n = s.length() = t.length() after the check);
//      Space O(1) - a fixed table of 128 ints
//   A. Time O(n * m) - contains() and remove() scan the list for every character of t;
//      Space O(n) - the list of boxed Characters
//
// GOTCHAS
//   - list.remove((Character) ch): the cast selects remove(Object), which deletes one equal
//     element. A plain char would be widened to int and call remove(int index) instead.
//   - A needs no length check: a longer t runs out of matches (contains fails) and a longer
//     s leaves the list non-empty.
//   - B checks the count BEFORE decrementing, so a count never drops below 0.
//   - freq is indexed by character code: a character of code 128 or more throws
//     ArrayIndexOutOfBoundsException. Fine for lowercase letters, not for the Unicode follow-up.
//
// STATUS
//   Correct for the lowercase input the problem promises. main() calls only isAnagram()
//   (method B) and does not print the result (it would be false). isAnagram1() is never called.
//
// RELATED
//   - LC 49 Group Anagrams - group words that are anagrams of each other; solved in
//     LC49GroupAnagramsSortedKey.java and LC49GroupAnagramsPrimeProduct.java.
//   - LC 438 Find All Anagrams in a String - the same letter counting on a sliding window.
//
// SEE ALSO
//   LC242ValidAnagramV1.java - int[26] counting: s up, t down, then a scan for non-zeros
//   LC242ValidAnagramV2.java - same int[26] idea with an early length check and one loop
//   src/Leetcode/FrequencyAndMultisetResoning4/LC242ValidAnagram.java - your curated version
// ============================================================================
public class LC242ValidAnagramV3 {

    public static void main(String[] args) {
        // different lengths -> isAnagram returns false at once (the result is not printed)
        String s = "ab";
        String t = "a";
        isAnagram(s, t);
    }
    // Approach A: list of s's characters; delete one copy for every character of t.
    // O(n * m) time, O(n) space. Not called by main().
    public static boolean isAnagram1(String s, String t) {
        List<Character> list = new ArrayList<>();
        for(int i = 0; i < s.length(); i++){
            list.add(s.charAt(i));
        }

        for(int i = 0; i < t.length(); i++){
            if(!list.contains(t.charAt(i))){
                // t has a character that s never had, or has used up already
                return false;
            }else{
                // the (Character) cast picks remove(Object): delete one copy by value
                list.remove((Character) t.charAt(i));
            }
        }
        // empty list -> nothing of s was left over either
        return list.isEmpty();
    }

    // Approach B: length check, then a table of character counts. O(n) time, O(1) space.
    // main() runs this one.
    public static boolean isAnagram(String s, String t) {

        // different lengths can never be anagrams
        if (s.length() != t.length()) {
            return false;
        }

        // count the characters of s (slot = character code)
        int[] freq = new int[128];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i)]++;
        }

        // spend one count per character of t; a count already at 0 means t has one too many
        for (int i = 0; i < t.length(); i++) {
            if(freq[t.charAt(i)] == 0){
                return false;
            }
            freq[t.charAt(i)]--;
        }
        return true;
    }
}
