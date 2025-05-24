package SDE.MiscProblemsLeetCode;

public class RemoveElement {
    public static void main(String[] args) {
        int[] nums =  {3,2,2,3};
        int val = 3;
        removeElement(nums, val);
    }
    public static int removeElement(int[] nums, int val) {
        int i = 0;
        int j = nums.length - 1;
        int temp = 0;
        while (i <= j){
            if(nums[i] == val && nums[j] != val){
                temp = nums[j];
                nums[j] = nums[i];
                nums[i] = temp;
                i++;
                j--;
            }else if(nums[i] == val && nums[j] == val){
                j--;
            }else {
                i++;
            }
        }
        return i;
    }
}
