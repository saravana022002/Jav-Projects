package Practice.HashMap;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

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
//   step to x+1, x+2, ... while the value is present. Each value is stepped over only by
//   the one run it belongs to, so all the inner stepping together is O(n), even though it
//   is a loop inside a loop.
//
// APPROACH (as implemented below - optimalSolution())
//   1. Empty array -> return 0.
//   2. Box the values into the Set hashedNums (duplicates collapse).
//   3. For each num whose num - 1 is absent (a run start): walk currentElement = num,
//      num + 1, ... while it is in the set, counting the steps in currentSeq.
//   4. Keep the largest currentSeq in longestSeq (it starts at 1, since a non-empty array
//      always has a run of length 1) and return it.
//
// DRY RUN   main(): kums = [1,0,1,2]
//   hashedNums = {0,1,2}
//   num=0        : -1 absent -> run start; 0,1,2 present, 3 absent -> currentSeq 3
//                  longestSeq = 3
//   num=1, num=2 : num - 1 present -> skip
//   returns 3
//
// APPROACHES IN THIS FILE
//   A. optimalSolution() - HashSet, count up only from run starts - O(n)
//   B. bruteSolution()   - sort, then one scan that skips repeats and counts +1 steps
//                          - O(n log n)
//   main() runs B and then A on the same input and prints 3 twice.
//
// STATUS
//   Known issue in bruteSolution(): prevElement starts at the sentinel -99, so when the
//   smallest value is -98 it looks like the continuation of a -99 and its run is counted
//   one too long: [-98] returns 2 (expected 1), [-98,-97] returns 3 (expected 2).
//   optimalSolution() is correct. The code is left unchanged on purpose.
//
// COMPLEXITY
//   optimalSolution: Time O(n) average - building the set is O(n), and the inner while
//                    starts only at run starts, so it visits each value once in total.
//                    Space O(n) for the set.
//   bruteSolution  : Time O(n log n) for the sort, then one O(n) scan.
//                    Space O(1) extra besides Arrays.sort's internal work.
//
// GOTCHAS
//   - Duplicates must not break a run: bruteSolution skips a value equal to prevElement,
//     and the set never holds duplicates in the first place.
//   - The run-start check (num - 1 absent) is what keeps the set version O(n); without it
//     input like [1..n] would cost O(n^2).
//   - A sentinel must be a value the input can never contain; values here go down to
//     -10^9, so -99 is not safe. Starting the scan from nums[0] avoids the sentinel.
//   - bruteSolution sorts the caller's array in place (main passes two separate arrays).
//
// SEE ALSO
//   LC128LongestConsecutiveSequenceV1.java - same HashSet idea plus a sort + HashMap version
//   src/Leetcode/HashMapAsLookup3/LC128LongestConsecutive.java - your curated version
// ============================================================================
public class LC128LongestConsecutiveSequenceV2 {
    public static void main(String[] args) {
        // Example 3 through both methods. Expected output: 3, then 3
         int[] nums = {1,0,1,2};
        System.out.println(bruteSolution(nums));
        int[] kums = {1,0,1,2};
        System.out.println(optimalSolution(kums));
    }

    // Approach A: HashSet and count only from run starts. O(n). Returns the longest run.
    public static int optimalSolution(int[] nums) {
        int arrayLength = nums.length;
        if(arrayLength == 0){
            return 0;
        }
        int currentElement;
        int longestSeq = 1;
        int currentSeq;

        // all distinct values, for O(1) "is x present?" checks
        Set<Integer> hashedNums = Arrays.stream(nums)
                .boxed()
                .collect(Collectors.toSet());

        for (int num : hashedNums) {
            // only a run start (num - 1 missing) walks its run; everyone else is skipped
            if (!hashedNums.contains(num - 1)) {
                currentSeq = 0;
                currentElement = num;
                while (hashedNums.contains(currentElement)) {
                    currentSeq++;
                    currentElement = currentElement + 1;
                }
                if (currentSeq > longestSeq) {
                    longestSeq = currentSeq;
                }
            }
        }

        return longestSeq;

    }
    // Approach B: sort, then scan once; a +1 step extends the run, a repeat is skipped,
    // any other jump starts a new run. O(n log n). Sorts nums in place.
    // Wrong when the smallest value is -98 - see STATUS.
    public static int bruteSolution(int[] nums) {
        Arrays.sort(nums);
        int arrayLength = nums.length;
        if(arrayLength == 0){
            return 0;
        }
        // sentinel "previous value" - bug: a real -98 counts as the successor of -99
        int prevElement = -99;
        int longestSeq = 1;
        int currentSeq = longestSeq;
        for (int i = 0; i < arrayLength; i++) {
            // duplicate of the previous value: neither extends nor breaks the run
            if(prevElement == nums[i] ){
                continue;
            }
            if(nums[i] == prevElement + 1 ){
                currentSeq ++;
            }else {
                currentSeq = 1;
            }
            prevElement = nums[i];
            if(currentSeq > longestSeq){
                longestSeq = currentSeq;
            }
        }

        return longestSeq;

    }
}
