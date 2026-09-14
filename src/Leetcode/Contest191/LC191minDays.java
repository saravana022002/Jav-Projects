package Leetcode.Contest191;

public class LC191minDays {
    public static void main(String[] args) {
        int n = 2;
        System.out.println(minDays(n));
    }




    public static int minDays(int n) {

        if (n == 0) {
            return 0;
        }

        int ans = Integer.MAX_VALUE;

        for (int k = 1; k * (k + 1) / 2 <= n; k++) {

            int accumulatedCount = k * (k + 1) / 2;

            int days = k + 1 + minDays(n - accumulatedCount);

            ans = Math.min(ans, days);
        }

        return ans - 1;
    }

}
