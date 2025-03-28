package Arrays2;

import java.util.Arrays;

public class MergePrimitiveTypeSortedArrays {
    public static void main(String[] args) {
        long []a = {10};
        long []b = {4,6,7,8};
        mergeTwoSortedArrays(a, b);
    }

    private static void mergeTwoSortedArrays(long[] a, long[] b) {

        int sizeA = a.length;
        int sizeB = b.length;
        int i = 0;
        long temp;
        while(a[sizeA - i - 1] > b[i]){
            temp = a[sizeA - i - 1];
            a[sizeA - i - 1] = b[i];
            b[i] = temp;
            i++;
            if(i == sizeA || i == sizeB){
                break;
            }
        }
        Arrays.sort(a);
        Arrays.sort(b);
        int n = 0;
    }

}
