package Practice.Sorting;

// ============================================================================
// LC 912. Sort an Array   [Medium]
// https://leetcode.com/problems/sort-an-array/
// Topics: Array, Divide and Conquer, Sorting, Heap (Priority Queue), Merge Sort, Bucket Sort,
//         Radix Sort, Counting Sort
// ============================================================================
//
// PROBLEM
//   Put an int array into ascending order and hand it back. Built-in sort functions are not
//   allowed, the time must be O(n log n) and the extra memory as small as possible.
//   Duplicates may occur.
//
// EXAMPLES
//   nums = [5,2,3,1]      ->  [1,2,3,5]
//   nums = [5,1,1,2,0,0]  ->  [0,0,1,1,2,5]    the values need not be unique
//
// CONSTRAINTS
//   1 <= nums.length <= 5 * 10^4,   -5 * 10^4 <= nums[i] <= 5 * 10^4
//
// INTUITION
//   Merge sort. A range of 0 or 1 element is already sorted; otherwise sort the two halves
//   separately (recursion) and MERGE them. Merging two sorted runs is easy: keep taking the
//   smaller of the two front elements. Halving gives about log n levels, each level merges n
//   elements in total, so the cost is O(n log n) - provided every merge only costs time in
//   proportion to its own range (see STATUS for where this file does not).
//
// APPROACH (as implemented below)
//   1. main() sorts {5,2,3,1,6,4} with mergeSort(nums, 0, nums.length - 1) (low = 0,
//      high = nums.length - 1) and prints nums.
//   2. mergeSort(nums, low, high): if low >= high at most one element is left -> stop. Else
//      mid = (low + high) / 2, mergeSort(low..mid), mergeSort(mid+1..high), merge(low, mid, high).
//   3. merge(nums, low, mid, high): left walks the left half, right the right half (both
//      sorted) and count is the next free slot in temp.
//        - while both halves have elements, copy the smaller front element into temp
//          (nums[left] < nums[right] takes the left one, otherwise the right one)
//        - then copy what is left of whichever half remains
//        - finally copy temp[0 .. high - low] back into nums[low .. high]
//
// DRY RUN   nums = [5,2,3,1,6,4]   (what main() runs)
//   mergeSort(0,5), mid = 2
//     mergeSort(0,2), mid = 1:  mergeSort(0,1): merge [5] + [2]  => [2,5]
//                               merge(0,1,2):   [2,5] + [3]      => [2,3,5]
//     mergeSort(3,5), mid = 4:  mergeSort(3,4): merge [1] + [6]  => [1,6]
//                               merge(3,4,5):   [1,6] + [4]      => [1,4,6]
//     merge(0,2,5): [2,3,5] + [1,4,6]  ->  1, 2, 3, 4, 5, 6
//   nums = [1,2,3,4,5,6]
//
// COMPLEXITY
//   Time : O(n log n) of real merging work, but the oversized temp buffer (see STATUS) adds up
//          to O(n^2) zero-filling in total
//   Space: O(n) per merge buffer, plus O(log n) recursion depth
//
// GOTCHAS
//   - "<" in merge sends equal elements to the right one first, so this merge is not stable.
//     For plain ints that cannot be seen.
//   - Only the first high - low + 1 slots of temp are ever used; the rest is wasted.
//   - mid = (low + high) / 2 is fine for these sizes (l + (r - l) / 2 is the overflow-proof
//     form).
//
// STATUS
//   Known issue: the output is correct, but the cost is not O(n log n). merge() allocates
//   temp as new int[high + 1] instead of new int[high - low + 1]; Java zero-fills all high + 1
//   slots on every merge, so the total grows like n^2. Measured here: about 190 ms for
//   n = 5 * 10^4 (the LeetCode maximum) and about 1 s for n = 2 * 10^5, against about 3 ms and
//   15 ms with the right-sized buffer of LC912SortAnArrayV1.java.
//   Also, main() prints the array reference (something like [I@2a139a55) instead of its
//   contents; the sorted array is [1, 2, 3, 4, 5, 6]. The method is a private void
//   mergeSort, not LeetCode's int[] sortArray(int[] nums). The code is left unchanged on
//   purpose.
//
// RELATED
//   - LC 88 Merge Sorted Array - the merge step on its own (LC88MergeSortedArray.java in
//     this folder).
//   - LC 493 Reverse Pairs - merge sort that also counts pairs while merging
//     (LC493ReversePairs.java in this folder).
//
// SEE ALSO
//   LC912SortAnArrayV1.java - the same merge sort with a right-sized temp buffer and the
//   LeetCode signature sortArray(int[])
// ============================================================================
public class LC912SortAnArrayV2 {
    public static void main(String[] args) {

        // Expected sorted contents: [1, 2, 3, 4, 5, 6]. The println below prints the array
        // REFERENCE (e.g. [I@2a139a55), not its contents - see STATUS
        int[] nums = {5, 2, 3, 1, 6, 4};
        int low = 0;
        int high = nums.length - 1;
        mergeSort(nums, low, high);
        System.out.println(nums);
    }

    // Sorts nums[low..high]: sort both halves, then merge them.
    private static void mergeSort(int[] nums, int low, int high) {

        // zero or one element: already sorted
        if(low >= high){
            return;
        }
        int mid = (low + high)/2;
        mergeSort(nums, low, mid);
        mergeSort(nums, mid + 1, high);
        merge(nums, low, mid, high);
    }

    // Merges the sorted halves nums[low..mid] and nums[mid+1..high] through temp.
    private static void merge(int[] nums, int low, int mid, int high) {

        // only high - low + 1 slots are used, but high + 1 are allocated and zero-filled
        // on every call - the source of the slowdown described in STATUS
        int[] temp = new int[high+1];
        int left = low;
        int right = mid+1;
        // next free slot in temp
        int count = 0;
        // take the smaller front element (on a tie the right one goes first)
        while (left <= mid && right <= high){
            if(nums[left] < nums[right]) {
                temp[count] = nums[left];
                left++;
            }else {
                temp[count] = nums[right];
                right++;
            }
            count++;
        }

        // whatever is left of one half is already in order
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

        // copy the merged run back
        for (int i = low; i <= high; i++) {
            nums[i] = temp[i - low];
        }
    }


}
