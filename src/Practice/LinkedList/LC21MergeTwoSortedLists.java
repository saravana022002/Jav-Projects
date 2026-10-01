package Practice.LinkedList;

// ============================================================================
// LC 21. Merge Two Sorted Lists   [Easy]
// https://leetcode.com/problems/merge-two-sorted-lists/
// Topics: Linked List, Recursion
// ============================================================================
//
// PROBLEM
//   Two singly linked lists are given, each already sorted in non-decreasing order.
//   Combine them into ONE sorted list and return its head. The result has to be made by
//   splicing together the existing nodes of the two lists (re-linking them), not by
//   building new ones. Either list, or both, may be empty.
//
// EXAMPLES
//   list1 = [1,2,4], list2 = [1,3,4]  ->  [1,1,2,3,4,4]
//   list1 = [],      list2 = []       ->  []
//   list1 = [],      list2 = [0]      ->  [0]
//
// CONSTRAINTS
//   The number of nodes in both lists is in the range [0, 50]
//   -100 <= Node.val <= 100
//   Both list1 and list2 are sorted in non-decreasing order
//
// INTUITION
//   This is the merge step of merge sort. Both lists are sorted, so the smallest value that
//   is still unplaced is always at the front of one of the two lists. Compare the two
//   fronts, hook the smaller node onto the end of the result and advance that list. When
//   one list runs out, the rest of the other is already sorted and already linked, so it
//   can be attached in one move. A dummy head in front of the result means the very first
//   node needs no special case.
//
// APPROACH (as implemented below)
//   1. finalNode = new ListNode(-1) is the dummy head; finalNode then always points at the
//      LAST node of the merged part. ansFinal keeps pointing at the dummy.
//   2. While both lists have nodes: if list1.val < list2.val link list1's node after
//      finalNode and advance list1, otherwise do the same with list2 (ties go here).
//      Then move finalNode onto the node just linked.
//   3. One list is empty: attach the other one's remainder with a single assignment
//      (list1 if it still has nodes, else list2 - which may itself be null).
//   4. Return ansFinal.next, skipping the dummy.
//
// DRY RUN   list1 = [1,2,4], list2 = [1,3,4]
//   fronts (list1 vs list2)   node taken      merged so far
//   1 vs 1  (tie)             list2's 1       1
//   1 vs 3                    list1's 1       1 1
//   2 vs 3                    list1's 2       1 1 2
//   4 vs 3                    list2's 3       1 1 2 3
//   4 vs 4  (tie)             list2's 4       1 1 2 3 4     (list2 is now empty)
//   attach list1's rest       the node 4      1 1 2 3 4 4
//
// STATUS
//   main() merges the odd list [1,3,5,7,9] with the even list [2,4,6,8,10] and throws the
//   result away, so nothing is printed (the merged list is 1 to 10 in order).
//
// COMPLEXITY
//   Time : O(m + n) - every node is looked at once (m, n = the two list lengths)
//   Space: O(1) - nodes are only re-linked; the dummy is a single extra node
//
// GOTCHAS
//   - The dummy's value (-1) never matters; always return dummy.next, not the dummy.
//   - With < a tie takes the node from list2 first (see the dry run). Equal values are
//     interchangeable here; use <= if nodes of list1 must stay ahead of equal ones.
//   - The remainder is attached without a loop, because it is already linked and sorted.
//   - The input lists are consumed: their nodes now belong to the merged list.
//   - Two empty lists work too: the loop is skipped and finalNode.next = list2 = null.
//
// RELATED
//   - LC 88 Merge Sorted Array - the same merge of two sorted sequences, on arrays
//     (LC88MergeSortedArray.java in this repo).
//   - LC 23 Merge k Sorted Lists - merge k lists: repeat this merge or use a min-heap.
//   - LC 148 Sort List - merge sort on a linked list, which uses exactly this merge.
// ============================================================================
public class LC21MergeTwoSortedLists {
    public static void main(String[] args) {
        // list 1: 1 -> 3 -> 5 -> 7 -> 9
        ListNode node5 = new ListNode(9);
        ListNode node4 = new ListNode(7, node5);
        ListNode node3 = new ListNode(5, node4);
        ListNode node2 = new ListNode(3, node3);
        ListNode node1 = new ListNode(1, node2);


        // list 2: 2 -> 4 -> 6 -> 8 -> 10
        ListNode kode5 = new ListNode(10);
        ListNode kode4 = new ListNode(8, kode5);
        ListNode kode3 = new ListNode(6, kode4);
        ListNode kode2 = new ListNode(4, kode3);
        ListNode kode1 = new ListNode(2, kode2);


        // the merged list is 1 -> 2 -> ... -> 10; the result is ignored, nothing is printed
        mergeTwoLists(node1, kode1);
    }

    // Splices the two sorted lists into one sorted list and returns its head.
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // finalNode = tail of the merged list so far, starting on a dummy head;
        // ansFinal remembers the dummy so its next can be returned at the end
        ListNode finalNode = new ListNode(-1);
        ListNode ansFinal = finalNode;
        // take the smaller front node each time
        while (list1 != null && list2 != null){
            if(list1.val < list2.val){
                finalNode.next = list1;
                list1 = list1.next;
            }else {
                finalNode.next = list2;
                list2 = list2.next;
            }
            finalNode = finalNode.next;
        }

        // at most one list still has nodes: hook its whole remainder on (it is already sorted)
        if(list1 != null){
            finalNode.next = list1;
        }else {
            finalNode.next = list2;
        }

        // skip the dummy
        return ansFinal.next;
    }
    // Singly linked list node.
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
