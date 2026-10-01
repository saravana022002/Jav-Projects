package Practice.HashMap;

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
//   Two strings are anagrams exactly when every letter occurs equally often in both, so
//   count instead of comparing positions. One shared array goes UP by 1 for each letter of s
//   and DOWN by 1 for each letter of t; if the counts match, every counter cancels back to 0.
//   When the lengths differ the counters cannot all cancel (they add up to the length
//   difference), so no separate length check is needed.
//
// APPROACH (as implemented below)
//   1. freq = new int[26]: one counter per letter, the slot of a letter is (letter - 'a').
//   2. For every character of s (charS): freq[c - 'a']++.
//   3. For every character of t (charT): freq[c - 'a']--.
//   4. If any counter j is not 0 -> return false, otherwise return true.
//
// DRY RUN   s = "rat", t = "car"   (what main() runs)
//   after counting s : a=1 r=1 t=1
//   after counting t : a=0 c=-1 r=0 t=1          (c and t are left over)
//   scan a..z        : c = -1 is not 0 -> return false
//   (With "anagram" / "nagaram" every counter ends at 0 and the method returns true.)
//
// COMPLEXITY
//   Time : O(n + m) - one pass over each string plus a fixed 26-slot scan
//          (n = s.length(), m = t.length())
//   Space: O(1) for the 26 counters; toCharArray() also copies both strings, O(n + m)
//
// GOTCHAS
//   - No length check is needed: "ab" vs "a" leaves b = 1, which the final scan catches.
//   - Counters may go negative along the way; only the final state matters.
//   - Only 'a'..'z' work. Any other character (e.g. 'A') gives a slot outside 0..25 and
//     throws ArrayIndexOutOfBoundsException.
//
// STATUS
//   Correct for the lowercase input the problem promises. main() calls isAnagram() but does
//   not print the result (it would be false).
//
// RELATED
//   - LC 49 Group Anagrams - group words that are anagrams of each other; solved in
//     LC49GroupAnagramsSortedKey.java and LC49GroupAnagramsPrimeProduct.java.
//   - LC 438 Find All Anagrams in a String - the same letter counting on a sliding window.
//
// SEE ALSO
//   LC242ValidAnagramV2.java - same int[26] counting with an early length check and one loop
//   LC242ValidAnagramV3.java - length check + int[128] table (any ASCII) with an early exit
//   src/Leetcode/FrequencyAndMultisetResoning4/LC242ValidAnagram.java - your curated version
// ============================================================================
public class LC242ValidAnagramV1 {
    public static void main(String[] args) {
        // Example 2 - not anagrams; nothing is printed (isAnagram returns false)
        String s1 = "rat";
        String s2 = "car";
        isAnagram(s1, s2);
    }
    // Counts the letters of s up and the letters of t down in one int[26]; anagrams cancel
    // out to all zeros.
    public static boolean isAnagram(String s, String t) {
        // one counter per letter 'a'..'z'
        int[] freq = new int[26];
        char[] charS = s.toCharArray();
        // +1 for every letter of s
        for (char aChar : charS) {
            freq[aChar - 'a']++;
        }

        char[] charT = t.toCharArray();
        // -1 for every letter of t
        for (char c : charT) {
            freq[c - 'a']--;
        }
        // any leftover count means the two strings do not have the same letters
        for (int j : freq) {
            if (j != 0) {
                return false;
            }
        }
        return true;
    }
}
