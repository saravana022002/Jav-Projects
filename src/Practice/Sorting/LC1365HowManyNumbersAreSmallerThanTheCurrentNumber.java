package Practice.Sorting;

import java.util.Arrays;
import java.util.Comparator;

// ============================================================================
// LC 1365. How Many Numbers Are Smaller Than the Current Number   [Easy]
// https://leetcode.com/problems/how-many-numbers-are-smaller-than-the-current-number/
// Topics: Array, Hash Table, Sorting, Counting Sort
// ============================================================================
//
// PROBLEM
//   For every element nums[i], count how many OTHER elements are strictly smaller than it
//   (count the j != i with nums[j] < nums[i]). Return those counts as an array, in the same
//   order as nums.
//
// EXAMPLES
//   nums = [8,1,2,2,3]  ->  [4,0,1,1,3]    8 has four smaller (1,2,2,3); each 2 has only the 1
//   nums = [6,5,4,8]    ->  [2,1,0,3]
//   nums = [7,7,7,7]    ->  [0,0,0,0]      equal values are not "smaller"
//
// CONSTRAINTS
//   2 <= nums.length <= 500,   0 <= nums[i] <= 100
//
// INTUITION
//   The number of elements smaller than x is the position where x first appears once the
//   array is sorted. Sorting gives that in O(n log n). The values are tiny (0..100), so
//   counting is even simpler: count how often each value occurs, add the counts up so that
//   freq[v] becomes "how many elements are <= v", and then the answer for x is
//   freq[x - 1] ("how many are <= x - 1", i.e. strictly smaller than x).
//
// APPROACHES IN THIS FILE
//   A. smallerNumbersThanCurrent()    - sort rows {value, rank, original index}; a value's rank
//                                       is where its run starts in sorted order, then write
//                                       the ranks back - O(n log n) time, O(n) space
//   B. smallerNumbersThanCurrentOpt() - counting array + prefix sums - O(n) time, O(1) space
//                                       <- main() runs this one
//
// APPROACH (as implemented below - method B)
//   1. freq = new int[101]; for each num, freq[num]++ (values are 0..100).
//   2. Prefix sums: freq[i] += freq[i - 1], so freq[v] = number of elements <= v.
//   3. For each i: nums[i] = (nums[i] == 0) ? 0 : freq[nums[i] - 1]. Nothing is smaller
//      than 0, and the lookup uses freq[nums[i] - 1], i.e. strictly smaller elements.
//   4. Return nums (the answers replace the input values).
//
// DRY RUN   nums = [8,1,2,2,3]   (what main() runs)
//   counts          : freq[1]=1  freq[2]=2  freq[3]=1  freq[8]=1  (rest 0)
//   after prefix sum: freq[0]=0 freq[1]=1 freq[2]=3 freq[3]=4 ... freq[7]=4 freq[8]=5
//   nums[0]=8 -> freq[7] = 4      nums[1]=1 -> freq[0] = 0      nums[2]=2 -> freq[1] = 1
//   nums[3]=2 -> freq[1] = 1      nums[4]=3 -> freq[2] = 3
//   nums is now [4,0,1,1,3]
//
// COMPLEXITY
//   A. Time O(n log n) - the sort; Space O(n) - n rows of 3 ints
//   B. Time O(n + V) with V = 101 possible values, i.e. O(n); Space O(V) = O(1) - the
//      101-slot array (besides the output, which reuses nums)
//
// GOTCHAS
//   - B relies on the constraint 0 <= nums[i] <= 100: a value above 100 would be out of
//     bounds in freq.
//   - Both methods overwrite the caller's nums and return that same array.
//   - "Strictly smaller": equal values must not count each other. B gets this by looking at
//     freq[x - 1]; A gives equal values the rank of the first row of their run.
//   - nums[i] == 0 needs its own case, otherwise freq[-1] would be read.
//
// STATUS
//   Both methods are correct (checked against a brute force). main() calls only method B
//   and does not print the result (nums would become [4, 0, 1, 1, 3]).
//
// RELATED
//   - LC 315 Count of Smaller Numbers After Self - the same question, but only the elements
//     to the RIGHT of each position are counted.
// ============================================================================
public class LC1365HowManyNumbersAreSmallerThanTheCurrentNumber {
    public static void main(String[] args) {
        // Example 1 - nothing is printed; nums itself ends up as [4, 0, 1, 1, 3]
        int[] nums = {8, 1, 2, 2, 3};
        smallerNumbersThanCurrentOpt(nums);
    }

    // Approach A: sort rows {value, rank, original index}. O(n log n) time, O(n) space.
    // Overwrites nums with the answers and returns it. Not called by main().
    public static int[] smallerNumbersThanCurrent(int[] nums) {
        // row = {value, rank (filled in below), original index}
        int[][] tempAns = new int[nums.length][3];
        for (int i = 0; i < nums.length; i++) {
            tempAns[i][0] = nums[i];
            tempAns[i][1] = 0;
            tempAns[i][2] = i;
        }
        // order the rows by value
        Arrays.sort(tempAns, Comparator.comparingInt(a -> a[0]));
        // count = position in sorted order = number of elements before the current row
        int count = 1;
        int prev = tempAns[0][0];
        tempAns[0][1] = 0;
        for (int i = 1; i < tempAns.length; i++) {
            if (prev != tempAns[i][0]) {
                // new value: everything before it in sorted order is smaller
                tempAns[i][1] = count;
            } else {
                // same value as the row before: same number of smaller elements
                tempAns[i][1] = tempAns[i - 1][1];
            }
            prev = tempAns[i][0];
            count++;
        }
        // put each rank back at the element's original position
        for (int i = 0; i < tempAns.length; i++) {
            nums[tempAns[i][2]] = tempAns[i][1];
        }
        return nums;
    }

    // Approach B: counting array + prefix sums. O(n) time, O(1) space (101 slots).
    // Overwrites nums with the answers and returns it. main() runs this one.
    public static int[] smallerNumbersThanCurrentOpt(int[] nums) {
        // freq[v] = how many times v occurs (values are 0..100)
        int[] freq = new int[101];
        for (int num : nums) {
            freq[num]++;
        }

        // prefix sums: freq[v] = how many elements are <= v
        for (int i = 1; i < freq.length; i++) {
            freq[i] += freq[i - 1];
        }


        // smaller than x = at most x - 1; nothing is smaller than 0
        for (int i = 0; i < nums.length; i++) {
            nums[i] = nums[i] == 0 ? 0 : freq[nums[i] - 1];
        }
        return nums;
    }

}