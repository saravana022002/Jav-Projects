package Practice.TwoPointers;

// ============================================================================
// LC 27. Remove Element   [Easy]
// https://leetcode.com/problems/remove-element/
// Topics: Array, Two Pointers
// ============================================================================
//
// PROBLEM
//   Given an integer array nums and a value val, remove every occurrence of val IN PLACE.
//   The order of the remaining elements may change. Return k, the number of elements that
//   are not equal to val; the first k slots of nums must hold exactly those elements (in any
//   order), and whatever sits after index k - 1 does not matter.
//
// EXAMPLES
//   nums = [3,2,2,3],         val = 3  ->  k = 2, nums starts with [2,2]
//   nums = [0,1,2,2,3,0,4,2], val = 2  ->  k = 5, nums starts with 0,1,4,0,3 in any order
//
// CONSTRAINTS
//   0 <= nums.length <= 100,   0 <= nums[i] <= 50,   0 <= val <= 100
//
// INTUITION
//   Split the array into two zones: a front zone of survivors (values != val) that grows from
//   the left, and a back zone of unwanted val values that grows from the right. Two pointers
//   walk towards each other. A survivor at the front just extends the front zone. A val at the
//   front must be replaced by a survivor from the back, so swap it with the last element that
//   is not val; a val at the back is already where it belongs and only shrinks the range.
//   When the pointers meet, the front zone is exactly the answer, and its size is i.
//
// APPROACH (as implemented below)
//   1. i = 0 (front), j = nums.length - 1 (back).
//   2. While i <= j:
//      - nums[i] == val and nums[j] != val : swap them (temp), then i++ and j-- - the survivor
//                                            now sits at i, the val at j
//      - nums[i] == val and nums[j] == val : the back element is already a val -> j--
//      - otherwise (nums[i] != val)        : nums[i] is a survivor -> i++
//   3. Return i: slots 0 .. i-1 are the survivors.
//
// DRY RUN   nums = [3,2,2,3], val = 3   (the input main() uses)
//   i=0 j=3  nums[0] = 3 = val, nums[3] = 3 = val  -> j = 2
//   i=0 j=2  nums[0] = 3 = val, nums[2] = 2        -> swap: [2,2,3,3], i = 1, j = 1
//   i=1 j=1  nums[1] = 2 != val                    -> i = 2
//   i=2 > j=1 -> stop.   return 2; the array ends as [2,2,3,3]
//
// COMPLEXITY
//   Time : O(n) - every pass of the loop moves i or j (or both), so at most n passes
//   Space: O(1) - one temp variable
//
// GOTCHAS
//   - The condition is i <= j, not i < j: when the pointers meet, that last element still has
//     to be judged (it is counted if it is not val).
//   - When nums[i] is not val, nums[j] is not looked at at all.
//   - Order is not preserved (swaps move elements from the back to the front), which the
//     problem allows.
//   - main() ignores the returned count, so nothing is printed.
//
// STATUS
//   The method is correct (checked against a filter-and-compare reference on random small
//   arrays). main() calls it on Example 1 and discards the result (k = 2).
//
// SEE ALSO
//   LC27RemoveElementThreeApproaches.java - a forward write pointer, "refill the hole with the
//     last element", and a scratch-array version in one file
//
// RELATED
//   - LC 283 Move Zeroes - the same partition with val = 0, but the order must be kept
//     (solved in this folder).
//   - LC 75 Sort Colors - the same two-ended swapping, extended to three groups (solved in
//     this folder).
// ============================================================================
public class LC27RemoveElementTwoEndsSwap {
    public static void main(String[] args) {
        // Example 1: returns 2 (the array ends as [2,2,3,3]); the result is not printed
        int[] nums =  {3,2,2,3};
        int val = 3;
        removeElement(nums, val);
    }
    // Two pointers from both ends: survivors collect at the front, val values at the back.
    // Returns the number of survivors. O(n)
    public static int removeElement(int[] nums, int val) {
        int i = 0;
        int j = nums.length - 1;
        int temp = 0;
        while (i <= j){
            // front slot holds a val and the back slot a survivor: swap them
            if(nums[i] == val && nums[j] != val){
                temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                i++;
                j--;
            }else if(nums[i] == val && nums[j] == val){
                // the back element is already a val: just shrink the range
                j--;
            }else {
                // nums[i] is a survivor
                i++;
            }
        }
        return i;
    }
}
