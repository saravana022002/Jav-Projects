package Practice.SlidingWindow;

import java.util.*;

// ============================================================================
// LC 239. Sliding Window Maximum   [Hard]
// https://leetcode.com/problems/sliding-window-maximum/
// Topics: Array, Queue, Sliding Window, Heap (Priority Queue), Monotonic Queue,
//         Range Minimum/Maximum Query
// ============================================================================
//
// PROBLEM
//   A window of width k starts at the left end of nums and moves right one step at a time
//   until it touches the right end. For every position of the window, report the largest
//   number inside it. Return those maxima in order (n - k + 1 values).
//
// EXAMPLES
//   nums = [1,3,-1,-3,5,3,6,7], k = 3  ->  [3,3,5,5,6,7]
//        windows [1,3,-1] [3,-1,-3] [-1,-3,5] [-3,5,3] [5,3,6] [3,6,7]
//   nums = [1], k = 1                  ->  [1]
//
// CONSTRAINTS
//   1 <= nums.length <= 10^5,   -10^4 <= nums[i] <= 10^4,   1 <= k <= nums.length
//
// INTUITION
//   If a newer number is bigger than an older one in the window, the older one can never be
//   the maximum again: the newer one stays in the window at least as long and beats it.
//   So we can throw the older one away. What remains, from oldest to newest, has
//   DECREASING values - a "monotonic deque". Its front is the biggest value, i.e. the
//   window maximum. The front only has to leave when it slides out of the window, which
//   is why the deque stores indices, not values.
//
// APPROACHES IN THIS FILE
//   A. maxSlidingWindow() - queue of the window's values + a running max; when the max
//                           leaves, rescan the queue (reCompute) - O(n*k) worst case, buggy
//   B. maxWindow()        - monotonic deque of indices - O(n)   <- main() runs this one
//
// APPROACH (as implemented below, maxWindow)
//   For every index i:
//   1. While the front index is older than the window start (dq.peek() < i - k + 1), poll it.
//   2. While the value at the back is smaller than nums[i], pollLast() it - it is useless now.
//   3. offer(i) at the back.
//   4. Once the first window is complete (i >= k - 1), ans[i - k + 1] = nums[dq.peek()].
//
// DRY RUN   maxWindow, nums = [1,3,-1,-3,5,3,6,7], k = 3   (dq holds indices; values shown)
//   i  nums[i]  step 1: expire   step 2: drop smaller   dq after     answer
//   0    1      -                -                      [1]          -
//   1    3      -                1                      [3]          -
//   2   -1      -                -                      [3,-1]       ans[0] = 3
//   3   -3      -                -                      [3,-1,-3]    ans[1] = 3
//   4    5      3 (index 1)      -3, -1                 [5]          ans[2] = 5
//   5    3      -                -                      [5,3]        ans[3] = 5
//   6    6      -                3, 5                   [6]          ans[4] = 6
//   7    7      -                6                      [7]          ans[5] = 7
//   result [3,3,5,5,6,7]
//
// STATUS
//   Known issue in maxSlidingWindow (NOT the method main() runs): max starts at 0 instead of
//   Integer.MIN_VALUE. If the first window is all negative, that 0 - which is not in the
//   window - is reported until a value >= 0 enters. [-3,-2,-1], k = 2 returns [0, 0];
//   the correct answer is [-2, -1]. maxWindow is correct. The code is left unchanged on purpose.
//
// COMPLEXITY   (maxWindow)
//   Time : O(n) - every index is offered once and polled at most once, so the two while
//          loops do O(n) work in total over the whole run
//   Space: O(k) - the deque only holds indices of the current window (+ the n-k+1 answers)
//   (maxSlidingWindow: O(n*k) time - on a decreasing array the max leaves at every step and
//   reCompute rescans k-1 values each time.)
//
// GOTCHAS
//   - Expire the front BEFORE reading it, and write answers only from i = k - 1 onwards.
//   - Keep indices in the deque; with values alone you cannot tell when the front has left.
//   - Equal values are kept (strict <); the index check still expires them correctly.
//
// RELATED
//   - LC 1696 Jump Game VI - the same deque gives the best dp value among the last k indices.
//   - LC 2398 Maximum Number of Robots Within Budget - window max via this deque, plus a
//     running sum, inside a two-pointer window.
// ============================================================================
public class LC239SlidingWindowMaximum {
    public static void main(String[] args) {
        // k = 1: every window is a single element, so the output equals the input.
        // Expected output: [1, -1]
        int[] nums = {1,-1};
//        System.out.println(Arrays.toString(maxSlidingWindow(nums, 1)));
        System.out.println(Arrays.toString(maxWindow(nums, 1)));
    }

    // Approach A: queue of the window's values and a running max; rescan the queue only
    // when the value leaving the window is the max. O(n*k) worst case.
    // Known issue: max should start at Integer.MIN_VALUE, not 0 - see STATUS.
    public static int[] maxSlidingWindow(int[] nums, int k) {
        Queue<Integer> integers = new LinkedList<>();
        int max = 0;
        int i = 0;
        int[] ans = new int[nums.length - k + 1];
        // first window: fill the queue and take its max
        for (i = 0; i < k; i++) {
            integers.add(nums[i]);
            max = Math.max(nums[i], max);
        }
        ans[0] = max;
        for (int j = i; j < nums.length; j++) {
            // the value leaving the window was the max -> rescan the remaining k-1 values
            if(!integers.isEmpty() && integers.peek() == max){
                integers.poll();
                max = reCompute(integers);
            }else {
                // the max is still inside the window, just drop the oldest value
                integers.poll();
            }
            // the entering value may be the new max
            max = Math.max(max, nums[j]);
            integers.add(nums[j]);
            ans[j - k + 1] = max;
        }
        return ans;
    }




    // max of the values currently in the queue (Integer.MIN_VALUE when it is empty)
    public static int reCompute(Queue<Integer> integers){
        int max = Integer.MIN_VALUE;
        if(integers.isEmpty()){
            return max;
        }
        for (Integer i : integers){
            max = Math.max(i, max);
        }
        return max;
    }

    // Approach B: monotonic deque of indices (values decreasing front -> back). O(n)
    public static int[] maxWindow(int[] nums, int k){
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        Deque<Integer> dq = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            // step 1: the front index has slid out of the window [i-k+1 .. i]
            while (!dq.isEmpty() && dq.peek() < i - k + 1){
                dq.poll();
            }
            // step 2: smaller values at the back can never be a max again - nums[i] is
            // newer and bigger
            while (!dq.isEmpty() && nums[dq.peekLast()] < nums[i] ){
                dq.pollLast();
            }
            // step 3: i joins at the back
            dq.offer(i);
            // step 4: once the window is full, its max sits at the front
            if(i >= k - 1){
                ans[i - k + 1] = nums[dq.peek()];
            }
        }
        return ans;
    }
}
