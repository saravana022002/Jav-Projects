package LinkedList;

public class RemoveNthNodeFromBack {
    public static void main(String[] args) {
        ListNode node5 = new ListNode(5);
//        ListNode node4 = new ListNode(4, node5);
//        ListNode node3 = new ListNode(3, node4);
//        ListNode node2 = new ListNode(2, node3);
//        ListNode node1 = new ListNode(1, node2);
        removeNthFromEnd(node5, 1);
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val;}
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public static ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0); // Dummy node before head
        dummy.next = head;

        ListNode hare = dummy;
        ListNode tortoise = dummy;
        int cnt = 0;
        while(cnt <= n){
            hare = hare.next;
            cnt++;
        }

        while (hare != null){
            hare = hare.next;
            tortoise = tortoise.next;
        }
        tortoise.next = tortoise.next.next;

        return dummy.next;
    }

    public static ListNode bruteRemoveNthFromEnd(ListNode head, int n) {
      return null;
    }


}
