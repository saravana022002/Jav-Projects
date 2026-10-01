package Practice.Contests.Contest169;

// ============================================================================
// LC 3737. Count Subarrays With Majority Element I   [Medium]
// https://leetcode.com/problems/count-subarrays-with-majority-element-i/
// Topics: Array, Hash Table, Divide and Conquer, Segment Tree, Merge Sort, Counting, Prefix Sum
// ============================================================================
//
// PROBLEM
//   Given an array nums and a number target, count the subarrays (contiguous, non-empty)
//   in which target is the MAJORITY element: it appears strictly more than half of the
//   times, i.e. in more than half of the subarray's positions.
//
// EXAMPLES
//   nums = [1,2,2,3], target = 2  ->  5     [2], [2], [2,2], [1,2,2], [2,2,3]
//   nums = [1,1,1,1], target = 1  ->  10    every one of the 10 subarrays qualifies
//   nums = [1,2,3],   target = 4  ->  0     target never occurs
//
// CONSTRAINTS
//   1 <= nums.length <= 1000,   1 <= nums[i] <= 10^9,   1 <= target <= 10^9
//
// INTUITION
//   With n <= 1000 there are at most n(n+1)/2, about 500 000, subarrays, so every one can
//   be checked. Fix the start i and grow the end j one element at a time: the number of
//   target's inside is just the previous count plus 1 when the new element equals target,
//   so nothing is ever recounted. The subarray has size j - i + 1, and target is a strict
//   majority exactly when 2 * count > size.
//
// APPROACH (as implemented below)
//   1. ans = 0.
//   2. For every start i: count = 0 (number of target's in nums[i..j]).
//   3. For every end j from i to n - 1: size = j - i + 1; if nums[j] == target then
//      count++; if size / 2 < count then ans++.
//   4. Return ans.
//
// DRY RUN   nums = [1,2,2,3], target = 2   (example 1)
//   i=0: [1] count 0 no | [1,2] size 2 count 1 (needs >1) no | [1,2,2] size 3 count 2 YES (1)
//        | [1,2,2,3] size 4 count 2 no
//   i=1: [2] YES (2) | [2,2] YES (3) | [2,2,3] size 3 count 2 YES (4)
//   i=2: [2] YES (5) | [2,3] size 2 count 1 no
//   i=3: [3] count 0 no
//   ans = 5
//
// STATUS
//   main() calls countMajoritySubarrays but does not print the result, so running the file
//   shows nothing (for its input, [1,2,3] with target 4, the result is 0).
//
// COMPLEXITY
//   Time : O(n^2) - one O(1) update for each of the n(n+1)/2 subarrays
//   Space: O(1)
//
// GOTCHAS
//   - size / 2 < count uses integer division and is the same as 2 * count > size: for
//     size 2t or 2t + 1 both need count >= t + 1. Exactly half is NOT a majority ([1,2]
//     has one 2 out of two, so it does not count).
//   - count must be reset to 0 for every new start i.
//   - A one-element subarray equal to target counts (0 < 1).
//   - Only target's majority matters; another element may also be a majority elsewhere.
//
// RELATED
//   - LC 169 Majority Element (in this repo) - the same "strictly more than half" rule,
//     asked for the whole array.
//   - LC 560 Subarray Sum Equals K - counts subarrays through running totals; the
//     Prefix Sum tag here points to the same tool for larger inputs.
// ============================================================================
public class LC3737CountSubarraysWithMajorityElementI {
    public static void main(String[] args) {
        // Example 3 (target 4 is not in the array): the result 0 is not printed
        int[] nums = {1,2,3};
        int target = 4;
        countMajoritySubarrays(nums, target);
    }

    // Returns the number of subarrays in which target is a strict majority.
    public static int countMajoritySubarrays(int[] nums, int target) {
        int ans = 0;
        int n = nums.length;
        int count = 0;
        // i = start of the subarray
        for(int i = 0; i < n; i++){
            // number of targets in nums[i..j], restarted for each start
            count = 0;
            // j = end of the subarray, extended one element at a time
            for (int j = i; j < n; j++) {
                int size = j - i + 1;
                if(nums[j] == target){
                    count++;
                }
                // count > size / 2, i.e. more than half of the positions hold target
                if (size / 2 < count){
                    ans++;
                }
            }
        }
        return ans;
    }



}
