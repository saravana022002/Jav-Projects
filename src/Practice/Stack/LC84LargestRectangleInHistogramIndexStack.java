package Practice.Stack;

import java.util.Stack;

// ============================================================================
// LC 84. Largest Rectangle in Histogram   [Hard]
// https://leetcode.com/problems/largest-rectangle-in-histogram/
// Topics: Array, Stack, Monotonic Stack, Range Minimum/Maximum Query
// ============================================================================
//
// PROBLEM
//   heights[i] is the height of bar i in a histogram; every bar is 1 wide. Return the
//   area of the largest rectangle that fits entirely inside the bars.
//
// EXAMPLES
//   heights = [2,1,5,6,2,3]  ->  10     the bars 5 and 6 give a 5-high, 2-wide rectangle
//   heights = [2,4]          ->  4
//
// CONSTRAINTS
//   1 <= heights.length <= 10^5,   0 <= heights[i] <= 10^4
//
// INTUITION
//   The best rectangle is as tall as some bar j and stretches left and right until a
//   SHORTER bar blocks it: width = (next shorter index) - (previous shorter index) - 1.
//   Keep a stack of bar indices whose heights increase from bottom to top. When bar i is
//   shorter than the top, the top bar has just found its next shorter bar (i), and the bar
//   below it on the stack is its previous shorter bar - so its rectangle is known exactly:
//   pop it and measure. Every bar is pushed and popped once.
//
// APPROACH (as implemented below)
//   1. Push index 0. For each later bar i:
//        heights[top] <= heights[i] -> push i (the stack stays increasing)
//        otherwise: while heights[top] > heights[i]:
//          pop the top; width = i if the stack is now empty (it reaches the left edge),
//          else i - newTop - 1; max = max(max, height * width)
//        then push i
//   2. Bars left on the stack have no shorter bar to their right: drain them the same
//      way with heights.length as the right boundary.
//
// DRY RUN   heights = [2,1,5,6,2,3]   (main() runs [1,1], whose answer is 2)
//   i=1 (1): pop 2 -> width 1 (left edge)     area 2      stack [1]
//   i=2, i=3: 5 and 6 are taller -> push                   stack [1, 2, 3]
//   i=4 (2): pop 6 -> width 4 - 2 - 1 = 1     area 6
//            pop 5 -> width 4 - 1 - 1 = 2     area 10     stack [1, 4]
//   i=5 (3): push                                          stack [1, 4, 5]
//   drain  : 3 * 1 = 3,  2 * 4 = 8,  1 * 6 = 6            => max 10
//
// COMPLEXITY
//   Time : O(n) - every bar is pushed and popped exactly once
//   Space: O(n) - the stack
//
// GOTCHAS
//   - An empty stack after a pop means that bar was the lowest so far: its rectangle
//     reaches all the way to the left edge (width = i, or heights.length when draining).
//   - Do not forget the final drain - increasing bars at the end are never popped by i.
//   - height * width <= 10^4 * 10^5 = 10^9, so int does not overflow.
//
// STATUS
//   Correct. main() does not print the result (it is 2).
//
// SEE ALSO
//   LC84LargestRectangleInHistogramPairStack.java - same idea with (index, height) pairs
//
// RELATED
//   - LC 85 Maximal Rectangle - applies this algorithm to every row of a 0/1 matrix.
//   - LC 739 Daily Temperatures - the same "pop when the next smaller/larger arrives" stack.
// ============================================================================
public class LC84LargestRectangleInHistogramIndexStack {
    public static void main(String[] args) {
        // two bars of height 1 -> 2 (the result is not printed)
        int[] heights = {1,1};
        largestRectangleArea(heights);
    }
    public static int largestRectangleArea(int[] heights) {
        int max = heights[0];
        int element = 0;
        int width = 0;
        // indices of bars with non-decreasing heights (bottom -> top)
        Stack<Integer> maxStack = new Stack<>();
        maxStack.push(0);
        for (int i = 1; i < heights.length; i++) {
            if(!maxStack.isEmpty() && heights[maxStack.peek()] <= heights[i]){
                // still rising: bar i may extend the rectangles of the bars below it
                maxStack.push(i);
            }else {
                // bar i is shorter: every taller bar on top now knows both of its borders
                while (!maxStack.isEmpty() && heights[maxStack.peek()] > heights[i]){
                    element = heights[maxStack.pop()];
                    if (maxStack.isEmpty()) {
                        // lowest bar so far: stretches back to the left edge
                        width = i;
                    } else {
                        // between the previous shorter bar (new top) and bar i
                        width = i - maxStack.peek() - 1;
                    }
                    max = Math.max(max, element * width);
                }
                maxStack.push(i);
            }
        }
        // bars still on the stack have no shorter bar to their right: right border = n
        if(!maxStack.isEmpty()){
            while (!maxStack.isEmpty()){
                element = heights[maxStack.pop()];
                if (maxStack.isEmpty()) {
                    width = heights.length;
                } else {
                    width = heights.length - maxStack.peek() - 1;
                }
                max = Math.max(max, element * width);
            }
        }
        return max;
    }

}
