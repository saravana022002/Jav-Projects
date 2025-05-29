package SDE.StackAndQueue;

import java.util.Stack;

public class LargestRectangleInHistogram {
    public static void main(String[] args) {
        int[] heights = {2,1,5,6,2,3};
        System.out.println(largestRectangleArea(heights));
    }

    public static int largestRectangleArea(int[] heights) {
        Stack<Pair> stk = new Stack<>();
        int maxRectangle = 0;
        int nse = 0;
        int pse = 0;
        int element = 0;
        for (int i = 0; i < heights.length; i++) {
            while(!stk.isEmpty() && stk.peek().value >= heights[i]){
                element = stk.pop().value;
                nse = i;
                pse = stk.isEmpty() ? -1 : stk.peek().key;
                maxRectangle = Math.max(maxRectangle, element * (nse - pse - 1));
            }
            stk.push(new Pair(i, heights[i]));
        }

        while (!stk.isEmpty()){
            nse = heights.length;
            element = stk.pop().value;
            pse = stk.isEmpty() ? -1 : stk.peek().key;
            maxRectangle = Math.max(maxRectangle, element * (nse - pse - 1));
        }
        return maxRectangle;
    }

    public static class Pair {
        int key;
        int value;
        Pair(int k, int v) {
            this.key = k;
            this.value = v;
        }
    }

}
