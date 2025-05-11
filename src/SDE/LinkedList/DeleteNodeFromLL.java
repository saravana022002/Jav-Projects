package SDE.LinkedList;

public class DeleteNodeFromLL {
    public static void main(String[] args) {

        ListNode l1 = new ListNode(4);
        l1.next = new ListNode(3);
        l1.next.next = new ListNode(5);
        l1.next.next.next = new ListNode(1);

        ListNode deleteNode = l1.next.next;
        deleteNode(deleteNode);
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    }
    public static ListNode deleteNode(ListNode node) {
        if(node.next != null) {
            node.val = node.next.val;
            node.next = node.next.next;
        }else {
            node = null;
        }
        return node;
    }
}
