package Practice.Sorting;

// ============================================================================
// GFG - Count Inversions   [Medium]
// https://www.geeksforgeeks.org/problems/inversion-of-array-1587115620/1
// Topics: Merge Sort, Divide and Conquer, Counting
// ============================================================================
//
// PROBLEM
//   Given an int array arr, return its inversion count: the number of position pairs (i, j)
//   with i < j and arr[i] > arr[j]. The comparison is strict, so equal values do not count.
//
// EXAMPLES
//   arr = [2,4,1,3,5]  ->  3    the inversions are (2,1), (4,1) and (4,3)
//   arr = [2,3,4,5,6]  ->  0    already sorted
//   arr = [10,10,10]   ->  0    equal values are not inversions
//
// INTUITION
//   Testing every pair costs O(n^2). Merge sort can count the pairs while it sorts: split the
//   array, count the inversions inside each half recursively, then count the ones that CROSS
//   the middle while merging. At merge time both halves are already sorted, so when the next
//   right-half element is smaller than the current left-half element, it is also smaller than
//   every left element after it - it forms an inversion with ALL (mid - left + 1) left
//   elements still waiting, and they can be added in one step. On a tie the left element is
//   taken first (<=), so equal values are not counted.
//
// APPROACH (as implemented below)
//   1. mergeSort(nums, low, high): a range of 0 or 1 element has no inversions (return 0).
//      Otherwise mid = (low + high) / 2 and
//      inversions = mergeSort(low..mid) + mergeSort(mid+1..high) + merge(low, mid, high).
//   2. merge(nums, low, mid, high) builds the sorted range in temp; left and right walk the
//      two halves, count is the write position in temp, and while both halves are non-empty:
//        - nums[left] <= nums[right] -> take nums[left]
//        - otherwise take nums[right] and add mid - left + 1 to inversions
//   3. Then the rest of the left half, the rest of the right half and finally temp is copied
//      back into nums[low..high], so the range is sorted for the level above.
//   4. main() calls mergeSort(nums, 0, nums.length - 1) and prints the total.
//
// DRY RUN   nums = [4,3,2,1]   (what main() runs; every pair is an inversion)
//   merge [4] + [3]   : 4 > 3 -> take 3, add 0 - 0 + 1 = 1         -> [3,4]   1
//   merge [2] + [1]   : 2 > 1 -> take 1, add 1                     -> [1,2]   1
//   merge [3,4]+[1,2] : 3 > 1 -> take 1, add 1 - 0 + 1 = 2
//                       3 > 2 -> take 2, add 2, then copy 3, 4     -> [1,2,3,4]   4
//   total = 1 + 1 + 4 = 6
//
// COMPLEXITY
//   Time : O(n log n) - log n levels of merging, each touching all n elements
//   Space: O(n) for the temp buffers, plus O(log n) recursion depth
//
// GOTCHAS
//   - Compare with <= (take the left element on a tie). With < equal values would be counted
//     and [10,10,10] would give 3.
//   - Add (mid - left + 1), not 1: the left half is sorted, so every remaining left element is
//     greater than the right element that was just taken.
//   - The array is sorted as a side effect. That is required for the counting above, but the
//     caller's array is changed.
//   - The counters are int. The count is at most n(n-1)/2, which fits an int up to 65536
//     elements; a reversed array of 65537 elements already overflows (returns -2147450880
//     instead of 2147516416). Use long if the limits allow more.
//
// STATUS
//   Correct (checked against an O(n^2) brute force; the overflow above is the only limit).
//   There is no GFG-style entry method: main() calls the private mergeSort() directly and
//   its own array ends up sorted.
//
// RELATED
//   - LC 493 Reverse Pairs - the same merge-and-count technique with the condition
//     nums[i] > 2 * nums[j] (solved in LC493ReversePairs.java in this folder).
//   - LC 315 Count of Smaller Numbers After Self - a per-element version of the same counting.
//
// SOURCE
//   Source: GeeksforGeeks (found via Striver SDE Sheet (Arrays Day 2, Count Inversions)).
// ============================================================================
public class GFGCountInversions {
    public static void main(String[] arg) {
            // Write your code here.
            // Reversed array: all 6 pairs are inversions. Expected output: 6
            int[] nums = {4, 3, 2, 1};
            int low = 0;
            int high = nums.length - 1;
            int inversions = mergeSort(nums, low, high);
            System.out.println(inversions);
    }

    // Returns the inversion count of nums[low..high] and leaves that range sorted.
    private static int mergeSort(int[] nums, int low, int high) {

        int inversions = 0;
        // 0 or 1 element: nothing to count
        if(low >= high){
            return inversions;
        }
        int mid = (low + high)/2;
        // inversions inside the left half + inside the right half + pairs across the middle
        inversions += mergeSort(nums, low, mid);
        inversions += mergeSort(nums, mid + 1, high);
        inversions += merge(nums, low, mid, high);
        return inversions;
    }
    // Merges the sorted halves nums[low..mid] and nums[mid+1..high] and returns the number of
    // pairs (left element, right element) with left > right.
    private static int merge(int[] nums, int low, int mid, int high) {

        int[] temp = new int[high - low + 1];
        int left = low;
        int right = mid+1;
        // next free slot in temp
        int count = 0;
        int inversions = 0;
        while (left <= mid && right <= high){
            if(nums[left] <= nums[right]) {
                // left is not larger (ties included): no inversion
                temp[count] = nums[left];
                left++;
            }else {
                // right is smaller than nums[left] and than every left element after it
                temp[count] = nums[right];
                right++;
                inversions += mid - left + 1;
            }
            count++;
        }

        // leftovers of the left half
        while(left <= mid){
            temp[count] = nums[left];
            left++;
            count++;
        }

        // leftovers of the right half
        while(right <= high){
            temp[count] = nums[right];
            right++;
            count++;
        }

        // write the merged, sorted range back
        for (int i = low; i <= high; i++) {
            nums[i] = temp[i - low];
        }
        return inversions;
    }
}
