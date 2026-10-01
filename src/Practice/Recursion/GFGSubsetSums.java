package Practice.Recursion;

import java.util.ArrayList;
import java.util.List;

// ============================================================================
// GFG - Subset Sums   [Medium]
// https://www.geeksforgeeks.org/problems/subset-sums2234/1
// Topics: Recursion, Take / Skip Decisions, Power Set, Backtracking
// ============================================================================
//
// PROBLEM
//   You get an array of integers. For every subset of it - the empty subset included, whose
//   sum is 0 - compute the sum of its elements, and return all these sums in any order.
//   An array of n elements has 2^n subsets, so the answer has 2^n numbers. Subsets that
//   happen to have the same total are listed separately.
//
// EXAMPLES
//   arr = [2, 3]     ->  [0, 2, 3, 5]                  {}, {2}, {3}, {2,3}
//   arr = [1, 2, 1]  ->  [0, 1, 1, 2, 2, 3, 3, 4]      two different subsets give 1 (each
//                                                      of the 1s), and two give 3
//   arr = [5, 6, 7]  ->  [0, 5, 6, 7, 11, 12, 13, 18]
//
// INTUITION
//   Building a subset means one yes / no decision per element: TAKE it or SKIP it. Decide
//   the elements one after another and the choices form a binary tree with 2^n leaves, one
//   leaf per subset. If the running total of the elements taken so far travels down the
//   recursion as a parameter, then at a leaf (every element decided) it is exactly the
//   sum of that subset - just record it.
//
// APPROACH (as implemented below)
//   subsetSums1(ans, index, nums, sum): index = the element being decided, sum = total of
//   the elements taken so far, ans = the output list. The caller (main) starts with an
//   empty list, index 0 and sum 0.
//   1. index == nums.length -> every element is decided: ans.add(sum) and return.
//   2. TAKE nums[index]: subsetSums1(ans, index + 1, nums, sum + nums[index]).
//   3. SKIP nums[index]: subsetSums1(ans, index + 1, nums, sum).
//
// DRY RUN   nums = [2, 3]   (what main() runs; take is tried before skip)
//   take 2, take 3  -> sum 5   ans [5]
//   take 2, skip 3  -> sum 2   ans [5,2]
//   skip 2, take 3  -> sum 3   ans [5,2,3]
//   skip 2, skip 3  -> sum 0   ans [5,2,3,0]
//   main() prints [5, 2, 3, 0]: the same four sums as the example [0, 2, 3, 5], in
//   take-first order instead of sorted order.
//
// STATUS
//   There is no entry method that takes just the array and returns the list: the recursive
//   helper subsetSums1 takes the output list, the start index and the start sum
//   explicitly, and main() prepares and prints the list itself. The import java.util.List
//   is not used.
//
// COMPLEXITY
//   Time : O(2^n) - the recursion tree has 2^(n+1) - 1 calls and produces 2^n sums
//   Space: O(n) for the recursion depth, plus the 2^n numbers of the output list
//
// GOTCHAS
//   - sum is an int passed BY VALUE, so the SKIP branch needs no clean-up after the TAKE
//     branch: there is no "undo" step, unlike the list-based subset recursions (LC 78).
//   - The result is not sorted (take-first order). The statement accepts any order; call
//     Collections.sort(ans) afterwards if ascending order is wanted.
//   - Equal sums from different subsets are kept as separate entries ([1,2,1] -> two 1s and
//     two 3s).
//   - An empty array gives [0] (the base case fires at once).
//
// RELATED
//   - LC 78 Subsets - the same take / skip recursion, collecting the subsets themselves
//     (LC78Subsets.java in src/Practice/Backtracking).
//   - LC 1863 Sum of All Subset XOR Totals - also adds up one value per subset of the array.
//
// SOURCE
//   Source: GeeksforGeeks (found via Striver SDE Sheet).
// ============================================================================
public class GFGSubsetSums {
    public static void main(String[] args) {
        // GFG example 1 {2, 3}. Prints [5, 2, 3, 0] - all four sums, in take-first order
        int[] nums = {2, 3};
        ArrayList<Integer> ans = new ArrayList<>();
        subsetSums1(ans, 0, nums, 0);
        System.out.println(ans);
    }

    // Adds sum + s to ans for every subset sum s of nums[index..] (sum = total taken so far).
    static void subsetSums1(ArrayList<Integer> ans, int index, int[] nums, int sum){
        // all elements decided: sum is the total of one complete subset
        if(nums.length == index){
            ans.add(sum);
            return;
        }
        // take nums[index]
        subsetSums1(ans, index +  1, nums, sum + nums[index]);
        // skip nums[index]
        subsetSums1(ans, index +  1, nums, sum);
    }
}
