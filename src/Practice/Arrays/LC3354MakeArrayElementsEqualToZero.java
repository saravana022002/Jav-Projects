package Practice.Arrays;

import java.util.Arrays;

// ============================================================================
// LC 3354. Make Array Elements Equal to Zero   [Easy]
// https://leetcode.com/problems/make-array-elements-equal-to-zero/
// Topics: Array, Simulation, Prefix Sum
// ============================================================================
//
// PROBLEM
//   Pick a starting index curr where nums[curr] == 0 and a direction, left or right. Then a
//   walker moves as follows until it steps outside the array:
//     - on a cell holding 0, it simply takes a step in its current direction;
//     - on a cell holding more than 0, it lowers that cell by 1, turns around, and takes a
//       step in the new direction.
//   A (start, direction) choice is valid when every cell is 0 once the walker has left.
//   Return how many choices are valid.
//
// EXAMPLES
//   nums = [1,0,2,0,3]      ->  2    only curr = 3 works, going left or going right
//   nums = [2,3,4,0,4,1,0]  ->  0    no choice clears the whole array
//
// CONSTRAINTS
//   1 <= nums.length <= 100,   0 <= nums[i] <= 100,   at least one nums[i] is 0
//
// INTUITION
//   The rules are fully deterministic and the limits are tiny, so just act the walk out:
//   every zero cell is a legal start, there are 2 directions, and each of those walks is
//   replayed on a COPY of the array and then checked for "everything is zero". The replay
//   always ends: each bounce uses up one unit of the array's total, and between two bounces
//   the walker only crosses zero cells in a straight line, which leads to the next bounce or
//   out of the array.
//
// APPROACH (as implemented below)
//   1. countValidSelections: for every index i with nums[i] == 0, add isValid(i, nums, true)
//      (start moving left) and isValid(i, nums, false) (start moving right); each returns 1
//      or 0. Return the total count.
//   2. isValid starts from copy = nums.clone(), so every walk sees the original numbers.
//   3. While curr is inside the array: if copy[curr] != 0, decrement it and flip isLeft.
//      Then always step: curr += isLeft ? -1 : 1 (a zero cell is just walked across).
//   4. After the walker has left: return 0 if some cell of copy is still non-zero, else 1.
//
// DRY RUN   nums = [1,0,2,0,3], start curr = 3 moving left   (a valid choice)
//   Zero cells are walked across; the bounces (decrement, then turn around) are, in order:
//     bounce at index   2   4   2   4   0   4
//     cell becomes      1   2   0   1   0   0
//     now moving        R   L   R   L   R   L
//   That is 6 bounces = 1 + 2 + 3, all units used. After the last bounce (at 4, moving left)
//   the walker crosses 3, 2, 1, 0 and exits at -1 with copy = [0,0,0,0,0], so isValid = 1.
//   The other valid choice is curr = 3 moving right; curr = 1 fails in both directions, so
//   the total is 2.
//
// COMPLEXITY
//   Time : O(z * n * S) worst case, z = number of zeros, S = sum(nums). There are 2z walks,
//          each makes at most S bounces and at most n steps between bounces (plus an O(n)
//          clone and final check). Loose upper bound; fine for n <= 100.
//   Space: O(n) - the copy made for each walk
//
// GOTCHAS
//   - Only zero cells are tried as starts, but both directions are tried for each of them.
//   - Turn first, then step: after a bounce the walker immediately moves the opposite way.
//   - The clone matters: isValid changes its array, and every walk must start from the
//     original numbers.
//   - A faster O(n) solution exists with prefix sums. At a zero with L = sum of the cells on
//     its left and R = sum on its right: both directions work if L == R, exactly one works
//     if |L - R| == 1, none otherwise (this agrees with the code on random arrays).
// ============================================================================
public class LC3354MakeArrayElementsEqualToZero {
    public static void main(String[] args) {
        // Example 1 - prints 2
        int[] nums = {1,0,2,0,3};
        System.out.println(countValidSelections(nums));
    }

    // Counts the valid (start, direction) choices: every zero cell, tried both ways.
    public static int countValidSelections(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == 0){
                // isLeft = true: the walker starts moving left; false: moving right
                count += isValid(i, nums, true);
                count += isValid(i, nums, false);
            }
        }
        return count;
    }


    // Replays one walk from curr in the given direction; returns 1 if it ends with all zeros.
    static int isValid(int curr, int[] nums, boolean isLeft){

        // work on a copy so the next walk starts from the original numbers
        int[] copy = nums.clone();

        // walk until the walker steps outside the array
        while (curr >= 0 && curr < copy.length){
            if (copy[curr] != 0) {
                // bounce: use up one unit and turn around
                copy[curr]--;
                isLeft = !isLeft;
            }
            // step in the (possibly new) direction; zero cells are just walked across
            curr += isLeft ? -1 : 1;
        }

        // valid only if every cell was brought down to 0
        for (int num : copy) {
            if (num != 0) {
                return 0;
            }
        }
        return 1;
    }
}
