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
//   The best rectangle is as tall as some bar and stretches left and right until a
//   SHORTER bar blocks it: area = height * (nse - pse - 1), where nse / pse are the
//   indices of the next / previous smaller element. Keep a stack of bars with increasing
//   heights. When bar i is not taller than the top, i is the top's nse and the bar below
//   it on the stack is its pse - so pop it and measure its rectangle right away.
//
// APPROACH (as implemented below)
//   1. For each bar i: while the top's height >= heights[i]:
//        pop it; nse = i; pse = new top's index, or -1 if the stack is empty;
//        maxRectangle = max(maxRectangle, height * (nse - pse - 1))
//      then push (i, heights[i]).
//   2. Drain the bars that are left with nse = heights.length (no smaller bar to the right).
//
// DRY RUN   heights = [2,1,5,6,2,3]   (the input main() uses)
//   i=1 (1): pop 2 -> nse 1, pse -1 -> 2 * 1 = 2          stack [(1,1)]
//   i=2, 3 : push 5 and 6                                  stack [(1,1), (2,5), (3,6)]
//   i=4 (2): pop 6 -> nse 4, pse 2 -> 6 * 1 = 6
//            pop 5 -> nse 4, pse 1 -> 5 * 2 = 10           stack [(1,1), (4,2)]
//   i=5 (3): push                                          stack [(1,1), (4,2), (5,3)]
//   drain  : 3 * 1, 2 * 4, 1 * 6  (nse = 6)                => 10
//
// COMPLEXITY
//   Time : O(n) - every bar is pushed and popped exactly once
//   Space: O(n) - the stack of pairs
//
// GOTCHAS
//   - It pops on >= : an earlier bar of EQUAL height is closed too early and gets a
//     too-small width, but the later equal bar covers the full width, so the maximum is
//     still right.
//   - pse = -1 when the stack is empty: that bar reaches the left edge.
//   - height * width <= 10^4 * 10^5 = 10^9, so int does not overflow.
//
// SEE ALSO
//   LC84LargestRectangleInHistogramIndexStack.java - same idea, stack of indices only
//
// RELATED
//   - LC 85 Maximal Rectangle - applies this algorithm to every row of a 0/1 matrix.
//   - LC 739 Daily Temperatures - the same "pop when the next smaller/larger arrives" stack.
// ============================================================================
public class LC84LargestRectangleInHistogramPairStack {
    public static void main(String[] args) {
        // Example 1 -> expected output: 10
        int[] heights = {2,1,5,6,2,3};
        System.out.println(largestRectangleArea(heights));
    }

    public static int largestRectangleArea(int[] heights) {
        // (index, height) pairs with increasing heights, bottom -> top
        Stack<Pair> stk = new Stack<>();
        int maxRectangle = 0;
        // nse / pse = index of the next / previous smaller element of the popped bar
        int nse = 0;
        int pse = 0;
        int element = 0;
        for (int i = 0; i < heights.length; i++) {
            // bar i is the next smaller element of every taller-or-equal bar on top
            while(!stk.isEmpty() && stk.peek().value >= heights[i]){
                element = stk.pop().value;
                nse = i;
                pse = stk.isEmpty() ? -1 : stk.peek().key;
                // widest rectangle of this height: strictly between pse and nse
                maxRectangle = Math.max(maxRectangle, element * (nse - pse - 1));
            }
            stk.push(new Pair(i, heights[i]));
        }

        // leftover bars have no smaller bar to the right: nse = n
        while (!stk.isEmpty()){
            nse = heights.length;
            element = stk.pop().value;
            pse = stk.isEmpty() ? -1 : stk.peek().key;
            maxRectangle = Math.max(maxRectangle, element * (nse - pse - 1));
        }
        return maxRectangle;
    }

    // key = bar index, value = bar height
    public static class Pair {
        int key;
        int value;
        Pair(int k, int v) {
            this.key = k;
            this.value = v;
        }
    }

}
