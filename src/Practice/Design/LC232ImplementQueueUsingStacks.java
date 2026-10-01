package Practice.Design;

import java.util.Stack;

// ============================================================================
// LC 232. Implement Queue using Stacks   [Easy]
// https://leetcode.com/problems/implement-queue-using-stacks/
// Topics: Stack, Design, Queue
// ============================================================================
//
// PROBLEM
//   Build a FIFO queue - push(x) to the back, pop() and peek() at the front, empty() -
//   out of two stacks, using only the stack operations: push / peek / pop at the top,
//   size and isEmpty. pop and peek are only called when the queue is not empty.
//   Follow-up: make every operation amortized O(1).
//
// EXAMPLES
//   push(1), push(2)     queue is [1, 2], front on the left
//   peek()   -> 1
//   pop()    -> 1        queue is [2]
//   empty()  -> false
//   Output: [null, null, null, 1, 1, false]
//
// CONSTRAINTS
//   1 <= x <= 9,   at most 100 calls will be made to push, pop, peek, and empty
//   All the calls to pop and peek are valid.
//
// INTUITION
//   Pouring one stack into another reverses it: the element at the bottom (the oldest)
//   ends up on top. So split the queue into two halves:
//     stk1 = inbox   every push lands here, newest on top
//     stk2 = outbox  older elements already in queue order, oldest on top
//   pop / peek serve from stk2, and only when stk2 is empty is all of stk1 poured into
//   it. Everything still in stk2 is older than anything in stk1, so it must leave first.
//
// APPROACH (as implemented below)
//   1. push(x): stk1.push(x).
//   2. pop(): if stk2 is empty, move every element of stk1 onto stk2 (this reverses them
//      into queue order); then stk2.pop().
//   3. peek(): the same refill, then stk2.peek().
//   4. empty(): true only when BOTH stacks are empty.
//
// DRY RUN   the calls main() makes   (stacks written bottom -> top)
//   push(1)   stk1 [1]        stk2 []
//   push(2)   stk1 [1, 2]     stk2 []
//   peek()    stk2 empty -> pour:   stk1 []   stk2 [2, 1]                     -> 1
//   pop()     stk2 not empty -> pop its top:   stk2 [2]                        -> 1
//   empty()   stk2 still holds 2                                               -> false
//
// STATUS
//   main() prints nothing: it runs the LC example but only stores the results (1, 1,
//   false) in unused locals.
//
// COMPLEXITY
//   Time : push O(1);  pop / peek O(1) amortized - every element is pushed onto stk1
//          once, moved to stk2 once and popped once, so n operations cost O(n) in total
//          even though one refill may move many elements (this answers the follow-up)
//   Space: O(n) - the elements are split across the two stacks
//
// GOTCHAS
//   - Refill stk2 ONLY when it is empty. Pouring stk1 onto a non-empty stk2 would put
//     newer elements above older ones and break the FIFO order.
//   - empty() must look at both stacks - elements can be waiting in either one.
//
// RELATED
//   - LC 225 Implement Stack using Queues - the mirror problem; solved in this repo as
//     LC225ImplementStackUsingQueues.java.
// ============================================================================
public class LC232ImplementQueueUsingStacks {
    public static void main(String[] args) {
        // LC example: peek() -> 1, pop() -> 1, empty() -> false.
        // The results are only stored in locals, so nothing is printed.
        MyQueue obj = new MyQueue();
        obj.push(1);
        obj.push(2);
        int param_2 = obj.peek();
        int param_3 = obj.pop();
        boolean param_4 = obj.empty();
    }
    // FIFO queue on two stacks: stk1 = inbox (newest on top), stk2 = outbox (oldest on top).
    static class MyQueue {
        Stack<Integer> stk1;
        Stack<Integer> stk2;
        public MyQueue() {
            stk1 = new Stack<>();
            stk2 = new Stack<>();
        }

        // O(1): new elements always go to the inbox.
        public void push(int x) {
            stk1.push(x);
        }

        // Amortized O(1): refill the outbox only when it is empty, then take its top.
        public int pop() {
            if(stk2.empty()) {
                // pouring reverses the inbox, so the oldest element lands on top
                while (!stk1.empty()) {
                    stk2.push(stk1.pop());
                }
            }
            return stk2.pop();
        }

        // Same as pop(), but the front element stays in the queue.
        public int peek() {
            if(stk2.empty()) {
                while (!stk1.empty()) {
                    stk2.push(stk1.pop());
                }
            }
            return stk2.peek();
        }

        // Elements can wait in either stack.
        public boolean empty() {
            return stk2.empty() && stk1.empty();
        }
    }


}
