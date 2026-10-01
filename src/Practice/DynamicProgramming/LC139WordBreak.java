package Practice.DynamicProgramming;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// ============================================================================
// LC 139. Word Break   [Medium]
// https://leetcode.com/problems/word-break/
// Topics: Array, Hash Table, String, Dynamic Programming, Trie, Memoization, Brute-Force Search
// ============================================================================
//
// PROBLEM
//   You get a string s and a list of allowed words (wordDict). Decide whether s can be cut
//   into pieces, left to right with nothing left over, so that every piece is one of the
//   allowed words. A word may be used as many times as you like. Return true or false.
//
// EXAMPLES
//   s = "leetcode",      wordDict = ["leet","code"]                    ->  true
//       "leet" + "code"
//   s = "applepenapple", wordDict = ["apple","pen"]                    ->  true
//       "apple" + "pen" + "apple" - the word "apple" is used twice
//   s = "catsandog",     wordDict = ["cats","dog","sand","and","cat"]  ->  false
//       "cats"+"and" and "cat"+"sand" both leave "og" over, and no word matches "og"
//
// CONSTRAINTS
//   1 <= s.length <= 300,   1 <= wordDict.length <= 1000,   1 <= wordDict[i].length <= 20
//   s and wordDict[i] consist of only lowercase English letters
//   All the strings of wordDict are unique
//
// INTUITION
//   Look at the front of the part of s that is still unsplit: some allowed word has to be
//   a prefix of it. Try every word that matches, and the question turns into the SAME
//   question about what is left. What is left is always a suffix of s, so there are only
//   n different questions ("can the suffix starting at index be split?"), one per index.
//   Different paths reach the same index - "cat"+"sand" and "cats"+"and" both arrive at
//   index 7 - so without remembering answers the work repeats and grows exponentially.
//   Caching the answer per index (memoization) makes every suffix cost its work once.
//
// APPROACH (as implemented below)
//   wordBreak(s, wordDict, index, memo) answers: can s.substring(index) be split?
//   1. index == s.length()  -> nothing left, return true.
//   2. memo[index] != null  -> already solved, return the cached answer.
//      (memo is a Boolean[]: null = not computed yet, which differs from a computed false.)
//   3. For every word in wordDict (called compare): if it fits in the rest of s and
//      s.substring(index, index + compare.length()) equals it, recurse on
//      index + compare.length(). If that returns true, cache true and return true.
//   4. No word worked -> cache false and return false.
//   The first call is wordBreak(s, wordDict, 0, new Boolean[s.length()]).
//
// DRY RUN   s = "catsandog" (what main() runs), words cats, dog, sand, and, cat
//   index 0  "cats" matches   -> go to index 4
//     index 4 ("andog")  cats, dog, sand: no;  "and" matches -> go to index 7
//       index 7 ("og")   no word fits or matches  -> memo[7] = false
//     index 4  "cat" does not match "and"        -> memo[4] = false
//   index 0  "dog", "sand", "and" do not match;  "cat" matches -> go to index 3
//     index 3 ("sandog") "sand" matches -> index 7: memo[7] is already false, no rework
//     index 3  the remaining words fail          -> memo[3] = false
//   index 0  nothing left to try                 -> memo[0] = false, prints false
//
// STATUS
//   The signature differs from LeetCode's wordBreak(String s, List<String> wordDict): the
//   extra parameters index and memo are filled in by main() (0 and a fresh Boolean[]).
//   Wrap it in a two-argument method before submitting.
//
// COMPLEXITY
//   Time : O(n * m * L) - n = s.length() indexes are solved once each; every index scans
//          the m dictionary words, and a substring + equals check costs O(L) (L = longest word)
//   Space: O(n) - the memo array; the recursion is at most n frames deep, since every call
//          moves index forward by at least one character
//
// GOTCHAS
//   - The length check index + compare.length() <= s.length() comes BEFORE substring(),
//     otherwise a long word near the end of s throws StringIndexOutOfBoundsException.
//   - "return memo[index] = true;" assigns the cache entry and returns it in one step.
//   - wordDict is a List scanned linearly. A HashSet of words would let you try only the
//     prefixes of s up to length 20 instead of all m words.
//
// RELATED
//   - LC 140 Word Break II - same idea, but it must return every valid sentence (the
//     "print all ways" version), not just true or false.
//   - LC 2707 Extra Characters in a String - the same suffix DP over a dictionary, but it
//     minimises the characters left unmatched.
// ============================================================================
public class LC139WordBreak {
    public static void main(String[] args) {
        // Example 3: "cats"/"cat" leave "og" that no word covers. Expected output: false
        String s = "catsandog";
        List<String> wordDict = Arrays.asList("cats","dog","sand","and","cat");
        System.out.println(wordBreak(s, wordDict, 0, new Boolean[s.length()]));
    }
    // Can s.substring(index) be split into dictionary words?
    // memo[index]: null = not solved yet, otherwise the answer for the suffix at index.
    public static boolean wordBreak(String s, List<String> wordDict, int index, Boolean[] memo) {
        // the whole string has been used up exactly: success
        if(index == s.length()){
            return true;
        }
        // this suffix was solved before (reached by another path) - reuse that answer
        if(memo[index] != null){
            return memo[index];
        }
        // try every dictionary word as the next piece of s
        for (int i = 0; i < wordDict.size(); i++){
            String compare = wordDict.get(i);
            // the word must fit into what is left of s
            if (index + compare.length() <= s.length()) {
                String subString = s.substring(index, index + compare.length());
                // it matches here -> the rest of s must be splittable as well
                if (subString.equals(compare)) {
                    if (wordBreak(s, wordDict, index + compare.length(), memo)) {
                        return memo[index] = true;
                    }
                }
            }
        }
        // no word leads to a full split from this index
        return memo[index] = false;
    }
}
