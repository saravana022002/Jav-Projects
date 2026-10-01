package Practice.BinarySearch;

// ============================================================================
// LC 4. Median of Two Sorted Arrays   [Hard]
// https://leetcode.com/problems/median-of-two-sorted-arrays/
// Topics: Array, Binary Search, Divide and Conquer
// ============================================================================
//
// PROBLEM
//   nums1 (size m) and nums2 (size n) are both sorted ascending. Return the median of all
//   m + n numbers taken together: the middle value of the merged sorted array, or the
//   average of the two middle values when m + n is even. The running time must be
//   O(log(m + n)), so the two arrays cannot simply be merged.
//
// EXAMPLES
//   nums1 = [1,3], nums2 = [2]      ->  2.0    merged [1,2,3]
//   nums1 = [1,2], nums2 = [3,4]    ->  2.5    merged [1,2,3,4], (2 + 3) / 2
//
// CONSTRAINTS
//   0 <= m <= 1000,   0 <= n <= 1000,   1 <= m + n <= 2000,   -10^6 <= values <= 10^6
//
// INTUITION
//   The median only depends on where the merged array is cut into a LEFT half and a RIGHT
//   half of (almost) equal size. Cut both arrays: the first partitionA elements of nums1
//   and the first partitionB of nums2 go left, the rest go right, with
//   partitionA + partitionB = (m + n + 1) / 2 (the left side gets the extra element when
//   m + n is odd). The cut is correct when every left element is <= every right element.
//   Each array is already sorted, so it is enough to check across the arrays:
//       leftA <= rightB   and   leftB <= rightA
//   (leftX = last element on the left of X's cut, rightX = first on the right).
//   Then the median is read off the four border values:
//       odd  total -> max(leftA, leftB)
//       even total -> (max(leftA, leftB) + min(rightA, rightB)) / 2
//   Choosing partitionA fixes partitionB, so only ONE cut is searched, by binary search:
//   leftA > rightB means nums1 gave too many -> move the cut left; otherwise nums1 gave
//   too few -> move it right.
//
// APPROACH (as implemented below)
//   1. If nums1 is longer than nums2, call again with them swapped, so nums1 is the
//      shorter one. This is required: it keeps partitionB inside 0..sizeB.
//   2. low = 0, high = sizeA.
//   3. While low <= high: partitionA = (low + high) / 2 and
//      partitionB = (sizeA + sizeB + 1) / 2 - partitionA.
//   4. leftA, rightA, leftB, rightB are the border elements; Integer.MIN_VALUE /
//      Integer.MAX_VALUE stand in when a cut is at the very start / end of an array.
//   5. leftA <= rightB && leftB <= rightA -> valid cut: return the median (odd / even
//      formulas above; the even one is computed as a double).
//   6. leftA > rightB -> high = partitionA - 1, otherwise low = partitionA + 1.
//   7. return -1 is a fallback that valid (sorted, non-empty in total) input never reaches.
//
// DRY RUN   nums1 = [1,2,4], nums2 = [1,2,3,4,5,6,7]   (m = 3, n = 7, left side = 5)
//   low = 0, high = 3
//   partitionA=1, partitionB=4: leftA=1 rightA=2   leftB=4 rightB=5
//       leftB 4 > rightA 2: nums1 gave too few                      -> low = 2
//   partitionA=2, partitionB=3: leftA=2 rightA=4   leftB=3 rightB=4
//       2 <= 4 and 3 <= 4: valid cut. Even total (10):
//       (max(2,3) + min(4,4)) / 2 = (3 + 4) / 2 = 3.5
//   left side = {1,2} + {1,2,3}, right side = {4} + {4,5,6,7}
//
// COMPLEXITY
//   Time : O(log(min(m, n))) - binary search over the cut in the shorter array
//          (better than the required O(log(m + n)))
//   Space: O(1)
//
// GOTCHAS
//   - The swap in step 1 is not just an optimisation: with the longer array first,
//     partitionB can go negative or past sizeB and nums2[partitionB] throws an
//     ArrayIndexOutOfBoundsException.
//   - The left side holds (m + n + 1) / 2 elements, so for an odd total the median is the
//     LARGEST left element; that is why the odd case returns max(leftA, leftB).
//   - The cast (double) is applied to the SUM before the division by 2, so the even case
//     gives 2.5, not 2.
//   - One array may be empty: [] and [3] -> 3.0. The MIN / MAX sentinels cover it.
//
// RELATED
//   - GFGKthElementOfTwoSortedArrays.java (in this repo) - the same partition search for
//     any k; the median is just the element at position (m + n + 1) / 2 (and, for an
//     even total, the one after it).
//   - LC 2387 Median of a Row Wise Sorted Matrix - another median of sorted data found
//     without sorting everything.
// ============================================================================
public class LC4MedianOfTwoSortedArrays {
    public static void main(String[] args) {
        // merged array has 10 numbers, its middle two are 3 and 4 -> prints 3.5
        int[] arrA = {1,2,4};
        int[] arrB = {1,2,3,4,5,6,7};
        System.out.println(findMedianSortedArrays(arrA, arrB));
    }
    // Returns the median of the two sorted arrays without merging them.
    public static double findMedianSortedArrays(int[] nums1, int[] nums2){
        // always cut the shorter array (needed so that partitionB stays in range)
        if(nums1.length > nums2.length){
            return findMedianSortedArrays(nums2, nums1);
        }
        int low = 0;
        int sizeA = nums1.length;
        int sizeB = nums2.length;

        // partitionA can be anything from 0 (take nothing from nums1) to sizeA (take all)
        int high = sizeA;

        while(low <= high){
            // PartitionA
            // how many elements of nums1 go to the left half
            int partitionA = (low + high) / 2;
            // PartitionB
            // nums2 supplies the rest of the (m + n + 1) / 2 left-half elements
            int partitionB = (sizeB + sizeA + 1) / 2 - partitionA;

            // border values around each cut; MIN / MAX mean "nothing on that side"
            int leftA = partitionA > 0 ? nums1[partitionA - 1] : Integer.MIN_VALUE;
            int rightA = partitionA >= sizeA ? Integer.MAX_VALUE : nums1[partitionA];

            int leftB = partitionB > 0 ? nums2[partitionB - 1] : Integer.MIN_VALUE;
            int rightB = partitionB >= sizeB ? Integer.MAX_VALUE : nums2[partitionB];


            // valid cut: everything on the left is <= everything on the right
            if(leftA <= rightB && leftB <= rightA){
                if((sizeA + sizeB) % 2 == 0){
                    // even total: average of the two middle values
                    return  (double) (Math.max(leftA, leftB) + Math.min(rightA, rightB)) / 2;
                }else {
                    // odd total: the left half has the extra element, so it is the median
                    return Math.max(leftA, leftB);
                }
            }else if(leftA > rightB) {
                // nums1 put too many elements on the left: move its cut left
                high = partitionA - 1;
            }else {
                // nums1 put too few on the left (leftB > rightA): move its cut right
                low = partitionA + 1;
            }
        }

        // not reached for valid input
        return -1;
    }




}
