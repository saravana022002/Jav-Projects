package Practice.Stack;

import java.util.Stack;

// ============================================================================
// GFG - Sort a stack   [Medium]
// https://www.geeksforgeeks.org/problems/sort-a-stack/1
// Topics: Stack, Sorting
// ============================================================================
//
// PROBLEM
//   Sort a stack of integers so the smallest value ends up at the bottom and the largest
//   at the top. You may only use stack operations (push, pop, peek, isEmpty) - no
//   indexing into it and no library sort.
//
// EXAMPLES   (GFG writes the result from the top down)
//   stack [41, 3, 32, 2, 11]  ->  [41, 32, 11, 3, 2]    41 on top, 2 at the bottom
//   stack [3, 2, 1]           ->  [3, 2, 1]             already sorted
//
// CONSTRAINTS
//   See the link above for the full constraints.
//
// INTUITION
//   This is insertion sort with a second stack. Keep a helper stack that is always sorted
//   (largest at ITS bottom, smallest on its top). To insert a new value, temporarily move
//   the helper's smaller values back onto the input stack, drop the new value in place,
//   and let those smaller values be re-inserted later. When the input is empty, pouring
//   the helper back reverses it: smallest at the bottom, largest on top.
//
// APPROACH (as implemented below)
//   1. While stk is not empty: current = stk.pop().
//        While tempStk's top is smaller than current, move that top back onto stk.
//        Push current onto tempStk (now nothing smaller sits under it).
//   2. Pour tempStk back into stk one by one and return stk (it is sorted in place).
//
// DRY RUN   stk = [3, 1, 2]   (bottom -> top)
//   pop 2: tempStk empty                   -> tempStk [2]
//   pop 1: top 2 is not < 1                -> tempStk [2, 1]
//   pop 3: 1 < 3 and 2 < 3 go back to stk  -> stk [1, 2],  tempStk [3]
//   pop 2: top 3 is not < 2                -> tempStk [3, 2]
//   pop 1: top 2 is not < 1                -> tempStk [3, 2, 1]
//   pour back                              -> stk [1, 2, 3]  (3 on top)
//
// COMPLEXITY
//   Time : O(n^2) - a value can be moved back and forth once per larger value after it
//   Space: O(n)   - the helper stack
//
// GOTCHAS
//   - The strict < matters: with <=, two equal values keep swapping between the stacks
//     and the loop never ends (try [5, 5]).
//   - The result is written back into the SAME stack object that was passed in.
//
// STATUS
//   The method is correct. main() sorts its stack but prints nothing
//   (the result, bottom -> top, is [1, 2, 3, 5, 6]).
//
// SOURCE
//   Source: GeeksforGeeks (found via Striver SDE Sheet).
// ============================================================================
public class GFGSortAStack {
    public static void main(String[] args) {
        // pushes 2, 3, 6, 1, 5 -> sorted bottom -> top: [1, 2, 3, 5, 6] (not printed)
        Stack<Integer> stk = new Stack<>();
        stk.push(2);
        stk.push(3);
        stk.push(6);
        stk.push(1);
        stk.push(5);
        sortAStack(stk);
    }
    // sorts stk in place (smallest at the bottom, largest on top) and returns it
    public static Stack<Integer> sortAStack(Stack<Integer> stk){
        // helper stack kept sorted: largest at its bottom, smallest on its top
        Stack<Integer> tempStk = new Stack<>();
        while (!stk.isEmpty()){
            int current = stk.pop();
            // make room: smaller values go back to stk and will be re-inserted later
            while(!tempStk.isEmpty() && tempStk.peek() < current){
                stk.push(tempStk.pop());
            }
            tempStk.push(current);
        }

        // pour back: the smallest comes out first, so it lands at the bottom of stk
        while (!tempStk.isEmpty()){
            stk.push(tempStk.pop());
        }
        return stk;
    }
}
