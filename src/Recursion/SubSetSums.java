package Recursion;

import java.util.ArrayList;
import java.util.List;

public class SubSetSums {
    public static void main(String[] args) {
        int[] nums = {2, 3};
        ArrayList<Integer> ans = new ArrayList<>();
        subsetSums1(ans, 0, nums, 0);
        System.out.println(ans);
    }

    static void subsetSums1(ArrayList<Integer> ans, int index, int[] nums, int sum){
        if(nums.length == index){
            ans.add(sum);
            return;
        }
        subsetSums1(ans, index +  1, nums, sum + nums[index]);
        subsetSums1(ans, index +  1, nums, sum);
    }
}
