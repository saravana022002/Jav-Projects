package SDE.LinkedList;

public class MergeSortedLL {
    public static void main(String[] args) {
        ListNode node5 = new ListNode(9);
        ListNode node4 = new ListNode(7, node5);
        ListNode node3 = new ListNode(5, node4);
        ListNode node2 = new ListNode(3, node3);
        ListNode node1 = new ListNode(1, node2);


        ListNode kode5 = new ListNode(10);
        ListNode kode4 = new ListNode(8, kode5);
        ListNode kode3 = new ListNode(6, kode4);
        ListNode kode2 = new ListNode(4, kode3);
        ListNode kode1 = new ListNode(2, kode2);


        mergeTwoLists(node1, kode1);
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
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
