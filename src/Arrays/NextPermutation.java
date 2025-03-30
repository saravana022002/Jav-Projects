package Arrays;

import java.util.Arrays;
import java.util.Collections;

public class NextPermutation {
    public static void main(String[] args) {
        int[] nums = {5,1,1};
        nextPermutation(nums);
    }

    public static void nextPermutation(int[] nums) {
        int arraySize = nums.length;
        int i = arraySize - 1;
        if(arraySize == 1){
            return;
        }
        while(nums[i-1] >= nums[i]){
            if(i == 1){
                reverse(nums, 0, arraySize -1);
                return;
            }
            i--;
        }
        int j = arraySize - 1;
        while (nums[i-1] >= nums[j]){
            if(j == 0){
                return;
            }
            j--;
        }
        int temp;
        temp = nums[i-1];
        nums[i-1] = nums[j];
        nums[j] = temp;
        reverse(nums, i, arraySize -1);
    }

    public static void reverse(int[] array, int left, int right){
        while (left < right) {
            int temp = array[left];
            array[left] = array[right];
            array[right] = temp;
            left++;
            right--;
        }
    }
}
