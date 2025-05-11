package SDE.LinkedList2;

public class LinkedListCycle2 {
    public static void main(String[] args) {

        // Step 1: Create nodes
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);

        hasCycle(head);
    }

    public static ListNode hasCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        ListNode tortosie = head.next;
        ListNode hare = head.next.next;
        while(hare != null && hare.next != null){
            tortosie = tortosie.next;
            hare = hare.next.next;
            if(hare == tortosie){
                hare = head;
                while (tortosie != hare){
                    tortosie = tortosie.next;
                    hare = hare.next;
                }
                return hare;
            }
        }
        return null;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    }
}
