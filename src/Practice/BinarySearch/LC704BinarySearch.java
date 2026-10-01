package Practice.BinarySearch;

// ============================================================================
// LC 704. Binary Search   [Easy]
// https://leetcode.com/problems/binary-search/
// Topics: Array, Binary Search
// ============================================================================
//
// PROBLEM
//   nums is sorted in ascending order and has no repeated values. Return the index of
//   target in nums, or -1 if target is not there. It must run in O(log n) time.
//
// EXAMPLES
//   nums = [-1,0,3,5,9,12], target = 9  ->  4     9 sits at index 4
//   nums = [-1,0,3,5,9,12], target = 2  ->  -1    2 is not in the array
//
// CONSTRAINTS
//   1 <= nums.length <= 10^4,   -10^4 < nums[i], target < 10^4,
//   all values unique,   nums sorted ascending
//
// INTUITION
//   Because the array is sorted, one comparison with the middle element rules out half of
//   it: if target is smaller than the middle value, everything from the middle to the
//   right is too big; if it is larger, everything from the left up to the middle is too
//   small. Keep the window [left, right] that can still hold target, halve it every
//   round, and stop when it is found or the window is empty. That is about log2(n) rounds.
//
// APPROACH (as implemented below)
//   1. left = 0, right = n - 1, and the first probe half = (left + right) / 2 is
//      computed before the loop.
//   2. While left <= right, compare target with nums[half]:
//        target < nums[half]  -> right = half - 1
//        target > nums[half]  -> left = half + 1
//        target == nums[half] -> return half
//   3. At the end of each round the next probe is half = (right + left) / 2.
//   4. The window is empty (left > right): return -1.
//
// DRY RUN   nums = [-1,0,3,5,9,12]
//   target = 9 (example 1):  half=2 (3):  9 > 3 -> left = 3
//                            half=4 (9):  equal -> return 4
//   target = 13 (main):      half=2 (3):  13 > 3 -> left = 3
//                            half=4 (9):  13 > 9 -> left = 5
//                            half=5 (12): 13 > 12 -> left = 6
//                            left 6 > right 5 -> return -1
//
// STATUS
//   Correct. main() searches for 13, which is not in the array, and discards the result,
//   so nothing is printed (the method returns -1).
//
// COMPLEXITY
//   Time : O(log n) - the window halves every round
//   Space: O(1)
//
// GOTCHAS
//   - The loop condition is left <= right: a window of one element still has to be checked.
//   - half is recomputed at the END of each round (and once before the loop) instead of
//     at the top. The behaviour is the same as recomputing it first thing in the loop.
//   - (left + right) / 2 could overflow int for arrays of more than 2^30 elements;
//     left + (right - left) / 2 is the safe form. It cannot happen here (n <= 10^4).
//   - The last branch (target == nums[half]) is always true when it is reached, because
//     the two comparisons before it have already failed.
//
// RELATED
//   - LC 33 Search in Rotated Sorted Array (in this repo) - the same search when the sorted
//     array has been rotated.
//   - LC 74 Search a 2D Matrix (in this repo) - binary search applied to a sorted matrix.
// ============================================================================
public class LC704BinarySearch {
    public static void main(String[] args) {
        // target 13 is not in the array: search returns -1, but the result is discarded,
        // so nothing is printed (see STATUS)
        int[] nums = {-1,0,3,5,9,12};
        int target = 13;
        search(nums, target);
    }

    // Returns the index of target in the sorted array nums, or -1 if it is absent.
    public static int search(int[] nums, int target) {

        // window of positions that can still hold target, and the first probe
        int right = nums.length - 1;
        int left = 0;
        int half = (left + right) / 2;

        while(left <= right){
            if(target < nums[half]){
                // target is smaller: drop the middle and everything to its right
                right = half - 1;
            }else if (target > nums[half]){
                // target is larger: drop the middle and everything to its left
                left = half + 1;
            }else if (target == nums[half]){
                return half;
            }
            // next probe: middle of the smaller window
            half = (right + left)/2;
        }
        // window is empty: target is not in the array
        return -1;
    }
}
