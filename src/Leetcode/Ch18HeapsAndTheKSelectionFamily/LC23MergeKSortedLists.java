package Leetcode.Ch18HeapsAndTheKSelectionFamily;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Supplier;

/**
 * <pre>
 * LC 23. Merge k Sorted Lists   [Hard]
 * https://leetcode.com/problems/merge-k-sorted-lists/
 * Topics: Linked List, Divide and Conquer, Heap (Priority Queue), Merge Sort, Tournament Sort
 * Book: Algorithmic Thinking - ch. 18 Heaps and the k-selection family, Practice #8
 *       also: ch. 14 Merge and the k-way merge (#7), ch. 59 Divide and conquer (#6)
 *
 * STATUS
 *   Not solved yet. Implement the methods below (start with Variant A), then run
 *   main() - every case prints PASS or FAIL.
 *
 * PROBLEM
 *   lists holds k singly linked lists, each one already sorted in ascending order (an empty
 *   list is represented by null). Merge all of them into one sorted linked list and return
 *   its head, or null when there are no nodes at all.
 *   Below, N is the total number of nodes over all the lists.
 *
 * EXAMPLES
 *   lists = [[1,4,5],[1,3,4],[2,6]]  ->  [1,1,2,3,4,4,5,6]
 *   lists = []                       ->  []
 *   lists = [[]]                     ->  []
 *
 * CONSTRAINTS
 *   k == lists.length,   0 <= k <= 10^4,   0 <= lists[i].length <= 500,
 *   -10^4 <= lists[i][j] <= 10^4,   every list is sorted in ascending order,
 *   the total number of nodes is at most 10^4
 *
 * EARLIER SOLUTIONS IN THIS REPO
 *   src/Practice/Heap/LC23MergeKSortedLists.java
 *   (an earlier attempt - look at it only after you have tried this one yourself)
 * </pre>
 */
public class LC23MergeKSortedLists {

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
     * <br>Target: time O(N log N), space O(N)
     * <br>Complete first: ch. 6 Sorting as a preprocessing step
     *     + ch. 31 Linked lists and the dummy head (later chapter)
     */
    public static ListNode mergeKListsA(ListNode[] lists) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant B - merge the lists two at a time, round after round
     * <br>Target: time O(N log k), space O(1) extra (O(log k) if you use recursion)
     * <br>Complete first: ch. 14 Merge and the k-way merge
     *     + ch. 31 Linked lists and the dummy head (later chapter)
     */
    public static ListNode mergeKListsB(ListNode[] lists) {
        // TODO: write your solution
     */
    public static ListNode mergeKListsB(ListNode[] lists) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    /**
     * Variant C
     * <br>Target: time O(N log k), space O(k)
     * <br>Complete first: ch. 18 Heaps and the k-selection family (this chapter)
     *     + ch. 31 Linked lists and the dummy head (later chapter)
     */
    public static ListNode mergeKListsC(ListNode[] lists) {
        // TODO: write your solution
        throw new UnsupportedOperationException("not solved yet");
    }

    public static void main(String[] args) {
        // every case prints PASS or FAIL - all fail until you implement the methods
        runAll("A", LC23MergeKSortedLists::mergeKListsA);
        runAll("B", LC23MergeKSortedLists::mergeKListsB);
        runAll("C", LC23MergeKSortedLists::mergeKListsC);
        System.out.println(passed + "/" + total + " passed");
    }

    // ================================ TESTS =================================
    // Tests for this class (LC 23. Merge k Sorted Lists)
    // https://leetcode.com/problems/merge-k-sorted-lists/
    // Run main(): every case prints PASS or FAIL, then a summary line.
    // Each variant (A, B, C) is checked separately with the same cases: the three official
    // examples plus edge cases (expected values from a collect-and-sort reference). Lists are
    // built from int arrays and the returned list is read back into an int array before
    // comparing (a cycle or a runaway list is a FAIL).
    //   Expected result: 0/18 passed (every case fails until you implement the methods)
    // ==========================================================================
    private static int passed = 0, total = 0;

    interface Solver {
        ListNode solve(ListNode[] lists);
    }

    private static void runAll(String v, Solver s) {
        // official examples (every call builds fresh lists: a solution may relink the nodes)
        check(v + " example 1", new int[]{1, 1, 2, 3, 4, 4, 5, 6},
                () -> values(s.solve(lists(new int[]{1, 4, 5}, new int[]{1, 3, 4},
                        new int[]{2, 6}))));
        check(v + " example 2", new int[]{}, () -> values(s.solve(lists())));
        check(v + " example 3", new int[]{}, () -> values(s.solve(lists(new int[]{}))));
        // edge: a single list
        check(v + " single list", new int[]{3, 5, 9},
                () -> values(s.solve(lists(new int[]{3, 5, 9}))));
        // edge: empty lists mixed in with non-empty ones
        check(v + " some lists empty", new int[]{1, 2, 3},
                () -> values(s.solve(lists(new int[]{}, new int[]{2}, new int[]{},
                        new int[]{1, 3}))));
        // edge: extreme values and equal values across several lists
        check(v + " extremes and ties", new int[]{-10000, -10000, 0, 0, 0, 10000, 10000},
                () -> values(s.solve(lists(new int[]{-10000, 0, 10000}, new int[]{-10000},
                        new int[]{0, 0}, new int[]{10000}))));
    }

    // builds one linked list per int array; an empty array gives a null entry
    private static ListNode[] lists(int[]... arrays) {
        ListNode[] out = new ListNode[arrays.length];
        for (int i = 0; i < arrays.length; i++) {
            ListNode dummy = new ListNode();
            ListNode tail = dummy;
            for (int x : arrays[i]) {
                tail.next = new ListNode(x);
                tail = tail.next;
            }
            out[i] = dummy.next;
        }
        return out;
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
