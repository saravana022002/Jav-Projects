package Leetcode.Contest191;

public class LC191CountSpecialIntegers1 {
    public static void main(String[] args) {
        int[] nums = {1,8,1,5,1,5,8,5};
        System.out.println(countSpecialIntegers(nums));
    }

    public static int countSpecialIntegers(int[] nums) {
        int[][] can = new int[101][3];
        for (int i = 0; i < nums.length; i++) {
            if(can[nums[i]][0] == -1){
                continue;
            }
            can[nums[i]][0]++;
            if(can[nums[i]][0] == 3 && can[nums[i]][1] != i - can[nums[i]][2]) {
                can[nums[i]][0] = -1;
                continue;
            }
            can[nums[i]][1] =  i - can[nums[i]][2];
            can[nums[i]][2] =  i;
        }

        int count = 0;
        for (int i = 1; i < 101; i++) {
            if(can[i][0] == 3){
                count++;
            }
        }
        return count;
    }
}
