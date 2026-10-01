package Practice.Backtracking;

import java.util.ArrayList;
import java.util.List;

// ============================================================================
// LC 78. Subsets   [Medium]
// https://leetcode.com/problems/subsets/
// Topics: Array, Backtracking, Bit Manipulation
// ============================================================================
//
// PROBLEM
//   Given an array nums of distinct integers, return every possible subset (the power
//   set): the empty subset, each single element, each pair, ... up to nums itself.
//   No subset may appear twice, and the subsets may come back in any order.
//
// EXAMPLES
//   nums = [1,2,3]  ->  [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]   8 = 2^3 subsets
//   nums = [0]      ->  [[],[0]]                                      without 0 / with 0
//
// CONSTRAINTS
//   1 <= nums.length <= 10,   -10 <= nums[i] <= 10,   all numbers in nums are unique
//
// INTUITION
//   A subset is just one yes / no decision per element, so n elements give 2^n subsets.
//   Walk through the array index by index and, at each index, try both choices: TAKE
//   nums[index] or SKIP it. When every index has been decided, the picks made so far are
//   one finished subset. Each of the 2^n decision sequences builds a different subset and
//   the elements are unique, so nothing has to be filtered out as a duplicate.
//
// APPROACH (as implemented below)
//   1. subsets(sum, index, nums, ans): ans = the subset being built, index = the element
//      being decided, sum = the list of finished subsets.
//   2. index == nums.length: every element is decided - store a COPY of ans in sum.
//   3. TAKE: add nums[index] to ans and recurse on index + 1.
//   4. Backtrack: remove that last element from ans again.
//   5. SKIP: recurse on index + 1 with ans unchanged.
//
// DRY RUN   nums = [1,2,3]   (the leaves of the take / skip tree, in the order they are saved)
//   take 1, take 2, take 3  -> save [1,2,3]
//   take 1, take 2, skip 3  -> save [1,2]      (step 4 removed the 3 first)
//   take 1, skip 2, take 3  -> save [1,3]
//   take 1, skip 2, skip 3  -> save [1]
//   skip 1, take 2, take 3  -> save [2,3]
//   skip 1, take 2, skip 3  -> save [2]
//   skip 1, skip 2, take 3  -> save [3]
//   skip 1, skip 2, skip 3  -> save []
//   sum = [[1,2,3],[1,2],[1,3],[1],[2,3],[2],[3],[]]   the same 8 subsets as the example
//
// STATUS
//   Known issue: main() prints ans, the working list, which is empty again once the
//   recursion has backtracked - so running the file prints [] instead of the subsets.
//   The subsets are in sum (correct, in the order of the dry run); println(sum) would
//   show them. The code is left unchanged on purpose.
//
// COMPLEXITY
//   Time : O(n * 2^n) - the recursion tree has 2^n leaves and each leaf copies a list of
//          up to n elements; the other nodes do O(1) work
//   Space: O(n) - recursion depth n + 1 and the working list ans (the output itself,
//          2^n lists, is not counted)
//
// GOTCHAS
//   - sum.add(new ArrayList<>(ans)) must copy: ans keeps changing, so adding ans itself
//     would leave 2^n references to the same list, which ends up empty.
//   - ans.remove(ans.size() - 1) passes an int, so it removes by POSITION (the last
//     element), not by value - exactly what backtracking needs for a List<Integer>.
//
// SEE ALSO
//   src/Leetcode/BitManipulationAsASetEncoding5/LC78Subsets.java - your curated version:
//   counts i from 0 to 2^n - 1 and puts nums[j] into the subset when bit j of i is 1.
//
// RELATED
//   - LC 90 Subsets II (in this repo) - the same take / skip recursion, but nums may
//     hold duplicates, so equal subsets must be avoided.
//   - LC 39 Combination Sum (in this repo) - another pick / skip backtracking search.
// ============================================================================
public class LC78Subsets {
    public static void main(String[] args) {
        // Example 1 - the demo prints the wrong list: ans is [] again after backtracking,
        // so this prints [] (the 8 subsets are in sum) - see STATUS
        int[] nums = {1,2,3};
        List<List<Integer>> sum = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        int index = 0;
        subsets(sum, index, nums, ans);
        System.out.println(ans);
    }

    // Fills sum with every subset that extends the picks in ans using nums[index..].
    static void subsets(List<List<Integer>> sum, int index, int[] nums, List<Integer> ans){
        // all elements decided: ans is one finished subset, store a copy of it
        if(nums.length == index){
            sum.add(new ArrayList<>(ans));
            return;
        }
        // branch 1: take nums[index]
        ans.add(nums[index]);
        subsets(sum, index +  1, nums, ans);
        // backtrack: undo the take (remove by position, i.e. the last element)
        ans.remove(ans.size() - 1);
        // branch 2: skip nums[index]
        subsets(sum, index +  1, nums, ans);
    }

}
