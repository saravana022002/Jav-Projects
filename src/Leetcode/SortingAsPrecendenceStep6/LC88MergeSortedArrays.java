package Leetcode.SortingAsPrecendenceStep6;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class LC88MergeSortedArrays {
    public static void main(String[] args) {
        int[] nums1 = {1,2,3,0,0,0}, nums2 = {2,5,6};
        int m = 3, n = 3;
        mergeOpt(nums1, m, nums2, n);
    }

    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int j = 0;
        for (int i = nums1.length -  n; i < nums1.length; i++) {
            nums1[i] =  nums2[j++];
        }
        Arrays.sort(nums1);
    }


    public static void mergeOpt(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n -1;
        while(j >= 0){
            if(i >= 0 && nums1[i] >= nums2[j]){
                nums1[k--] = nums1[i--];
            }else {
                nums1[k--] = nums2[j--];
            }
        }
    }

    public static void mergeMine(int[] nums1, int m, int[] nums2, int n) {
        int i = m - 1;
        int j = n - 1;
        int k = m + n - 1;
        int temp = 0;
        while(j >= 0){
            if(i >= 0 && nums1[i] > nums2[j]){
                temp = nums1[i];
                nums1[i] = nums1[k];
                nums1[k] = temp;
                i--;
            }else {
                temp = nums2[j];
                nums2[j] = nums1[k];
                nums1[k] = temp;
                j--;
            }
            k--;
        }
    }

}
