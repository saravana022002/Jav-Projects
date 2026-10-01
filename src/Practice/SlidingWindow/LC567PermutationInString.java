package Practice.SlidingWindow;

// ============================================================================
// LC 567. Permutation in String   [Medium]
// https://leetcode.com/problems/permutation-in-string/
// Topics: Hash Table, Two Pointers, String, Sliding Window
// ============================================================================
//
// PROBLEM
//   You get two strings, s1 and s2. Decide whether s2 contains, as a contiguous substring,
//   some rearrangement of s1 (same letters with the same counts, in any order).
//   Return true if it does and false otherwise.
//
// EXAMPLES
//   s1 = "ab", s2 = "eidbaooo"  ->  true     the substring "ba" is a rearrangement of "ab"
//   s1 = "ab", s2 = "eidboaoo"  ->  false    'a' and 'b' are never next to each other
//
// CONSTRAINTS
//   1 <= s1.length, s2.length <= 10^4,   both strings use lowercase English letters only
//
// INTUITION
//   A rearrangement of s1 has exactly s1's letter counts, so it can only be a window of s2
//   that is as long as s1. Slide that fixed-size window along s2 and ask at every position:
//   "are the window's letter counts equal to s1's?" Comparing all 26 counts each time would
//   cost 26 steps per position, so the code keeps `matches` = how many of the 26 letters
//   currently have equal counts. Sliding one step changes only two counts (one letter leaves,
//   one enters), so matches can be repaired in O(1). matches == 26 means "this window is a
//   rearrangement of s1".
//
// APPROACH (as implemented below)
//   1. If s1 is longer than s2 no window fits -> return false.
//   2. countS1 = letter counts of s1; countS2 = letter counts of the first window
//      s2[0 .. lengthS1 - 1].
//   3. matches = number of letters (out of 26) whose two counts are equal. A letter that is
//      in neither string counts as equal (0 == 0).
//   4. The window is s2[l .. r - 1], starting at l = 0, r = lengthS1. While r < lengthS2:
//      a. matches == 26 -> the current window is a rearrangement -> return true.
//      b. Slide by one: leftChar = s2[l] leaves, rightChar = s2[r] enters. After each count
//         change: counts now equal -> matches++ (they differed before); counts were equal
//         before the change (now off by one) -> matches--.
//      c. l++ and r++.
//   5. The loop exits right after the last slide without testing that final window, so finish
//      with return matches == 26.
//
// DRY RUN   s1 = "ab", s2 = "eidbaooo" (Example 1)
//   start  window "ei": a, b, e, i all differ; the other 22 letters are 0 == 0
//                                                                    matches = 22
//   slide  out e, in d  -> window "id"                               matches = 22
//   slide  out i, in b  -> window "db"   (b now equals s1's count)   matches = 24
//   slide  out d, in a  -> window "ba"   (a now equals s1's count)   matches = 26
//   top of the next loop pass: matches == 26 -> return true
//   main()'s input (s2 = "eidboaoo") never reaches 26 - matches ends at 23 for the last
//   window "oo" - so the method returns false there.
//
// COMPLEXITY   (n = s1.length(), m = s2.length())
//   Time : O(n + m) - counting the first window is O(n), each of the at most m - n slides is
//          O(1), and the initial matches scan is a fixed 26 steps
//   Space: O(n + m) as written, because toCharArray() copies both strings; the two count
//          tables have a fixed size of 26, i.e. O(1). Reading with charAt() would make it O(1).
//
// GOTCHAS
//   - matches counts all 26 letters, including letters that appear in neither string, so the
//     target is 26 and not "the number of distinct letters in s1".
//   - Each changed count needs both checks (became equal / was equal before), because a count
//     can move into a match or out of one.
//   - When the leaving and the entering letter are the same (window "oa" -> "ao" in Example 2)
//     the decrement and the increment cancel out; doing them one after the other keeps
//     matches correct.
//   - Indexing with ch - 'a' only works for lowercase a-z, which the constraints guarantee.
//
// STATUS
//   The method is correct. main() calls it but does not print the result (it returns false
//   for the input main() uses).
//
// SEE ALSO
//   src/Leetcode/FrequencyAndMultisetResoning4/LC567PermutationinString.java - your curated
//     version: the same matches counter, written as one loop that adds s2[i] and then drops
//     the letter that left the window
//
// RELATED
//   - LC 438 Find All Anagrams in a String - the same fixed-size window and letter counts,
//     but it collects every start index instead of stopping at the first match.
//   - LC 76 Minimum Window Substring - keeps a similar count of satisfied letters, on a window
//     that grows and shrinks instead of a fixed one.
// ============================================================================
public class LC567PermutationInString {
    public static void main(String[] args) {
        // Example 2: 'a' and 'b' are never adjacent, so the answer is false. The return value
        // is not printed, so this run shows nothing on the console.
        String s1 = "ab";
        String s2 = "eidboaoo";
        checkInclusion(s1, s2);
    }
    // Returns true if some window of s2 (as long as s1) has exactly the letter counts of s1
    public static boolean checkInclusion(String s1, String s2) {
        int lengthS1 = s1.length();
        int lengthS2 = s2.length();
        // s1 cannot fit inside a shorter s2
        if(lengthS1 > lengthS2){
            return false;
        }
        int[] countS1 = new int[26];
        int[] countS2 = new int[26];
        char[] charStr1 = s1.toCharArray();
        char[] charStr2 = s2.toCharArray();
        // countS1 = letters of s1, countS2 = letters of the first window s2[0 .. lengthS1 - 1]
        for (int i = 0; i < lengthS1; i++) {
            countS1[charStr1[i] - 'a']++;
            countS2[charStr2[i] - 'a']++;
        }

        // matches = how many of the 26 letters have equal counts (absent letters match 0 == 0)
        int matches = 0;
        for (int i = 0; i < 26; i++) {
            if(countS1[i] == countS2[i]){
                matches++;
            }
        }

        // window = s2[l .. r - 1]; r is the index of the next letter to enter
        int l = 0;
        int r = lengthS1;
        while (r < lengthS2){
            // all 26 counts agree -> the current window is a rearrangement of s1
            if(matches == 26){
                return true;
            }

            int leftChar = charStr2[l] - 'a';
            int rightChar = charStr2[r] - 'a';
            // letter leaving the window: equal now -> +1; it was equal just before -> -1
            countS2[leftChar]--;
            if(countS1[leftChar] == countS2[leftChar]){
                matches++;
            }else if(countS1[leftChar] == countS2[leftChar] + 1){
                matches--;
            }
            // letter entering the window: the same two checks, mirrored
            countS2[rightChar]++;
            if(countS1[rightChar] == countS2[rightChar]){
                matches++;
            }else if(countS1[rightChar] == countS2[rightChar] - 1){
                matches--;
            }
            l++;
            r++;
        }
        // the loop never tests the last window, so test it here
        return matches == 26;
    }



}
