package Practice.Design;

import java.util.LinkedList;
import java.util.Queue;

// ============================================================================
// LC 225. Implement Stack using Queues   [Easy]
// https://leetcode.com/problems/implement-stack-using-queues/
// Topics: Stack, Design, Queue
// ============================================================================
//
// PROBLEM
//   Build a LIFO stack - push(x), pop(), top(), empty() - out of queues, using only the
//   queue operations: add to the back, peek / remove at the front, size and isEmpty.
//   The problem allows two queues; its follow-up asks for a single queue, which is what
//   this file does. pop and top are only called when the stack is not empty.
//
// EXAMPLES
//   push(1), push(2)
//   top()    -> 2       the last value pushed is the top
//   pop()    -> 2
//   empty()  -> false   1 is still inside
//   Output: [null, null, null, 2, 2, false]
//
// CONSTRAINTS
//   1 <= x <= 9,   at most 100 calls will be made to push, pop, top, and empty
//   All the calls to pop and top are valid.
//
// INTUITION
//   A queue gives out its OLDEST element, a stack must give out the NEWEST one, which
//   sits at the back of the queue. Rotation fixes that: take the size-1 elements in front
//   of it and re-add each one at the back. Their order among themselves does not change,
//   but the newest element is now at the front, ready to be polled.
//   Invariant between calls: front -> back = oldest -> newest.
//
// APPROACH (as implemented below)
//   1. push(x): queue.add(x) - a plain enqueue.
//   2. pop(): rotate size-1 times (queue.add(queue.poll())), then poll the front, which
//      is the newest element.
//   3. top(): the same rotation, peek the front as result, then move that element to the
//      back as well, which restores the oldest -> newest order.
//   4. empty(): queue.isEmpty().
//
// DRY RUN   the calls main() makes   (queue written front -> back)
//   push(1)   [1]
//   push(2)   [1, 2]
//   top()     rotate 1: [2, 1]   result = 2   move 2 to the back: [1, 2]    -> 2
//   pop()     rotate 1: [2, 1]   poll 2:  [1]                                -> 2
//   empty()   [1] is not empty                                               -> false
//
// STATUS
//   main() prints nothing: it runs the LC example but only stores the results (2, 2,
//   false) in unused locals.
//
// COMPLEXITY
//   Time : push O(1);  pop O(n) and top O(n) - each rotates the n-1 older elements
//   Space: O(n) - the single queue
//
// GOTCHAS
//   - Rotate size-1 elements, not size: a full rotation brings the oldest element back to
//     the front.
//   - top() must also send the element it read to the back. Leaving it at the front breaks
//     the oldest -> newest invariant and a later pop returns the wrong value.
//   - The mirror design makes push O(n) instead (add x, then rotate the older elements
//     behind it) so that pop and top become O(1). Pick by which operation is more common.
//
// RELATED
//   - LC 232 Implement Queue using Stacks - the mirror problem; solved in this repo as
//     LC232ImplementQueueUsingStacks.java.
// ============================================================================
public class LC225ImplementStackUsingQueues {
    public static void main(String[] args) {
        // LC example: top() -> 2, pop() -> 2, empty() -> false.
        // The results are only stored in locals, so nothing is printed.
        MyStack obj = new MyStack();
        obj.push(1);
        obj.push(2);
        int param_3 = obj.top();
        int param_2 = obj.pop();
        boolean param_4 = obj.empty();
    }
    // LIFO stack on ONE queue. Between calls: front -> back = oldest -> newest.
    static class MyStack {

        Queue<Integer> queue;
        public MyStack() {
            queue = new LinkedList<>();
        }

        // O(1): the newest element simply joins the back.
        public void push(int x) {
            queue.add(x);
        }

        // O(n): rotate the size-1 older elements behind the newest one, then poll it.
        public int pop() {
            int size = queue.size() - 1;
            while(size > 0){
                queue.add(queue.poll());
                size--;
            }
            return queue.poll();
        }


        // O(n): same rotation, read the front, then send it to the back too so the
        // oldest -> newest order is restored.
        public int top() {
            int size = queue.size() - 1;
            while(size > 0){
                queue.add(queue.poll());
                size --;
            }
            int result = queue.peek();
            queue.add(queue.poll());
            return result;
        }

        public boolean empty() {
            return queue.isEmpty();
        }
    }

}
