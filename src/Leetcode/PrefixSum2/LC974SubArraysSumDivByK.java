package Leetcode.PrefixSum2;

import java.util.HashMap;

public class LC974SubArraysSumDivByK {
    public static void main(String[] args) {
        int[] nums = {4,5,0,-2,-3,1};
        int k = 5;
        System.out.println(subArraysDivByK(nums, k));
    }



    // p[r] - p[l] = t ; where t is divisible by k
    // in Modulo arithmetic it is, p[r] - p[l] =_= 0 (mod K)
    // (Mod k)p[r] = p[l] (Mod k)
    public static int subArraysDivByK(int[] nums, int k) {
        int[] map = new int[nums.length];
        map[0] = 1;
        int count = 0;
        int ans = 0;
        for (int num : nums) {
            count += num;
            int remainder = ((count % k) + k) % k;
            ans += map[remainder];
            map[remainder]++;
        }
        return ans;
    }
}
