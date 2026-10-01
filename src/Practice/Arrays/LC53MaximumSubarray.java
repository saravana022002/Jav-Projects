package Practice.Arrays;


// ============================================================================
// LC 53. Maximum Subarray   [Medium]
// https://leetcode.com/problems/maximum-subarray/
// Topics: Array, Divide and Conquer, Dynamic Programming
// ============================================================================
//
// PROBLEM
//   Among all contiguous slices (subarrays) of nums, find the one with the largest sum and
//   return that sum. A slice must contain at least one element, so if every number is
//   negative the answer is the largest (least negative) single element.
//   Follow-up: after the O(n) solution, also try divide and conquer.
//
// EXAMPLES
//   nums = [-2,1,-3,4,-1,2,1,-5,4]  ->  6    the slice [4,-1,2,1]
//   nums = [1]                      ->  1
//   nums = [5,4,-1,7,8]             ->  23   the whole array
//
// CONSTRAINTS
//   1 <= nums.length <= 10^5,   -10^4 <= nums[i] <= 10^4
//
// INTUITION
//   Every slice ends somewhere. The best slice that ENDS at index i is either nums[i] alone
//   (start fresh here) or nums[i] glued onto the best slice that ended at i - 1 (extend it).
//   Extending only pays off when that earlier best is positive; if it is negative it would
//   only drag the sum down, so we drop it and restart. Keep that single number, currentMax,
//   while walking the array; the answer is the largest currentMax met on the way
//   (Kadane's algorithm).
//
// APPROACHES IN THIS FILE
//   A. bruteForce(input)         - for every start index, re-add the elements for every end
//                                  index with an inner loop        - O(n^3) time, O(1) space
//   B. betterApproach(input)     - for every start, grow a running sum one element at a time
//                                                                   - O(n^2) time, O(1) space
//   C. optimalApproach(input, n) - Kadane, one pass                  - O(n) time,   O(1) space
//   main() runs A, B and C on example 1 and prints 6 three times. (approach1 and
//   recursivelyFindTheMaxSumInArray are an abandoned attempt that is never called.)
//   (APPROACH and DRY RUN below describe C.)
//
// APPROACH (as implemented below, optimalApproach)
//   1. globalMax = Integer.MIN_VALUE (best answer so far); currentMax = 0 (best slice ending
//      at the previous index - starting at 0 is harmless because of step 2).
//   2. For i = 0 .. n-1: currentMax = Math.max(input[i], currentMax + input[i])
//      i.e. start a new slice at i, or extend the one that ended at i - 1.
//   3. globalMax = Math.max(globalMax, currentMax).
//   4. Return globalMax. (n is just input.length, passed in by the caller.)
//
// DRY RUN   input = [-2,1,-3,4,-1,2,1,-5,4]
//   i    x    currentMax = max(x, previous + x)    globalMax
//   0   -2    max(-2, 0 + -2)  = -2                   -2
//   1    1    max( 1, -2 + 1)  =  1                    1
//   2   -3    max(-3, 1 + -3)  = -2                    1
//   3    4    max( 4, -2 + 4)  =  4                    4
//   4   -1    max(-1, 4 + -1)  =  3                    4
//   5    2    max( 2, 3 + 2)   =  5                    5
//   6    1    max( 1, 5 + 1)   =  6                    6
//   7   -5    max(-5, 6 + -5)  =  1                    6
//   8    4    max( 4, 1 + 4)   =  5                    6          -> returns 6
//
// COMPLEXITY
//   C: Time O(n) - one pass;  Space O(1) - two variables
//   B: Time O(n^2) - n start indexes, each extended across up to n ends;  Space O(1)
//   A: Time O(n^3) - every (start, end) pair re-adds its elements from scratch;  Space O(1)
//
// GOTCHAS
//   - All-negative input: the answer is the largest single element, not 0 ([-3,-1,-2] -> -1).
//     That is why globalMax starts at Integer.MIN_VALUE and currentMax is compared with
//     input[i] itself, never clamped to 0.
//   - The slice must be non-empty, so "take nothing" is never an option.
//   - Only the sum is returned; Kadane as written does not remember where the best slice is.
//
// RELATED
//   - LC 121 Best Time to Buy and Sell Stock - the best profit is the best-sum run of daily
//     price changes (LC121BestTimeToBuyAndSellStock.java in this folder).
//   - LC 152 Maximum Product Subarray - the same "extend or restart" idea, for products.
//
// STATUS
//   The three methods main() calls are correct. approach1 / recursivelyFindTheMaxSumInArray
//   are dead code (private, never called, the call in main() is commented out): the
//   recursion only tracks sums of slices that start at index 0 and needs about n! steps, so
//   on example 1 it would give 2 instead of 6.
//   Also: main() prints the label "better approach:" for the third result, which is the
//   optimal (Kadane) one.
// ============================================================================
public class LC53MaximumSubarray{
    public static void main(String[] args) {
        // Example 1 - expected output (all three approaches agree): 6
        int[] input = {-2,1,-3,4,-1,2,1,-5,4};
        int max = bruteForce(input);
        System.out.println("normal approach:"+max);

//        this is not the approach
//        int [] sumLocal = approach1(input);
//        System.out.println("recursive approach:"+sumLocal[1]);
        int nsqComplexity = betterApproach(input);
        System.out.println("better approach:"+nsqComplexity);

        // this is the O(n) Kadane call, although the label says "better approach"
        int nComplexity = optimalApproach(input, input.length);
        System.out.println("better approach:"+nComplexity);

    }

    // Approach B: for each start index i, extend a running sum over j = i.. and keep the
    // best sum seen. O(n^2) time, O(1) space.
    private static int betterApproach(int[] input) {
        int globalMax = Integer.MIN_VALUE;
        for (int i = 0; i < input.length; i++) {
            int sum = 0;
            for(int j = i; j < input.length; j++){
                // sum = input[i] + ... + input[j]
                sum += input[j];
                globalMax = Math.max(globalMax, sum);
            }
        }
        return globalMax;
    }


    // Approach C: Kadane's algorithm. O(n) time, O(1) space. n is the length of input.
    public static int optimalApproach(int[] input, int n) {
        int globalMax = Integer.MIN_VALUE;
        // best sum of a slice that ends at the current index
        int currentMax = 0;

        for (int i = 0; i < n; i++) {
            // restart at input[i], or extend the slice that ended at i - 1, whichever is larger
            currentMax = Math.max(input[i], currentMax + input[i]);
            // the best slice overall ends somewhere, so keep the largest value seen
            globalMax = Math.max(globalMax, currentMax);
        }
        return globalMax;
    }


    // Abandoned attempt (never called - see STATUS): ans[0] is a running sum, ans[1] the
    // best running sum seen.
    private static int[] approach1(int[] input) {
        int[] ans = new int[2];
        ans[1] = Integer.MIN_VALUE;
        recursivelyFindTheMaxSumInArray(0, input.length , input, ans);
        return ans;
    }

    // Part of the abandoned attempt: it only ever sums from startingAddress, i.e. it only
    // sees prefix sums, and it recurses n times per level (about n! calls in total).
    private static int[] recursivelyFindTheMaxSumInArray(int startingAddress, int endingAddress, int[] input, int[] ans) {
        if(endingAddress == startingAddress){
            ans[0] = 0;
            return ans;
        }
        for (int i = startingAddress; i < endingAddress; i++) {
            ans = recursivelyFindTheMaxSumInArray(startingAddress, endingAddress - 1, input, ans);
            ans[0] = ans[0] + input[endingAddress - 1];
            if (ans[0] > ans[1]) {
                ans[1] = ans[0];
            }
        }
        return ans;

    }
//{-2,1,-3,4,-1,2,1,-5,4}
    // Approach A: brute force - for each start index take the best slice starting there.
    // O(n^3) time, O(1) space.
    public static int bruteForce(int[] input){
        int localMax = 0;
        int globalMax = Integer.MIN_VALUE;
        int startingIndex = 0;
        for (int i = 0; i < input.length; i++) {
            // best sum among all slices that start at startingIndex (startingIndex == i)
            localMax = getMaxFormStaringIndex(input, startingIndex);
            if(localMax > globalMax){
                 globalMax = localMax;
            }
            startingIndex++;
        }
        return globalMax;
    }

    // Best sum among the slices input[startingIndex..i], for every end index i.
    private static int getMaxFormStaringIndex(int[] input, int startingIndex) {
        int tempMax = 0;
        int max = Integer.MIN_VALUE;
        for (int i = startingIndex; i < input.length; i++) {
            // re-add the whole slice from its start (this inner loop makes it cubic)
            for (int j = startingIndex; j <= i; j++) {
                tempMax = tempMax + input[j];
            }
            if(tempMax > max){
                max = tempMax;
            }
            tempMax = 0;
        }
        return max;
    }
}
