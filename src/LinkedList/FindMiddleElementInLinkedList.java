package LinkedList;

public class FindMiddleElementInLinkedList {
    public static void main(String[] args) {
        ListNode node5 = new ListNode(5);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode node1 = new ListNode(1, node2);
        middleNode(node1);
    }
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    }
    public static ListNode middleNode(ListNode head) {
        ListNode hare = head;
        ListNode tortoise = head;
        while(hare != null){
            if(hare.next == null){
                return tortoise;
            } else {
                tortoise = tortoise.next;
                hare = hare.next.next;
            }
        }
        return tortoise;
    }
}
