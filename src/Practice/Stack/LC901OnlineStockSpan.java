package Practice.Stack;

import java.util.Queue;
import java.util.Stack;

// ============================================================================
// LC 901. Online Stock Span   [Medium]
// https://leetcode.com/problems/online-stock-span/
// Topics: Stack, Design, Monotonic Stack, Data Stream
// ============================================================================
//
// PROBLEM
//   Stock prices arrive one per day through next(price). For each new price return its
//   span: how many consecutive days, counting back from today (today included), had a
//   price <= today's price.
//
// EXAMPLES
//   prices 100, 80, 60, 70, 60, 75, 85  ->  spans 1, 1, 1, 2, 1, 4, 6
//     75: covers 60, 70, 60 and itself (4 days) - 80 is higher and stops it
//     85: covers everything back to 80 (6 days) - 100 stops it
//
// CONSTRAINTS
//   1 <= price <= 10^5,   at most 10^4 calls to next
//
// INTUITION
//   A span stops at the most recent day with a HIGHER price, so the answer is
//   "today - (last higher day)". Keep a stack of (price, day) for the days that can still
//   stop a future span; their prices strictly decrease towards the top. A new price
//   pops every day with price <= it: those days lie inside today's span, and any later
//   span that could reach them would have to pass today first - so they are never needed
//   again. Whatever remains on top is the last higher day.
//
// APPROACH (as implemented below)
//   cnt = index of the current day.
//   next(price):
//     1. pop while the top's price <= price
//     2. res = cnt - (top's day, or -1 if the stack is empty -> all days so far)
//     3. push (price, cnt), cnt++, return res
//
// DRY RUN   (the calls main() makes; stack shows (price, day), bottom -> top)
//   100 day 0: empty           -> 0 - (-1) = 1   [(100,0)]
//    80 day 1: 100 is higher   -> 1 - 0 = 1      [(100,0), (80,1)]
//    60 day 2:                 -> 2 - 1 = 1      [..., (60,2)]
//    70 day 3: pop 60          -> 3 - 1 = 2      [(100,0), (80,1), (70,3)]
//    60 day 4:                 -> 4 - 3 = 1      [..., (60,4)]
//    75 day 5: pop 60, 70      -> 5 - 1 = 4      [(100,0), (80,1), (75,5)]
//    85 day 6: pop 75, 80      -> 6 - 0 = 6      [(100,0), (85,6)]
//
// COMPLEXITY
//   Time : O(1) amortized per call - every day is pushed once and popped at most once
//   Space: O(n) - the stack, for n calls
//
// GOTCHAS
//   - Pop on <= : days with an EQUAL price belong to today's span.
//   - Using -1 for "no higher day" makes the span cnt + 1, i.e. every day so far.
//
// RELATED
//   - LC 739 Daily Temperatures - the same monotonic stack, looking forward in time.
//   - LC 496 Next Greater Element I - "next greater" instead of "previous greater".
// ============================================================================
public class LC901OnlineStockSpan {
public static void main(String[] args) {
    // replays LC example 1; the expected output of each call is noted on the right
    StockSpanner obj = new StockSpanner();
    System.out.println(obj.next(100)); // Output: 1
    System.out.println(obj.next(80));  // Output: 1
    System.out.println(obj.next(60));  // Output: 1
    System.out.println(obj.next(70));  // Output: 2
    System.out.println(obj.next(60));  // Output: 1
    System.out.println(obj.next(75));  // Output: 4
    System.out.println(obj.next(85));  // Output: 6
}

static class StockSpanner {
    // {price, day} of days that can still stop a span; prices decrease towards the top
    Stack<int[]> stk;
    // index of the current day
    int cnt = 0;
    public StockSpanner() {
        this.stk = new Stack<>();
    }

    public int next(int price) {
        // days not higher than today are inside today's span and never block again
        while (!stk.empty() && stk.peek()[0] <= price){
            stk.pop();
        }
        // days since the last higher price (-1 = none: the span covers every day so far)
        int res = cnt - (stk.isEmpty() ? -1 : stk.peek()[1]);
        stk.push(new int[]{price, cnt});
        cnt ++;
        return res;
    }
}
}
