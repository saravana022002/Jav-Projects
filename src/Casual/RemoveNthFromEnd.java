package Casual;

public class RemoveNthFromEnd {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode hare = dummy;
        ListNode tortoise = dummy;
        int count = 0;
        while (count <= n){
            hare = hare.next;
            count++;
        }
        while (hare != null){
            tortoise = tortoise.next;
            hare = hare.next;
        }
        tortoise.next = tortoise.next.next;
        return dummy.next;
    }
}
