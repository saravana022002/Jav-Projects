package Casual;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DistributeElements {
    public static void main(String[] args) {
        int[] nums = {2,1,3};
        resultArray(nums);
    }

    public static int[] resultArray(int[] nums) {
        List<Integer> arr1 = new ArrayList<>();
        List<Integer> arr2 = new ArrayList<>();
        int i = 2;
        arr1.add(nums[0]);
        arr2.add(nums[1]);
        while(i < nums.length){
            if(arr1.get(arr1.size() - 1) > arr2.get(arr2.size() - 1)){
                arr1.add(nums[i]);
            }else {
                arr2.add(nums[i]);
            }
            i++;
        }
        arr1.addAll(arr2);
        return arr1.stream().mapToInt(Integer::intValue).toArray();
    }
}
