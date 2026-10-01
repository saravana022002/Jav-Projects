package Practice.TwoPointers;

// ============================================================================
// LC 27. Remove Element   [Easy]
// https://leetcode.com/problems/remove-element/
// Topics: Array, Two Pointers
// ============================================================================
//
// PROBLEM
//   Given an integer array nums and a value val, remove every occurrence of val IN PLACE.
//   The order of the remaining elements may change. Return k, the number of elements that
//   are not equal to val; the first k slots of nums must hold exactly those elements (in any
//   order), and whatever sits after index k - 1 does not matter.
//
// EXAMPLES
//   nums = [3,2,2,3],         val = 3  ->  k = 2, nums starts with [2,2]
//   nums = [0,1,2,2,3,0,4,2], val = 2  ->  k = 5, nums starts with 0,1,4,0,3 in any order
//
// CONSTRAINTS
//   0 <= nums.length <= 100,   0 <= nums[i] <= 50,   0 <= val <= 100
//
// INTUITION
//   Only the survivors (values different from val) have to end up in the first k slots; their
//   order is free and the tail is ignored. So the job is "gather the survivors at the front".
//   There are three ways to do it: copy them forward one by one, plug every hole with the last
//   element of the array, or copy them through a scratch array.
//
// APPROACHES IN THIS FILE
//   A. removeElement()        - write pointer l: every num != val is written to nums[l++]
//                               - O(n) time, O(1) space, keeps the order
//   B. removeElementOptimal() - scan i from the front; when nums[i] == val, overwrite it with
//                               the last element still in play and shrink the end n - O(n)
//                               time, O(1) space, fewest writes   <- main() runs this one
//   C. removeElementBrute()   - copy the survivors into a new array anss, then copy them back
//                               - O(n) time, O(n) extra space
//
// APPROACH (as implemented below, method B)
//   1. i = 0 is the slot being decided; n = nums.length - 1 is the last index still in play.
//   2. While i <= n:
//      - nums[i] == val : nums[i] = nums[n], then n-- (the last element fills the hole and the
//                         range shrinks). i does NOT move: the moved value is not checked yet.
//      - otherwise      : nums[i] is a survivor, i++.
//   3. Return i: slots 0 .. i-1 hold the survivors.
//
// DRY RUN   B on nums = [3,2,2,3], val = 3   (the input main() uses)
//   i=0 n=3  nums[0] = 3 = val  -> nums[0] = nums[3] = 3, n = 2     [3,2,2,3]
//   i=0 n=2  nums[0] = 3 = val  -> nums[0] = nums[2] = 2, n = 1     [2,2,2,3]
//   i=0 n=1  nums[0] = 2        -> i = 1
//   i=1 n=1  nums[1] = 2        -> i = 2
//   i=2 > n=1 -> stop.   return 2; the first two slots are [2,2]
//
// COMPLEXITY
//   A: Time O(n), Space O(1)
//   B: Time O(n) - every step either advances i or shrinks n, so at most nums.length steps;
//      Space O(1)
//   C: Time O(n), Space O(n) for the scratch array anss
//
// GOTCHAS
//   - In B, after the last element is moved into slot i, i stays put: that element may itself
//     equal val (for example [3,3]) and has to be tested too.
//   - nums[n--] reads nums[n] first and decrements n afterwards.
//   - B changes the order of the survivors, A and C keep it; the problem allows any order.
//   - The slots after index k - 1 are leftovers and differ between methods; for Example 2,
//     A and C end as [0,1,3,0,4,0,4,2] (survivors in their old order) and B as
//     [0,1,4,0,3,0,4,2]. Only the first k = 5 slots count.
//   - Empty input is fine: in B n = -1 so the loop never runs and 0 is returned.
//
// STATUS
//   All three methods are correct (checked against a filter-and-compare reference on random
//   small arrays). main() calls B and prints 2.
//
// SEE ALSO
//   LC27RemoveElementTwoEndsSwap.java - the opposite-ends version: swaps a val found at the
//     front with a non-val at the back and returns the front index
//
// RELATED
//   - LC 26 Remove Duplicates from Sorted Array - the same write pointer, keeping the first of
//     each value (solved in this folder).
//   - LC 283 Move Zeroes - gathers the non-zero elements at the front with a write pointer
//     (solved in this folder).
// ============================================================================
public class LC27RemoveElementThreeApproaches {
    public static void main(String[] args) {
        // Example 1: the two 3s are removed, k = 2. Expected output: 2
        int[] nums = {3,2,2,3};
        int val = 3;
        System.out.println(removeElementOptimal(nums, val));
    }
    // Approach A: forward write pointer - copy every survivor to the next free front slot.
    // Keeps the order. O(n)
    public static int removeElement(int[] nums, int val) {
        int l = 0;
        for (int num : nums) {
            if (num == val) {
                continue;
            }
            // l never passes the element being read, so nothing unread is overwritten
            nums[l] = num;
            l++;
        }
        return l;
    }


    // Approach B: a removed slot is refilled with the last element and the end shrinks, so
    // each element is moved at most once. O(n)
    public static int removeElementOptimal(int[] nums, int val) {
        int i = 0;
        int n = nums.length - 1;
        while (i <= n){
            if(nums[i] == val){
                // the last element fills the hole; i stays, because it must be checked too
                nums[i] = nums[n--];
            }else {
                i++;
            }
        }
        return i;
    }


    // Approach C: collect the survivors in a scratch array, then copy them back. O(n) time,
    // O(n) extra space
    public static int removeElementBrute(int[] nums, int val) {
        int k = 0;
        int[] anss = new int[nums.length];
        for (int num : nums) {
            if (num == val) {
                continue;
            }
            anss[k] = num;
            k++;
        }
        // copy the first k survivors back into nums
        int l = 0;
        for (int ans : anss) {
            if(l >= k){
              break;
            }
            nums[l] = ans;
            l++;
        }
        return l;
    }
}
