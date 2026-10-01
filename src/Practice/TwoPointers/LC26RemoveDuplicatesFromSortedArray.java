package Practice.TwoPointers;

// ============================================================================
// LC 26. Remove Duplicates from Sorted Array   [Easy]
// https://leetcode.com/problems/remove-duplicates-from-sorted-array/
// Topics: Array, Two Pointers
// ============================================================================
//
// PROBLEM
//   nums is sorted in non-decreasing order. Remove the duplicates IN PLACE so that every
//   distinct value appears once and the order is kept, then return k, the number of distinct
//   values. The first k slots of nums must hold those values in order; whatever sits after
//   index k - 1 is ignored.
//
// EXAMPLES
//   nums = [1,1,2]                ->  k = 2, nums starts with [1,2]
//   nums = [0,0,1,1,1,2,2,3,3,4]  ->  k = 5, nums starts with [0,1,2,3,4]
//
// CONSTRAINTS
//   1 <= nums.length <= 3 * 10^4,   -100 <= nums[i] <= 100,   nums is sorted (non-decreasing)
//
// INTUITION
//   Because the array is sorted, equal values sit together in blocks, so a value is a
//   duplicate exactly when it equals the last value we decided to keep. Keep a write pointer
//   i on the last kept element and scan ahead with a read pointer j: whenever nums[j] differs
//   from nums[i], it is the first element of a new block, so step i forward and copy nums[j]
//   there. The prefix nums[0..i] is always the duplicate-free version of what was scanned, and
//   i never overtakes j, so nothing unread is overwritten.
//
// APPROACHES IN THIS FILE
//   A. removeDuplicates() - the owner's earlier "look-ahead" version: for each kept value i,
//                           an inner loop moves j forward to the next different value, which
//                           is copied to nums[i + 1]; k counts the distinct values - O(n).
//                           Its call in main() is commented out.
//   B. optimizedSol()     - the standard write/read pointer compaction - O(n)
//                           <- main() runs this one
//
// APPROACH (as implemented below, method B)
//   1. Empty array -> 0 (the constraints never allow it).
//   2. i = 0: the index of the last kept element.
//   3. For j from 1 to nums.length - 1: if nums[j] != nums[i], then i++ and nums[i] = nums[j].
//   4. Return i + 1, the number of kept elements.
//
// DRY RUN   B on nums = [0,0,1,1,1,2,2,3,3,4]   (the input main() uses)
//   j=1  0 == nums[0]          skip
//   j=2  1 != 0  -> i=1, nums[1] = 1     [0,1,1,1,1,2,2,3,3,4]
//   j=3  1 == nums[1]          skip;   j=4  1 == nums[1]  skip
//   j=5  2 != 1  -> i=2, nums[2] = 2     [0,1,2,1,1,2,2,3,3,4]
//   j=6  2 == nums[2]          skip
//   j=7  3 != 2  -> i=3, nums[3] = 3     [0,1,2,3,1,2,2,3,3,4]
//   j=8  3 == nums[3]          skip
//   j=9  4 != 3  -> i=4, nums[4] = 4     [0,1,2,3,4,2,2,3,3,4]
//   return i + 1 = 5; the first five slots are 0,1,2,3,4 (the rest are leftovers)
//
// COMPLEXITY
//   Time : O(n) for both - B reads every element once; in A, j only ever moves forward, and
//          the outer loop runs once per distinct value
//   Space: O(1) - in-place, a few indices
//
// GOTCHAS
//   - The array must be sorted: only then are equal values adjacent, so comparing with the last
//     kept value finds every duplicate.
//   - Only the first k slots matter; the tail keeps stale values ([0,1,2,3,4,2,2,3,3,4] above).
//   - The result of optimizedSol is ignored in main(), so nothing is printed.
//   - In A, j is not reset for each i, which is why it is still linear.
//
// STATUS
//   Both methods are correct (checked against a distinct-values reference on random sorted
//   arrays). main() calls optimizedSol and discards the result, so it prints nothing; k is 5.
//
// RELATED
//   - LC 27 Remove Element - the same in-place write pointer, removing one given value
//     (solved in this folder).
//   - LC 80 Remove Duplicates from Sorted Array II - allows each value to stay twice.
//   - LC 283 Move Zeroes - write pointer that collects the non-zero values (in this folder).
// ============================================================================
public class LC26RemoveDuplicatesFromSortedArray {

    public static void main(String[] args) {
        // Example 2. optimizedSol would return 5, but the result is not printed.
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
//        removeDuplicates(nums);
        optimizedSol(nums);
    }
    // Approach A (earlier version): i is the last kept value, j scans ahead (it never moves
    // back) to the next different value, which is copied to nums[i + 1]; k counts the
    // distinct values. O(n)
    public static int removeDuplicates(int[] nums) {
        int length = nums.length;
        if(length == 0){
            return 0;
        }
        // k = distinct values found so far (nums[0] is the first one)
        int i, j = 0, k = 1;
        for (i = 0; i < length; i++) {
            // skip every element equal to nums[i]
            while (j < length){
                if(nums[i] != nums[j]){
                    // the next distinct value
                    k++;
                    break;
                }
                j++;
            }
            if(j < length) {
                // store it right after the kept values
                nums[i + 1] = nums[j];
            }else {
                // j ran off the end: no more distinct values
                break;
            }
        }
        return k;
    }

    // Approach B: i is the last kept element; every nums[j] that differs from it starts a new
    // block and is copied to nums[i + 1]. Returns the number of kept elements. O(n)
    public static int optimizedSol(int[] nums) {
        if(nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            // sorted array: a value different from the last kept one is new
            if(nums[j] != nums[i]){
                i++;
                nums[i] = nums[j];
            }
        }
        // indices 0..i hold the distinct values
        return i + 1;
    }
}
