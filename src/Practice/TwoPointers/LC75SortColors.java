package Practice.TwoPointers;

import java.util.Arrays;
import java.util.Collections;

// ============================================================================
// LC 75. Sort Colors   [Medium]
// https://leetcode.com/problems/sort-colors/
// Topics: Array, Two Pointers, Sorting, Quicksort, Bubble Sort
// ============================================================================
//
// PROBLEM
//   nums holds objects coloured red, white or blue, written as 0, 1 and 2. Sort the array in
//   place so that all 0s come first, then all 1s, then all 2s, without using the library sort.
//   (Follow-up: do it in one pass with constant extra space.)
//
// EXAMPLES
//   nums = [2,0,2,1,1,0]  ->  [0,0,1,1,2,2]
//   nums = [2,0,1]        ->  [0,1,2]
//
// CONSTRAINTS
//   n == nums.length,   1 <= n <= 300,   every nums[i] is 0, 1 or 2
//
// INTUITION
//   With only three possible values a general sort is overkill. Either count how many 0s, 1s
//   and 2s there are and rewrite the array (two passes), or keep three zones inside the array
//   - the 0s at the front, the 2s at the back, the 1s in the middle - and a scanner that walks
//   through the still-unknown middle part, sending every element to its zone with at most one
//   swap. That second way is the "Dutch national flag" partition and needs a single pass.
//
// APPROACHES IN THIS FILE
//   A. myApproach1() - Dutch national flag with three indices (startingAddress, midAddress,
//                      endingAddress), one pass - O(n) time, O(1) space
//                      <- main() runs this one
//   B. myApproach()  - counting sort: count the 0s, 1s and 2s, then overwrite the array in
//                      that order - two passes, O(n) time, O(1) space. Nothing calls it.
//
// APPROACH (as implemented below, method A)
//   Names: startingAddress = "low", midAddress = "mid", endingAddress = "high". Invariant:
//     nums[0 .. low-1] are 0s,   nums[low .. mid-1] are 1s,
//     nums[mid .. high] are not examined yet,   nums[high+1 .. n-1] are 2s.
//   1. low = 0, mid = 0, high = nums.length - 1.
//   2. While mid <= high, look at nums[mid]:
//      - 0 : swap it with nums[low]; low++ and mid++ (what comes from low is a 1, or the same
//            element when low == mid, so it is already classified)
//      - 1 : it is in the right zone already; mid++
//      - 2 : swap it with nums[high]; high--. mid stays, because the value that just arrived
//            from the end has not been examined.
//
// DRY RUN   A on nums = [2,0,2,1,1,0]   (the input main() uses)   low=0 mid=0 high=5
//   mid=0 sees 2  swap with nums[5]          [0,0,2,1,1,2]   low=0 mid=0 high=4
//   mid=0 sees 0  swap with itself           [0,0,2,1,1,2]   low=1 mid=1 high=4
//   mid=1 sees 0  swap with itself           [0,0,2,1,1,2]   low=2 mid=2 high=4
//   mid=2 sees 2  swap with nums[4]          [0,0,1,1,2,2]   low=2 mid=2 high=3
//   mid=2 sees 1  mid++                      [0,0,1,1,2,2]   low=2 mid=3 high=3
//   mid=3 sees 1  mid++                      [0,0,1,1,2,2]   low=2 mid=4 high=3
//   mid=4 > high=3 -> stop.   Result [0,0,1,1,2,2].
//
// COMPLEXITY
//   A: Time O(n) - every step either advances mid or moves high down, so the unexamined zone
//      shrinks each time; Space O(1)
//   B: Time O(n) - one pass to count, one to rewrite; Space O(1) - three counters
//
// GOTCHAS
//   - When nums[mid] is 2, mid must NOT advance: the element swapped in from high is unknown
//     and could be a 0 or a 2 as well. After a 0, mid does advance, because the element that
//     comes from low is known to be a 1 (or the element itself).
//   - The loop runs while mid <= high (inclusive): the element at high is still unexamined.
//   - A value other than 0, 1 or 2 would match none of the branches and the loop would never
//     end; the constraints rule that out.
//   - The import java.util.Collections is not used, and myApproach is never called.
//
// STATUS
//   Both methods are correct (checked against Arrays.sort on random arrays of 0, 1 and 2).
//   main() sorts Example 1 with A and prints [0, 0, 1, 1, 2, 2].
//
// RELATED
//   - LC 283 Move Zeroes - a two-way version of the same in-place partition (solved in this
//     folder).
//   - LC 27 Remove Element - two-ended swapping of unwanted elements to the back (solved in
//     this folder).
// ============================================================================
public class LC75SortColors {

    public static void main(String[] args) {
        // Example 1. Expected output: [0, 0, 1, 1, 2, 2]
        int[] nums = {2,0,2,1,1,0};
        myApproach1(nums);
        System.out.println(Arrays.toString(nums));
    }

    // Approach A: Dutch national flag in one pass. 0s are swapped to the front, 2s to the back,
    // 1s are left in the middle. O(n) time, O(1) space
    private static void myApproach1(int[] nums) {

        // startingAddress = low: next slot for a 0
        int startingAddress = 0;

        // midAddress = mid: the element being examined; endingAddress = high: next slot for a 2
        int midAddress = startingAddress;
        int endingAddress = nums.length-1;
        int temp;
        while (midAddress <= endingAddress){
            if(nums[midAddress] == 0){
                // a 0 goes to the front zone
                temp = nums[startingAddress];
                nums[startingAddress] = nums[midAddress];
                nums[midAddress] = temp;
                startingAddress++;
                midAddress++;
            }else if(nums[midAddress] == 1){
                // a 1 is already where it belongs
                midAddress++;
            } else if(nums[midAddress] == 2){
                // a 2 goes to the back zone; the value swapped in is unknown, so mid stays
                temp = nums[endingAddress];
                nums[endingAddress] = nums[midAddress];
                nums[midAddress] = temp;
                endingAddress --;
            }
        }
    }

    // Approach B (not called): counting sort - count each value, then rewrite the array.
    // Two passes, O(n) time, O(1) space
    private static void myApproach(int[] nums) {
        // pass 1: count the 0s, 1s and 2s
        int count0 = 0;
        int count1 = 0;
        int count2 = 0;
        for (int num : nums) {
            if (num == 0) {
                count0++;
            } else if (num == 1) {
                count1++;
            } else {
                count2++;
            }
        }
        // pass 2: write the 0s, then the 1s, then the 2s
        for (int i = 0; i < nums.length; i++) {
            if(count0 > 0) {
                nums[i] = 0;
                count0 --;
            } else if(count1 > 0) {
                nums[i] = 1;
                count1 --;
            }else if(count2 > 0) {
                nums[i] = 2;
                count2 --;
            }
        }
    }
}
