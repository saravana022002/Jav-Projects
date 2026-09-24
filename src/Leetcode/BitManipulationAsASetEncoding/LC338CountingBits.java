package Leetcode.BitManipulationAsASetEncoding;

import java.util.Arrays;

public class LC338CountingBits {
    public static void main(String[] args) {
        int n = 2;
        System.out.println(Arrays.toString(countBits(n)));
    }

    public static int[] countBits(int n) {
        int[] ans = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            ans[i] = hammingWeight(i);
        }
        return ans;
    }

    public static int hammingWeight(int n) {
        int temp;
        int count = 0;
        while(n > 0){
            temp = n - 1;
            n = n & temp;
            count++;
        }
        return count;
    }
    public static int[] countBitsOpt(int n) {
        int[] ans = new int[n + 1];
        for (int i = 0; i < n; i++) {
            ans[i] = ans[i & (i - 1)] +  1;
        }
        return ans;
    }
}
