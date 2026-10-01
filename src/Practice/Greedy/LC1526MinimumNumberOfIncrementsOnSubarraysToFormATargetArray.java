package Practice.Greedy;

// ============================================================================
// LC 1526. Minimum Number of Increments on Subarrays to Form a Target Array   [Hard]
// https://leetcode.com/problems/minimum-number-of-increments-on-subarrays-to-form-a-target-array/
// Topics: Array, Dynamic Programming, Stack, Greedy, Monotonic Stack
// ============================================================================
//
// PROBLEM
//   You start from an array of zeros as long as target. One operation picks any contiguous
//   subarray and adds 1 to every element in it. Return the fewest operations needed to turn
//   the zeros into target. (The answer is guaranteed to fit in a 32-bit int.)
//
// EXAMPLES
//   target = [1,2,3,2,1]  ->  3     raise everything once, then indices 1..3, then index 2
//   target = [3,1,1,2]    ->  4     raise everything once, then index 0 twice more and
//                                   index 3 once more
//   target = [3,1,5,4,2]  ->  7
//
// CONSTRAINTS
//   1 <= target.length <= 10^5,   1 <= target[i] <= 10^5
//   the answer fits in a 32-bit integer
//
// INTUITION
//   Picture target as a skyline and every operation as a flat strip laid over a run of
//   neighbouring columns. Count each strip at the column where it STARTS.
//     - Column 0 starts from nothing, so target[0] strips must start there.
//     - At column i, up to target[i-1] strips coming from the left can simply continue
//       into it. Only if target[i] is taller do we need extra strips, and exactly
//       target[i] - target[i-1] of them must start here.
//     - If target[i] is not taller, no new strip is needed (strips can just end early).
//   So the minimum is target[0] plus every positive step up between neighbours. It cannot be
//   lower, because that many strips are forced to start, and it is achievable by letting
//   strips continue as far right as they can.
//
// APPROACH (as implemented below)
//   1. Empty array -> return 0 (a safety guard; LeetCode never sends an empty array).
//   2. ans = target[0]: the first column needs that many new strips.
//   3. For i = 1 .. n-1: if target[i-1] < target[i], add the rise target[i] - target[i-1].
//   4. Return ans.
//
// DRY RUN   target = [3,1,5,4,2]  (example 3)
//   start  ans = 3
//   i=1    3 < 1? no            ans = 3
//   i=2    1 < 5? yes, +4       ans = 7
//   i=3    5 < 4? no            ans = 7
//   i=4    4 < 2? no            ans = 7        -> 7
//
// COMPLEXITY
//   Time : O(n) - one pass over the array
//   Space: O(1) - a single counter
//
// GOTCHAS
//   - Drops in height cost nothing: a strip can just stop, so only rises are added.
//   - ans is an int; LeetCode guarantees the result fits in 32 bits. On arbitrary data
//     (say 1, 100000, 1, 100000, ...) the sum of rises could exceed it and a long would be
//     needed.
//
// STATUS
//   The method is correct (it gives 3, 4 and 7 for the three examples). main() feeds it an
//   empty array, which LeetCode never does (length >= 1); the guard makes it print 0.
//
// RELATED
//   - LC 135 Candy (LC135Candy.java in this folder) - also solved by comparing each element
//     with its neighbours instead of looking at the array as a whole.
// ============================================================================
public class LC1526MinimumNumberOfIncrementsOnSubarraysToFormATargetArray {
    public static void main(String[] args) {
        // Empty array (not a valid LeetCode input): prints 0 thanks to the length == 0 guard.
        int[] target = {};
        System.out.println(minNumberOperations(target));
    }


    // Returns the fewest subarray +1 operations that build target from zeros.
    public static int minNumberOperations(int[] target) {
        if(target.length == 0){
            return 0;
        }
        // the first column starts with target[0] brand-new strips
        int ans = target[0];
        for (int i = 1; i < target.length; i++) {
            // a taller column than its left neighbour needs that many extra strips to start here
            if(target[i - 1] < target[i]){
                ans += target[i] - target[i - 1];
            }
        }
        return ans;
    }

}
