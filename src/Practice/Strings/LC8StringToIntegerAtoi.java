package Practice.Strings;

// ============================================================================
// LC 8. String to Integer (atoi)   [Medium]
// https://leetcode.com/problems/string-to-integer-atoi/
// Topics: String
// ============================================================================
//
// PROBLEM
//   Implement myAtoi(s), which turns a string into a 32-bit signed integer the way C's atoi
//   does:
//     1. ignore leading spaces (only the ' ' character),
//     2. read an optional '-' or '+' sign (no sign means positive),
//     3. read digits until a non-digit or the end of the string; leading zeros are fine, and
//        if no digit was read the result is 0,
//     4. if the value leaves the range [-2^31, 2^31 - 1], clamp it to the nearest end.
//   Library converters such as Integer.parseInt are not allowed; do the conversion by hand.
//
// EXAMPLES
//   s = " -042"          ->  -42     space skipped, '-' read, "042" gives 42
//   s = "1337c0d3"       ->  1337    reading stops at the first non-digit, 'c'
//   s = "words and 987"  ->  0       the first character is a letter, so no digits are read
//
// CONSTRAINTS
//   0 <= s.length <= 200,   s has English letters (both cases), digits, ' ', '+', '-' and '.'
//
// INTUITION
//   Treat the string as a small scanner with four stages that always run in the same order:
//   spaces, then one optional sign, then digits, then stop. The only real difficulty is the
//   32-bit limit. The value is built as sum = sum * 10 + digit, and an int would silently wrap
//   around once it passes 2147483647, so the code asks BEFORE multiplying whether the next
//   value would be too big: Integer.MAX_VALUE / 10 is 214748364 and MAX_VALUE ends in the
//   digit 7, so sum > 214748364, or sum == 214748364 with a digit above 7, means overflow and
//   the answer is clamped right away.
//
// APPROACH (as implemented below)
//   1. i = 0, n = s.length(). While s.charAt(i) == ' ', i++.
//   2. Sign: '-' sets isNegative = true and moves on; '+' just moves on.
//   3. sum = 0. While i < n and s.charAt(i) is between '0' and '9':
//      digit = s.charAt(i) - '0'.
//      If sum > MAX/10 or (sum == MAX/10 and digit > 7), return Integer.MIN_VALUE when
//      isNegative, else Integer.MAX_VALUE. Otherwise sum = sum * 10 + digit and i++.
//   4. Return -sum if isNegative, else sum.
//
// DRY RUN   s = "1337c0d3"   (the input main() uses)
//   no spaces, no sign               i = 0, sum = 0
//   '1'  sum = 0 * 10 + 1 = 1
//   '3'  sum = 1 * 10 + 3 = 13
//   '3'  sum = 13 * 10 + 3 = 133
//   '7'  sum = 133 * 10 + 7 = 1337
//   'c'  not a digit -> the loop stops         return 1337
//   Overflow, s = "-2147483649": '-' sets isNegative; after nine digits sum = 214748364 =
//   MAX/10 and the tenth digit is 9 > 7 -> return Integer.MIN_VALUE (-2147483648).
//
// COMPLEXITY
//   Time : O(n) - each character is looked at once
//   Space: O(1) - a few int variables
//
// GOTCHAS
//   - The overflow test comes BEFORE sum * 10 + digit is evaluated; afterwards the int would
//     already have wrapped around and the damage could not be detected.
//   - The same test serves negatives: the smallest int, -2147483648, has the last digit 8,
//     which is > 7, so it is clamped to Integer.MIN_VALUE - which happens to be exactly the
//     right value.
//   - Only ' ' counts as whitespace. A second sign or a sign after digits is just a non-digit
//     and ends the number: "+-12" gives 0 and "0-1" gives 0.
//   - Leading zeros are harmless: sum simply stays 0.
//
// STATUS
//   Known issue. The code is left unchanged on purpose.
//   An empty string or a string made only of spaces throws StringIndexOutOfBoundsException:
//   after the space-skipping loop the sign check calls s.charAt(i) with i == n, without a
//   bounds check. The constraints allow length 0, so myAtoi("") and myAtoi("   ") should
//   return 0. All other inputs matched a reference implementation in random tests, including
//   the overflow cases.
//
// RELATED
//   - LC 7 Reverse Integer - the same "check for 32-bit overflow before multiplying by 10".
//   - LC 65 Valid Number - a harder parsing problem on the same kind of string.
// ============================================================================
public class LC8StringToIntegerAtoi {
    public static void main(String[] args) {
       // reading stops at the first non-digit, 'c'. Expected output: 1337
       String s = "1337c0d3";
       System.out.println(myAtoi(s));
    }
    // Converts s to a 32-bit int: skip spaces, optional sign, digits, clamp on overflow
    public static int myAtoi(String s) {
        int i = 0, n = s.length();;
        // stage 1: skip leading spaces
        while (i < n && s.charAt(i) == ' '){
            i++;
        }
        // stage 2: optional sign. No bounds check here, so an empty or all-space s throws
        // (see STATUS)
        boolean isNegative = false;
        if(s.charAt(i) == '-'){
            isNegative = true;
            i++;
        }else if (s.charAt(i) == '+'){
            i++;
        }
        // stage 3: read digits until the first non-digit
        int sum = 0;
        while (i < n && s.charAt(i) >= '0' && s.charAt(i) <= '9') {
            int digit = s.charAt(i) - '0';
            // would sum * 10 + digit pass Integer.MAX_VALUE (2147483647)? MAX / 10 = 214748364
            // and MAX ends in 7, so this test catches it before the multiplication
            if(sum > Integer.MAX_VALUE / 10 || (sum == Integer.MAX_VALUE / 10 && digit > 7)){
                return isNegative ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            sum = sum * 10 + digit;
            i++;
        }
        // stage 4: apply the sign
        return isNegative ? -sum : sum;
    }
}
