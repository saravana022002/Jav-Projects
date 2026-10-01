package Practice.Arrays;


// ============================================================================
// LC 121. Best Time to Buy and Sell Stock   [Easy]
// https://leetcode.com/problems/best-time-to-buy-and-sell-stock/
// Topics: Array, Dynamic Programming
// ============================================================================
//
// PROBLEM
//   prices[i] is a stock's price on day i. You may buy one share on one day and sell it
//   on a LATER day - a single transaction at most. Return the largest profit you can
//   make; if every possible trade loses money, do not trade and return 0.
//
// EXAMPLES
//   prices = [7,1,5,3,6,4]  ->  5   buy at 1 (day 2), sell at 6 (day 5). Selling at 7 is
//                                   not allowed: that day comes before the purchase.
//   prices = [7,6,4,3,1]    ->  0   the price only falls, so no trade is made
//
// CONSTRAINTS
//   1 <= prices.length <= 10^5,   0 <= prices[i] <= 10^4
//
// INTUITION
//   Fix the SELL day. The best purchase for it is simply the cheapest price on any
//   earlier day. So walk through the days once and remember the lowest price seen so far;
//   for the next day, "its price - that lowest earlier price" is the best profit when
//   selling on that day. The answer is the largest such value, and never below 0.
//
// APPROACHES IN THIS FILE
//   A. approach(prices)  - brute force: every (buy i, sell j > i) pair - O(n^2)
//   B. approach1(prices) - one pass with the running minimum     - O(n)
//   main() runs both on example 1; each prints 5.
//   (APPROACH and DRY RUN below describe B.)
//
// APPROACH (as implemented below, approach1)
//   1. min = Integer.MAX_VALUE (cheapest price so far), maxDifference = 0 (best profit
//      so far; 0 means "no trade").
//   2. For i = 0 .. n-2: min = min(min, prices[i]) - the cheapest buy among days 0..i.
//   3. maxDifference = max(maxDifference, prices[i+1] - min) - profit when selling on
//      day i+1 after buying at that cheapest day.
//   4. Return maxDifference.
//
// DRY RUN   prices = [7,1,5,3,6,4]
//   i=0: min=7   sell at 1: 1-7 = -6   maxDifference = 0
//   i=1: min=1   sell at 5: 5-1 =  4   maxDifference = 4
//   i=2: min=1   sell at 3: 3-1 =  2   maxDifference = 4
//   i=3: min=1   sell at 6: 6-1 =  5   maxDifference = 5
//   i=4: min=1   sell at 4: 4-1 =  3   maxDifference = 5    -> return 5
//
// COMPLEXITY
//   approach1: Time O(n) - one pass;  Space O(1) - two variables
//   approach : Time O(n^2) - n(n-1)/2 pairs, about 5 * 10^9 for n = 10^5, far too slow
//              on the largest inputs;  Space O(1)
//
// GOTCHAS
//   - Start the answer at 0: when prices only fall the answer is 0 (no trade), not a
//     negative number.
//   - The buy must come first: approach1 folds prices[i] into min and then sells at
//     prices[i+1], so a sale is always paired with an earlier day.
//   - A single day (n = 1): the loop never runs and 0 is returned.
//
// RELATED
//   - LC 122 Best Time to Buy and Sell Stock II - unlimited trades: add up every rise.
//   - LC 2016 Maximum Difference Between Increasing Elements - the same running-min scan,
//     but -1 when nothing increases (LC2016MaximumDifferenceBetweenIncreasingElements.java).
//   - LC 53 Maximum Subarray - the best profit is the best-sum run of daily price changes
//     (Kadane's algorithm).
// ============================================================================
public class LC121BestTimeToBuyAndSellStock {
    public static void main(String[] args) {
        // Example 1 - both approaches print 5 (buy at 1, sell at 6)
        int[] prices = {7,1,5,3,6,4};
        int profit = approach(prices);
        System.out.println(profit);
        int profit1 = approach1(prices);
        System.out.println(profit1);
    }

    // Approach B: one pass with the running minimum. O(n) time, O(1) space.
    private static int approach1(int[] prices) {
        int n = prices.length;
        // cheapest price seen so far, and the best profit so far (0 = do not trade)
        int min = Integer.MAX_VALUE;
        int maxDifference = 0;
        for (int i = 0; i < n - 1; i++) {
            // cheapest day to buy among days 0..i
            min = Math.min(min, prices[i]);
            // profit if we sell on the next day, day i+1
            maxDifference = Math.max(maxDifference, prices[i+1] - min);
        }
        return maxDifference;
    }

    // Approach A: brute force - try every buy day i with every later sell day j. O(n^2).
    private static int approach(int[] prices) {
        int n = prices.length;
        int globalMax = 0;
        for (int i = 0; i < n; i++) {
            // best profit for a purchase on day i
            int maxProfit = 0;
            for (int j = i+1; j < n; j++) {
                maxProfit = Math.max(maxProfit, prices[j] - prices[i]);
            }
            globalMax = Math.max(globalMax,maxProfit);
        }
        return globalMax;
    }
}
