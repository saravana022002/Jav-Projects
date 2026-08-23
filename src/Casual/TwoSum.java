package Casual;

import java.util.Arrays;
import java.util.HashMap;

public class TwoSum {
    public static void main(String[] args) {
        int[] nums = {3,3};
        int target = 6;
        System.out.println(Arrays.toString(twoSum(nums, target)));
    }

    public static int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int index = 0;
        for(int i : nums){
            if(!map.containsKey(i)){
                map.put(target - i, index);
                index++;
            }else{
                return new int[]{map.get(i), index};
            }
        }
        return new int[]{0,0};
    }
}
