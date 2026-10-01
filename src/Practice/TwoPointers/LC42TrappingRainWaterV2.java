package Practice.TwoPointers;

// ============================================================================
// LC 42. Trapping Rain Water   [Hard]
// https://leetcode.com/problems/trapping-rain-water/
// Topics: Array, Two Pointers, Dynamic Programming, Stack, Monotonic Stack
// ============================================================================
//
// PROBLEM
//   The array describes an elevation map: bar i is 1 unit wide and height[i] units tall. After
//   rain, water collects in the dips between taller bars. Return how many units of water are
//   trapped in total.
//
// EXAMPLES
//   height = [0,1,0,2,1,0,1,3,2,1,2,1]  ->  6    the input main() uses
//   height = [4,2,0,3,2,5]               ->  9
//
// CONSTRAINTS
//   n == height.length,   1 <= n <= 2 * 10^4,   0 <= height[i] <= 10^5
//
// INTUITION
//   The water above bar i stands as high as the LOWER of the two tallest walls around it:
//   min(tallest bar to its left, tallest bar to its right) - height[i], and never below 0.
//   Keeping both "tallest so far" arrays costs O(n) memory; two pointers avoid that. Start at
//   both ends and track the tallest bar seen on each side (leftMax, rightMax). Look at the
//   bars under the two pointers and settle the LOWER one: a taller bar stands at the other
//   pointer, so the far side is high enough and the water above the lower bar is decided only
//   by the tallest bar seen so far on its own side. Then step that pointer inwards.
//
// APPROACH (as implemented below)
//   1. If height is null or empty, return 0.
//   2. left = 0, right = height.length - 1; leftMax = rightMax = 0; water = 0.
//   3. While left < right:
//      - height[left] < height[right] : settle the left bar. If height[left] >= leftMax it
//                                       becomes the new leftMax (it holds no water); otherwise
//                                       water += leftMax - height[left]. Then left++.
//      - otherwise                    : the same for the right bar with rightMax, then right--.
//   4. Return water.
//
// DRY RUN   height = [0,1,0,2,1,0,1,3,2,1,2,1]   (Example 1, the input main() uses)
//   step  left(h)  right(h)  settled  what happens                      water
//    1    0 (0)    11 (1)    left     0 >= leftMax 0 -> leftMax = 0         0
//    2    1 (1)    11 (1)    right    1 >= rightMax 0 -> rightMax = 1       0   (1 < 1 is false)
//    3    1 (1)    10 (2)    left     1 >= leftMax 0 -> leftMax = 1         0
//    4    2 (0)    10 (2)    left     0 < leftMax 1 -> += 1                 1
//    5    3 (2)    10 (2)    right    2 >= rightMax 1 -> rightMax = 2       1
//    6    3 (2)     9 (1)    right    1 < rightMax 2 -> += 1                2
//    7    3 (2)     8 (2)    right    2 >= rightMax 2 -> rightMax = 2       2
//    8    3 (2)     7 (3)    left     2 >= leftMax 1 -> leftMax = 2         2
//    9    4 (1)     7 (3)    left     1 < leftMax 2 -> += 1                 3
//   10    5 (0)     7 (3)    left     0 < leftMax 2 -> += 2                 5
//   11    6 (1)     7 (3)    left     1 < leftMax 2 -> += 1                 6
//   left reaches 7 = right -> stop.   return 6
//
// COMPLEXITY
//   Time : O(n) - every pass moves one pointer inwards, so there are at most n - 1 passes
//   Space: O(1) - a few ints
//   (The answer is at most about 2 * 10^9 = (n - 2) * 10^5, which still fits in an int.)
//
// GOTCHAS
//   - The side is chosen by comparing the bar HEIGHTS under the pointers; V1 compares the
//     running maxima instead. On a tie (height[left] == height[right]) the right side moves.
//   - A bar that is at least as tall as the current max only raises the max and adds no water;
//     the `else` branch is what adds water, so the amount is never negative.
//   - The bar where the pointers meet is never settled, because it cannot hold water (in the
//     dry run that is index 7, the bar of height 3).
//   - The null / empty guard returns 0 instead of throwing.
//
// STATUS
//   The method is correct (checked against a prefix-max / suffix-max reference on random
//   arrays and the two official examples). main() prints "Water Quantity is: 6".
//
// SEE ALSO
//   LC42TrappingRainWaterV1.java - the same two-pointer idea, deciding the side by the running
//     maxima leftMax / rightMax instead of the bar heights
//
// RELATED
//   - LC 11 Container With Most Water - two pointers closing in from both ends on a bar chart.
//   - LC 407 Trapping Rain Water II - the same question on a 2D grid of heights.
//   - LC 135 Candy - combines a bound from the left and a bound from the right
//     (solved in src/Practice/Greedy).
// ============================================================================
public class LC42TrappingRainWaterV2 {
    public static void main(String[] args) {
        // Example 1. Expected output: Water Quantity is: 6
        int[] height = {0,1,0,2,1,0,1,3,2,1,2,1};
        System.out.println("Water Quantity is: " + trapWater(height));
    }

    // Two pointers from both ends; the lower of the two bars under the pointers is settled
    // first. O(n) time, O(1) space
    public static int trapWater(int[] height) {
        // nothing to hold water
        if (height == null || height.length == 0) {
            return 0;
        }

        int left = 0; // Start pointer
        int right = height.length - 1; // End pointer
        int leftMax = 0; // Maximum height on the left
        int rightMax = 0; // Maximum height on the right
        int water = 0; // Total water trapped

        while (left < right) {
            // Determine which side to process
            if (height[left] < height[right]) {
                // Process left side
                if (height[left] >= leftMax) {
                    leftMax = height[left];
                } else {
                    water += leftMax - height[left];
                }
                left++;
            } else {
                // Process right side
                if (height[right] >= rightMax) {
                    rightMax = height[right];
                } else {
                    water += rightMax - height[right];
                }
                right--;
            }
        }

        return water;
    }
}
