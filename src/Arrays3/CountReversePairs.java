package Arrays3;

public class CountReversePairs {
    public static void main(String[] arg) {
        // Write your code here.
        int[] nums = {2147483647,2147483647,2147483647,2147483647,2147483647,2147483647};
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
        inversions += countPairs(nums, low, mid, high);
        merge(nums, low, mid, high);
        return inversions;
    }

    private static int countPairs(int[] nums, int low, int mid, int high) {

        int right = mid+1;
        int inversions = 0;

        for(int left = low; left <= mid; left++){
            while(right <= high && (long)nums[left] > 2L * nums[right]){
                right++;
            }
            inversions = inversions + (right - (mid + 1));
        }
        return inversions;
    }
    private static void merge(int[] nums, int low, int mid, int high) {

        int[] temp = new int[high - low + 1];
        int left = low;
        int right = mid+1;
        int count = 0;
        while (left <= mid && right <= high){
            if(nums[left] <= nums[right]) {
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
