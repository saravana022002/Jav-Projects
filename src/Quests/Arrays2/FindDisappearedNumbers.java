package Quests.Arrays2;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindDisappearedNumbers {
    public static void main(String[] args) {
        int[] nums = {4,3,2,7,8,2,3,1};
        findDisappearedNumbers(nums);
    }

    public static List<Integer> findDisappearedNumbers(int[] nums) {
        Set<Integer> sets = new HashSet<>();
        List<Integer> ans = new ArrayList<>();
        for (int num : nums){
            sets.add(num);
        }
        for (int i = 1; i <= nums.length; i++) {
            if(!sets.contains(i)){
                ans.add(i);
            }
        }
        return ans;
    }

    public static List<Integer> findDisappearedNumbersBetter(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i < nums.length; i++) {
            int idx = Math.abs(nums[i]) - 1;
            if(nums[idx] > 0){
                nums[idx] = -nums[idx];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if(nums[i] > 0){
                ans.add(i + 1);
            }
        }
        return ans;
    }
}
