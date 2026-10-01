package Practice.HashMap;

import java.util.Arrays;
import java.util.HashMap;

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
//   For a number x the partner we need is target - x. Re-scanning the array for it costs O(n)
//   per number, while a HashMap answers "have I seen it?" in O(1). This version files each
//   number under the partner it is WAITING for (key = target - x, value = x's index). When
//   a later number equals a stored key, it is exactly the partner of the number that stored
//   that key, so the map hands back the earlier index immediately.
//
// APPROACH (as implemented below)
//   1. map: key = the partner value a stored number still needs (target - its value),
//      value = that number's index. index counts the current position.
//   2. For each value i in nums (i is the VALUE, not a position), left to right:
//        - i is already a key -> return {map.get(i), index}: the earlier index + this one.
//        - otherwise map.put(target - i, index), then index++.
//   3. The final return {0,0} is only reached when no pair exists, which the problem rules out.
//
// DRY RUN   nums = [3,3], target = 6   (what main() runs)
//   i=3, index=0: 3 is not a key                    -> map.put(6 - 3, 0) => {3=0}, index = 1
//   i=3, index=1: 3 IS a key (filed by the first 3) -> return {map.get(3), 1} = [0,1]
//   Another trace, nums = [3,2,4]: 3 files key 3, 2 files key 4, then the value 4 finds
//   key 4 (index 1) -> returns [1,2].
//
// COMPLEXITY
//   Time : O(n) - one pass, and each HashMap lookup/insert is O(1) on average
//   Space: O(n) - the map can end up holding one entry per number
//
// GOTCHAS
//   - The lookup happens BEFORE the current number is stored, so a number can never pair
//     with itself: [3,3] -> [0,1] works, while a single 3 with target 6 finds nothing.
//   - index only advances on a miss. That is safe because a hit returns immediately, so
//     index always equals the current position.
//   - target - i can reach -2*10^9 or +2*10^9 at the extremes, which still fits in an int.
//
// RELATED
//   - LC 167 Two Sum II - Input Array Is Sorted - same task on sorted input, where two
//     pointers replace the map (O(1) extra space).
//   - LC 560 Subarray Sum Equals K - same "remember what you need in a map" trick, applied
//     to running prefix sums (solved in Practice/PrefixSum).
//
// SEE ALSO
//   LC1TwoSumHashMap.java - same one-pass idea, but stores each value and looks up target - x
//   LC1TwoSumThreeApproaches.java - brute force, hash map and sort + two pointers
//   src/Leetcode/HashMapAsLookup3/LC1TwoSum.java - your curated version
// ============================================================================
public class LC1TwoSumComplementMap {
    public static void main(String[] args) {
        // Example 3 - two equal values at two positions. Expected output: [0, 1]
        int[] nums = {3,3};
        int target = 6;
        System.out.println(Arrays.toString(twoSum(nums, target)));
    }

    // One pass with a HashMap keyed by the partner each stored number is waiting for.
    // Returns {earlier index, current index}.
    public static int[] twoSum(int[] nums, int target) {
        // key: the value a stored number still needs (target - its value); value: its index
        HashMap<Integer, Integer> map = new HashMap<>();
        // current position - advances only on a miss, because a hit returns immediately
        int index = 0;
        // i is a VALUE taken from nums, not a position
        for(int i : nums){
            if(!map.containsKey(i)){
                // nobody was waiting for i: file the partner this number needs
                map.put(target - i, index);
                index++;
            }else{
                // i is the partner an earlier number was waiting for, so the pair sums to target
                return new int[]{map.get(i), index};
            }
        }
        // not reached: the problem guarantees exactly one valid pair
        return new int[]{0,0};
    }
}
