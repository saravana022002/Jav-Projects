package Practice.Arrays;

// ============================================================================
// LC 1550. Three Consecutive Odds   [Easy]
// https://leetcode.com/problems/three-consecutive-odds/
// Topics: Array
// ============================================================================
//
// PROBLEM
//   Given an integer array arr, return true if somewhere in it three odd numbers sit
//   right next to each other (three neighbouring positions), otherwise false.
//
// EXAMPLES
//   arr = [2,6,4,1]                ->  false   only one odd number at all
//   arr = [1,2,34,3,4,5,7,23,12]   ->  true    5, 7, 23 are neighbours and all odd
//
// CONSTRAINTS
//   1 <= arr.length <= 1000,   1 <= arr[i] <= 1000
//
// INTUITION
//   Keep the length of the current run of odd numbers. An odd number extends the run,
//   an even number breaks it back to 0. The answer is true the moment the run hits 3.
//
// APPROACH (as implemented below)
//   1. count = 0 (length of the current run of odds).
//   2. For each value j: odd -> count++, even -> count = 0.
//   3. If count == 3, return true right away.
//   4. After the loop, return false.
//
// DRY RUN   arr = [1,2,34,3,4,5,7,23,12]
//   1 odd -> 1,  2 even -> 0,  34 even -> 0,  3 odd -> 1,  4 even -> 0,
//   5 odd -> 1,  7 odd -> 2,  23 odd -> 3  => return true (12 is never read)
//
// COMPLEXITY
//   Time : O(n) - one pass, stops early on success
//   Space: O(1) - one counter
//
// GOTCHAS
//   - j % 2 == 1 only works for non-negative values: in Java -3 % 2 is -1. That is fine
//     here (arr[i] >= 1); for negatives use j % 2 != 0.
//   - The loop variable j is a VALUE from arr, not an index.
//
// RELATED
//   - LC 485 Max Consecutive Ones - the same "grow the run, reset on a break" counter
//     (LC485MaxConsecutiveOnesV1.java / V2.java in this folder).
// ============================================================================
public class LC1550ThreeConsecutiveOdds {
    public static void main(String[] args) {
        // Example 2 - 5, 7, 23 are three odds in a row. Expected output: true
        int[] arr = {1,2,34,3,4,5,7,23,12};
        System.out.println(threeConsecutiveOdds(arr));
    }

    // Returns true as soon as three odd values appear in a row.
    public static boolean threeConsecutiveOdds(int[] arr) {
        // length of the current run of odd numbers
        int count = 0;
        for (int j : arr) {
            // an odd value extends the run, an even value breaks it
            if (j % 2 == 1)
                count++;
            else
                count = 0;
            if (count == 3)
                return true;
        }
        return false;
    }
}
