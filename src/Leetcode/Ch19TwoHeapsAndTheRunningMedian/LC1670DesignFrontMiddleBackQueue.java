package Leetcode.Ch19TwoHeapsAndTheRunningMedian;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 1670. Design Front Middle Back Queue   [Medium]
 * https://leetcode.com/problems/design-front-middle-back-queue/
 * Topics: Array, Linked List, Design, Queue, Doubly-Linked List, Data Stream
 * Book: Algorithmic Thinking - ch. 19 Two heaps and the running median, Practice #11
 *
 * STATUS
 *   Not solved yet. Implement the classes below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Design a queue that can add and remove elements at the front, in the middle and at the back:
 *     pushFront(val)   pushMiddle(val)   pushBack(val)
 *     popFront()       popMiddle()       popBack()      - each pop returns the removed value,
 *                                                         or -1 when the queue is empty
 *   When the queue has an even length there are two middle positions and the frontmost one
 *   is used. For example, pushing 6 into the middle of [1,2,3,4,5] gives [1,2,6,3,4,5], and
 *   popping the middle of [1,2,3,4,5,6] returns 3 and leaves [1,2,4,5,6].
 *
 * EXAMPLES
 *   q.pushFront(1)   -> [1]
 *   q.pushBack(2)    -> [1,2]
 *   q.pushMiddle(3)  -> [1,3,2]
 *   q.pushMiddle(4)  -> [1,4,3,2]
 *   q.popFront()     -> 1      queue is now [4,3,2]
 *   q.popMiddle()    -> 3      queue is now [4,2]
 *   q.popMiddle()    -> 4      queue is now [2]
 *   q.popBack()      -> 2      queue is now []
 *   q.popFront()     -> -1     the queue is empty
 *
 * CONSTRAINTS
 *   1 <= val <= 10^9,   at most 1000 calls will be made to pushFront, pushMiddle, pushBack,
 *   popFront, popMiddle and popBack
 * </pre>
 */
public class LC1670DesignFrontMiddleBackQueue {

    /**
     * Variant A
     * <br>Target: time O(n) per operation (O(1) at the back), space O(n)
     */
    public static class FrontMiddleBackQueueA {
        public FrontMiddleBackQueueA() {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public void pushFront(int val) {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public void pushMiddle(int val) {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public void pushBack(int val) {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public int popFront() {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public int popMiddle() {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public int popBack() {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }
    }

    /**
     * Variant B
     * <br>Target: time O(1) per operation, space O(n)
     */
    public static class FrontMiddleBackQueueB {
        public FrontMiddleBackQueueB() {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public void pushFront(int val) {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public void pushMiddle(int val) {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public void pushBack(int val) {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public int popFront() {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public int popMiddle() {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }

        public int popBack() {
            // TODO: write your solution
            throw new UnsupportedOperationException("not solved yet");
        }
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", () -> {
            LC1670DesignFrontMiddleBackQueue.FrontMiddleBackQueueA q =
                    new LC1670DesignFrontMiddleBackQueue.FrontMiddleBackQueueA();
            return new Ops() {
                public void pushFront(int val) { q.pushFront(val); }
                public void pushMiddle(int val) { q.pushMiddle(val); }
                public void pushBack(int val) { q.pushBack(val); }
                public int popFront() { return q.popFront(); }
                public int popMiddle() { return q.popMiddle(); }
                public int popBack() { return q.popBack(); }
            };
        });
        runAll("B", () -> {
            LC1670DesignFrontMiddleBackQueue.FrontMiddleBackQueueB q =
                    new LC1670DesignFrontMiddleBackQueue.FrontMiddleBackQueueB();
            return new Ops() {
                public void pushFront(int val) { q.pushFront(val); }
                public void pushMiddle(int val) { q.pushMiddle(val); }
                public void pushBack(int val) { q.pushBack(val); }
                public int popFront() { return q.popFront(); }
                public int popMiddle() { return q.popMiddle(); }
                public int popBack() { return q.popBack(); }
            };
        });
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 1670. Design Front Middle Back Queue)
    // https://leetcode.com/problems/design-front-middle-back-queue/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B) is checked separately with the same cases: the official example plus
    // edge cases checked against a plain-list simulation. A case is a sequence of operations:
    //   F5 = pushFront(5)   M5 = pushMiddle(5)   B5 = pushBack(5)
    //   f = popFront()      m = popMiddle()      b = popBack()
    // and the list of values returned by the pops is compared.
    //   Expected result: 0/8 passed (every case fails until you implement the classes)
    // ==========================================================================
    private static int passed = 0, total = 0;

    // the six operations every variant offers
    interface Ops {
        void pushFront(int val);

        void pushMiddle(int val);

        void pushBack(int val);

        int popFront();

        int popMiddle();

        int popBack();
    }

    interface Factory {
        Ops create();
    }

    private static void runAll(String v, Factory f) {
        // official example
        check(v + " example 1", Arrays.asList(1, 3, 4, 2, -1),
                () -> replay(f, "F1 B2 M3 M4 f m m b f"));
        // edge: popping from an empty queue, then using it again
        check(v + " empty queue", Arrays.asList(-1, -1, -1, 5, -1),
                () -> replay(f, "f m b F5 b f"));
        // edge: the frontmost middle is used, for pushes and for pops, at even and odd lengths
        check(v + " frontmost middle", Arrays.asList(6, 3, 2, 4, 1, 5),
                () -> replay(f, "B1 B2 B3 B4 B5 M6 m m m m m m"));
        // edge: a long mix of all six operations
        check(v + " long mix",
                Arrays.asList(5, 8, 4, 10, 7, 2, 9, 13, 12, 14, 15, 6, 3, 17, 1, 11, 18, 16,
                        -1, -1, -1),
                () -> replay(f, "M1 M2 M3 M4 M5 m F6 B7 M8 m m F9 B10 M11 b b m f M12 M13 B14 F15 "
                        + "m m b f f m M16 M17 F18 m b m f m b f b"));
    }

    private static List<Integer> replay(Factory f, String ops) {
        Ops q = f.create();
        List<Integer> popped = new ArrayList<>();
        for (String op : ops.split(" ")) {
            switch (op.charAt(0)) {
                case 'F' -> q.pushFront(Integer.parseInt(op.substring(1)));
                case 'M' -> q.pushMiddle(Integer.parseInt(op.substring(1)));
                case 'B' -> q.pushBack(Integer.parseInt(op.substring(1)));
                case 'f' -> popped.add(q.popFront());
                case 'm' -> popped.add(q.popMiddle());
                default -> popped.add(q.popBack());
            }
        }
        return popped;
    }

    // one case: a wrong value or an exception counts as FAIL; the run always continues
    private static void check(String name, Object expected, Supplier<Object> actual) {
        total++;
        Object got;
        try {
            got = actual.get();
        } catch (Throwable t) {
            got = "threw " + t.getClass().getSimpleName();
        }
        boolean ok = Objects.deepEquals(expected, got);
        if (ok) passed++;
        System.out.println((ok ? "PASS  " : "FAIL  ") + name
                + (ok ? "" : "   expected " + expected + ", got " + got));
    }

    // ============================== END TESTS ===============================
}
