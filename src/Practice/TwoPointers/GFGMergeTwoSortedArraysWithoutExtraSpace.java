package Practice.TwoPointers;

import java.util.Arrays;

// ============================================================================
// GFG - Merge Without Extra Space   [Medium]
// https://www.geeksforgeeks.org/problems/merge-two-sorted-arrays-1587115620/1
// Topics: Array, Two Pointers, Merging sorted sequences
// ============================================================================
//
// PROBLEM
//   a[] holds n numbers in non-decreasing order and b[] holds m numbers in non-decreasing
//   order. Sort all n + m numbers together WITHOUT using a third array: afterwards a[] must
//   contain the n smallest values (in order) and b[] the m largest values (in order). Nothing
//   is returned; the two arrays themselves are rearranged.
//
// EXAMPLES
//   a = [2,4,7,10], b = [2,3]  ->  a = [2,2,3,4], b = [7,10]    merged: [2,2,3,4,7,10]
//   a = [1,5,9,10,15,20], b = [2,3,8,13]
//                              ->  a = [1,2,3,5,8,9], b = [10,13,15,20]
//   a = [0,1], b = [2,3]       ->  a = [0,1], b = [2,3]         already in place
//
// CONSTRAINTS
//   1 <= n, m <= 10^5,   0 <= a[i], b[i] <= 10^7
//
// INTUITION
//   Merging two sorted sequences is the merge step of merge sort: keep one pointer in each
//   and repeatedly take the smaller front element. Writing the result into a NEW array is
//   easy; the GFG task forbids that, and the usual in-place answers are different techniques
//   (swap a's big elements with b's small ones and re-sort both, or the Shell-sort-like "gap"
//   method). This file only has the plain merge into a new array, and it has a bug - see
//   STATUS.
//
// APPROACH (as implemented below, mergeTwoSortedArraysWithoutExtraSpace)
//   1. size = a.length + b.length; result = new array of that size; pointA = pointB = 0 mark
//      the next unused element of a and of b.
//   2. For each position i of result:
//      - pointA == a.length (a is used up): result[i] = b[pointB]
//      - pointB == b.length (b is used up): result[i] = a[pointA]
//      - otherwise take the smaller of a[pointA] and b[pointB] (ties: a) and advance that
//        pointer.
//   3. Return result. (a and b stay unchanged.)
//
// DRY RUN   a = {1, 8, 8}, b = {2, 3, 4, 5}   (the input main() uses, size = 7)
//   i=0  1 <= 2  take a[0] = 1                     pointA = 1
//   i=1  8 >  2  take b[0] = 2                     pointB = 1
//   i=2  8 >  3  take b[1] = 3                     pointB = 2
//   i=3  8 >  4  take b[2] = 4                     pointB = 3
//   i=4  8 >  5  take b[3] = 5                     pointB = 4 = b.length, b is used up
//   i=5  b used up: result[5] = a[pointA] = a[1] = 8        pointA is not advanced
//   i=6  b used up: result[6] = a[pointA] = a[1] = 8        a[2] would be right - also an 8
//   result = [1, 2, 3, 4, 5, 8, 8]
//   With a = {1}, b = {2, 3} the same tail logic gives [1, 2, 2]: b[1] = 3 is never reached.
//
// COMPLEXITY
//   Time : O(n + m) - every position of result is filled once
//   Space: O(n + m) for the new result array - which is exactly what the problem forbids
//   (mergeTwoLists below: Time O(n + m), Space O(1) - it only re-links nodes.)
//
// GOTCHAS
//   - Both "used up" branches end with `continue`, which skips the pointer update: pointA /
//     pointB stay where they are, so the same element is copied again and again (STATUS).
//   - `a[pointA] <= b[pointB]` takes from a on ties, which keeps the merge stable.
//   - The task wants a[] and b[] modified, with the n smallest values in a[] and the m largest
//     in b[]. This method returns one combined array instead and leaves a[] and b[] alone.
//
// STATUS
//   Known issues. The code is left unchanged on purpose.
//   1. mergeTwoSortedArraysWithoutExtraSpace copies the wrong values once one array is used
//      up: the two "used up" branches never advance pointA / pointB, so every remaining slot
//      gets the same leftover element. It is right only when the leftover elements are all
//      equal, as in main()'s sample (8, 8). Examples: a = [1], b = [2,3] returns [1,2,2] (should
//      be [1,2,3]); GFG example 1 gives [2,2,3,4,4,4] (should be [2,2,3,4,7,10]). About 43%
//      of small random inputs came out wrong.
//   2. It does not solve the GFG task at all: it allocates a new n + m array and returns it,
//      where the task is to rearrange a[] and b[] in place without extra space.
//   3. mergeTwoLists1 (unused) is broken: it writes tail = list1 / tail = list2 instead of
//      tail.next = ..., so the tail ends up null and the method throws a NullPointerException
//      whenever both lists have nodes. mergeTwoLists is the working version.
//   4. main() prints only the array result; the merged list from mergeTwoLists is returned and
//      thrown away, so it is never shown.
//
// ALSO IN THIS FILE
//   LC 21. Merge Two Sorted Lists   [Easy]   https://leetcode.com/problems/merge-two-sorted-lists/
//   Merge two sorted singly linked lists into one sorted list by re-using the existing nodes.
//   mergeTwoLists() keeps a dummy head (ansNode) and a tail pointer: while both lists have
//   nodes it appends the node with the smaller val (ties: list1) and steps that list; then it
//   attaches whatever is left of the other list and returns ansNode.next. The result is
//   correct: 1->8->8 and 2->3->4->5 give 1->2->3->4->5->8->8. ListNode is the usual node
//   class (val, next).
//
// SOURCE
//   Source: GeeksforGeeks (found via Striver SDE Sheet (Arrays Day 2); the file also holds
//   the LC 21 linked-list version).
//   Closest LeetCode problem: LC 88 Merge Sorted Array (it merges into the first array, which
//   has spare room at the end, so it can be filled from the back).
// ============================================================================
public class GFGMergeTwoSortedArraysWithoutExtraSpace {
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

        // Prints [1, 2, 3, 4, 5, 8, 8]. Right only because the leftover 8, 8 are equal (STATUS)
        System.out.println(Arrays.toString(mergeTwoSortedArraysWithoutExtraSpace(a, b)));
        // the merged list 1->2->3->4->5->8->8 is returned but discarded, so nothing is printed
        mergeTwoLists(listA, listB);
    }
    // Merges a and b into a NEW sorted array (the merge step of merge sort). It is not in
    // place, and it copies wrong values once one array is used up - see STATUS.
    public static long[] mergeTwoSortedArraysWithoutExtraSpace(long []a, long []b){
        int size = a.length + b.length;

        //new array for storing result
        long[] result = new long[size];
        // next unused element of a and of b
        int pointA = 0;
        int pointB = 0;

        // fill the result left to right with the smallest element that is still unused
        for (int i = 0; i < size; i++) {

            // a is used up: take from b (pointB is not advanced - the bug, see STATUS)
            if(pointA == a.length){
                result[i] = b[pointB];
                continue;
            }
            // b is used up: take from a (pointA is not advanced - the same bug)
            if (pointB == b.length){
                result[i] = a[pointA];
                continue;
            }
            // both have elements left: take the smaller one (ties from a) and step that pointer
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

    // LC 21: merges two sorted linked lists by re-linking the existing nodes behind a dummy
    // head. O(n + m) time, O(1) extra space
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // dummy head, so the first real node needs no special case; tail = last merged node
        ListNode ansNode = new ListNode(0);
        ListNode tail = ansNode;
        while(list1 != null && list2 != null){
            // append the node with the smaller value (ties: list1) and step that list
            if(list1.val <= list2.val){
                tail.next = list1;
                list1 = list1.next;
            }else {
                tail.next = list2;
                list2 = list2.next;
            }
            tail = tail.next;
        }
        // at most one list still has nodes: attach all of it in one go
        if (list1 != null) {
            tail.next = list1;
        } else {
            tail.next = list2;
        }

        // skip the dummy head
        return ansNode.next;
    }


    // Unused, broken variant of mergeTwoLists: `tail = list1` moves the tail pointer onto an
    // input node instead of linking it (tail.next = list1), so nothing is attached to the
    // dummy head and tail ends up null -> NullPointerException when both lists have nodes.
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



    // singly linked list node, as used by LeetCode
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
