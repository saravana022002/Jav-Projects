package SDE.MiscProblemsLeetCode;

public class TrappingWater {
    public static void main(String[] args) {

        int[] nums = {4,2,0,3,2,5};
        System.out.println(trapWater(nums));

    }

    private static int trapWater(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        int leftMax = nums[left];
        int rightMax = nums[right];
        int water = 0;
        while (left < right){
           if(leftMax < rightMax){
               left ++;
               if(nums[left] > leftMax){
                   leftMax = nums[left];
               }
               water += leftMax - nums[left];
           }else {
               right --;
               if(nums[right] > rightMax){
                   rightMax = nums[right];
               }
               water += rightMax - nums[right];
           }
        }
        return water;
    }
}
