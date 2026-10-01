package Practice.Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

// ============================================================================
// LC 1441. Build an Array With Stack Operations   [Medium]
// https://leetcode.com/problems/build-an-array-with-stack-operations/
// Topics: Array, Stack, Simulation
// ============================================================================
//
// PROBLEM
//   The numbers 1, 2, ..., n arrive one at a time. Starting from an empty stack you may
//   "Push" the next number or "Pop" the top. Return the list of operations that leaves
//   the stack (bottom -> top) equal to target, and stop the moment it matches - reading
//   more numbers after that is not allowed. target is strictly increasing.
//
// EXAMPLES
//   target = [1,3],   n = 3  ->  [Push, Push, Pop, Push]   2 is pushed and thrown away
//   target = [1,2,3], n = 3  ->  [Push, Push, Push]
//   target = [1,2],   n = 4  ->  [Push, Push]              stop before reading 3
//
// CONSTRAINTS
//   1 <= target.length <= 100,   1 <= n <= 100,   1 <= target[i] <= n,
//   target is strictly increasing
//
// INTUITION
//   The stream is increasing and so is target, so every stream number needs exactly one
//   decision: wanted -> "Push" and keep it; not wanted -> "Push" then "Pop" right away.
//   Nothing ever has to be popped later, so no real stack is needed - just emit the
//   words. Stop as soon as the last target value is pushed.
//
// APPROACH (as implemented below)
//   1. j = index of the next wanted value in target.
//   2. For i = 1 .. n:
//        i == target[j] -> "Push", j++; if j reached the end of target, stop
//        otherwise      -> "Push", "Pop"
//
// DRY RUN   target = [1,3], n = 3
//   i = 1: wanted      -> Push        j = 1
//   i = 2: not wanted  -> Push, Pop
//   i = 3: wanted      -> Push        j = 2 = target.length -> stop
//   result [Push, Push, Pop, Push]
//
// COMPLEXITY
//   Time : O(n) - one step per stream number, up to the last wanted one
//   Space: O(1) besides the output list (at most 2n words)
//
// GOTCHAS
//   - Break right after the last wanted number (example 3); continuing would add extra
//     operations and the answer would be rejected.
//   - java.util.Stack is imported but never used - the simulation needs no stack.
//
// STATUS
//   Correct. main() does not print the result (it is [Push, Push]).
//
// SEE ALSO
//   LC1441BuildAnArrayWithStackOperationsV2.java - same idea; checks "all pushed" at the
//     top of the loop instead of right after the push
//   src/Leetcode/CountingStack/LC1441BuildArrayWithStack.java - your curated version;
//     loops over target and fills each gap with Push/Pop pairs
// ============================================================================
public class LC1441BuildAnArrayWithStackOperationsV1 {
    public static void main(String[] args) {
        // Example 3 -> [Push, Push] (the result is not printed)
        int[] target = {1,2};
        int n = 4;
        buildArray(target, n);
    }
    public static List<String> buildArray(int[] target, int n) {
        List<String> list = new ArrayList<>();
        // index of the next value we still need from the stream
        int j = 0;
        for (int i = 1; i <= n; i++) {
            if(target[j] == i){
                // wanted: push it and keep it
                list.add("Push");
                j++;
                // target is complete -> must stop reading the stream
                if(j >= target.length){
                    break;
                }
            }else{
                // not wanted: push it and immediately throw it away
                list.add("Push");
                list.add("Pop");
            }
        }
        return list;
    }
}
