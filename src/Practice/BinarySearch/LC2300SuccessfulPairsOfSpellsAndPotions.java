package Practice.BinarySearch;

import java.util.Arrays;

// ============================================================================
// LC 2300. Successful Pairs of Spells and Potions   [Medium]
// https://leetcode.com/problems/successful-pairs-of-spells-and-potions/
// Topics: Array, Two Pointers, Binary Search, Sorting
// ============================================================================
//
// PROBLEM
//   spells[i] is the strength of spell i and potions[j] the strength of potion j (all
//   positive). A spell and a potion form a successful pair when spell * potion >= success.
//   For EVERY spell return how many potions make a successful pair with it: the answer is
//   an array pairs with pairs[i] = that count for spells[i].
//
// EXAMPLES
//   spells = [5,1,3], potions = [1,2,3,4,5], success = 7  ->  [4,0,3]
//       spell 5 gives 5,10,15,20,25 -> 4 reach 7;  spell 1 gives 1..5 -> none;
//       spell 3 gives 3,6,9,12,15 -> 3 reach 7
//   spells = [3,1,2], potions = [8,5,8], success = 16      ->  [2,0,2]
//
// CONSTRAINTS
//   n == spells.length, m == potions.length,   1 <= n, m <= 10^5,
//   1 <= spells[i], potions[i] <= 10^5,   1 <= success <= 10^10
//
// INTUITION
//   Fix one spell s. The products s * potion grow with the potion's strength, so if a
//   potion is good enough, every STRONGER potion is too. With the potions sorted, the
//   successful ones therefore form a suffix of the array, and the answer for s is just
//   m - (index where that suffix starts). That index is found with a binary search, so
//   one sort of the potions pays for all n spells.
//
// APPROACHES IN THIS FILE
//   A. successfulPairs() - brute force: multiply every spell with every potion and count
//                          - O(n * m) time, O(1) extra space
//   B. betterApproach()  - sort the potions, then binary search the first successful
//                          potion for each spell - O((n + m) log m) time   <- main() runs this
//
// APPROACH (as implemented below, method B)
//   1. Arrays.sort(potions) - sorts the caller's array in place; the spells stay as they
//      are, because result[i] must belong to spells[i].
//   2. For each spell i, idx = binarySearch(spells[i], potions, success): the first index
//      whose product with the spell reaches success (potions.length when none does).
//      binarySearch keeps left / right around the boundary: product < success -> left =
//      mid + 1, otherwise right = mid - 1; when the loop ends, left is that first index.
//   3. result[i] = potions.length - idx  (everything from idx to the end succeeds).
//
// DRY RUN   spells = [5,1,3], potions = [1,2,3,4,5] (already sorted), success = 7
//   spell 5: mid=2 -> 3*5=15 ok, right=1;  mid=0 -> 5 < 7, left=1;  mid=1 -> 10 ok,
//            right=0;  stop with left=1  -> result[0] = 5 - 1 = 4
//   spell 1: 3<7 left=3;  4<7 left=4;  5<7 left=5;  stop with left=5 -> result[1] = 0
//   spell 3: mid=2 -> 9 ok, right=1;  mid=0 -> 3 < 7, left=1;  mid=1 -> 6 < 7, left=2;
//            stop with left=2 -> result[2] = 5 - 2 = 3
//   result = [4,0,3]
//
// COMPLEXITY
//   Time : A: O(n * m).   B: O(m log m) for the sort + n binary searches of O(log m) each
//          = O((n + m) log m)
//   Space: O(1) besides the result array (the sort is in place; its own stack is O(log m))
//
// GOTCHAS
//   - A product can reach 10^5 * 10^5 = 10^10, which overflows int, so the code multiplies
//     as long; success is a long too (it goes up to 10^10).
//   - Only the potions are sorted. Sorting the spells would scramble which answer belongs
//     to which spell.
//   - binarySearch returns potions.length when no potion is strong enough, which gives a
//     count of 0, so no special case is needed.
//   - Method B sorts the array that was passed in (a side effect for the caller).
//
// RELATED
//   - LC 2389 Longest Subsequence With Limited Sum - sort once, then binary search the
//     answer for each query, exactly like the spells here.
//   - LC 2410 Maximum Matching of Players With Trainers - another problem that starts by
//     sorting two arrays and then matches them up.
// ============================================================================
public class LC2300SuccessfulPairsOfSpellsAndPotions {
    public static void main(String[] args) {

        // LC example 1 - prints Successful Pairs: [4, 0, 3]
        int[] spells = {5, 1, 3};
        int[] potions = {1, 2, 3, 4, 5};
        long success = 7;

        int[] result = betterApproach(spells, potions, success);

        // print the counts as [a, b, c]
        System.out.print("Successful Pairs: [");
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i]);
            if (i < result.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    // Approach A: brute force - test every spell / potion pair. O(n * m)
    public static int[] successfulPairs(int[] spells, int[] potions, long success) {
        //spells - n
        // potions - m
        // success - atleast
        //return - n sized product array count whose size is greater than success

        long tempsuccess;
        int tempCount = 0;
        int[] result = new int[spells.length];
        for (int i = 0; i < spells.length; i++) {
            // count the potions that reach success together with spell i
            for (int j = 0; j < potions.length; j++) {
                // multiply as long: the product can be up to 10^10
                tempsuccess = (long)spells[i] * (long)potions[j];
                if (tempsuccess >= success){
                    tempCount++;
                }
            }
            result[i] = tempCount;
            // reset the counter for the next spell
            tempCount = 0;
        }
        return result;
    }

    // Approach B: sort the potions once, then for each spell binary search the first potion
    // that is strong enough. O((n + m) log m)   <- main() runs this one
    public static int[] betterApproach(int[] spells, int[] potions, long success) {
        //spells - n
        // potions - m
        // success - atleast
        //return - n sized product array count whose size is greater than success

        // sorted (in place): for any spell the successful potions are now a suffix
        Arrays.sort(potions);

        int spellCount = spells.length;
        int[] result = new int[spellCount];

        for (int i = 0; i < spellCount; i++) {
            // idx = first potion that reaches success with this spell (potions.length if none)
            int idx = binarySearch(spells[i], potions, success);
            // every potion from idx to the end is successful
            result[i] = potions.length - idx;
        }
        return result;
    }

    // Returns the first index of the sorted potions whose product with spell is >= success,
    // or potions.length if there is none (a lower-bound binary search).
    public static int binarySearch(int spell, int[] potions, long success){
        int left = 0;
        int right = potions.length - 1;
        int mid;
        while(left <= right){
            mid = left + (right - left) / 2;
            // multiply as long: potion * spell can be up to 10^10
            long product = (long) potions[mid] * spell;
            if(product < success){
                // too weak: the first strong-enough potion lies to the right
                left = mid + 1;
            }else {
                // strong enough: an earlier potion might be too, so keep looking left
                right = mid - 1;
            }
        }
        // left = first index that is strong enough
        return left;
    }
}
