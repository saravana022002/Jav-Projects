package Practice.HashMap;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// ============================================================================
// LC 217. Contains Duplicate   [Easy]
// https://leetcode.com/problems/contains-duplicate/
// Topics: Array, Hash Table, Sorting
// ============================================================================
//
// PROBLEM
//   Given an int array nums, return true if some value shows up at least twice, and false
//   if all the values are different.
//
// EXAMPLES
//   nums = [1,2,3,1]                 ->  true     1 sits at indices 0 and 3
//   nums = [1,2,3,4]                 ->  false    every value is distinct
//   nums = [1,1,1,3,3,4,3,2,4,2]     ->  true     several values repeat
//
// CONSTRAINTS
//   1 <= nums.length <= 10^5,   -10^9 <= nums[i] <= 10^9
//
// INTUITION
//   "Duplicate" just means "I have seen this value before". So remember the values seen so
//   far while walking the array: if the current value is already remembered we are done,
//   otherwise remember it. A hash lookup costs O(1), so one pass is enough.
//   (Alternatives: sort first so equal values become neighbours - O(n log n), no extra
//   memory - or compare every pair - O(n^2).)
//
// APPROACH (as implemented below - containsDuplicateBrute(), which is really the hash one)
//   1. ints: an empty HashMap used as a set. Only its keys matter; the stored 1 is never read.
//   2. For each num in nums: if ints already contains it, return true.
//   3. Otherwise ints.put(num, 1) and go on.
//   4. The loop ended without a repeat -> return false.
//
// DRY RUN   nums = [1,2,3,1]   (what main() runs)
//   num=1: not in ints -> put   ints = {1}
//   num=2: not in ints -> put   ints = {1,2}
//   num=3: not in ints -> put   ints = {1,2,3}
//   num=1: already in ints      -> return true
//
// COMPLEXITY
//   Time : O(n) - one pass with O(1) average hash work per value (stops at the first repeat)
//   Space: O(n) - when all values are distinct, every one of them is stored
//
// GOTCHAS
//   - Despite its name, containsDuplicateBrute() is not brute force: it is the O(n) hash solution.
//   - The map's value (always 1) is never used; a HashSet would do the same job.
//   - A one-element array cannot contain a duplicate: [7] -> false.
//
// STATUS
//   Correct. main() calls containsDuplicateBrute() but does not print the result (it would
//   be true). The two commented-out methods at the bottom, containsDuplicateBetter() and
//   containsDuplicateOptimal(), are empty stubs - only the hash-based method was written.
//   The imports HashSet and Set are unused.
//
// RELATED
//   - LC 219 Contains Duplicate II - the repeats must also be at most k positions apart
//     (src/Leetcode/HashMapAsLookup3/Lc219ContainsDuplicate2.java).
//   - LC 220 Contains Duplicate III - near-equal values within an index window
//     (src/Leetcode/HashMapAsLookup3/LC220ContainsDuplicate3.java).
//
// SEE ALSO
//   src/Leetcode/HashMapAsLookup3/LC217ContainsDuplicate.java - your curated version
// ============================================================================
public class LC217ContainsDuplicate {
    public static void main(String[] args) {
        // Example 1 - nothing is printed; the method returns true for this input
        int[] nums = {1,2,3,1};
        containsDuplicateBrute(nums);
    }

    // Returns true as soon as a value is seen a second time (hash-based, O(n)).
    public static boolean containsDuplicateBrute(int[] nums) {
        // used as a set: the keys are the values seen so far
        Map<Integer, Integer> ints = new HashMap<>();
        for (int num : nums) {
            if (ints.containsKey(num)) {
                // seen before -> duplicate found
                return true;
            } else {
                ints.put(num, 1);
            }
        }
        // every value was new
        return false;
    }
    // Empty stubs below were never filled in; they are left as they were.
//    public static boolean containsDuplicateBetter(int[] nums) {
//
//    }

//    public static boolean containsDuplicateOptimal(int[] nums) {
//
//    }
}
