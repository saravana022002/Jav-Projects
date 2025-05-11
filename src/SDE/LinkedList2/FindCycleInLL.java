package SDE.LinkedList2;

public class FindCycleInLL {
    public static void main(String[] args) {

        // Step 1: Create nodes
        ListNode head = new ListNode(-21);
        head.next = new ListNode(10);
        head.next.next = new ListNode(17);
        head.next.next.next = new ListNode(8);
        head.next.next.next.next = new ListNode(4);
        head.next.next.next.next.next = new ListNode(26);
        head.next.next.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next.next.next = new ListNode(35);
        head.next.next.next.next.next.next.next.next = new ListNode(33);
        head.next.next.next.next.next.next.next.next.next = new ListNode(-7);
        head.next.next.next.next.next.next.next.next.next.next = new ListNode(-16);
        head.next.next.next.next.next.next.next.next.next.next.next = new ListNode(27);
        head.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(-12);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(6);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(29);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(-12);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(5);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(9);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(20);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(14);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(14);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(2);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(13);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(-24);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(21);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(23);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(-21);
        head.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next.next = new ListNode(5);

        hasCycle(head);
    }

    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode tortosie = head.next;
        ListNode hare = head.next.next;
        while(tortosie != null && hare != null && hare != tortosie){
            if(hare.next == null) return false;
            tortosie = tortosie.next;
            hare = hare.next.next;
        }
        return hare == tortosie;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    }
}
