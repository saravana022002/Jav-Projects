package Contest158;

import java.util.*;

public class MaximizeSubarrayGCDScore {
    public static void main(String[] args) {
        int[] nums1 = {2, 4};
        int k1 = 1;
        System.out.println(maxScore(nums1, k1)); // Output: 8

        int[] nums2 = {3, 5, 7};
        int k2 = 2;
        System.out.println(maxScore(nums2, k2)); // Output: 14

        int[] nums3 = {5, 5, 5};
        int k3 = 1;
        System.out.println(maxScore(nums3, k3)); // Output: 15
    }

    public static long maxScore(int[] nums, int k) {
        int n = nums.length;
        int maxScore = 0;

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int len = j - i + 1;
                int[] subarray = Arrays.copyOfRange(nums, i, j + 1);
                int m = subarray.length;
                int maxGcd = 0;

                for (int mask = 0; mask < (1 << m); mask++) {
                    if (Integer.bitCount(mask) > k) {
                        continue;
                    }
                    int[] temp = subarray.clone();
                    for (int bit = 0; bit < m; bit++) {
                        if ((mask & (1 << bit)) != 0) {
                            temp[bit] *= 2;
                        }
                    }
                    int currentGcd = computeGcd(temp);
                    if (currentGcd > maxGcd) {
                        maxGcd = currentGcd;
                    }
                }
                int currentScore = len * maxGcd;
                if (currentScore > maxScore) {
                    maxScore = currentScore;
                }
            }
        }
        return maxScore;
    }

    private static int computeGcd(int[] arr) {
        int gcd = arr[0];
        for (int i = 1; i < arr.length; i++) {
            gcd = gcd(gcd, arr[i]);
            if (gcd == 1) {
                return 1;
            }
        }
        return gcd;
    }

    private static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}