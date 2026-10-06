package Leetcode.Ch14MergeAndTheKWayMerge;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 148. Sort List   [Medium]
 * https://leetcode.com/problems/sort-list/
 * Topics: Linked List, Two Pointers, Divide and Conquer, Sorting, Merge Sort
 * Book: Algorithmic Thinking - ch. 14 Merge and the k-way merge, Practice #6
 *       also: ch. 59 Divide and conquer (#5)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   Given the head of a singly linked list, return the head of the list sorted in ascending
 *   order (an empty list stays empty).
 *   Follow-up: can you sort it in O(n log n) time using only O(1) extra memory?
 *
 * EXAMPLES
 *   head = [4,2,1,3]      ->  [1,2,3,4]
 *   head = [-1,5,3,4,0]   ->  [-1,0,3,4,5]
 *   head = []             ->  []
 *
 * CONSTRAINTS
 *   the list has 0 to 5 * 10^4 nodes,   -10^5 <= Node.val <= 10^5
 * </pre>
 */
public class LC148SortList {

    // LeetCode's node class
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * Variant A
     * <br>Target: time O(n log n), space O(n)
     * <br>Complete first: ch. 6 Sorting as a preprocessing step
     *     + ch. 31 Linked lists and the dummy head (later chapter)
     */
    public static ListNode sortListA(ListNode head) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - top-down merge sort: cut the list in the middle, sort both halves, merge
     * <br>Target: time O(n log n), space O(log n) for the recursion
     * <br>Complete first: ch. 14 Merge and the k-way merge (this chapter)
     *     + ch. 32 Fast and slow pointers (later chapter; needs ch. 31 first)
     */
    public static ListNode sortListB(ListNode head) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(n log n), space O(1)
     * <br>Complete first: ch. 14 Merge and the k-way merge (this chapter)
     *     + ch. 31 Linked lists and the dummy head (later chapter)
     */
    public static ListNode sortListC(ListNode head) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC148SortList::sortListA);
        runAll("B", LC148SortList::sortListB);
        runAll("C", LC148SortList::sortListC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 148. Sort List)
    // https://leetcode.com/problems/sort-list/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases. Lists are built from int arrays and the returned list is
    // read back into an int array before comparing (a cycle or a runaway list is a FAIL).
    //   Expected result: 0/18 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        ListNode solve(ListNode head);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", new int[]{1, 2, 3, 4}, () -> values(s.solve(list(4, 2, 1, 3))));
        check(v + " example 2", new int[]{-1, 0, 3, 4, 5},
                () -> values(s.solve(list(-1, 5, 3, 4, 0))));
        check(v + " example 3", new int[]{}, () -> values(s.solve(list())));
        // edge: a single node
        check(v + " single node", new int[]{7}, () -> values(s.solve(list(7))));
        // edge: descending order with repeated values
        check(v + " descending with duplicates", new int[]{1, 3, 3, 4, 5, 5},
                () -> values(s.solve(list(5, 5, 4, 3, 3, 1))));
        // edge: the extreme values of the constraints
        check(v + " extremes", new int[]{-100000, -100000, 0, 100000, 100000},
                () -> values(s.solve(list(100000, -100000, 0, 100000, -100000))));
    }

    // builds a linked list from the given values (an empty argument list gives null)
    private static ListNode list(int... vals) {
        ListNode dummy = new ListNode();
        ListNode tail = dummy;
        for (int x : vals) {
            tail.next = new ListNode(x);
            tail = tail.next;
        }
        return dummy.next;
    }

    // reads a linked list into an array; null if it has more than 1000 nodes (cycle or bug)
    private static int[] values(ListNode head) {
        int[] buf = new int[1000];
        int n = 0;
        for (ListNode p = head; p != null; p = p.next) {
            if (n == buf.length) return null;
            buf[n++] = p.val;
        }
        return Arrays.copyOf(buf, n);
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
                + (ok ? "" : "   expected " + show(expected) + ", got " + show(got)));
    }

    private static String show(Object o) {
        if (o instanceof int[]) return Arrays.toString((int[]) o);
        if (o instanceof Object[]) return Arrays.deepToString((Object[]) o);
        return String.valueOf(o);
    }

    // ============================== END TESTS ===============================
}
