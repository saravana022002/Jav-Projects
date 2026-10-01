package Practice.TwoPointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// ============================================================================
// LC 18. 4Sum   [Medium]
// https://leetcode.com/problems/4sum/
// Topics: Array, Two Pointers, Sorting
// ============================================================================
//
// PROBLEM
//   Given an integer array nums and an integer target, return every distinct quadruplet of
//   values [nums[a], nums[b], nums[c], nums[d]] that comes from four different positions and
//   adds up to target. Each quadruplet may appear only once in the answer; the order of the
//   quadruplets does not matter.
//
// EXAMPLES
//   nums = [1,0,-1,0,-2,2], target = 0  ->  [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]]
//   nums = [2,2,2,2,2],     target = 8  ->  [[2,2,2,2]]   five 2s, but only one distinct
//                                                         quadruplet
//
// CONSTRAINTS
//   1 <= nums.length <= 200,   -10^9 <= nums[i] <= 10^9,   -10^9 <= target <= 10^9
//
// INTUITION
//   This is 3Sum with one more loop. Sort the array, fix the two smallest numbers of the
//   quadruplet with two nested loops (i, then j > i), and the remaining two are a two-sum
//   question on the sorted rest, answered by two pointers moving inwards: a sum that is too
//   big moves right left, a sum that is too small moves left right. Sorting puts equal values
//   next to each other, so skipping repeated values (for i, for j and for the pointers after
//   a hit) removes duplicate quadruplets without any set. The four numbers can add up to 4 *
//   10^9, which does not fit in an int, so the sum is computed as a long.
//
// APPROACH (as implemented below, optimalSolution)
//   1. Arrays.sort(nums) (this reorders the caller's array).
//   2. For i from 0 to size - 4: skip it if nums[i] == nums[i - 1] (i > 0).
//   3.   For j from i + 1 to size - 3: skip it if nums[j] == nums[j - 1] and j > i + 1.
//   4.     left = j + 1, right = size - 1. While left < right:
//          sum = (long) nums[i] + nums[j] + nums[left] + nums[right].
//          - sum == target : add the four values; move left over equal values and right over
//                            equal values, then left++ and right--
//          - sum > target  : right--
//          - sum < target  : left++
//   5. Return the list of quadruplets.
//
// DRY RUN   nums = [1,0,-1,0,-2,2], target = 0   sorted: [-2,-1,0,0,1,2]   (size 6)
//   i=0 (-2), j=1 (-1): left=2 right=5: -2-1+0+2 = -1 < 0 -> left=3: -1 -> left=4: -2-1+1+2
//             = 0 -> add [-2,-1,1,2]; the pointers meet
//   i=0 (-2), j=2 (0) : left=3 right=5: -2+0+0+2 = 0 -> add [-2,0,0,2]; the pointers meet
//   i=0, j=3 (0)      : equals nums[2] and j > i + 1 -> skipped
//   i=1 (-1), j=2 (0) : left=3 right=5: -1+0+0+2 = 1 > 0 -> right=4: -1+0+0+1 = 0 ->
//             add [-1,0,0,1]; the pointers meet;   j=3 is skipped as a repeat of j=2
//   i=2 (0),  j=3 (0) : left=4 right=5: 0+0+1+2 = 3 > 0 -> right=4 -> nothing
//   result [[-2,-1,1,2],[-2,0,0,2],[-1,0,0,1]].
//   main()'s input is LeetCode's overflow test: four 1000000000 and target -294967296. The
//   true sum is 4000000000; as an int it would wrap to exactly -294967296 and wrongly
//   match. With the long sum, 4000000000 > target, so right moves in and [] is returned.
//
// COMPLEXITY
//   Time : O(n^3) - the sort is O(n log n); two nested loops give O(n^2) pairs (i, j), and
//          the two pointers sweep the rest once for each pair
//   Space: O(1) besides the answer (the sort is in place)
//
// GOTCHAS
//   - Sums are built as (long) nums[i] + ...; the cast on the first operand makes the whole
//     chain long. Comparing the long sum with the int target is fine.
//   - j skips repeats only when j > i + 1: the first j after i may legitimately equal
//     nums[i] (for example [2,2,2,2]).
//   - The loops stop at size - 3 (for i) and size - 2 (for j) to leave room for left and
//     right.
//   - The pointer skip loops read nums[left + 1] and nums[right - 1]; left < right keeps
//     those reads inside the array.
//
// STATUS
//   The method is correct (checked against a brute force on random small arrays and the two
//   official examples). main() runs the overflow test and prints []. The method is private
//   and named optimalSolution; LeetCode's template calls it fourSum.
//
// RELATED
//   - LC 15 3Sum - the same idea with one loop fewer (solved in this folder).
//   - LC 1 Two Sum - the pair search at the bottom of both problems.
//   - LC 454 4Sum II - four arrays instead of one; counts tuples with a hash map.
// ============================================================================
public class LC18FourSum {
    public static void main(String[] args) {
        // LeetCode's overflow test: the real sum is 4000000000, not the wrapped int.
        // Expected output: []
        int[] nums = {1000000000,1000000000,1000000000,1000000000};
        int target = -294967296;
        System.out.println(optimalSolution(nums, target));
    }

    // Sort, fix two numbers with nested loops, find the other two with two pointers.
    // O(n^3), skipping duplicates at every level
    private static List<List<Integer>> optimalSolution(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> ansFinal = new ArrayList<>();

        int size = nums.length;
        for (int i = 0; i < size - 3; i++) {
            // same first number as before would only repeat its quadruplets
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < size - 2; j++) {
                // same second number as before (for this i) would only repeat them
                if (j > i+1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int left = j + 1;
                int right = size - 1;
                while (left < right) {
                    // long: four values up to 10^9 can add up to 4 * 10^9, beyond int
                    long sum = (long) nums[i] + nums[j] + nums[left] + nums[right];
                    if (sum == target) {
                        ansFinal.add(Arrays.asList(nums[i], nums[j], nums[left], nums[right]));
                        // step over repeated values so the same quadruplet is not added again
                        while (left < right && nums[left] == nums[left + 1]) left++;
                        while (left < right && nums[right] == nums[right - 1]) right--;
                        right--;
                        left++;
                    } else if (sum > target) {
                        // too big: a smaller right value is needed
                        right--;
                    } else {
                        // too small: a larger left value is needed
                        left++;
                    }
                }
            }
        }
        return ansFinal;
    }
}
