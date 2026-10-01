package Practice.TwoPointers;

// ============================================================================
// LC 42. Trapping Rain Water   [Hard]
// https://leetcode.com/problems/trapping-rain-water/
// Topics: Array, Two Pointers, Dynamic Programming, Stack, Monotonic Stack
// ============================================================================
//
// PROBLEM
//   The array describes an elevation map: bar i is 1 unit wide and nums[i] units tall. After
//   rain, water collects in the dips between taller bars. Return how many units of water are
//   trapped in total.
//
// EXAMPLES
//   height = [0,1,0,2,1,0,1,3,2,1,2,1]  ->  6
//   height = [4,2,0,3,2,5]               ->  9    the input main() uses: the dips hold
//                                                 2 + 4 + 1 + 2 = 9 units under the walls 4 and 5
//
// CONSTRAINTS
//   n == height.length,   1 <= n <= 2 * 10^4,   0 <= height[i] <= 10^5
//
// INTUITION
//   The water above bar i stands as high as the LOWER of the two tallest walls around it:
//   min(tallest bar to its left, tallest bar to its right) - height[i], and never below 0.
//   Storing both "tallest so far" arrays costs O(n) memory. Two pointers avoid that: start at
//   both ends, keep the running maximum of each side (leftMax, rightMax), and always settle a
//   bar on the side whose running maximum is SMALLER. The other side already owns a wall at
//   least as tall as that maximum, so the smaller maximum alone decides the water level for the
//   next bar on that side.
//
// APPROACH (as implemented below)
//   1. left = 0, right = nums.length - 1; leftMax = nums[left], rightMax = nums[right];
//      water = 0. (The end bars hold no water themselves; they just seed the maxima.)
//   2. While left < right:
//      - leftMax < rightMax : left++ (step first, then look at the new bar);
//                             leftMax = max(leftMax, nums[left]);
//                             water += leftMax - nums[left]
//      - otherwise          : right--; rightMax = max(rightMax, nums[right]);
//                             water += rightMax - nums[right]
//   3. Return water.
//
// DRY RUN   nums = [4,2,0,3,2,5]   leftMax = 4, rightMax = 5 at the start
//   leftMax 4 < rightMax 5  -> left=1 (h=2): water += 4 - 2 = 2     water = 2
//   4 < 5                   -> left=2 (h=0): water += 4 - 0 = 4     water = 6
//   4 < 5                   -> left=3 (h=3): water += 4 - 3 = 1     water = 7
//   4 < 5                   -> left=4 (h=2): water += 4 - 2 = 2     water = 9
//   4 < 5                   -> left=5 (h=5): 5 > leftMax, so leftMax = 5 and water += 0
//   left == right -> stop.   return 9
//
// COMPLEXITY
//   Time : O(n) - every loop pass moves one pointer inwards, and they meet after n - 1 passes
//   Space: O(1) - a few ints
//   (The answer is at most about 2 * 10^9 = (n - 2) * 10^5, which still fits in an int.)
//
// GOTCHAS
//   - The side is chosen by comparing the running MAXIMA (leftMax < rightMax); V2 compares the
//     bar heights under the pointers instead. When the maxima are equal the right side moves,
//     which is just as correct.
//   - The pointer moves first and the new bar is read afterwards, so the end bars are never
//     "processed"; they only start the maxima.
//   - leftMax / rightMax are updated BEFORE the water is computed, so a new tallest bar adds
//     0 and the amount can never be negative.
//   - An empty array would throw (nums[left] with left = 0); the constraints guarantee
//     n >= 1. trapWater is private static, which main() can still call.
//
// STATUS
//   The method is correct (checked against a prefix-max / suffix-max reference on random
//   arrays and the two official examples). main() prints 9.
//
// SEE ALSO
//   LC42TrappingRainWaterV2.java - the same two-pointer idea, deciding the side by the bar
//     heights under the pointers instead of the running maxima, with a null / empty guard
//
// RELATED
//   - LC 11 Container With Most Water - two pointers closing in from both ends on a bar chart.
//   - LC 407 Trapping Rain Water II - the same question on a 2D grid of heights.
//   - LC 135 Candy - combines a bound from the left and a bound from the right
//     (solved in src/Practice/Greedy).
// ============================================================================
public class LC42TrappingRainWaterV1 {
    public static void main(String[] args) {

        // Example 2. Expected output: 9
        int[] nums = {4,2,0,3,2,5};
        System.out.println(trapWater(nums));

    }

    // Two pointers from both ends; the side with the smaller running maximum is settled first.
    // O(n) time, O(1) space
    private static int trapWater(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        // tallest bar seen so far on each side (the end bars to begin with)
        int leftMax = nums[left];
        int rightMax = nums[right];
        int water = 0;
        while (left < right){
           // the left wall is the lower one: the next left bar is limited by leftMax
           if(leftMax < rightMax){
               left ++;
               if(nums[left] > leftMax){
                   leftMax = nums[left];
               }
               water += leftMax - nums[left];
           }else {
               // the right wall is the lower (or equal) one: the next right bar is limited by
               // rightMax
               right --;
               if(nums[right] > rightMax){
                   rightMax = nums[right];
               }
               water += rightMax - nums[right];
           }
        }
        return water;
    }
}
