package Quests.Quiz1CH1;

import java.util.Arrays;

public class PlusOne {
    public static void main(String[] args) {
        int[] digits = {1,9,0};

        System.out.println(Arrays.toString(plusOne(digits)));
    }
    public static int[] plusOne(int[] digits) {
         int k = digits.length - 1;
         while (k >= 0 && digits[k] == 9){
             k--;
         }
         boolean extra = k == -1;
         k++;
         int[] ans = Arrays.copyOf(digits, extra ? digits.length + 1 : digits.length);
         if(extra){
             ans[k++] = 1;
         }else {
             ans[k - 1] = ans[k - 1] + 1;
         }
         while (k >= 0 && k < ans.length){
             ans[k++] = 0;
         }
         return ans;
    }
}
