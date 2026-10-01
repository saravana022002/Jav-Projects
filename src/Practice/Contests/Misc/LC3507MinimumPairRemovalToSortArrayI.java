package Practice.Contests.Misc;

// ============================================================================
// LC 3507. Minimum Pair Removal to Sort Array I   [Easy]
// https://leetcode.com/problems/minimum-pair-removal-to-sort-array-i/
// Topics: Array, Hash Table, Linked List, Heap (Priority Queue), Simulation,
//         Doubly-Linked List, Ordered Set
// ============================================================================
//
// PROBLEM
//   One operation has no free choice: take the adjacent pair of nums with the smallest sum
//   (the leftmost one if several tie) and replace the two numbers by their sum, so the array
//   gets one element shorter. Return the number of operations needed before the array is
//   non-decreasing (each element >= the one before it). An already sorted array needs 0.
//
// EXAMPLES
//   nums = [5,2,3,1]  ->  2     (3,1) has the smallest sum 4 -> [5,2,4];
//                               then (2,4) has sum 6 -> [5,6], which is sorted
//   nums = [1,2,2]    ->  0     already sorted
//
// CONSTRAINTS
//   1 <= nums.length <= 50,   -1000 <= nums[i] <= 1000
//
// INTUITION   (how a correct solution works - the code below does NOT do this)
//   Because every step is forced, the answer is found by simulating the statement: while the
//   array is not sorted, find the leftmost adjacent pair with the smallest sum, merge it and
//   count one operation. Each operation removes one element, so there are at most n - 1 of
//   them, and with n <= 50 a plain O(n) rescan per operation is plenty.
//
// APPROACH (as implemented below - a local heuristic, not that simulation)
//   1. counter = 0. One pass over i = 1 .. n - 2; currentSum is the sum of the pair that
//      starts at i, except that when i + 1 is the LAST index it is just nums[i + 1] and
//      the code counts one when nums[i] <= nums[i + 1].
//   2. Otherwise, if nums[i - 1] <= currentSum, write currentSum into nums[i], negate
//      nums[i + 1] and count one.
//   3. Return counter.
//   It never searches for the minimum-sum pair, never checks whether the array is already
//   sorted, and it modifies the input array.
//
// DRY RUN   main(): nums = [1,2,2]
//   n = 3, so the loop runs only for i = 1, and i + 1 = 2 is the last index:
//   currentSum = nums[2] = 2;  nums[1] = 2 <= 2 -> counter = 1, continue.
//   Returns 1 - but the array is already sorted, so the correct answer is 0.
//
// STATUS
//   Known issue: the method does not simulate the process, so its results are wrong: main()'s
//   sorted input {1,2,2} returns 1 (correct 0), [2,3,3,7] returns 2 (correct 0), [2,-7]
//   returns 0 (correct 1). Against a faithful simulation on 20000 random arrays of up to 7
//   elements it was wrong 13539 times. For [5,2,3,1] it returns 2, the right number, only by
//   coincidence. It also overwrites the array it is given ([5,2,3,1] becomes [5,5,-3,1]),
//   and main() does not print the result. The code is left unchanged on purpose.
//   NOTE: identification is a judgment call - LC 3510 Minimum Pair Removal to Sort Array II
//   is the Hard version with the same story. The file is filed as I (Easy); confirm at the link.
//
// COMPLEXITY
//   As written: Time O(n), Space O(1) - but the answer is wrong.
//   A correct simulation: Time O(n^2) (at most n - 1 operations, each an O(n) sortedness
//   check plus an O(n) scan for the smallest pair), Space O(n) for the working copy.
//
// GOTCHAS
//   - The merge rule is fixed (smallest sum, leftmost on ties); the only question is when
//     to stop, namely as soon as the array is non-decreasing.
//   - After a merge the neighbouring sums change, so the smallest pair has to be searched
//     again from the current array every time - a single left-to-right pass cannot do that.
//   - Sums may be negative; the smallest pair can be anywhere in the array.
//
// RELATED
//   - LC 1046 Last Stone Weight - another "repeatedly pick by a fixed rule, merge, count"
//     simulation.
// ============================================================================
public class LC3507MinimumPairRemovalToSortArrayI {
    public static void main(String[] args) {
        // Example 2 (already sorted): the method returns 1 instead of 0 and the result is
        // not printed either - see STATUS
        int[] input = {1,2,2};
        minimumPairRemoval(input);
    }

    // Intended: the number of min-sum merges needed to sort nums. Wrong - see STATUS.
    public static int minimumPairRemoval(int[] nums) {
        int currentSum = 0;
        int counter = 0;

        for (int i = 1; i < nums.length - 1; i++) {
            // the pair (i, i + 1) is the last pair: compare the two elements directly
            if(nums.length - 1 == i + 1) {
                currentSum = nums[i + 1];
                if(nums[i] <= currentSum){
                    counter++;
                    continue;
                }
            }else {
                // sum of the pair starting at i
                currentSum = nums[i] + nums[i + 1];
            }
            // "merge" when the left neighbour does not exceed the sum (mutates the input)
            if(nums[i - 1] <= currentSum){
                nums[i] = currentSum;
                nums[i + 1] = -nums[i + 1];
                counter ++;
            }
        }
        return counter;
    }
}
