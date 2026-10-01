package Practice.Backtracking;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// ============================================================================
// LC 131. Palindrome Partitioning   [Medium]
// https://leetcode.com/problems/palindrome-partitioning/
// Topics: String, Dynamic Programming, Backtracking
// ============================================================================
//
// PROBLEM
//   Cut the string s into consecutive pieces so that EVERY piece reads the same forwards
//   and backwards (is a palindrome). Return all the different ways to do this; each way
//   is a list of the pieces in order. Any order of the ways is accepted.
//
// EXAMPLES
//   s = "aab"  ->  [["a","a","b"],["aa","b"]]   "aab" and "ab" are not palindromes, so
//                                               these are the only two cuts
//   s = "a"    ->  [["a"]]
//
// CONSTRAINTS
//   1 <= s.length <= 16,   s contains only lowercase English letters
//
// INTUITION
//   Decide the pieces from left to right. Standing at index i, the next piece is some
//   s[i..j]; it is only allowed if it is a palindrome, and then the rest s[j+1..] is the
//   same problem again, just smaller. Trying every allowed j, recursing, and then taking
//   the piece back off (backtracking) walks through every valid partition exactly once.
//   A single letter is always a palindrome, so at least one partition always exists.
//
// APPROACH (as implemented below)
//   1. partition() sets s = "aabb" and starts partitionSubStr(s, 0, part = [], res = []).
//   2. partitionSubStr(s, i, ...): if i >= s.length() the whole string is used up, so add
//      a copy of part to res and return.
//   3. Otherwise for j = i .. n-1: if isPalindrome(s.substring(i, j + 1)), add that piece
//      to part, recurse with i = j + 1, then remove the piece again.
//   4. isPalindrome() compares characters with two pointers moving inward.
//
// DRY RUN   s = "aabb" (main's input)
//   i=0  "a"  ok -> i=1  "a" ok -> i=2  "b" ok -> i=3  "b" ok -> i=4  save [a,a,b,b]
//                                       "bb" ok -> i=4  save [a,a,bb]
//                        "ab", "abb" are not palindromes -> skipped
//        "aa" ok -> i=2  "b" ok -> i=3  "b" ok -> save [aa,b,b]
//                        "bb" ok -> save [aa,bb]
//        "aab", "aabb" are not palindromes -> skipped
//   res = [[a, a, b, b], [a, a, bb], [aa, b, b], [aa, bb]]
//
// STATUS
//   Signature differs from LeetCode: partition() takes no argument - the input "aabb" is
//   hard-coded inside it (LeetCode: partition(String s)) - and it prints the result
//   itself before returning it. The Collections import is unused.
//
// COMPLEXITY
//   Time : O(n * 2^n) - each of the n-1 gaps between letters is either a cut or not, so
//          there are at most 2^(n-1) partitions (all of them valid for "aaaa..."), and
//          copying each finished partition into res costs O(n). The substring + palindrome
//          checks inside the loop add work of the same order.
//   Space: O(n) - recursion depth and part (at most n pieces), not counting res.
//
// GOTCHAS
//   - res.add(new ArrayList<>(part)) must COPY part; part keeps changing while the search
//     backtracks, so storing part itself would leave res full of emptied lists.
//   - The palindrome test is redone from scratch for every substring. A precomputed
//     isPal[i][j] table (isPal[i][j] = s[i]==s[j] && isPal[i+1][j-1]) makes each test O(1).
//   - s.substring(i, j + 1) is built twice per kept piece (test + add); harmless here.
//
// RELATED
//   - LC 132 Palindrome Partitioning II - only the minimum number of cuts is wanted, so
//     DP replaces listing every partition.
//   - LC 5 Longest Palindromic Substring (in this repo) - the same palindrome checks; its
//     DP table is the speed-up mentioned in GOTCHAS.
// ============================================================================
public class LC131PalindromePartitioning {
    public static void main(String[] args) {
        // partition() prints its own result for "aabb":
        // [[a, a, b, b], [a, a, bb], [aa, b, b], [aa, bb]]
        partition();
    }

    // LeetCode's partition(String s), but with the input hard-coded (see STATUS).
    public static List<List<String>> partition() {
        String s = "aabb";
        List<List<String>> res = new ArrayList<>();
        List<String> part = new ArrayList<>();
        partitionSubStr(s, 0,  part, res);
        System.out.println(res);
        return res;
    }

    // Adds to res every way to split s[i..] into palindromes, each after the pieces in part.
    static void partitionSubStr(String s, int i, List<String> part, List<List<String>> res){
        // whole string consumed: part is one complete partition - store a copy
        if(i >= s.length()){
            res.add(new ArrayList<>(part));
            return;
        }
        // try every possible next piece s[i..j]; only a palindrome may be cut off
        for (int j = i; j < s.length(); j++) {
            if(isPalindrome(s.substring(i, j + 1))){
                // choose the piece, partition the rest, then undo the choice
                part.add(s.substring(i, j + 1));
                partitionSubStr(s, j + 1, part, res);
                part.remove(part.size() - 1);
            }
        }
    }

    // Two pointers walk inward from both ends; any mismatch means "not a palindrome".
    public static boolean isPalindrome(String s) {
        int left = 0;
        int right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }


}
