package SDE.GreedyAlgorithm;

import java.util.TreeSet;

public class CoinChange {
    public static void main(String[] args) {
        int[] coins = {1};
        int amount = 0;
        System.out.println(coinChange(coins, amount));
    }
    public static int coinChange(int[] coins, int amount) {
        TreeSet<Integer> integers = new TreeSet<>();
        for (int coin : coins) {
            integers.add(coin);
        }
        if(integers.isEmpty())
            return -1;
        int count = 0;
        while (amount > 0 ) {
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
