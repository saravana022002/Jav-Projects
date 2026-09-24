package Leetcode.Daily;

public class LC3550SmallestIndex {
    public static void main(String[] args) {
        int[] nums = {1,3,2};
    }

    public int smallestIndex(int[] nums) {
        int temp = 0;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count = 0;
            while(nums[i] > 0){
                temp = nums[i] % 10;
                nums[i] = nums[i] / 10;
                count += temp;
            }
            if(count == i){
                return i;
            }
        }
        return -1;
    }
}
