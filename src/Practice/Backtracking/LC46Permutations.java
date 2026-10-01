package Practice.Backtracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// ============================================================================
// LC 46. Permutations   [Medium]
// https://leetcode.com/problems/permutations/
// Topics: Array, Backtracking
// ============================================================================
//
// PROBLEM
//   nums holds distinct integers. Return every possible ordering (permutation) of all of
//   them, in any order. n numbers have n! orderings.
//
// EXAMPLES
//   nums = [1,2,3]  ->  [[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
//   nums = [0,1]    ->  [[0,1],[1,0]]
//   nums = [1]      ->  [[1]]
//
// CONSTRAINTS
//   1 <= nums.length <= 6,   -10 <= nums[i] <= 10,   all integers in nums are unique
//
// INTUITION
//   Fill the answer one position at a time: position 0 can hold any of the n numbers,
//   position 1 any of the n-1 that are left, and so on. Trying every choice at every
//   position, and undoing it before trying the next one, visits each ordering exactly
//   once. The swap trick keeps the "numbers still left" inside the array itself:
//   nums[0..index-1] is the fixed prefix and nums[index..] are the unused numbers, so
//   swapping nums[i] into slot index means "put nums[i] at this position".
//
// APPROACHES IN THIS FILE
//   A. permuteSolution2() - in-place swaps, no extra lists - O(n * n!)  <- main() runs this
//   B. permute()          - builds a path res, a HashMap marks used values - O(n * n!)
//                           (its call in main() is commented out)
//
// APPROACH (as implemented below, approach A)
//   1. permuteSolution2(nums, ans, index): positions 0..index-1 are already decided.
//   2. If index == nums.length, copy nums into a new list and add it to ans.
//   3. Otherwise for i = index .. n-1: swap(i, index) puts nums[i] at position index,
//      recurse with index + 1, then swap(i, index) again to restore the array.
//
// DRY RUN   nums = [1,2,3] (main's input)
//   index 0, i=0: [1,2,3] -> index 1: i=1 save [1,2,3];  i=2 swap -> save [1,3,2], undo
//   index 0, i=1: swap 0,1 -> [2,1,3] -> save [2,1,3];  swap 1,2 -> save [2,3,1], undo
//   index 0, i=2: swap 0,2 -> [3,2,1] -> save [3,2,1];  swap 1,2 -> save [3,1,2], undo
//   ans = [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 2, 1], [3, 1, 2]]
//   (the last two are in a different order than LeetCode's, which is allowed)
//
// COMPLEXITY
//   Time : O(n * n!) for both - there are n! permutations and each costs O(n) to copy
//          into ans; the inner nodes of the recursion tree add about the same again
//          (B also scans all n numbers in every call).
//   Space: A: O(n) recursion depth, the array is permuted in place.
//          B: O(n) depth + the path res + the map of n flags. (Output is extra.)
//
// GOTCHAS
//   - The second swap (undo) is essential: every loop step must start from the array
//     as it was, otherwise orderings repeat and others are missed.
//   - At index == n the array must be COPIED; nums keeps changing afterwards.
//   - B marks used numbers by VALUE, which only works because values are distinct. With
//     duplicates (LC 47) use an index-based used[] and skip equal values.
//   - main() still builds res and map, which only the commented-out permute() call uses.
//
// RELATED
//   - LC 47 Permutations II - the same problem when nums may contain duplicates.
//   - LC 31 Next Permutation (in this repo) - steps from one ordering to the next one in
//     lexicographic order, without listing them all.
//   - LC 60 Permutation Sequence (in this repo) - jumps straight to the k-th ordering.
// ============================================================================
public class LC46Permutations {
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        List<List<Integer>> ans = new ArrayList<>();
        // res and map are only needed by approach B (commented out below)
        List<Integer> res = new ArrayList<>();
        Map<Integer, Boolean> map = new HashMap<>();
        for(int num : nums){
            map.put(num, false);
        }
//        permute(nums, ans, res, map);


        // approach A. Expected output (any order is accepted):
        // [[1, 2, 3], [1, 3, 2], [2, 1, 3], [2, 3, 1], [3, 2, 1], [3, 1, 2]]
        permuteSolution2(nums, ans, 0);


        System.out.println(ans);
    }


    // Approach A: adds to ans every ordering of nums[index..] behind the fixed prefix
    // nums[0..index-1]. O(n * n!)
    public static void permuteSolution2(int[] nums, List<List<Integer>> ans, int index) {
        // every position is decided: snapshot the current array
        if(index == nums.length){
            List<Integer> temp = new ArrayList<>();
            for(int num : nums){
                temp.add(num);
            }
            ans.add(temp);
            return;
        }
        // let each unused number nums[i] take position index in turn
        for (int i = index; i < nums.length; i++) {
            swap(i, index, nums);
            permuteSolution2(nums, ans, index + 1);
            // undo, so the next i starts from the same array
            swap(i, index, nums);
        }

    }


    public static void swap(int i, int j, int[] nums){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    // Approach B: grows the path res with numbers not yet used (map[value] == false).
    // O(n * n!)
    public static void permute(int[] nums, List<List<Integer>> ans, List<Integer> res, Map<Integer, Boolean> map) {
        // the path holds all n numbers: one full permutation (store a copy)
        if(nums.length == res.size()){
            ans.add(new ArrayList<>(res));
            return;
        }
        for (int num : nums) {
            if (!map.get(num)) {
                // choose num, recurse, then un-choose it
                res.add(num);
                map.put(num, true);
                permute(nums, ans, res, map);
                res.remove(res.size() - 1);
                map.put(num, false);
            }
        }
        
    }
}
