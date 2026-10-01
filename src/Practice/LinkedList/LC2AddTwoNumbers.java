package Practice.LinkedList;

// ============================================================================
// LC 2. Add Two Numbers   [Medium]
// https://leetcode.com/problems/add-two-numbers/
// Topics: Linked List, Math, Recursion
// ============================================================================
//
// PROBLEM
//   Two non-empty linked lists each hold a non-negative integer, one digit per node, with
//   the digits in REVERSE order: the head is the ones digit, the next node the tens digit,
//   and so on. Add the two numbers and return the sum as a list in the same format. Apart
//   from the number 0 itself, the inputs have no leading zeros.
//
// EXAMPLES
//   l1 = [2,4,3], l2 = [5,6,4]  ->  [7,0,8]      342 + 465 = 807
//   l1 = [0],     l2 = [0]      ->  [0]
//   l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]  ->  [8,9,9,9,0,0,0,1]
//       9999999 + 9999 = 10009998 (main() runs this one)
//
// CONSTRAINTS
//   The number of nodes in each linked list is in the range [1, 100]
//   0 <= Node.val <= 9
//   The lists contain no leading zeros (except the number 0 itself)
//
// INTUITION
//   Reverse order is a gift: the heads are the ones digits, so the two lists already line
//   up for school-book addition. Add the two digits plus the carry from the previous
//   column; the digit to write is sum % 10 and the carry for the next column is sum / 10
//   (at most 1, because 9 + 9 + 1 = 19). A list that runs out early simply contributes 0,
//   and a carry left over after the last column becomes one extra digit at the end.
//   The numbers can have 100 digits, so they must never be converted to an int or long.
//
// APPROACH (as implemented below)
//   1. carry = 0. ans = new ListNode(0) is a dummy head whose next will be the result;
//      ans then moves along as the tail of the new list, dummy keeps the start.
//   2. While l1 != null || l2 != null:
//        val1 / val2 = the current digit of each list, or 0 if that list has ended;
//        sum = val1 + val2 + carry;  carry = sum / 10;
//        append a new node holding sum % 10 and move ans onto it;
//        move l1 and l2 one node on (only the ones that are not null yet).
//   3. After the loop, if carry > 0 append one more node holding the carry.
//   4. Return dummy.next.
//
// DRY RUN   l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]  (what main() runs)
//   column  val1 val2 carry in   sum   digit  carry out
//   1       9    9    0          18    8      1
//   2       9    9    1          19    9      1
//   3       9    9    1          19    9      1
//   4       9    9    1          19    9      1
//   5       9    0    1          10    0      1        (l2 has ended, so val2 = 0)
//   6       9    0    1          10    0      1
//   7       9    0    1          10    0      1
//   both lists are done and carry = 1 > 0, so one more node: 1
//   result 8 -> 9 -> 9 -> 9 -> 0 -> 0 -> 0 -> 1, which main() prints
//
// STATUS
//   main() runs LC example 3 and prints it with printList. Example 1 is kept commented out
//   at the top of main(). The input lists are not modified: the result is built from new
//   nodes.
//
// COMPLEXITY
//   Time : O(max(m, n)) - one step per column (m, n = the list lengths)
//   Space: O(max(m, n)) for the result list (one more node at most for a final carry);
//          nothing else beyond a few variables
//
// GOTCHAS
//   - Loop while EITHER list has nodes (||, not &&); otherwise the tail of the longer
//     list is dropped.
//   - Do not forget the final carry: 5 + 5 has to give [0,1], not [0].
//   - The dummy head's 0 is not part of the answer; return dummy.next.
//   - Advance l1 and l2 only when they are not null, since the lists can differ in length.
//
// RELATED
//   - LC 445 Add Two Numbers II - the same sum, but the digits are stored most significant
//     first, so the lists have to be reversed or pushed on stacks first.
//   - LC 415 Add Strings - the same carry loop over two digit strings.
//   - LC 67 Add Binary - the same carry loop in base 2.
// ============================================================================
public class LC2AddTwoNumbers {
    public static void main(String[] args) {
//        ListNode l1 = new ListNode(2);
//        l1.next = new ListNode(4);
//        l1.next.next = new ListNode(3);
//
//        ListNode l2 = new ListNode(5);
//        l2.next = new ListNode(6);
//        l2.next.next = new ListNode(4);


        // Example 3: 9999999 + 9999, digits stored in reverse.
        // Expected output: 8 -> 9 -> 9 -> 9 -> 0 -> 0 -> 0 -> 1
        ListNode l1 = new ListNode(9);
        l1.next = new ListNode(9);
        l1.next.next = new ListNode(9);
        l1.next.next.next = new ListNode(9);
        l1.next.next.next.next = new ListNode(9);
        l1.next.next.next.next.next = new ListNode(9);
        l1.next.next.next.next.next.next = new ListNode(9);

        ListNode l2 = new ListNode(9);
        l2.next = new ListNode(9);
        l2.next.next = new ListNode(9);
        l2.next.next.next = new ListNode(9);

        printList(addTwoNumbers(l1, l2));
    }
    // Column-by-column addition with a carry; returns the sum as a new reverse-digit list.
    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int sum;
        int carry = 0;
        // dummy head; ans is the tail of the result built so far
        ListNode ans = new ListNode(0);
        ListNode dummy = ans;
        // a list that has ended counts as 0, so go on while either one still has a digit
        while(l1 != null || l2 != null){
            int val1 = l1 != null ? l1.val : 0;
            int val2 = l2 != null ? l2.val : 0;
            // this column: both digits plus the carry from the column before
            sum =  val1 + val2 + carry;
            carry = sum / 10;
            ans.next = new ListNode(sum % 10);
            ans = ans.next;
            if(l1 != null) {
                l1 = l1.next;
            }
            if(l2 != null){
                l2 = l2.next;
            }
        }

        // a carry left after the last column is one more (most significant) digit
        if(carry > 0){
            ans.next = new ListNode(carry);
        }
        return dummy.next;
    }

    // Prints the digits in list order, like 8 -> 9 -> 1.
    public static void printList(ListNode head) {
        while (head != null) {
            System.out.print(head.val);
            if (head.next != null) System.out.print(" -> ");
            head = head.next;
        }
        System.out.println();
    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val;}
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
