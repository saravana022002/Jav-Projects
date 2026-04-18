package Quests.Arrays1;

public class MaxConsecutiveOnes {
    public static void main(String[] args) {
        int[] nums = {1,1,0,1,1,1};
        System.out.println(findMaxConsecutiveOnes(nums));
    }
    public static int findMaxConsecutiveOnes(int[] nums) {
        int i = 0;
        int count = 0;
        int max = Integer.MIN_VALUE;
        while (i < nums.length){
            max = Math.max(count, max);
            if(nums[i] == 0){
                count = -1;
            }
            count++;
            i++;
        }
        max = Math.max(count, max);
        return max;
    }
}
