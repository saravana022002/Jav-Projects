package SDE.LLAndArrays;

public class RemoveDuplicatesFromSortedArr {

    public static void main(String[] args) {
        int[] nums = {0,0,1,1,1,2,2,3,3,4};
//        removeDuplicates(nums);
        optimizedSol(nums);
    }
    public static int removeDuplicates(int[] nums) {
        int length = nums.length;
        if(length == 0){
            return 0;
        }
        int i, j = 0, k = 1;
        for (i = 0; i < length; i++) {
            while (j < length){
                if(nums[i] != nums[j]){
                    k++;
                    break;
                }
                j++;
            }
            if(j < length) {
                nums[i + 1] = nums[j];
            }else {
                break;
            }
        }
        return k;
    }

    public static int optimizedSol(int[] nums) {
        if(nums.length == 0) return 0;
        int i = 0;
        for (int j = 1; j < nums.length; j++) {
            if(nums[j] != nums[i]){
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }
}
