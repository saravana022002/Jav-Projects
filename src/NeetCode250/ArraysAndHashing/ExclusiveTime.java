package NeetCode250.ArraysAndHashing;

import java.util.*;

public class ExclusiveTime {
    public static void main(String[] args) {
        int n = 1;
        List<String> logs = Arrays.asList("0:start:0","1:start:5","2:start:6","3:start:9","4:start:11","5:start:12","6:start:14","7:start:15","1:start:24","1:end:29","7:end:34","6:end:37","5:end:39","4:end:40","3:end:45","0:start:49","0:end:54","5:start:55","5:end:59","4:start:63","4:end:66","2:start:69","2:end:70","2:start:74","6:start:78","0:start:79","0:end:80","6:end:85","1:start:89","1:end:93","2:end:96","2:end:100","1:end:102","2:start:105","2:end:109","0:end:114");
        exclusiveTime(n, logs);
    }

    public static int[] exclusiveTime(int n, List<String> logs) {
        Map<String, Integer> map = new HashMap<>();
        Stack<String> stk  = new Stack<>();
        for (int i = 0; i < n; i++) {
            map.put(i+"", 0);
        }
        int prevTime = 0;
        for (String log : logs){
            String[] curr = log.split(":");

            String unique = curr[0];
            String action = curr[1];
            int time = Integer.parseInt(curr[2]);
            if(action.equals("start")){
                if (!stk.isEmpty()) {
                    String[] prev = stk.peek().split(":");
                    map.put(prev[0], map.get(prev[0]) + time - prevTime);
                }
                stk.push(log);
                prevTime = time;
            }else {

            }

            stk.push(log);
        }


        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = map.get(i+"");
        }

        return ans;
    }
}
