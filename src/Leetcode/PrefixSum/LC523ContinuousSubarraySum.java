package Leetcode.PrefixSum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class LC523ContinuousSubarraySum {
    public static void main(String[] args) {
        int[] nums = {23,2,4,6,7};
        int k = 6;
        System.out.println(checkSubarraySum(nums, k));
    }

    // p[r] - p[l] = x , // where t is multiple of k , //x = n * k , n can be 0 also, r - l >= 2
    public static boolean checkSubarraySum(int[] nums, int k) {
        double[] ans = new double[k];
        Arrays.fill(ans, -99);
        ans[0] =  -1;
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            count += nums[i];
            int remainder = count % k;
            if(ans[remainder] > -2){
                double l = ans[remainder];
                if(i - l >= 2){
                    return true;
                }
            }else {
                ans[remainder] = i;
            }
        }
        return false;
    }
}
