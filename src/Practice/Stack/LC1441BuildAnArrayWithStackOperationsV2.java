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
//   words. Stop as soon as every target value has been pushed.
//
// APPROACH (as implemented below)
//   1. j = index of the next wanted value in target.
//   2. For i = 1 .. n:
//        all of target pushed (j == target.length) -> stop
//        i == target[j] -> j++, "Push"
//        otherwise      -> "Push", "Pop"
//
// DRY RUN   target = [1,2], n = 4 (the input main() uses)
//   i = 1: wanted     -> Push   j = 1
//   i = 2: wanted     -> Push   j = 2
//   i = 3: j == 2 == target.length -> stop before touching 3
//   prints [Push, Push]
//
// COMPLEXITY
//   Time : O(n) - one step per stream number, up to the last wanted one
//   Space: O(1) besides the output list (at most 2n words)
//
// GOTCHAS
//   - The "done" check must come before handling i; otherwise target[j] would be read
//     past the end of the array once everything is pushed.
//   - java.util.Stack is imported but never used - the simulation needs no stack.
//
// SEE ALSO
//   LC1441BuildAnArrayWithStackOperationsV1.java - same idea; breaks right after the
//     last push instead of at the top of the loop
//   src/Leetcode/CountingStack/LC1441BuildArrayWithStack.java - your curated version;
//     loops over target and fills each gap with Push/Pop pairs
// ============================================================================
public class LC1441BuildAnArrayWithStackOperationsV2 {
    public static void main(String[] args) {
        // Example 3 -> expected output: [Push, Push]
        int[] target = {1,2};
        int n = 4;
        System.out.println(buildArray(target, n));
    }
    public static List<String> buildArray(int[] target, int n) {
        List<String> ans = new ArrayList<>();
        // index of the next value we still need from the stream
        int j = 0;
        for (int i = 1; i <= n; i++) {
            // everything pushed -> stop reading the stream (also keeps target[j] in range)
            if (j == target.length) break;
            if(target[j] == i){
                // wanted: push it and keep it
                j++;
                ans.add("Push");
            }else {
                // not wanted: push it and immediately throw it away
                ans.add("Push");
                ans.add("Pop");
            }
        }
        return ans;
    }
}
