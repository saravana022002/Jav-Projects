package NeetCode250.ArraysAndHashing;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class TwoSum {
    public static void main(String[] args) {
        int[] nums = {3,2,4};
        int target = 6;
        twoSumBetter(nums, target);
    }
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if(nums[i] + nums[j] == target){
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

    static class ValueArr{
        int value;
        int index;
        ValueArr(int value, int index){
            this.value = value;
            this.index = index;
        }
    }

    public static int[] twoSumOpt(int[] nums, int target) {
        Map<Integer, ValueArr> integerValueArrMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if(integerValueArrMap.containsKey(target - nums[i])){
                int index2 = integerValueArrMap.get(target - nums[i]).index;
                return new int[]{i, index2};
            }
            integerValueArrMap.put(nums[i], new ValueArr(target - nums[i], i));
        }
        return new int[]{-1, -1};
    }

    public static int[] twoSumBetter(int[] nums, int target) {
        int[][] arr = new int[nums.length][2];
        for (int i = 0; i < nums.length; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }
        Arrays.sort(arr, Comparator.comparingInt(a -> a[0]));
        int l = 0;
        int r = nums.length - 1;
        while (l < r){
            if(arr[l][0] + arr[r][0] == target){
                return new int[]{l, r};
            }if(arr[l][0] + arr[r][0] > target){
                r--;
            }else{
                l++;
            }
        }
        return new int[0];
    }

}
