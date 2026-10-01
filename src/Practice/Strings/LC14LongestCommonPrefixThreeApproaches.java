package Practice.Strings;

import java.util.Arrays;

// ============================================================================
// LC 14. Longest Common Prefix   [Easy]
// https://leetcode.com/problems/longest-common-prefix/
// Topics: Array, String, Trie
// ============================================================================
//
// PROBLEM
//   Given an array of strings, return the longest string that every one of them starts with
//   (their longest common prefix). If they share no prefix at all, return "".
//
// EXAMPLES
//   strs = ["flower","flow","flight"]  ->  "fl"   all three start with "fl"; the next
//                                                 letters are 'o', 'o' and 'i'
//   strs = ["dog","racecar","car"]     ->  ""     not even the first letter is shared
//
// CONSTRAINTS
//   1 <= strs.length <= 200,   0 <= strs[i].length <= 200,
//   strs[i] consists of lowercase English letters only (when it is non-empty)
//
// INTUITION
//   Every string starts with the answer, so the answer is a prefix of any single string, for
//   example strs[0]. The job is to find the point where some string disagrees. The three
//   methods hunt for that point in different ways:
//     - horizontal: shrink a candidate prefix against one string after another
//     - vertical  : compare the strings letter by letter, column by column
//     - sorting   : after sorting, strings with a shared prefix sit next to each other, so
//                   whatever the smallest and the largest string share, every string in
//                   between shares too - only those two need to be compared
//
// APPROACHES IN THIS FILE
//   A. longestCommonPrefix()         - horizontal: start from strs[0], cut it back against
//                                      each following string - O(n * m)
//   B. longestCommonPrefixVertical() - vertical: go column by column over all strings, stop
//                                      at the first short string or mismatch - O(n * m)
//   C. longestCommonPrefixSorting()  - sort, then compare only the first and the last string
//                                      - O(n * m * log n)   <- main() runs this one
//   (n = number of strings, m = length of the longest string.)
//
// APPROACH (as implemented below, method C)
//   1. Arrays.sort(strs) puts the strings in lexicographic order (in place).
//   2. firstStr = strs[0] is the smallest string, lastStr = strs[strs.length - 1] the largest.
//   3. length = min(firstStr.length(), lastStr.length()) - the prefix cannot be longer.
//   4. Walk l from 0 while firstStr.charAt(l) == lastStr.charAt(l); stop at the first
//      difference or at length.
//   5. Return firstStr.substring(0, l).
//
// DRY RUN   strs = ["flower","flow","flight"]
//   C: sorted = ["flight","flow","flower"]   firstStr = "flight"   lastStr = "flower"
//      length = min(6, 6) = 6
//      l=0 'f' == 'f'    l=1 'l' == 'l'    l=2 'i' != 'o' -> stop with l = 2
//      return "flight".substring(0, 2) = "fl"
//   A: matches = "flower"; vs "flow" -> j = 4 -> "flow"; vs "flight" -> j = 2 -> "fl"
//   B: columns 0 and 1 are the same in all three strings; at column 2 "flight" has 'i' but
//      strs[0] has 'o' -> return "flight".substring(0, 2) = "fl"
//   main() input ["ab","a"]: sorted = ["a","ab"], length = 1, l reaches 1 -> "a"
//
// COMPLEXITY
//   A: Time O(n * m) - each string is compared for at most as many letters as the current
//      candidate is long.  Space O(m) for the substring copies
//   B: Time O(n * m) - at most (shortest length + 1) columns, n comparisons per column.
//      Space O(1) beyond the returned string
//   C: Time O(n * m * log n) - sorting makes O(n log n) string comparisons of up to m
//      letters each; the final scan is O(m).  Space O(n) for the sort's temporary buffer
//
// GOTCHAS
//   - Approach C reorders the caller's array (Arrays.sort works in place): after main()'s
//     call, strs is {"a", "ab"}.
//   - An empty string anywhere gives "" in all three methods; a single string is returned
//     whole.
//   - In B, str.substring(0, i) is safe: every string agrees with strs[0] on columns
//     0 .. i-1, and a string that is too short has length exactly i.
//   - All methods read strs[0], so they assume a non-empty array (the constraints say
//     strs.length >= 1).
//
// STATUS
//   All three methods are correct (checked against a brute force on random inputs).
//   main() runs only the sorting one and prints "a" for ["ab","a"].
//
// SEE ALSO
//   LC14LongestCommonPrefixVerticalScan.java - the vertical scan on its own, building the
//     answer in a StringBuilder
//
// RELATED
//   - LC 208 Implement Trie (Prefix Tree) - the data structure behind a fourth approach
//     (Topics lists Trie): insert every string and walk down while a node has a single child.
//     Not implemented in this file.
// ============================================================================
public class LC14LongestCommonPrefixThreeApproaches {

    public static void main(String[] args) {
        // Approach C only. The common prefix of "ab" and "a" is "a". Expected output: a
        String[] strs = {"ab", "a"};
        System.out.println(longestCommonPrefixSorting(strs));
    }
    // Approach A (horizontal): start from strs[0] and cut it back against each following
    // string. O(n * m)
    public static String longestCommonPrefix(String[] strs) {


        // the answer is a prefix of strs[0], so that is the first candidate
        String matches = strs[0];
        for (int i = 1; i < strs.length; i++) {
            // j = how many leading letters the candidate and strs[i] share
            int j = 0;
            while (j < Math.min(strs[i].length(), matches.length())){
                if (strs[i].charAt(j) != matches.charAt(j)) {
                    break;
                }
                j++;
            }
            // keep only the shared part
            matches = matches.substring(0, j);
        }
        return matches;
    }


    // Approach B (vertical): compare column by column over all strings; the first column where
    // a string ends or differs from strs[0] ends the prefix. O(n * m)
    public static String longestCommonPrefixVertical(String[] strs) {
        String first = strs[0];
        for (int i = 0; i < strs[0].length(); i++) {
            for (String str : strs){
                // str is too short, or has another letter in column i -> the prefix is the
                // first i letters (every string agreed on columns 0 .. i-1)
                if(i >= str.length() || first.charAt(i) != str.charAt(i)){
                    return str.substring(0, i);
                }
            }
        }
        // every column of strs[0] matched in every string
        return first;
    }

    // Approach C (sorting): once sorted, the first and last strings differ the most, so their
    // common prefix is the common prefix of the whole array. O(n * m * log n). It reorders
    // the caller's array.
    public static String longestCommonPrefixSorting(String[] strs) {
        Arrays.sort(strs);
        String firstStr = strs[0];
        String lastStr = strs[strs.length - 1];

        // the shared prefix cannot be longer than the shorter of the two
        int length = Math.min(firstStr.length(), lastStr.length());
        int l = 0;
        // advance while the smallest and the largest string still agree
        while (l < length){
            if(firstStr.charAt(l) != lastStr.charAt(l)){
                break;
            }
            l++;
        }
        // l letters are shared by the two, and therefore by every string
        return firstStr.substring(0, l);
    }
}
