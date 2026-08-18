package SDE.DailyChallenge;

import java.util.*;

public class SumOfDistance {
    public static void main(String[] args) {
        int[] nums = {1,3,1,1,2};
        distance(nums);
    }

    public static long[] distance(int[] nums) {
        HashMap<Integer, List<Integer>> sumArr =  new HashMap<>();


        for (int i = 0; i < nums.length; i++) {
            sumArr.putIfAbsent(nums[i], new ArrayList<>(Arrays.asList(0, 0)));
            List<Integer> list = sumArr.get(nums[i]);
            list.set(0, list.get(0) + 1);
            list.set(1, list.get(1) + i);
        }

        long[] arr = new long[nums.length];
        for (int i = 0; i < nums.length; i++){
            List<Integer> list = sumArr.get(nums[i]);

            if(list == null || list.size() < 2){
                arr[i] = 0;
            }else {
                arr[i] = Math.abs((i * (list.get(0) - 1)) - (list.get(1) - i));
            }

        }
        return arr;
    }
}
