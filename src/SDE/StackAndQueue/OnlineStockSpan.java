package SDE.StackAndQueue;

import java.util.Queue;
import java.util.Stack;

public class OnlineStockSpan {
public static void main(String[] args) {
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
    Stack<int[]> stk;
    int cnt = 0;
    public StockSpanner() {
        this.stk = new Stack<>();
    }

    public int next(int price) {
        while (!stk.empty() && stk.peek()[0] <= price){
            stk.pop();
        }
        int res = cnt - (stk.isEmpty() ? -1 : stk.peek()[1]);
        stk.push(new int[]{price, cnt});
        cnt ++;
        return res;
    }
}
}
