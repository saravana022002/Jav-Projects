package Practice.Backtracking;

import java.util.ArrayList;
import java.util.List;

// ============================================================================
// LC 39. Combination Sum   [Medium]
// https://leetcode.com/problems/combination-sum/
// Topics: Array, Backtracking
// ============================================================================
//
// PROBLEM
//   candidates holds DISTINCT positive integers. Find every combination of them whose sum
//   is exactly target, where each number may be used any number of times. Two
//   combinations count as different only if some number is used a different number of
//   times - so [2,2,3] and [3,2,2] are the same one. Return them in any order.
//
// EXAMPLES
//   candidates = [2,3,6,7], target = 7  ->  [[2,2,3],[7]]      2 is used twice
//   candidates = [2,3,5],   target = 8  ->  [[2,2,2,2],[2,3,3],[3,5]]
//   candidates = [2],       target = 1  ->  []                 nothing adds up to 1
//
// CONSTRAINTS
//   1 <= candidates.length <= 30,   2 <= candidates[i] <= 40,   all candidates distinct,
//   1 <= target <= 40,   fewer than 150 answers per input
//
// INTUITION
//   For every candidate we must decide how many copies to use. The recursion decides it
//   one copy at a time: either "take one more candidates[index]" (stay on the same index,
//   so it can be taken again) or "done with this number" (move to index + 1 and never
//   come back). Since the index only moves forward, each combination is built in exactly
//   one order, so [2,2,3] is never produced again as [2,3,2]. All numbers are positive,
//   so once sum reaches target any further pick can only overshoot - stop there.
//
// APPROACH (as implemented below)
//   1. combinationSum(res, ans, candidates, target, sum, index): ans = numbers picked so
//      far, sum = their total, index = the candidate being decided.
//   2. If sum >= target or index == candidates.length, stop; save a copy of ans to res
//      only when sum == target.
//   3. PICK: add candidates[index] to ans, recurse with sum + candidates[index] and the
//      SAME index, then remove it again (backtrack).
//   4. SKIP: recurse with the same sum and index + 1.
//
// DRY RUN   candidates = [2,3,6,7], target = 7 (main's input)
//   pick 2, pick 2, pick 2          sum 6 -> every further pick overshoots (8, 9, 12, 13)
//   pick 2, pick 2, skip, pick 3    sum 7 -> save [2, 2, 3]
//   pick 2, skip, pick 3            sum 5 -> 3, 6, 7 all overshoot
//   skip, pick 3, pick 3            sum 6 -> 3, 6, 7 all overshoot
//   skip, skip, pick 6              sum 6 -> 6, 7 overshoot
//   skip, skip, skip, pick 7        sum 7 -> save [7]
//   every other branch overshoots 7 or runs past the last index
//   res = [[2, 2, 3], [7]]
//
// STATUS
//   Signature differs from LeetCode (combinationSum(int[] candidates, int target) that
//   returns the list): here the caller passes res, ans, sum = 0, index = 0 and reads res.
//
// COMPLEXITY
//   Time : exponential. Each call picks or skips, and a root-to-leaf path holds at most
//          T/M picks and n skips (T = target, M = smallest candidate, n = length), so
//          O(2^(n + T/M)) calls as a loose bound, plus O(T/M) to copy each answer. The
//          sum >= target stop keeps the real tree much smaller.
//   Space: O(n + T/M) - recursion depth; ans holds at most T/M numbers (output extra).
//
// GOTCHAS
//   - PICK keeps index (unlimited reuse). Passing index + 1 there would allow each number
//     only once, which is a different problem (LC 40).
//   - The early stop at sum >= target is only safe because every candidate is positive.
//   - res.add(new ArrayList<>(ans)) must copy, because ans keeps changing afterwards.
//
// RELATED
//   - LC 40 Combination Sum II - each number at most once and the input may repeat, so
//     equal values must be skipped to avoid duplicate answers.
//   - LC 78 Subsets (in this repo) - the same pick / not-pick recursion without a target.
// ============================================================================
public class LC39CombinationSum {
    public static void main(String[] args) {
        // Example 1. Expected output: [[2, 2, 3], [7]]
        int[] candidates = {2,3,6,7};
        int target = 7;
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        combinationSum(res, ans, candidates, target, 0, 0);
        System.out.println(res);
    }
    // Adds to res every way to reach target by extending ans with candidates[index..].
    public static void combinationSum(List<List<Integer>> res, List<Integer> ans, int[] candidates, int target, int sum, int index) {
        // stop when the sum is reached / passed or no candidate is left;
        // only an exact hit is an answer (stored as a copy)
        if(sum >= target || index == candidates.length){
            if(sum == target){
                res.add(new ArrayList<>(ans));
            }
            return;
        }
        // pick one more copy of candidates[index]; the SAME index allows reusing it
        ans.add(candidates[index]);
        combinationSum(res, ans, candidates, target, sum + candidates[index], index);
        ans.remove(ans.size() - 1);
        // skip: never use candidates[index] again on this path
        combinationSum(res, ans, candidates, target, sum, index + 1);
    }

}
