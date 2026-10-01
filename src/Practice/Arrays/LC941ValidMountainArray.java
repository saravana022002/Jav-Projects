package Practice.Arrays;

// ============================================================================
// LC 941. Valid Mountain Array   [Easy]
// https://leetcode.com/problems/valid-mountain-array/
// Topics: Array
// ============================================================================
//
// PROBLEM
//   Decide whether arr is a mountain: it has at least 3 elements, climbs strictly up to one
//   peak, and then goes strictly down to the end. In symbols, for some peak index i with
//   0 < i < arr.length - 1:  arr[0] < arr[1] < ... < arr[i] > ... > arr[arr.length - 1].
//   Return true if it is, false otherwise.
//
// EXAMPLES
//   arr = [2,1]      ->  false   too short, there is no climb before the descent
//   arr = [3,5,5]    ->  false   the step 5,5 is flat - both slopes must be strict
//   arr = [0,3,2,1]  ->  true    up 0,3 then down 3,2,1
//
// CONSTRAINTS
//   1 <= arr.length <= 10^4,   0 <= arr[i] <= 10^4
//
// INTUITION
//   Look at the steps between neighbours; each step is up, down or flat. A mountain is
//   one or more up steps followed by one or more down steps, with no flat step anywhere.
//   So a single pass can reject the bad patterns as soon as they appear: a flat step, a
//   down step before any up step, or an up step after a down step. If nothing was
//   rejected, it is a mountain exactly when both an up step and a down step occurred.
//
// APPROACH (as implemented below)
//   1. isValid = true (no flat step so far), isIncrease = false, isDecrease = false.
//   2. For i = 1 .. n-1 compare arr[i-1] with arr[i]:
//        equal   -> isValid = false (a flat step can never be part of a mountain)
//        up      -> isIncrease = true; if a descent already began (isDecrease), return false
//        down    -> isDecrease = true; if no climb came first (!isIncrease), return false
//   3. Return isValid && isIncrease && isDecrease.
//
// DRY RUN   arr = [0,3,2,1]
//   i=1: 0 < 3  -> isIncrease = true
//   i=2: 3 > 2  -> isDecrease = true   (a climb came first, fine)
//   i=3: 2 > 1  -> isDecrease stays true
//   end: isValid (no flat step) && isIncrease && isDecrease = true
//   main()'s [2,0,2]: i=1: 2 > 0 is a down step with no climb before it -> returns false.
//
// COMPLEXITY
//   Time : O(n) - one pass over the neighbouring pairs
//   Space: O(1) - three boolean flags
//
// GOTCHAS
//   - No separate length check is needed: with fewer than 3 elements the array cannot have
//     both an up step and a down step, so [1], [1,2] and [2,1] all end up false.
//   - A flat step does not return immediately; it only sets isValid = false, and the final
//     line reports it.
//   - The peak cannot be at either end: a pure climb [1,2,3] and a pure descent [3,2,1]
//     are both false.
//   - The other common way is two pointers: climb from the left, climb from the right, and
//     check that they stop on the same index, which is neither the first nor the last.
//
// RELATED
//   - LC 845 Longest Mountain in Array - find the longest mountain-shaped subarray.
//   - LC 1671 Minimum Number of Removals to Make Mountain Array - make an array a mountain
//     by removing as few elements as possible.
// ============================================================================
public class LC941ValidMountainArray {
    public static void main(String[] args) {
        // [2,0,2] starts by going down, so it is not a mountain. Prints false
        int[] arr = {2,0,2};
        System.out.println(validMountainArray(arr));
    }
    // Returns true when arr climbs strictly to a peak and then falls strictly.
    public static boolean validMountainArray(int[] arr) {
        // isIncrease / isDecrease = has an up / down step been seen yet?
        boolean isValid = true;
        boolean isIncrease = false;
        boolean isDecrease = false;
        for (int i = 1; i < arr.length; i++) {
            // flat step: the slopes must be strict
            if(arr[i - 1] == arr[i]){
                isValid = false;
            }
            // up step
            if(arr[i - 1] < arr[i]){
                isIncrease = true;
                // rising again after the descent began -> a second peak
                if(isDecrease){
                    return false;
                }
            }
            // down step
            if(arr[i - 1] > arr[i]){
                isDecrease = true;
                // the descent began without any climb before it
                if(!isIncrease){
                    return false;
                }
            }
        }
        // no flat step, and both a climb and a descent happened
        return isValid && isIncrease && isDecrease;
    }
}
