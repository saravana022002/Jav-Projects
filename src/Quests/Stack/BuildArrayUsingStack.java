package Quests.Stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BuildArrayUsingStack {
    public static void main(String[] args) {
        int[] target = {1,2};
        int n = 4;
        buildArray(target, n);
    }
    public static List<String> buildArray(int[] target, int n) {
        List<String> list = new ArrayList<>();
        int j = 0;
        for (int i = 1; i <= n; i++) {
            if(target[j] == i){
                list.add("Push");
                j++;
                if(j >= target.length){
                    break;
                }
            }else{
                list.add("Push");
                list.add("Pop");
            }
        }
        return list;
    }
}
