package SDE.DailyChallenge;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GetSneakyNumbers {
    public static void main(String[] args) {
        int[] nums = {7,1,5,4,3,4,6,0,9,5,8,2};
        System.out.println(Arrays.toString(getSneakyNumbers1(nums)));
    }

    public static int[] getSneakyNumbers(int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        int[] ans = new int[2];
        int count = 0;
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + 1);

            if (freqMap.get(num) == 2) {
                ans[count] = num;
                count++;
                if (count == 2) {
                    break;
                }
            }
        }
        return ans;
    }

    public static int[] getSneakyNumbers1(int[] nums) {
        long N = nums.length;
        long n = N - 2;

        long S_actual = 0;
        long S2_actual = 0;
        for (int num : nums) {
            S_actual += num;
            S2_actual += (long)num * num;
        }
        long n_sum = (n * (n - 1)) / 2;


        long n_sum2 = (n * (n - 1) * (2 * n - 1)) / 6;

        long X = S_actual - n_sum ;
        long Y = S2_actual - n_sum2;


        long zSquared = 2*Y - X*X;

        long Z = Math.round(Math.sqrt(zSquared));


        int A = (int) ((X + Z)/2);
        int B = (int) ((X - Z)/2);

        return new int[]{A, B};
    }


    public static boolean isNegative(int n) {
        return n < 0;
    }
}
