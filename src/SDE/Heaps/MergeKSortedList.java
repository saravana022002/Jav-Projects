package SDE.Heaps;


import java.util.*;

public class MergeKSortedList {


     public static class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      }

    public static void main(String[] args) {
        // First list: [1, 4, 5]
        ListNode list1 = new ListNode(1, new ListNode(4, new ListNode(5)));

        // Second list: [1, 3, 4]
        ListNode list2 = new ListNode(1, new ListNode(3, new ListNode(4)));

        // Third list: [2, 6]
        ListNode list3 = new ListNode(2, new ListNode(6));

        ListNode[] lists = {list1, list2, list3};
        mergeKListsRec(lists, 0, lists.length - 1);
    }



    public static ListNode mergeKListsWithPriorityQ(ListNode[] lists){
        PriorityQueue<ListNode> nodePriorityQueue = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));

        if(lists.length == 0){
            return null;
        }
        for (ListNode node : lists) {
            if (node != null) {
                nodePriorityQueue.add(node);
            }
        }

        ListNode dummy = new ListNode(-1);
        ListNode current = dummy;
        while(!nodePriorityQueue.isEmpty()){
            ListNode smallest = nodePriorityQueue.poll();
            current.next = smallest;
            current = current.next;
            if (smallest.next != null){
                nodePriorityQueue.add(smallest.next);
            }
        }
        return dummy.next;
    }



    public static ListNode mergeKLists1(ListNode[] lists) {
        List<Integer> list = new ArrayList<>();

        for (ListNode listNode : lists){
            while(listNode != null){
                list.add(listNode.val);
                listNode = listNode.next;
            }
        }
        Collections.sort(list);
        ListNode dummy = new ListNode();
        ListNode current = dummy;
        for(int val : list){
            current.next = new ListNode(val);
            current = current.next;
        }

        return dummy.next;
    }

    public static ListNode mergeKLists(ListNode[] lists) {
         if(lists.length == 0){
             return null;
         }
        ListNode head = lists[0];
        for (int i = 1; i < lists.length; i++){
            head = mergeTwoLists(head, lists[i]);
        }
        return head;
    }
    public static ListNode mergeKListsRec(ListNode[] lists, int start, int end) {
        if(start == end){
            return lists[start];
        }
        int mid = start + (end - start) / 2;
        ListNode leftNode = mergeKListsRec(lists, start, mid);
        ListNode rightNode = mergeKListsRec(lists, mid + 1, end);
        return mergeTwoLists(leftNode, rightNode);
    }
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode finalNode = new ListNode(-1);
        ListNode ansFinal = finalNode;
        while (list1 != null && list2 != null){
            if(list1.val < list2.val){
                finalNode.next = list1;
                list1 = list1.next;
            }else {
                finalNode.next = list2;
                list2 = list2.next;
            }
            finalNode = finalNode.next;
        }

        if(list1 != null){
            finalNode.next = list1;
        }else {
            finalNode.next = list2;
        }

        return ansFinal.next;
    }

}
