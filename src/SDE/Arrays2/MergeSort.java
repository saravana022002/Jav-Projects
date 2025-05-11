package SDE.Arrays2;

/**
 * Given an array of integers nums, sort the array in ascending order and return it.
 *
 * You must solve the problem without using any built-in functions in O(nlog(n)) time complexity and with the smallest space complexity possible.
 *
 *
 *
 * Example 1:
 *
 * Input: nums = [5,2,3,1]
 * Output: [1,2,3,5]
 * Explanation: After sorting the array, the positions of some numbers are not changed (for example, 2 and 3), while the positions of other numbers are changed (for example, 1 and 5).
 * Example 2:
 *
 * Input: nums = [5,1,1,2,0,0]
 * Output: [0,0,1,1,2,5]
 * Explanation: Note that the values of nums are not necessairly unique.
 */
public class MergeSort {
    public static void main(String[] args) {

        int[] nums = {5, 2, 3, 1, 6, 4};
        int low = 0;
        int high = nums.length - 1;
        mergeSort(nums, low, high);
        System.out.println(nums);
    }

    private static void mergeSort(int[] nums, int low, int high) {

        if(low >= high){
            return;
        }
        int mid = (low + high)/2;
        mergeSort(nums, low, mid);
        mergeSort(nums, mid + 1, high);
        merge(nums, low, mid, high);
    }

    private static void merge(int[] nums, int low, int mid, int high) {

        int[] temp = new int[high+1];
        int left = low;
        int right = mid+1;
        int count = 0;
        while (left <= mid && right <= high){
            if(nums[left] < nums[right]) {
                temp[count] = nums[left];
                left++;
            }else {
                temp[count] = nums[right];
                right++;
            }
            count++;
        }

        while(left <= mid){
            temp[count] = nums[left];
            left++;
            count++;
        }

        while(right <= high){
            temp[count] = nums[right];
            right++;
            count++;
        }

        for (int i = low; i <= high; i++) {
            nums[i] = temp[i - low];
        }
    }


}
