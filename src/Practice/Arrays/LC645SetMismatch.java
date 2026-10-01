package Practice.Arrays;

import java.util.Arrays;

// ============================================================================
// LC 645. Set Mismatch   [Easy]
// https://leetcode.com/problems/set-mismatch/
// Topics: Array, Hash Table, Bit Manipulation, Sorting
// ============================================================================
//
// PROBLEM
//   A set originally held every number from 1 to n. Through an error one number was turned
//   into a copy of another, so the array nums (length n) now holds one value twice and is
//   missing one value of 1..n. Return [the value that occurs twice, the missing value].
//
// EXAMPLES
//   nums = [1,2,2,4]  ->  [2,3]    2 is repeated, 3 is absent
//   nums = [1,1]      ->  [1,2]
//
// CONSTRAINTS
//   2 <= nums.length <= 10^4,   1 <= nums[i] <= 10^4
//
// INTUITION
//   Each value v of 1..n needs a "seen already?" flag, and value v can use slot v - 1. The
//   second time a value shows up its flag is already set, which exposes the duplicate; after
//   the pass, the one flag that was never set belongs to the missing number. A boolean[]
//   gives the flags in extra memory (A). Approach B keeps them inside nums itself: a
//   negative nums[v - 1] means "v was seen".
//
// APPROACHES IN THIS FILE
//   A. findErrorNums(nums)    - boolean[] seen flags          - O(n) time, O(n) space
//   B. findErrorNumsOpt(nums) - sign flags inside nums        - O(n) time, O(1) extra space;
//                               it changes nums   <- main() runs this one
//   (APPROACH and DRY RUN below describe B.)
//
// APPROACH (as implemented below, findErrorNumsOpt)
//   1. dup = -1, miss = -1 (answers), idx = the slot of the value being looked at.
//   2. For each i: idx = Math.abs(nums[i]) - 1 (abs, because nums[i] may already have been
//      made negative). If nums[idx] < 0, the value idx + 1 was seen before: dup = idx + 1.
//      Otherwise flag it: nums[idx] *= -1.
//   3. Scan again: the first index i with nums[i] > 0 was never flagged, so miss = i + 1.
//   4. Return {dup, miss}.
//
// DRY RUN   nums = [3,2,2]   (what main() runs)
//   i=0: idx = 2, nums[2] = 2 is positive  -> flag it        [3,2,-2]
//   i=1: idx = 1, nums[1] = 2 is positive  -> flag it        [3,-2,-2]
//   i=2: idx = |-2| - 1 = 1, nums[1] is negative -> dup = 2
//   scan: nums[0] = 3 is still positive -> miss = 1          returns [2,1]
//
// COMPLEXITY
//   B: Time O(n) - two passes;  Space O(1) - a few variables (the flags live in nums)
//   A: Time O(n) - two passes;  Space O(n) - the seen array
//
// GOTCHAS
//   - B changes the array it is given (entries are left negative); A does not.
//   - The answer order is [duplicate, missing].
//   - Both rely on the guarantee: values stay within 1..n, exactly one value is repeated
//     (twice) and exactly one is absent. A value above n would be an out-of-range index.
//
// RELATED
//   - LC 448 Find All Numbers Disappeared in an Array - the same sign marking, reporting
//     every missing value (LC448FindAllNumbersDisappearedInAnArray.java in this folder).
//   - LC 287 Find the Duplicate Number - finding the repeated value only, with stricter
//     limits on memory and on changing the array.
//
// STATUS
//   Both methods are correct. main() runs findErrorNumsOpt on [3,2,2] and prints [2, 1].
// ============================================================================
public class LC645SetMismatch {
    // Approach A: flag every value in a boolean[]. O(n) time, O(n) space.
    public static int[] findErrorNums(int[] nums) {
        // seen[v - 1] == true once value v has been met
        boolean[] seen = new boolean[nums.length];
        int dup = -1;
        int miss = -1;
        for (int num : nums) {
            // flag already set -> this value occurs a second time
            if(seen[num - 1]){
                dup = num;
            }
            seen[num - 1] = true;
        }
        // the only flag that was never set belongs to the missing value
        for (int i = 0; i < seen.length; i++) {
            if(!seen[i]){
                miss = i+1;
                break;
            }
        }
        return new int[]{dup, miss};
    }

    // Approach B: use the sign of nums[v - 1] as the "seen" flag. O(n) time, O(1) extra
    // space, but it leaves nums with negative entries.
    public static int[] findErrorNumsOpt(int[] nums) {
        int dup = -1;
        int miss = -1;
        int idx;
        for (int i = 0; i < nums.length; i++) {
            // home slot of the value at i (abs: it may already be flagged)
            idx = Math.abs(nums[i]) - 1;
            if(nums[idx] < 0){
                // the slot is already flagged: this value was seen before
                dup = idx + 1;
            }else {
                nums[idx] *= -1;
            }
        }
        // the slot that stayed positive was never flagged: its value is missing
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] > 0){
                miss = i+1;
                break;
            }
        }
        return new int[]{dup, miss};
    }

    public static void main(String[] args) {
        // nums = [3,2,2]: 2 is repeated, 1 is missing. Prints [2, 1]
        int[] nums = {3,2,2};
        System.out.println(Arrays.toString(findErrorNumsOpt(nums)));
    }
}
