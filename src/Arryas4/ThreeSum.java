package Arryas4;

import java.util.*;

public class ThreeSum {
    public static void main(String[] args) {
        int[] nums = {-1,0,1,2,-1,-4};
        betterSolution(nums);
        
        optimalSolution(nums);
    }

    private static void optimalSolution(int[] nums) {

    }
    //nums[i] + nums[j] + nums[k] = 0

    public static List<List<Integer>> betterSolution(int[] nums) {
        Map<Integer, Integer> ansMap = new HashMap<>();
        List<List<Integer>> ansFinal = new ArrayList<>();
        int temp;
        for (int j = 0; j < nums.length; j++) {
            for (int i = j+1; i < nums.length; i++) {
                temp = - (nums[j] + nums[i]);
                if(ansMap.containsKey(temp) && nums[i] != temp && i != ansMap.get(temp) && nums[i] != nums[j]){
                    int[] ans = new int[3];
                    ans[0] = nums[j];
                    ans[1] = temp;
                    ans[2] = nums[i];
                    Arrays.sort(ans);
                    List<Integer> triplet = Arrays.asList(ans[0], ans[1], ans[2]);
                    if(!ansFinal.contains(triplet)) {
                        ansFinal.add(triplet);
                    }
                }
                ansMap.put(nums[i], i);
            }
            ansMap = new HashMap<>();
        }
        return ansFinal;
    }
}
