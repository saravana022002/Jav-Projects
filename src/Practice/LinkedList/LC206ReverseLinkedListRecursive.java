package Practice.LinkedList;

import java.util.Stack;

// ============================================================================
// LC 206. Reverse Linked List   [Easy]
// https://leetcode.com/problems/reverse-linked-list/
// Topics: Linked List, Recursion
// ============================================================================
//
// PROBLEM
//   Given the head of a singly linked list, reverse it and return the head of the reversed
//   list. The old last node becomes the head and the old head becomes the last node.
//   An empty list stays empty.
//
// EXAMPLES
//   head = [1,2,3,4,5]  ->  [5,4,3,2,1]
//   head = [1,2]        ->  [2,1]
//   head = []           ->  []
//
// CONSTRAINTS
//   The number of nodes in the list is in the range [0, 5000]
//   -5000 <= Node.val <= 5000
//   Follow-up: reverse it iteratively or recursively - can you do both?
//
// INTUITION
//   Trust the recursion: ask it to reverse everything AFTER the head, and only then fix up
//   the head. When that call returns, the rest is already reversed, and head.next still
//   points at the node that used to come second - which is now the TAIL of the reversed
//   rest. So two small steps finish the job: make that tail point back at head, and cut
//   head's own next so that head becomes the new last node.
//
// APPROACHES IN THIS FILE
//   A. recursiveSolution - reverse the tail by recursion, then hook head onto its end
//                          - O(n) time, O(n) space (call stack)   <- main() runs this one
//   B. bruteForce        - push every value on a Stack and pop them back into the nodes
//                          from the front: the VALUES are reversed, the links stay
//                          - O(n) time, O(n) space
//   C. optimalSolution   - iterative three-pointer reversal in place - O(n) time, O(1) space
//                          (the same as LC206ReverseLinkedListIterative.java)
//
// APPROACH (as implemented below, method A)
//   recursiveSolution(head) returns the head of the reversed list.
//   1. head == null or head.next == null -> return head: an empty or one-node list is
//      already reversed. This is the base case that stops the recursion.
//   2. newHead = recursiveSolution(head.next). It reverses the rest and returns the OLD
//      LAST node, which stays the answer all the way back up.
//   3. front = head.next is the old second node, now the tail of the reversed rest.
//      front.next = head appends head behind it.
//   4. head.next = null makes head the last node (and removes the head <-> front loop).
//   5. return newHead.
//
// DRY RUN   head = 1 -> 2 -> 3   (main() does the same on 1 -> 2 -> 3 -> 4 -> 5)
//   call(1) calls call(2), which calls call(3): 3.next == null, so it returns node 3
//   back in call(2)   newHead = 3, front = 3;  3.next = 2;  2.next = null   3 -> 2
//   back in call(1)   newHead = 3, front = 2;  2.next = 1;  1.next = null   3 -> 2 -> 1
//   call(1) returns newHead = 3, the head of 3 -> 2 -> 1
//
// STATUS
//   main() builds 1 -> 2 -> 3 -> 4 -> 5 and calls recursiveSolution. The result goes into
//   newHead, which is never printed (the line int i = 0 after it does nothing), so the
//   program prints nothing. The calls of bruteForce and optimalSolution are commented out.
//
// COMPLEXITY
//   A. Time O(n) - every node is handled once.  Space O(n) - n nested calls on the stack.
//   B. Time O(n) - two passes.                  Space O(n) - the stack of values.
//   C. Time O(n) - one pass.                    Space O(1) - three pointers.
//
// GOTCHAS
//   - head.next = null is essential. After front.next = head the two nodes point at each
//     other; without the cut the end of the result would be a two-node cycle.
//   - The recursion is as deep as the list is long. That is fine for the 5000-node limit;
//     in a test run a 100000-node list overflowed the default stack.
//   - B changes the VALUES stored in the nodes and leaves every link alone, so anything
//     else holding one of those nodes suddenly sees a different value.
//
// SEE ALSO
//   LC206ReverseLinkedListIterative.java - the iterative solution on its own (the same as
//   optimalSolution here)
//
// RELATED
//   - LC 92 Reverse Linked List II - reverse only a part of the list.
//   - LC 234 Palindrome Linked List - reverses the second half and compares it with the
//     first (LC234PalindromeLinkedList.java in this folder).
//   - LC 25 Reverse Nodes in k-Group - reverses the list in blocks of k
//     (LC25ReverseNodesInKGroup.java in this folder).
// ============================================================================
public class LC206ReverseLinkedListRecursive {
    public static void main(String[] args) {
        // builds 1 -> 2 -> 3 -> 4 -> 5
        ListNode node5 = new ListNode(5);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode head = new ListNode(1, node2);
//        bruteForce(head);
//        optimalSolution(head);
        // newHead is the node 5 (list 5 -> 4 -> 3 -> 2 -> 1); nothing is printed
        ListNode newHead = recursiveSolution(head);
        int i = 0;
    }

    // Approach A (the one main() runs): reverse everything after head first, then hook head
    // onto the end of that. O(n) time, O(n) stack.
    public static ListNode recursiveSolution(ListNode head) {
        // empty or one node: already reversed
        if(head == null || head.next == null){
            return head;
        }
        // reverse the rest; newHead is the old last node, the head of the final answer
        ListNode newHead = recursiveSolution(head.next);
        // head.next still points at the old second node, now the tail of the reversed rest
        ListNode front = head.next;
        // append head behind that tail ...
        front.next = head;
        // ... and make it the last node (this also breaks the head <-> front loop)
        head.next = null;
        return newHead;
    }
    // Singly linked list node.
    public static class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }

      }

        // Approach B: push all values on a stack, then pop them back into the nodes from the
        // front. The values come out reversed; no link changes. O(n) time, O(n) space.
        public static ListNode bruteForce(ListNode head) {
            Stack<Integer> stack = new Stack<>();
            ListNode node = head;
            ListNode starting = head;
            // pass 1: stack up every value (the last value ends on top)
            while (node != null){
                stack.push(node.val);
                node = node.next;
            }
            // pass 2: overwrite the values front to back with what comes off the stack
            while (!stack.empty() && starting != null){
                starting.val = stack.pop();
                starting = starting.next;
            }
            return head;
        }


    // Approach C: iterative reversal in place, flipping one next pointer per node
    // (temp = node being flipped, prev = reversed part, front = saved rest of the list).
    // O(n) time, O(1) space.
    public static ListNode optimalSolution(ListNode head) {
        ListNode temp = head;
        ListNode prev = null;
        ListNode front = null;
        while (temp != null){
            // save the rest, flip the arrow, then step forward
            front = temp.next;
            temp.next = prev;
            prev = temp;
            temp = front;
        }
        return prev;
    }

}
