package Arrays;


/**
 * Example 1:
 * Input:
 *  prices = [7,1,5,3,6,4]
 * Output:
 *  5
 * Explanation:
 *  Buy on day 2 (price = 1) and
 * sell on day 5 (price = 6), profit = 6-1 = 5.
 *
 * Note
 * : That buying on day 2 and selling on day 1
 * is not allowed because you must buy before
 * you sell.
 *
 * Example 2:
 * Input:
 *  prices = [7,6,4,3,1]
 * Output:
 *  0
 * Explanation:
 *  In this case, no transactions are
 * done and the max profit = 0.
 */
public class StockMaxProfit {
    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        int profit = approach(prices);
        System.out.println(profit);
        int profit1 = approach1(prices);
        System.out.println(profit1);
    }

    private static int approach1(int[] prices) {
        int n = prices.length;
        int min = Integer.MAX_VALUE;
        int maxDifference = 0;
        for (int i = 0; i < n - 1; i++) {
            min = Math.min(min, prices[i]);
            maxDifference = Math.max(maxDifference, prices[i+1] - min);
        }
        return maxDifference;
    }

    private static int approach(int[] prices) {
        int n = prices.length;
        int globalMax = 0;
        for (int i = 0; i < n; i++) {
            int maxProfit = 0;
            for (int j = i+1; j < n; j++) {
                maxProfit = Math.max(maxProfit, prices[j] - prices[i]);
            }
            globalMax = Math.max(globalMax,maxProfit);
        }
        return globalMax;
    }
}
