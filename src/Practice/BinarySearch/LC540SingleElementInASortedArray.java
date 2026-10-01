package Practice.BinarySearch;

// ============================================================================
// LC 540. Single Element in a Sorted Array   [Medium]
// https://leetcode.com/problems/single-element-in-a-sorted-array/
// Topics: Array, Binary Search
// ============================================================================
//
// PROBLEM
//   nums is sorted, and every value appears exactly twice except one value that appears
//   once. Return that single value. The solution must take O(log n) time and O(1) space.
//
// EXAMPLES
//   nums = [1,1,2,3,3,4,4,8,8]  ->  2
//   nums = [3,3,7,7,10,11,11]   ->  10
//
// CONSTRAINTS
//   1 <= nums.length <= 10^5,   0 <= nums[i] <= 10^5
//
// INTUITION
//   Look at where the pairs sit. Left of the single element, each pair occupies index
//   2i and 2i+1 (first copy on an EVEN index). The single element then shifts everything
//   after it by one, so further right each pair occupies 2i+1 and 2i+2 (first copy on
//   an ODD index). So for a probe mid we only ask: does nums[mid] equal its partner as
//   if the pairs were still aligned normally (mid+1 when mid is even, mid-1 when odd)?
//     yes -> still left of the single element -> search to the right of mid
//     no  -> the single element is at mid or to its left -> search the left part
//   The search ends on the first index where the alignment breaks: the single element.
//   (n is always odd, so the last index is even.)
//
// APPROACHES IN THIS FILE
//   A. singleNonDuplicate()  - the partner index is chosen with an explicit even / odd
//                              test on mid - O(log n)
//   B. singleNonDuplicate1() - same idea; mid ^ 1 flips the last bit of mid, which IS the
//                              partner index (even -> mid+1, odd -> mid-1) - O(log n)
//                              <- main() runs this one (the call to A is commented out)
//
// APPROACH (as implemented below, method B)
//   1. l = 0, r = nums.length - 2. The last index is left out so mid ^ 1 can never point
//      past the array.
//   2. While l <= r: mid = (l + r) >> 1.
//   3. nums[mid] == nums[mid ^ 1] -> pairs are still aligned -> l = mid + 1;
//      otherwise r = mid - 1.
//   4. Return nums[l] - l is now the index where the alignment broke.
//   (Method A does the same with "mid % 2 == 0 -> compare with mid + 1, else with mid - 1".)
//
// DRY RUN   nums = [1,1,2,3,3,4,4,8,8]   (n = 9, single element = 2 at index 2)
//   l=0 r=7  mid=3: nums[3]=3 vs nums[3^1=2]=2  differ -> r = 2
//   l=0 r=2  mid=1: nums[1]=1 vs nums[1^1=0]=1  same   -> l = 2
//   l=2 r=2  mid=2: nums[2]=2 vs nums[2^1=3]=3  differ -> r = 1
//   l=2 > r=1: stop, return nums[2] = 2
//
// COMPLEXITY
//   Time : O(log n) for both methods - the search range halves every round
//   Space: O(1)
//
// GOTCHAS
//   - Why r = n - 2: n is odd, so index n - 1 is even and its partner n - 1 + 1 = n would
//     be out of range. The single element can still be the last one; then every probe
//     says "aligned", l runs up to n - 1 and nums[l] is returned.
//   - n = 1 works: r = -1, the loop is skipped and nums[0] is returned.
//   - mid ^ 1 is XOR with 1: 4 ^ 1 = 5 and 5 ^ 1 = 4, so it swaps the two members of an
//     aligned pair.
//   - The test only uses equality, never the actual values, so it would work on any
//     array where equal values are adjacent.
//
// RELATED
//   - LC 136 Single Number (in this repo) - the same "everything twice except one"
//     puzzle on an unsorted array, solved with XOR in O(n).
// ============================================================================
public class LC540SingleElementInASortedArray {

    public static void main(String[] args) {
        // Example 1 - prints 2 (method B; method A gives the same)
        int[] nums = {1,1,2,3,3,4,4,8,8};
//        System.out.println(singleNonDuplicate(nums));
        System.out.println(singleNonDuplicate1(nums));
    }
    // Approach A: explicit even / odd check. O(log n)
    public static int singleNonDuplicate(int[] nums) {
        // r = n - 2 keeps mid + 1 / mid - 1 inside the array
        int l = 0;
        int r = nums.length - 2;
        int mid;
        while(l <= r){
            mid = (l + r) >> 1;
            // even mid: its partner should be mid + 1
            if((mid % 2 == 0)){
                // aligned -> the single element is to the right
                if(nums[mid] == nums[mid + 1]){
                    l = mid + 1;
                }else {
                    r = mid - 1;
                }
            }else{
                // odd mid: its partner should be mid - 1
                if(nums[mid] == nums[mid - 1]){
                    l = mid + 1;
                }else {
                    r = mid - 1;
                }
            }
        }
        // l is the first index where the pairing breaks = the single element
        return nums[l];
    }

    // Approach B: mid ^ 1 gives the partner index without an if. O(log n)   <- main() runs this
    public static int singleNonDuplicate1(int[] nums) {
        int l = 0;
        // the last index is skipped so mid ^ 1 never goes out of range
        int r = nums.length - 2;
        int mid;
        while(l <= r){
            mid = (l + r) >> 1;
            // partner matches (even -> mid + 1, odd -> mid - 1): still left of the single element
            if(nums[mid] == nums[mid ^ 1]){
                l = mid + 1;
            }else {
                r = mid - 1;
            }
        }
        // l is the index where the pairing breaks = the single element
        return nums[l];
    }
}
