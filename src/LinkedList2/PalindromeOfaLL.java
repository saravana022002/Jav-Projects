package LinkedList2;

public class PalindromeOfaLL {
    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(4);
        head.next.next.next = new ListNode(3);
        head.next.next.next.next = new ListNode(1);
        System.out.println(isPalindrome(head));
    }
    public static boolean isPalindrome(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode counter = dummy.next;
        ListNode trav = dummy.next;
        int cnt = 0;
        while(counter != null){
            cnt ++;
            counter= counter.next;
        }
        int half = 0;
        ListNode traversal = dummy.next;
        half = cnt / 2;
        int i = 0;
        while(i < half){
            traversal = traversal.next;
            i++;
        }
        ListNode reverseHead = null;
        if (cnt % 2 == 0){
            reverseHead = traversal;
        }else {
            reverseHead = traversal.next;
        }
        ListNode prev = null;
        ListNode curr = reverseHead;
        while (curr != null){
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        while (prev != null && (trav != null && half > 0)){
            if(prev.val != trav.val){
                return false;
            }
            prev = prev.next;
            trav = trav.next;
            half--;
        }
        return true;
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    }
}
