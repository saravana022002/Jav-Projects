package SDE.StackAndQueue;

import java.util.Stack;

public class SortAStack {
    public static void main(String[] args) {
        Stack<Integer> stk = new Stack<>();
        stk.push(2);
        stk.push(3);
        stk.push(6);
        stk.push(1);
        stk.push(5);
        sortAStack(stk);
    }
    public static Stack<Integer> sortAStack(Stack<Integer> stk){
        Stack<Integer> tempStk = new Stack<>();
        while (!stk.isEmpty()){
            int current = stk.pop();
            while(!tempStk.isEmpty() && tempStk.peek() < current){
                stk.push(tempStk.pop());
            }
            tempStk.push(current);
        }

        while (!tempStk.isEmpty()){
            stk.push(tempStk.pop());
        }
        return stk;
    }
}
