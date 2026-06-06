package Quests.MonotonicStack;

import SDE.StackAndQueue.LargestRectangleInHistogram;

import java.util.Arrays;
import java.util.Stack;

public class DailyTemperatures {
    public static void main(String[] args) {
        int[] temperatures = {73,74,75,71,69,72,76,73};
        System.out.println(Arrays.toString(dailyTemperaturesStack(temperatures)));
    }

    public static int[] dailyTemperatures(int[] temperatures) {
        int[] answer = new int[temperatures.length];
        int count = 0;
        for (int i = 0; i < temperatures.length; i++) {
            for (int j = i + 1; j < temperatures.length; j++) {
                if(temperatures[j] > temperatures[i]){
                    answer[i] = count + 1;
                    count = 0;
                    break;
                }
                count++;
            }
            if(answer[i] == 0){
                count = 0;
            }
        }
        return answer;
    }


    public static int[] dailyTemperaturesStack(int[] temperatures) {
        Stack<Integer> stk = new Stack<>();
        int[] ans = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            while (!stk.isEmpty() && temperatures[stk.peek()] < temperatures[i]){
                ans[stk.peek()] = i - stk.peek();
                stk.pop();
            }
            stk.push(i);
        }
        return ans;
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
