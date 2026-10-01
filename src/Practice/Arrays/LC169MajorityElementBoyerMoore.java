package Practice.Arrays;

import java.util.HashMap;
import java.util.Map;

// ============================================================================
// LC 169. Majority Element   [Easy]
// https://leetcode.com/problems/majority-element/
// Topics: Array, Hash Table, Divide and Conquer, Sorting, Counting,
//         Boyer-Moore Majority Vote Algorithm
// ============================================================================
//
// PROBLEM
//   Given an array nums of length n, return its majority element: the value that occurs
//   MORE than floor(n / 2) times. The input always has one, so no "not found" case.
//   Follow-up: do it in O(n) time and O(1) extra space.
//
// EXAMPLES
//   nums = [3,2,3]           ->  3   3 occurs 2 times, and 2 > floor(3/2) = 1
//   nums = [2,2,1,1,1,2,2]   ->  2   2 occurs 4 of 7 times
//
// CONSTRAINTS
//   1 <= n <= 5 * 10^4,   -10^9 <= nums[i] <= 10^9,   a majority element always exists
//
// INTUITION
//   Boyer-Moore voting: let every copy of the majority "fight" one different value, and
//   both drop out. The majority has more copies than all other values together, so it
//   is the one left standing. cnt counts the not-yet-cancelled copies of the current
//   candidate; a different value cancels one. When cnt falls to 0, the part read so far
//   has paired off completely - at most half of it was the majority - so the majority
//   is still a majority of the rest, and we simply restart with the next value.
//
// APPROACHES IN THIS FILE
//   A. findMajorityBrute(nums)   - count each value with a full scan, keep the most
//                                  frequent                      - O(n^2) time, O(1) space
//   B. findMajorityBetter(nums)  - HashMap value -> count, return the key > n/2
//                                                                - O(n) time,   O(n) space
//   C. findMajorityOptimal(nums) - Boyer-Moore voting            - O(n) time,   O(1) space
//                                  <- main() runs this one (A and B are commented out)
//   (APPROACH and DRY RUN below describe C.)
//
// APPROACH (as implemented below, findMajorityOptimal)
//   1. cnt = 0, element = nums[0] (the candidate).
//   2. For each num: if cnt == 0, num becomes the new candidate (element = num, cnt = 1)
//      and we move on to the next value.
//   3. Otherwise num == element -> cnt + 1 (one more vote), else cnt - 1 (one cancelled).
//   4. Return element. No second counting pass is needed because a majority is
//      guaranteed to exist.
//
// DRY RUN   nums = [2,2,1,1,1,2,2]  (example 2 - the candidate changes twice)
//   num       2  2  1  1  1  2  2
//   element   2  2  2  2  1  1  2
//   cnt       1  2  1  0  1  0  1     -> returns 2
//   main()'s array [1,3,1,1,4,1,1,5,1,1,6,2,2] keeps element = 1 all the way and prints 1.
//
// COMPLEXITY
//   C: Time O(n) - one pass;  Space O(1) - two variables
//   B: Time O(n) - one pass to count, one over the map;  Space O(n) - the map
//   A: Time O(n^2) - a full inner scan for every element;  Space O(1)
//
// GOTCHAS
//   - Boyer-Moore only returns the right value when a majority EXISTS. On [1,2,3] it
//     returns 3 (checked); without the guarantee, count the candidate in a second pass.
//   - "More than n/2" is strict; findMajorityBetter tests `> nums.length / 2`.
//   - findMajorityBetter's `return 0` is only a fallback, never reached on valid input.
//
// RELATED
//   - LC 229 Majority Element II - values occurring more than n/3 times: the same vote
//     with two candidates plus a checking pass (LC229MajorityElementII.java).
//   - LC 2780 Minimum Index of a Valid Split - first find the majority ("dominant")
//     element, then scan for the split.
//
// SEE ALSO
//   LC169MajorityElementHashMap.java - HashMap count that returns as soon as a count
//   passes n/2 (its own Boyer-Moore attempt skips nums[0] - see its STATUS)
// ============================================================================
public class LC169MajorityElementBoyerMoore {

    public static void main(String[] args) {
        // 1 occurs 7 times out of 13 (more than 13/2). Expected output: 1
        int[] nums = {1,3,1,1,4,1,1,5,1,1,6,2,2};
//        System.out.println(findMajorityBrute(nums));
//        System.out.println(findMajorityBetter(nums));
        System.out.println(findMajorityOptimal(nums));
    }

    // Approach C: Boyer-Moore voting. O(n) time, O(1) space.
    // Returns the majority element; relies on one existing.
    private static int findMajorityOptimal(int[] nums) {
        // cnt = votes of the current candidate `element` that are not cancelled yet
        int cnt = 0;
        int element = nums[0];
        for (int num : nums) {
            // everything so far cancelled out: this value becomes the new candidate
            if(cnt == 0){
                element = num;
                cnt ++;
                continue;
            }
            // same value -> one more vote; different value -> cancels one vote
            cnt = element == num ? cnt + 1 : cnt - 1;
        }
        return element;
    }

    // Approach A: brute force - count every value with a full scan and keep the most
    // frequent one. O(n^2) time, O(1) space.
    private static int findMajorityBrute(int[] nums) {

        int cnt = 0;
        int cntMax = 0;
        int cntMaxElement = 0;
        for (int i : nums) {
            // cnt = how many times the value i occurs in nums
            for (int j : nums) {
                if (j == i) {
                    cnt++;
                }
            }
            if (cnt > cntMax) {
                cntMax = cnt;
                cntMaxElement = i;
            }
            // reset for the next value
            cnt = 0;
        }
        return cntMaxElement;
    }

    // Approach B: count every value in a HashMap, then return the one seen more than
    // n/2 times. O(n) time, O(n) space.
    private static int findMajorityBetter(int[] nums) {
        Map<Integer, Integer> solns = new HashMap<>();
        for (int num : nums) {
            if (!solns.containsKey(num)) {
                solns.put(num, 1);
            } else {
                solns.put(num, solns.get(num) + 1);
            }
        }

        for(Map.Entry<Integer, Integer> entry : solns.entrySet()){
            if(entry.getValue() > nums.length / 2){
                return entry.getKey();
            }
        }
        // fallback only - a majority always exists on valid input
        return 0;
    }


}
