package Practice.Greedy;

import java.util.*;

// ============================================================================
// LC 3085. Minimum Deletions to Make String K-Special   [Medium]
// https://leetcode.com/problems/minimum-deletions-to-make-string-k-special/
// Topics: Hash Table, String, Greedy, Sorting, Counting
// ============================================================================
//
// PROBLEM
//   word is "k-special" when any two letters that appear in it have frequencies differing by
//   at most k: |freq(x) - freq(y)| <= k. Given word and k, return the fewest characters you
//   must delete from word to make it k-special.
//
// EXAMPLES
//   word = "aabcaba", k = 0       ->  3     frequencies a=4, b=2, c=1; delete two a's and the c
//                                           to get "baba" (a=2, b=2)
//   word = "dabdcbdcdcd", k = 2   ->  2     delete the a and one d -> b=2, c=3, d=4
//   word = "aaabaaa", k = 2       ->  1     delete the b -> "aaaaaa"
//
// CONSTRAINTS
//   1 <= word.length <= 10^5,   0 <= k <= 10^5,   word has only lowercase English letters
//
// INTUITION
//   Only the letter counts matter. Suppose the smallest frequency that survives is t. Then
//     - a letter with fewer than t occurrences cannot stay (it would drag the minimum below
//       t), so all of it is deleted;
//     - a letter with t .. t+k occurrences is fine as it is, no deletions;
//     - a letter with more than t+k occurrences is trimmed down to t+k, so we delete the
//       excess comp - (t + k).
//   The best t is always one of the original counts (cutting a kept letter below its own
//   count just to lower t never helps), and there are at most 26 distinct letters, so simply
//   try every count as t and keep the cheapest.
//
// APPROACH (as implemented below)
//   1. Count each letter into the map `freq`, then copy the counts into the list `frequency`.
//   2. For every count in the list, call it `target` (the smallest kept frequency) and start
//      deletions = 0.
//   3. For every count `comp` in the list:
//        target > comp         -> delete the whole letter: deletions += comp
//        target + k < comp     -> trim it: deletions += comp - (target + k)
//        otherwise             -> no deletions
//   4. ans = min(ans, deletions) over all targets; return ans.
//
// DRY RUN   word = "aabcaba", k = 0   (counts a=4, b=2, c=1; sorted list [1, 2, 4])
//   target = 1:  c=1 ok, b=2 -> 2-1 = 1, a=4 -> 4-1 = 3                 deletions 4
//   target = 2:  c=1 < 2 -> 1, b=2 ok, a=4 -> 4-2 = 2                   deletions 3
//   target = 4:  c=1 < 4 -> 1, b=2 < 4 -> 2, a=4 ok                     deletions 3
//   ans = min(4, 3, 3) = 3
//
// COMPLEXITY
//   Time : O(n + m^2) with m = number of distinct letters (at most 26) - O(n) to count, then
//          m targets times m letters; in practice O(n).
//   Space: O(m) - the map and the list of counts.
//
// GOTCHAS
//   - Letters that do not occur in word are not in the map, so they never count as
//     "frequency 0" and never force deletions.
//   - Collections.sort(frequency) is not needed by the algorithm (every target is tried
//     against every count); it only makes the order tidy.
//   - Equal counts are tried more than once; that is harmless.
//   - With k >= the spread of the counts the answer is 0.
//
// RELATED
//   - LC 1647 Minimum Deletions to Make Character Frequencies Unique - also deletes
//     characters to fix the letter-frequency profile.
// ============================================================================
public class LC3085MinimumDeletionsToMakeStringKSpecial {
    public static void main(String[] args) {
        // Example 1 - expected output: 3
        String word = "aabcaba";
        int k = 0;
        System.out.println(minimumDeletions(word, k));
    }
    // Returns the fewest deletions that make every pair of letter counts differ by <= k.
    public static int minimumDeletions(String word, int k) {
        int deletions;
        int ans = Integer.MAX_VALUE;
        // step 1: count the occurrences of every letter
        Map<Character, Integer> freq = new HashMap<>();
        for(Character c : word.toCharArray()){
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }
        List<Integer> frequency = new ArrayList<>(freq.values());

        Collections.sort(frequency);

        // step 2: try each existing count as the smallest count that is kept
        for (int i = 0; i < frequency.size(); i++) {
            int target = frequency.get(i);
            deletions = 0;
            for (int comp : frequency) {
                if (target > comp) {
                    // too rare to stay: delete the whole letter
                    deletions += comp;
                } else if (target + k < comp) {
                    // too common: cut it down to target + k
                    deletions += comp - (target + k);
                }
            }
            ans = Math.min(ans, deletions);
        }
        return ans;
    }
}
