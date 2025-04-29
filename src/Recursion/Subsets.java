package Recursion;

import java.util.ArrayList;
import java.util.List;

public class Subsets {
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        List<List<Integer>> sum = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        int index = 0;
        subsets(sum, index, nums, ans);
        System.out.println(ans);
    }

    static void subsets(List<List<Integer>> sum, int index, int[] nums, List<Integer> ans){
        if(nums.length == index){
            sum.add(new ArrayList<>(ans));
            return;
        }
        ans.add(nums[index]);
        subsets(sum, index +  1, nums, ans);
        ans.remove(ans.size() - 1);
        subsets(sum, index +  1, nums, ans);
    }

}
