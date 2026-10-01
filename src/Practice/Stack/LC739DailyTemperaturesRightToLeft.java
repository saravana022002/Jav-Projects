package Practice.Stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

// ============================================================================
// LC 739. Daily Temperatures   [Medium]
// https://leetcode.com/problems/daily-temperatures/
// Topics: Array, Stack, Monotonic Stack
// ============================================================================
//
// PROBLEM
//   temperatures[i] is the temperature on day i. For every day, return how many days you
//   have to wait until a strictly warmer day; if no warmer day ever comes, the answer is 0.
//
// EXAMPLES
//   [73,74,75,71,69,72,76,73]  ->  [1,1,4,2,1,1,0,0]   e.g. day 2 (75) waits 4 days for 76
//   [30,40,50,60]              ->  [1,1,1,0]
//   [30,60,90]                 ->  [1,1,0]
//
// CONSTRAINTS
//   1 <= temperatures.length <= 10^5,   30 <= temperatures[i] <= 100
//
// INTUITION
//   Walk BACKWARDS from the last day and keep a stack of future days that could still be
//   somebody's "next warmer day". Once today is known, every future day that is not
//   warmer than today becomes useless for all earlier days too - today is closer and at
//   least as warm - so pop them. The day left on top is the nearest warmer day.
//
// APPROACHES IN THIS FILE   (same idea)
//   A. dailyTemperatures()  - java.util.Stack of Pair(temperature, index)
//   B. dailyTemperatures1() - ArrayDeque of indices only           <- main() runs this one
//
// APPROACH (as implemented below, method B)
//   1. For i from the last day down to 0:
//        pop every index whose temperature is <= temperatures[i]
//        answer[i] = top - i, or 0 if the stack is empty
//        push i
//
// DRY RUN   [30,40,50,60]   (the input main() uses; the stack holds indices, top first)
//   i=3 (60): stack empty        -> 0    stack [3]
//   i=2 (50): top 60 is warmer   -> 1    stack [2, 3]
//   i=1 (40): top 50 is warmer   -> 1    stack [1, 2, 3]
//   i=0 (30): top 40 is warmer   -> 1    => [1, 1, 1, 0]
//
// COMPLEXITY
//   Time : O(n) - every index is pushed and popped at most once
//   Space: O(n) - the stack
//
// GOTCHAS
//   - Pop on <= : an equal temperature is not warmer, so it can never be the answer.
//   - ArrayDeque's push/pop/peek work on the FRONT, so it behaves as a stack - and it is
//     faster than the old synchronized java.util.Stack.
//
// SEE ALSO
//   LC739DailyTemperaturesLeftToRight.java - the same stack idea, scanning forwards
//
// RELATED
//   - LC 496 Next Greater Element I - the same backward monotonic stack.
//   - LC 84 Largest Rectangle in Histogram - a harder monotonic-stack problem.
// ============================================================================
public class LC739DailyTemperaturesRightToLeft {

    public static void main(String[] args) {
        // Example 2 -> expected output: [1, 1, 1, 0]
        int[] temperatures = {30,40,50,60};
        System.out.println(Arrays.toString(dailyTemperatures1(temperatures)));
    }
    // Approach A: backward scan with a stack of (temperature, index) pairs
    public static int[] dailyTemperatures(int[] temperatures) {
        Stack<Pair> stk = new Stack<>();
        int[] array = new int[temperatures.length];
        for (int i = temperatures.length - 1; i >= 0; i--) {
            if(stk.empty()){
                array[i] = 0;
            }else {
                // future days not warmer than today can never be an answer again
                while (!stk.empty() && stk.peek().val <= temperatures[i]){
                    stk.pop();
                }
                // the top (if any) is the nearest warmer day
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


    // Approach B: the same backward scan, storing only indices in an ArrayDeque
    public static int[] dailyTemperatures1(int[] temperatures) {
        Deque<Integer> stk = new ArrayDeque<>();
        int n = temperatures.length;
        int[] array = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            // drop future days that are not warmer than today
            while (!stk.isEmpty() && temperatures[stk.peek()] <= temperatures[i]){
                stk.pop();
            }
            // nearest warmer day, or 0 when none is left
            array[i] = stk.isEmpty() ? 0 : stk.peek() - i;
            stk.push(i);
        }
        return array;
    }

    // (temperature, index) pair used by approach A
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
