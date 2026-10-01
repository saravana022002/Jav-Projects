package Practice.TwoPointers;

// ============================================================================
// LC 283. Move Zeroes   [Easy]
// https://leetcode.com/problems/move-zeroes/
// Topics: Array, Two Pointers
// ============================================================================
//
// PROBLEM
//   Move every 0 of the integer array nums to the end, keeping the relative order of the
//   non-zero elements. It must be done in place, without making a copy of the array.
//
// EXAMPLES
//   nums = [0,1,0,3,12]  ->  [1,3,12,0,0]   the non-zero values 1, 3, 12 keep their order
//   nums = [0]           ->  [0]
//
// CONSTRAINTS
//   1 <= nums.length <= 10^4,   -2^31 <= nums[i] <= 2^31 - 1
//   (follow-up: minimise the total number of operations)
//
// INTUITION
//   Think of the answer as "all non-zero values, in order, then enough zeros". Keep a write
//   pointer i that marks where the next non-zero value belongs, and a read pointer j that
//   hunts for the next non-zero value by skipping zeros. Each non-zero value is copied down
//   to slot i. When j has reached the end, slots i .. end are leftovers (old copies or
//   zeros), and all of them must become 0. Elements in front of the first zero have i == j
//   and are never rewritten.
//
// APPROACH (as implemented below)
//   1. i = 0 (next slot for a non-zero value), j = 0 (read position).
//   2. While j < nums.length:
//      a. Skip zeros: while nums[j] == 0, j++.
//      b. If j is in range and i != j: nums[i] = nums[j] (copy the non-zero value down),
//         i++, j++.
//      c. Else if i == j (and nums[j] != 0): the value is already in place, i++, j++.
//   3. Now j == nums.length and i = number of non-zero values. Fill slots i .. j-1 with 0.
//
// DRY RUN   nums = [0,1,0,3,12]
//   i=0 j=0  skip the zero -> j=1.  i != j: nums[0] = 1, i=1, j=2     [1,1,0,3,12]
//   i=1 j=2  skip the zero -> j=3.  i != j: nums[1] = 3, i=2, j=4     [1,3,0,3,12]
//   i=2 j=4  nothing to skip.       i != j: nums[2] = 12, i=3, j=5    [1,3,12,3,12]
//   j=5 = length -> the main loop ends.   Fill slots 3 and 4 with 0:  [1,3,12,0,0]
//   main()'s input {1} has no zero: i == j at the only step, then nothing is left to fill.
//
// COMPLEXITY
//   Time : O(n) - j only moves forward, and the final fill writes at most n zeros in total
//   Space: O(1) - two indices, all changes are made in the array itself
//
// GOTCHAS
//   - Every test of nums[j] is protected by j < nums.length first (and by i != j / i == j
//     short-circuits), so the array is never read past its end.
//   - i never gets ahead of j, so a copy nums[i] = nums[j] can never overwrite a value that
//     has not been read yet; the old copy left at nums[j] is overwritten by the final fill.
//   - The method returns void, and main() does not print the array, so a run shows nothing.
//
// STATUS
//   The method is correct (checked against a reference on random arrays of 0, 1 and 2). The
//   branching is more involved than necessary; a plain "if (nums[j] != 0) nums[i++] = nums[j]"
//   loop followed by the same zero fill does the same job.
//
// RELATED
//   - LC 27 Remove Element - the same write pointer, removing one given value (solved in this
//     folder).
//   - LC 26 Remove Duplicates from Sorted Array - the same write pointer, keeping only the
//     first of each value (solved in this folder).
//   - LC 2460 Apply Operations to an Array - ends with exactly this "move zeroes" step.
// ============================================================================
public class LC283MoveZeroes {

    public static void main(String[] args) {
        // nothing to move: the array stays [1]. Nothing is printed
        int[] nums = {1};
        moveZeroes(nums);
    }
    // Moves the zeros to the end in place, keeping the order of the other values. O(n)
    public static void moveZeroes(int[] nums) {
        // i = next slot for a non-zero value, j = read position
        int i = 0 ;
        int j = 0 ;
        while(j < nums.length){
            // skip zeros: j stops on the next non-zero value (or at the end)
            while (j < nums.length && nums[j] == 0){
                j++;
            }
            if(j < nums.length && i != j && nums[j] != 0){
                // a zero sits before it: copy the value down to slot i
                nums[i] = nums[j];
                i++;
                j++;
            }else if (i == j && nums[j] != 0){
                // no zero has been skipped so far: already in place
                i++;
                j++;
            }
        }

        // slots i .. j-1 hold leftovers: make them zeros
        while (i < j){
            nums[i] = 0;
            i++;
        }
    }
}
