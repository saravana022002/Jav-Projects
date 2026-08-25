package Leetcode.Daily;

import java.util.Arrays;

public class LC3718missingMultiple {
    public static void main(String[] args) {
        int[] nums = {1,4,7,10,15};
        int k = 5;
        System.out.println(missingMultiple(nums, k));
    }

    public static int missingMultiple(int[] nums, int k) {
        int[] space = new int[101];
        Arrays.fill(space, -1);
        for (int num : nums) {
            if (num % k == 0) {
                space[num / k] = 1;
            }
        }
        int lastIdx = -1;
        for (int i = 1; i < space.length; i++) {
           if(space[i] == 1){
               lastIdx = i;
           }else {
               return (i) * k;
           }
        }
        return (lastIdx + 1) * k;
    }
}
