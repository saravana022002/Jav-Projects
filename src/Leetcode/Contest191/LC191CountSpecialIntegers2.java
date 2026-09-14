package Leetcode.Contest191;

import java.util.HashMap;
import java.util.Map;

public class LC191CountSpecialIntegers2 {
    public static void main(String[] args) {
        int[] nums = {1,8,1,5,1,5,8,5};
        System.out.println(countSpecialIntegers(nums));
    }

    public static int countSpecialIntegers(int[] nums) {
        Map<Integer, int[]> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.putIfAbsent(nums[i], new int[3]);
            if(map.get(nums[i])[0] == -1){
                continue;
            }
            map.get(nums[i])[0]++;
            if(map.get(nums[i])[0] >= 3 && map.get(nums[i])[1] != i - map.get(nums[i])[2]) {
                map.get(nums[i])[0] = -1;
                continue;
            }
            map.get(nums[i])[1] =  i - map.get(nums[i])[2];
            map.get(nums[i])[2] =  i;
        }

        int count = 0;

        for (Map.Entry<Integer, int[]> entry : map.entrySet()){
            if(entry.getValue()[0] >= 3){
                count++;
            }
        }
        return count;
    }
}
