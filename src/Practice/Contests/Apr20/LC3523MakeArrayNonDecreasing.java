package Practice.Contests.Apr20;

// ============================================================================
// LC 3523. Make Array Non-decreasing   [Medium]
// https://leetcode.com/problems/make-array-non-decreasing/
// Topics: Array, Stack, Greedy, Monotonic Stack
// ============================================================================
//
// PROBLEM
//   One operation: pick a contiguous piece (subarray) of nums and replace the whole piece
//   by ONE element equal to the largest value in it. You may do this any number of times,
//   including zero. The finished array must be non-decreasing. Return the largest length
//   it can have.
//
// EXAMPLES
//   nums = [4,2,5,3,5]  ->  3     [2,5] -> 5 gives [4,5,3,5], then [3,5] -> 5 gives [4,5,5]
//   nums = [1,2,3]      ->  3     already non-decreasing, nothing to do
//
// CONSTRAINTS
//   1 <= nums.length <= 2 * 10^5,   1 <= nums[i] <= 2 * 10^5
//
// INTUITION
//   Every element of the final array is the maximum of a block of consecutive original
//   elements, and the block maxima must not decrease left to right. So we want as many
//   blocks as possible. An element that is at least as big as everything before it can
//   open its own block: its maximum is no smaller than the block before. An element that
//   is SMALLER than the maximum so far cannot open a block of its own (the block maximum
//   would drop), so it has to be merged into a neighbouring block, where it disappears
//   without changing that block's maximum. Hence the answer is the number of elements
//   that are >= every element before them.
//
// APPROACH (as implemented below)
//   1. i = 0, count = 0. While i < n, a new group starts at nums[i]; maxInGroup = nums[i].
//   2. j = i + 1; while nums[j] < maxInGroup, that element is swallowed by the group and
//      j++. The group's maximum never changes, because every swallowed element is
//      smaller than it (so the Math.max there is a no-op).
//   3. The group is one element of the final array: count++, then i = j. nums[j] is >= the
//      group's maximum, so it starts the next group.
//   4. Return count.
//
// DRY RUN   main(): nums = [2,27,15]
//   i=0: maxInGroup = 2;  nums[1] = 27 is not < 2   -> group [2],      count = 1, i = 1
//   i=1: maxInGroup = 27; nums[2] = 15 < 27 -> swallowed, j = 3
//                                                   -> group [27,15],  count = 2, i = 3
//   i = 3 = n: stop, return 2      (final array [2,27])
//   Example 1, [4,2,5,3,5], gives the groups [4,2], [5,3], [5] -> 3.
//
// COMPLEXITY
//   Time : O(n) - j only moves forward and i jumps to j, so each element is visited once
//   Space: O(1)
//
// GOTCHAS
//   - The inner test is strict (<): an EQUAL element starts a new group, because equal
//     neighbours are allowed in a non-decreasing array (the two 5s at the end of example 1).
//   - maxInGroup is always just the first element of its group; the Math.max(...) can
//     never change it.
//   - The first element always starts a group, so the answer is at least 1.
//
// RELATED
//   - LC 769 Max Chunks To Make Sorted - also cuts an array into blocks by following the
//     running maximum.
// ============================================================================
public class LC3523MakeArrayNonDecreasing {

    public static void main(String[] args) {
        // prints 2: [2] and [27,15] merged into 27 -> [2,27]
        int[] nums = {2,27,15};
        System.out.println(maximumPossibleSize(nums));
    }

    // Returns the largest possible length of a non-decreasing array made by the merges.
    public static int maximumPossibleSize(int[] nums) {
        int i = 0;
        int count = 0;
        while(i < nums.length){
           // start a group at nums[i]
           int maxInGroup = nums[i];
           int j = i + 1;
           // swallow the following elements that are smaller than the group's maximum
           while(j < nums.length && nums[j] < maxInGroup){
               maxInGroup = Math.max(nums[j], maxInGroup);
               j++;
           }
           // the group becomes one element of the final array; nums[j] (if any) starts the next
           count++;
           i = j;
        }
        return count;
    }
}
