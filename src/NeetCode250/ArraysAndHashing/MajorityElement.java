package NeetCode250.ArraysAndHashing;

import java.util.HashMap;
import java.util.Map;

public class MajorityElement {

    public static void main(String[] args) {
        int[] nums = {3,2,3};
        System.out.println(majorityElement(nums));
    }
    public static int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int length = nums.length;
        for (int num : nums) {
            int count = map.getOrDefault(num, 0);
            if (count >= length / 2) {
                return num;
            }
            map.put(num, count + 1);
        }
        return 0;
    }


    public static int majorityElementOptimal(int[] nums) {
        int count = 0;
        int num = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if(count == 0){
               count++;
               num = nums[i];
               continue;
            }
            count = num == nums[i] ? count + 1 : count - 1;
        }
        return num;
    }
}
