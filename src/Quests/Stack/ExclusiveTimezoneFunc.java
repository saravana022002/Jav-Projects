package Quests.Stack;

import java.util.*;

public class ExclusiveTimezoneFunc {

    public static void main(String[] args) {
        int noOfFunc = 2;
        List<String> arr = Arrays.asList("0:start:0", "1:start:2", "1:end:5", "0:end:6");
        exclusiveTime(noOfFunc, arr);
    }
    public static int[] exclusiveTime(int n, List<String> logs) {
        int prevTime = 0;
        int[] res = new int[n];
        Stack<Integer> stk = new Stack<>();
        for (String log : logs) {
            String[] strings = log.split(":");
            int id = Integer.parseInt(strings[0]);
            String action = strings[1];
            int time = Integer.parseInt(strings[2]);
            if("start".equals(action)){
                if(!stk.isEmpty()){
                    res[stk.peek()] += time - prevTime;
                }
                stk.push(id);
                prevTime = time;
            }else {
                res[stk.pop()] += time - prevTime + 1;
                prevTime = time + 1;
            }
        }
        return res;
    }
}
