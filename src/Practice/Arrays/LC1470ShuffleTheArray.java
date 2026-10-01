package Practice.Arrays;

import java.util.Arrays;

// ============================================================================
// LC 1470. Shuffle the Array   [Easy]
// https://leetcode.com/problems/shuffle-the-array/
// Topics: Array
// ============================================================================
//
// PROBLEM
//   nums holds 2n numbers: a first half x1..xn followed by a second half y1..yn.
//   Return them interleaved, taking one from each half in turn: x1,y1,x2,y2,...,xn,yn.
//
// EXAMPLES
//   nums = [2,5,1,3,4,7], n = 3  ->  [2,3,5,4,1,7]   halves [2,5,1] and [3,4,7], in turns
//   nums = [1,1,2,2],     n = 2  ->  [1,2,1,2]
//
// CONSTRAINTS
//   1 <= n <= 500,   nums.length == 2n,   1 <= nums[i] <= 10^3
//
// INTUITION
//   The i-th pair of the answer is (x_i, y_i) = (nums[i], nums[i + n]): the second half
//   starts exactly n places after the first, so one index i over the first half also
//   finds its partner at i + n. That pair lands in answer slots 2i and 2i + 1.
//
// APPROACH (as implemented below)
//   1. arr = new int[2n]; j = 0 is the next free slot in arr.
//   2. For i = 0 .. n-1: arr[j] = nums[i] (x), arr[j+1] = nums[i + n] (y), then j += 2.
//   3. Return arr.
//
// DRY RUN   nums = [2,5,1,3,4,7], n = 3
//   i=0: arr[0] = nums[0] = 2, arr[1] = nums[3] = 3   => [2,3,_,_,_,_]
//   i=1: arr[2] = nums[1] = 5, arr[3] = nums[4] = 4   => [2,3,5,4,_,_]
//   i=2: arr[4] = nums[2] = 1, arr[5] = nums[5] = 7   => [2,3,5,4,1,7]
//
// COMPLEXITY
//   Time : O(n) - n iterations, two writes each
//   Space: O(n) - the 2n-long output array; nothing else
//
// GOTCHAS
//   - j is always 2 * i, so the slots could also be written as arr[2*i] and arr[2*i + 1].
//   - i runs only to n - 1; i + n then ends exactly at the last index 2n - 1.
//
// RELATED
//   - LC 1929 Concatenation of Array - another 2n-long output built by index mapping
//     (LC1929ConcatenationOfArray.java in this folder).
// ============================================================================
public class LC1470ShuffleTheArray {

    public static void main(String[] args) {
       // Example 1 - prints [2, 3, 5, 4, 1, 7]
       int[] nums = {2,5,1,3,4,7};
       int n = 3;
        System.out.println(Arrays.toString(shuffle(nums, n)));
    }

    // Returns x1,y1,x2,y2,... built from the two halves of nums.
    public static int[] shuffle(int[] nums, int n) {
        int[] arr = new int[2*n];
        // j = next free slot in arr (always 2 * i)
        int j = 0;
        for (int i = 0; i < n; i++) {
            // x_i from the first half, y_i from the same position in the second half
            arr[j] = nums[i];
            arr[j+1] =  nums[i + n];
            j = j+2;
        }
        return arr;
    }
}
