package Practice.Contests.Contest158;

// ============================================================================
// LC 3573. Best Time to Buy and Sell Stock V   [Medium]
// https://leetcode.com/problems/best-time-to-buy-and-sell-stock-v/
// Topics: Array, Dynamic Programming
// ============================================================================
//
// PROBLEM
//   prices[i] is a stock's price on day i and k is the maximum number of transactions you
//   may make. A transaction is one of
//     normal : buy on day i, sell on a later day j    -> profit prices[j] - prices[i]
//     short  : sell on day i, buy back on a later day j -> profit prices[i] - prices[j]
//   A transaction must be finished before the next one starts, and the day a transaction
//   closes cannot also open another. Return the largest total profit from at most k
//   transactions.
//
// EXAMPLES
//   prices = [1,7,9,8,2], k = 2  ->  14
//       normal: buy day 0 (1), sell day 2 (9) = +8;  short: sell day 3 (8), buy back day 4 (2) = +6
//   prices = [12,16,19,19,8,1,19,13,9], k = 3  ->  36
//       normal 12 -> 19 (+7), short 19 -> 8 (+11), normal 1 -> 19 (+18)
//
// CONSTRAINTS
//   2 <= prices.length <= 10^3,   1 <= prices[i] <= 10^9,   1 <= k <= prices.length / 2
//
// INTUITION
//   Process the days in order and remember only what you are "doing" right now:
//     state 0 = flat (no open trade), state 1 = holding a bought stock (long),
//     state 2 = sold a stock you do not own and still owe it back (short).
//   dp[i][j][s] is the best profit after day i, having opened j transactions, in state s.
//   Each day a value can move like this:
//     flat  <- stay flat | sell the long (+price) | buy back the short (-price)
//     long  <- keep holding | buy today from flat with one transaction fewer (-price)
//     short <- keep the short | sell short today from flat with one transaction fewer (+price)
//   A trade is opened from flat (this uses one of the k) and closed back into flat. Only a
//   FLAT state on the last day has every trade closed, so the answer should be the best
//   flat value over all j <= k. (The code differs in two places - see STATUS.)
//
// APPROACH (as implemented below)
//   1. Return 0 for empty prices or k == 0. dp = int[n][k + 1][3].
//   2. Day 0, for every j: dp[0][j][0] = 0 (flat), dp[0][j][1] = -prices[0] (bought on
//      day 0), dp[0][j][2] = 0 (the comment says a short sale is impossible on day 0).
//   3. For each later day i and each j, fill the three states with the transitions above;
//      for j = 0 the long and short states can only carry over.
//   4. Answer: the largest of dp[n-1][j][0], dp[n-1][j][1] and dp[n-1][j][2] over all j.
//
// DRY RUN   prices = [1,7,9,8,2], k = 2   (cells are [flat / long / short]; j = 1 shown,
//                                          j = 2 comes out the same here)
//   day 0 (price 1):  [ 0 / -1 /  0]     base states
//   day 1 (price 7):  [ 6 / -1 /  7]
//   day 2 (price 9):  [ 8 / -1 / 15]
//   day 3 (price 8):  [ 8 /  0 / 16]
//   day 4 (price 2):  [14 /  6 / 16]
//   The code returns the largest cell, 16. The correct answer is the flat value 14.
//
// STATUS
//   Known issue: the result is wrong - main() prints 16 and 49, but the LeetCode answers
//   (noted in main()) are 14 and 36. Two defects, each confirmed by fixing it in a copy (with
//   both fixed the copy gives 14 and 36, and matches a brute force on thousands of random
//   inputs):
//   1. The final answer also looks at states 1 and 2, which still have a trade open. An
//      open short keeps the money of its sale without ever buying back. In the dry run the
//      16 is the open short: 8 (buy 1, sell 9) + 8 (short sale on day 3, never bought
//      back). Another example: prices [1,5] with k = 1 returns 5, the correct answer is 4.
//   2. The day-0 base states are wrong. dp[0][j][1] = -prices[0] is also set for j = 0, so
//      a stock can be held without using up a transaction, and dp[0][j][2] should be
//      +prices[0] for j >= 1 (a short sale on day 0 IS allowed): prices [9,1] with k = 1
//      returns 1, the correct answer is 8.
//   Also: the parameters are (k, prices) while the problem lists prices first, and int is
//   too small for the profit, which can reach about 5 * 10^11 (1000 days alternating 1 and
//   10^9 with k = 500).
//   The code is left unchanged on purpose.
//
// COMPLEXITY
//   Time : O(n * k) - three states per (day, j) cell, each updated in O(1)
//   Space: O(n * k) - the full dp table (only the previous day is ever read, so O(k) would do)
//
// GOTCHAS
//   - j counts transactions OPENED, so buying or short selling moves from j - 1 to j.
//   - The day a transaction closes cannot open the next one: each day's new state is built
//     from the previous day's values only.
//   - A trade that is never closed must not count toward the profit - defect 1.
//
// RELATED
//   - LC 121 Best Time to Buy and Sell Stock (in this repo) - one normal transaction only.
//   - LC 188 Best Time to Buy and Sell Stock IV - at most k normal transactions, the same
//     kind of day / transactions / holding DP.
// ============================================================================
public class LC3573BestTimeToBuyAndSellStockV {

    // Intended: the best profit from at most k transactions - wrong, see STATUS.
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
            // defect 2 (see STATUS): the long is set for j = 0 too, and a short sale on
            // day 0 is possible - the short state should start at +prices[0] for j >= 1
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j <= k; j++) {
                // State 0: No position
                // (reached by staying flat or by closing a trade: sell the long or buy back
                // the short)
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

        // the answer should use dp[n - 1][j][0] only: states 1 and 2 still have an open trade
        // (defect 1 in STATUS)
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
        // prints 16 (wrong) - see STATUS
        System.out.println(maxProfit(k1, prices1)); // Expected: 14

        int[] prices2 = {12, 16, 19, 19, 8, 1, 19, 13, 9};
        int k2 = 3;
        // prints 49 (wrong) - see STATUS
        System.out.println(maxProfit(k2, prices2)); // Expected: 36
    }
}