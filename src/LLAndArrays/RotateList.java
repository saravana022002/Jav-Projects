package LLAndArrays;

public class RotateList {
    public static void main(String[] args) {
        ListNode node2 = new ListNode(2);
        ListNode node1 = new ListNode(1, node2);
        ListNode head = new ListNode(0, node1);
        int k = 4;
        System.out.println(rotateRight(head, k));
    }
    public static ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null || k == 0){
            return head;
        }

        ListNode fast = head;
        int cnt = 1;
        while (fast.next != null){
            fast = fast.next;
            cnt ++;
        }
        // last node should point to head
        fast.next = head;

        k = k % cnt;
        if (k == 0) return head;

        int stepsToBeMoved = cnt - k;

        ListNode pointer = head;
        for (int i = 1; i < stepsToBeMoved; i++) {
            pointer = pointer.next;
        }

        ListNode newHead = pointer.next;
        pointer.next = null;
        return newHead;
    }
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    }
}
