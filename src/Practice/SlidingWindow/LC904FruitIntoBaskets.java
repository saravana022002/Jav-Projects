package Practice.SlidingWindow;

import java.util.HashMap;
import java.util.Map;

// ============================================================================
// LC 904. Fruit Into Baskets   [Medium]
// https://leetcode.com/problems/fruit-into-baskets/
// Topics: Array, Hash Table, Sliding Window
// ============================================================================
//
// PROBLEM
//   Trees stand in a row and fruits[i] is the fruit type of tree i. You carry two baskets,
//   each holding a single fruit type (any amount of it). Choose a starting tree, then walk to
//   the right and take exactly one fruit from every tree, the start tree included. You must
//   stop at the first tree whose type fits in neither basket. Return the most fruit you can
//   collect. Put differently: the length of the longest stretch of neighbouring trees that
//   holds at most two different fruit types.
//
// EXAMPLES
//   fruits = [1,2,1]      ->  3    only two types, so every tree can be picked
//   fruits = [0,1,2,2]    ->  3    start at index 1 and take [1,2,2]; starting at index 0
//                                  stops at the 2 after only [0,1]
//   fruits = [1,2,3,2,2]  ->  4    start at index 1 and take [2,3,2,2]
//
// CONSTRAINTS
//   1 <= fruits.length <= 10^5,   0 <= fruits[i] < fruits.length
//
// INTUITION
//   The picked trees always form one unbroken stretch, and the rule "stop at a third type"
//   means that stretch may hold at most two distinct values. So the task is the longest
//   subarray with at most two distinct values. Keep a window fruits[left..right] together
//   with a count per type: grow it on the right, and when a third type sneaks in, move left
//   forward until one of the types has been squeezed out completely. The window is valid
//   again, and the answer is the widest valid window ever seen.
//
// APPROACH (as implemented below)
//   1. integerMap = fruit type -> how many of that type lie in the window fruits[left..right];
//      left = 0, max = 0.
//   2. For each right, add fruits[right] to the map (count + 1).
//   3. While integerMap.size() > 2 (three types in the window), drop fruits[left]: decrement
//      its count, remove the key when the count reaches 0 so that size() really shrinks,
//      then left++.
//   4. The window is valid again: max = max(max, right - left + 1).
//   5. Return max.
//
// DRY RUN   fruits = [1,2,3,2,2,3]   (the input main() uses)
//   right=0 (1)  map {1:1}       window [1]            len 1  max = 1
//   right=1 (2)  map {1:1,2:1}   window [1,2]          len 2  max = 2
//   right=2 (3)  three types -> drop the 1 (count 0, key removed), left = 1
//                map {2:1,3:1}   window [2,3]          len 2  max = 2
//   right=3 (2)  map {2:2,3:1}   window [2,3,2]        len 3  max = 3
//   right=4 (2)  map {2:3,3:1}   window [2,3,2,2]      len 4  max = 4
//   right=5 (3)  map {2:3,3:2}   window [2,3,2,2,3]    len 5  max = 5
//   return 5
//
// COMPLEXITY
//   Time : O(n) - right visits each tree once and left only ever moves forward, so every tree
//          enters and leaves the window at most once; each map operation is O(1) because the
//          map never holds more than 3 keys
//   Space: O(1) - at most 3 map entries (two types plus the one that triggers the shrinking)
//
// GOTCHAS
//   - The key must be REMOVED when its count hits 0. A key left at 0 would still be counted by
//     size() and the window would shrink further than necessary.
//   - One step of left does not always free a type, which is why this is a while loop and
//     not an if: for [2,1,2,2,3], admitting the 3 needs left to pass the first 2 (another 2
//     remains in the window) and then the 1.
//   - fruits has at least one tree, so the answer is always >= 1.
//
// STATUS
//   The method is correct. main() calls it but does not print the result (it returns 5).
//
// RELATED
//   - LC 3 Longest Substring Without Repeating Characters - the same grow-right, shrink-left
//     window, with "no repeated character" as the validity rule instead of "two types".
//   - LC 159 Longest Substring with At Most Two Distinct Characters - this exact problem on
//     a string (LC 340 generalises it to k distinct characters).
//   - LC 3477 Fruits Into Baskets II - shares only the name; it is a different problem (its
//     input also has a baskets array), so this window does not apply to it.
// ============================================================================
public class LC904FruitIntoBaskets {
    public static void main(String[] args) {
        // The best stretch is [2,3,2,2,3] (types 2 and 3), so totalFruit returns 5.
        // The result is not printed.
        int[] fruits = {1, 2, 3, 2, 2, 3};
        totalFruit(fruits);
    }

    // Returns the length of the longest stretch of trees with at most two fruit types
    public static int totalFruit(int[] fruits) {
        // fruit type -> how many of it are inside the window fruits[left..right]
        Map<Integer, Integer> integerMap = new HashMap<>();
        int left = 0;
        int max = 0;
        for (int right = 0; right < fruits.length; right++) {
            // take the fruit of tree `right` into the window
            integerMap.put(fruits[right],  integerMap.getOrDefault(fruits[right], 0) + 1);
            // a third type is in: shrink from the left until one type is gone completely
            while (integerMap.size() > 2){
                integerMap.put(fruits[left], integerMap.get(fruits[left]) - 1);
                // remove the key at 0, otherwise size() would keep counting a type that is gone
                if(integerMap.get(fruits[left]) == 0){
                    integerMap.remove(fruits[left]);
                }
                left++;
            }
            // the window is valid again (at most two types): record its length
            max = Math.max(max, right - left + 1);
        }
        return max;
    }
}
