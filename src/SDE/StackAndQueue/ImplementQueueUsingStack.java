package SDE.StackAndQueue;

import java.util.Stack;

public class ImplementQueueUsingStack {
    public static void main(String[] args) {
        MyQueue obj = new MyQueue();
        obj.push(1);
        obj.push(2);
        int param_2 = obj.peek();
        int param_3 = obj.pop();
        boolean param_4 = obj.empty();
    }
    static class MyQueue {
        Stack<Integer> stk1;
        Stack<Integer> stk2;
        public MyQueue() {
            stk1 = new Stack<>();
            stk2 = new Stack<>();
        }

        public void push(int x) {
            stk1.push(x);
        }

        public int pop() {
            if(stk2.empty()) {
                while (!stk1.empty()) {
                    stk2.push(stk1.pop());
                }
            }
            return stk2.pop();
        }

        public int peek() {
            if(stk2.empty()) {
                while (!stk1.empty()) {
                    stk2.push(stk1.pop());
                }
            }
            return stk2.peek();
        }

        public boolean empty() {
            return stk2.empty() && stk1.empty();
        }
    }


}
