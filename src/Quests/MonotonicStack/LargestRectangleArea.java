package Quests.MonotonicStack;

import java.util.Stack;

public class LargestRectangleArea {
    public static void main(String[] args) {
        int[] heights = {1,1};
        largestRectangleArea(heights);
    }
    public static int largestRectangleArea(int[] heights) {
        int max = heights[0];
        int element = 0;
        int width = 0;
        Stack<Integer> maxStack = new Stack<>();
        maxStack.push(0);
        for (int i = 1; i < heights.length; i++) {
            if(!maxStack.isEmpty() && heights[maxStack.peek()] <= heights[i]){
                maxStack.push(i);
            }else {
                while (!maxStack.isEmpty() && heights[maxStack.peek()] > heights[i]){
                    element = heights[maxStack.pop()];
                    if (maxStack.isEmpty()) {
                        width = i;
                    } else {
                        width = i - maxStack.peek() - 1;
                    }
                    max = Math.max(max, element * width);
                }
                maxStack.push(i);
            }
        }
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
