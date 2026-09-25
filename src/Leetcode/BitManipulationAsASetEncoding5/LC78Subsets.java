package Leetcode.BitManipulationAsASetEncoding5;

import java.util.ArrayList;
import java.util.List;



//78. Subsets
//        Medium
//Topics
//premium lock icon
//        Companies
//Given an integer array nums of unique elements, return all possible subsets (the power set).
//
//The solution set must not contain duplicate subsets. Return the solution in any order.
//
//
//
//Example 1:
//
//Input: nums = [1,2,3]
//Output: [[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
public class LC78Subsets {
    public static void main(String[] args) {
       int[] nums = {1,2,3};
        System.out.println(subsets(nums));
    }

    public static List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;
        List<List<Integer>> ans = new ArrayList<>();
        for (int i = 0; i < (1 << n); i++) {
            ans.add(new ArrayList<>());
            for (int j = 0; j < n; j++) {
                if(((i >> j) & 1) == 1){
                    ans.get(ans.size() - 1).add(nums[j]);
                }
            }
        }
        return ans;
    }
}
