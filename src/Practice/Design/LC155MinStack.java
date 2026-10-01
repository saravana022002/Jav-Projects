package Practice.Design;

import java.util.Stack;

// ============================================================================
// LC 155. Min Stack   [Medium]
// https://leetcode.com/problems/min-stack/
// Topics: Stack, Design
// ============================================================================
//
// PROBLEM
//   Design an int stack with the usual push(val), pop() and top(), plus getMin(), which
//   returns the smallest value currently in the stack. Every operation must be O(1).
//   pop, top and getMin are only ever called when the stack is not empty.
//
// EXAMPLES
//   push(-2), push(0), push(-3)
//   getMin()  -> -3
//   pop()                  removes -3
//   top()     -> 0
//   getMin()  -> -2        -3 is gone, the smallest value left is -2
//   Output: [null,null,null,null,-3,null,0,-2]
//
// CONSTRAINTS
//   -2^31 <= val <= 2^31 - 1
//   pop, top and getMin are always called on non-empty stacks
//   At most 3 * 10^4 calls will be made to push, pop, top, and getMin.
//
// INTUITION
//   The textbook fix is a second stack holding "min so far". This file avoids it: it keeps
//   ONE variable min and hides the previous minimum inside the pushed value itself.
//     - Pushing val < min: store 2*val - min instead of val, then min = val.
//       Since val < min, 2*val - min = val - (min - val) < val, i.e. below the NEW min.
//     - Every normally stored x is >= the min that was current when x was pushed.
//   So a top that is SMALLER than min must be an encoded entry. Its real value is min
//   itself, and the previous minimum comes back as 2*min - top:
//     2*val - (2*val - oldMin) = oldMin.
//
// APPROACH (as implemented below)
//   1. push(val) on an empty stack: min = val, store val.
//   2. push(val) with min > val: store 2L*val - min (the marker), then min = val.
//      Otherwise store val as it is.
//   3. pop(): if stack.peek() < min it is a marker -> restore min = 2L*min - peek, then pop.
//   4. top(): if stack.peek() < min the real top is min; otherwise it is the stored value.
//   5. getMin(): return min.
//
// DRY RUN   the LC example (exactly what main() replays)
//   op         stack (bottom -> top)   min   note
//   push(-2)   [-2]                    -2    empty stack: stored as is
//   push(0)    [-2, 0]                 -2    0 >= min: stored as is
//   push(-3)   [-2, 0, -4]             -3    -3 < -2: store 2*(-3) - (-2) = -4
//   getMin     -> -3
//   pop        [-2, 0]                 -2    -4 < -3 is a marker: min = 2*(-3) - (-4) = -2
//   top        -> 0                          0 >= min, a normal value
//   getMin     -> -2
//
// COMPLEXITY
//   Time : O(1) per operation - one stack operation plus a little arithmetic
//   Space: O(n) - one long per element, no second stack
//
// GOTCHAS
//   - Overflow: 2*val - min can reach about -6.4 * 10^9 (val = -2^31, min = 2^31 - 1), so
//     the stack holds Long and the math uses 2L; a Stack<Integer> version breaks.
//   - Encode only on a strict min > val. A value equal to min is stored as is, and when it
//     is popped peek() < min is false, so min correctly stays.
//   - The empty-stack branch of push resets min, so a stale min left by earlier pops is
//     never used.
//   - Outside LC's contract: top() on an empty stack returns -1, pop() does nothing, and
//     getMin() returns a stale value.
//
// RELATED
//   - LC 716 Max Stack - the mirror design with max plus popMax (premium).
// ============================================================================
public class LC155MinStack {
    public static void main(String[] args) {
        // Replays the LC example: operations[i] is called with values[i].
        // Expected output, one per line: null null null null -3 null 0 -2
        // Input simulation
        String[] operations = {
                "MinStack", "push", "push", "push", "getMin", "pop", "top", "getMin"
        };
        int[][] values = {
                {}, {-2}, {0}, {-3}, {}, {}, {}, {}
        };

        LC155MinStack minStack = null;
        for (int i = 0; i < operations.length; i++) {
            switch (operations[i]) {
                case "MinStack":
                    minStack = new LC155MinStack();
                    System.out.println("null");
                    break;
                case "push":
                    minStack.push(values[i][0]);
                    System.out.println("null");
                    break;
                case "pop":
                    minStack.pop();
                    System.out.println("null");
                    break;
                case "top":
                    int top = minStack.top();
                    System.out.println(top);
                    break;
                case "getMin":
                    int min = minStack.getMin();
                    System.out.println(min);
                    break;
            }
        }
    }



    // min = current minimum of the stack. Stored entries are longs: either a real value
    // (>= min) or an encoded marker 2*val - oldMin (< min) left by a new minimum.
    int min;
    Stack<Long> stack;
    public LC155MinStack() {
        this.min = Integer.MAX_VALUE;
        this.stack = new Stack<>();
    }

    public void push(int val) {
        if(stack.empty()){
            // first element: it is the minimum, store it plainly
            min = val;
            stack.push((long) val);
        }else {
            if(min > val){
                // new minimum: store a marker below val that still encodes the old min
                stack.push(2L * val - min);
                min = val;
            }else {
                stack.push((long) val);
            }
        }
    }

    public void pop() {
        if(stack.isEmpty()){
            return;
        }
        // a marker (below min) is being removed -> bring back the previous minimum
        if(stack.peek() < min){
            min = (int) (2L * min - stack.peek());
        }
        stack.pop();
    }

    public int top() {
        if(stack.isEmpty()){
            return -1;
        }
        long top = stack.peek();
        // a marker on top stands for the value that became min when it was pushed
        if(stack.peek() < min){
            return min;
        }else {
            return (int) top;
        }
    }

    // O(1): min is always kept up to date by push and pop.
    public int getMin() {
        return min;
    }
}
