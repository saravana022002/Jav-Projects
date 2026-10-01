package Practice.Stack;

import java.util.*;

// ============================================================================
// LC 636. Exclusive Time of Functions   [Medium]
// https://leetcode.com/problems/exclusive-time-of-functions/
// Topics: Array, Stack
// ============================================================================
//
// PROBLEM
//   A single-threaded CPU runs n functions (ids 0..n-1) and logs every call:
//   "id:start:t" means the call starts at the BEGINNING of time t, "id:end:t" means it
//   ends at the END of time t. Calls nest like a call stack (a function can even call
//   itself). A function's exclusive time is the time it was actually running - time
//   spent inside the functions it called does not count. Return every function's total.
//
// EXAMPLES
//   n = 2, logs = [0:start:0, 1:start:2, 1:end:5, 0:end:6]  ->  [3, 4]
//        0 runs 0-1, 1 runs 2-5, 0 resumes for time 6: 0 gets 2 + 1, 1 gets 4
//   n = 1, logs = [0:start:0, 0:start:2, 0:end:5, 0:start:6, 0:end:6, 0:end:7]  ->  [8]
//        recursion: every nested call of 0 counts for 0 as well
//   n = 2, logs = [0:start:0, 0:start:2, 0:end:5, 1:start:6, 1:end:6, 0:end:7]  ->  [7, 1]
//
// CONSTRAINTS
//   1 <= n <= 100,   2 <= logs.length <= 500,   0 <= function_id < n,
//   0 <= timestamp <= 10^9, no two starts (or two ends) share a timestamp,
//   every "start" has a matching "end"
//
// INTUITION
//   Replay the logs with a stack of running calls. Only the call on top is using the CPU,
//   so every time slice between two logs belongs to it. Keep prevTime = the first time
//   unit not yet credited to anyone:
//     start at t -> the running call gets t - prevTime; push the new call; prevTime = t
//     end at t   -> the finishing call gets t - prevTime + 1 (t itself is included);
//                   pop it; prevTime = t + 1
//
// APPROACH (as implemented below)
//   1. res[] = 0, prevTime = 0; split each log into id, action, time.
//   2. "start": if a call is running, res[top] += time - prevTime; push id;
//      prevTime = time.
//   3. "end": res[pop()] += time - prevTime + 1; prevTime = time + 1.
//   4. Return res.
//
// DRY RUN   n = 2, logs = [0:start:0, 1:start:2, 1:end:5, 0:end:6]  (main()'s input)
//   0:start:0  stack empty               push 0      prevTime = 0   res [0, 0]
//   1:start:2  res[0] += 2 - 0 = 2       push 1      prevTime = 2   res [2, 0]
//   1:end:5    res[1] += 5 - 2 + 1 = 4   pop 1       prevTime = 6   res [2, 4]
//   0:end:6    res[0] += 6 - 6 + 1 = 1   pop 0       prevTime = 7   res [3, 4]
//
// COMPLEXITY
//   Time : O(L) - one pass over the L logs
//   Space: O(n + L) - the result array and the stack of open calls
//
// GOTCHAS
//   - End timestamps are inclusive, start timestamps are not - hence the "+ 1" and
//     prevTime = time + 1 on "end".
//   - The stack holds calls: a recursive call of the same id is pushed again, and each
//     "end" pops exactly the call it closes.
//
// STATUS
//   Correct. main() does not print the result (it is [3, 4]).
//
// SEE ALSO
//   LC636ExclusiveTimeOfFunctionsV1.java - an unfinished attempt at the same idea
// ============================================================================
public class LC636ExclusiveTimeOfFunctionsV2 {

    public static void main(String[] args) {
        // Example 1 -> [3, 4] (the result is not printed)
        int noOfFunc = 2;
        List<String> arr = Arrays.asList("0:start:0", "1:start:2", "1:end:5", "0:end:6");
        exclusiveTime(noOfFunc, arr);
    }
    public static int[] exclusiveTime(int n, List<String> logs) {
        // first time unit not yet credited to any function
        int prevTime = 0;
        int[] res = new int[n];
        // ids of the calls that are open right now; the top one owns the CPU
        Stack<Integer> stk = new Stack<>();
        for (String log : logs) {
            String[] strings = log.split(":");
            int id = Integer.parseInt(strings[0]);
            String action = strings[1];
            int time = Integer.parseInt(strings[2]);
            if("start".equals(action)){
                // the running call is paused: it ran from prevTime up to (not incl.) time
                if(!stk.isEmpty()){
                    res[stk.peek()] += time - prevTime;
                }
                stk.push(id);
                prevTime = time;
            }else {
                // the call on top finishes at the END of time, so time itself counts (+1)
                res[stk.pop()] += time - prevTime + 1;
                prevTime = time + 1;
            }
        }
        return res;
    }
}
