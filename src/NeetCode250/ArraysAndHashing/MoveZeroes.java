package NeetCode250.ArraysAndHashing;

public class MoveZeroes {

    public static void main(String[] args) {
        int[] nums = {1};
        moveZeroes(nums);
    }
    public static void moveZeroes(int[] nums) {
        int i = 0 ;
        int j = 0 ;
        while(j < nums.length){
            while (j < nums.length && nums[j] == 0){
                j++;
            }
            if(j < nums.length && i != j && nums[j] != 0){
                nums[i] = nums[j];
                i++;
                j++;
            }else if (i == j && nums[j] != 0){
                i++;
                j++;
            }
        }

        while (i < j){
            nums[i] = 0;
            i++;
        }
    }
}
