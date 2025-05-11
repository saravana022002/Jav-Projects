package BinarySearch;

import java.util.ArrayList;
import java.util.Arrays;

public class KthElementInSorted {
    public static void main(String[] args) {
        ArrayList<Integer> arrA = new ArrayList<>(Arrays.asList(23, 34, 234, 235, 2311
        ));
        ArrayList<Integer> arrB = new ArrayList<>(Arrays.asList(2, 4, 6, 8));
        int sizeA = arrA.size();
        int sizeB = arrB.size();
        int k = 4;
        System.out.println(kthElement(arrA, arrB, sizeA, sizeB, k));
    }

    public static int kthElement(ArrayList<Integer> arr1, ArrayList<Integer> arr2, int m, int n, int k){
        if (m > n) {
            return kthElement(arr2, arr1, n, m, k);
        }

        int low = Math.max(0, k-n);
        int high = Math.min(k, m);

        while (low <= high) {
            int partitionA = (low + high) / 2;
            int partitionB = k - partitionA;

            int leftA = (partitionA == 0) ? Integer.MIN_VALUE : arr1.get(partitionA - 1);
            int rightA = (partitionA == m) ? Integer.MAX_VALUE : arr1.get(partitionA);

            int leftB = (partitionB == 0) ? Integer.MIN_VALUE : arr2.get(partitionB - 1);
            int rightB = (partitionB == n) ? Integer.MAX_VALUE : arr2.get(partitionB);

            if (leftA <= rightB && leftB <= rightA) {
                return Math.max(leftA, leftB);
            } else if (leftA > rightB) {
                high = partitionA - 1;
            } else {
                low = partitionA + 1;
            }
        }

        return -1;
    }
}
