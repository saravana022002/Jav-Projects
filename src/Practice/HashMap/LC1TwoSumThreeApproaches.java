package Practice.HashMap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

// ============================================================================
// LC 1. Two Sum   [Easy]
// https://leetcode.com/problems/two-sum/
// Topics: Array, Hash Table
// ============================================================================
//
// PROBLEM
//   nums is an int array and target an int. Exactly one pair of different positions holds
//   two values that add up to target - return those two indices, in any order. The same
//   element may not be used twice. (The follow-up asks for something faster than O(n^2).)
//
// EXAMPLES
//   nums = [2,7,11,15], target = 9  ->  [0,1]    2 + 7 = 9
//   nums = [3,2,4],     target = 6  ->  [1,2]    2 + 4 = 6; the lone 3 cannot pair with itself
//   nums = [3,3],       target = 6  ->  [0,1]    equal values are fine at two positions
//
// CONSTRAINTS
//   2 <= nums.length <= 10^4,  -10^9 <= nums[i] <= 10^9,  -10^9 <= target <= 10^9,
//   only one valid answer exists
//
// INTUITION
//   The partner of a number x is target - x; the three methods differ in how they find it.
//   Brute force tests every pair. A HashMap remembers the numbers already visited, so the
//   partner is one O(1) lookup. Sorting lets two pointers close in from both ends: if the
//   smallest + largest remaining values overshoot target, the largest is too big for ANY
//   partner (the smallest is its best case) and can be dropped; if they undershoot, the
//   smallest is useless and is dropped instead. Sorting scrambles positions, so every
//   value has to carry its original index with it.
//
// APPROACHES IN THIS FILE
//   A. twoSum()       - try every pair (i, j) - O(n^2) time, O(1) space (instance method)
//   B. twoSumOpt()    - one pass, HashMap: value -> ValueArr holding its index
//                       - O(n) time, O(n) space
//   C. twoSumBetter() - sort (value, index) rows, then two pointers - O(n log n) time,
//                       O(n) space   <- main() runs this one (and it has a bug, see STATUS)
//
// APPROACH (as implemented below - twoSumBetter(), method C)
//   1. arr[i] = {nums[i], i}: each row pairs a value with its original index.
//   2. Sort arr by arr[k][0], the value.
//   3. l = 0 (smallest value), r = nums.length - 1 (largest value). While l < r:
//        - arr[l][0] + arr[r][0] == target -> found, return the pair
//        - sum > target -> r--        (the right value is too big for every partner)
//        - sum < target -> l++        (the left value is too small for every partner)
//   4. Pointers met without a hit -> return an empty array (ruled out by the problem).
//   Step 3 returns {l, r}, which are positions in the SORTED rows; the original indices
//   arr[l][1] and arr[r][1] were needed. See STATUS.
//
// DRY RUN   nums = [3,2,4], target = 6   (what main() runs)
//   rows before sorting : {3,0} {2,1} {4,2}
//   rows after sorting  : {2,1} {3,0} {4,2}
//   l=0, r=2: 2 + 4 = 6 == target -> returns {l, r} = [0,2]
//   The rows say those two values sit at original indices arr[0][1] = 1 and arr[2][1] = 2,
//   so the right answer is [1,2].
//
// COMPLEXITY
//   A. Time O(n^2) - n(n-1)/2 pairs;  Space O(1)
//   B. Time O(n) - one pass, O(1) average per HashMap operation;  Space O(n)
//   C. Time O(n log n) - the sort dominates, the two-pointer scan is O(n);  Space O(n) for arr
//
// GOTCHAS
//   - Sorting destroys positions. A sort-based Two Sum must return the stored original
//     indices, never the sorted positions (that is the bug in C).
//   - twoSum() (A) is an instance method: call it as new LC1TwoSumThreeApproaches().twoSum(..).
//     B and C are static.
//   - B returns {current index, earlier index}; the order does not matter for LeetCode.
//   - ValueArr.value (= target - nums[i]) is stored in B but never read; only .index is used.
//
// STATUS
//   Known issue: twoSumBetter() returns the pointer positions {l, r} in the sorted rows
//   instead of the original indices {arr[l][1], arr[r][1]}. For nums = [3,2,4], target = 6
//   it returns [0,2]; the correct answer is [1,2]. It is right only when the pair's sorted
//   positions happen to equal its original indices (e.g. [2,7,11,15] or [3,3]).
//   twoSum() and twoSumOpt() are correct. The code is left unchanged on purpose.
//   main() also does not print the result.
//
// RELATED
//   - LC 167 Two Sum II - Input Array Is Sorted - approach C without the sort step and
//     without the index bookkeeping.
//   - LC 15 3Sum - sort once, then run this two-pointer scan for every fixed first number.
//
// SEE ALSO
//   LC1TwoSumHashMap.java - a clean version of approach B (value -> index)
//   LC1TwoSumComplementMap.java - hash map keyed by the partner (target - x)
//   src/Leetcode/HashMapAsLookup3/LC1TwoSum.java - your curated version
// ============================================================================
public class LC1TwoSumThreeApproaches {
    public static void main(String[] args) {
        // Example 2 - runs approach C and prints nothing. It returns [0, 2], but the correct
        // answer is [1, 2] - see STATUS
        int[] nums = {3,2,4};
        int target = 6;
        twoSumBetter(nums, target);
    }
    // Approach A: brute force - try every pair (i, j) with i < j. O(n^2) time, O(1) space.
    // Instance method (not static). Returns {-1, -1} when no pair adds up to target.
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if(nums[i] + nums[j] == target){
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    // Map value for approach B. Only index is ever read; value (= target - nums[i]) is not.
    static class ValueArr{
        int value;
        int index;
        ValueArr(int value, int index){
            this.value = value;
            this.index = index;
        }
    }

    // Approach B: one pass with a HashMap of every visited value -> its index. O(n) time,
    // O(n) space. Returns {current index, earlier index}; {-1, -1} when no pair exists.
    // Trace for [3,2,4], target 6: 3 -> store; 2 (partner 4) -> store; 4 (partner 2) found.
    public static int[] twoSumOpt(int[] nums, int target) {
        Map<Integer, ValueArr> integerValueArrMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // the partner was visited earlier: its stored index completes the pair
            if(integerValueArrMap.containsKey(target - nums[i])){
                int index2 = integerValueArrMap.get(target - nums[i]).index;
                return new int[]{i, index2};
            }
            // store after the lookup so a number cannot pair with itself
            integerValueArrMap.put(nums[i], new ValueArr(target - nums[i], i));
        }
        return new int[]{-1, -1};
    }

    // Approach C: sort (value, index) rows, then close in with two pointers. O(n log n) time,
    // O(n) space. main() runs this one.
    // KNOWN ISSUE: it returns positions in the SORTED rows, not original indices - see STATUS.
    public static int[] twoSumBetter(int[] nums, int target) {
        // arr[i] = {value, original index}: sorting moves rows around, so each keeps its index
        int[][] arr = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }
        // order the rows by value
        Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));
        // l starts on the smallest value, r on the largest
        int l = 0;
        int r = nums.length - 1;
        while (l < r){
            // sum == target: found. sum > target: the right value is too big for any partner,
            // drop it (r--). sum < target: the left value is too small, drop it (l++).
            if(arr[l][0] + arr[r][0] == target){
                // BUG: l and r are sorted positions; the original indices are arr[l][1], arr[r][1]
                return new int[]{l, r};
            }if(arr[l][0] + arr[r][0] > target){
                r--;
            }else{
                l++;
            }
        }
        return new int[0];
    }

}
