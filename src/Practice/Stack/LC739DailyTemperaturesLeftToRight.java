package Practice.Stack;

import Practice.Stack.LC84LargestRectangleInHistogramPairStack;

import java.util.Arrays;
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
//   Walk forward through the days and keep a stack of the days that are still WAITING for
//   a warmer day. Their temperatures never increase from bottom to top (a warmer day
//   would already have answered the colder ones). So when today is warmer than the top,
//   today is the answer for that day - pop it, record the distance, and repeat with the
//   next one down. Each day is pushed once and answered (popped) at most once.
//
// APPROACHES IN THIS FILE
//   A. dailyTemperatures()      - brute force: for each day scan forward for a warmer day;
//                                 count = days checked so far - O(n^2)
//   B. dailyTemperaturesStack() - the waiting-days stack above - O(n)  <- main() runs this
//
// APPROACH (as implemented below, method B)
//   1. For each day i:
//        while the day on top of the stack is colder than day i:
//          ans[top] = i - top, pop it
//        push i (it now waits for its own warmer day)
//   2. Days still on the stack never got a warmer day and keep ans = 0.
//
// DRY RUN   [73,74,75,71,69,72,76,73]   (the input main() uses; the stack holds indices)
//   i=0..2: each day is warmer than the one before -> ans[0] = 1, ans[1] = 1, stack [2]
//   i=3 (71), i=4 (69): colder than 75            -> stack [2, 3, 4]
//   i=5 (72): answers 69 (1 day) and 71 (2 days)  -> stack [2, 5]
//   i=6 (76): answers 72 (1 day) and 75 (4 days)  -> stack [6]
//   i=7 (73): colder -> stack [6, 7]; they stay 0   => [1,1,4,2,1,1,0,0]
//
// COMPLEXITY
//   Time : B: O(n) - every index is pushed and popped at most once;  A: O(n^2)
//   Space: B: O(n) - the stack;  A: O(1) extra
//
// GOTCHAS
//   - Store INDICES on the stack, not temperatures - the answer is a distance.
//   - Pop only on strictly warmer (<): an equal temperature is not warmer.
//
// STATUS
//   Both methods are correct. The import of LC84LargestRectangleInHistogramPairStack and
//   the nested Pair class are unused leftovers.
//
// SEE ALSO
//   LC739DailyTemperaturesRightToLeft.java - the same stack idea, scanning backwards
//
// RELATED
//   - LC 496 Next Greater Element I - the same "next greater" monotonic stack.
//   - LC 84 Largest Rectangle in Histogram - a harder monotonic-stack problem.
// ============================================================================
public class LC739DailyTemperaturesLeftToRight {
    public static void main(String[] args) {
        // Example 1 -> expected output: [1, 1, 4, 2, 1, 1, 0, 0]
        int[] temperatures = {73,74,75,71,69,72,76,73};
        System.out.println(Arrays.toString(dailyTemperaturesStack(temperatures)));
    }

    // Approach A: brute force - for each day scan forward for the first warmer day
    public static int[] dailyTemperatures(int[] temperatures) {
        int[] answer = new int[temperatures.length];
        // days checked so far for day i without finding a warmer one
        int count = 0;
        for (int i = 0; i < temperatures.length; i++) {
            for (int j = i + 1; j < temperatures.length; j++) {
                if(temperatures[j] > temperatures[i]){
                    // count + 1 == j - i
                    answer[i] = count + 1;
                    count = 0;
                    break;
                }
                count++;
            }
            // no warmer day found: answer stays 0, reset the counter for the next day
            if(answer[i] == 0){
                count = 0;
            }
        }
        return answer;
    }


    // Approach B: stack of days still waiting for a warmer day (left to right)
    public static int[] dailyTemperaturesStack(int[] temperatures) {
        Stack<Integer> stk = new Stack<>();
        int[] ans = new int[temperatures.length];
        for (int i = 0; i < temperatures.length; i++) {
            // today is the warmer day every colder waiting day was looking for
            while (!stk.isEmpty() && temperatures[stk.peek()] < temperatures[i]){
                ans[stk.peek()] = i - stk.peek();
                stk.pop();
            }
            // today starts waiting for its own warmer day
            stk.push(i);
        }
        return ans;
    }
    // unused leftover (not needed by either method)
    public static class Pair {
        int key;
        int value;
        Pair(int k, int v) {
            this.key = k;
            this.value = v;
        }
    }


    }
