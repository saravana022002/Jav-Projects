package Quests.Arrays2;

import java.util.Arrays;
import java.util.Comparator;

public class SmallerNumbersThanCurrent {
    public static void main(String[] args) {
        int[] nums = {8, 1, 2, 2, 3};
        smallerNumbersThanCurrentOpt(nums);
    }

    public static int[] smallerNumbersThanCurrent(int[] nums) {
        int[][] tempAns = new int[nums.length][3];
        for (int i = 0; i < nums.length; i++) {
            tempAns[i][0] = nums[i];
            tempAns[i][1] = 0;
            tempAns[i][2] = i;
        }
        Arrays.sort(tempAns, Comparator.comparingInt(a -> a[0]));
        int count = 1;
        int prev = tempAns[0][0];
        tempAns[0][1] = 0;
        for (int i = 1; i < tempAns.length; i++) {
            if (prev != tempAns[i][0]) {
                tempAns[i][1] = count;
            } else {
                tempAns[i][1] = tempAns[i - 1][1];
            }
            prev = tempAns[i][0];
            count++;
        }
        for (int i = 0; i < tempAns.length; i++) {
            nums[tempAns[i][2]] = tempAns[i][1];
        }
        return nums;
    }

    public static int[] smallerNumbersThanCurrentOpt(int[] nums) {
        int[] freq = new int[101];
        for (int num : nums) {
            freq[num]++;
        }

        for (int i = 1; i < freq.length; i++) {
            freq[i] += freq[i - 1];
        }


        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] == 0 ? 0 : freq[nums[i] - 1];
        }
        return nums;
    }

}