package Practice.Stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

// ============================================================================
// LC 496. Next Greater Element I   [Easy]
// https://leetcode.com/problems/next-greater-element-i/
// Topics: Array, Hash Table, Stack, Monotonic Stack
// ============================================================================
//
// PROBLEM
//   The "next greater element" of a value in nums2 is the first value to its RIGHT in
//   nums2 that is bigger (or -1 if there is none). nums1 is a subset of nums2, all values
//   are distinct. For every value in nums1 return its next greater element in nums2.
//
// EXAMPLES
//   nums1 = [4,1,2], nums2 = [1,3,4,2]  ->  [-1,3,-1]   nothing right of 4 is bigger;
//                                                       1 -> 3; nothing right of 2
//   nums1 = [2,4],   nums2 = [1,2,3,4]  ->  [3,-1]
//
// CONSTRAINTS
//   1 <= nums1.length <= nums2.length <= 1000,   0 <= values <= 10^4,
//   all values unique, every value of nums1 appears in nums2
//   Follow-up: O(nums1.length + nums2.length).
//
// INTUITION
//   Answer the question once for EVERY value of nums2, then just look nums1 up. Scan
//   nums2 from right to left and keep a stack of candidates: values to the right that
//   are not hidden behind a bigger, closer value. For the current value, pop every
//   candidate <= it (they can never be anyone's answer further left either, because the
//   current value is bigger and closer). Whatever remains on top is its answer.
//
// APPROACH (as implemented below)
//   1. For i from the end of nums2 down to 0:
//        pop while the top <= nums2[i]
//        answer = top, or -1 if the stack is empty; store map[nums2[i]] = answer
//        push nums2[i]
//   2. Replace each nums1[i] by map[nums1[i]] and return nums1.
//
// DRY RUN   nums2 = [1,3,4,2]   (the input main() uses)
//   2: stack empty          -> 2 -> -1   stack [2]
//   4: pop 2                -> 4 -> -1   stack [4]
//   3: top 4 > 3            -> 3 -> 4    stack [4, 3]
//   1: top 3 > 1            -> 1 -> 3    stack [4, 3, 1]
//   nums1 [4,1,2] -> [-1, 3, -1]
//
// COMPLEXITY
//   Time : O(n + m) - each value of nums2 is pushed and popped at most once, plus m lookups
//   Space: O(n)     - the map and the stack
//
// GOTCHAS
//   - The method writes the answers INTO nums1, so the caller's array is changed.
//   - A value -> answer map only works because all values are distinct.
//
// STATUS
//   Correct. main() does not print the result (it is [-1, 3, -1]).
//
// RELATED
//   - LC 503 Next Greater Element II - the same question on a circular array.
//   - LC 739 Daily Temperatures - the same monotonic stack, returning distances.
// ============================================================================
public class LC496NextGreaterElementI {
    public static void main(String[] args) {
        // Example 1 -> [-1, 3, -1] (the result is not printed)
        int[] nums1 = {4,1,2};
        int[] nums2 = {1,3,4,2};
        nextGreaterElement(nums1, nums2);
    }

    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // value in nums2 -> its next greater element (-1 if none)
        Map<Integer, Integer> integerIntegerMap = new HashMap<>();
        // candidates to the right, decreasing from bottom to top
        Stack<Integer> stack = new Stack<>();
        for (int i = nums2.length - 1; i >= 0; i--) {
            // smaller-or-equal candidates are hidden behind nums2[i] from now on
            while (!stack.empty() && stack.peek() <= nums2[i]){
                stack.pop();
            }
            // the first bigger value to the right, if any
            if(stack.empty()){
                integerIntegerMap.put(nums2[i], -1);
            }else {
                integerIntegerMap.put(nums2[i], stack.peek());
            }
            stack.push(nums2[i]);
        }
        // answer the queries in place (nums1 is overwritten)
        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = integerIntegerMap.get(nums1[i]);
        }
        return nums1;
    }
}
