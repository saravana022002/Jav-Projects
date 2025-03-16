package Arrays;

import java.util.Arrays;
import java.util.Collections;

public class Sort0s1s2s {

    public static void main(String[] args) {
        int[] nums = {0,0};
        myApproach1(nums);
        System.out.println(Arrays.toString(nums));
    }

    private static void myApproach1(int[] nums) {

        int startingAddress = 0;

        int loopCount = 0;
        int endingAddress = nums.length-1;
        int temp;
        while (startingAddress < endingAddress) {
            if(nums[startingAddress] == 0){
                startingAddress++;
            }else if(nums[startingAddress] == 2){
                temp = nums[startingAddress];
                nums[startingAddress] = nums[endingAddress];
                nums[endingAddress] = temp;
                endingAddress --;
            }

            if(nums[endingAddress] == 2){
                endingAddress--;
            }else if(nums[endingAddress] == 0){
                temp = nums[endingAddress];
                nums[endingAddress] = nums[startingAddress];
                nums[startingAddress] = temp;
                startingAddress++;
            }

            if(startingAddress > endingAddress){
                break;
            }
            if(nums[endingAddress] == 1){

                if(loopCount == nums.length){
                    break;
                }
                if(nums[endingAddress-1] == 2){
                    temp = nums[endingAddress];
                    nums[endingAddress] = nums[endingAddress-1];
                    nums[endingAddress-1] = temp;
                    endingAddress --;
                }
                loopCount ++;
            }
            if(nums[startingAddress] == 1){
                if(loopCount == nums.length){
                    break;
                }
                if(nums[startingAddress+1] == 0) {
                    temp = nums[startingAddress];
                    nums[startingAddress] = nums[startingAddress + 1];
                    nums[startingAddress + 1] = temp;
                    startingAddress++;
                }
                loopCount ++;
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
