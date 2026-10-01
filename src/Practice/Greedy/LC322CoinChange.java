package Practice.Greedy;

import java.util.TreeSet;

// ============================================================================
// LC 322. Coin Change   [Medium]
// https://leetcode.com/problems/coin-change/
// Topics: Array, Dynamic Programming, Breadth-First Search, Knapsack Problem, Complete Knapsack
// ============================================================================
//
// PROBLEM
//   coins[] lists coin denominations and you own unlimited coins of each kind. Return the
//   fewest coins whose values add up to exactly `amount`, or -1 if no combination can do it.
//   An amount of 0 needs 0 coins.
//
// EXAMPLES
//   coins = [1,2,5], amount = 11  ->  3     5 + 5 + 1
//   coins = [2],     amount = 3   -> -1     only even amounts can be made from 2s
//   coins = [1],     amount = 0   ->  0     nothing to pay (this is what main() runs)
//
// CONSTRAINTS
//   1 <= coins.length <= 12,   1 <= coins[i] <= 2^31 - 1,   0 <= amount <= 10^4
//
// INTUITION   (the idea this file tries - it is NOT correct in general, see STATUS)
//   A big coin covers more of the amount, so take the biggest coin that still fits, repeat
//   on what is left, and count the coins. That greedy works for "nice" coin sets such as
//   1, 2, 5 but breaks on others, because it commits to the big coin and never looks back.
//   With [1,3,4] and amount 6 it takes 4 and is left with 2 = 1 + 1 (three coins), while
//   3 + 3 needs only two. With [5,2] and amount 6 it takes 5, is left with 1, finds no coin
//   <= 1 and gives up, although 2 + 2 + 2 works.
//   A correct solution has to try every coin for every smaller amount: dynamic programming
//   with dp[a] = 1 + min(dp[a - coin]) over the coins that fit, dp[0] = 0 (or a BFS over
//   amounts).
//
// APPROACH (as implemented below - the greedy)
//   1. Put the coins in a TreeSet `integers` (sorted, duplicates removed).
//   2. No coins at all -> return -1 (the constraints never allow this).
//   3. While amount > 0: coin = integers.floor(amount), the largest coin <= amount.
//      null (every coin is bigger than what is left) -> return -1.
//      Otherwise amount -= coin and count++.
//   4. Return count.
//
// DRY RUN   coins = [1,3,4], amount = 6   (the greedy answers 3, the correct answer is 2)
//   floor(6) = 4   amount 6 -> 2   count 1
//   floor(2) = 1   amount 2 -> 1   count 2
//   floor(1) = 1   amount 1 -> 0   count 3      -> returns 3   (3 + 3 would use 2 coins)
//   For the LeetCode example [1,2,5], 11 the same loop takes 5, 5, 1 and returns 3 correctly.
//   main(): coins = {1}, amount = 0 -> the loop never runs -> prints 0.
//
// COMPLEXITY   (of this code)
//   Time : O(c log c + k log c) - building the TreeSet, then one floor() per coin taken;
//          k <= amount (for example only coin 1), so at worst O(amount log c).
//   Space: O(c) - the TreeSet.
//   (The correct DP would be O(amount * c) time and O(amount) space.)
//
// GOTCHAS
//   - amount = 0 returns 0 without entering the loop (example 3).
//   - floor() ignores coins larger than amount, so coin values near 2^31 - 1 are harmless.
//   - Passing the three official examples proves nothing here: they all use coin sets
//     where greedy happens to work.
//
// STATUS
//   Known issue: the algorithm (largest coin first) is wrong for many valid inputs.
//   coinChange({1,3,4}, 6) returns 3 but the answer is 2 (3 + 3);
//   coinChange({5,2}, 6) returns -1 but the answer is 3 (2 + 2 + 2);
//   coinChange({186,419,83,408}, 6249) returns -1 but the answer is 20 (checked with a DP).
//   It does pass the three official examples (3, -1, 0). The code is left unchanged on
//   purpose.
//
// RELATED
//   - LC 518 Coin Change II - the same coins, but it counts the ways to make the amount.
//   - LC 2224 Minimum Number of Operations to Convert Time - a coin-like task (steps of 60,
//     15, 5, 1 minutes) where the largest-first greedy IS correct.
// ============================================================================
public class LC322CoinChange {
    public static void main(String[] args) {
        // Example 3: nothing to pay, so 0 coins. Expected output: 0
        // (The greedy gives wrong answers on other inputs - see STATUS.)
        int[] coins = {1};
        int amount = 0;
        System.out.println(coinChange(coins, amount));
    }
    // Greedy "largest coin first" - returns a count, but not always the fewest (see STATUS).
    public static int coinChange(int[] coins, int amount) {
        // sorted set of distinct coin values, so floor() can find the largest coin <= amount
        TreeSet<Integer> integers = new TreeSet<>();
        for (int coin : coins) {
            integers.add(coin);
        }
        if(integers.isEmpty())
            return -1;
        int count = 0;
        while (amount > 0 ) {
            // biggest coin that still fits; null -> every coin is larger than what is left
            Integer coin = integers.floor(amount);
            if (coin == null) {
                return -1;
            }
            amount = amount - coin;
            count++;
        }
        return count;
    }
}
