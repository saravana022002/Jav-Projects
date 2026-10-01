package Practice.LinkedList;


// ============================================================================
// LC 234. Palindrome Linked List   [Easy]
// https://leetcode.com/problems/palindrome-linked-list/
// Topics: Linked List, Two Pointers, Stack, Recursion
// ============================================================================
//
// PROBLEM
//   Given the head of a singly linked list, return true if its values read the same from
//   the front to the back as from the back to the front (a palindrome), otherwise false.
//
// EXAMPLES
//   head = [1,2,2,1]    ->  true
//   head = [1,2]        ->  false
//   head = [1,2,4,2,1]  ->  true    odd length, the middle 4 stands alone (main()'s list)
//
// CONSTRAINTS
//   The number of nodes in the list is in the range [1, 10^5]
//   0 <= Node.val <= 9
//   Follow-up: can you do it in O(n) time and O(1) space?   (this file does)
//
// INTUITION
//   A palindrome matches its first half against its second half read backwards, but a
//   singly linked list cannot be walked backwards. So turn the second half around instead:
//   find the middle, reverse the second half in place, and then walk one pointer from the
//   head and one from the start of the reversed half, comparing node by node. The middle
//   is found with a fast and a slow pointer: when the fast one (2 nodes per step) reaches
//   the end, the slow one (1 node per step) is at the middle. With an odd length the middle
//   node has no partner, so it is skipped.
//
// APPROACHES IN THIS FILE
//   A. isPalindrome(head)  - count the nodes (cnt), walk cnt / 2 steps to the second half
//                            (one node further when cnt is odd), reverse it and compare
//                            cnt / 2 pairs - O(n) time, O(1) space
//   B. isPalindrome1(head) - fast/slow pointers find the second half in one pass, then
//                            reverse it and compare - O(n) time, O(1) space
//                            <- main() runs this one
//   (middleNode is a small helper that returns the middle node; nothing calls it.)
//
// APPROACH (as implemented below, method B)
//   1. trav = head; hare and tortoise also start at head. Loop while hare != null and
//      hare.next != null: tortoise moves 1 node, hare moves 2. The tortoise ends on the
//      middle node (odd length) or on the first node of the second half (even length).
//   2. If hare != null the length is odd: tortoise = tortoise.next, which skips the middle.
//   3. Reverse the list that starts at tortoise, in place (the prev / curr / temp loop,
//      same as LC 206). prev becomes the head of the reversed half: the original last node.
//   4. While prev != null: if prev.val != trav.val return false; else advance both.
//   5. Everything matched: return true.
//
// DRY RUN   head = [1,2,4,2,1]  (main() runs this)
//   find the middle   hare: n0 -> n2 -> n4,   tortoise: n0 -> n1 -> n2     (n0..n4 = nodes)
//                     hare = n4 is not null and hare.next is null: stop. tortoise = n2 (value 4)
//   odd length        hare != null, so tortoise = n3 (value 2)
//   reverse n3 -> n4  becomes  n4 (1) -> n3 (2) -> null,  prev = n4
//   compare           prev 1 vs trav 1 equal;  prev 2 vs trav 2 equal;  prev == null: stop
//   return true, which main() prints
//
// STATUS
//   main() prints true for [1,2,4,2,1], which is correct. isPalindrome (method A) is not
//   called and middleNode is unused. Both methods were checked against every 0/1 list of
//   length 1 to 11.
//
// COMPLEXITY
//   Time : O(n) - the middle search, the reversal and the comparison each touch at most n
//          nodes (method A adds one counting pass)
//   Space: O(1) - only pointers; the reversal is done in place
//
// GOTCHAS
//   - Both methods leave the list changed: the second half stays reversed. Read from the
//     head afterwards, [1,2,3,2,1] shows up as 1 2 3 2 and [1,2,2,1] as 1 2 2 (the last
//     node is cut off). Reverse the half again if the caller needs the list intact.
//   - The loop condition hare != null && hare.next != null must test hare first: hare can
//     be null when the length is even.
//   - With an odd length the middle node is skipped; with an even length it is not.
//   - A single node is a palindrome: tortoise ends up null, the reversed half is empty, and
//     the method returns true.
//
// RELATED
//   - LC 206 Reverse Linked List - the reversal used here (LC206ReverseLinkedListIterative.java
//     in this folder).
//   - LC 876 Middle of the Linked List - the fast/slow pointer search in middleNode
//     (LC876MiddleOfTheLinkedList.java in this folder).
//   - LC 2130 Maximum Twin Sum of a Linked List - reverses the second half and pairs it
//     up with the first half in the same way.
// ============================================================================
public class LC234PalindromeLinkedList {
    public static void main(String[] args) {
        // the list 1 -> 2 -> 4 -> 2 -> 1 (a palindrome of odd length). Expected output: true
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(4);
        head.next.next.next = new ListNode(2);
        head.next.next.next.next = new ListNode(1);
        System.out.println(isPalindrome1(head));
    }
    // Approach A: count the nodes, locate the second half by index, reverse it, compare.
    public static boolean isPalindrome(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode counter = dummy.next;
        ListNode trav = dummy.next;
        int cnt = 0;
        // pass 1: count the nodes
        while(counter != null){
            cnt ++;
            counter= counter.next;
        }
        int half = 0;
        ListNode traversal = dummy.next;
        // half = how many pairs have to be compared
        half = cnt / 2;
        int i = 0;
        // walk to index half: the first node of the second half (the middle when cnt is odd)
        while(i < half){
            traversal = traversal.next;
            i++;
        }
        ListNode reverseHead = null;
        if (cnt % 2 == 0){
            reverseHead = traversal;
        }else {
            // odd length: skip the middle node
            reverseHead = traversal.next;
        }
        // reverse the second half in place; prev ends as its head (the original last node)
        ListNode prev = null;
        ListNode curr = reverseHead;
        while (curr != null){
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        // compare `half` pairs: reversed second half against the first half
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

    // Approach B (main() runs this): fast/slow pointers find the middle in one pass, then
    // reverse the second half and compare. O(n) time, O(1) space.
    public static boolean isPalindrome1(ListNode head) {
        ListNode trav = head;
        ListNode hare = head;
        ListNode tortoise = head;
        // when hare reaches the end, tortoise is at the middle
        while(hare != null && hare.next != null){
            tortoise = tortoise.next;
            hare = hare.next.next;
        }
        // hare != null means an odd length: step over the middle node
        if(hare != null){
            tortoise = tortoise.next;
        }
        // reverse the second half in place; prev ends as its head (the original last node)
        ListNode prev = null;
        ListNode curr = tortoise;
        while (curr != null){
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        // walk the reversed half and the front half together
        while (prev != null){
            if(prev.val != trav.val){
                return false;
            }
            prev = prev.next;
            trav = trav.next;
        }
        return true;
    }


    // Helper (unused): the middle node; for an even length, the second of the two middles.
    public static ListNode middleNode(ListNode head) {
        ListNode hare = head;
        ListNode tortoise = head;
        while(hare != null && hare.next != null){
            tortoise = tortoise.next;
            hare = hare.next.next;
        }
        return tortoise;
    }
    // Singly linked list node.
    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

    }
}
