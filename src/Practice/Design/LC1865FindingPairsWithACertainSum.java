package Practice.Design;

import java.util.*;

// ============================================================================
// LC 1865. Finding Pairs With a Certain Sum   [Medium]
// https://leetcode.com/problems/finding-pairs-with-a-certain-sum/
// Topics: Array, Hash Table, Design
// ============================================================================
//
// PROBLEM
//   You hold two int arrays, nums1 and nums2, and must support two operations:
//     - add(index, val)  do nums2[index] += val   (val is positive)
//     - count(tot)       return how many index pairs (i, j) have nums1[i] + nums2[j] == tot
//   Only nums2 ever changes. Implement FindSumPairs(nums1, nums2), add and count.
//
// EXAMPLES
//   nums1 = [1,1,2,2,2,3],  nums2 = [1,4,5,2,5,4]
//   count(7)  -> 8      each of the three 2s meets two 5s (6 pairs), the 3 meets two 4s (2)
//   add(3,2)            nums2 = [1,4,5,4,5,4]
//   count(8)  -> 2      3 + 5, with either 5
//   count(4)  -> 1      3 + 1
//   add(0,1), add(1,1)  nums2 = [2,5,5,4,5,4]
//   count(7)  -> 11     three 2s x three 5s = 9, plus the 3 x two 4s = 2
//   Output: [null, 8, null, 2, 1, null, null, 11]
//
// CONSTRAINTS
//   1 <= nums1.length <= 1000,     1 <= nums2.length <= 10^5
//   1 <= nums1[i] <= 10^9,         1 <= nums2[i] <= 10^5
//   0 <= index < nums2.length,     1 <= val <= 10^5,     1 <= tot <= 10^9
//   At most 1000 calls are made to add and count each.
//
// INTUITION
//   nums1 is short (<= 1000) but nums2 can be long (10^5), so count must not scan nums2.
//   For a fixed nums1[i] the partner must be exactly tot - nums1[i], and the number of
//   such j is simply how often that value occurs in nums2. So keep a frequency map of
//   nums2: count becomes one map lookup per element of the short array nums1.
//   add changes a single element of nums2, so only two map entries move: the old value
//   loses one occurrence and the new value gains one.
//
// APPROACH (as implemented below)
//   1. Constructor: keep both arrays and build freqMap = value -> occurrences in nums2.
//   2. add(index, val): decrement the old nums2[index] in freqMap (remove the key at 0),
//      apply nums2[index] += val, increment the new value.
//   3. count(tot): add up freqMap.getOrDefault(tot - num, 0) for every num in nums1.
//
// DRY RUN   the LC example (what main() replays)
//   constructor  freqMap = {1:1, 2:1, 4:2, 5:2}
//   count(7)     1 needs 6: 0 (x2),  2 needs 5: 2 (x3 = 6),  3 needs 4: 2      -> 8
//   add(3,2)     2 -> 4:  {1:1, 4:3, 5:2}   (key 2 dropped to 0 and is removed)
//   count(8)     1 needs 7: 0,  2 needs 6: 0,  3 needs 5: 2                     -> 2
//   count(4)     1 needs 3: 0,  2 needs 2: 0,  3 needs 1: 1                     -> 1
//   add(0,1)     1 -> 2:  {2:1, 4:3, 5:2}
//   add(1,1)     4 -> 5:  {2:1, 4:2, 5:3}
//   count(7)     1 needs 6: 0,  2 needs 5: 3 (x3 = 9),  3 needs 4: 2            -> 11
//
// STATUS
//   main() replays the LC example but throws away what count() returns, so it prints
//   nothing (the four counts are 8, 2, 1, 11 - checked).
//   LeetCode names the class FindSumPairs: rename the class and constructor to submit.
//
// COMPLEXITY
//   Time : constructor O(n2) to build the map;  add O(1) average (a few HashMap calls);
//          count O(n1) - one lookup per element of nums1
//   Space: O(n2) for freqMap (the arrays themselves are only referenced, not copied)
//
// GOTCHAS
//   - Loop over nums1 in count, never over nums2 (the official hint): 1000 lookups per
//     call instead of up to 10^5.
//   - add has to update the map for BOTH values; skipping the decrement would keep
//     counting pairs with a value that no longer exists.
//   - The constructor stores the caller's nums2 array itself, so add changes the caller's
//     array too.
//
// RELATED
//   - LC 1 Two Sum - the same "look up target - x in a hash map" idea.
//   - LC 2006 Count Number of Pairs With Absolute Difference K - counts partner values
//     with a frequency map as well.
// ============================================================================
public class LC1865FindingPairsWithACertainSum {
    public static void main(String[] args) {
        // Replays the LC example: operations[] in order, their arguments in values[].
        // The count() results are not printed (they would be 8, 2, 1, 11), so the output
        // is empty.
        String[] operations = {
                "FindSumPairs", "count", "add", "count", "count", "add", "add", "count"
        };

        int[][] values = {
                {1, 1, 2, 2, 2, 3},
                {1, 4, 5, 2, 5, 4},
                {7},
                {3, 2},
                {8},
                {4},
                {0, 1},
                {1, 1},
                {7}
        };

        LC1865FindingPairsWithACertainSum obj = null;
        // next unused row of values[]; the constructor takes two rows (nums1 and nums2)
        int valueIndex = 0;

        for (String op : operations) {
            switch (op) {
                case "FindSumPairs":
                    int[] nums1 = values[valueIndex++];
                    int[] nums2 = values[valueIndex++];
                    obj = new LC1865FindingPairsWithACertainSum(nums1, nums2);
                    break;
                case "add":
                    int index = values[valueIndex][0];
                    int val = values[valueIndex][1];
                    valueIndex++;
                    obj.add(index, val);
                    break;
                case "count":
                    int tot = values[valueIndex++][0];
                    obj.count(tot);
                    break;
            }
        }
    }


    // freqMap: value -> how many times it occurs in nums2 (kept in sync by add)
    private int[] nums1;
    private int[] nums2;
    private Map<Integer, Integer> freqMap;
    public LC1865FindingPairsWithACertainSum(int[] nums1, int[] nums2) {
        this.nums1 = nums1;
        this.nums2 = nums2;
        freqMap = new HashMap<>();
        for (int num : nums2) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);
        }
    }

    // nums2[index] += val, moving one occurrence from the old value to the new one.
    public void add(int index, int val) {
        // the old value loses one occurrence (drop the key when none is left)
        int oldValue = nums2[index];
        freqMap.put(oldValue, freqMap.get(oldValue) - 1);
        if (freqMap.get(oldValue) == 0) freqMap.remove(oldValue);
        // the new value gains one
        nums2[index] += val;
        freqMap.put(nums2[index], freqMap.getOrDefault(nums2[index], 0) + 1);
    }

    // Returns the number of pairs: for each nums1 value, how many nums2 entries equal
    // tot - num. Loops over the short array only.
    public int count(int tot) {
        int count = 0;
        for (int num : nums1){
            count += freqMap.getOrDefault(tot - num, 0);
        }
        return count;
    }

}
