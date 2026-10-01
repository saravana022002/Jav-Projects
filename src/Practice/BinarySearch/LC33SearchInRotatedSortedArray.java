package Practice.BinarySearch;

// ============================================================================
// LC 33. Search in Rotated Sorted Array   [Medium]
// https://leetcode.com/problems/search-in-rotated-sorted-array/
// Topics: Array, Binary Search
// ============================================================================
//
// PROBLEM
//   nums was sorted ascending with all-different values and then possibly rotated left by
//   an unknown amount, e.g. [0,1,2,4,5,6,7] can become [4,5,6,7,0,1,2]. Given the rotated
//   array and a target, return the index where target sits, or -1 if it is not there. The
//   algorithm must run in O(log n).
//
// EXAMPLES
//   nums = [4,5,6,7,0,1,2], target = 0  ->  4
//   nums = [4,5,6,7,0,1,2], target = 3  ->  -1     3 is not in the array
//   nums = [1],             target = 0  ->  -1
//
// CONSTRAINTS
//   1 <= nums.length <= 5000,   -10^4 <= nums[i] <= 10^4,   all values unique,
//   nums is ascending but possibly rotated,   -10^4 <= target <= 10^4
//
// INTUITION
//   A rotated sorted array is two sorted runs glued together. Cut it anywhere at mid: the
//   "drop" from the largest to the smallest value can be in only one of the two halves,
//   so AT LEAST ONE HALF IS ALWAYS PLAIN SORTED. A sorted half is easy to reason about:
//   compare target with its two end values and you know for certain whether target can be
//   inside it. If yes, keep that half; if not, it must be in the other half. Either way
//   half of the array is discarded each round, which is the O(log n) we need.
//
// APPROACH (as implemented below)
//   1. left = 0, right = n - 1. While left <= right: mid = (left + right) / 2.
//   2. nums[mid] == target -> return mid.
//   3. nums[left] <= nums[mid]: the LEFT half [left..mid] is sorted.
//        nums[left] <= target <= nums[mid] -> target is in it: right = mid - 1
//        otherwise                         -> it must be on the right: left = mid + 1
//   4. Else the RIGHT half [mid..right] is sorted.
//        nums[mid] <= target <= nums[right] -> target is in it: left = mid + 1
//        otherwise                          -> it must be on the left: right = mid - 1
//   5. The window became empty: return -1.
//
// DRY RUN   nums = [4,5,6,7,0,1,2], target = 0
//   left=0 right=6 mid=3 (7):  4 <= 7, left half [4,5,6,7] is sorted; 0 is not in 4..7
//                              -> left = 4
//   left=4 right=6 mid=5 (1):  0 <= 1, left half [0,1] is sorted; 0 is in 0..1
//                              -> right = 4
//   left=4 right=4 mid=4 (0):  equals target -> return 4
//
// COMPLEXITY
//   Time : O(log n) - the window halves every round
//   Space: O(1)
//
// GOTCHAS
//   - The test is nums[left] <= nums[mid] with <=: when left == mid the "half" is a single
//     element, which counts as sorted.
//   - Values must be DISTINCT. With duplicates nums[left] == nums[mid] no longer proves
//     that the left half is sorted (LC 81), so this code would need extra handling.
//   - A rotation by 0 (plain sorted array) needs no special case: the left half is
//     then always the sorted one.
//
// RELATED
//   - LC 81 Search in Rotated Sorted Array II - the same search when duplicates are allowed.
//   - LC 153 Find Minimum in Rotated Sorted Array - finds where the rotation happened.
//   - LC 704 Binary Search (in this repo) - the plain version on an unrotated array.
// ============================================================================
public class LC33SearchInRotatedSortedArray {
    public static void main(String[] args) {
        // Example 1 - prints 4
        int[] nums = {4,5,6,7,0,1,2};
        int target = 0;
        System.out.println(search(nums, target));
    }
    // Returns the index of target in the rotated sorted array, or -1 if it is absent.
    public static int search(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;
        int mid;
        while(left <= right){
            mid = (left + right) / 2;

            if(nums[mid] == target){
                return mid;
            }

            // left half [left..mid] is sorted: keep it only if target lies within its range
            if(nums[left] <= nums[mid]){
                if(nums[left] <= target && target <= nums[mid]){
                    right = mid - 1;
                }else {
                    left = mid + 1;
                }
            }else {
                // otherwise the right half [mid..right] is sorted: same test on that half
                if(nums[mid] <= target && target <= nums[right]){
                    left = mid + 1;
                }else {
                    right = mid - 1;
                }
            }
        }
        // window is empty: target is not in the array
        return -1;
    }


}
