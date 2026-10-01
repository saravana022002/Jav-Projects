package Practice.Queue;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

// ============================================================================
// LC 2073. Time Needed to Buy Tickets   [Easy]
// https://leetcode.com/problems/time-needed-to-buy-tickets/
// Topics: Array, Queue, Simulation
// ============================================================================
//
// PROBLEM
//   n people stand in a line to buy tickets; person i wants tickets[i] tickets. Buying one
//   ticket takes exactly one second and only one ticket can be bought at a time: the person
//   at the front buys a ticket and, if they still want more, instantly walks to the back of
//   the line; if they are done they leave. Return how many seconds pass until the person who
//   STARTED at position k has bought all their tickets.
//
// EXAMPLES
//   tickets = [2,3,2],   k = 2  ->  6   the line shrinks as people finish; person 2 is done
//                                       at second 6
//   tickets = [5,1,1,1], k = 0  ->  8   the four people each buy once (4 seconds), the three
//                                       one-ticket people are gone, then person 0 needs 4
//                                       more seconds for its remaining 4 tickets
//
// CONSTRAINTS
//   n == tickets.length,   1 <= n <= 100,   1 <= tickets[i] <= 100,   0 <= k < n
//
// INTUITION
//   Just replay the line, one second per purchase: take the person at the front, let them buy
//   one ticket, send them to the back if they want more. Person k stays in the line until
//   their last ticket, so the clock can be read off when that moment arrives. The code stops
//   one step early - as soon as person k is at the front with exactly ONE ticket left - and
//   adds the final second for that last purchase.
//
// APPROACH (as implemented below)
//   1. que holds a Pair(key, value) for every person in order: key = the ORIGINAL position
//      (how person k is recognised after rotations), value = tickets still wanted.
//   2. count = seconds spent. While the front of the line is NOT "person k with exactly 1
//      ticket left":
//        temp = que.poll() - this person buys one ticket (one second);
//        if temp.value > 1 -> temp.value--, que.offer(temp) (back of the line), else they leave;
//        count++.
//   3. Return count + 1: the last second, in which person k buys the final ticket.
//
// DRY RUN   tickets = [5,1,1,1], k = 0   (what main() runs)
//   start                                    que [(0,5),(1,1),(2,1),(3,1)]   count 0
//   front (0,5): person k but 5 tickets -> buys one, back of line, 4 left
//                                            que [(1,1),(2,1),(3,1),(0,4)]   count 1
//   front (1,1): buys the last ticket, leaves   que [(2,1),(3,1),(0,4)]      count 2
//   front (2,1): leaves                         que [(3,1),(0,4)]            count 3
//   front (3,1): leaves                         que [(0,4)]                  count 4
//   front (0,4): buys one -> (0,3)              que [(0,3)]                  count 5
//   front (0,3): buys one -> (0,2)              count 6
//   front (0,2): buys one -> (0,1)              count 7
//   front (0,1): person k with exactly 1 left -> the loop stops
//   return 7 + 1 = 8
//
// STATUS
//   The import java.util.Deque is not used.
//
// COMPLEXITY
//   Time : O(sum of tickets) - every loop pass is one simulated second (at most
//          100 * 100 = 10^4 passes here)
//   Space: O(n) - the queue of Pair objects
//
// GOTCHAS
//   - The "+ 1" at the end is the final purchase of person k, which the loop stops just
//     before.
//   - The loop condition is "not (front is person k AND it has exactly 1 ticket)". In the
//     code's longer form the inner "que.peek().key == k" is always true at that point, so it
//     is redundant.
//   - Closed form, O(n) and no simulation (not used here): everyone at or before k buys
//     min(tickets[i], tickets[k]) times before k finishes, everyone after k buys
//     min(tickets[i], tickets[k] - 1) times, because k's last ticket comes before theirs in
//     the final round. The answer is the sum. Example 2: 5 + 1 + 1 + 1 = 8.
//
// RELATED
//   - LC 1700 Number of Students Unable to Eat Lunch - another queue simulation in which the
//     front person goes to the back of the line
//     (LC1700NumberOfStudentsUnableToEatLunch.java in this folder).
// ============================================================================
public class LC2073TimeNeededToBuyTickets {
    public static void main(String[] args) {
        // example 2. Expected output: 8
        int[] tickets = {5,1,1,1};
        int k = 0;
        System.out.println(timeRequiredToBuy(tickets, k));
    }

    // a person in the line: key = original position, value = tickets still wanted
    static class  Pair{
        int key;
        int value;
        Pair(int key, int value){
            this.key = key;
            this.value =  value;
        }
    }
    // Returns the seconds until the person who started at position k has all their tickets.
    public static int timeRequiredToBuy(int[] tickets, int k) {
        Queue<Pair> que = new LinkedList<>();
        for (int i = 0 ; i < tickets.length; i++) {
            que.offer(new Pair(i, tickets[i]));
        }
        // seconds spent so far
        int count = 0;
        Pair temp = null;
        // run until the front of the line is person k with exactly one ticket left
        while (!que.isEmpty() && (que.peek().key != k || (que.peek().key == k && que.peek().value != 1))){
            // the front person buys one ticket (one second)
            temp = que.poll();
            // still wants more -> back of the line; otherwise they leave
            if(temp.value > 1){
                temp.value--;
                que.offer(temp);
            }
            count++;
        }
        // + 1 second for person k's final ticket
        return count + 1;
    }

}
