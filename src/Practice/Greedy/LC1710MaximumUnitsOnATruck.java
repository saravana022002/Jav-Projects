package Practice.Greedy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// ============================================================================
// LC 1710. Maximum Units on a Truck   [Easy]
// https://leetcode.com/problems/maximum-units-on-a-truck/
// Topics: Array, Greedy, Sorting
// ============================================================================
//
// PROBLEM
//   boxTypes[i] = [numberOfBoxes, unitsPerBox] says there are that many identical boxes of
//   type i, each holding that many units. The truck takes at most truckSize boxes in total
//   (any mix of types, any number of each up to what exists). Return the largest total number
//   of units you can load.
//
// EXAMPLES
//   boxTypes = [[1,3],[2,2],[3,1]], truckSize = 4  ->  8
//        take the 1 box of 3 units, the 2 boxes of 2 units, and 1 of the 1-unit boxes:
//        3 + 4 + 1
//   boxTypes = [[5,10],[2,5],[4,7],[3,9]], truckSize = 10  ->  91
//        best order is 10, 9, 7, 5 units per box: 5 boxes * 10, 3 * 9, and 2 of the 4 boxes
//        of 7 = 50 + 27 + 14
//
// CONSTRAINTS
//   1 <= boxTypes.length <= 1000
//   1 <= numberOfBoxes_i, numberOfUnitsPerBox_i <= 1000
//   1 <= truckSize <= 10^6
//
// INTUITION
//   Every box takes exactly one place on the truck, so the place is best spent on the box
//   holding the most units. Sort the box types by units per box, biggest first, and keep
//   loading: take all boxes of the current type while they fit, and when fewer places are
//   left than boxes of that type, fill the remaining places from it and stop. Swapping any
//   loaded box for one with fewer units can only lower the total.
//
// APPROACH (as implemented below)
//   1. Sort boxTypes by boxInfo[1] (units per box), largest first.
//   2. Walk the sorted types with truckSize = places still free, maxProfit = units so far.
//   3. If truckSize > 0 and the whole type fits (boxInfo[0] < truckSize): take it all -
//      truckSize -= boxInfo[0], maxProfit += boxInfo[0] * boxInfo[1].
//   4. Otherwise take only truckSize boxes of this type: maxProfit += truckSize * boxInfo[1],
//      truckSize = 0. Later types then add 0 * units = 0.
//   5. Return maxProfit.
//
// DRY RUN   boxTypes = [[1,3],[2,2],[3,1]], truckSize = 4   (already sorted by units)
//   [1,3]: 1 < 4  -> take all   truckSize 4 -> 3   maxProfit = 3
//   [2,2]: 2 < 3  -> take all   truckSize 3 -> 1   maxProfit = 3 + 4 = 7
//   [3,1]: 3 < 1? no -> take 1 box   maxProfit = 7 + 1 * 1 = 8   truckSize = 0
//   return 8
//
// COMPLEXITY
//   Time : O(n log n) - the sort (n = boxTypes.length); the walk is O(n).
//   Space: O(n) in the worst case - the comparator sort of an object array (int[][]) may use
//          a merge buffer; nothing else is allocated.
//
// GOTCHAS
//   - Arrays.sort reorders the caller's boxTypes array in place.
//   - The test is a strict `<`. When a type fits exactly (boxInfo[0] == truckSize) it goes to
//     the else branch, which adds truckSize * units - the same value as taking the whole type.
//   - The loop does not stop when the truck is full; the remaining types just add 0.
//   - Integer.compare(b[1], a[1]) sorts descending and, unlike a subtraction comparator,
//     cannot overflow.
//   - If there are fewer boxes than places, the truck simply leaves space unused.
//
// RELATED
//   - LC 2279 Maximum Bags With Full Capacity of Rocks - another "sort, then fill greedily" task.
//   - GFGFractionalKnapsack.java (this folder) - the same "best value per unit first" idea,
//     with arbitrary weights and cut-able items; here every box weighs 1.
// ============================================================================
public class LC1710MaximumUnitsOnATruck {
    public static void main(String[] args) {
        // Example 1 - expected output: 8
        int[][] boxTypes = {{1,3},{2,2},{3,1}};
        int truckSize = 4;
        System.out.println(maximumUnits(boxTypes, truckSize));
    }

    // Returns the most units that fit on the truck.
    public static int maximumUnits(int[][] boxTypes, int truckSize) {
        // step 1: box types with the most units per box first
        Arrays.sort(boxTypes, (a, b) -> Integer.compare(b[1], a[1]));
        int maxProfit = 0;
        for(int[] boxInfo : boxTypes){
            // the whole type fits -> load all of it
            if(truckSize > 0 && boxInfo[0] < truckSize ){
                truckSize = truckSize - boxInfo[0];
                maxProfit = maxProfit + boxInfo[0] * boxInfo[1];
            }else {
                // only part of the type fits (or the truck is already full and this adds 0)
                maxProfit = maxProfit + truckSize * boxInfo[1];
                truckSize = 0;
            }
        }
        return maxProfit;
    }
    // not used by the solution above
    static class BoxInfo{
        int numberOfBoxes;
        int numberOfUnitsPerBox;
        BoxInfo(int numberOfBoxes, int numberOfUnitsPerBox){
            this.numberOfBoxes = numberOfBoxes;
            this.numberOfUnitsPerBox = numberOfUnitsPerBox;
        }
    }
}
