package SDE.GreedyAlgorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class FractionalKnapsack {
    public static void main(String[] args) {
        int W = 21;
        int[] values = {8 , 2 ,10 ,1 ,9 ,7 ,2 ,6 ,4 , 9};
        int[] weights = {10 ,1 ,7 ,7 ,5 ,1 ,8 ,6 ,8 ,7};
        System.out.println(fractionalKnapsack(values, weights, W));
    }
    static double fractionalKnapsack(int[] values, int[] weights, int W) {
        int n = values.length;
        List<KnapSack> knapSacks = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            knapSacks.add(new KnapSack(values[i], weights[i], (double) values[i] / weights[i]));
        }
        knapSacks.sort((a, b) -> Double.compare(b.valuePerWeight, a.valuePerWeight));

        double profit = 0;
        for (KnapSack knapSack : knapSacks){
            if(W > 0 && knapSack.weight <= W){
                W = W - knapSack.weight;
                profit = profit + knapSack.value;
            }else {
                profit = profit + W * knapSack.valuePerWeight;
                break;
            }
        }
        return profit;
    }

    static class KnapSack{
        int value;
        int weight;
        double valuePerWeight;
        KnapSack(int value, int weight, double valuePerWeight){
            this.value = value;
            this.weight = weight;
            this.valuePerWeight = valuePerWeight;
        }
    }
}
