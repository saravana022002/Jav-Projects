package LinkedList2;

import LinkedList.DeleteNodeFromLL;

public class IntersectionOfLL {
    public static void main(String[] args) {

        // Shared intersection part: c1 → c2 → c3
        ListNode c3 = new ListNode(8);
        ListNode c2 = new ListNode(7, c3);
        ListNode c1 = new ListNode(6, c2);

        // List A: a1 → a2 → c1 → c2 → c3
        ListNode a2 = new ListNode(2, c1);
        ListNode a1 = new ListNode(1, a2);

        // List B: b1 → b2 → b3 → c1 → c2 → c3
        ListNode b3 = new ListNode(5, c1);
        ListNode b2 = new ListNode(4, b3);
        ListNode b1 = new ListNode(3, b2);
//        getIntersectionNode(a1, b1);
        getIntersectionNodeOptimal(a1, b1);
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    }
    public static ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode t1 = headA;
        ListNode t2 = headB;
        ListNode s = null;
        ListNode sq = null;

        int size1 = 0;
        int size2 = 0;
        int diff = 0;

        while(t1 != null){
            size1 ++;
            t1 = t1.next;
        }

        while(t2 != null){
            size2 ++;
            t2 = t2.next;
        }

        if(size1 >= size2){
            diff = size1 - size2;
            s = headA;
            sq = headB;
        }else {
            diff = size2 - size1;
            s = headB;
            sq = headA;
        }

        while (s != null && diff > 0){
            s = s.next;
            diff --;
        }

        while (s != null && sq != null){
            if(s == sq){
                return s;
            }
            s = s.next;
            sq = sq.next;
        }
        return null;
    }

     public static ListNode getIntersectionNodeOptimal(ListNode headA, ListNode headB) {
         if (headA == null || headB == null) return null;
         ListNode a = headA;
         ListNode b = headB;
         while (a != b) {
             a = (a == null) ? headB : a.next;
             b = (b == null) ? headA : b.next;
         }
         return a;
    }


}
