package Practice.TwoPointers;

import java.util.*;

// ============================================================================
// LC 15. 3Sum   [Medium]
// https://leetcode.com/problems/3sum/
// Topics: Array, Two Pointers, Sorting
// ============================================================================
//
// PROBLEM
//   Given an integer array nums, return every triplet of values [nums[i], nums[j], nums[k]]
//   taken from three different positions i, j, k that adds up to 0. The answer must not
//   contain the same triplet twice (the same three values count as one triplet whatever the
//   positions), and the order of the triplets does not matter.
//
// EXAMPLES
//   nums = [-1,0,1,2,-1,-4]  ->  [[-1,-1,2],[-1,0,1]]   the two -1 give the same triplet
//                                                       [-1,0,1] twice, but it is listed once
//   nums = [0,1,1]           ->  []                     the only triplet sums to 2
//   nums = [0,0,0]           ->  [[0,0,0]]
//
// CONSTRAINTS
//   3 <= nums.length <= 3000,   -10^5 <= nums[i] <= 10^5
//
// INTUITION
//   Sort the array, then fix the first number nums[i]. What is left is a two-sum question on
//   the sorted rest: find pairs that add up to -nums[i]. On a sorted array one pair of
//   pointers does that in one sweep: left at the start, right at the end; a sum that is too
//   big moves right inwards, a sum that is too small moves left inwards. Sorting also puts
//   equal values side by side, so duplicates are removed simply by skipping repeated values
//   (for the fixed number and for the pointers after a hit) - no set is needed.
//
// APPROACHES IN THIS FILE
//   A. betterSolution()  - for every j, scan i to the right and keep a HashMap of the values
//                          seen between them; a pair (j, i) is completed when -(nums[j] +
//                          nums[i]) is in the map. Each triplet is sorted and added only if
//                          ansFinal.contains() does not have it - see COMPLEXITY
//   B. optimalSolution() - sort, fix i, move left/right pointers, skip duplicates - O(n^2)
//   main() runs both on {1,-1,-1,0}; each prints [[-1, 0, 1]].
//
// APPROACH (as implemented below, method B)
//   1. Arrays.sort(nums) (this reorders the caller's array).
//   2. For i from 0 to size - 3: skip i when nums[i] == nums[i - 1] (that value was already
//      tried as the first number).
//   3. left = i + 1, right = size - 1. While left < right, sum = nums[i] + nums[left] +
//      nums[right]:
//      - sum == 0 : add [nums[i], nums[left], nums[right]]; move left over every equal value
//                   and right over every equal value, then left++ and right-- once more
//      - sum > 0  : right--   (need a smaller value)
//      - sum < 0  : left++    (need a larger value)
//   4. Return the list of triplets.
//
// DRY RUN   B on nums = [-1,0,1,2,-1,-4]   sorted: [-4,-1,-1,0,1,2]   (size 6, i = 0..3)
//   i=0 (-4)  left=1 right=5: -4-1+2 = -3 < 0 -> left++ ... every sum stays < 0 until the
//             pointers meet (-3, -3, -2, -1)                              -> nothing
//   i=1 (-1)  left=2 right=5: -1-1+2 = 0 -> add [-1,-1,2]; no equal neighbours to skip;
//             right=4, left=3: -1+0+1 = 0 -> add [-1,0,1]; pointers cross   -> done
//   i=2 (-1)  equals nums[1] -> skipped (it would repeat the triplets above)
//   i=3 (0)   left=4 right=5: 0+1+2 = 3 > 0 -> right-- -> pointers meet     -> nothing
//   result [[-1,-1,2],[-1,0,1]].   main()'s input {1,-1,-1,0} sorts to [-1,-1,0,1] and yields
//   [[-1,0,1]]: at i=0 the pointers reach left=2 (0), right=3 (1) and -1+0+1 = 0.
//
// COMPLEXITY
//   B: Time O(n^2) - the sort is O(n log n); for each i the two pointers sweep the rest once.
//      Space O(1) besides the answer (the sort is in place)
//   A: Time O(n^2) pair checks with O(1) map work each, but every hit also runs
//      ansFinal.contains(), a linear scan of the T triplets found so far, so the worst case
//      is O(n^2 * T). Measured here on the distinct values -n/2 .. n/2: n = 500 took about
//      1.3 s and n = 1000 about 20 s, while B needed a few ms. Space O(n) for the map.
//
// GOTCHAS
//   - Duplicates are the hard part: skipping a repeated i AND repeated left/right values after
//     a hit is what keeps [-1,-1,2]-style triplets from appearing twice.
//   - The skip loops read nums[left + 1] and nums[right - 1]; the left < right guard keeps
//     those reads inside the array.
//   - B sorts the caller's array in place; A does not touch it (it sorts only a 3-element
//     copy of each triplet so that equal triplets compare equal). In main(), A runs first on
//     the unsorted array, then B sorts it.
//   - An anchor i only goes up to size - 3, because two more elements must follow it.
//
// STATUS
//   Both methods return the right answers (checked against a brute force on random small
//   arrays), but A gets very slow when there are many distinct triplets, because of
//   ansFinal.contains() - see COMPLEXITY; the time grew about 16x from n = 500 to n = 1000,
//   so the n = 3000 limit would be far worse. The method names differ from LeetCode's
//   threeSum, and optimalSolution is private. The helpers minusRight, addI and addLeft are
//   leftovers that nothing calls.
//
// RELATED
//   - LC 1 Two Sum - the hash-map idea behind method A.
//   - LC 16 3Sum Closest - the same sort + two pointers loop, keeping the closest sum instead
//     of an exact zero.
//   - LC 18 4Sum - one more nested loop around the same pointers (solved in this folder).
// ============================================================================
public class LC15ThreeSum {
    public static void main(String[] args) {
        // Both print [[-1, 0, 1]]: the two -1 give only one distinct triplet with 0 and 1
        int[] nums = {1,-1,-1,0};
        System.out.println("Better:-->"+betterSolution(nums));

        System.out.println("Optimal:-->"+optimalSolution(nums));
    }

    // Approach B: sort, fix the first number, then move two pointers inwards, skipping
    // duplicates. O(n^2)
    private static List<List<Integer>> optimalSolution(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> ansFinal = new ArrayList<>();

        int size = nums.length;
        for (int i = 0; i < size - 2; i++) {
            // same first number as the previous round would only repeat its triplets
            if(i > 0 && nums[i] == nums[i - 1]){
                continue;
            }
            int left = i + 1;
            int right = size - 1;
            while(left < right ){
                int sum = nums[left] + nums[right] + nums[i];
                if(sum == 0){
                    ansFinal.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // step over repeated values so the same triplet is not added again
                    while (left < right && nums[left] == nums[left+1]) left++;
                    while (left < right && nums[right] == nums[right-1]) right--;
                    right --;
                    left ++;
                }else if(sum > 0){
                    // too big: a smaller right value is needed
                    right --;
                }else {
                    // too small: a larger left value is needed
                    left ++;
                }
            }
        }
        return ansFinal;
    }

    // Unused leftover helper: index of the first element to the left of `right` whose value
    // differs from nums[right] (it never looks at index 0). B skips duplicates inline instead.
    private static int minusRight(int right, int[] nums) {
        int prevRight = nums[right];
        for (int i = right - 1; i > 0; i--) {
            if (nums[i] != prevRight) {
                return i;
            }
            right = i;
        }
        right = right - 1;
        return right;
    }

    // Unused leftover helper: index of the first element after i whose value differs from
    // nums[i] (skips a run of equal values going forward).
    private static int addI(int i, int[] nums) {
        int prevI = nums[i];
        for (int j = i + 1; j < nums.length; j++) {
            if (nums[j] != prevI) {
                return j;
            }
            i = j;
        }
        i = i + 1;
        return i;
    }

    // Unused leftover helper: does the same as addI (first index after `left` with a
    // different value).
    private static int addLeft(int left, int[] nums) {
        int prevLeft = nums[left];
        for (int i = left + 1; i < nums.length; i++) {
            if (nums[i] != prevLeft) {
                return i;
            }
            left = i;
        }
        left = left + 1;
        return left;
    }
    //nums[i] + nums[j] + nums[k] = 0

    // Approach A: for each first index j, scan i to the right; ansMap holds the values at
    // positions j+1 .. i-1, so finding -(nums[j] + nums[i]) in it completes a triplet.
    // Correct, but the contains() dedupe can be very slow - see COMPLEXITY.
    public static List<List<Integer>> betterSolution(int[] nums) {
        Map<Integer, Integer> ansMap = new HashMap<>();
        List<List<Integer>> ansFinal = new ArrayList<>();
        int temp;
        for (int j = 0; j < nums.length; j++) {
            for (int i = j+1; i < nums.length; i++) {
                // the value that would complete the zero sum with nums[j] and nums[i]
                temp = - (nums[j] + nums[i]);
                // it was seen between j and i -> three different positions add up to 0
                if(ansMap.containsKey(temp)){
                    int[] ans = new int[3];
                    ans[0] = nums[j];
                    ans[1] = temp;
                    ans[2] = nums[i];
                    // sorted copy = canonical form, so equal triplets compare equal
                    Arrays.sort(ans);
                    List<Integer> triplet = Arrays.asList(ans[0], ans[1], ans[2]);
                    // linear scan of everything found so far: the slow part
                    if(!ansFinal.contains(triplet)) {
                        ansFinal.add(triplet);
                    }
                }
                ansMap.put(nums[i], i);
            }
            // a new first index starts with an empty map
            ansMap = new HashMap<>();
        }
        return ansFinal;
    }
}
