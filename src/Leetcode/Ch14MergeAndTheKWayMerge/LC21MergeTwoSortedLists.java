package Leetcode.Ch14MergeAndTheKWayMerge;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 21. Merge Two Sorted Lists   [Easy]
 * https://leetcode.com/problems/merge-two-sorted-lists/
 * Topics: Linked List, Recursion
 * Book: Algorithmic Thinking - ch. 14 Merge and the k-way merge, Practice #1
 *       also: ch. 31 Linked lists and the dummy head (#5),
 *             ch. 56 Recursion and the shape of a subproblem (#5)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   list1 and list2 are the heads of two singly linked lists, each sorted in non-decreasing
 *   order. Merge them into one sorted list built by splicing together the nodes of the two
 *   inputs, and return the head of the merged list (null when both inputs are empty).
 *   Below, m and n are the lengths of list1 and list2.
 *
 * EXAMPLES
 *   list1 = [1,2,4],  list2 = [1,3,4]  ->  [1,1,2,3,4,4]
 *   list1 = [],       list2 = []       ->  []
 *   list1 = [],       list2 = [0]      ->  [0]
 *
 * CONSTRAINTS
 *   each list has 0 to 50 nodes,   -100 <= Node.val <= 100,
 *   both lists are sorted in non-decreasing order
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Practice/LinkedList/LC21MergeTwoSortedLists.java
 *   (an earlier attempt - look at it only after you have tried this one yourself)
 * </pre>
 */
public class LC21MergeTwoSortedLists {

    // LeetCode's node class
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    /**
     * Variant A - copy all values into an array, sort it, build a fresh list
     * <br>Target: time O((m + n) log(m + n)), space O(m + n)
     * <br>Complete first: ch. 6 Sorting as a preprocessing step
     *     + ch. 31 Linked lists and the dummy head (later chapter)
     */
    public static ListNode mergeTwoListsSorting(ListNode list1, ListNode list2) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - recursion: the smaller head, followed by the merge of everything after it
     * <br>Target: time O(m + n), space O(m + n) for the call stack
     * <br>Complete first: ch. 31 Linked lists and the dummy head (later chapter)
     *     + ch. 56 Recursion and the shape of a subproblem (later chapter)
     */
    public static ListNode mergeTwoListsRecursive(ListNode list1, ListNode list2) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C - this chapter's technique: iterative merge from a dummy head, relinking the nodes
     * <br>Target: time O(m + n), space O(1)
     * <br>Complete first: ch. 14 Merge and the k-way merge (this chapter)
     *     + ch. 31 Linked lists and the dummy head (later chapter)
     */
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC21MergeTwoSortedLists::mergeTwoListsSorting);
        runAll("B", LC21MergeTwoSortedLists::mergeTwoListsRecursive);
        runAll("C", LC21MergeTwoSortedLists::mergeTwoLists);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 21. Merge Two Sorted Lists)
    // https://leetcode.com/problems/merge-two-sorted-lists/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases. Lists are built from int arrays and the returned list is
    // read back into an int array before comparing (a cycle or a runaway list is a FAIL).
    //   Expected result: 0/18 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        ListNode solve(ListNode list1, ListNode list2);
    }

    private static void runAll(String v, Solver s) {
        // official examples
        check(v + " example 1", new int[]{1, 1, 2, 3, 4, 4},
                () -> values(s.solve(list(1, 2, 4), list(1, 3, 4))));
        check(v + " example 2", new int[]{}, () -> values(s.solve(list(), list())));
        check(v + " example 3", new int[]{0}, () -> values(s.solve(list(), list(0))));
        // edge: one list runs out first, the rest of the other is appended
        check(v + " leftover tail", new int[]{1, 2, 3, 5},
                () -> values(s.solve(list(5), list(1, 2, 3))));
        // edge: the second list is empty
        check(v + " second list empty", new int[]{7, 8}, () -> values(s.solve(list(7, 8), list())));
        // edge: extreme values and ties between the lists
        check(v + " extremes and ties", new int[]{-100, -100, 0, 100, 100},
                () -> values(s.solve(list(-100, 100), list(-100, 0, 100))));
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
