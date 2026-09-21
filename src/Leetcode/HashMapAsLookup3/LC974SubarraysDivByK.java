package Leetcode.HashMapAsLookup3;

public class LC974SubarraysDivByK {
    public static void main(String[] args) {
        int[] nums = {4,5,0,-2,-3,1};
        int k = 5;
        System.out.println(subarraysDivByK(nums, k));
    }

    public static int subarraysDivByK(int[] nums, int k) {
        int[] rems = new int[k];
        rems[0] = 1;
        int count = 0;
        int sum = 0;
        for (int num : nums) {
            sum += num;
            int rem = ((sum % k) + k) % k;
            count += rems[rem];
            rems[rem]++;
        }
        return count;
    }

}
