package Arryas4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {
        int[] nums = {3,3};
        int target = 6;
        twoSum(nums, target);
    }
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> ansMap = new HashMap<>();
        int[] ansFinal = new int[2];
        int temp;
        for (int i= 0; i < nums.length ; i++) {
            temp = target - nums[i];
            if(ansMap.containsKey(temp)){
                ansFinal[0] = ansMap.get(temp);
                ansFinal[1] = i;
                return ansFinal;
            }
            ansMap.put(nums[i], i);
        }
        return new int[0];
    }
}
