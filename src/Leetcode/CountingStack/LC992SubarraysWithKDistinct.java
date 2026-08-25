package Leetcode.CountingStack;

import java.util.HashMap;
import java.util.Map;

public class LC992SubarraysWithKDistinct {

    public static void main(String[] args) {
        int[] nums = {1,2,1,3,4};
        int k = 3;
        System.out.println(subarraysWithKDistinctOPT(nums, k));
    }

    public static int subarraysWithKDistinct(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        int ans = 0;
        for (int l = 0; l < nums.length; l++) {
            map = new HashMap<>();
            for (int r = l; r < nums.length; r++) {
                map.put(nums[r] , map.getOrDefault(nums[r], 0) + 1);
                if(map.size() < k){
                    continue;
                }else if(map.size() == k){
                    ans++;
                }else {
                    break;
                }
            }
        }
        return ans;
    }



    public static int subarraysWithKDistinctOPT(int[] nums, int k) {
        return subarraysWithAtmostK(nums, k) - subarraysWithAtmostK(nums, k - 1);
    }

    public static int subarraysWithAtmostK(int[] nums, int k){
        if(k <= 0){
            return 0;
        }
        int[] size = new int[nums.length + 1];
        int distinct = 0;
        int l = 0;
        int target = 0;
        for (int r = 0; r < nums.length; r++) {
            int temp = nums[r];
            if(size[temp] == 0){
                distinct++;
            }
            size[temp]++;
            while (distinct > k){
                int leftNum = nums[l];
                size[leftNum]--;
                if(size[leftNum] == 0){
                    distinct--;
                }
                l++;
            }
            target += r - l + 1;
        }
        return target;
    }

}
