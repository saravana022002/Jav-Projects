package Practice.Greedy;

import java.util.List;

// ============================================================================
// LC 2566. Maximum Difference by Remapping a Digit   [Easy]
// https://leetcode.com/problems/maximum-difference-by-remapping-a-digit/
// Topics: Math, Greedy
// ============================================================================
//
// PROBLEM
//   Bob remaps exactly one digit d1 (0-9) of num to another digit d2: every d1 in num is
//   replaced by d2. He may map a digit to itself, so num can stay unchanged. Return the
//   largest number he can make minus the smallest number he can make. The largest and the
//   smallest may use different remaps, and the results may have leading zeros.
//
// EXAMPLES
//   num = 11891  ->  99009     max: 1 -> 9 gives 99899;  min: 1 -> 0 gives 00890 = 890
//   num = 90     ->  99        max: 0 -> 9 gives 99;     min: 9 -> 0 gives 00 = 0
//
// CONSTRAINTS
//   1 <= num <= 10^8
//
// INTUITION
//   The leftmost digits weigh the most, so look for the first digit that can still be
//   improved - and since a remap hits every copy of that digit, all its copies change too.
//     largest : the first digit that is not 9 becomes 9 (leading zeros are harmless, so
//               there is nothing to protect).
//     smallest: the first digit that is not 0 becomes 0. num >= 1 has no leading zeros,
//               so that is always the leading digit, and it may legally become 0 here.
//   Then answer = largest - smallest.
//
// APPROACH (as implemented below)
//   1. minMaxDifference = maxNum(num) - minNum(num).
//   2. maxNum: copy the digits into char[] characters; find the first digit that is not '9'
//      at index j, store it in `mapper` and overwrite it with '9'; then overwrite every digit
//      from j on that equals mapper. (Digits before j are all 9, so these are all its
//      copies.) If every digit is 9 nothing changes.
//   3. minNum: the same, with the first digit that is not '0' and the replacement '0'.
//   4. Each helper rebuilds the number with number = number * 10 + (c - '0'), so leading
//      zeros simply vanish numerically.
//
// DRY RUN   num = 90   (main() prints 99)
//   maxNum: characters = 9 0; '9' is skipped, '0' at j = 1 is not 9 -> mapper = '0'
//           overwrite it with '9'  -> 9 9 = 99
//   minNum: characters = 9 0; '9' at j = 0 is not '0' -> mapper = '9'
//           overwrite it with '0'  -> 0 0 = 0 (no other 9 to change)
//   99 - 0 = 99
//
// COMPLEXITY
//   Time : O(d) with d = number of digits (at most 9), i.e. O(log10 num) - a few passes.
//   Space: O(d) - the digit string and the char[] copy.
//
// GOTCHAS
//   - A remap changes ALL copies of the digit (11891 -> 99899, not 19891).
//   - Leading zeros are allowed in this problem, so nothing special is needed for the minimum;
//     it can even be 0 (num = 90 or 9). LC 1432 forbids that and needs extra rules.
//   - If num is all 9s the maximum is num itself (no digit to improve).
//   - The largest value is at most 900000000 (for num = 10^8), which fits in an int.
//   - The unused import java.util.List is harmless.
//
// RELATED
//   - LC 1432 Max Difference You Can Get From Changing an Integer - same "biggest minus
//     smallest after replacing a digit everywhere" idea, but the result may not start with
//     0 (solved in LC1432MaxDifferenceYouCanGetFromChangingAnInteger.java in this folder).
// ============================================================================
public class LC2566MaximumDifferenceByRemappingADigit {
    public static void main(String[] args) {
        // Example 2 - max 99, min 0. Expected output: 99
        int num = 90;
        System.out.println(minMaxDifference(num));
    }

    // Largest reachable value minus smallest reachable value.
    public static int minMaxDifference(int num) {
        return maxNum(num) - minNum(num);
    }

    // Largest value after one remap: the first digit that is not 9 (and its copies) becomes 9.
    public static int maxNum(int num){
        String strNum = String.valueOf(num);
        char[] characters = strNum.toCharArray();
        char mapper = 's';
        int j = 0;
        // find the leftmost digit that is not already 9 and turn it into 9
        for (j = 0; j < characters.length; j++) {
            if(characters[j] != '9'){
                mapper = characters[j];
                characters[j] = '9';
                break;
            }
        }

        // turn the remaining copies of that digit into 9 as well
        for (int i = j; i < characters.length; i++) {
            if(characters[i] == mapper){
                characters[i] = '9';
            }
        }
        // digits back to an int
        int number = 0;
        for (char c : characters) {
            number = number * 10 + (c - '0');
        }
        return number;
    }

    // Smallest value after one remap: the first digit that is not 0 (and its copies) becomes 0.
    public static int minNum(int num){
        String strNum = String.valueOf(num);
        char[] characters = strNum.toCharArray();
        char mapper = 's';
        int j = 0;
        // find the leftmost digit that is not already 0 (always the leading digit) -> 0
        for (j = 0; j < characters.length; j++) {
            if(characters[j] != '0'){
                mapper = characters[j];
                characters[j] = '0';
                break;
            }
        }

        // turn the remaining copies of that digit into 0 as well
        for (int i = j; i < characters.length; i++) {
            if(characters[i] == mapper){
                characters[i] = '0';
            }
        }
        // digits back to an int
        int number = 0;
        for (char c : characters) {
            number = number * 10 + (c - '0');
        }
        return number;
    }

}
