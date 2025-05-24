package SDE.StackAndQueue;

import java.util.LinkedList;
import java.util.Queue;

public class ImplementStackUsingQueue {
    public static void main(String[] args) {
        MyStack obj = new MyStack();
        obj.push(1);
        obj.push(2);
        int param_3 = obj.top();
        int param_2 = obj.pop();
        boolean param_4 = obj.empty();
    }
    static class MyStack {

        Queue<Integer> queue;
        public MyStack() {
            queue = new LinkedList<>();
        }

        public void push(int x) {
            queue.add(x);
        }

        public int pop() {
            int size = queue.size() - 1;
            while(size > 0){
                queue.add(queue.poll());
                size--;
            }
            return queue.poll();
        }


        public int top() {
            int size = queue.size() - 1;
            while(size > 0){
                queue.add(queue.poll());
                size --;
            }
            int result = queue.peek();
            queue.add(queue.poll());
            return result;
        }

        public boolean empty() {
            return queue.isEmpty();
        }
    }

}
