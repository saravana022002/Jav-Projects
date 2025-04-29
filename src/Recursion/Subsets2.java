package Recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subsets2 {
    public static void main(String[] args) {
        int[] nums = {1,2,2,3};
        List<List<Integer>> sum = new ArrayList<>();
        List<Integer> ans = new ArrayList<>();
        Arrays.sort(nums);
        subsets2(sum, 0, nums, ans);
        System.out.println(sum);
    }

    static void subsets2(List<List<Integer>> sum, int index, int[] nums, List<Integer> ans){
        if(nums.length == index){
            sum.add(new ArrayList<>(ans));
            return;
        }
        ans.add(nums[index]);
        subsets2(sum, index +  1, nums, ans);
        ans.remove(ans.size() - 1);
        while(index + 1 <= nums.length - 1 && nums[index + 1] == nums[index])
            index++;
        subsets2(sum, index +  1, nums, ans);
    }
}
