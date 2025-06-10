package Contest158;

public class MaxProfitWithShortSelling {

    public static int maxProfit(int k, int[] prices) {
        if (prices == null || prices.length == 0 || k == 0) {
            return 0;
        }
        int n = prices.length;
        int[][][] dp = new int[n][k + 1][3]; // [day][transactions][state]

        // Initialize base cases
        for (int j = 0; j <= k; j++) {
            dp[0][j][0] = 0; // No position
            dp[0][j][1] = -prices[0]; // Bought on day 0
            dp[0][j][2] = 0; // Can't short sell on day 0
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                // State 0: No position
                dp[i][j][0] = Math.max(dp[i - 1][j][0],
                        Math.max(dp[i - 1][j][1] + prices[i], // Sold
                                dp[i - 1][j][2] - prices[i])); // Bought back

                // State 1: Holding stock (from buying)
                if (j > 0) {
                    dp[i][j][1] = Math.max(dp[i - 1][j][1], // Keep holding
                            dp[i - 1][j - 1][0] - prices[i]); // Buy
                } else {
                    dp[i][j][1] = dp[i - 1][j][1]; // Can't buy if no transactions left
                }

                // State 2: Short position (from short selling)
                if (j > 0) {
                    dp[i][j][2] = Math.max(dp[i - 1][j][2], // Keep short
                            dp[i - 1][j - 1][0] + prices[i]); // Short sell
                } else {
                    dp[i][j][2] = dp[i - 1][j][2]; // Can't short sell if no transactions left
                }
            }
        }

        int maxProfit = 0;
        for (int j = 0; j <= k; j++) {
            maxProfit = Math.max(maxProfit,
                    Math.max(dp[n - 1][j][0],
                            Math.max(dp[n - 1][j][1], dp[n - 1][j][2])));
        }
        return maxProfit;
    }

    public static void main(String[] args) {
        int[] prices1 = {1, 7, 9, 8, 2};
        int k1 = 2;
        System.out.println(maxProfit(k1, prices1)); // Expected: 14

        int[] prices2 = {12, 16, 19, 19, 8, 1, 19, 13, 9};
        int k2 = 3;
        System.out.println(maxProfit(k2, prices2)); // Expected: 36
    }
}