package SDE.Arrays2;

public class CountNoInversions {
    public static void main(String[] arg) {
            // Write your code here.
            int[] nums = {4, 3, 2, 1};
            int low = 0;
            int high = nums.length - 1;
            int inversions = mergeSort(nums, low, high);
            System.out.println(inversions);
    }

    private static int mergeSort(int[] nums, int low, int high) {

        int inversions = 0;
        if(low >= high){
            return inversions;
        }
        int mid = (low + high)/2;
        inversions += mergeSort(nums, low, mid);
        inversions += mergeSort(nums, mid + 1, high);
        inversions += merge(nums, low, mid, high);
        return inversions;
    }
    private static int merge(int[] nums, int low, int mid, int high) {

        int[] temp = new int[high - low + 1];
        int left = low;
        int right = mid+1;
        int count = 0;
        int inversions = 0;
        while (left <= mid && right <= high){
            if(nums[left] <= nums[right]) {
                temp[count] = nums[left];
                left++;
            }else {
                temp[count] = nums[right];
                right++;
                inversions += mid - left + 1;
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
        return inversions;
    }
}
