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
//   Count the values while reading them. A value is the majority exactly when its count
//   goes past floor(n/2), and only one value can ever get that far - so the first value
//   to cross the line is the answer, and we can stop right there.
//
// APPROACHES IN THIS FILE
//   A. majorityElement(nums)        - HashMap count, early return - O(n) time, O(n) space
//                                     <- main() runs this one
//   B. majorityElementOptimal(nums) - Boyer-Moore voting attempt  - O(n) time, O(1) space
//                                     BUGGY (see STATUS), never called
//   (APPROACH and DRY RUN below describe A.)
//
// APPROACH (as implemented below, majorityElement)
//   1. map: value -> copies seen so far; length = n.
//   2. For each num: count = copies seen BEFORE this one (map.getOrDefault(num, 0)).
//   3. If count >= length / 2, this copy is number count + 1 > n/2 -> return num.
//   4. Otherwise store count + 1 and continue.
//   5. The final `return 0` is only a fallback; a majority always exists.
//
// DRY RUN   nums = [3,2,3]  (main), length / 2 = 1
//   num=3: count 0, 0 >= 1? no   => map {3=1}
//   num=2: count 0, no           => map {3=1, 2=1}
//   num=3: count 1, 1 >= 1 yes   => return 3
//
// STATUS
//   Known issue: majorityElementOptimal starts with count = 0 but loops from i = 1, so
//   nums[0] is never counted and the first vote is lost. It returns 2 for [3,2,3] and 1
//   for [2,2,1,1,1,2,2] (expected 3 and 2; checked). Starting the loop at i = 0 would fix
//   it. main() only calls majorityElement, which is correct.
//   The code is left unchanged on purpose.
//
// COMPLEXITY
//   A: Time O(n) - one pass, O(1) average per HashMap call;  Space O(n) - the map
//   B: Time O(n) - one pass;  Space O(1)
//
// GOTCHAS
//   - The check comes BEFORE the put: count is the number of EARLIER copies, so
//     count >= n/2 means the current copy is the (n/2 + 1)-th - more than half.
//   - n = 1: length / 2 = 0, so the first value is returned at once.
//   - Boyer-Moore must let nums[0] vote too - see STATUS.
//
// RELATED
//   - LC 229 Majority Element II - values occurring more than n/3 times: two-candidate
//     voting plus a checking pass (LC229MajorityElementII.java).
//
// SEE ALSO
//   LC169MajorityElementBoyerMoore.java - brute force, HashMap and a CORRECT Boyer-Moore
//   version of this problem
// ============================================================================
public class LC169MajorityElementHashMap {

    public static void main(String[] args) {
        // Example 1 - 3 occurs twice out of three. Expected output: 3
        int[] nums = {3,2,3};
        System.out.println(majorityElement(nums));
    }
    // Approach A: HashMap count that returns as soon as one value passes n/2 copies.
    // O(n) time, O(n) space.
    public static int majorityElement(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        int length = nums.length;
        for (int num : nums) {
            // copies of num seen BEFORE this one
            int count = map.getOrDefault(num, 0);
            // this copy is number count + 1, which is more than length / 2
            if (count >= length / 2) {
                return num;
            }
            map.put(num, count + 1);
        }
        // fallback only - a majority always exists on valid input
        return 0;
    }


    // Approach B: Boyer-Moore voting attempt. BUGGY - see STATUS; main() does not call it.
    public static int majorityElementOptimal(int[] nums) {
        int count = 0;
        int num = nums[0];
        // bug: starting at i = 1 means nums[0] never votes - with count == 0 the first
        // iteration simply replaces num by nums[1]. Starting at i = 0 would fix it.
        for (int i = 1; i < nums.length; i++) {
            if(count == 0){
               count++;
               num = nums[i];
               continue;
            }
            count = num == nums[i] ? count + 1 : count - 1;
        }
        return num;
    }
}
