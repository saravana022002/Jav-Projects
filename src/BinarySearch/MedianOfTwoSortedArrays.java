package BinarySearch;

public class MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        int[] arrA = {1,2,4};
        int[] arrB = {1,2,3,4,5,6,7};
        System.out.println(findMedianSortedArrays(arrA, arrB));
    }
    public static double findMedianSortedArrays(int[] nums1, int[] nums2){
        if(nums1.length > nums2.length){
            return findMedianSortedArrays(nums2, nums1);
        }
        int low = 0;
        int sizeA = nums1.length;
        int sizeB = nums2.length;

        int high = sizeA;

        while(low <= high){
            // PartitionA
            int partitionA = (low + high) / 2;
            // PartitionB
            int partitionB = (sizeB + sizeA + 1) / 2 - partitionA;

            int leftA = partitionA > 0 ? nums1[partitionA - 1] : Integer.MIN_VALUE;
            int rightA = partitionA >= sizeA ? Integer.MAX_VALUE : nums1[partitionA];

            int leftB = partitionB > 0 ? nums2[partitionB - 1] : Integer.MIN_VALUE;
            int rightB = partitionB >= sizeB ? Integer.MAX_VALUE : nums2[partitionB];


            if(leftA <= rightB && leftB <= rightA){
                if((sizeA + sizeB) % 2 == 0){
                    return  (double) (Math.max(leftA, leftB) + Math.min(rightA, rightB)) / 2;
                }else {
                    return Math.max(leftA, leftB);
                }
            }else if(leftA > rightB) {
                high = partitionA - 1;
            }else {
                low = partitionA + 1;
            }
        }

        return -1;
    }




}
