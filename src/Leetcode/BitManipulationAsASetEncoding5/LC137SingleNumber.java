package Leetcode.BitManipulationAsASetEncoding5;

public class LC137SingleNumber {
    public static void main(String[] args) {
        int[] nums = {2,2,3,2};
        System.out.println(singleNumber(nums));
    }
    public static int singleNumber(int[] nums) {
        int count;
        int ans = 0;
        for (int i = 0; i < 32; i++) {
            count = 0;
            for (int num : nums) {
                count += (num >> i) & 1;
            }

            if(count % 3 == 1){
                ans |= (1 << i);
            }
        }
        return ans;
    }
}
