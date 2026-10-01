package Practice.TwoPointers;

import java.util.Arrays;

// ============================================================================
// LC 287. Find the Duplicate Number   [Medium]
// https://leetcode.com/problems/find-the-duplicate-number/
// Topics: Array, Two Pointers, Binary Search, Bit Manipulation, Pigeonhole Principle,
//         Floyd's Cycle Finding Algorithm
// ============================================================================
//
// PROBLEM
//   nums holds n + 1 integers, each between 1 and n. Exactly one value is repeated (it may
//   appear two or more times); every other value appears once. Return the repeated value.
//   The official rules: do not modify nums and use only constant extra space.
//
// EXAMPLES
//   nums = [1,3,4,2,2]  ->  2
//   nums = [3,1,3,4,2]  ->  3
//   nums = [3,3,3,3,3]  ->  3    the repeated value may occur more than twice
//
// CONSTRAINTS
//   1 <= n <= 10^5,   nums.length == n + 1,   1 <= nums[i] <= n,   exactly one value repeats
//
// INTUITION
//   Why a duplicate must exist: n + 1 numbers cannot fit into n different values (pigeonhole).
//   Now read the array as a set of arrows: from index i you jump to index nums[i]. Every value
//   is at least 1, so nothing ever jumps back to index 0, and every jump lands on an index in
//   1..n. Starting at index 0 you therefore walk a "tail" and then run round in a cycle. The
//   duplicate value v is the index that two different indices point to (one from the tail, one
//   from the cycle), so v is exactly where the cycle starts. Finding the start of a cycle is
//   Floyd's tortoise and hare: phase 1 - the turtle takes one step at a time, the hare two,
//   and they meet somewhere inside the cycle; phase 2 - restart the hare at index 0 and move
//   both one step at a time: they meet again at the start of the cycle (the distance from 0
//   to the start equals the distance from the meeting point on to the start, going round the
//   cycle, possibly after some whole laps).
//
// APPROACHES IN THIS FILE
//   A. findDuplicateWithFrequency()    - mark each value in a boolean-like freq array, return
//                                        the first value met twice - O(n) time, O(n) space
//   B. findDuplicateWithSorting()      - sort, then look for two equal neighbours
//                                        - O(n log n) time; sorts the caller's array
//   C. findDuplicateWithNoExtraSpace() - Floyd's cycle detection on the "jump" arrows
//                                        - O(n) time, O(1) space, array untouched
//   main() runs all three on the same array; only C follows both official rules.
//
// APPROACH (as implemented below, method C)
//   1. hare = 0, turtle = 0.
//   2. Phase 1 (do-while): turtle = arr[turtle] (one jump), hare = arr[arr[hare]] (two jumps);
//      repeat until turtle == hare. They meet inside the cycle.
//   3. hare = 0. Phase 2 (do-while): turtle = arr[turtle], hare = arr[hare] (one jump each);
//      repeat until turtle == hare.
//   4. Return turtle: the start of the cycle, which is the duplicate value.
//
// DRY RUN   C on arr = [1,3,4,2,2]   (the jumps are 0->1, 1->3, 3->2, 2->4, 4->2)
//   phase 1   turtle=1 hare=3  |  turtle=3 hare=4  |  turtle=2 hare=4  |  turtle=4 hare=4 meet
//   phase 2   turtle starts at 4, hare at 0:
//             turtle=2 hare=1  |  turtle=4 hare=3  |  turtle=2 hare=2 meet
//   return 2.   A: marks 1, 3, 4, 2 and then meets 2 again -> 2.   B: sorted [1,2,2,3,4], the
//   neighbours 2 and 2 are equal -> 2.   (The cycle is 2 -> 4 -> 2; both 3 and 4 jump to 2.)
//
// COMPLEXITY
//   A: Time O(n) - one pass.  Space O(n) for freq
//   B: Time O(n log n) for the sort (the scan is O(n)).  Space O(1) extra, but nums is reordered
//   C: Time O(n) - each phase walks at most a tail plus a few laps of the cycle.  Space O(1)
//
// GOTCHAS
//   - The walk must start at index 0: no value equals 0, so index 0 is never inside a cycle,
//     and the walk is guaranteed to enter one.
//   - Both phases are do-while loops. In phase 1, turtle and hare start equal, so a plain
//     while (turtle != hare) would never run.
//   - Values are used as indices, which is safe because 1 <= nums[i] <= n < arr.length.
//   - A and B return 0 (a value that cannot be the answer) if no duplicate is found.
//   - B sorts the caller's array. In main(), C therefore runs on the sorted array
//     [1,2,2,3,4], which is still a valid input, so it still returns 2.
//
// STATUS
//   All three methods return the right value (checked on random valid arrays). Only C
//   satisfies the LeetCode rules: A uses O(n) extra space and B modifies the array.
//
// RELATED
//   - LC 142 Linked List Cycle II - the same two-phase tortoise and hare on a real linked list.
//   - LC 645 Set Mismatch - an array of 1..n values with one repeated and one missing number.
//   - LC 268 Missing Number - the companion problem of finding a missing value instead.
// ============================================================================
public class LC287FindTheDuplicateNumber {
    public static void main(String[] args) {
        // Example 1: the duplicate is 2, so all three lines print 2. findDuplicateWithSorting
        // sorts arr in place, so the last call runs on the sorted array.
        int[] arr = {1,3,4,2,2};
        int duplicateWithFrequency = findDuplicateWithFrequency(arr);
        int dupWithSorting = findDuplicateWithSorting(arr);
        int dupWithNoExtraSpace = findDuplicateWithNoExtraSpace(arr);


        System.out.println("The Duplicate Element with frequency,"+duplicateWithFrequency);
        System.out.println("The Duplicate Element with sorting,"+dupWithSorting);
        System.out.println("The Duplicate Element with NoExtraSpace,"+dupWithNoExtraSpace);
    }

    // Approach C: Floyd's cycle detection. Index i "points to" index arr[i]; the duplicate value
    // is where the cycle that starts from index 0 begins. O(n) time, O(1) space
    private static int findDuplicateWithNoExtraSpace(int[] arr) {

        int hare = 0;
        int turtle = 0;
        // phase 1: turtle moves 1 step, hare moves 2; they meet inside the cycle
        do {
            turtle = arr[turtle];
            hare = arr[arr[hare]];
        } while (turtle != hare);

        // phase 2: restart the hare at index 0; with equal speeds they meet at the cycle start
        hare = 0;

        do {
            turtle = arr[turtle];
            hare = arr[hare];
        } while (turtle != hare);

        return turtle;
    }

    // Approach A: freq[v] becomes 1 when v is first seen; meeting it again means v is the
    // duplicate. O(n) time, O(n) extra space
    private static int findDuplicateWithFrequency(int[] arr) {
        int size = arr.length;
        int[] freq = new int[size+1];
        for (int i = 0; i < size ; i++){
            if(freq[arr[i]] == 0){
                freq[arr[i]]++;
            }else {
                return arr[i];
            }
        }
        return 0;
    }

    // Approach B: after sorting, equal values are neighbours. O(n log n); reorders arr
    private static int findDuplicateWithSorting(int[] arr) {
        int size = arr.length;
        Arrays.sort(arr);

        // compare each element with the one before it
        int comparingElement = arr[0];
        for (int i = 1; i < size; i++) {
            if(comparingElement == arr[i]){
                return comparingElement;
            }else {
                comparingElement = arr[i];
            }
        }
        return 0;
    }

}
