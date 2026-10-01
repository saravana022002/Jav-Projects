package Practice.Greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// ============================================================================
// GFG - Fractional Knapsack   [Easy]
// https://www.geeksforgeeks.org/problems/fractional-knapsack-1587115620/1
// Topics: Greedy, Sorting
// ============================================================================
//
// PROBLEM
//   Item i is worth val[i] and weighs wt[i]. A knapsack holds at most `capacity` weight.
//   Unlike 0/1 knapsack you may cut an item and take any fraction of it (half the weight
//   gives half the value). Return the largest total value you can carry, as a double
//   (GFG shows it rounded to 6 decimal places).
//
// EXAMPLES
//   val = [60, 100, 120], wt = [10, 20, 30], capacity = 50  ->  240.000000
//        take the first two whole (60 + 100) and 20/30 of the third (80)
//   val = [500], wt = [30], capacity = 10  ->  166.670000 (as GFG prints it)
//        only 10/30 of the item fits: 500 * 10/30 = 166.666..., which this code returns
//
// CONSTRAINTS
//   Not part of the saved problem text - see the link.
//
// INTUITION
//   Because items can be cut, the only question is what each unit of capacity should be
//   filled with. value/weight says how much one unit of weight earns with that item, so
//   fill the bag with the best ratio first, then the next best, and so on. When an item
//   no longer fits whole, fill the remaining space with a slice of it and stop. Any
//   other choice swaps some high-ratio weight for low-ratio weight and can only lose.
//
// APPROACH (as implemented below)
//   1. Wrap every item as KnapSack(value, weight, valuePerWeight = value / weight).
//   2. Sort the list by valuePerWeight, largest first.
//   3. Walk it: if the whole item fits (weight <= W), take it - W -= weight,
//      profit += value.
//   4. Otherwise take only the W units left (profit += W * valuePerWeight) and stop.
//
// DRY RUN   main(): W = 21, ten items; sorted by ratio the list starts
//   7/1 = 7.0,  2/1 = 2.0,  9/5 = 1.8,  10/7 = 1.43,  9/7 = 1.29,  6/6 = 1.0, ...
//   take 7/1    W 21 -> 20   profit  7
//   take 2/1    W 20 -> 19   profit  9
//   take 9/5    W 19 -> 14   profit 18
//   take 10/7   W 14 -> 7    profit 28
//   take 9/7    W  7 -> 0    profit 37
//   6/6: W is 0, so the else branch adds 0 * 1.0 and breaks   => prints 37.0
//
// COMPLEXITY
//   Time : O(n log n) - the sort; building the list and the final walk are O(n).
//   Space: O(n) - the list of KnapSack objects.
//
// GOTCHAS
//   - Cast before dividing: (double) values[i] / weights[i]. Integer division would
//     truncate the ratios and break the ordering.
//   - When W hits exactly 0, the next item lands in the else branch and adds 0 - harmless.
//   - This greedy is only right because items can be split. For 0/1 knapsack (whole
//     items only) it fails and dynamic programming is needed.
//
// RELATED
//   - LC 1710 Maximum Units on a Truck - the same "best value per unit first" greedy, with
//     boxes that all weigh 1 (solved in LC1710MaximumUnitsOnATruck.java in this folder).
//
// SOURCE
//   Source: GeeksforGeeks (found via Striver SDE Sheet).
// ============================================================================
public class GFGFractionalKnapsack {
    public static void main(String[] args) {
        // Ten items and capacity 21 - prints 37.0 (the best five items fill it exactly).
        int W = 21;
        int[] values = {8 , 2 ,10 ,1 ,9 ,7 ,2 ,6 ,4 , 9};
        int[] weights = {10 ,1 ,7 ,7 ,5 ,1 ,8 ,6 ,8 ,7};
        System.out.println(fractionalKnapsack(values, weights, W));
    }
    // Returns the maximum value that fits in capacity W when items may be cut.
    static double fractionalKnapsack(int[] values, int[] weights, int W) {
        // step 1: remember each item's value per unit of weight
        int n = values.length;
        List<KnapSack> knapSacks = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            knapSacks.add(new KnapSack(values[i], weights[i], (double) values[i] / weights[i]));
        }
        // step 2: best ratio first (b before a -> descending)
        knapSacks.sort((a, b) -> Double.compare(b.valuePerWeight, a.valuePerWeight));

        double profit = 0;
        for (KnapSack knapSack : knapSacks){
            if(W > 0 && knapSack.weight <= W){
                // step 3: the whole item fits
                W = W - knapSack.weight;
                profit = profit + knapSack.value;
            }else {
                // step 4: fill the space that is left with a slice of it; the bag is full
                profit = profit + W * knapSack.valuePerWeight;
                break;
            }
        }
        return profit;
    }

    // one item and its value per unit of weight
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
