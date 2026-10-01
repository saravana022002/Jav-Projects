package Practice.Greedy;

// ============================================================================
// LC 605. Can Place Flowers   [Easy]
// https://leetcode.com/problems/can-place-flowers/
// Topics: Array, Greedy
// ============================================================================
//
// PROBLEM
//   flowerbed is a row of plots: 1 = a flower is already planted, 0 = empty. Flowers may not
//   sit in adjacent plots (the given bed already obeys this). Return true if n more flowers
//   can be planted without two flowers ending up next to each other, otherwise false.
//
// EXAMPLES
//   flowerbed = [1,0,0,0,1], n = 1  ->  true      plant in the middle plot
//   flowerbed = [1,0,0,0,1], n = 2  ->  false     the plots next to the existing flowers
//                                                 are blocked, so only the middle one is left
//   flowerbed = [0], n = 1          ->  true      what main() runs
//
// CONSTRAINTS
//   1 <= flowerbed.length <= 2 * 10^4,   flowerbed[i] is 0 or 1,
//   no two adjacent flowers in the input,   0 <= n <= flowerbed.length
//
// INTUITION
//   Walk left to right and plant in every plot that is legal right now (empty, with empty
//   neighbours; beyond either end counts as empty). Planting as early as possible never hurts:
//   in any best plan, the first flower can be slid left to this earliest legal plot without
//   touching the flowers after it. Write each new flower into the array so the following plots
//   see it as a neighbour.
//
// APPROACH (as implemented below)
//   1. One-plot bed with n == 1: the answer is just "is that plot empty".
//   2. For i = 0 .. last:
//        - n == 0 -> all requested flowers are placed, return true;
//        - flowerbed[i] == 1 -> skip it;
//        - middle plot: plant if flowerbed[i-1] and flowerbed[i+1] are both 0;
//        - first plot: plant if flowerbed[i+1] == 0;
//        - last plot: plant if flowerbed[i-1] == 0;
//        planting = n-- and flowerbed[i] = 1.
//   3. After the loop return n == 0 (covers the last flower going into the very last plot).
//
// DRY RUN   flowerbed = [1,0,0,0,1], n = 2   (example 2)
//   i=0   plot is 1                      -> skip
//   i=1   left neighbour is 1            -> cannot plant
//   i=2   neighbours 0 and 0             -> plant, n = 1, bed = [1,0,1,0,1]
//   i=3   left neighbour (plot 2) is now 1 -> cannot plant
//   i=4   plot is 1                      -> skip
//   loop ends with n = 1 != 0            -> false
//   main(): [0] with n = 1 takes the one-plot shortcut, flowerbed[0] == 0 -> true.
//
// COMPLEXITY
//   Time : O(len) - a single pass over the bed
//   Space: O(1) - the bed is updated in place
//
// GOTCHAS
//   - The input array is modified: planted flowers are written into it.
//   - The one-plot shortcut also protects the first-plot branch, which would otherwise read
//     flowerbed[1] out of bounds; it relies on n <= length, which the constraints give.
//   - The first and last plots have their own branches because they only have one neighbour.
//   - `n == 0` is checked at the top of the loop, so n = 0 returns true immediately.
// ============================================================================
public class LC605CanPlaceFlowers {
    public static void main(String[] args) {
        // A single empty plot and one flower to plant. Expected output: true
        int[] flowerbed = {0};
        int n = 1;
        System.out.println(canPlaceFlowers(flowerbed, n));
    }
    // Returns true if n more flowers fit into the bed (greedy, left to right).
    public static boolean canPlaceFlowers(int[] flowerbed, int n) {

        // one plot only: the general branches below would look at a neighbour that is not there
        if(flowerbed.length == 1 && n == 1){
            return flowerbed[0] == 0;
        }

        for (int i = 0; i < flowerbed.length; i++) {
            // everything requested has been planted
            if(n == 0){
                return true;
            }
            // already a flower here
            if(flowerbed[i] == 1){
               continue;
            }
            // middle plot: both neighbours must be empty
            if (i > 0 && i < flowerbed.length - 1) {
                if (flowerbed[i - 1] == 0 && flowerbed[i + 1] == 0) {
                    n--;
                    flowerbed[i] = 1;
                }
            // first plot: only the right neighbour exists
            }else if(i == 0 && flowerbed[i + 1] == 0){
                n--;
                flowerbed[i] = 1;
            // last plot: only the left neighbour exists
            }else if (i == flowerbed.length - 1 && flowerbed[i - 1] == 0){
                n--;
                flowerbed[i] = 1;
            }
        }
        // the last flower may just have been planted in the last plot
        return n == 0;
    }
}
