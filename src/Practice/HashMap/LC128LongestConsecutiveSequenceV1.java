package Practice.HashMap;

import java.util.*;

// ============================================================================
// LC 128. Longest Consecutive Sequence   [Medium]
// https://leetcode.com/problems/longest-consecutive-sequence/
// Topics: Array, Hash Table, Union-Find
// ============================================================================
//
// PROBLEM
//   You get an unsorted int array nums. Pick values from anywhere in it that form a run of
//   consecutive integers (x, x+1, x+2, ...) - positions do not matter and a repeated value
//   counts only once. Return the length of the longest such run (0 for an empty array).
//   The problem asks for an O(n) algorithm, so plain sorting is not the intended answer.
//
// EXAMPLES
//   nums = [100,4,200,1,3,2]        ->  4    run 1,2,3,4
//   nums = [0,3,7,2,5,8,4,6,0,1]    ->  9    run 0..8 (the second 0 adds nothing)
//   nums = [1,0,1,2]                ->  3    run 0,1,2
//
// CONSTRAINTS
//   0 <= nums.length <= 10^5,   -10^9 <= nums[i] <= 10^9
//
// INTUITION
//   Put every value in a HashSet so "is x present?" costs O(1). Count each run once, from
//   its smallest value: x starts a run exactly when x - 1 is NOT in the set. From a start,
//   step to x+1, x+2, ... while the next value is present. Each value is stepped over only
//   by the one run it belongs to, so all the inner stepping together is O(n), even though
//   it is a loop inside a loop.
//
// APPROACH (as implemented below - longestConsecutiveAmr(), the one main() runs)
//   1. Copy all values into the HashSet list (duplicates collapse).
//   2. For each k in the set: if k - 1 is also in the set, k sits inside a run - skip it.
//   3. Otherwise k starts a run: len = 1, hello = k, and while hello + 1 is in the set,
//      increase both.
//   4. Keep the largest len in max and return it.
//
// DRY RUN   main(): nums = [100,4,200,1,3,2]
//   set = {1,2,3,4,100,200}   (iteration order may differ - the result does not)
//   k=1      : 0 absent -> run start; 2,3,4 present, 5 absent -> len 4    max = 4
//   k=2,3,4  : k - 1 present -> skip
//   k=100    : 99 absent -> run start; 101 absent -> len 1                max = 4
//   k=200    : 199 absent -> run start; 201 absent -> len 1               max = 4
//   returns 4
//
// APPROACHES IN THIS FILE
//   A. longestConsecutive()    - sort, then run length ending at n = (run ending at n-1) + 1,
//                                kept in a HashMap - O(n log n)
//   B. longestConsecutiveAmr() - HashSet, count up only from run starts - O(n)
//                                <- main() runs this one
//
// STATUS
//   Known issue: longestConsecutiveAmr() starts max at Integer.MIN_VALUE, so an empty array
//   (allowed: nums.length can be 0) returns -2147483648 instead of 0. longestConsecutive()
//   returns 0 there. The code is left unchanged on purpose.
//   main() does not print the result (it would be 4).
//
// COMPLEXITY
//   longestConsecutiveAmr: Time O(n) average - n set inserts, and the inner while starts
//                          only at run starts, so it steps over each value once in total.
//                          Space O(n) for the set.
//   longestConsecutive   : Time O(n log n) for the sort; the map pass after it is O(n).
//                          Space O(n) for the map.
//
// GOTCHAS
//   - Duplicates: the set drops them. In the sort version a repeated n just rewrites the
//     same run length, so [1,1,2] still gives 2.
//   - Without the "k - 1 present -> skip" check every value would walk the rest of its run
//     and input like [1..n] would cost O(n^2).
//   - longestConsecutive() sorts the caller's array in place.
//
// SEE ALSO
//   LC128LongestConsecutiveSequenceV2.java - same HashSet idea plus a sort-and-scan version
//   src/Leetcode/HashMapAsLookup3/LC128LongestConsecutive.java - your curated version
// ============================================================================
public class LC128LongestConsecutiveSequenceV1 {
    public static void main(String[] args) {
        // Example 1 - the answer is 4 (run 1,2,3,4), but the return value is not printed
        int[] nums = {100,4,200,1,3,2};
        longestConsecutiveAmr(nums);
    }
    // Approach A: sort first, so n - 1 (if present) is always processed before n.
    // O(n log n). Returns 0 for an empty array. Note: sorts nums in place.
    public static int longestConsecutive(int[] nums) {
        Arrays.sort(nums);
        // map: value -> length of the consecutive run that ENDS at that value
        Map<Integer, Integer> map = new HashMap<>();
        int max = 0;
        for(int n : nums){
            // extend the run that ends at n - 1 (0 if n - 1 is absent); a repeated n
            // writes the same length again, so duplicates do no harm
            map.put(n, map.getOrDefault(n - 1, 0) + 1);
            max = Math.max(max, map.getOrDefault(n, 0));
        }
        return max;
    }

    // Approach B: HashSet and count only from run starts. O(n). main() runs this one.
    // Returns Integer.MIN_VALUE instead of 0 for an empty array - see STATUS.
    public static int longestConsecutiveAmr(int[] nums) {
        // despite the name, list is a HashSet: O(1) lookups and no duplicates
        Set<Integer> list = new HashSet<>();
        for (int i : nums) list.add(i);
        // stays Integer.MIN_VALUE when nums is empty (should be 0)
        int max = Integer.MIN_VALUE;
        for (int k : list){
            // k - 1 exists -> k is inside a run that is counted from the run's start
            if(list.contains(k - 1)){
                continue;
            }
            // k starts a run: hello walks upward while the next value exists
            int len = 1;
            int hello = k;
            while (list.contains(hello + 1)){
                len++;
                hello++;
            }
            max = Math.max(len, max);
        }
        return max;
    }
}
