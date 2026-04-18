package NeetCode250.ArraysAndHashing;

public class MergeSort {
    public static void main(String[] args) {
        int[] nums = {5,2,3,1};
        sortArray(nums);
    }
    public static int[] sortArray(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    static void mergeSort(int[] nums, int l, int r){
        if (l >= r){
            return;
        }
        int mid = l + (r - l)/ 2;
        mergeSort(nums, l, mid);
        mergeSort(nums, mid + 1, r);
        merge(nums, l, mid, r);
    }

    static void merge(int[] nums, int l, int mid, int r){
        int p = l;
        int k = mid + 1;
        int[] ans = new int[r - l + 1];
        int i = 0;
        while (p <= mid && k <= r){
            if (nums[p] <= nums[k]) {
                ans[i++] = nums[p++];
            } else {
                ans[i++] = nums[k++];
            }
        }
        while (p <= mid){
            ans[i++] = nums[p++];
        }
        while (k <= r){
            ans[i++] = nums[k++];
        }

        i = 0;
        for (int j = l; j <= r; j++) {
            nums[j] = ans[i++];
        }
    }
}
