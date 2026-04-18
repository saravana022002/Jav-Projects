package Posh;

public class ReverseALL {
    public static void main(String[] args) {
        ListNode node = new ListNode();
        reverseList(node);
    }

    public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
    public static ListNode reverseList(ListNode node) {
        ListNode prev = null;
        ListNode curr = node;
        ListNode nextNode = null;
        while (curr != null){
            nextNode = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextNode;
        }
        return prev;
    }
}
