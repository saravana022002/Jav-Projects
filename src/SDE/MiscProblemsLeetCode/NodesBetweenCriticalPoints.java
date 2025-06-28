package SDE.MiscProblemsLeetCode;

import java.util.ArrayList;
import java.util.List;

public class NodesBetweenCriticalPoints {
    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(2);
        head.next.next = new ListNode(1);
        head.next.next.next = new ListNode(3);
        nodesBetweenCriticalPoints(head);
    }

    public static int[] nodesBetweenCriticalPoints(ListNode head) {
        List<Integer> indexes = new ArrayList<>();
        ListNode dummy;
        dummy = head;
        int index = 0;
        while (dummy != null){
            if(dummy.next != null && dummy.next.next != null && ((dummy.next.val < dummy.next.next.val && dummy.next.val < dummy.val) || (dummy.next.val > dummy.next.next.val && dummy.next.val > dummy.val))){
                indexes.add(index);
            }
            dummy = dummy.next;
            index++;
        }
        int[] result = new int[2];
        result[0] = -1;
        result[1] = -1;
        if (!indexes.isEmpty() && indexes.size() > 1) {
            int min = Integer.MAX_VALUE;
            int max = indexes.get(indexes.size() - 1) - indexes.get(0);
            for (int i = 1; i < indexes.size(); i++) {
                min = Math.min(min, indexes.get(i) - indexes.get(i - 1));
            }
            result[0] = min;
            result[1] = max;
        }
        return result;
    }


  public static class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
  }

}
