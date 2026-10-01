package Practice.Arrays;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// ============================================================================
// LC 3069. Distribute Elements Into Two Arrays I   [Easy]
// https://leetcode.com/problems/distribute-elements-into-two-arrays-i/
// Topics: Array, Two Pointers, Simulation
// ============================================================================
//
// PROBLEM
//   Deal the (all distinct) elements of nums, in order, into two lists arr1 and arr2.
//   The first element goes to arr1 and the second to arr2. Every later element goes to arr1
//   if arr1's last element is greater than arr2's last element, otherwise to arr2.
//   Return arr1 followed by arr2 as one array.
//
// EXAMPLES
//   nums = [2,1,3]    ->  [2,3,1]     arr1 = [2], arr2 = [1]; 2 > 1, so 3 joins arr1
//   nums = [5,4,3,8]  ->  [5,3,4,8]   arr1 = [5,3] (5 > 4 sent 3 there), then 3 < 4 sends
//                                     8 to arr2 = [4,8]
//
// CONSTRAINTS
//   3 <= n <= 50,   1 <= nums[i] <= 100,   all elements of nums are distinct
//
// INTUITION
//   This is a plain simulation - follow the rule exactly. Only the LAST element of each list
//   ever matters for the decision, so nothing else needs to be tracked. Since the values
//   are distinct, the two last elements are never equal: the choice is always a clean
//   "arr1's last is bigger" versus "arr2's last is bigger".
//
// APPROACH (as implemented below)
//   1. arr1 = [nums[0]] and arr2 = [nums[1]] (the two forced first moves); i = 2 is the
//      next index to place.
//   2. While i < nums.length: if the last element of arr1 is greater than the last element
//      of arr2, add nums[i] to arr1, otherwise add it to arr2. Then i++.
//   3. arr1.addAll(arr2) appends arr2 to arr1 - that is the concatenation.
//   4. Convert the List<Integer> to an int[] with a stream and return it.
//
// DRY RUN   nums = [5,4,3,8]
//   start  arr1 = [5], arr2 = [4], i = 2
//   i=2    last(arr1) = 5 > last(arr2) = 4  -> arr1 = [5,3]
//   i=3    last(arr1) = 3 > last(arr2) = 4? no  -> arr2 = [4,8]
//   addAll -> arr1 = [5,3,4,8]   (main()'s [2,1,3]: only i=2 runs, 2 > 1 -> [2,3,1])
//
// COMPLEXITY
//   Time : O(n) - one pass (ArrayList.add is O(1) amortised), then addAll and the stream
//          copy are O(n) each
//   Space: O(n) - the two lists hold n elements in total, plus the returned array
//
// GOTCHAS
//   - The statement counts from 1 (nums[1], nums[2]); the code counts from 0, so the two
//     forced placements use nums[0] and nums[1] and the loop starts at i = 2.
//   - This is the easy version: one comparison of two last elements. LC 3072 is the hard
//     sequel with a different rule.
//   - arr1.addAll(arr2) changes arr1 in place; after it, arr1 is the whole result.
//   - A List<Integer> cannot simply be returned as int[], hence the stream with
//     mapToInt(Integer::intValue).toArray().
//
// RELATED
//   - LC 3072 Distribute Elements Into Two Arrays II - the harder sequel: the same dealing
//     into two arrays, but the rule compares how many elements of each array are greater
//     than nums[i].
//
// STATUS
//   Correct, but main() calls resultArray without printing the result
//   (it returns [2, 3, 1] for the example in main()).
// ============================================================================
public class LC3069DistributeElementsIntoTwoArraysI {
    public static void main(String[] args) {
        // Example 1 - the result is not printed; it is [2, 3, 1]
        int[] nums = {2,1,3};
        resultArray(nums);
    }

    // Deals nums into arr1 / arr2 by the "last element" rule and returns arr1 + arr2.
    public static int[] resultArray(int[] nums) {
        List<Integer> arr1 = new ArrayList<>();
        List<Integer> arr2 = new ArrayList<>();
        int i = 2;
        // the first two elements are placed without any comparison
        arr1.add(nums[0]);
        arr2.add(nums[1]);
        while(i < nums.length){
            // compare the tails: the list whose last element is bigger receives nums[i]
            if(arr1.get(arr1.size() - 1) > arr2.get(arr2.size() - 1)){
                arr1.add(nums[i]);
            }else {
                arr2.add(nums[i]);
            }
            i++;
        }
        // concatenate: arr2 goes after arr1, then copy the list into an int[]
        arr1.addAll(arr2);
        return arr1.stream().mapToInt(Integer::intValue).toArray();
    }
}
