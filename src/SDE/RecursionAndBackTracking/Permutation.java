package SDE.RecursionAndBackTracking;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Permutation {
    public static void main(String[] args) {
        int[] nums = {1,2,3};
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer> res = new ArrayList<>();
        Map<Integer, Boolean> map = new HashMap<>();
        for(int num : nums){
            map.put(num, false);
        }
//        permute(nums, ans, res, map);


        permuteSolution2(nums, ans, 0);


        System.out.println(ans);
    }


    public static void permuteSolution2(int[] nums, List<List<Integer>> ans, int index) {
        if(index == nums.length){
            List<Integer> temp = new ArrayList<>();
            for(int num : nums){
                temp.add(num);
            }
            ans.add(temp);
            return;
        }
        for (int i = index; i < nums.length; i++) {
            swap(i, index, nums);
            permuteSolution2(nums, ans, index + 1);
            swap(i, index, nums);
        }

    }


    public static void swap(int i, int j, int[] nums){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    public static void permute(int[] nums, List<List<Integer>> ans, List<Integer> res, Map<Integer, Boolean> map) {
        if(nums.length == res.size()){
            ans.add(new ArrayList<>(res));
            return;
        }
        for (int num : nums) {
            if (!map.get(num)) {
                res.add(num);
                map.put(num, true);
                permute(nums, ans, res, map);
                res.remove(res.size() - 1);
                map.put(num, false);
            }
        }
        
    }
}
