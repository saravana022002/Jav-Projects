package Practice.Strings;

// ============================================================================
// LC 14. Longest Common Prefix   [Easy]
// https://leetcode.com/problems/longest-common-prefix/
// Topics: Array, String, Trie
// ============================================================================
//
// PROBLEM
//   You get an array of strings. Return the longest string that is a prefix of every one of
//   them, or "" when the strings have no common prefix.
//
// EXAMPLES
//   strs = ["flower","flow","flight"]  ->  "fl"   the first two letters agree everywhere;
//                                                 the third letters are 'o', 'o' and 'i'
//   strs = ["dog","racecar","car"]     ->  ""     not even the first letter is shared
//
// CONSTRAINTS
//   1 <= strs.length <= 200,   0 <= strs[i].length <= 200,
//   strs[i] consists of lowercase English letters only (when it is non-empty)
//
// INTUITION
//   Stack the strings in rows so that their letters line up in columns. The common prefix is
//   the run of leading columns in which every row shows the same letter. So test column 0 in
//   every string, then column 1, and so on, and stop at the first column that is not
//   unanimous - either a letter differs, or some string has run out of letters. The first
//   string serves as the reference for "the same letter", and nothing beyond the answer is
//   ever read.
//
// APPROACH (as implemented below)
//   1. builder = a StringBuilder that collects the prefix.
//   2. For each column i of strs[0] (i from 0 to strs[0].length() - 1):
//      a. For every str in strs: if i == str.length() (no letter left in this column) or
//         strs[0].charAt(i) != str.charAt(i), the prefix ends here -> return
//         builder.toString().
//      b. All strings agreed -> builder.append(strs[0].charAt(i)).
//   3. Every column of strs[0] survived, so strs[0] itself is the prefix: return
//      builder.toString().
//
// DRY RUN   strs = ["flower","flow","flight"]
//   i=0  'f'  flower, flow, flight all have 'f'          -> builder = "f"
//   i=1  'l'  all three have 'l'                         -> builder = "fl"
//   i=2  'o'  flower ok, flow ok, flight has 'i'         -> return "fl"
//
// COMPLEXITY   (n = number of strings, m = length of the answer, at most the shortest string)
//   Time : O(n * m) - at most m + 1 columns are examined, each against up to n strings
//   Space: O(m) - the StringBuilder holding the answer; no other storage
//
// GOTCHAS
//   - The length test i == str.length() is written before charAt(i) on purpose: for a string
//     with no letter in column i, the || short-circuits and charAt is never reached. It is
//     enough to test for equality because a shorter string would already have stopped the
//     scan in an earlier column.
//   - strs[0] is compared with itself in every column, which always passes.
//   - An empty strs[0] gives "" at once (the column loop never runs); an empty string later in
//     the array stops the scan at column 0.
//   - strs[0] is read without a check, so an empty array would throw; the constraints
//     guarantee at least one string.
//
// STATUS
//   The method is correct; main() prints fl.
//
// SEE ALSO
//   LC14LongestCommonPrefixThreeApproaches.java - horizontal, vertical (returning a substring
//     instead of using a builder) and sorting versions in one file
//
// RELATED
//   - LC 208 Implement Trie (Prefix Tree) - a trie of all the strings gives the same answer:
//     walk down from the root while a node has exactly one child and is not a word end.
// ============================================================================
public class LC14LongestCommonPrefixVerticalScan {
    public static void main(String[] args) {
        // the strings share "fl" (next letters 'o', 'o', 'i'). Expected output: fl
        String[] strs = {"flower","flow","flight"};
        System.out.println(longestCommonPrefix(strs));
    }
    // Returns the leading columns on which all strings agree
    public static String longestCommonPrefix(String[] strs) {

        // collects the prefix, one letter per column that every string agrees on
        StringBuilder builder = new StringBuilder();
        // column i is checked across all strings, with strs[0] as the reference
        for (int i = 0; i < strs[0].length(); i++) {
            for (String str : strs) {
                // str has no letter in column i, or a different one -> the prefix ends here
                if (i == str.length() || strs[0].charAt(i) != str.charAt(i)) {
                    return builder.toString();
                }
            }
            // every string agreed on this column
            builder.append(strs[0].charAt(i));
        }
        // all of strs[0] is a prefix of every string
        return builder.toString();
    }
}
