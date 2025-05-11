package SDE.LinkedList;

import java.util.Stack;

public class ReverseALinkedList {
    public static void main(String[] args) {
        ListNode node5 = new ListNode(5);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode head = new ListNode(1, node2);
//        bruteForce(head);
//        optimalSolution(head);
        ListNode newHead = recursiveSolution(head);
        int i = 0;
    }

    public static ListNode recursiveSolution(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        ListNode newHead = recursiveSolution(head.next);
        ListNode front = head.next;
        front.next = head;
        head.next = null;
        return newHead;
    }
    public static class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }

      }

        public static ListNode bruteForce(ListNode head) {
            Stack<Integer> stack = new Stack<>();
            ListNode node = head;
            ListNode starting = head;
            while (node != null){
                stack.push(node.val);
                node = node.next;
            }
            while (!stack.empty() && starting != null){
                starting.val = stack.pop();
                starting = starting.next;
            }
            return head;
        }


    public static ListNode optimalSolution(ListNode head) {
        ListNode temp = head;
        ListNode prev = null;
        ListNode front = null;
        while (temp != null){
            front = temp.next;
            temp.next = prev;
            prev = temp;
            temp = front;
        }
        return prev;
    }

}
