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
//   elements in total, so the cost is O(n log n) for every input - there is no bad case like
//   quicksort's.
//
// APPROACH (as implemented below)
//   1. sortArray(nums) calls mergeSort(nums, 0, nums.length - 1) and returns nums (sorted in
//      place).
//   2. mergeSort(nums, l, r): if l >= r the range has at most one element -> stop. Else
//      mid = l + (r - l) / 2, mergeSort(l..mid), mergeSort(mid+1..r), then merge(l, mid, r).
//   3. merge(nums, l, mid, r): p walks the left half, k the right half (both sorted), ans is a
//      temp buffer of r - l + 1 slots.
//        - while both halves have elements, copy the smaller front element into ans
//          (nums[p] <= nums[k] -> the left one wins ties)
//        - then copy what is left of whichever half remains
//        - finally copy ans back into nums[l..r]
//
// DRY RUN   nums = [5,2,3,1]   (what main() runs)
//   mergeSort(0,3), mid = 1
//     mergeSort(0,1), mid = 0: halves [5] and [2]   merge -> 2, 5        => [2,5]
//     mergeSort(2,3), mid = 2: halves [3] and [1]   merge -> 1, 3        => [1,3]
//     merge(0,1,3): [2,5] + [1,3]  ->  1 (right), 2 (left), 3 (right), 5 (left rest)
//   nums = [1,2,3,5]
//
// COMPLEXITY
//   Time : O(n log n) in every case - log n levels, O(n) merging per level
//   Space: O(n) extra - merge() allocates a temp buffer of its own range (at most n), plus
//          O(log n) recursion depth
//
// GOTCHAS
//   - mid = l + (r - l) / 2 cannot overflow the way (l + r) / 2 could for huge indices.
//   - "<=" in merge takes the left element on a tie, which keeps equal elements in their
//     original order (a stable sort). For plain ints that does not matter.
//   - merge() allocates a NEW buffer on every call. It is sized r - l + 1, so the total work
//     is still O(n log n); see LC912SortAnArrayV2.java for what happens with an oversized one.
//   - The statement asks for the smallest space possible: merge sort needs O(n) extra, while
//     an in-place O(n log n) sort such as heap sort needs O(1) (the tags list Heap).
//   - The caller's array is sorted in place and the same array is returned.
//
// STATUS
//   Correct (checked against Arrays.sort on random arrays). main() ignores the return value
//   and prints nothing; the sorted result would be [1, 2, 3, 5].
//
// RELATED
//   - LC 88 Merge Sorted Array - the merge step on its own (LC88MergeSortedArray.java in
//     this folder).
//   - LC 493 Reverse Pairs - merge sort that also counts pairs while merging
//     (LC493ReversePairs.java in this folder).
//
// SEE ALSO
//   LC912SortAnArrayV2.java - the same merge sort with low / high names, a strict "<" and a
//   temp buffer sized high + 1 (slow for large n, see its STATUS)
// ============================================================================
public class LC912SortAnArrayV1 {
    public static void main(String[] args) {
        // Example 1 - nothing is printed; nums ends up as [1, 2, 3, 5]
        int[] nums = {5,2,3,1};
        sortArray(nums);
    }
    // Sorts nums in place with merge sort and returns the same array.
    public static int[] sortArray(int[] nums) {
        mergeSort(nums, 0, nums.length - 1);
        return nums;
    }

    // Sorts nums[l..r]: sort both halves, then merge them.
    static void mergeSort(int[] nums, int l, int r){
        // zero or one element: already sorted
        if (l >= r){
            return;
        }
        int mid = l + (r - l)/ 2;
        mergeSort(nums, l, mid);
        mergeSort(nums, mid + 1, r);
        merge(nums, l, mid, r);
    }

    // Merges the sorted halves nums[l..mid] and nums[mid+1..r] through a temp buffer.
    static void merge(int[] nums, int l, int mid, int r){
        int p = l;
        int k = mid + 1;
        int[] ans = new int[r - l + 1];
        int i = 0;
        // take the smaller front element; on a tie the left one goes first
        while (p <= mid && k <= r){
            if (nums[p] <= nums[k]) {
                ans[i++] = nums[p++];
            } else {
                ans[i++] = nums[k++];
            }
        }
        // whatever is left of one half is already in order
        while (p <= mid){
            ans[i++] = nums[p++];
        }
        while (k <= r){
            ans[i++] = nums[k++];
        }

        // copy the merged run back
        i = 0;
        for (int j = l; j <= r; j++) {
            nums[j] = ans[i++];
        }
    }
}
