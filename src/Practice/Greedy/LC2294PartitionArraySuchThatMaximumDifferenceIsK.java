package Practice.Greedy;

import java.util.Arrays;

// ============================================================================
// LC 2294. Partition Array Such That Maximum Difference Is K   [Medium]
// https://leetcode.com/problems/partition-array-such-that-maximum-difference-is-k/
// Topics: Array, Greedy, Sorting
// ============================================================================
//
// PROBLEM
//   Split the elements of nums into one or more subsequences (every element goes into
//   exactly one). In each subsequence the largest value minus the smallest value may be at
//   most k. Return the smallest number of subsequences that makes this possible.
//
// EXAMPLES
//   nums = [3,6,1,2,5], k = 2  ->  2     groups [3,1,2] (spread 2) and [6,5] (spread 1)
//   nums = [1,2,3], k = 1      ->  2     [1,2] and [3]
//   nums = [2,2,4,5], k = 0    ->  3     only equal values may share a group: [2,2] [4] [5]
//
// CONSTRAINTS
//   1 <= nums.length <= 10^5,   0 <= nums[i] <= 10^5,   0 <= k <= 10^5
//
// INTUITION
//   Any set of elements can be taken as a subsequence, so the positions do not matter - only
//   the values do, and a group is fully described by its smallest and largest value. On the
//   number line that means covering all the values with as few "windows" of width k as
//   possible. Sort the values, open a window at the smallest value not covered yet, and let
//   it swallow everything up to that value + k. Starting at the smallest uncovered value
//   reaches furthest to the right, so no other choice can need fewer windows.
//
// APPROACH (as implemented below)
//   1. Sort nums.
//   2. min = nums[0] is the smallest value of the current group; cnt counts the groups
//      started AFTER the first one.
//   3. For i = 1 .. n-1: if nums[i] - min > k, nums[i] cannot join the current group (it is
//      the largest value so far) -> cnt++ and min = nums[i], it opens a new group.
//      Otherwise it joins the current group and nothing changes.
//   4. Return cnt + 1 (the first group was never counted).
//
// DRY RUN   nums = [1,2,3,0], k = 2   (main()'s data; sorted: [0,1,2,3])
//   start   min = 0, cnt = 0
//   i=1     1 - 0 = 1 > 2? no
//   i=2     2 - 0 = 2 > 2? no       (a spread of exactly k is allowed)
//   i=3     3 - 0 = 3 > 2? yes      -> cnt = 1, min = 3
//   return cnt + 1 = 2              groups [0,1,2] and [3]
//
// COMPLEXITY
//   Time : O(n log n) - the sort; the scan is O(n).
//   Space: O(1) extra - the array is sorted in place (the sort's own working space aside).
//
// GOTCHAS
//   - The test is a strict `> k`: a value exactly k above the group's minimum still fits.
//   - Return cnt + 1, not cnt - the loop only counts groups that start after the first.
//   - Arrays.sort reorders the caller's array.
//   - nums[0] is read without a guard; the constraints guarantee at least one element.
//
// STATUS
//   The code is correct (it gives 2, 2 and 3 for the three examples). main() calls
//   partitionArray() but does not print the result (it is 2 for its data).
//
// RELATED
//   - LC 2779 Maximum Beauty of an Array After Applying Operation - also sorts, then looks
//     at a window of values whose spread is bounded.
//   - LC 2966 Divide Array Into Arrays With Max Difference - sort and cut into groups of
//     three with a bounded spread (solved in
//     src/Practice/Sorting/LC2966DivideArrayIntoArraysWithMaxDifference.java).
// ============================================================================
public class LC2294PartitionArraySuchThatMaximumDifferenceIsK {
    public static void main(String[] args) {
        // Sorted this is [0,1,2,3]: groups [0,1,2] and [3], so the result is 2
        // (the returned value is not printed).
        int[] nums = {1,2,3,0};
        int k = 2;
        partitionArray(nums, k);
    }
    // Returns the fewest groups such that every group's max - min is at most k.
    public static int partitionArray(int[] nums, int k) {
        // step 1: sorted, each group becomes a run of neighbouring values
        Arrays.sort(nums);
        // cnt = groups opened after the first; min = smallest value of the current group
        int cnt = 0;
        int min = nums[0];
        for(int i = 1 ; i < nums.length; i++){
            // too far above the group's minimum -> start a new group at this value
            if(nums[i] - min  > k){
                cnt++;
                min = nums[i];
            }
        }
        // + 1 for the first group
        return cnt + 1;
    }
}
