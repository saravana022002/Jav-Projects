package Casual;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

public class DailyTemperatures {

    public static void main(String[] args) {
        int[] temperatures = {30,40,50,60};
        System.out.println(Arrays.toString(dailyTemperatures1(temperatures)));
    }
    public static int[] dailyTemperatures(int[] temperatures) {
        Stack<Pair> stk = new Stack<>();
        int[] array = new int[temperatures.length];
        for (int i = temperatures.length - 1; i >= 0; i--) {
            if(stk.empty()){
                array[i] = 0;
            }else {
                while (!stk.empty() && stk.peek().val <= temperatures[i]){
                    stk.pop();
                }
                if(!stk.empty()) {
                    array[i] = stk.peek().idx - i;
                }else {
                    array[i] = 0;
                }
            }
            stk.push(new Pair(temperatures[i], i));
        }
        return array;
    }


    public static int[] dailyTemperatures1(int[] temperatures) {
        Deque<Integer> stk = new ArrayDeque<>();
        int n = temperatures.length;
        int[] array = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            while (!stk.isEmpty() && temperatures[stk.peek()] <= temperatures[i]){
                stk.pop();
            }
            array[i] = stk.isEmpty() ? 0 : stk.peek() - i;
            stk.push(i);
        }
        return array;
    }

    static class Pair{
        Pair(){}

        public Pair(int val, int idx) {
            this.val = val;
            this.idx = idx;
        }

        int val;
        int idx;
    }
}
