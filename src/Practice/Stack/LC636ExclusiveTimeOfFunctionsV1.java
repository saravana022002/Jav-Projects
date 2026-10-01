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
//   1. map[id] = 0 for every id; prevTime = 0; parse each log into id, action, time.
//   2. "start": if a call is running, add time - prevTime to the one on top; push the
//      log; prevTime = time.
//   3. "end": left empty (see STATUS).
//   4. Copy the map into the answer array.
//
// COMPLEXITY   (once finished)
//   Time : O(L) - one pass over the L logs
//   Space: O(n + L) - the totals and the stack of open calls
//
// GOTCHAS
//   - End timestamps are inclusive, start timestamps are not - hence the "+ 1" and
//     prevTime = t + 1 on "end".
//   - Keep calls on the stack, not just ids: recursive calls of the same id must stack up.
//
// STATUS
//   Unfinished:
//   - the "end" branch is empty - it should pop the finished call, add
//     time - prevTime + 1 to it and set prevTime = time + 1;
//   - after the if/else EVERY log is pushed again (stk.push(log)), so the stack never
//     matches the real call stack;
//   - main() passes n = 1 while its logs use ids up to 7, so map.get(...) returns null
//     for id "1" and the run stops with a NullPointerException.
//   The code is left unchanged on purpose.
//
// SEE ALSO
//   LC636ExclusiveTimeOfFunctionsV2.java - a finished, correct version of this idea
// ============================================================================
public class LC636ExclusiveTimeOfFunctionsV1 {
    public static void main(String[] args) {
        // n = 1, but the logs use ids 0..7 -> throws NullPointerException (see STATUS)
        int n = 1;
        List<String> logs = Arrays.asList("0:start:0","1:start:5","2:start:6","3:start:9","4:start:11","5:start:12","6:start:14","7:start:15","1:start:24","1:end:29","7:end:34","6:end:37","5:end:39","4:end:40","3:end:45","0:start:49","0:end:54","5:start:55","5:end:59","4:start:63","4:end:66","2:start:69","2:end:70","2:start:74","6:start:78","0:start:79","0:end:80","6:end:85","1:start:89","1:end:93","2:end:96","2:end:100","1:end:102","2:start:105","2:end:109","0:end:114");
        exclusiveTime(n, logs);
    }

    public static int[] exclusiveTime(int n, List<String> logs) {
        // function id (as a String) -> exclusive time so far
        Map<String, Integer> map = new HashMap<>();
        // open calls, stored as their whole log line
        Stack<String> stk  = new Stack<>();
        for (int i = 0; i < n; i++) {
            map.put(i+"", 0);
        }
        // first time unit not yet credited to any function
        int prevTime = 0;
        for (String log : logs){
            String[] curr = log.split(":");

            String unique = curr[0];
            String action = curr[1];
            int time = Integer.parseInt(curr[2]);
            if(action.equals("start")){
                // the running call (top of the stack) gets the time up to this start
                if (!stk.isEmpty()) {
                    String[] prev = stk.peek().split(":");
                    map.put(prev[0], map.get(prev[0]) + time - prevTime);
                }
                stk.push(log);
                prevTime = time;
            }else {
                // missing: pop the finished call, credit it time - prevTime + 1,
                // then prevTime = time + 1 (see STATUS)
            }

            // pushes EVERY log a second time - this should not be here (see STATUS)
            stk.push(log);
        }


        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = map.get(i+"");
        }

        return ans;
    }
}
