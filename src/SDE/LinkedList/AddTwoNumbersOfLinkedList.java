package SDE.LinkedList;

public class AddTwoNumbersOfLinkedList {
    public static void main(String[] args) {
//        ListNode l1 = new ListNode(2);
//        l1.next = new ListNode(4);
//        l1.next.next = new ListNode(3);
//
//        ListNode l2 = new ListNode(5);
//        l2.next = new ListNode(6);
//        l2.next.next = new ListNode(4);


        ListNode l1 = new ListNode(9);
        l1.next = new ListNode(9);
        l1.next.next = new ListNode(9);
        l1.next.next.next = new ListNode(9);
        l1.next.next.next.next = new ListNode(9);
        l1.next.next.next.next.next = new ListNode(9);
        l1.next.next.next.next.next.next = new ListNode(9);

        ListNode l2 = new ListNode(9);
        l2.next = new ListNode(9);
        l2.next.next = new ListNode(9);
        l2.next.next.next = new ListNode(9);

        printList(addTwoNumbers(l1, l2));
    }
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int sum;
        int carry = 0;
        ListNode ans = new ListNode(0);
        ListNode dummy = ans;
        while(l1 != null || l2 != null){
            int val1 = l1 != null ? l1.val : 0;
            int val2 = l2 != null ? l2.val : 0;
            sum =  val1 + val2 + carry;
            carry = sum / 10;
            ans.next = new ListNode(sum % 10);
            ans = ans.next;
            if(l1 != null) {
                l1 = l1.next;
            }
            if(l2 != null){
                l2 = l2.next;
            }
        }

        if(carry > 0){
            ans.next = new ListNode(carry);
        }
        return dummy.next;
    }

    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) System.out.print(" -> ");
            head = head.next;
        }
        System.out.println();
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val;}
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
