package Arrays2;

import java.util.Arrays;

public class MergePrimitiveTypeSortedArrays {
    public static void main(String[] args) {
        int []a = {0};
        int []b = {1};
        mergeTwoSortedArrays(a, 0, b, 1);
        sortArrays(a, 0, b, 1);
    }

    public static  void mergeTwoSortedArrays(int[] nums1, int m, int[] nums2, int n) {
        int i = 0;
        int j = 0;
        int temp;
        if(m == 0 || n == 0){
            return;
        }
        while(nums1[m - i - 1] > nums2[i]){
            temp = nums1[m - i - 1];
            nums1[m - i - 1] = nums2[i];
            nums2[i] = temp;
            i++;
            if(i == m || i == n){
                break;
            }
        }

    }

    private static void sortArrays(int[] nums1, int m, int[] nums2, int n) {
        int j = 0;
        while (j < n){
            nums1[m++] = nums2[j];
            j++;
        }
        Arrays.sort(nums1);
    }

}
