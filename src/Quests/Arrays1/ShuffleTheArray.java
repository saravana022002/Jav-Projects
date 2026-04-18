package Quests.Arrays1;

import java.util.Arrays;

public class ShuffleTheArray {

    public static void main(String[] args) {
       int[] nums = {2,5,1,3,4,7};
       int n = 3;
        System.out.println(Arrays.toString(shuffle(nums, n)));
    }

    public static int[] shuffle(int[] nums, int n) {
        int[] arr = new int[2*n];
        int j = 0;
        for (int i = 0; i < n; i++) {
            arr[j] = nums[i];
            arr[j+1] =  nums[i + n];
            j = j+2;
        }
        return arr;
    }
}
