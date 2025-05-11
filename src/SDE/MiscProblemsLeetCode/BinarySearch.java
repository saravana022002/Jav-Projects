package SDE.MiscProblemsLeetCode;

public class BinarySearch {
    public static void main(String[] args) {
        int[] nums = {-1,0,3,5,9,12};
        int target = 13;
        search(nums, target);
    }

    public static int search(int[] nums, int target) {

        int right = nums.length - 1;
        int left = 0;
        int half = (left + right) / 2;

        while(left <= right){
            if(target < nums[half]){
                right = half - 1;
            }else if (target > nums[half]){
                left = half + 1;
            }else if (target == nums[half]){
                return half;
            }
            half = (right + left)/2;
        }
        return -1;
    }
}
