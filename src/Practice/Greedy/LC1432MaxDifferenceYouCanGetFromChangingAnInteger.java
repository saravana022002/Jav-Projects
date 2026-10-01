package Practice.Greedy;

// ============================================================================
// LC 1432. Max Difference You Can Get From Changing an Integer   [Medium]
// https://leetcode.com/problems/max-difference-you-can-get-from-changing-an-integer/
// Topics: Math, Greedy
// ============================================================================
//
// PROBLEM
//   You get an integer num and edit it twice, independently. One edit = choose a digit x and
//   a digit y (y may equal x) and replace EVERY x in num's decimal form with y. Call the two
//   results a and b. Return the largest possible value of a - b. Neither a nor b may start
//   with a zero, and neither may be 0.
//
// EXAMPLES
//   num = 555      ->  888     a = 999 (5 -> 9), b = 111 (5 -> 1)
//   num = 9        ->  8       a = 9 (9 -> 9, a legal no-op), b = 1 (9 -> 1)
//   num = 1101057  ->  8808050 a = 9909057, b = 1101007   (what main() runs)
//
// CONSTRAINTS
//   1 <= num <= 10^8
//
// INTUITION
//   The two edits do not affect each other, so make a as large as possible and b as small as
//   possible, then subtract. A replacement hits every copy of a digit, and the leftmost digit
//   is worth the most, so decide by the leftmost digit that can still improve:
//     largest : the first digit that is not 9 becomes 9 (all its copies too).
//     smallest: the leading digit cannot become 0, so turn it into 1 (all its copies too).
//               If it already is 1 it must stay 1 - then take the first later digit that is
//               neither 0 nor 1 and turn it (and all its copies) into 0. Zeros are already
//               as small as they get, and a 1 cannot change because it is the leading digit.
//
// APPROACH (as implemented below)
//   1. maxDiff = maxNum(num) - minNum(num).
//   2. maxNum: copy the digits into char[] characters. Scan for the first digit that is not
//      '9' at index j; remember it in `mapper` and overwrite it with '9'. Then overwrite every
//      digit from j on that equals mapper. (All digits before j are already 9, so these are all
//      the copies.) If every digit is 9, nothing changes.
//   3. minNum, leading digit != '1': mapper = characters[0]; overwrite every copy with '1'.
//   4. minNum, leading digit == '1': scan j = 1.. for the first digit that is not '0' and not
//      characters[0]; that is mapper. Overwrite it and every later copy with '0'. If there is
//      no such digit the number is already the smallest.
//   5. Both helpers rebuild the number with number = number * 10 + (c - '0').
//
// DRY RUN   num = 1101057   (main() prints 8808050)
//   maxNum: characters = 1 1 0 1 0 5 7; index 0 is '1' (not 9) -> mapper = '1'
//           every '1' becomes '9'  -> 9 9 0 9 0 5 7 = 9909057
//   minNum: leading digit is '1', so scan from j = 1: '1' equals the leading digit, '0' is
//           skipped, '1' skipped, '0' skipped, '5' at j = 5 qualifies -> mapper = '5'
//           every '5' becomes '0'  -> 1 1 0 1 0 0 7 = 1101007
//   9909057 - 1101007 = 8808050
//
// COMPLEXITY
//   Time : O(d) with d = number of digits (at most 9), i.e. O(log10 num) - each helper makes
//          a few passes over the digits.
//   Space: O(d) - the digit string and the char[] copy.
//
// GOTCHAS
//   - One edit changes all copies of a digit (555 -> 999), which is why only the FIRST
//     improvable digit matters.
//   - For the minimum, a leading '1' must not be touched, and any other '1' is a copy of it,
//     so the scan skips every digit equal to characters[0].
//   - Turning the leading digit into 0 would be illegal, which is why the minimum uses 1
//     there rather than 0.
//   - num = 9 or 99 (all nines): the max is unchanged; the min turns the nines into 1s.
//   - Largest possible values stay inside int (at most 900000000 for num = 10^8).
//
// RELATED
//   - LC 2566 Maximum Difference by Remapping a Digit - the same "biggest minus smallest after
//     replacing one digit everywhere" idea, but leading zeros are allowed there
//     (solved in LC2566MaximumDifferenceByRemappingADigit.java in this folder).
// ============================================================================
public class LC1432MaxDifferenceYouCanGetFromChangingAnInteger {
    public static void main(String[] args) {
        // max 9909057 - min 1101007 -> prints 8808050
        int num = 1101057;
        System.out.println(maxDiff(num));
    }

    // Largest reachable value minus smallest reachable value.
    public static int maxDiff(int num) {
        return maxNum(num) - minNum(num);
    }
    // Largest value after one edit: the first digit that is not 9 (and its copies) becomes 9.
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

    // Smallest legal value after one edit (no leading zero, never 0).
    public static int minNum(int num){
        String strNum = String.valueOf(num);
        char[] characters = strNum.toCharArray();
        char mapper = 's';
        int j = 0;

        if(characters[0] != '1'){
            // leading digit is not 1: make it (and all its copies) 1 - it cannot become 0
            mapper = characters[0];
            for (int i = 0; i < characters.length; i++) {
                if (characters[i] == mapper) {
                    characters[i] = '1';
                }
            }
        }else {
            // leading digit is 1 and must stay: find the first later digit that is neither
            // 0 nor 1 (skipping digits equal to characters[0]) and turn it into 0
            for (j = 1; j < characters.length; j++) {
                if (characters[j] != '0' && characters[j] != characters[0]) {
                    mapper = characters[j];
                    characters[j] = '0';
                    break;
                }
            }
            // turn the remaining copies of that digit into 0 (nothing to do if none was found)
            for (int i = j; i < characters.length; i++) {
                if (characters[i] == mapper) {
                    characters[i] = '0';
                }
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
