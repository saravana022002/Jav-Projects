package Practice.Sorting;

// ============================================================================
// LC 493. Reverse Pairs   [Hard]
// https://leetcode.com/problems/reverse-pairs/
// Topics: Array, Binary Search, Divide and Conquer, Binary Indexed Tree, Segment Tree,
//         Merge Sort, Ordered Set, Treap
// ============================================================================
//
// PROBLEM
//   Count the pairs of positions (i, j) with i < j and nums[i] > 2 * nums[j]. The values can
//   be any int, negative included.
//
// EXAMPLES
//   nums = [1,3,2,3,1]  ->  2    (index 1, index 4): 3 > 2*1   and   (index 3, index 4): 3 > 2*1
//   nums = [2,4,3,5,1]  ->  3    the last element 1 is beaten by 4, 3 and 5 (each > 2*1)
//
// CONSTRAINTS
//   1 <= nums.length <= 5 * 10^4,   -2^31 <= nums[i] <= 2^31 - 1
//
// INTUITION
//   Same skeleton as counting inversions: split the array in two halves, count the pairs
//   inside each half recursively, then count the pairs that CROSS the middle (i in the left
//   half, j in the right half). Once both halves are sorted, the right elements that are
//   "less than half of" a left element form a prefix of the right half, and that prefix only
//   grows as the left element grows. So one pointer that never moves back counts all the
//   crossing pairs in a single linear pass. Because "twice" breaks the tie-handling of the
//   normal merge, the count is done in its own pass BEFORE the halves are merged.
//
// APPROACH (as implemented below)
//   1. mergeSort(nums, low, high): a range of 0 or 1 element has no pairs (return 0).
//      Else mid = (low + high) / 2 and the total (named inversions, but it counts reverse
//      pairs) = mergeSort(left half) + mergeSort(right half) + countPairs(...).
//   2. countPairs(nums, low, mid, high) on the two sorted halves: right starts at mid + 1; for
//      each left from low to mid, while right <= high and (long) nums[left] > 2L * nums[right],
//      move right forward. Then right - (mid + 1) right elements are beaten by nums[left].
//   3. merge(nums, low, mid, high): ordinary merge of the two sorted halves through temp, so
//      the level above sees a sorted range.
//   4. main() calls mergeSort(nums, 0, nums.length - 1) and prints the total.
//
// DRY RUN   nums = [1,3,2,3,1]   (example 1)
//   top level: low=0, high=4, mid=2; the recursion sorts the halves into [1,2,3] and [1,3]
//   pairs inside the left half [1,3,2]: 0      pairs inside the right half [3,1]: 1 (3 > 2*1)
//   countPairs on left [1,2,3] and right [1,3] (right starts at index 3):
//     left=1: 1 > 2*1? no                         -> adds 0
//     left=2: 2 > 2*1? no                         -> adds 0
//     left=3: 3 > 2*1 yes, right -> 4; 3 > 2*3? no -> adds 4 - 3 = 1
//   total = 0 + 1 + 1 = 2
//   main() runs six copies of Integer.MAX_VALUE: no x satisfies x > 2x here, so it prints 0.
//
// COMPLEXITY
//   Time : O(n log n) - log n levels, and each level does a linear count plus a linear merge
//   Space: O(n) for the temp buffers, plus O(log n) recursion depth
//
// GOTCHAS
//   - Overflow: 2 * nums[j] can leave the int range. The comparison is done in long:
//     (long) nums[left] > 2L * nums[right]. In int arithmetic 2 * 2147483647 wraps to -2
//     and main()'s six equal values would wrongly count 15 pairs instead of 0.
//   - Count BEFORE merge(): after merging, the boundary between the halves is gone.
//   - right only moves forward inside one countPairs call, because the left half is sorted
//     ascending (works for negative values too).
//   - The array is sorted as a side effect.
//
// STATUS
//   Correct (checked against a brute force, including Integer.MAX_VALUE / MIN_VALUE inputs).
//   The method is not named like LeetCode's reversePairs(int[]): main() calls the private
//   mergeSort() directly. The "Write your code here." comment is a template leftover.
//
// RELATED
//   - LC 315 Count of Smaller Numbers After Self - a per-element version of this counting.
//   - GFGCountInversions.java (same folder) - the same merge-sort skeleton with the plain
//     condition nums[i] > nums[j], counted inside the merge step.
// ============================================================================
public class LC493ReversePairs {
    public static void main(String[] arg) {
        // Write your code here.
        // Overflow trap: six Integer.MAX_VALUE, no pair qualifies. Expected output: 0
        int[] nums = {2147483647,2147483647,2147483647,2147483647,2147483647,2147483647};
        int low = 0;
        int high = nums.length - 1;
        int inversions = mergeSort(nums, low, high);
        System.out.println(inversions);
    }

    // Returns the number of reverse pairs in nums[low..high] and leaves that range sorted.
    private static int mergeSort(int[] nums, int low, int high) {

        int inversions = 0;
        // 0 or 1 element: no pairs
        if(low >= high){
            return inversions;
        }
        int mid = (low + high)/2;
        // pairs inside the left half + inside the right half + pairs across the middle
        inversions += mergeSort(nums, low, mid);
        inversions += mergeSort(nums, mid + 1, high);
        inversions += countPairs(nums, low, mid, high);
        // only now merge, so that the level above sees a sorted range
        merge(nums, low, mid, high);
        return inversions;
    }

    // Counts pairs (left in nums[low..mid], right in nums[mid+1..high]) with
    // nums[left] > 2 * nums[right]. Both halves are already sorted here.
    private static int countPairs(int[] nums, int low, int mid, int high) {

        // right = first right-half element NOT beaten by the current nums[left]
        int right = mid+1;
        int inversions = 0;

        for(int left = low; left <= mid; left++){
            // long arithmetic: 2 * nums[right] may overflow int
            while(right <= high && (long)nums[left] > 2L * nums[right]){
                right++;
            }
            // the right elements from mid + 1 up to right - 1 are all beaten by nums[left]
            inversions = inversions + (right - (mid + 1));
        }
        return inversions;
    }
    // Plain merge of the sorted halves nums[low..mid] and nums[mid+1..high].
    private static void merge(int[] nums, int low, int mid, int high) {

        int[] temp = new int[high - low + 1];
        int left = low;
        int right = mid+1;
        int count = 0;
        while (left <= mid && right <= high){
            if(nums[left] <= nums[right]) {
                temp[count] = nums[left];
                left++;
            }else {
                temp[count] = nums[right];
                right++;
            }
            count++;
        }

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

        for (int i = low; i <= high; i++) {
            nums[i] = temp[i - low];
        }
    }
}
