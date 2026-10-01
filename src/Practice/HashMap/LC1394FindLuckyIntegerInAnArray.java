package Practice.HashMap;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

// ============================================================================
// LC 1394. Find Lucky Integer in an Array   [Easy]
// https://leetcode.com/problems/find-lucky-integer-in-an-array/
// Topics: Array, Hash Table, Counting
// ============================================================================
//
// PROBLEM
//   Call a number "lucky" when it appears in arr exactly as many times as its own value
//   (a 3 that appears exactly 3 times). Return the biggest lucky number, or -1 if the array
//   has none.
//
// EXAMPLES
//   arr = [2,2,3,4]        ->  2     2 appears twice; 3 and 4 appear only once
//   arr = [1,2,2,3,3,3]    ->  3     1, 2 and 3 are all lucky - take the largest
//   arr = [2,2,2,3,3]      -> -1     2 appears 3 times, 3 appears 2 times - no match
//
// CONSTRAINTS
//   1 <= arr.length <= 500,   1 <= arr[i] <= 500
//
// INTUITION
//   Luckiness only depends on how often each value occurs, so count first. After that,
//   every distinct value can be checked on its own: is count == value? The answer is the
//   largest value that passes, and -1 is a safe "none" because every value is >= 1.
//
// APPROACH (as implemented below)
//   1. Build freqMap: value -> number of occurrences.
//   2. Start max at -1 (the "no lucky number" answer).
//   3. For every entry, if its count equals its key and is bigger than max, store it in max.
//   4. Return max.
//
// DRY RUN   main(): arr = [2,2,3,4]
//   freqMap = {2=2, 3=1, 4=1}
//   2=2 : count == value, 2 > -1  -> max = 2
//   3=1 : 1 != 3                  -> skip
//   4=1 : 1 != 4                  -> skip
//   returns 2
//
// STATUS
//   main() does not print the result (it would be 2).
//
// COMPLEXITY
//   Time : O(n) - one counting pass plus one pass over at most n distinct values
//   Space: O(n) - the frequency map (at most 500 keys here)
//
// GOTCHAS
//   - Objects.equals compares the two Integer objects by value. Writing
//     map.getValue() == map.getKey() would compare references, which only works by luck
//     for values -128..127 (the Integer cache) - a 200 appearing 200 times would be missed.
//   - Since count == value in the matched entry, storing getValue() is the same as storing
//     the key itself.
// ============================================================================
public class LC1394FindLuckyIntegerInAnArray {
    public static void main(String[] args) {
        // Example 1 - the answer is 2, but the return value is not printed
        int[] arr = {2,2,3,4};
        findLucky(arr);
    }
    // Returns the largest value whose frequency equals the value itself, or -1.
    public static int findLucky(int[] arr) {
        // value -> how many times it appears
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            freqMap.put(arr[i], freqMap.getOrDefault(arr[i] , 0) + 1);
        }

        // -1 is the answer when no value is lucky
        int max = -1;
        for (Map.Entry<Integer, Integer> map : freqMap.entrySet()){
            // lucky = count equals value (compared by value, not by Integer reference)
            if(Objects.equals(map.getValue(), map.getKey()) && map.getValue() > max){
                max = map.getValue();
            }
        }
        return max;
    }
}
