package SDE.DailyChallenge;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class KthSmallestLex {
    public static void main(String[] args) {
        int n = 681692778;
        int k = 351251360;
        System.out.println(findKthNumber(n, k));
    }

    public static int findKthNumber(int n, int k) {

        int curr = 1;
        long i = 1;
        while (i < k){
          long steps = count(curr, n);
          if(i + steps <= k){
              curr = curr + 1;
              i += steps;
          }else {
              curr = curr * 10;
              i++;
          }
        }
        return curr;
    }
    public static long count(int curr, int n){
        long res = 0;
        long neg = curr + 1;
        while (curr <= n){
            res += Math.min(neg, n + 1) - curr;
            curr *= 10;
            neg *= 10;
        }
        return res;
    }
    public static void dfsTraversal(List<Integer> result, int i, int n, int k){
        if(i > n){
            return;
        }
        if(result.size() >= k) {
            return;
        }
        result.add(i);
        for (int digit = 0; digit <= 9; digit++) {
            int curr = i * 10 + digit;
            if (curr > n) {
                break;
            }
            dfsTraversal(result, curr, n, k);
        }
    }



}
