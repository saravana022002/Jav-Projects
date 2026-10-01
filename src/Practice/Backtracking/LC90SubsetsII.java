package Practice.Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// ============================================================================
// LC 90. Subsets II   [Medium]
// https://leetcode.com/problems/subsets-ii/
// Topics: Array, Backtracking, Bit Manipulation
// ============================================================================
//
// PROBLEM
//   Given an integer array nums that MAY contain duplicates, return every possible subset
//   (the power set). The answer must not contain the same subset twice - [1,2] built from
//   the first 2 and [1,2] built from the second 2 count as one. Any order is fine.
//
// EXAMPLES
//   nums = [1,2,2]  ->  [[],[1],[1,2],[1,2,2],[2],[2,2]]    6 distinct subsets
//   nums = [0]      ->  [[],[0]]
//
// CONSTRAINTS
//   1 <= nums.length <= 10,   -10 <= nums[i] <= 10
//
// INTUITION
//   Plain take / skip recursion (see LC 78) builds [1,2] twice for [1,2,2]: "take the
//   first 2, skip the second" and "skip the first 2, take the second". The fix is to sort
//   so equal values sit side by side, and to treat a run of equal values as a group whose
//   only real choice is HOW MANY copies to use (0, 1, 2, ...). The code enforces that by
//   always using the copies from the left: once you SKIP a value, you skip every equal
//   value that follows it as well. Taking copy k is still allowed after taking copy k-1,
//   so every count is reached by exactly one path and no subset repeats.
//
// APPROACH (as implemented below)
//   1. main() sorts nums with Arrays.sort so equal values are neighbours. subsets2 relies
//      on this and does not sort by itself.
//   2. subsets2(sum, index, nums, ans): ans = the subset being built, index = the element
//      being decided, sum = the list of finished subsets.
//   3. index == nums.length: store a COPY of ans in sum.
//   4. TAKE: add nums[index] to ans, recurse on index + 1, then remove it again.
//   5. SKIP with a jump: while the next value equals nums[index], index++ ; then recurse
//      on index + 1. The whole run of equal values is left out, not just one copy.
//
// DRY RUN   nums = [1,2,2]   (already sorted; ans = picks so far)
//   index 0: take 1 -> ans [1]
//     index 1: take 2 -> [1,2]
//       index 2: take 2 -> [1,2,2]; index 3 == length: save [1,2,2]
//                remove  -> [1,2];   no equal value after it; skip -> save [1,2]
//     back at index 1: remove 2 -> [1]; the next value is also 2, so index jumps to 2;
//                skip -> save [1]
//   back at index 0: remove 1 -> []; the next value 2 differs from 1, no jump; skip:
//     index 1: take 2 -> [2]; take 2 -> [2,2], save [2,2]; remove -> [2]; skip -> save [2]
//     remove 2 -> [], jump over the second 2, skip -> save []
//   sum = [[1,2,2],[1,2],[1],[2,2],[2],[]]   the six subsets of the example, no repeats
//
// COMPLEXITY
//   Time : O(n * 2^n) - at most 2^n leaves (all values distinct) and each leaf copies a
//          list of up to n elements; plus O(n log n) for the sort in main()
//   Space: O(n) - recursion depth and the working list ans (the output is not counted)
//
// GOTCHAS
//   - The input MUST be sorted first. On the unsorted [2,1,2] the equal values are not
//     neighbours, the jump never fires and [2] is produced twice.
//   - The jump comes AFTER the take branch and the backtrack. Doing it before would
//     also skip the take branch for the whole run.
//   - index++ only changes this call's own copy of the parameter (Java passes by value).
//
// RELATED
//   - LC 78 Subsets (in this repo) - the same take / skip recursion when all numbers are
//     distinct, so no duplicate handling is needed.
//   - LC 40 Combination Sum II - the same "sort, then never reuse an equal value that an
//     earlier choice already covered" trick, applied to candidate sums.
// ============================================================================
public class LC90SubsetsII {
    public static void main(String[] args) {
        // Example with a duplicate: the 12 distinct subsets of {1,2,2,3} are printed
        // (2 choices for 1, 3 for the pair of 2s, 2 for 3 = 12)
        int[] nums = {1,2,2,3};
        List<List<Integer>> sum = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        // equal values must be neighbours for the duplicate skipping below to work
        Arrays.sort(nums);
        subsets2(sum, 0, nums, ans);
        System.out.println(sum);
    }

    // Fills sum with every distinct subset that extends the picks in ans using nums[index..].
    static void subsets2(List<List<Integer>> sum, int index, int[] nums, List<Integer> ans){
        // all elements decided: ans is one finished subset, store a copy of it
        if(nums.length == index){
            sum.add(new ArrayList<>(ans));
            return;
        }
        // branch 1: take nums[index]
        ans.add(nums[index]);
        subsets2(sum, index +  1, nums, ans);
        // backtrack: undo the take (remove by position, i.e. the last element)
        ans.remove(ans.size() - 1);
        // branch 2: skip nums[index] AND every equal value right after it, so a later copy
        // can never stand in for this one (that would rebuild a subset already produced)
        while(index + 1 <= nums.length - 1 && nums[index + 1] == nums[index])
            index++;
        subsets2(sum, index +  1, nums, ans);
    }
}
