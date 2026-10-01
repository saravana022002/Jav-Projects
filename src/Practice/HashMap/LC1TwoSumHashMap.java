package Practice.HashMap;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
//   Walk left to right and remember every number already visited (value -> index). For the
//   current number x the partner is target - x: if the map already holds it, that partner
//   came earlier and the pair is found; if not, store x and move on. Every pair is found
//   when its LATER member is reached, so one pass is enough, and looking up before storing
//   keeps a number from pairing with itself.
//
// APPROACH (as implemented below)
//   1. ansMap: value -> index of each number visited so far.
//   2. For each position i, temp = target - nums[i] is the partner we need.
//   3. ansMap.containsKey(temp) -> ansFinal = {ansMap.get(temp), i}; return it.
//   4. Otherwise ansMap.put(nums[i], i) and continue.
//   5. Falling out of the loop means no pair: return an empty array (ruled out by the problem).
//
// DRY RUN   nums = [3,3], target = 6   (what main() runs)
//   i=0: temp = 3, map is empty -> no hit;  put(3, 0)           => {3=0}
//   i=1: temp = 3, key 3 present            -> ansFinal = {0, 1}, return [0,1]
//
// COMPLEXITY
//   Time : O(n) - one pass, and each HashMap lookup/insert is O(1) on average
//   Space: O(n) - the map can end up holding one entry per number
//
// GOTCHAS
//   - Look up first, store second. Storing first would let nums[i] match itself, e.g.
//     a single 3 with target 6.
//   - target - nums[i] can reach -2*10^9 or +2*10^9 at the extremes, which still fits in an int.
//   - The answer comes back as {earlier index, current index}; any order is accepted.
//
// STATUS
//   Correct. main() calls twoSum() but does not print the result (it would be [0, 1]).
//   The imports java.util.ArrayList and java.util.List are unused.
//
// RELATED
//   - LC 167 Two Sum II - Input Array Is Sorted - same task on sorted input, where two
//     pointers replace the map (O(1) extra space).
//   - LC 15 3Sum - fix one number, then run a two-sum search for the other two.
//
// SEE ALSO
//   LC1TwoSumComplementMap.java - same one-pass idea, but keyed by the partner (target - x)
//   LC1TwoSumThreeApproaches.java - brute force, hash map and sort + two pointers
//   src/Leetcode/HashMapAsLookup3/LC1TwoSum.java - your curated version
// ============================================================================
public class LC1TwoSumHashMap {
    public static void main(String[] args) {
        // Example 3 - nothing is printed; twoSum returns [0, 1] for this input
        int[] nums = {3,3};
        int target = 6;
        twoSum(nums, target);
    }
    // One pass: look up the partner (target - nums[i]) among the numbers visited so far,
    // then store nums[i]. Returns {earlier index, current index}.
    public static int[] twoSum(int[] nums, int target) {
        // value -> index of every number visited so far
        Map<Integer, Integer> ansMap = new HashMap<>();
        int[] ansFinal = new int[2];
        int temp;
        for (int i= 0; i < nums.length ; i++) {
            // the value that would complete the sum together with nums[i]
            temp = target - nums[i];
            if(ansMap.containsKey(temp)){
                // the partner was visited earlier: answer = its index and the current one
                ansFinal[0] = ansMap.get(temp);
                ansFinal[1] = i;
                return ansFinal;
            }
            // stored only AFTER the lookup, so a number cannot pair with itself
            ansMap.put(nums[i], i);
        }
        // not reached: the problem guarantees a pair (an empty array would mean "none")
        return new int[0];
    }
}
