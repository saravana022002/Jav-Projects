package SDE.StackAndQueue;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BuildAnArrayWithStackOp {
    public static void main(String[] args) {
        int[] target = {1,2};
        int n = 4;
        System.out.println(buildArray(target, n));
    }
    public static List<String> buildArray(int[] target, int n) {
        List<String> ans = new ArrayList<>();
        int j = 0;
        for (int i = 1; i <= n; i++) {
            if (j == target.length) break;
            if(target[j] == i){
                j++;
                ans.add("Push");
            }else {
                ans.add("Push");
                ans.add("Pop");
            }
        }
        return ans;
    }
}
