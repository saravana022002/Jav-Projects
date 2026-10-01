package Practice.Arrays;

// ============================================================================
// LC 2154. Keep Multiplying Found Values by Two   [Easy]
// https://leetcode.com/problems/keep-multiplying-found-values-by-two/
// Topics: Array, Hash Table, Sorting, Simulation
// ============================================================================
//
// PROBLEM
//   You get an array nums and a starting number original. Look for original in nums. If it
//   is there, double it and look for the new value; keep repeating. The first time the
//   current value is missing from nums, stop and return that value.
//
// EXAMPLES
//   nums = [5,3,6,1,12], original = 3  ->  24    3, 6 and 12 are all in nums (3 -> 6 -> 12
//                                                 -> 24); 24 is not, so the chain ends there
//   nums = [2,7,9],      original = 4  ->  4     4 is not in nums, so nothing is doubled
//
// CONSTRAINTS
//   1 <= nums.length <= 1000,   1 <= nums[i], original <= 1000
//
// INTUITION
//   The values searched for form the chain original, 2*original, 4*original, ... and the
//   chain continues only while each member is present in nums. The order of nums does not
//   matter, as long as the WHOLE array is searched again after every doubling. The chain
//   is also short: nums never holds anything above 1000, so after at most 10 doublings the
//   value is too big to be found and the search must stop.
//
// APPROACH (as implemented below)
//   1. Scan nums with index i, from left to right.
//   2. If nums[i] == original: double original and set i = -1. The loop's i++ turns that
//      into 0, so the next scan starts again from the first element.
//   3. When a scan reaches the end without a match, original is the final value; return it.
//
// DRY RUN   nums = [5,3,6,1,12], original = 3
//   scan 1:  5 no, 3 yes  -> original = 6,  restart
//   scan 2:  5 no, 3 no, 6 yes  -> original = 12, restart
//   scan 3:  5, 3, 6, 1 no, 12 yes  -> original = 24, restart
//   scan 4:  none of the five equals 24, the loop ends  -> return 24
//
// COMPLEXITY
//   Time : O(n * d) - at most d + 1 scans of n elements, where d is the number of doublings.
//          Here d <= 10 (original = 1 doubles up to 1024, above every possible element), so
//          it is effectively O(n)
//   Space: O(1) - the parameter original is reused as the running value
//
// GOTCHAS
//   - i = -1 (not 0) is deliberate: the for-loop increments i right after, so the next
//     check is at index 0. Setting i = 0 would skip nums[0] on the new scan.
//   - The restart is what makes the order irrelevant: nums = [12,6,3], original = 3 still
//     gives 24, although 6 and 12 sit BEFORE the 3. Continuing from the current index
//     would miss them.
//   - No overflow: original stops growing once it passes 1000 (at most 2000).
//   - Putting nums into a HashSet first makes each lookup O(1), so the whole thing would be
//     O(n) for any input size; for n <= 1000 the plain rescan is fine.
//
// RELATED
//   - LC 1346 Check If N and Its Double Exist - also asks whether the double of a value is
//     present in the array (a HashSet lookup).
// ============================================================================
public class LC2154KeepMultiplyingFoundValuesByTwo {

    public static void main(String[] args) {
        // Example 1 - prints 24
        int[] nums = {5,3,6,1,12};
        int original = 3;
        System.out.println(findFinalValue(nums, original));
    }
    // Returns the value at which the doubling chain stops (the first one not found in nums).
    public static int findFinalValue(int[] nums, int original) {

        for (int i = 0; i < nums.length; i++) {
            if(nums[i] == original){
                // found: double the target, then rescan the whole array for the new value
                original = original * 2;
                // -1 because the loop's i++ makes the next index 0
                i = -1;
            }
        }
        return original;
    }
}
