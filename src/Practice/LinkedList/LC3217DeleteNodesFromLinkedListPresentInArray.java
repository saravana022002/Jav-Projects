package Practice.LinkedList;

import java.util.HashSet;
import java.util.Set;

// ============================================================================
// LC 3217. Delete Nodes From Linked List Present in Array   [Medium]
// https://leetcode.com/problems/delete-nodes-from-linked-list-present-in-array/
// Topics: Array, Hash Table, Linked List
// ============================================================================
//
// PROBLEM
//   You get an int array nums and the head of a linked list. Delete every node whose value
//   occurs in nums and return the head of the list that remains.
//
// EXAMPLES
//   nums = [1,2,3], head = [1,2,3,4,5]      ->  [4,5]          the nodes 1, 2, 3 go
//   nums = [1],     head = [1,2,1,2,1,2]    ->  [2,2,2]        every 1 goes (main() runs this)
//   nums = [5],     head = [1,2,3,4]        ->  [1,2,3,4]      no node holds 5
//
// CONSTRAINTS
//   1 <= nums.length <= 10^5,   1 <= nums[i] <= 10^5,   all elements of nums are unique
//   The number of nodes is in the range [1, 10^5],   1 <= Node.val <= 10^5
//   At least one node has a value that is not in nums (the result is never empty)
//
// INTUITION
//   Both inputs can hold 10^5 items, so asking "is this node's value in nums?" by scanning
//   the array for every node would cost about 10^10 steps. Put nums into a HashSet first:
//   each question then takes O(1). After that it is one pass over the list, unlinking the
//   nodes whose value is in the set. Unlinking a node needs the node BEFORE it (prev), and a
//   dummy head in front of the list gives even the first node a predecessor.
//
// APPROACH (as implemented below)
//   1. sets = a HashSet holding every number of nums.
//   2. dummy -> head. prev = dummy (the last node that was kept), curr = head.
//   3. While curr != null:
//        - sets.contains(curr.val): prev.next = curr.next removes curr, then
//          curr = prev.next. prev stays where it is.
//        - otherwise keep it: prev = curr, curr = curr.next.
//   4. Return dummy.next.
//
// DRY RUN   nums = [1], head = 1 -> 2 -> 1 -> 2 -> 1 -> 2  (what main() runs)
//   start     dummy -> 1 -> 2 -> 1 -> 2 -> 1 -> 2     prev = dummy, curr = first 1
//   curr = 1  in the set: remove it      dummy -> 2 -> 1 -> 2 -> 1 -> 2     curr = first 2
//   curr = 2  not in the set: keep it    prev = that 2, curr = second 1
//   curr = 1  in the set: remove it      dummy -> 2 -> 2 -> 1 -> 2          curr = second 2
//   curr = 2  not in the set: keep it    prev = second 2, curr = third 1
//   curr = 1  in the set: remove it      dummy -> 2 -> 2 -> 2               curr = third 2
//   curr = 2  not in the set: keep it    curr = null: stop
//   return dummy.next: the list 2 -> 2 -> 2
//
// STATUS
//   main() runs example 2 and discards the result, so nothing is printed (the returned list
//   is 2 -> 2 -> 2).
//
// COMPLEXITY
//   Time : O(n + m) - m = nums.length steps to build the set, then one pass over the n nodes
//   Space: O(m) - the set; the list itself is edited in place
//
// GOTCHAS
//   - After removing a node, do NOT move prev: the node that now follows prev may have to
//     go as well (two removable nodes in a row).
//   - Without the dummy head, removing the first node(s) would need a separate case.
//   - Test membership with the set, never by scanning nums for every node.
//
// RELATED
//   - LC 203 Remove Linked List Elements - the same unlinking loop, but for a single value.
//   - LC 237 Delete Node in a Linked List - deleting a node when you do not have its
//     predecessor (LC237DeleteNodeInALinkedList.java in this folder).
// ============================================================================
public class LC3217DeleteNodesFromLinkedListPresentInArray {
    public static void main(String[] args) {
        // remove every 1 from 1 -> 2 -> 1 -> 2 -> 1 -> 2 (result 2 -> 2 -> 2 is not printed)
        int[] nums = {1};

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(1);
        head.next.next.next.next.next = new ListNode(2);
        modifiedList(nums, head);
    }

    // Removes every node whose value is in nums; returns the new head.
    public static ListNode modifiedList(int[] nums, ListNode head) {
        // O(1) membership tests
        Set<Integer> sets = new HashSet<>();
        for (int num : nums){
            sets.add(num);
        }

        // dummy head so the first node also has a predecessor
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        // prev = last node kept so far, curr = node being examined
        ListNode prev =  dummy;
        ListNode curr =  head;

        while (curr != null){
            if(sets.contains(curr.val)){
                // unlink curr; prev stays put because the next node may be removable too
                prev.next = curr.next;
                curr = prev.next;
            }else {
                // keep curr and move on
                prev = curr;
                curr = curr.next;
            }
        }

        return dummy.next;
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
