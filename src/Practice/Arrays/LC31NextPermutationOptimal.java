package Practice.Arrays;

import java.util.Arrays;
import java.util.Collections;

// ============================================================================
// LC 31. Next Permutation   [Medium]
// https://leetcode.com/problems/next-permutation/
// Topics: Array, Two Pointers
// ============================================================================
//
// PROBLEM
//   A permutation is one ordering of the numbers in nums. Sort all the orderings in
//   dictionary (lexicographic) order; the "next permutation" of nums is the ordering that
//   comes right after the current one. If the current one is the very last ordering, the
//   answer wraps around to the first: the numbers sorted ascending. Rearrange nums into
//   its next permutation in place, using only constant extra memory.
//
// EXAMPLES
//   nums = [1,2,3]  ->  [1,3,2]
//   nums = [3,2,1]  ->  [1,2,3]     the last ordering, so it wraps around to the smallest
//   nums = [1,1,5]  ->  [1,5,1]     equal numbers are allowed
//
// CONSTRAINTS
//   1 <= nums.length <= 100,   0 <= nums[i] <= 100
//
// INTUITION
//   Read the array as a number written in "digits". To get the next larger number we must
//   change it as little as possible, so we change the RIGHTMOST position that can still
//   grow. A tail that is in non-increasing order (biggest first) is already the largest
//   arrangement of its own members, so nothing inside it can grow; the change has to be made
//   at the element just left of that tail - the pivot, the rightmost place where
//   nums[pivot] < nums[pivot + 1].
//     - Put into the pivot the smallest tail element that is bigger than it (the tail is
//       non-increasing, so that is the rightmost one bigger than the pivot). The number has
//       now grown, by the least possible amount at that position.
//     - Then make everything to the right of the pivot as small as possible: ascending
//       order. After the swap the tail is still non-increasing, so reversing it is enough.
//   If no pivot exists, the whole array is non-increasing: it is the last ordering, and
//   reversing all of it gives the first one.
//
// APPROACH (as implemented below)
//   1. n = arraySize; i = n - 1. A single element needs no change: return.
//   2. Move i left while nums[i-1] >= nums[i]. If i gets to 1 and nums[0] >= nums[1] still
//      holds, there is no pivot: reverse(nums, 0, n-1) and return.
//   3. Otherwise nums[i-1] < nums[i]: the pivot is nums[i-1] and the tail nums[i..n-1] is
//      non-increasing.
//   4. j = n - 1; move j left while nums[i-1] >= nums[j]. It stops at the rightmost element
//      bigger than the pivot (it always does, at j >= i, because nums[i] > nums[i-1]).
//   5. Swap nums[i-1] and nums[j].
//   6. reverse(nums, i, n-1): the tail becomes ascending, its smallest arrangement.
//
// DRY RUN   nums = [2,4,3,1]
//   step 2  i=3: 3 >= 1 yes -> i=2;  i=2: 4 >= 3 yes -> i=1;  i=1: 2 >= 4 no -> stop
//           pivot = nums[0] = 2, tail = [4,3,1]
//   step 4  j=3: 2 >= 1 yes -> j=2;  j=2: 2 >= 3 no -> stop   (nums[2] = 3 is the target)
//   step 5  swap nums[0] and nums[2]  -> [3,4,2,1]
//   step 6  reverse the tail [4,2,1] -> [1,2,4]  -> [3,1,2,4]
//   main()'s [5,1,1]: 5 >= 1 and 1 >= 1 all the way, so no pivot; reversed to [1,1,5].
//
// COMPLEXITY
//   Time : O(n) - the pivot scan, the j scan and the reverse each walk the array at most once
//   Space: O(1) - only a few indices and a temp variable; nums is changed in place
//
// GOTCHAS
//   - Both scans use >= (not >): equal neighbours are skipped, which is what makes repeated
//     numbers work ([1,1,5] -> [1,5,1]).
//   - Reversing the tail is enough (no sort needed) because the tail is non-increasing
//     before the swap and still non-increasing after it.
//   - The early return for arraySize == 1 also prevents reading nums[-1].
//   - The "if(j == 0) return" inside the j scan can never fire: nums[i] > nums[i-1] stops the
//     scan at j >= i. It is only a safety net.
//
// RELATED
//   - LC 556 Next Greater Element III - the same pivot / swap / reverse steps applied to the
//     digits of an integer.
//   - LC 60 Permutation Sequence - finds the k-th ordering directly
//     (src/Practice/Math/LC60PermutationSequence.java).
//   - LC 1850 Minimum Adjacent Swaps to Reach the Kth Smallest Number - uses next
//     permutation k times.
//
// SEE ALSO
//   LC31NextPermutationBruteForce.java - lists all orderings, sorts them and takes the one
//   after the current ordering (O(n! * n^2 log n); it has known issues, see its STATUS)
//
// STATUS
//   Correct (checked against a brute force on random inputs, repeated numbers included).
//   main() calls nextPermutation on [5,1,1] but does not print; nums ends as [1,1,5].
//   The imports java.util.Arrays and java.util.Collections are unused.
// ============================================================================
public class LC31NextPermutationOptimal {
    public static void main(String[] args) {
        // [5,1,1] is the last ordering, so it wraps around. nums becomes [1,1,5] (not printed)
        int[] nums = {5,1,1};
        nextPermutation(nums);
    }

    // Rearranges nums into its next permutation, in place (smallest ordering if it is the last).
    public static void nextPermutation(int[] nums) {
        int arraySize = nums.length;
        int i = arraySize - 1;
        if(arraySize == 1){
            return;
        }
        // find the pivot: i ends at the rightmost index with nums[i-1] < nums[i]
        while(nums[i-1] >= nums[i]){
            if(i == 1){
                // no pivot: the array is non-increasing (the last ordering) -> wrap to the first
                reverse(nums, 0, arraySize -1);
                return;
            }
            i--;
        }
        // from the right, find the first element that is bigger than the pivot nums[i-1]
        int j = arraySize - 1;
        while (nums[i-1] >= nums[j]){
            if(j == 0){
                return;
            }
            j--;
        }
        // swap it into the pivot position
        int temp;
        temp = nums[i-1];
        nums[i-1] = nums[j];
        nums[j] = temp;
        // the tail after the pivot is non-increasing; reversing gives its smallest arrangement
        reverse(nums, i, arraySize -1);
    }

    // Reverses array[left..right] in place by swapping the two ends and moving inwards.
    public static void reverse(int[] array, int left, int right){
        while (left < right) {
            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            left++;
            right--;
        }
    }
}
