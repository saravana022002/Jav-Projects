package Practice.HashMap;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

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
//   Follow-up: what if the inputs contain Unicode characters? (The 26-slot table below would
//   throw for them; counts keyed by character in a map would be needed.)
//
// INTUITION
//   Two strings are anagrams exactly when every letter occurs equally often in both. Strings
//   of different lengths can never match, so that is checked first - after it, one index can
//   walk both strings together. At each position the counter of s's letter goes UP and the
//   counter of t's letter goes DOWN; if the letter counts match, everything cancels to 0.
//
// APPROACH (as implemented below)
//   1. char1 / char2 = the characters of s and t. Different lengths -> return false.
//   2. char1int = new int[26]: one counter per letter, the slot is (letter - 'a').
//   3. One while loop over i < length: char1int[char1[i] - 'a']++ and
//      char1int[char2[i] - 'a']--.
//   4. Scan k = 0..25: any non-zero counter -> return false; otherwise return true.
//
// DRY RUN   s = "dgqztusjuu", t = "dqugjzutsu"   (what main() runs)
//   non-zero counters after each step (+ letter of s, - letter of t):
//   i=0 +d -d   (none)                   i=5 +u -z   j=-1 t=1
//   i=1 +g -q   g=1 q=-1                 i=6 +s -u   j=-1 s=1 t=1 u=-1
//   i=2 +q -u   g=1 u=-1                 i=7 +j -t   s=1 u=-1
//   i=3 +z -g   u=-1 z=1                 i=8 +u -s   (none)
//   i=4 +t -j   j=-1 t=1 u=-1 z=1        i=9 +u -u   (none)
//   the final scan finds 26 zeros -> return true
//
// COMPLEXITY
//   Time : O(n) - one loop over the n characters plus a fixed 26-slot scan
//   Space: O(1) for the 26 counters; toCharArray() also copies both strings, O(n)
//
// GOTCHAS
//   - The early length check is what makes a single loop safe: both arrays have the same
//     length, so index i exists in each.
//   - Only 'a'..'z' work. Any other character (e.g. 'A') gives a slot outside 0..25 and
//     throws ArrayIndexOutOfBoundsException.
//
// STATUS
//   Correct for the lowercase input the problem promises. main() calls isAnagram() but does
//   not print the result (it would be true). The HashSet lines at the end of main() have
//   nothing to do with the check, and the imports Arrays and Stack are unused.
//
// RELATED
//   - LC 49 Group Anagrams - group words that are anagrams of each other; solved in
//     LC49GroupAnagramsSortedKey.java and LC49GroupAnagramsPrimeProduct.java.
//   - LC 438 Find All Anagrams in a String - the same letter counting on a sliding window.
//
// SEE ALSO
//   LC242ValidAnagramV1.java - same int[26] idea, counts s up and t down in two loops
//   LC242ValidAnagramV3.java - length check + int[128] table (any ASCII) with an early exit
//   src/Leetcode/FrequencyAndMultisetResoning4/LC242ValidAnagram.java - your curated version
// ============================================================================
public class LC242ValidAnagramV2 {
    public static void main(String[] args) {
        // same 10 letters in a different order - nothing is printed; isAnagram returns true
        String str1 = "dgqztusjuu";
        String str2 = "dqugjzutsu";
        isAnagram(str1, str2);
        // leftover lines, unrelated to the anagram check
        Set<String> set = new HashSet<>();
        set.add("add");
    }

    // Early length check, then ONE loop that counts s up and t down at the same time.
    public static boolean isAnagram(String s, String t) {
        char[] char1 = s.toCharArray();
        char[] char2 = t.toCharArray();
        // different lengths can never be anagrams (and this lets one index walk both arrays)
        if(char1.length != char2.length){
            return false;
        }

        // one counter per letter 'a'..'z'
        int[] char1int = new int[26];

        int length = char1.length;

        // +1 for s's letter, -1 for t's letter at the same position
        int i = 0;
        while(i < length){
            char1int[char1[i] - 'a' ]++;
            char1int[char2[i] - 'a' ]--;
            i++;
        }

        // every counter back at 0 -> the same letters with the same counts
        for (int k = 0; k < 26; k++) {
            if(char1int[k] != 0){
                return false;
            }
        }

        return true;
    }
}
