package SDE.MiscProblemsLeetCode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MaximumProductOfTwoDigits {

    public static void main(String[] args) {
        int n = 124;
        maxProduct(n);
        optimizedMaxProduct(n);
    }
    public static int maxProduct(int n) {
        List<Integer> integers = new ArrayList<>();
        int remainder = 0;
        while (n > 0){
            remainder = n % 10;
            integers.add(remainder);
            n = n / 10;
        }

        Integer max = Collections.max(integers);
        integers.remove(max);
        Integer secondMax = Collections.max(integers);
        int ans = max * secondMax;
        return ans;
    }

    public static int optimizedMaxProduct(int n) {
        int max1 = 0;
        int max2 = 0;
        int digit;
        while (n > 0){
            digit = n % 10;
            if(digit > max1){
                max2 = max1;
                max1 = digit;
            }else if (digit > max2){
                max2 = digit;
            }
            n = n / 10;
        }

        return max1 * max2;
    }



}
