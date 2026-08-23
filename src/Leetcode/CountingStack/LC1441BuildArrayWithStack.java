package Leetcode.CountingStack;

import java.util.ArrayList;
import java.util.List;

public class LC1441BuildArrayWithStack {

    public static void main(String[] args) {
        int[] target = {1,3};
        int n = 3;
        buildArray(target, n);
    }
    public static List<String> buildArray(int[] target, int n) {
        List<String> ans = new ArrayList<>();
        int streamNum = 1;
        for (int j : target) {
            while (streamNum < j) {
                ans.add("Push");
                ans.add("Pop");
                streamNum++;
            }
            ans.add("Push");
            streamNum++;
        }
        return ans;
    }

}
