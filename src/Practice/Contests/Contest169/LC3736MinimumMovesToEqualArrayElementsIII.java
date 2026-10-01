package Practice.Contests.Contest169;

// ============================================================================
// LC 3736. Minimum Moves to Equal Array Elements III   [Easy]
// https://leetcode.com/problems/minimum-moves-to-equal-array-elements-iii/
// Topics: Array, Math
// ============================================================================
//
// PROBLEM
//   In one move you pick any single element of nums and increase it by 1. Return the
//   smallest total number of moves that makes all elements equal.
//
// EXAMPLES
//   nums = [2,1,3]  ->  3     raise 2 to 3 (1 move) and 1 to 3 (2 moves)
//   nums = [4,4,5]  ->  2     raise both 4s to 5
//
// CONSTRAINTS
//   1 <= nums.length <= 100,   1 <= nums[i] <= 100
//
// INTUITION
//   Moves only ever raise a value, so no element can come down to meet the others: the
//   common final value must be at least the current maximum. Aiming higher than the maximum
//   just adds extra moves for every element, so the cheapest target is the maximum itself.
//   Element i then needs max - nums[i] moves, and the answer is the sum of those gaps.
//
// APPROACH (as implemented below)
//   1. One pass to find max, the largest element.
//   2. Second pass: temp = max - nums[i] is the moves element i needs; add it to ans.
//   3. Return ans.
//
// DRY RUN   nums = [4,4,5]
//   max = 5
//   i=0: 5 - 4 = 1  -> ans = 1
//   i=1: 5 - 4 = 1  -> ans = 2
//   i=2: 5 - 5 = 0  -> ans = 2
//   return 2
//
// COMPLEXITY
//   Time : O(n) - two passes over the array
//   Space: O(1)
//
// GOTCHAS
//   - This is NOT LC 453, where one move raises n - 1 elements at once. Here a move touches
//     a single element, so nothing can be lowered and the target is simply the maximum.
//   - The answer equals n * max - sum(nums); a one-element array needs 0 moves.
//
// RELATED
//   - LC 453 Minimum Moves to Equal Array Elements - a move raises all elements but one.
//   - LC 462 Minimum Moves to Equal Array Elements II - a move can raise OR lower an
//     element, so the best target becomes the median instead of the maximum.
// ============================================================================
public class LC3736MinimumMovesToEqualArrayElementsIII {
    public static void main(String[] args) {
        // Example 2 - prints 2
        int[] nums = {4,4,5};
        System.out.println(minMoves(nums));
    }

    // Returns the total number of +1 moves needed to raise every element to the maximum.
    public static int minMoves(int[] nums) {
        int ans = 0;
        // the target: the largest value (nothing can be lowered)
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
        }

        // moves needed by each element to reach the target
        int temp = 0;
        for (int i = 0; i < nums.length; i++) {
            temp = max - nums[i];
            ans = ans + temp;
        }
        return ans;
    }
}
