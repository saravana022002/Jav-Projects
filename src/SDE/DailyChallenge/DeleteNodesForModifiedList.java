package SDE.DailyChallenge;

import java.util.HashSet;
import java.util.Set;

public class DeleteNodesForModifiedList {
    public static void main(String[] args) {
        int[] nums = {1};

        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(1);
        head.next.next.next.next.next = new ListNode(2);
        modifiedList(nums, head);
    }

    public static ListNode modifiedList(int[] nums, ListNode head) {
        Set<Integer> sets = new HashSet<>();
        for (int num : nums){
            sets.add(num);
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev =  dummy;
        ListNode curr =  head;

        while (curr != null){
            if(sets.contains(curr.val)){
                prev.next = curr.next;
                curr = prev.next;
            }else {
                prev = curr;
                curr = curr.next;
            }
        }

        return dummy.next;
    }

      public static class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }
}
