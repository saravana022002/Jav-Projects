package Practice.TwoPointers;

// ============================================================================
// LC 5. Longest Palindromic Substring   [Medium]
// https://leetcode.com/problems/longest-palindromic-substring/
// Topics: Two Pointers, String, Dynamic Programming, Manacher
// ============================================================================
//
// PROBLEM
//   Given a string s, return its longest palindromic substring: a contiguous piece of s that
//   reads the same forwards and backwards. If several palindromes share the maximum length,
//   any one of them is accepted.
//
// EXAMPLES
//   s = "babad"  ->  "bab"   "aba" is an equally valid answer
//   s = "cbbd"   ->  "bb"
//   s = "cbba"   ->  "bb"    the input main() uses
//
// CONSTRAINTS
//   1 <= s.length <= 1000,   s consists of digits and English letters only
//
// INTUITION
//   Every palindrome mirrors around its centre, and the centre is either one character (odd
//   length, like "aba") or the gap between two characters (even length, like "bb"). So try all
//   2n - 1 centres and grow outwards with two pointers while the two ends are equal; the
//   longest span seen is the answer. Each palindrome is reached from its own centre, so none
//   is missed, and a growth step costs O(1).
//
// APPROACH (as implemented below)
//   1. maxLen = 0 (length of the best palindrome so far); maxStr = "".
//   2. For every index i:
//      a. Odd centre: l = r = i. While l >= 0, r < s.length() and s.charAt(l) == s.charAt(r):
//         if maxLen < r - l + 1, then maxLen = r - l + 1 and maxStr = s.substring(l, r + 1);
//         then r++, l--.
//      b. Even centre: lE = i, rE = i + 1, the same loop with lE / rE.
//   3. Return maxStr.
//   The test is a strict <, so a palindrome that only ties with the best so far does not
//   replace it: the FIRST one of maximum length wins.
//
// DRY RUN   s = "cbba"   (the input main() uses)
//   i=0 'c'  odd: "c" (length 1) -> maxLen = 1, maxStr = "c"      even: 'c' != 'b'
//   i=1 'b'  odd: "b" is not longer; widen: 'c' != 'b'
//            even: 'b' == 'b' -> "bb" (length 2) -> maxLen = 2, maxStr = "bb"; widen: 'c' != 'a'
//   i=2 'b'  odd: no improvement; widen: 'b' != 'a'.   even: 'b' != 'a'
//   i=3 'a'  odd: no improvement; widen: r runs past the end.   even: rE is past the end
//   return "bb"
//
// COMPLEXITY
//   Time : O(n^2) - 2n - 1 centres, each widening at most n/2 times; a substring is copied
//          only when the best grows, which happens at most n times
//   Space: O(1) besides the returned substring (it reads s with charAt, no char[] copy)
//
// GOTCHAS
//   - Even-length palindromes have no middle character. Without the second loop "cbbd" would
//     come out as "b" instead of "bb".
//   - maxLen is the real length here (r - l + 1); V1 keeps r - l instead.
//   - The strict < returns the earlier of equal-length palindromes: "babad" gives "bab",
//     exactly LeetCode's sample answer (V1, with <=, gives "aba").
//   - The index checks l >= 0 and r < s.length() come before the charAt comparison.
//
// STATUS
//   The method is correct (checked against a brute force on random strings). main() prints bb.
//
// SEE ALSO
//   LC5LongestPalindromicSubstringV1.java - the same expand-around-centre idea with a <=
//     comparison (the later of equal palindromes wins); its main() currently runs an unrelated
//     LC 1411 brute force
//
// RELATED
//   - LC 647 Palindromic Substrings - the same centre expansion, counting every palindrome
//     instead of keeping the longest.
//   - LC 516 Longest Palindromic Subsequence - palindromes that need not be contiguous (DP).
// ============================================================================
public class LC5LongestPalindromicSubstringV2 {

    public static void main(String[] args) {
        // the longest palindrome in "cbba" is "bb". Expected output: bb
        String s = "cbba";
        System.out.println(longestPalindrome(s));
    }

    // Expand around each of the 2n - 1 centres; on equal length the first palindrome stays
    // (strict <). O(n^2)
    public static String longestPalindrome(String s) {
        int maxLen = 0;
        String maxStr = "";
        for (int i = 0; i < s.length(); i++) {
            // odd-length palindromes: the centre is the character i
            int r = i;
            int l = i;
            while(l >= 0 && r < s.length() && s.charAt(l) == s.charAt(r)){
                // strictly longer than the best so far: remember it
                if(maxLen < r - l + 1){
                    maxLen = r - l + 1;
                    maxStr = s.substring(l, r + 1);
                }
                r++;
                l--;
            }

            // even-length palindromes: the centre is the gap between i and i + 1
            int lE = i;
            int rE = i + 1;
            while(lE >= 0 && rE < s.length() && s.charAt(lE) == s.charAt(rE)){
                if(maxLen < rE - lE + 1){
                    maxLen = rE - lE + 1;
                    maxStr = s.substring(lE, rE + 1);
                }
                rE++;
                lE--;
            }
        }
        return maxStr;
    }
}
