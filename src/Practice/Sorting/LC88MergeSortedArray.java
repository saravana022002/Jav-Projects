package Practice.Sorting;

import java.util.Arrays;

// ============================================================================
// LC 88. Merge Sorted Array   [Easy]
// https://leetcode.com/problems/merge-sorted-array/
// Topics: Array, Two Pointers, Sorting
// ============================================================================
//
// PROBLEM
//   nums1 and nums2 are sorted in non-decreasing order. nums1 has m + n slots: the first m
//   hold real values and the last n are zero padding. Merge nums2 into nums1 so that nums1
//   ends up sorted (nothing is returned - the result lives inside nums1).
//
// EXAMPLES
//   nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3  ->  nums1 = [1,2,2,3,5,6]
//   nums1 = [1],           m = 1, nums2 = [],      n = 0  ->  nums1 = [1]
//   nums1 = [0],           m = 0, nums2 = [1],     n = 1  ->  nums1 = [1]   (that 0 is padding)
//
// CONSTRAINTS
//   nums1.length == m + n,   nums2.length == n,   0 <= m, n <= 200,   1 <= m + n <= 200,
//   -10^9 <= nums1[i], nums2[j] <= 10^9
//   Follow-up: can it be done in O(m + n) time?
//
// INTUITION
//   Simplest correct idea: park nums2 in the padding at the end of nums1 and sort all of
//   nums1. It ignores that both inputs are already sorted, so it costs O((m+n) log(m+n))
//   instead of the O(m + n) the follow-up wants (that one fills nums1 from the BACK with
//   the larger of the two current tails; it is not in this file). The file also holds an
//   unfinished in-place swap idea: while nums1's largest real value is bigger than nums2's
//   smallest, swap them; afterwards the m smallest values sit in nums1 and the n largest in
//   nums2 - but each part still has to be sorted, which that method never does.
//
// APPROACHES IN THIS FILE
//   A. mergeTwoSortedArrays() - UNFINISHED swap idea (see STATUS); does not produce the result
//   B. sortArrays()           - copy nums2 into the padding, then Arrays.sort(nums1)
//                               - O((m+n) log(m+n)) time, O(1) extra space
//                               <- the only working version; main() calls both
//
// APPROACH (as implemented below - sortArrays(), method B)
//   1. Copy nums2 into nums1 right after the real data: nums1[m++] = nums2[j] for j = 0 .. n-1.
//   2. Arrays.sort(nums1): all m + n slots now hold real values, so one sort finishes the job.
//
// DRY RUN   nums1 = [0], m = 0, nums2 = [1], n = 1   (example 3, what main() runs)
//   mergeTwoSortedArrays(a, 0, b, 1) : m == 0 -> returns at once, a is still [0]
//   sortArrays(a, 0, b, 1)           : j=0: nums1[0] = nums2[0] -> a = [1]; sorted: [1]
//   Example 1: [1,2,3,0,0,0] -> after the copy [1,2,3,2,5,6] -> after the sort [1,2,2,3,5,6].
//
// COMPLEXITY
//   B. Time O((m+n) log(m+n)) - the sort dominates, the copy is O(n);  Space O(1) extra
//   A. Time O(min(m, n)) for the swaps - but it does not finish the job
//
// GOTCHAS
//   - nums1 must really have m + n slots, otherwise the copy runs past its end.
//   - nums1[m++] both uses and advances the parameter m while copying.
//   - In A the test "i == m || i == n" keeps the indices in range, and the variable j is
//     unused.
//
// STATUS
//   Unfinished: mergeTwoSortedArrays() only performs the swap phase. For example 1 it leaves
//   nums1 = [1,2,2,0,0,0] and nums2 = [3,5,6] instead of nums1 = [1,2,2,3,5,6]; for
//   nums1 = [1,3,5,0,0,0], nums2 = [2,4,6] it leaves [1,3,2,0,0,0] and [5,4,6]; and for m == 0
//   it does nothing at all. The working solution is the private sortArrays(). The code is
//   left unchanged on purpose. The method names differ from LeetCode's merge(nums1, m,
//   nums2, n), and main() prints nothing (a ends up as [1]).
//
// RELATED
//   - LC 21 Merge Two Sorted Lists - the same merge step on linked lists
//     (solved in src/Practice/LinkedList/LC21MergeTwoSortedLists.java).
//   - LC 977 Squares of a Sorted Array - another two-pointer problem over sorted data.
// ============================================================================
public class LC88MergeSortedArray {
    public static void main(String[] args) {
        // Example 3 - nothing is printed; a ends up as [1] (only sortArrays does the work)
        int []a = {0};
        int []b = {1};
        mergeTwoSortedArrays(a, 0, b, 1);
        sortArrays(a, 0, b, 1);
    }

    // Approach A (UNFINISHED): swap nums1[m-1-i] with nums2[i] while the nums1 value is bigger.
    // Afterwards nums1[0..m-1] holds the m smallest values and nums2 the n largest, but neither
    // part is sorted and nums1's padding is untouched - see STATUS.
    public static  void mergeTwoSortedArrays(int[] nums1, int m, int[] nums2, int n) {
        int i = 0;
        int j = 0;
        int temp;
        // nothing to swap (note: for m == 0 nums2 is NOT copied into nums1 here)
        if(m == 0 || n == 0){
            return;
        }
        // compare the largest real value of nums1 with the smallest of nums2, then the next pair
        while(nums1[m - i - 1] > nums2[i]){
            temp = nums1[m - i - 1];
            nums1[m - i - 1] = nums2[i];
            nums2[i] = temp;
            i++;
            // stop before an index runs off either array
            if(i == m || i == n){
                break;
            }
        }

    }

    // Approach B: copy nums2 into the padding of nums1, then sort everything.
    // O((m+n) log(m+n)) time, O(1) extra space. The working version.
    private static void sortArrays(int[] nums1, int m, int[] nums2, int n) {
        int j = 0;
        // the padding starts at index m
        while (j < n){
            nums1[m++] = nums2[j];
            j++;
        }
        Arrays.sort(nums1);
    }

}
