package Arrays2;

import java.util.Arrays;

public class MergeTwoSortedArrays {
    public static void main(String[] args) {
        long[] a = {1, 8, 8};
        long[] b = {2, 3, 4, 5};

        // Manually creating List A: 1 -> 8 -> 8 -> null
        ListNode listA = new ListNode(1);
        listA.next = new ListNode(8);
        listA.next.next = new ListNode(8);

        // Manually creating List B: 2 -> 3 -> 4 -> 5 -> null
        ListNode listB = new ListNode(2);
        listB.next = new ListNode(3);
        listB.next.next = new ListNode(4);
        listB.next.next.next = new ListNode(5);

        System.out.println(Arrays.toString(mergeTwoSortedArraysWithoutExtraSpace(a, b)));
        mergeTwoLists(listA, listB);
    }
    public static long[] mergeTwoSortedArraysWithoutExtraSpace(long []a, long []b){
        int size = a.length + b.length;

        //new array for storing result
        long[] result = new long[size];
        int pointA = 0;
        int pointB = 0;

        for (int i = 0; i < size; i++) {

            if(pointA == a.length){
                result[i] = b[pointB];
                continue;
            }
            if (pointB == b.length){
                result[i] = a[pointA];
                continue;
            }
            if(a[pointA] <= b[pointB]){
                result[i] = a[pointA];
                pointA++;
            }else {
                result[i] = b[pointB];
                pointB++;
            }
        }
        return result;
    }

    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode ansNode = new ListNode(0);
        ListNode tail = ansNode;
        while(list1 != null && list2 != null){
            if(list1.val <= list2.val){
                tail.next = list1;
                list1 = list1.next;
            }else {
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }
        if (list1 != null) {
            tail.next = list1;
        } else {
            tail.next = list2;
        }

        return ansNode.next;
    }


    public static ListNode mergeTwoLists1(ListNode list1, ListNode list2) {

        ListNode ansNode = new ListNode(0);
        ListNode tail = ansNode;
        while(list1!=null && list2 != null){
            if(list1.val <= list2.val){
                tail = list1;
                list1 = list1.next;
            }else {
                tail = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }

        if(list1!=null){
            tail.next = list1;
        }else {
            tail.next = list2;
        }
        return ansNode.next;

    }



    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

}
