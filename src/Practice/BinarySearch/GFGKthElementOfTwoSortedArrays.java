package Practice.BinarySearch;

import java.util.ArrayList;
import java.util.Arrays;

// ============================================================================
// GFG - K-th of Two Sorted Arrays   [Medium]
// https://www.geeksforgeeks.org/problems/k-th-element-of-two-sorted-array1317/1
// Topics: Binary Search (partition), Arrays
// ============================================================================
//
// PROBLEM
//   Two arrays a[] and b[] are each sorted in ascending order. Picture them merged into
//   one sorted array and return the element at position k of that merged array (k counts
//   from 1, so k = 1 is the smallest). We want it without really building the merge.
//
// EXAMPLES
//   a = [2,3,6,7,9],     b = [1,4,8,10],        k = 5  ->  6    merged [1,2,3,4,6,7,8,9,10]
//   a = [1,4,8,10,12],   b = [5,7,11,15,17],    k = 6  ->  10   merged [1,4,5,7,8,10,11,12,15,17]
//
// INTUITION
//   The k smallest values of the union are some prefix of a plus some prefix of b, so the
//   only unknown is HOW MANY come from a - call it partitionA; b supplies k - partitionA.
//   A split is right exactly when everything taken is <= everything not taken. Both arrays
//   are sorted already, so it is enough to compare across them:
//       leftA <= rightB   and   leftB <= rightA
//   (leftX = last element taken from X, rightX = first element NOT taken from X).
//   Then the k-th smallest is the largest element taken: max(leftA, leftB).
//   If leftA > rightB we took too many from a -> take fewer; if leftB > rightA we took too
//   few -> take more. That signal is monotone in partitionA, so binary search finds it.
//
// APPROACH (as implemented below)
//   1. If arr1 is longer than arr2 (m > n), call again with the two swapped, so the search
//      runs over the shorter array (this only speeds it up).
//   2. partitionA can be any value from low = max(0, k - n) to high = min(k, m): arr2 has
//      only n elements, and we never take more than k, or more than m from arr1.
//   3. While low <= high: partitionA = (low + high) / 2 and partitionB = k - partitionA.
//   4. leftA, rightA = arr1 elements just before / at index partitionA; leftB, rightB the
//      same for arr2. Off the ends use Integer.MIN_VALUE (nothing taken) and
//      Integer.MAX_VALUE (nothing left) so the comparisons still pass.
//   5. leftA <= rightB && leftB <= rightA -> return max(leftA, leftB).
//   6. leftA > rightB -> high = partitionA - 1, otherwise low = partitionA + 1.
//   7. If the loop ends without an answer, k was not in 1..m+n: return -1.
//
// DRY RUN   main(): arr1 = [23,34,234,235,2311], arr2 = [2,4,6,8], k = 4
//   m = 5 > n = 4, so swap: arr1 = [2,4,6,8] (m = 4), arr2 = [23,34,234,235,2311] (n = 5)
//   low = max(0, 4 - 5) = 0, high = min(4, 4) = 4
//   partitionA=2, partitionB=2: leftA=4  rightA=6    leftB=34  rightB=234
//       leftB 34 > rightA 6 (too few from arr1)                      -> low = 3
//   partitionA=3, partitionB=1: leftA=6  rightA=8    leftB=23  rightB=34
//       leftB 23 > rightA 8                                          -> low = 4
//   partitionA=4, partitionB=0: leftA=8  rightA=MAX  leftB=MIN rightB=23
//       8 <= 23 and MIN <= MAX: valid split -> return max(8, MIN) = 8
//   (merged, the array starts 2,4,6,8,23,... so the 4th element is indeed 8)
//
// COMPLEXITY
//   Time : O(log(min(m, n))) - binary search over partitionA, whose range has at most
//          min(m, n) + 1 values after the swap; each step is O(1) (ArrayList.get)
//   Space: O(1) - no merged array; the swap adds one extra call frame at most
//
// GOTCHAS
//   - k counts from 1. k > m + n has no answer and returns -1.
//   - m and n are passed in by the caller and must equal the list sizes (main uses size()).
//   - The MIN / MAX sentinels cover the cases "take nothing from one array" and "take all
//     of it" without special code.
//   - Duplicates are fine because the tests use <=.
//
// RELATED
//   - LC 4 Median of Two Sorted Arrays (in this repo) - the median is just a k-th element
//     (or the average of two), solved with the same partition search.
//
// SOURCE
//   Source: GeeksforGeeks (found via Striver SDE Sheet (Binary Search, K-th element of two
//   sorted arrays)).
//   Closest LeetCode problem: LC 4 Median of Two Sorted Arrays
// ============================================================================
public class GFGKthElementOfTwoSortedArrays {
    public static void main(String[] args) {
        // arrA is longer than arrB, so kthElement swaps them. k = 4 -> prints 8
        // (merged, the array starts 2, 4, 6, 8, ...)
        ArrayList<Integer> arrA = new ArrayList<>(Arrays.asList(23, 34, 234, 235, 2311
        ));
        ArrayList<Integer> arrB = new ArrayList<>(Arrays.asList(2, 4, 6, 8));
        int sizeA = arrA.size();
        int sizeB = arrB.size();
        int k = 4;
        System.out.println(kthElement(arrA, arrB, sizeA, sizeB, k));
    }

    // Returns the k-th smallest (k from 1) of two sorted lists of sizes m and n, -1 if k > m + n.
    public static int kthElement(ArrayList<Integer> arr1, ArrayList<Integer> arr2, int m, int n, int k){
        // always binary search the shorter list (a speed-up; correctness does not need it)
        if (m > n) {
            return kthElement(arr2, arr1, n, m, k);
        }

        // partitionA = how many of the k smallest come from arr1: at least k - n
        // (arr2 has only n elements), at most min(k, m)
        int low = Math.max(0, k-n);
        int high = Math.min(k, m);

        while (low <= high) {
            // take partitionA elements from arr1 and the other k - partitionA from arr2
            int partitionA = (low + high) / 2;
            int partitionB = k - partitionA;

            // last element taken / first element left behind in each list; MIN / MAX stand
            // in when nothing is taken / nothing is left
            int leftA = (partitionA == 0) ? Integer.MIN_VALUE : arr1.get(partitionA - 1);
            int rightA = (partitionA == m) ? Integer.MAX_VALUE : arr1.get(partitionA);

            int leftB = (partitionB == 0) ? Integer.MIN_VALUE : arr2.get(partitionB - 1);
            int rightB = (partitionB == n) ? Integer.MAX_VALUE : arr2.get(partitionB);

            // everything taken <= everything left behind: the split is right, and the k-th
            // smallest is the largest element taken
            if (leftA <= rightB && leftB <= rightA) {
                return Math.max(leftA, leftB);
            } else if (leftA > rightB) {
                // took too many from arr1: take fewer
                high = partitionA - 1;
            } else {
                // took too few from arr1 (leftB > rightA): take more
                low = partitionA + 1;
            }
        }

        // only reached when k is outside 1..m+n
        return -1;
    }
}
