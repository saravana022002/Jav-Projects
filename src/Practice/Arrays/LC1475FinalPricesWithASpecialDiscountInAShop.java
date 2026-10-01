package Practice.Arrays;

import java.util.Arrays;

// ============================================================================
// LC 1475. Final Prices With a Special Discount in a Shop   [Easy]
// https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop/
// Topics: Array, Stack, Monotonic Stack
// ============================================================================
//
// PROBLEM
//   prices[i] is the price of item i. When you buy item i, you get a discount equal to
//   the price of the FIRST item after it (smallest j > i) whose price is less than or
//   equal to prices[i]. If no later item qualifies, there is no discount.
//   Return the final price paid for every item.
//
// EXAMPLES
//   prices = [8,4,6,2,3]  ->  [4,2,4,2,3]   8-4, 4-2, 6-2; the last two get no discount
//   prices = [1,2,3,4,5]  ->  [1,2,3,4,5]   every later price is higher: no discounts
//   prices = [10,1,1,6]   ->  [9,0,1,6]     an EQUAL later price counts: 1 - 1 = 0
//
// CONSTRAINTS
//   1 <= prices.length <= 500,   1 <= prices[i] <= 1000
//
// INTUITION
//   For each item the discount is its "next smaller-or-equal element" to the right.
//   With at most 500 items, simply scanning right from each item and stopping at the
//   first price <= it is fast enough (at most about 125,000 comparisons).
//   The O(n) alternative, not used here, is a monotonic stack: keep the indices still
//   waiting for a discount (their prices increase up the stack); each new price p pops
//   and discounts every waiting item with price >= p, then waits itself.
//
// APPROACH (as implemented below - brute force, in place)
//   1. For each item i, scan j = i+1, i+2, ... to the right.
//   2. At the first j with prices[j] <= prices[i]: prices[i] -= prices[j], then break.
//   3. If no such j exists, prices[i] stays as it is.
//   4. Return the same array, now holding the final prices.
//
// DRY RUN   prices = [8,4,6,2,3]
//   i=0 (8): j=1 -> 4 <= 8   => 8 - 4 = 4
//   i=1 (4): j=2 -> 6 > 4, j=3 -> 2 <= 4   => 4 - 2 = 2
//   i=2 (6): j=3 -> 2 <= 6   => 6 - 2 = 4
//   i=3 (2): j=4 -> 3 > 2, end   => stays 2
//   i=4 (3): nothing to the right   => stays 3
//   result [4,2,4,2,3]
//
// COMPLEXITY
//   Time : O(n^2) - for each i the scan may run to the end (e.g. an increasing array)
//   Space: O(1) extra - the answer is written into the input array
//
// GOTCHAS
//   - The rule is <=, not <: an equal price is a valid discount (example 3).
//   - Only the FIRST qualifying item counts, hence the break.
//   - Overwriting prices[i] in place is safe: the scan only reads j > i, and those
//     entries have not been changed yet. The caller's array is modified, though.
//
// RELATED
//   - LC 739 Daily Temperatures - "next warmer day", the same next-element search that a
//     monotonic stack solves in O(n) (src/Practice/Stack/LC739DailyTemperatures*.java).
//   - LC 496 Next Greater Element I - next greater element with a monotonic stack
//     (src/Practice/Stack/LC496NextGreaterElementI.java).
// ============================================================================
public class LC1475FinalPricesWithASpecialDiscountInAShop {
    public static void main(String[] args) {
        // Example 1 - prints [4, 2, 4, 2, 3]
        int[] prices = {8,4,6,2,3};
        System.out.println(Arrays.toString(finalPrices(prices)));
    }

    // Returns the final prices; overwrites the input array (brute force, O(n^2)).
    public static int[] finalPrices(int[] prices) {
        for (int i = 0; i < prices.length; i++) {
            // look right for the first price that is not higher than prices[i]
            for (int j = i + 1; j < prices.length; j++) {
                if(prices[j] <= prices[i]) {
                    // safe to overwrite: later scans only read indices > i, still original
                    prices[i] = prices[i] - prices[j];
                    // only the first qualifying item gives the discount
                    break;
                }
            }
        }
        return prices;
    }
}
