package Practice.Arrays;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// ============================================================================
// LC 229. Majority Element II   [Medium]
// https://leetcode.com/problems/majority-element-ii/
// Topics: Array, Hash Table, Sorting, Counting, Boyer-Moore Majority Vote Algorithm
// ============================================================================
//
// PROBLEM
//   Given an integer array nums of length n, return every value that occurs MORE than
//   floor(n / 3) times. There can be none, one or two such values (never more - see
//   INTUITION). Follow-up: do it in O(n) time and O(1) extra space.
//
// EXAMPLES
//   nums = [3,2,3]  ->  [3]     3 occurs 2 times and 2 > floor(3/3) = 1; 2 occurs only once
//   nums = [1]      ->  [1]     floor(1/3) = 0, and one occurrence is more than 0
//   nums = [1,2]    ->  [1,2]   floor(2/3) = 0, so both values qualify
//
// CONSTRAINTS
//   1 <= nums.length <= 5 * 10^4,   -10^9 <= nums[i] <= 10^9
//
// INTUITION
//   At most TWO values can occur more than n/3 times - three of them would need more than
//   n elements in total. So vote with two candidates (LC 169 needs one, for n/2). A number
//   that matches a candidate adds a vote to it; a number that matches neither cancels one
//   vote of EACH candidate, which throws away three different values at once. There are at
//   most n/3 such throw-aways and each removes only one copy of any given value, so a value
//   with more than n/3 copies always survives as a candidate. The reverse is not true: a
//   survivor may be frequent only by luck, so a second pass counts the real occurrences.
//
// APPROACHES IN THIS FILE
//   A. findMajorityBrute(nums)   - count each value with a full scan, keep those above n/3
//                                                                - O(n^2) time, O(1) space
//   B. findMajorityBetter(nums)  - HashMap value -> count, keep the entries above n/3
//                                                                - O(n) time,   O(n) space
//   C. findMajorityOptimal(nums) - two-candidate Boyer-Moore vote plus a checking pass
//                                                                - O(n) time,   O(1) space
//                                  <- main() runs this one (A and B are commented out)
//   (APPROACH and DRY RUN below describe C.)
//
// APPROACH (as implemented below, findMajorityOptimal)
//   1. cnt1 = cnt2 = 0 (votes), element1 = element2 = Integer.MIN_VALUE (candidates;
//      MIN_VALUE means "empty" and lies outside the allowed value range).
//   2. Pass 1 - for each num the FIRST rule that applies is used:
//        cnt1 == 0 and num != element2  ->  element1 = num, cnt1++   (slot 1 is free)
//        cnt2 == 0 and num != element1  ->  element2 = num, cnt2++   (slot 2 is free)
//        num == element1                ->  cnt1++
//        num == element2                ->  cnt2++
//        otherwise                      ->  cnt1--, cnt2--   (num matches neither candidate)
//   3. Pass 2 - reset cnt1 = cnt2 = 0 and count the real occurrences of both candidates.
//   4. res gets element1 if cnt1 > nums.length / 3, and element2 if cnt2 > nums.length / 3.
//
// DRY RUN   nums = [1,1,1,3,3,2,2]   (n = 7, so a value needs more than 7 / 3 = 2 copies)
//   num        1   1   1   3   3   2   2
//   element1   1   1   1   1   1   1   1
//   cnt1       1   2   3   3   3   2   1
//   element2   -   -   -   3   3   3   3        (- = Integer.MIN_VALUE, slot still empty)
//   cnt2       0   0   0   1   2   1   0
//   Pass 2: 1 occurs 3 times (3 > 2, kept), 3 occurs 2 times (2 > 2 is false, dropped).
//   Returns [1]. main()'s [2,2]: element1 = 2 with cnt1 = 2, slot 2 stays empty, 2 > 0 -> [2].
//
// COMPLEXITY
//   C: Time O(n) - two passes;  Space O(1) - four variables and at most two values in res
//   B: Time O(n) - one pass to count, one over the map;  Space O(n) - the map
//   A: Time O(n^2) - a full inner scan for every element;  Space O(1) besides res
//
// GOTCHAS
//   - The checking pass is not optional. On [1,2,3] the vote ends with candidates 1 and 2
//     (counts back at 0), but each occurs once and 1 > 1 is false, so the answer is [].
//   - The threshold uses integer division, so for n = 1 or 2 it is 0 and any value that
//     occurs at all qualifies ([1,2] -> [1,2]).
//   - The guards num != element2 and num != element1 stop one value from taking both
//     slots when a slot has count 0 but still remembers an old value.
//   - Integer.MIN_VALUE as the "empty" marker is safe only because every value is at least
//     -10^9. A slot that is never filled keeps count 0, and 0 > n/3 is never true.
//   - In A, `!res.contains(i)` stops the same value being added once per copy; in B the
//     same check is redundant because map keys are unique.
//
// RELATED
//   - LC 169 Majority Element - the "more than n/2" version, where one candidate is enough
//     (LC169MajorityElementBoyerMoore.java and LC169MajorityElementHashMap.java here).
//
// STATUS
//   All three methods are correct. main() runs only findMajorityOptimal, on [2,2], and
//   prints [2]; the calls to the other two are commented out.
// ============================================================================
public class LC229MajorityElementII {

    public static void main(String[] args) {
        // 2 occurs 2 times, more than floor(2/3) = 0. Prints [2]
        int[] nums = {2,2};
//        System.out.println(findMajorityBrute(nums));
//        System.out.println(findMajorityBetter(nums));
        System.out.println(findMajorityOptimal(nums));
    }

    // Approach C: Boyer-Moore voting with two candidates, then a checking pass.
    // O(n) time, O(1) space. Returns every value that occurs more than n/3 times.
    private static List<Integer> findMajorityOptimal(int[] nums) {
        // two candidates and their not-yet-cancelled votes (MIN_VALUE = slot is empty)
        int cnt1 = 0;
        int cnt2 = 0;
        int element1 = Integer.MIN_VALUE;
        int element2 = Integer.MIN_VALUE;
        // pass 1: vote - the first branch that matches is the one that runs
        for (int num : nums) {
            if (cnt1 == 0 && num != element2) {
                // slot 1 is free: num becomes candidate 1 (the guard keeps the candidates distinct)
                element1 = num;
                cnt1++;
            } else if (cnt2 == 0 && num != element1) {
                // slot 2 is free: num becomes candidate 2
                element2 = num;
                cnt2++;
            } else if (num == element1) {
                // same as candidate 1 -> one more vote
                cnt1++;
            } else if (num == element2) {
                // same as candidate 2 -> one more vote
                cnt2++;
            } else {
                // a third, different value: it cancels one vote of each candidate
                cnt1--;
                cnt2--;
            }
        }
        List<Integer> res = new ArrayList<>();

        // pass 2: the candidates are only possible answers - count how often they really occur
        cnt1 = 0; cnt2 = 0;
        for (int num : nums){
            if(num == element1)
                cnt1++;
            if (num == element2)
                cnt2++;
        }

        // keep a candidate only if it occurs MORE than floor(n / 3) times
        if(cnt1 > nums.length / 3)
            res.add(element1);

        if(cnt2 > nums.length / 3)
            res.add(element2);

        return res;
    }

    // Approach A: brute force - count every value with a full scan and collect those above
    // n/3. O(n^2) time, O(1) extra space.
    private static List<Integer> findMajorityBrute(int[] nums) {
        int cnt = 0;
        List<Integer> res = new ArrayList<>();
        for (int i : nums) {
            // cnt = how many times the value i occurs in nums
            for (int j : nums) {
                if (j == i) {
                    cnt++;
                }
            }
            // add each qualifying value once, however many copies of it there are
            if (cnt > (nums.length / 3) && !res.contains(i)) {
                res.add(i);
            }
            // reset for the next value
            cnt = 0;
        }
        return res;
    }

    // Approach B: count every value in a HashMap, then keep the ones seen more than n/3
    // times. O(n) time, O(n) space.
    private static List<Integer> findMajorityBetter(int[] nums) {
        Map<Integer, Integer> solns = new HashMap<>();
        List<Integer> res = new ArrayList<>();
        // value -> number of occurrences
        for (int num : nums) {
            if (!solns.containsKey(num)) {
                solns.put(num, 1);
            } else {
                solns.put(num, solns.get(num) + 1);
            }
        }

        // the res.contains check is redundant here: every map key is already unique
        for (Map.Entry<Integer, Integer> entry : solns.entrySet()) {
            if (entry.getValue() > nums.length / 3 && !res.contains(entry.getKey())) {
                res.add(entry.getKey());
            }
        }
        return res;
    }
}
