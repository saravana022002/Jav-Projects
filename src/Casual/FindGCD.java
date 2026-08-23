package Casual;

public class FindGCD {
    public static void main(String[] args) {
        int[] nums = {2,5,6,9,10};
        findGCD(nums);
    }

    public static int findGCD(int[] nums) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            max = Math.max(max, nums[i]);
            min = Math.min(min, nums[i]);
        }
        return gcd(max, min);
    }

    public static int gcd(int a, int b){
        int temp = 0;
        while(b != 0){
            temp = a;
            a = b;
            b = temp % b;
        }
        return a;
    }



}
