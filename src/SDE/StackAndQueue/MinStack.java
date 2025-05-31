package SDE.StackAndQueue;

import java.util.Stack;

public class MinStack {
    public static void main(String[] args) {
        // Input simulation
        String[] operations = {
                "MinStack", "push", "push", "push", "getMin", "pop", "top", "getMin"
        };
        int[][] values = {
                {}, {-2}, {0}, {-3}, {}, {}, {}, {}
        };

        MinStack minStack = null;
        for (int i = 0; i < operations.length; i++) {
            switch (operations[i]) {
                case "MinStack":
                    minStack = new MinStack();
                    System.out.println("null");
                    break;
                case "push":
                    minStack.push(values[i][0]);
                    System.out.println("null");
                    break;
                case "pop":
                    minStack.pop();
                    System.out.println("null");
                    break;
                case "top":
                    int top = minStack.top();
                    System.out.println(top);
                    break;
                case "getMin":
                    int min = minStack.getMin();
                    System.out.println(min);
                    break;
            }
        }
    }



    int min;
    Stack<Long> stack;
    public MinStack() {
        this.min = Integer.MAX_VALUE;
        this.stack = new Stack<>();
    }

    public void push(int val) {
        if(stack.empty()){
            min = val;
            stack.push((long) val);
        }else {
            if(min > val){
                stack.push(2L * val - min);
                min = val;
            }else {
                stack.push((long) val);
            }
        }
    }

    public void pop() {
        if(stack.isEmpty()){
            return;
        }
        if(stack.peek() < min){
            min = (int) (2L * min - stack.peek());
        }
        stack.pop();
    }

    public int top() {
        if(stack.isEmpty()){
            return -1;
        }
        long top = stack.peek();
        if(stack.peek() < min){
            return min;
        }else {
            return (int) top;
        }
    }

    public int getMin() {
        return min;
    }
}
