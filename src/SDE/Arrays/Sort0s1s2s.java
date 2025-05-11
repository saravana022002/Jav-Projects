package SDE.Arrays;

import java.util.Arrays;
import java.util.Collections;

public class Sort0s1s2s {

    public static void main(String[] args) {
        int[] nums = {2,0,2,1,1,0};
        myApproach1(nums);
        System.out.println(Arrays.toString(nums));
    }

    private static void myApproach1(int[] nums) {

        int startingAddress = 0;

        int midAddress = startingAddress;
        int endingAddress = nums.length-1;
        int temp;
        while (midAddress <= endingAddress){
            if(nums[midAddress] == 0){
                temp = nums[startingAddress];
                nums[startingAddress] = nums[midAddress];
                nums[midAddress] = temp;
                startingAddress++;
                midAddress++;
            }else if(nums[midAddress] == 1){
                midAddress++;
            } else if(nums[midAddress] == 2){
                temp = nums[endingAddress];
                nums[endingAddress] = nums[midAddress];
                nums[midAddress] = temp;
                endingAddress --;
            }
        }
    }

    private static void myApproach(int[] nums) {
        int count0 = 0;
        int count1 = 0;
        int count2 = 0;
        for (int num : nums) {
            if (num == 0) {
                count0++;
            } else if (num == 1) {
                count1++;
            } else {
                count2++;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if(count0 > 0) {
                nums[i] = 0;
                count0 --;
            } else if(count1 > 0) {
                nums[i] = 1;
                count1 --;
            }else if(count2 > 0) {
                nums[i] = 2;
                count2 --;
            }
        }
    }
}
