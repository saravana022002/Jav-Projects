package Practice.Heap;


import java.util.*;

// ============================================================================
// LC 23. Merge k Sorted Lists   [Hard]
// https://leetcode.com/problems/merge-k-sorted-lists/
// Topics: Linked List, Divide and Conquer, Heap (Priority Queue), Merge Sort, Tournament Sort
// ============================================================================
//
// PROBLEM
//   You get an array of k linked lists, each sorted in ascending order. Merge them all into
//   one sorted linked list and return its head. Lists may be empty (null), and the array
//   itself may be empty.
//
// EXAMPLES
//   lists = [[1,4,5],[1,3,4],[2,6]]  ->  [1,1,2,3,4,4,5,6]     (main() builds this one)
//   lists = []                       ->  []
//   lists = [[]]                     ->  []    one list, and it is empty
//
// CONSTRAINTS
//   k == lists.length,   0 <= k <= 10^4,   0 <= lists[i].length <= 500
//   -10^4 <= lists[i][j] <= 10^4,   every list is sorted ascending
//   the total number of nodes does not exceed 10^4
//
// INTUITION
//   Merging TWO sorted lists is easy and linear: keep taking the smaller head (LC 21). For k
//   lists there are two natural ways to reuse that: (1) always ask "which of the k current
//   heads is the smallest?", which a min-heap answers quickly, or (2) merge the lists in
//   pairs. Pairing them up like a tournament, round after round (divide and conquer), means
//   each node is touched only about log2(k) times, instead of up to k times when one growing
//   result absorbs the lists one by one.
//
// APPROACHES IN THIS FILE   (N = total number of nodes, k = number of lists)
//   A. mergeKListsWithPriorityQ() - min-heap of the current list heads: pop the smallest,
//                                   link it, push its successor - O(N log k) time, O(k) space
//   B. mergeKLists1()             - copy every value into a list, sort it, build new nodes
//                                   - O(N log N) time, O(N) space
//   C. mergeKLists()              - merge the lists one after another into a growing result
//                                   with mergeTwoLists - O(N * k) time worst case
//   D. mergeKListsRec()           - divide and conquer: merge the left half and the right half
//                                   recursively, then mergeTwoLists - O(N log k) time
//                                   <- main() runs this one
//
// APPROACH (as implemented below, method D)
//   1. mergeKListsRec(lists, start, end): if start == end there is a single list - return it.
//   2. Otherwise mid = start + (end - start) / 2; leftNode = the merge of lists[start..mid],
//      rightNode = the merge of lists[mid+1..end] (two recursive calls).
//   3. Return mergeTwoLists(leftNode, rightNode): a dummy head (finalNode, kept as ansFinal)
//      and a loop that links the smaller of the two current heads (ties take list2's node)
//      and moves on; when one list runs out the rest of the other is linked in one go.
//
// DRY RUN   lists = [1->4->5, 1->3->4, 2->6]   (start = 0, end = 2, mid = 1)
//   left  = merge of lists[0..1]: mid = 0, so mergeTwoLists(1->4->5, 1->3->4) = 1,1,3,4,4,5
//   right = merge of lists[2..2]: single list, returned as is = 2->6
//   mergeTwoLists(1,1,3,4,4,5 and 2,6) = 1,1,2,3,4,4,5,6
//
// COMPLEXITY
//   A: Time O(N log k) - every node enters and leaves a heap of at most k heads.
//      Space O(k) for the heap.
//   B: Time O(N log N) for the sort.  Space O(N) for the value list and the new nodes.
//   C: Time O(N * k) worst case - the growing result is walked again by every later merge.
//      Space O(1) extra.
//   D: Time O(N log k) - about log2(k) rounds, each round merging N nodes in total.
//      Space O(log k) recursion depth; no new nodes are created.
//
// GOTCHAS
//   - mergeKListsRec has no guard for an empty array: mergeKListsRec(lists, 0, lists.length-1)
//     becomes (0, -1) and lists[0] throws ArrayIndexOutOfBoundsException (see STATUS). The
//     other three methods return null for an empty array.
//   - null entries (empty lists) are fine in every method.
//   - A, C and D relink the ORIGINAL nodes, so the input lists are consumed; only B builds new
//     nodes and leaves the input untouched.
//   - Do not run two methods on the same input lists: A, C and D change the nodes' next links.
//   - ListNode is nested in this file because LeetCode normally supplies it.
//
// STATUS
//   Known issue: mergeKListsRec() - the method main() runs - throws
//   ArrayIndexOutOfBoundsException when lists is empty (k = 0, LeetCode example 2) because
//   it reads lists[0] when called as (lists, 0, -1). A, B and C handle that case and return
//   null, and all four methods merge the other examples correctly. Also, main() does not print
//   the merged list (1->1->2->3->4->4->5->6). The code is left unchanged on purpose.
//
// RELATED
//   - LC 21 Merge Two Sorted Lists - the building block of methods C and D
//     (solved in src/Practice/LinkedList/LC21MergeTwoSortedLists.java).
// ============================================================================
public class LC23MergeKSortedLists {


     // the file's own singly linked list node (LeetCode normally provides it)
     public static class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }

    public static void main(String[] args) {
        // Builds LeetCode example 1 and merges it with approach D. The merged list
        // 1->1->2->3->4->4->5->6 is returned but not printed.
        // First list: [1, 4, 5]
        ListNode list1 = new ListNode(1, new ListNode(4, new ListNode(5)));

        // Second list: [1, 3, 4]
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));

        // Third list: [2, 6]
        ListNode list3 = new ListNode(2, new ListNode(6));

        ListNode[] lists = {list1, list2, list3};
        mergeKListsRec(lists, 0, lists.length - 1);
    }



    // Approach A: min-heap of the current heads - O(N log k) time, O(k) space.
    public static ListNode mergeKListsWithPriorityQ(ListNode[] lists){
        // smallest value on top
        PriorityQueue<ListNode> nodePriorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));

        if(lists.length == 0){
            return null;
        }
        // start with the head of every non-empty list
        for (ListNode node : lists) {
            if (node != null) {
                nodePriorityQueue.add(node);
            }
        }

        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
        while(!nodePriorityQueue.isEmpty()){
            // the smallest of all current heads goes next in the result...
            ListNode smallest = nodePriorityQueue.poll();
            current.next = smallest;
            current = current.next;
            // ...and its successor becomes that list's new head
            if (smallest.next != null){
                nodePriorityQueue.add(smallest.next);
            }
        }
        return dummy.next;
    }



    // Approach B: copy all values out, sort them, build a fresh list - O(N log N) time, O(N) space.
    public static ListNode mergeKLists1(ListNode[] lists) {
        List<Integer> list = new ArrayList<>();

        // collect every value from every list
        for (ListNode listNode : lists){
            while(listNode != null){
                list.add(listNode.val);
                listNode = listNode.next;
            }
        }
        Collections.sort(list);
        // rebuild the answer as new nodes
        ListNode dummy = new ListNode();
        ListNode current = dummy;
        for(int val : list){
            current.next = new ListNode(val);
            current = current.next;
        }

        return dummy.next;
    }

    // Approach C: fold the lists into one result, one mergeTwoLists at a time - O(N * k).
    public static ListNode mergeKLists(ListNode[] lists) {
         if(lists.length == 0){
             return null;
         }
        ListNode head = lists[0];
        for (int i = 1; i < lists.length; i++){
            head = mergeTwoLists(head, lists[i]);
        }
        return head;
    }
    // Approach D (run by main): divide and conquer over lists[start..end] - O(N log k).
    // Note: it reads lists[start] when start == end, so an empty array (end = -1) crashes.
    public static ListNode mergeKListsRec(ListNode[] lists, int start, int end) {
        // one list left: nothing to merge
        if(start == end){
            return lists[start];
        }
        int mid = start + (end - start) / 2;
        // merge each half, then merge the two results
        ListNode leftNode = mergeKListsRec(lists, start, mid);
        ListNode rightNode = mergeKListsRec(lists, mid + 1, end);
        return mergeTwoLists(leftNode, rightNode);
    }
    // Merges two sorted lists by relinking their nodes (LC 21); used by approaches C and D.
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // dummy head; finalNode is the tail of the merged list, ansFinal remembers the start
        ListNode finalNode = new ListNode(-1);
        ListNode ansFinal = finalNode;
        // take the smaller head each time
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

        // one list is used up: hang the remainder of the other one on the tail
        if(list1 != null){
            finalNode.next = list1;
        }else {
            finalNode.next = list2;
        }

        return ansFinal.next;
    }

}
