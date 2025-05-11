package SDE.LinkedList2;


public class ReverseKGroup {
    public static void main(String[] args) {
        ListNode node5 = new ListNode(5);
        ListNode node4 = new ListNode(4, node5);
        ListNode node3 = new ListNode(3, node4);
        ListNode node2 = new ListNode(2, node3);
        ListNode head = new ListNode(1, node2);
        reverseKGroup(head, 2);
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    }
    public static ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode groupPrev = dummy;
        ListNode tail = head;
        int cnt = 0;
        while(tail != null){
            cnt++;
            if(cnt % k == 0) {
                ListNode groupNext = tail.next;
                ListNode prev = groupNext;
                ListNode curr = groupPrev.next;
                while (curr != groupNext){
                    ListNode temp = curr.next;
                    curr.next = prev;
                    prev = curr;
                    curr = temp;
                }
                ListNode temp = groupPrev.next;
                groupPrev.next = tail;
                groupPrev = temp;
                tail = groupPrev.next;
            } else {
                tail = tail.next;
            }
        }
        return dummy.next;
    }


    public static ListNode myReverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode groupPrev = dummy;
        ListNode tail = groupPrev.next;
        int cnt = 0;
        while(tail != null){
            cnt++;
            if(cnt % k == 0){
                ListNode groupNext = tail.next;
                ListNode prev = groupNext;
                ListNode curr = groupPrev.next;
                while (curr != groupNext){
                    ListNode temp = curr.next;
                    curr.next = prev;
                    prev = curr;
                    curr = temp;
                }


                ListNode temp = groupPrev.next;
                groupPrev.next = tail;

                groupPrev = temp;
                tail = groupPrev.next;

            }else {
                tail = tail.next;
            }
        }
        return dummy.next;
    }

    private static ListNode getReverse(ListNode front, ListNode tail) {
        ListNode temp = front;
        ListNode prev = null;
        ListNode fronter = null;
        while (temp != tail){
            fronter = temp.next;
            temp.next = prev;
            prev = temp;
            temp = fronter;
        }
        return null;
    }
}
