package Practice.HashMap;

import java.util.HashMap;
import java.util.Map;

// ============================================================================
// LC 3583. Count Special Triplets   [Medium]
// https://leetcode.com/problems/count-special-triplets/
// Topics: Array, Hash Table, Counting
// ============================================================================
//
// PROBLEM
//   Count the index triples (i, j, k) with i < j < k where nums[i] and nums[k] both equal
//   twice nums[j]. Return that count modulo 10^9 + 7.
//
// EXAMPLES
//   nums = [6,3,6]        ->  1    only (0,1,2): 6 = 2*3 on both sides of the 3
//   nums = [0,1,0,0]      ->  1    only (0,2,3): 0 = 2*0, so the three zeros form one triple
//   nums = [8,4,2,8,4]    ->  2    (0,1,3): 8,4,8   and   (1,2,4): 4,2,4
//
// CONSTRAINTS
//   3 <= n == nums.length <= 10^5,   0 <= nums[i] <= 10^5
//
// INTUITION
//   Fix the MIDDLE index j. The triple needs the value 2*nums[j] somewhere to its left and
//   somewhere to its right, and any left choice pairs with any right choice, so j adds
//       (how many earlier elements equal 2*nums[j]) * (how many later ones do).
//   Every triple has exactly one middle index, so summing over j counts each one once. Both
//   counts come from a sweep with a running frequency map: one from the left, one from the
//   right. In the code "above" means earlier (left) and "below" means later (right).
//
// APPROACH (as implemented below)
//   1. Sweep left -> right with freq (value -> times seen so far): freqAbove[i] = the count
//      of 2*nums[i] in freq, or 0; THEN add nums[i] to freq.
//   2. Sweep right -> left the same way with freq1: freqBelow[i] = the count of 2*nums[i]
//      among the later elements; then add nums[i].
//   3. For each middle index i = 1 .. n-2 with both counts > 0:
//      count = (count + freqAbove[i] * freqBelow[i]) % MODULO, multiplying as long.
//   4. Return (int) count.
//
// DRY RUN   nums = [0,1,0,0]   (what main() runs)
//   left -> right : i=0 (0): no 0 yet -> 0     freq = {0=1}
//                   i=1 (1): no 2 yet -> 0     freq = {0=1, 1=1}
//                   i=2 (0): one 0 seen -> 1   freq = {0=2, 1=1}
//                   i=3 (0): two 0s seen -> 2  => freqAbove = [0,0,1,2]
//   right -> left : i=3 (0): none -> 0, i=2 (0): one 0 after -> 1,
//                   i=1 (1): no 2 after -> 0, i=0 (0): two 0s after -> 2
//                   => freqBelow = [2,0,1,0]
//   middle indices 1..2: i=1 has freqAbove 0 -> skipped; i=2 adds 1 * 1 -> count = 1
//   return 1 (the triple (0,2,3))
//
// COMPLEXITY
//   Time : O(n) - three linear passes, O(1) average per HashMap operation
//   Space: O(n) - two int arrays plus two maps (at most n distinct values each)
//
// GOTCHAS
//   - Read the running map BEFORE adding nums[i]; otherwise an element counts itself, which
//     matters for 0 because 2*0 == 0.
//   - A product can reach (10^5) * (10^5) = 10^10, so the two counts are copied into longs
//     (frqAbove, frqBelow) before multiplying, and the sum is reduced by MODULO every step.
//   - nums[i] * 2 is at most 2 * 10^5, so the lookup key cannot overflow an int.
//   - Index 0 has nothing on its left and index n-1 nothing on its right, which is why the
//     loop only visits 1 .. n-2; the "> 0" test is just a shortcut (the product would be 0).
//
// STATUS
//   Correct (checked against an O(n^3) brute force on random inputs). The commented-out class
//   Freq inside the file is an unused leftover; the arrays hold the same two counts.
// ============================================================================
public class LC3583CountSpecialTriplets {

    public static void main(String[] args) {
        // Example 2 - the only triple is (0, 2, 3). Expected output: 1
        int[] nums = {0,1,0,0};
        System.out.println(specialTriplets(nums));
    }

    // Unused leftover (commented-out): one object per index holding the two counts.
//    class Freq{
//        Freq(){
//        }
//       int freqAbove;
//       int freqBelow;
//       void setFreqAbove(int freqAbove){
//          this.freqAbove = freqAbove;
//       }
//        void setFreqBelow(int freqBelow){
//            this.freqBelow = freqBelow;
//        }
//    }
    // For every middle index i: (earlier elements equal to 2*nums[i]) * (later ones),
    // summed modulo 10^9 + 7.
    public static int specialTriplets(int[] nums) {
        // left -> right: freq holds the values seen BEFORE index i
        Map<Integer, Integer> freq = new HashMap<>();
        int[] freqAbove = new int[nums.length];
        for(int i = 0; i < nums.length; i++){
            // earlier elements equal to twice nums[i]
            freqAbove[i] = freq.get(nums[i] * 2) != null ? freq.get(nums[i] * 2) : 0;
            // added only after the read above, so index i never counts itself
            freq.put(nums[i], freq.getOrDefault(nums[i], 0) + 1);
        }

        // right -> left: freq1 holds the values seen AFTER index i
        Map<Integer, Integer> freq1 = new HashMap<>();
        int[] freqBelow = new int[nums.length];
        for(int i = nums.length - 1; i >= 0; i--){
            // later elements equal to twice nums[i]
            freqBelow[i] = freq1.get(nums[i] * 2) != null ? freq1.get(nums[i] * 2) : 0;
            freq1.put(nums[i], freq1.getOrDefault(nums[i], 0) + 1);
        }

        long count = 0;
        long MODULO = 1_000_000_007;
        // i is the middle index j of the triple; the two ends cannot be a middle
        for(int i = 1; i <= nums.length - 2; i++){
            // widened to long: the product can reach 10^10
            long frqAbove = freqAbove[i];
            long frqBelow = freqBelow[i];
            if( frqAbove > 0 && frqBelow > 0){
                // every left choice pairs with every right choice
                count = (count + frqAbove * frqBelow) % MODULO;
            }
        }
        return (int) count;
    }
}
